#/usr/bin/env python3
import serial
import time

ser = serial.Serial('/dev/ttyACM0', 9600, timeout=1)
ser.reset_input_buffer()

while True:
	#_input = ser.read()
	# print(_input.decode("utf-8"))
	ser.write(b"Hello from PI \n")
	line = ser.readline().decode('utf-8').rstrip()
	print(line)
	time.sleep(1)
