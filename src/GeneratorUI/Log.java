/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneratorUI;

import java.awt.Insets;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author x
 */
public class Log {
    
    static private final String newline = "\n";
    
    private static JScrollPane logPane;
    
    private static JTextArea log;
    
    private static Log l;
    
    private Log(){
        log = new JTextArea(20,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        logPane = new JScrollPane(log);
    }
    
    public static Log getInstance(){
        if(l== null){
            l = new Log();
        }
        return l;
    }
    
    public void append(String line){
        log.append(getWholeLine(line));
        log.setCaretPosition(log.getDocument().getLength());
    }
    
    public String getWholeLine(String line){
        return new StringBuilder(getCurrentTime())
                .append(": ")
                .append(line)
                .append(newline).toString();
    }
    
    public String getCurrentTime(){
        Date current = new Date(System.currentTimeMillis());
        return current.toString();
    }
    
    public JScrollPane getLogPane(){
        return logPane;
    }
}
