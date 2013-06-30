package logicalExpression.atomicAction.scanner;

import logicalExpression.atomicAction.ActionType;
import logicalExpression.atomicAction.AtomicAction;
import logicalExpression.atomicAction.ConditionAction;
import logicalExpression.atomicAction.InvokeAction;
import logicalExpression.atomicAction.ReceiveAction;
import org.w3c.dom.Element;

public class ActionFactory {
    public static AtomicAction CreateAction(ActionType type, Element node) {
        switch (type) {
            case Invoke:
                return new InvokeAction(node);
            case Receive:
                return new ReceiveAction(node);
            case Condition:
                return new ConditionAction(node);
        }
        return new AtomicAction();
    }
}
