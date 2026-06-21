package guis;

import javax.swing.*;
import java.awt.*;

public class BasicForm extends JFrame{
    // create constructor
    public BasicForm(String title) {
        //set the title of the title bar
        super(title);

        //set the size of the GUI
        setSize(520, 800);

        //configure GUI to end process after closing
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // disable layout management
        setLayout(null);

        // load GUI in the center of the screen
        setLocationRelativeTo(null);

        // prevent GUI from changing size
        setResizable(false);

        // set Icon
        ImageIcon FrameImage = new ImageIcon("icon.png");
        setIconImage(FrameImage.getImage());

    }
}
