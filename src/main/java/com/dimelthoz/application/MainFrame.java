package com.dimelthoz.application;

import javax.swing.*;
import java.awt.*;

import static com.dimelthoz.application.Application.application;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("vlcj-media-player");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setBounds(100, 100, 600, 400);
        setContentPane(application().mediaPlayerComponent());

    }
}