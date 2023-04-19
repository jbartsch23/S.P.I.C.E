import java.io.UnsupportedEncodingException;

import com.fazecast.jSerialComm.*;
public class ArduinoToJava
{
    public static void main (String[] Args)
   {
     SerialPort [] AvailablePorts = SerialPort.getCommPorts();

       // use the for loop to print the available serial ports
        //  for(SerialPort S : AvailablePorts)
        //       System.out.println("\n  " + S.toString());

        SerialPort myPort = AvailablePorts[0];
        
        int baudRate = 9600;
        int dataBits = 8;
        int stopBits = SerialPort.ONE_STOP_BIT;
        int parity = SerialPort.NO_PARITY;

        myPort.setComPortParameters(baudRate, dataBits, stopBits, parity);
        myPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        myPort.openPort();
        try {
            while(true) {
                byte[] readBuffer = new byte[100];
                int numRead = myPort.readBytes(readBuffer, readBuffer.length);
                System.out.print("Read: " + numRead + " bytes -");
                String S = new String(readBuffer, "UTF-8");
                System.out.println("Received -> " + S);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(myPort.isOpen()) {
            System.out.println("is Open ");

        } else {
            System.out.println(" Port not Open ");
        }
        myPort.closePort();

        if(myPort.isOpen()) {
            System.out.println("is Open ");
        } else {
            System.out.println(" Port not Open ");
        }
   }   
}