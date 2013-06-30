package LogicalExpression.DesignPattern.Scanner;

import LogicalExpression.DesignPattern.DesignPatternType;
import LogicalExpression.AtomicAction.AtomicAction;
import LogicalExpression.DesignPattern.BaseDesignPattern;
import LogicalExpression.DesignPattern.FlowPattern;
import LogicalExpression.DesignPattern.SeqSeqPattern;
import LogicalExpression.DesignPattern.SequencePattern;
import LogicalExpression.DesignPattern.SwitchPattern;
import LogicalExpression.DesignPattern.WhilePattern;

public class DesignPatternFactory {
    public static BaseDesignPattern CreatePattern(DesignPatternType type){
        switch (type) {
            case Flow:
                return new FlowPattern();
            case Switch:
                return new SwitchPattern();
            case Sequence:
                return new SequencePattern();         
            case While:
                return new WhilePattern();
            case SeqSeq:
                return new SeqSeqPattern();
            case Atomic:
                return new AtomicAction();
        }
        throw new IllegalArgumentException("Znaleziony wzorzec " + type +" jest nierozpoznawany.");        
    }
}
