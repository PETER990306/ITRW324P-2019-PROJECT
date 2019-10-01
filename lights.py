#https://www.youtube.com/watch?v=bOGltcgiXiU

import RPI.GPIO as GPIO 
import datetime 

GPIO.setmode(GPIO.BCM)
GPIO.setup(20,GPIO.OUT)

While True :
	now = datetime.datetime.now().time()
	if now.hour == 20 and now.minute == 44 :
		GPIO.output(20,True)
	
	
finally :
	GPIO.cleanup()