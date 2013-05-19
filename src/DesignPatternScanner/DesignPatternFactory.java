package DesignPatternScanner;

import DesignPatternScanner.DesignPatterns.AtomicAction;
import DesignPatternScanner.DesignPatterns.BaseDesignPattern;
import DesignPatternScanner.DesignPatterns.FlowPattern;
import DesignPatternScanner.DesignPatterns.SequencePattern;
import DesignPatternScanner.DesignPatterns.SwitchPattern;
import DesignPatternScanner.DesignPatterns.WhilePattern;

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
            case Atomic:
                return new AtomicAction();
        }
        throw new IllegalArgumentException("Znaleziony wzorzec " + type +" jest nierozpoznawany.");        
    }
}
