package logicalExpression.atomicAction.scanner;

import logicalExpression.atomicAction.AtomicAction;
import org.w3c.dom.Element;

public interface IAtomicActionScanner {
    AtomicAction getActionIfAny(Element node);    
}
