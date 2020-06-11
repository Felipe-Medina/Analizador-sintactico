/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author fmedi
 */
public enum Tipo {
    
    
    TD("(int|string|float|double|short|long|boolean|void|char)"),
   
    ID("[A-Z][0-9|a-zA-Z|_]*"),
    
    DEL("[(|)]"),
    
    DELICOR("[{|}]"),
   
    SEPCOM("[,]"),
   
    AS("[=]"),
 
    SIG("[-?]"),
   
    CNEPF("[0-9]+(\\.[0-9])?"),
    
    OA("[*|/|+|-|%|]"), 
    
    SEP("[;]");
    

    public final String patron;

    Tipo(String s) {
        this.patron = s;

    }
    
}
