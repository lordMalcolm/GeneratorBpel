package logicalExpression.atomicAction;
import org.w3c.dom.Element;
/**
 * Aktywność Condition
 */
public class ConditionAction extends AtomicAction {
    
    public ConditionAction(Element node) {
        actionType = ActionType.Condition;
        name = node.getAttribute("condition");
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        result.append("c(").append(name).append(")");
        return result;
    }
}