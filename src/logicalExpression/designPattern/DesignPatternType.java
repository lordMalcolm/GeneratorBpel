package logicalExpression.designPattern;

//enum obslugiwanych wzorcow procesowych

import static logicalExpression.designPattern.DesignPatternType.Flow;
import static logicalExpression.designPattern.DesignPatternType.SeqSeq;
import static logicalExpression.designPattern.DesignPatternType.Sequence;
import static logicalExpression.designPattern.DesignPatternType.Switch;
import static logicalExpression.designPattern.DesignPatternType.While;

public enum DesignPatternType {
    Sequence, Flow, Switch, While, SeqSeq;
    
    public static DesignPatternType getType(String type){
        if("Sequence".equalsIgnoreCase(type))
            return Sequence;
        if("Flow".equalsIgnoreCase(type))
            return Flow;
        if("Switch".equalsIgnoreCase(type))
            return Switch;
        if("While".equalsIgnoreCase(type))
            return While;
        if("SeqSeq".equalsIgnoreCase(type))
            return SeqSeq;
        
        throw new RuntimeException("niepoprawny format pliku /patterns");       
    }
}