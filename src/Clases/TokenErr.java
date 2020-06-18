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
public class TokenErr {
    private String TokenErr;
    private String LexemaErr;
    private int Linea;
    private String Des;

    public String getTokenErr() {
        return TokenErr;
    }

    public void setTokenErr(String tokenerr) {
        this.TokenErr = tokenerr;
    }

    public String getLexemaErr() {
        return LexemaErr;
    }

    public void setLexemaErr(String lexemaerr) {
        this.LexemaErr = lexemaerr;
    }
    
    public int getLinea() {
        return Linea;
    }
    public void setLinea(int linea) {
        this.Linea = linea;
    }
    public String getDes() {
        return Des;
    }
    public void setDes(String des) {
        this.Des = des;
    }
    
}
