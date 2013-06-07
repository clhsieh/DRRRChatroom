# import code for encoding urls and generating md5 hashes
import urllib, hashlib
 
# Set your variables here
email = "someone@somewhere.com"
default = "http://www.example.com/default.jpg"
size = 58
 
# construct the url
gravatar_url = "http://www.gravatar.com/avatar/" + hashlib.md5(email.lower()).hexdigest() + "?"
gravatar_url += urllib.urlencode({'d':default, 's':str(size)})

print gravatar_url
