package logicalExpression.designPattern.scanner;

import logicalExpression.designPattern.DesignPatternType;
import logicalExpression.designPattern.BaseDesignPattern;
import logicalExpression.designPattern.FlowPattern;
import logicalExpression.designPattern.SeqSeqPattern;
import logicalExpression.designPattern.SequencePattern;
import logicalExpression.designPattern.SwitchPattern;
import logicalExpression.designPattern.WhilePattern;

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
        }
        throw new IllegalArgumentException("Znaleziony wzorzec " + type +" jest nierozpoznawany.");        
    }
}
