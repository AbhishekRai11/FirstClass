import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JTextArea textarea;
    JMenuBar jmenubar;
    TextEditor(){
        frame=new JFrame();
        textarea=new JTextArea();
        jmenubar=new JMenuBar();

        JMenu file=new JMenu("File");
        JMenu edit=new JMenu("Edit");

        JMenuItem openfile=new JMenuItem("Open File");
        JMenuItem savefile=new JMenuItem("Save File");
        JMenuItem printfile=new JMenuItem("Print File");
        JMenuItem newfile=new JMenuItem("New File");

        openfile.addActionListener(this);
        savefile.addActionListener(this);
        printfile.addActionListener(this);
        newfile.addActionListener(this);

        file.add(openfile);
        file.add(savefile);
        file.add(printfile);
        file.add(newfile);

        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem paste=new JMenuItem("Paste");
        JMenuItem close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);

        jmenubar.add(file);
        jmenubar.add(edit);

        frame.setJMenuBar(jmenubar);
        frame.add(textarea);

        frame.setSize(800,800);
        //frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String arg[]){
        TextEditor textEditor=new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call= e.getActionCommand();
        if(call=="New File"){
            textarea.setText("");
        } else if (call=="Cut") {
            textarea.cut();
        } else if (call=="Copy") {
            textarea.copy();
        } else if (call=="Paste") {
            textarea.paste();
        } else if (call=="Close") {
            frame.setVisible(false);
        } else if (call=="Save File") {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer=new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textarea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (call=="Open File") {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());

                try {
                    String s1="",s2="";
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                    textarea.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if(call=="Print File"){
            try {
                textarea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
