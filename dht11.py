import RPi.GPIO as GPIO
import time
import http.client, json
import Adafruit_DHT

def bin2dec(string_num): #https://www.instructables.com/id/Home-Room-Temperature-and-Humidity-Monitor-with-Web
    return str(string_num)

data = []

GPIO.setmode(GPIO.BCM)
#DHT_SENSOR = Adafruit_DHT.DHT22
GPIO.setup(4,GPIO.OUT)
GPIO.output(4,GPIO.HIGH)
time.sleep(0.025)
GPIO.output(4,GPIO.LOW)
time.sleep(0.02)

GPIO.setup(4, GPIO.IN, pull_up_down=GPIO.PUD_UP)
#DHT_PIN = 4
#humidity , temperature = Adafruit_DHT.read_retry(DHT_SENSOR, DHT_PIN)

for i in range(0,500):
    data.append(GPIO.input(4))
   #data.append(temperature)
    #data.append(humidity)

bit_count = 0
tmp = 0
count = 0
HumidityBit = ""
TemperatureBit = ""
crc = ""

try:
    while data[count] == 1:
        tmp = 1
        count=count+1

    for i in range(0, 32):
        bit_count = 0
	
    while data[count] == 0:
        tmp = 1
        count = count + 1

    while data[count] == 1:
        bit_count = bit_count + 1
        count = count + 1

        if bit_count > 3:
            if i>=0 and i<8:
                HumidityBit = HumidityBit + "1"
            if i>=16 and i<24:
                TemperatureBit = TemperatureBit + "1"
        else:
            if i>=0 and i<0:
                HumidityBit = HumidityBit + "0"
            if i>=16 and i<24:
                TemperatureBit = TemperatureBit + "0"

except:
    print ("ERR_RANGE")
    exit(0)

try:
    for i in range(0, 8):
        bit_count = 0

    while data[count] == 0:
        tmp = 1
        count = count + 1

    while data[count] == 1:
        bit_count = bit_count + 1
        count = count + 1

    if bit_count > 3:
        crc = crc + "1"
    else:
        crc = crc + "0"

except:

    headers = { "charset" : "utf-8", "Content-Type": "application/json" }
    conn = http.client.HTTPConnection("192.168.137.69")
    sample_1 = { "nodata" : no_data }
    sampleJson_1 = json.dumps(sample_1, ensure_ascii = 'false')

    conn.request("POST", "/home/pi/Desktop/rpi/rpi.php", sampleJson_1, headers)
    response = conn.getresponse()
    print(response.read())
    conn.close()
    exit(0)

    Humidity = bin2dec(HumidityBit)
    Temperature = bin2dec(TemperatureBit)

    if  int(Humidity) + int(Temperature) - int(bin2dec(crc)) == 0:
        headers = { "charset" : "utf-8", "Content-Type": "application/json" }
        conn = http.client.HTTPConnection("192.168.137.69")
        sample = { "humidity" : Humidity, "temperature" : Temperature }
        sampleJson = json.dumps(sample, ensure_ascii = 'False')

        conn.request("POST", "/home/pi/Desktop/rpi/rpi.php", sampleJson, headers)
        response = conn.getresponse()
        print(response.read())
        conn.close()

    else:
        print ("ERR_CRC")