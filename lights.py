#https://www.youtube.com/watch?v=bOGltcgiXiU

#packages time and RPi are declared 
import time
import RPi.GPIO as GPIO


GPIO.setmode(GPIO.BCM)
GPIO.setup(26, GPIO.OUT)
GPIO.setwarnings(False)
GPIO.output(26, GPIO.LOW)

#time.sleep(5)

GPIO.output(26, GPIO.HIGH) #OUTPUT IN gpio pin 26 will be displayed , through a relay module the light will be switched on
GPIO.cleanup()
