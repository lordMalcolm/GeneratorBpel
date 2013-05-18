package DesignPatternScanner;

import DesignPatternScanner.DesignPatterns.BaseDesignPattern;
import DesignPatternScanner.DesignPatterns.FlowPattern;
import DesignPatternScanner.DesignPatterns.SequencePattern;
import DesignPatternScanner.DesignPatterns.SwitchPattern;

public class DesignPatternFactory {
    public static BaseDesignPattern CreatePattern(DesignPatternType type){
        switch (type) {
            case Flow:
                return new FlowPattern();
            case Switch:
                return new SwitchPattern();
            case Sequence:
                return new SequencePattern();            
        }
        throw new IllegalArgumentException("Znaleziony wzorzec " + type +" jest nierozpoznawany.");        
    }
}
