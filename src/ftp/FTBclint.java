
package ftp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FTBclint {
    
    public static class clint extends JFrame implements ActionListener
    {
        
        private JLabel sel;
        private JTextField tx;
        private JButton bro,send;
        private Socket con;
        private String locatio;
        private OutputStream out;
        private InputStream in;
        public clint()
        {
            
            this.setSize(600, 500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLayout(null);
            
            sel = new JLabel("location");
            sel.setForeground(Color.black);
            sel.setBounds(2, 2, 100, 50);
            this.add(sel);
            
            tx = new JTextField();
            tx.setForeground(Color.gray);
            tx.setBounds(120, 2, 300, 50);
            this.add(tx);
            
            bro = new JButton("browes");
            bro.setForeground(Color.yellow);
            bro.setBackground(Color.black);
            bro.setBounds(490, 2, 100, 50);
            bro.addActionListener(this);
            this.add(bro);
            
            
            send = new JButton("send");
            send.addActionListener(this);
            send.setBounds(100, 300, 150, 50);
            this.add(send);
        
        }
        
        public void ready()
        {
            try {
                //set Server IP  and change port 5000 if you need
                this.con = new Socket("127.0.0.1",5000);
                
            
                } catch (IOException ex) {
                    System.out.println("error");
                }
        
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bro)
            {
                JFileChooser cho = new JFileChooser();
                cho.showOpenDialog(bro);
                locatio = cho.getSelectedFile().getAbsolutePath();
                tx.setText(locatio);
            }
            else
            {
                File file = new File(locatio);
                byte b[] = new byte[16*1024];
                try {
                    in = new FileInputStream(file);
                    out = con.getOutputStream();
                    int count ;
                    while((count = in.read(b))>0)
                    {
                        out.write(b, 0, count);
                    }
                    
                    
                } catch (Exception  ex) {
                    System.out.println("el file m4 mwoood ");
                } 
            }
        }
    
    }
    
    
    public static void main(String[] args)
    {
        clint x = new clint();
        x.setVisible(true);
        x.ready();
    }
    
}
