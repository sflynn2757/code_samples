#this file provides the applications core functionality

#import connection functions to establish http connections with site
from http.client import HTTPConnection
#parses urls
from urllib.parse import urlparse


#function returns true if target URL is online, raise an exception otherwise
def site_is_online(url, timeout=2):

    error = Exception("unknown error")
    parser = urlparse(url)
    host = parser.netloc or parser.path.split("/")[0]
    #check both unsecure and secure ports
    for port in (80, 443):
        connection = HTTPConnection(host=host, port=port, timeout=timeout)
        try:
            connection.request("HEAD","/")
            return True
        except Exception as e:
            error = e
        finally:
            connection.close()
    raise error
