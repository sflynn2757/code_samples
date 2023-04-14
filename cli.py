#this file contains the command line application for usage

import argparse

def read_user_cli_args():
    """Handle all the CLI arguments and options."""
    parser = argparse.ArgumentParser(
        prog="SiteConnector", description="check the availability of websites"
    )
    parser.add_argument(
        "-u",
        "--urls",
        metavar="URLs",
        nargs="+",
        type=str,
        default=[],
        help="enter one or mroe website URLs"
    )
    parser.add_argument(
        "-f",
        "--input-file",
        metavar="FILE",
        type=str,
        default="",
        help="read URLs from a file"
    )
    return parser.parse_args()

def display_check_result(result, url, error=""):
    """Display the result of the connectivity check."""
    print(f'The status of "{url}" is:', end=" ")
    if result:
        print('"Online!"')
    else:
        print(f'"Offline?" /n Error: "{error}"')