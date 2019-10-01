#!/usr/local/bin/python

import RPi.GPIO as GPIO
import time 

GPIO.setmode(GPIO.BCM)

pin_to_circuit = 19


def rc_time (pin_to_circuit):
    count = 0

    #Output on pin for 
    GPIO.setup(pin_to_circuit, GPIO.OUT)
    GPIO.output(pin_to_circuit, GPIO.LOW)
    time.sleep(0.1)

    #change the pin back to input
    GPIO.setup(pin_to_circuit, GPIO.IN)

#Count until the pin goes high
    while (GPIO.input(pin_to_circuit) == GPIO.LOW):
        count += 1

    return count


#Catch when script is interrupted, cleanup correctly
try:
    # Main Loop
    while True:
       print (rc_time(pin_to_circuit))
except KeyboardInterrupt:
    pass
finally:
    GPIO.cleanup() 
