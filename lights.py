#https://www.youtube.com/watch?v=bOGltcgiXiU


import time

import RPi.GPIO as GPIO


GPIO.setmode(GPIO.BCM)
GPIO.setup(26, GPIO.OUT)
GPIO.setwarnings(False)
GPIO.output(26, GPIO.LOW)

#time.sleep(5)

GPIO.output(26, GPIO.HIGH)
GPIO.cleanup()
