package LogicalExpression.Scanner;

import LogicalExpression.LogicalExpression;
import org.w3c.dom.Element;

public interface ILogicalExpressionScanner {
    LogicalExpression getLogicalExpression(Element node);    
}
