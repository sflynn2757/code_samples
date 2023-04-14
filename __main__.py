#this file works as an entry point script for the app

import pathlib
import sys

from SiteConnector.cli import read_user_cli_args, display_check_result
from SiteConnector.checker import site_is_online

def main():
    """Run SiteConnector."""
    user_args = read_user_cli_args()
    urls = _get_websites_urls(user_args)
    if not urls:
        print("Error: no URLs to check", file=sys.stderr)
        sys.exit(1)
    _synchronous_check(urls)

def _get_websites_urls(user_args):
    """Helper function to store URLs from input specifically"""
    urls = user_args.urls
    #input checking
    if user_args.input_file:
        #checks if there is also a file to parse
        urls += _read_urls_from_file(user_args.input_file)
    return urls

def _read_urls_from_file(file):
    """Helper function to store URLs from a file specifically"""
    #convert file object to pathlib.Path object to further process
    file_path = pathlib.Path(file)
    #check if file exists
    if file_path.is_file():
        with file_path.open() as urls_file:
            #create list of urls in the file that are parsed
            #while eliminating any whitespace
            urls = [url.strip() for url in urls_file]
            if urls:
                #either print the urls, ending the function
                return urls
            #or, if it gets to here, print error
            print(f"Error: empty input file, {file}", file = sys.stderr)
    else:
        print("Error input file not found", file = sys.stderr)
    return []

def _synchronous_check(urls):
    for url in urls:
        error = ""
        try:
            result = site_is_online(url)
        except Exception as e:

            result = False
            error = str(e)
        display_check_result(result, url, error)

if __name__ == "__main__":
    main()