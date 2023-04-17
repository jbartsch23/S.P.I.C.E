import com.fazecast.jSerialComm.*;

public class JavaToArduino {
    public static void main(String[] Args) 
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
            Thread.sleep(2000);
            byte[] writeByte = new byte[1];
            writeByte[0] = 65;
            int bytesTxted = myPort.writeBytes(writeByte, 1);
            System.out.println(" Bytes transmitted -> " + bytesTxted);

        } catch (InterruptedException e) {
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
