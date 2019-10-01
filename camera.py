# Capturing pictures for the face recognition
from picamera import PiCamera

camera = PiCamera()

camera.capture('/home/pi/LERA/3.png')
camera.close()