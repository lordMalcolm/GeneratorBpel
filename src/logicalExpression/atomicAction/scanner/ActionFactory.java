package logicalExpression.atomicAction.scanner;

import logicalExpression.atomicAction.ActionType;
import logicalExpression.atomicAction.AtomicAction;

public class ActionFactory {
    public static AtomicAction CreateAction(ActionType type){
//        switch (type) {
//            case Invoke:
//                return new ;
//            case Receive:
//                return new ;
//        }
//        return null;
        return new AtomicAction();
    }
}
