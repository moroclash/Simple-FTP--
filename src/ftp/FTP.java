
package ftp;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jdk.internal.util.xml.impl.Input;

public class FTP {

    public static class FTBserver extends JFrame
    {
        private ServerSocket s;
        private Socket con;
        private int port;
        private JLabel mas,x;
        private InputStream in;
        private OutputStream out;
        
        public FTBserver(int port)
        {
            this.setLayout(null);
            this.port = port;
            this.setSize(500, 500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            
            x = new JLabel("Server");
            x.setBounds(10, 10, 200, 100);
            
            
            mas = new JLabel("dasd");
            mas.setBounds(100, 100, 200, 200);
            mas.setForeground(Color.red);
            
            this.add(mas);
            this.add(x);
        
        }
    
        public void Serverready()
        {
            try {
                s = new ServerSocket(port);
                if(s.isClosed()) 
                    mas.setText("no connection");
                else
                    {
                    mas.setText("no connection");
                    con = s.accept();
                    mas.setForeground(Color.GREEN);
                    mas.setText("connect");
                    in = con.getInputStream();
                    out = new FileOutputStream("test.txt");
                    byte b[]  = new byte[16*1024];
                    int count;
                    while((count = in.read(b))>0)
                    {
                        out.write(b,0, count);
                    }
                    
                    }
                } catch (Exception ex) {
                    mas.setText("no Connection");
                }
        }
   
    }
    
    
    public static void main(String[] args) {
       
        FTBserver s  = new FTBserver(5000);
        s.setVisible(true);
        s.Serverready();
        
    }
    
}
