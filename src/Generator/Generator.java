package Generator;
import DesignPatternModels.BaseDesignPattern;
import DesignPatternTreeGenerator.DesignPatternTreeGenerator;
import DesignPatternTreeGenerator.IDesignPatternTreeGenerator;
import InitialFileScanner.IInitialFileScanner;
import InitialFileScanner.InitialFileScanner;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

//agregacja klas implementujących logikę
public class Generator implements IGenerator{

    private String inputFilePath;
    private String outputFilePath;
    private Document document;
    private BaseDesignPattern designPatternTree;
    private IInitialFileScanner initialFileScanner;
    private IDesignPatternTreeGenerator designPatternTreeGenerator;
   
    public Generator(String input, String output) {
        inputFilePath = input;
        outputFilePath = output;
        initialFileScanner = new InitialFileScanner();
        designPatternTreeGenerator = new DesignPatternTreeGenerator();
    }
    
    @Override
    public void run() throws Exception {
        
        document = readFile(inputFilePath);
        checkModel();
        designPatternTree = getDesignPatternTree();
        
        System.out.println("ok");
    }

    
    @Override
    public Document readFile(String filePath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        File file = new File(filePath);
        return db.parse(file);
    }
    
    @Override
    public boolean checkModel() {
        initialFileScanner.prepareAndCheckFile(document);
        return true;
    }
        
    private BaseDesignPattern getDesignPatternTree(){
        return designPatternTreeGenerator.generateDesignPatternTree(document);
    }
}