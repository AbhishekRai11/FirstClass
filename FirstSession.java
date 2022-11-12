import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FirstSession implements ActionListener {
    JFrame frame;
    JTextArea myarea;
    JButton button;
    FirstSession(){
        frame=new JFrame("First Session");
        frame.setBounds(250,250,300,300);
        frame.setBackground(Color.black);
        frame.getContentPane().setBackground(Color.black);
        button=new JButton("Click");
        button.setBounds(120,20,70 ,20);
        button.addActionListener(this);
        myarea=new JTextArea("Welcome To Text Area");
        myarea.setBounds(50,50,200,200);
        frame.add(myarea);
        frame.add(button);
        myarea.setBackground(Color.red);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String arg[])
    {
        FirstSession object=new FirstSession();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myarea.setText("you click button");
    }
}
