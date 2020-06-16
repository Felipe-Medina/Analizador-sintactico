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
    //ERLX_TD("(in|it|sring|sting|strng|strig|strin|foat|flat|flot|floa|duble|doble|doule|doube|doubl|sort|shrt|shot|"
          // + "shor|lng|log|lon|bolean|booean|boolan|"
          // + "boolan|boolen|boolea|vid|vod|voi|car|chr|cha)"),
   
    ID("[A-Z][0-9|a-z|_]*"),
    //ERLX_ID("[a-z][0-9|A-Z|_]*"),
    
    DEL("[(|)|{|}]"),
    
    SEP("[,|;]"),
   
    AS1("[=]"),
 
    SIG1("[-?]"),
   
    CNEPF("[0-9]+(\\.[0-9]+)?"),
    
    OA("[*|/|+|-|%|]");
    

    public final String patron;

    Tipo(String s) {
        this.patron = s;

    }
    
}
