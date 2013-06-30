package logicalExpression.atomicAction.scanner;

import logicalExpression.atomicAction.ActionType;
import logicalExpression.atomicAction.AtomicAction;
import logicalExpression.atomicAction.ConditionAction;
import logicalExpression.atomicAction.InvokeAction;
import logicalExpression.atomicAction.ReceiveAction;

public class ActionFactory {
    public static AtomicAction CreateAction(ActionType type){
        switch (type) {
            case Invoke:
                return new InvokeAction();
            case Receive:
                return new ReceiveAction();
            case Condition:
                return new ConditionAction();
        }
        return new AtomicAction();
    }
}
