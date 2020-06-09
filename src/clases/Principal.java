/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.io.File;
/**
 *
 * @author fmedi
 */
public class Principal {
    public static void main(String[] args) {
        String ruta = "C:/Users/fmedi/Desktop/Git/Analizador_Sintactico/src/clases/Lexer.flex";
        generarLexer(ruta);
    }
    public static void generarLexer(String ruta){
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
           
}
