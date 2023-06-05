package src.main.java;
import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class Main{
    public static void main(String[] args){
        // manually setting just for testing
        SerialPort serialPort = SerialPort.getCommPort("cu.usbmodem1112101");
        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        serialPort.openPort();
        try{Thread.sleep(3000);}catch(Exception e){System.out.println(e);};
        System.out.println("Starting Mapper");
        sendStartMessage(serialPort);
        serialPort.closePort();
    }

    /**
     * Starts a start message to the micro-controller using the active serial port
     */
    public static void sendStartMessage(SerialPort serialPort) {
        try{
            String startMsg = "start";
            byte[] byteMsg = startMsg.getBytes(StandardCharsets.US_ASCII);
            OutputStream outputStream = serialPort.getOutputStream();
            outputStream.write(byteMsg);
        }
        catch(Exception e){ System.out.println(e); }
    }
}