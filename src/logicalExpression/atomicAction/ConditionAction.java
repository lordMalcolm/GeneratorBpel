package logicalExpression.atomicAction;
import org.w3c.dom.Element;

public class ConditionAction extends AtomicAction {
    public ConditionAction(Element node) {
        actionType = ActionType.Condition;
        name = node.getAttribute("condition");
    }
}