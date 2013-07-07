package logicalExpression.generator;

import generatorUI.Log;
import java.util.List;
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
    private boolean error;
    
    public LogicalExpressionGenerator() {
        logicalExpressionScanner = new LogicalExpressionScanner();
        log = Log.getInstance();
        error = false;
    }
    
    @Override
    public LogicalExpression generateLogicalExpression(Document document) {
        LogicalExpression result = searchTree(document.getDocumentElement(), 0);
        if (error)
            return null;
        
        return result;
    }   

    //rekurencyjna metoda przeszukiwania podanej gałęzi
    private LogicalExpression searchTree(Element node, int deepLevel) {
        LogicalExpression baseExpression = logicalExpressionScanner.getLogicalExpression(node);
        List<Element> nestedNodes = logicalExpressionScanner.getNodesToExpand(node, baseExpression);
        
        LogExpressionRecognitionStatus(baseExpression);
        baseExpression.deepLevel = deepLevel;
        
        for (Element child : nestedNodes) {
            //przeszukiwanie kolejnych zagnieżdżonych gałęzi
            LogicalExpression nestedExpression = searchTree(child, deepLevel+1);
            //jeżeli aktualne wyrażenie jest wzorcem dodawane są jego argumenty
            if (nestedExpression != null && baseExpression.logicalExpressionType == LogicalExpressionType.DesignPattern) {
                BaseDesignPattern basePattern = (BaseDesignPattern) baseExpression;
                basePattern.nestedPatterns.add(nestedExpression);
            }
        }       
        return baseExpression;
    }

    private void LogExpressionRecognitionStatus(LogicalExpression expression) {
        if (expression.logicalExpressionType == LogicalExpressionType.DesignPattern) {
            log.append("rozpoznano wzorzec: " + expression.getSpecificType());
            return;
        }
        
        AtomicAction action = (AtomicAction) expression;
        if (action.actionType == ActionType.Unknown) {
            log.append("nie rozpoznano aktywności");
            error = true;
        }
        else
            log.append("rozpoznano aktywność: " + expression.getSpecificType());       
    }
}
