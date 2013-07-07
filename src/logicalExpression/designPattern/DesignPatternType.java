package logicalExpression.designPattern;

/**
 * Typ wzorca procesowego
 */
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
