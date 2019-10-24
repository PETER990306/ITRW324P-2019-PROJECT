from socket import *
from time import ctime
import RPi.GPIO as GPIO

ctrCmd = ['On','Off']
HOST = ''
PORT = 27247
BUFSIZE = 1024
ADDR = (HOST,PORT)
tcpSerSock = socket(AF_INET, SOCK_STREAM)
tcpSerSock.bind(ADDR)
tcpSerSock.listen(5)
while True:
        print 'Waiting for connection'
 	tcpCliSock, addr = tcpSerSock.accept()
	print '...connected from :',addr
	try:
		while True:
			data = ''
		        data = tcpCliSock.recv(BUFSIZE)
                        data = 'On'
			if not data:
				break
			if data == ctrCmd[0]:
				print 'logged in'
			if data == ctrCmd[1]:
				print 'logged off'
	except KeyboardInterrupt:
		GPIO.cleanup()	
	tcpCliSock.close()
tcpSerSock.close()
