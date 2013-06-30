package logicalExpression.atomicAction;
import org.w3c.dom.Element;

public class InvokeAction extends AtomicAction {
    public InvokeAction(Element node) {
        actionType = ActionType.Invoke;
        name = node.getAttribute("name");
    }
}