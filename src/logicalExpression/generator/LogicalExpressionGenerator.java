package logicalExpression.generator;

import generatorUI.Log;
import logicalExpression.LogicalExpression;
import logicalExpression.scanner.ILogicalExpressionScanner;
import logicalExpression.scanner.LogicalExpressionScanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LogicalExpressionGenerator implements ILogicalExpressionGenerator{
    
    private ILogicalExpressionScanner logicalExpressionScanner;
    private final Log log;
    
    public LogicalExpressionGenerator() {
        logicalExpressionScanner = new LogicalExpressionScanner();
        log = Log.getInstance();
    }
    
    @Override
    public LogicalExpression generateLogicalExpression(Document document) {
        Element root = document.getDocumentElement();
        LogicalExpression mainLogicalExpression = this.searchTree(root, 0);
        return mainLogicalExpression;
    }   

    //rekurencyjna metoda przeszukiwania podanej gałęzi
    private LogicalExpression searchTree(Element node, int deepLevel) {
        
        LogicalExpression expression = logicalExpressionScanner.getLogicalExpression(node);
        expression.deepLevel = deepLevel;
        
        for (int i = 0; i < expression.childNodes.size(); i++) {
            Element child = (Element) expression.childNodes.get(i);
            LogicalExpression tmp = searchTree(child, deepLevel+1);
            
            if (expression != null && tmp != null)
                expression.nestedPatterns.add(tmp);
            
        }
        log.append("Poprawnie rozpoznano " + expression.logicalExpressionType.toString() + " " + expression.getSpecificType());
        return expression;
    }
}
