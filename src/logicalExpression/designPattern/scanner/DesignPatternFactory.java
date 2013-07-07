package logicalExpression.designPattern.scanner;

import logicalExpression.designPattern.DesignPatternType;
import logicalExpression.designPattern.BaseDesignPattern;
import logicalExpression.designPattern.FlowPattern;
import logicalExpression.designPattern.SeqSeqPattern;
import logicalExpression.designPattern.SequencePattern;
import logicalExpression.designPattern.SwitchPattern;
import logicalExpression.designPattern.WhilePattern;
import org.w3c.dom.Element;
/**
 * Fabryka tworząca wzorce procesowe
 */
public class DesignPatternFactory {
    public static BaseDesignPattern CreatePattern(DesignPatternType type, Element node){
        switch (type) {
            case Flow:
                return new FlowPattern(node);
            case Switch:
                return new SwitchPattern(node);
            case Sequence:
                return new SequencePattern(node);         
            case While:
                return new WhilePattern(node);
            case SeqSeq:
                return new SeqSeqPattern(node);
        }
        throw new IllegalArgumentException("Znaleziony wzorzec " + type +" jest nierozpoznawany.");        
    }
}
