package generatorUI;
import generator.Generator;
import generator.IGenerator;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class GeneratorUI extends JPanel
                             implements ActionListener, FocusListener {
    
    JButton inputButton;
    JButton outputButton;
    JButton runButton;
    JTextField outputFileField;
    
    Log log;
    
    JFileChooser inputFc;
    JFileChooser outputFc;
    
    String inputFilePath;
    String outputFilePath;
    String outputFileName;
    
    public GeneratorUI() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = Log.getInstance();
        JScrollPane logScrollPane = log.getLogPane();

        //Create a file chooser
        inputFc = new JFileChooser();
        inputFc.setApproveButtonText("Choose");
        inputFc.setDialogTitle("Generator input file choosing");
        
        outputFc = new JFileChooser();
        outputFc.setApproveButtonText("Choose");
        outputFc.setDialogTitle("Generator output directory path choosing");
        outputFc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        outputFileField = new JTextField("out", 15);
        outputFileField.addFocusListener(this);
                
        inputButton = new JButton("Wybierz plik wejściowy...");
        inputButton.addActionListener(this);
        
        outputButton = new JButton("Wybierz folder pliku wynikowego...");
        outputButton.addActionListener(this);

        runButton = new JButton("Uruchom przetwarzanie");
        runButton.addActionListener(this);
        
        JLabel label = new JLabel("Nazwa pliku wynikowego");
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        
        buttonPanel.add(inputButton);
        buttonPanel.add(outputButton);
        buttonPanel.add(runButton);
        
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(label, BorderLayout.PAGE_START);
        textPanel.add(outputFileField, BorderLayout.CENTER);
        
        add(buttonPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(logScrollPane, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inputButton) {
            int returnVal = inputFc.showOpenDialog(GeneratorUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                inputFilePath = inputFc.getSelectedFile() == null ? 
                        null : inputFc.getSelectedFile().getAbsolutePath();
                
                log.append("Plik wejściowy: " + inputFilePath + "." );
            } else {
                log.append("Nie wybrano pliku wejściowego." );
            }

        } else if (e.getSource() == outputButton) {
            int returnVal = outputFc.showSaveDialog(GeneratorUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                outputFilePath = outputFc.getSelectedFile() == null ? 
                        null : outputFc.getSelectedFile().getAbsolutePath();
                //This is where a real application would save the file.
                log.append("Folder pliku wynikowego: " + outputFilePath + "." );
            } else {
                log.append("Nie wybrano pliku wynikowego " );
            }
            
        } else if(e.getSource() == runButton){
            if(!isAllParamsSet()){
                log.append("Przed uruchomieniem generatora należy poprawnie wybrać wszystkie parametry." );
            }
            else {
                run();
            }
        }
    }
    
    private void run(){
	try{
            IGenerator generator = new Generator(inputFilePath, outputFilePath + outputFileName);
            String out = generator.run();
            log.append("Zakończono przetwarzanie.");
            saveToFile(out);
        } catch(Exception ex){
            log.append(ex.toString());
        }
    }

    private void saveToFile(String out){
        BufferedWriter outWriter = null;
        String filePath;
        if(outputFilePath.contains("/" ))
            filePath = outputFilePath + "/" + outputFileName;
        else
            filePath = outputFilePath + "\\" + outputFileName;
        
        try{// Create file 
            FileWriter fstream = new FileWriter(filePath);
            outWriter = new BufferedWriter(fstream);
            outWriter.write(out);
            outWriter.close();
            log.append("Zapisano do pliku o nazwie : " + filePath );
        } catch(IOException ex){
          log.append("Nieudany zapis do pliku : " + ex.getMessage());
        } 
    }
    
    private boolean isAllParamsSet(){
        if(inputFilePath == null || inputFilePath.isEmpty()){
           return false;
        } else if(outputFilePath == null || outputFilePath.isEmpty()){
            return false;
        } else if(outputFileName == null || outputFileName.isEmpty()){
            return false;
        }
        return true;
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GeneratorUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Generator BPEL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GeneratorUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }

    /*
     * Ignored - implemented just because of FocusListener interface requirements
     */
    @Override
    public void focusGained(FocusEvent fe) {
        
    }

    /*
     * Saving outputFileName when you change focus from outputFileField to any other
     */
    @Override
    public void focusLost(FocusEvent fe) {
        if(fe.getSource() == outputFileField){
            
            if (outputFileField.getText() != null || 
                    outputFileField.getText().isEmpty()) {
                outputFileName = outputFileField.getText();
                
                log.append("Nazwa pliku wynikowego: " + outputFileName + "." );
            } else {
                log.append("Nazwa pliku wynikowego nie może być pusta" );
            }
        }
    }
}
