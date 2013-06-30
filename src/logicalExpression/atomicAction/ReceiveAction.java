package logicalExpression.atomicAction;
import org.w3c.dom.Element;

public class ReceiveAction extends AtomicAction {
    public ReceiveAction(Element node) {
        actionType = ActionType.Receive;
        name = node.getAttribute("name");
    }
}