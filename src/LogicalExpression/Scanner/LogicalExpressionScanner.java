package LogicalExpression.Scanner;

import LogicalExpression.AtomicAction.AtomicAction;
import LogicalExpression.AtomicAction.Scanner.AtomicActionScanner;
import LogicalExpression.AtomicAction.Scanner.IAtomicActionScanner;
import LogicalExpression.DesignPattern.Scanner.*;
import LogicalExpression.DesignPattern.BaseDesignPattern;
import LogicalExpression.LogicalExpression;
import org.w3c.dom.Element;

//klasa przetwarzajaca podana galaz XML w poszukiwaniu wzorcow
public class LogicalExpressionScanner implements ILogicalExpressionScanner{

    private IDesignPatternScanner designPatternScanner;
    private IAtomicActionScanner atomicActionScanner;
    
    public LogicalExpressionScanner(){
        designPatternScanner = new DesignPatternScanner();
        atomicActionScanner = new AtomicActionScanner();
    }
    
    @Override
    public LogicalExpression getLogicalExpression(Element node) {
        
        BaseDesignPattern pattern = designPatternScanner.getDesignPatternIfAny(node);
        if (pattern != null)
            return pattern;
        
        AtomicAction action = atomicActionScanner.getActionIfAny(node);
        if (action != null)
            return action;
        
        return null;
    }
}
