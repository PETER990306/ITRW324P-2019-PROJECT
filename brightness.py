import RPi.GPIO as GPIO
import time

pin = 21
GPIO.setmode(GPIO.BCM)
GPIO.setup(pin,GPIO.OUT)
GPIO.setwarnings(False)

pwm_light = GPIO.PWM(pin,50)

pwm_light.start(100)

try:

    while True:
        duty = input("Enter Brightness Value ,(0 to 100) :")
        x= int(duty)
        pwm_light.ChangeDutyCycle(x)
        time.sleep(0.5)

except KeyboardInterrupt:
    print("Exiting Program")

#except :
    #print("Error Occured,Exiting Program")

finally:
    GPIO.cleanup()