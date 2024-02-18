package DesignPatterns.FlyheightPattern.Example2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Flywheight extends JFrame {
    
    JButton startDrawing;

    int windowWidth = 1750;
    int windoeHeight = 1200;

    Color[] shapeColor = {Color.orange, Color.red, Color.yellow, Color.blue, Color.pink, Color.cyan, Color.magenta, Color.black, Color.gray};

    public static void main(String[] args) {
        new Flywheight();
    }

    public Flywheight(){
        this.setSize(windowWidth, windoeHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("FlyWheight Test");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        
        final JPanel drawingJPanel = new JPanel();
        
        startDrawing = new JButton("Draw Stuff");
        contentPanel.add(drawingJPanel, BorderLayout.CENTER);
        contentPanel.add(startDrawing, BorderLayout.SOUTH);

        startDrawing.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event){
                Graphics g = drawingJPanel.getGraphics();

                long startTime = System.currentTimeMillis();

                for(int i=0; i < 100000; i++){
                    Myrect rect = RectangleFactory.gMyrect(getRandColor());
                    rect.draw(g, getRandX(), getRandY(), getRandX(), getRandY());
                }

                long endTime = System.currentTimeMillis();
                System.out.println("That took "+ (endTime - startTime) + " Milliseconds to complete!");


            }


        });


        this.add(contentPanel);
        this.setVisible(true);


    }


    private Color getRandColor(){
        Random randomGenerator = new Random();
        int randInt = randomGenerator.nextInt(9);
        return shapeColor[randInt];
    }

    private int getRandX(){
        return (int)(Math.random()*windowWidth);
    }

    private int getRandY(){
        return (int)(Math.random()*windoeHeight);
    }


}
