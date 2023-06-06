package src.main.java;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.*;
public class ControlWindow extends JPanel{
    private JSlider[] sliders;
    private JLabel[] sliderLabels;
    public ControlWindow(){
        sliders = new JSlider[2]; // only 2 servos connected to begin with
        sliderLabels = new JLabel[2];
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(500, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // create a name array for the sliders
        String[] names = {"tendon", "ankle"};
        // setting up the sliders
        for(int i=0; i<sliders.length; i++){
            sliders[i] = new JSlider(0, 180, 0);
            sliders[i].setName(names[i]);
            sliderLabels[i] = new JLabel(sliders[i].getName() + ": " + "0");
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
                                break;
                            case "ankle":
                                sliderLabels[1].setText("ankle: " + Integer.toString(value));
                                break;
                            // Add more cases for additional sliders if needed
                        }
                    }
                }
            });
            this.add(sliders[i]);
        }
    }
}