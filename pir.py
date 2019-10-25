import time
#from gpiozero import MotionSensor
import RPi.GPIO as GPIO
GPIO.setwarnings(False)

#pir = MotionSensor(23) #Input will be retrieved from pin 23 in the raspberry pi
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(23,GPIO.IN)



while True: #While input is detected in the above pin ,then output via a message will be displayed

    i =GPIO.input(23)
    if i == 0:
        print ('Motion Not Detected')
        time.sleep(0.5)
    elif i == 1:
        print ('Motion Detected')
        time.sleep(0.5)