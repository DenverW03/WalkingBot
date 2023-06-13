package src.main.java;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import com.fazecast.jSerialComm.SerialPort;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.awt.*;
public class ControlWindow extends JPanel{
    private JSlider[] sliders;
    private JLabel[] sliderLabels;
    private SerialPort serialPort;
    public ControlWindow(SerialPort serialPort){
        this.serialPort = serialPort;
        sliders = new JSlider[3]; // only 2 servos connected to begin with
        sliderLabels = new JLabel[3];
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(500, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // create a name array for the sliders
        String[] names = {"tendon", "ankle", "knee"};
        int[] startingValues = {27, 113, 54};
        // setting up the sliders
        for(int i=0; i<sliders.length; i++){
            sliders[i] = new JSlider(0, 180, startingValues[i]);
            sliders[i].setName(names[i]);
            sliderLabels[i] = new JLabel(sliders[i].getName() + ": " + startingValues[i]);
            this.add(sliderLabels[i]);
            sliders[i].addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e){
                    JSlider source = (JSlider)e.getSource();
                    if (!source.getValueIsAdjusting()) {
                        int value = source.getValue();
                        // Determine the source and perform actions accordingly
                        switch (source.getName()) {
                            case "tendon":
                                sliderLabels[0].setText("tendon: " + Integer.toString(value));
                                moveServo("tendon", value);
                                break;
                            case "ankle":
                                sliderLabels[1].setText("ankle: " + Integer.toString(value));
                                moveServo("ankle", value);
                                break;
                            case "knee":
                                sliderLabels[2].setText("knee: " + Integer.toString(value));
                                moveServo("knee", value);
                                break;
                            // Add more cases for additional servo sliders
                        }
                    }
                }
            });
            this.add(sliders[i]);
        }
    }

    public void moveServo(String name, int angle){
        try{
            String msg = name + ":" + Integer.toString(angle);
            byte[] byteMsg = msg.getBytes(StandardCharsets.US_ASCII);
            OutputStream outputStream = serialPort.getOutputStream();
            outputStream.write(byteMsg);
        }
        catch(Exception e){ System.out.println(e); }
    }
}