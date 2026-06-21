package guis;

import javax.swing.*;

public class AdvanceForm extends JFrame{
    // create constructor
    public AdvanceForm(String title) {
        //set the title of the title bar
        super(title);

        //set the size of the GUI
        setSize(1000, 800);

        //configure GUI to end process after closing
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // disable layout management
        setLayout(null);

        // load GUI in the center of the screen
        setLocationRelativeTo(null);

        // prevent GUI from changing size
        setResizable(true);

        // set Icon
        ImageIcon FrameIamge = new ImageIcon("icon.png");
        setIconImage(FrameIamge.getImage());

    }
}
