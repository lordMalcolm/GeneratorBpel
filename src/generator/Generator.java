package generator;
import logicalExpression.ILogicalExpression;
import logicalExpression.generator.LogicalExpressionGenerator;
import logicalExpression.generator.ILogicalExpressionGenerator;
import generatorUI.Log;
import initialFileScanner.IInitialFileScanner;
import initialFileScanner.InitialFileScanner;
import logicalExpression.LogicalExpression;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import logicalSpecification.generator.ILogicalSpecificationGenerator;
import logicalSpecification.generator.LogicalSpecificationGenerator;
import org.w3c.dom.Document;
/**
 * Klasa zajmująca się właściwym przetworzeniem pliku wejściowego
 * w zbiór formuł
 */
public class Generator implements IGenerator{

    private ILogicalExpression logicalExpression;
    private IInitialFileScanner initialFileScanner;
    private ILogicalExpressionGenerator logicalExpressionGenerator;
    private ILogicalSpecificationGenerator logicalSpecificationGenerator;
    private final Log log;
    private Document document;
    private String inputFilePath;
    private String outputFilePath;
   
    public Generator(String input, String output) {
        inputFilePath = input;
        outputFilePath = output;
        initialFileScanner = new InitialFileScanner();
        logicalExpressionGenerator = new LogicalExpressionGenerator();
        logicalSpecificationGenerator = new LogicalSpecificationGenerator();
        log = Log.getInstance();
    }
    
    @Override
    public String run() throws Exception {
        document = readFile(inputFilePath);
        checkModel();
        
        logicalExpression = getLogicalExpression();
        StringBuilder sb = logicalExpression.printLogicalExpression();
        //String logicalSpecification = logicalSpecificationGenerator.printLogicalSpecification(logicalExpression);
        //sb.append(logicalSpecification);
        //StringBuilder sb = logicalExpression.printLogicalExpression();

        String logicalSpecification = logicalSpecificationGenerator.printLogicalSpecification(logicalExpression);
        sb.append(logicalSpecification);
        
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
