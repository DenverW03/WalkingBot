package src.main.java;
import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class Main{
    private static JFrame frame;
    private static ControlWindow cw;
    public static void main(String[] args){
        // v-- manually setting just for testing
        Scanner scanner = new Scanner(System.in);
        SerialPort serialPort = SerialPort.getCommPort(scanner.nextLine());
        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        serialPort.openPort();
        try{Thread.sleep(3000);}catch(Exception e){System.out.println(e);};
        // send start message to microcontroller
        sendStartMessage(serialPort);
        // initialising window
        frame = new JFrame("Controller");
        cw = new ControlWindow(serialPort);
        frame.add(cw);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // v-- important so that serial port is not blocked for other processes
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                //serialPort.closePort();
            }
        });
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