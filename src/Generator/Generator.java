package Generator;
import LogicalExpression.ILogicalExpression;
import LogicalExpression.Generator.LogicalExpressionGenerator;
import LogicalExpression.Generator.ILogicalExpressionGenerator;
import GeneratorUI.Log;
import InitialFileScanner.IInitialFileScanner;
import InitialFileScanner.InitialFileScanner;
import LogicalExpression.LogicalExpression;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

//agregacja klas implementujących logikę
public class Generator implements IGenerator{

    private String inputFilePath;
    private String outputFilePath;
    private Document document;
    private ILogicalExpression logicalExpression;
    private IInitialFileScanner initialFileScanner;
    private ILogicalExpressionGenerator logicalExpressionGenerator;
    private final Log log;
   
    public Generator(String input, String output) {
        inputFilePath = input;
        outputFilePath = output;
        initialFileScanner = new InitialFileScanner();
        logicalExpressionGenerator = new LogicalExpressionGenerator();
        log = Log.getInstance();
    }
    
    @Override
    public String run() throws Exception {
        document = readFile(inputFilePath);
        checkModel();
        logicalExpression = getLogicalExpression();
        
        StringBuilder sb = new StringBuilder();
        logicalExpression.PrintLogicalExpression(sb);
        
        return sb.toString();
    }
    
    @Override
    public Document readFile(String filePath) throws Exception {
        log.append("Wczytywanie pliku.");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        File file = new File(filePath);
        Document result = db.parse(file);
        log.append("Wykonano.");
        
        return result;
    }
    
    @Override
    public boolean checkModel() {
        log.append("Sprawdzanie poprawności pliku wejściowego.");
        initialFileScanner.prepareAndCheckFile(document);
        log.append("Wykonano.");
        return true;
    }
        
    private LogicalExpression getLogicalExpression(){
        log.append("Pobieranie drzewa wzorców.");
        LogicalExpression result = logicalExpressionGenerator.generateLogicalExpression(document);
        log.append("Wykonano.");
        
        return result;
    }
}