package DesignPatternScanner;

import DesignPatternModels.DesignPatternType;
import DesignPatternModels.AtomicAction;
import DesignPatternModels.BaseDesignPattern;
import DesignPatternModels.FlowPattern;
import DesignPatternModels.SeqSeqPattern;
import DesignPatternModels.SequencePattern;
import DesignPatternModels.SwitchPattern;
import DesignPatternModels.WhilePattern;

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
