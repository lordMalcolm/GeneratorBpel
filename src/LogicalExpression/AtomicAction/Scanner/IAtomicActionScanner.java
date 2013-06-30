package LogicalExpression.AtomicAction.Scanner;

import LogicalExpression.AtomicAction.AtomicAction;
import org.w3c.dom.Element;

public interface IAtomicActionScanner {
    AtomicAction getActionIfAny(Element node);    
}
