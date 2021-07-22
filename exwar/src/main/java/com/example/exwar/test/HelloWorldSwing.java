package com.example.exwar.test;

import sun.misc.GC;

import javax.swing.*;

public class HelloWorldSwing {



    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);


        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);


        long l = GC.currentLatencyTarget();
        System.out.println("l = " + l);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
