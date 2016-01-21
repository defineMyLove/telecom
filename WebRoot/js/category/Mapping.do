Traceback (most recent call last):
  File "./service.py", line 784, in application
    status, header, body = default_handler(environ)
  File "./service.py", line 632, in default_handler
    extra_params.append(u'%s=%s' % (k,v))
UnicodeDecodeError: 'ascii' codec can't decode byte 0xe5 in position 0: ordinal not in range(128)
