package DesignPatternTreeGenerator;

import DesignPatternModels.BaseDesignPattern;
import org.w3c.dom.Document;

public interface IDesignPatternTreeGenerator {
    BaseDesignPattern generateDesignPatternTree(Document document);
}
