#!/usr/local/bin/python3

import sys
import requests
import logging

try:
    import http.client as http_client
except ImportError:
    # Python 2
    import httplib as http_client
http_client.HTTPConnection.debuglevel = 1

# You must initialize logging, otherwise you'll not see debug output.
logging.basicConfig()
logging.getLogger().setLevel(logging.DEBUG)
requests_log = logging.getLogger("requests.packages.urllib3")
requests_log.setLevel(logging.DEBUG)
requests_log.propagate = True

print(sys.version_info)

if len(sys.argv) != 4:
    print("Usage: " + sys.argv[0] + " userId commodityCode count ")
    sys.exit(1)

payload = {'userId': sys.argv[1],
           'commodityCode': sys.argv[2], 'count': sys.argv[3]}

r = requests.post(
    "http://localhost:8080/business-ejb/services/business/createOrder", data=payload)

print(r.text)
