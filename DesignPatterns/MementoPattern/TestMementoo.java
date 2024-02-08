package DesignPatterns.MementoPattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TestMementoo extends JFrame {

    public static void main(String[] args) {
        new TestMementoo();
    }

    private JButton saveBut, undoBut, redoBut;

    private JTextArea theArticle = new JTextArea(40, 60);

    CareTaker careTaker = new CareTaker();

    Originator originator = new Originator();

    int saveFiles = 0, currentArticle = 0;

    public TestMementoo(){
        this.setSize(750, 780);
        this.setTitle("Memento Design Pattern");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Article"));
        panel1.add(theArticle);

        ButtonListener saveListener = new ButtonListener();
        ButtonListener undoListener = new ButtonListener();
        ButtonListener redoListener = new ButtonListener();

        saveBut = new JButton("Save");
        saveBut.addActionListener(saveListener);

        undoBut = new JButton("Undo");
        undoBut.addActionListener(undoListener);

        redoBut = new JButton("Redo");
        redoBut.addActionListener(redoListener);

        panel1.add(saveBut);
        panel1.add(undoBut);
        panel1.add(redoBut);

        this.add(panel1);
        this.setVisible(true);

    }


    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (e.getSource() == saveBut) {
                String textInTextArea = theArticle.getText();
                originator.set(textInTextArea);

                careTaker.addMemento(originator.storeInMemento());

                saveFiles++;
                currentArticle++;

                System.out.println("Saved Files " + saveFiles);

                undoBut.setEnabled(true);

            } else if (e.getSource() == undoBut) {

                if (currentArticle >=1 ) {
                    
                    currentArticle--;

                    String textBooxString = originator.restoreFroomMemento(careTaker.getMemento(currentArticle));

                    theArticle.setText(textBooxString);

                    redoBut.setEnabled(true);


                } else {

                    undoBut.setEnabled(false);

                }
            } else if (e.getSource() == redoBut) {
                
                if ((saveFiles - 1) > currentArticle) {

                    currentArticle++;

                    String textBooxString = originator.restoreFroomMemento(careTaker.getMemento(currentArticle));

                    theArticle.setText(textBooxString);

                    undoBut.setEnabled(true);

                } else {

                    redoBut.setEnabled(false);


                }

            }
        }
    }
}
