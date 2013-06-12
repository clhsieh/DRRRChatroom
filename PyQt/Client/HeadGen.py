#!/usr/bin/env python

import urllib, hashlib, cStringIO, Image, ImageQt

def HeadGen(param, isGravatar):
    size=58
    if isGravatar:
        url = 'http://www.gravatar.com/avatar/' + hashlib.md5(param.lower()).hexdigest() + '?'
        url += urllib.urlencode({'d':'http://drrr.us/css/icon_tanaka.png.pagespeed.ce.u-k_OoP5t0.png', 's':str(size)})
        imagefile = cStringIO.StringIO(urllib.urlopen(url).read())
        imagetemp = Image.open(imagefile)
        image = imagetemp.convert('RGBA')
    else:
        params = param.split('.')
        imagetemp = Image.new('RGB', (size, size), (int(params[2]), int(params[3]), int(params[4])))
        head = Image.open('Head/%02d.png' % int(params[0]), 'r')
        body = Image.open('Body/%02d.png' % int(params[1]), 'r')
        frame = Image.open('frame.png', 'r')
        imagetemp.paste(head, mask=head)
        imagetemp.paste(body, mask=body)
        imagetemp.paste(frame, mask=frame)
        image = imagetemp.convert('RGBA')
    return ImageQt.ImageQt(image)
