#enables this project as a Python package

__version__=  "0.1.0"
#import https methods to check connectivity without downloading the page
from http.client import HTTPConnection


#create a connection instance for pypi.org using common port 80
connection = HTTPConnection("pypi.org", port=80, timeout=10)
#performs HEAD request at root path "/" using .request()
connection.request("HEAD", "/")


#then call response to capture response from the server
response = connection.getresponse()
#inspect the headers returned from the request
response.getheaders()