package logicalExpression.atomicAction;
import org.w3c.dom.Element;

public class InvokeAction extends AtomicAction {
    
    public InvokeAction(Element node) {
        actionType = ActionType.Invoke;
        name = node.getAttribute("name");
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        result.append("i(").append(name).append(")");
        return result;
    }
}