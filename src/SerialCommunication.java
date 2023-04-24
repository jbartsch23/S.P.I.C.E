import java.util.Scanner;

import com.fazecast.jSerialComm.*;

public class SerialCommunication {
    public static void testComm() {
        Scanner sc = new Scanner(System.in);
        //System.out.println("List COM ports");
        SerialPort[] AvailablePorts = SerialPort.getCommPorts();

        // use the for loop to print the available serial ports
        // for(SerialPort S : AvailablePorts)
        // System.out.println("\n " + S.toString());

        SerialPort myPort = AvailablePorts[2]; // may have to change based on what port the arduino is on

        int baudRate = 9600;
        int dataBits = 8;
        int stopBits = SerialPort.ONE_STOP_BIT;
        int parity = SerialPort.NO_PARITY;

        myPort.setComPortParameters(baudRate, dataBits, stopBits, parity);
        myPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        myPort.openPort();
        System.out.println("Enter data to send:");

        try{
            while(true) {
                if(System.in.available() > 0) {
                    String s = sc.nextLine() + "\n";
                    Thread.sleep(2000);
                    byte[] writeBuffer = s.getBytes();
                    myPort.writeBytes(writeBuffer, writeBuffer.length);
                }
                while(myPort.bytesAvailable() > 0) {
                    byte[] readBuffer = new byte[myPort.bytesAvailable()];
                    myPort.readBytes(readBuffer, readBuffer.length);
                    for(int i = 0; i < readBuffer.length; i++) {
                        System.out.print((char)readBuffer[i]);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        myPort.closePort();
        sc.close();
    }

    public static void main (String[] args) {
        testComm();
    }
}