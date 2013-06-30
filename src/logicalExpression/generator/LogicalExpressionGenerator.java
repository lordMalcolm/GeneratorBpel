package logicalExpression.generator;

import generatorUI.Log;
import logicalExpression.LogicalExpression;
import logicalExpression.LogicalExpressionType;
import logicalExpression.atomicAction.ActionType;
import logicalExpression.atomicAction.AtomicAction;
import logicalExpression.designPattern.BaseDesignPattern;
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
        return searchTree(document.getDocumentElement(), 0);
    }   

    //rekurencyjna metoda przeszukiwania podanej gałęzi
    private LogicalExpression searchTree(Element node, int deepLevel) {
        
        LogicalExpression expression = logicalExpressionScanner.getLogicalExpression(node);
        LogExpressionRecognitionStatus(expression);
        expression.deepLevel = deepLevel;
        for (Element child : expression.childNodes) {
            
            //przeszukiwanie kolejnych zagnieżdżonych gałęzi
            LogicalExpression tmp = searchTree(child, deepLevel+1);
            
            //jeżeli aktualne wyrażenie jest wzorcem dodawane są jego argumenty
            if (tmp != null && expression.logicalExpressionType == LogicalExpressionType.DesignPattern) {
                BaseDesignPattern base = (BaseDesignPattern) expression;
                base.nestedPatterns.add(tmp);
            }
        }       
        return expression;
    }

    private void LogExpressionRecognitionStatus(LogicalExpression expression) {
        
        if (expression.logicalExpressionType == LogicalExpressionType.DesignPattern) {
            log.append("rozpoznano wzorzec: " + expression.getSpecificType());
            return;
        }
        
        AtomicAction action = (AtomicAction) expression;
        if (action.actionType == ActionType.Unknown) {
            log.append("nie rozpoznano aktywności");
        }
        else {
            log.append("rozpoznano aktywność: " + expression.getSpecificType());
        }
        
    }
}
