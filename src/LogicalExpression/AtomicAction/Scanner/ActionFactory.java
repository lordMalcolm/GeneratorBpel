package LogicalExpression.AtomicAction.Scanner;

import LogicalExpression.AtomicAction.ActionType;
import LogicalExpression.AtomicAction.AtomicAction;

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
