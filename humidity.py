import Adafruit_DHT
import socket
from time import ctime
from socket import *

DHT_SENSOR = Adafruit_DHT.DHT22

DHT_PIN = 4
global temp, humidity
temp = hum = 0
humidity , temperature = Adafruit_DHT.read_retry(DHT_SENSOR, DHT_PIN)
#while True:
	#humidity , temperature = Adafruit_DHT.read_retry(DHT_SENSOR, DHT_PIN)
	#if humidity is not None and temperature is not None:
   		#print ("Temp={0:0.1f}*C  Humidity={1:0.1f}%".format(temperature, humidity))
	#else:
    		#print ("Failed to retrieve data from humidity sensor")

temp = temperature
hum = humidity

def web_page():
  html = """<!DOCTYPE HTML><html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
  <style>
    html {
     font-family: Arial;
     display: inline-block;
     margin: 0px auto;
     text-align: center;
    }
    h2 { font-size: 3.0rem; }
    p { font-size: 3.0rem; }
    .units { font-size: 1.2rem; }
    .dht-labels{
      font-size: 1.5rem;
      vertical-align:middle;
      padding-bottom: 15px;
    }
  </style>
</head>
<body>
  <h2>ESP DHT Server</h2>
  <p>
    <i class="fas fa-thermometer-half" style="color:#059e8a;"></i>
    <span class="dht-labels">Temperature</span>
    <span>"""+str(temp)+"""</span>
    <sup class="units">&deg;C</sup>
  </p>
  <p>
    <i class="fas fa-tint" style="color:#00add6;"></i>
    <span class="dht-labels">Humidity</span>
    <span>"""+str(hum)+"""</span>
    <sup class="units">%</sup>
  </p>
</body>
</html>"""
  return html

  s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  s.bind(('', 21570))
  s.listen(5)

while True:
  conn, addr = s.accept()
  print('Got a connection from %s' % str(addr))
  request = conn.recv(1024)
  print('Content = %s' % str(request))
  sensor_readings = read_sensor()
  print(sensor_readings)
  response = web_page()
  conn.send('HTTP/1.1 200 OK\n')
  conn.send('Content-Type: text/html\n')
  conn.send('Connection: close\n\n')
  conn.sendall(response)
  conn.close()