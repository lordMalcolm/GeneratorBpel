package logicalExpression.atomicAction;
import org.w3c.dom.Element;
/**
 * Aktywność Receive
 */
public class ReceiveAction extends AtomicAction {
    
    public ReceiveAction(Element node) {
        actionType = ActionType.Receive;
        name = node.getAttribute("name");
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        result.append("r(").append(name).append(")");
        return result;
    }
}