/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatternScanner;

import DesignPatternModels.BaseDesignPattern;
import org.w3c.dom.Element;

/**
 *
 * @author malcolm
 */
public interface IDesignPatternScanner {
    BaseDesignPattern getDesignPatternIfAny(Element node);    
}
