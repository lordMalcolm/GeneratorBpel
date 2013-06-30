package logicalExpression;

import org.w3c.dom.Element;

public interface ILogicalExpression {    
    StringBuilder printLogicalExpression();
    String getSpecificType();
    void process(Element mainNode);
}