package logicalExpression.designPattern.scanner;

import java.util.List;
import logicalExpression.LogicalExpression;
import logicalExpression.designPattern.BaseDesignPattern;
import org.w3c.dom.Element;

public interface IDesignPatternScanner {
    BaseDesignPattern getDesignPatternIfAny(Element node);    
    List<Element> getNodesToExpand(Element node, LogicalExpression expression);
}
