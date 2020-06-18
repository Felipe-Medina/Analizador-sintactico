/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import Clases.Tipo;
import Clases.Token;
import Clases.TokenErr;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Entrada extends javax.swing.JFrame {

    ArrayList<String> tipo = new ArrayList<>();
    ArrayList<String> tipotoken = new ArrayList<>();
    ArrayList<String> errores = new ArrayList<>();
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm2 = new DefaultTableModel();
    int [] contToken = new int[8];
    ArrayList<Integer> PosicionTE = new ArrayList<>();
    public Entrada() {
        initComponents();
        setSize(1000, 450);
        setResizable(false);
        setTitle("Analizador Léxico");
        setLocationRelativeTo(null);
        mostrar();
            
    }
public void Analizar_lex(String Texto_Entrada, ArrayList<Token> TokenS, ArrayList<TokenErr> TokenE) {
        //Arreglos auxiliares;
        //final ArrayList<TablaSim> Aux_TokenS = new ArrayList<TablaSim>();
        //final ArrayList<TablaErr> Aux_TokenE =  new ArrayList<TablaErr>();
        //-----------------------------------------------------------
        final StringTokenizer st = new StringTokenizer(Texto_Entrada);
        char[] letras; //Almacenar la palabra por caracteres
        ArrayList<Integer> ConTokens =  new ArrayList<Integer>();
        int cont=0; //recorrer el arreglo para ver si es un número con punto flotante
        int contTOKENS=0; //Sirve para contar la posición de la tabla de simbolo
        boolean numEnt=true;
        
        while (st.hasMoreTokens()) {
            contTOKENS++;
            String palabra = st.nextToken();
            System.out.println("PALABRA--> "+palabra);
            letras=palabra.toCharArray();  
            if(letras[0]== '*' | letras[0]== '/' | letras[0]== '+' | letras[0]== '-'){
                crear_token(Tipo.OA, palabra,TokenS,TokenE,2," Operador artimético",contTOKENS); //Operador aritmético 2
                } else if(letras[0]== '=') {
                    crear_token(Tipo.AS, palabra,TokenS,TokenE,3," Operador de asignación",contTOKENS); //Asignacion 3
                }else if(letras[0] == 'v' | letras[0] == 'i'| letras[0] == 'f'| letras[0] == 'b' | letras[0] == 'c'| letras[0] == 'c')
                {crear_token(Tipo.TD, palabra,TokenS,TokenE,4," Tipo de dato",contTOKENS); //Tipo de datos 4
                }else if(letras [0]>= 'A' && letras[0]<='Z') 
                {crear_token(Tipo.ID, palabra,TokenS,TokenE,5," Identificador",contTOKENS); //Identificadores 5s
                }else if(letras [0]== ';' | letras[0]==',')
                { crear_token(Tipo.SEP, palabra,TokenS,TokenE,6," Miscelaneos",contTOKENS); //Miscelaneos 6
                }else if(letras[0] == '(' | letras[0] == ')'| letras[0] == '{'| letras[0] == '}' ){
                    crear_token(Tipo.DEL, palabra,TokenS,TokenE,7," Delimitador",contTOKENS); //Delimitadores 7
                }else if(letras[0]>='0'&& letras[0]<='9'){
                    System.out.println("Entrando a los Números");
                     while(cont<letras.length){
                         if(letras[cont]== '.')
                         {crear_token(Tipo.CNEPF, palabra,TokenS,TokenE,1, " Punto Flotante",contTOKENS);
                         System.out.println("Punto encontrador, evaluarpunto flotante");
                         numEnt=false;
                         break;
                         }
                         cont++;
                     }
                     if(numEnt)
                     {crear_token(Tipo.CNE, palabra,TokenS,TokenE,0, " Constante Numérica",contTOKENS);
                         System.out.println("Constante Numérica");
                     }
                }else{
                crear_token(Tipo.TD, palabra,TokenS,TokenE,4," Tipo de dato",contTOKENS);
                }
            
        }
}
public void crear_token(Tipo Tipo,String cadena,ArrayList<Token> TokensC ,ArrayList<TokenErr> TokensE,int id_ContT, String NameER,int LineTE){           
                
                boolean repetido = false;
                System.out.println(Tipo.patron);
                Pattern patron = Pattern.compile(Tipo.patron);
                Matcher matcher = patron.matcher(cadena); //*
                
                //generar TOKEN si hay
                if (matcher.find()) {
                    System.out.println("Generando el TOKEN CORRECTO");
                    Token tk = new Token();
                    repetido= contRep(TokensC,cadena); //Crear metodo contRep
                    if(!repetido)
                    {   contToken[id_ContT]+=1; //incrementando el contador de token
                        String ContT_aux = String.valueOf(contToken[id_ContT]); //auxiliar INT-STRING
                        //Agregar TOKEN Y LEXEAMA A LA TABLA
                        tk.setToken(Tipo+ContT_aux);
                        tk.setLexema(cadena);
                        TokensC.add(tk); //arrayList de tipo Token
                    }
                }else{ //generar el ERROR
                    System.out.println("Generando el ERROR");
                    TokenErr tke = new TokenErr();
                    //Realizar el mismo incremento en la parte anterior
                    
                    //------Agregar a la tabla de Símbolos
                    Token tk = new Token();
                    repetido= contRep(TokensC,cadena); //devuelve un True o False
                        if(!repetido)
                        {contToken[id_ContT]+=1; //incrementando el contador de token
                        String ContT_aux = String.valueOf(contToken[id_ContT]); //auxiliar INT-STRING
                        //------------Agregar ERRORES TABLA SIMBOLOS---------------------------------
                        tk.setToken("ERLX"+Tipo+ContT_aux);
                        tk.setLexema(cadena);
                        PosicionTE.add(LineTE); //agrego la posición del TOKEN
                        TokensC.add(tk);
                        //---------Agregar Errores TABLA ERRORES---------------
                        tke.setLexemaErr(cadena);
                        tke.setTokenErr("ERLX"+Tipo+ContT_aux);
                        tke.setDes("Incorrecto el "+NameER);
                        TokensE.add(tke);
                        }
                    
                    
                //creo que debemos de crear otro método para imprimirlo
                }    
    }
 public boolean contRep(ArrayList<Token> Token, String lexema){
     boolean repetido=false;
     for(Token d: Token){
         if(d.getLexema().equals(lexema)){
             repetido = true;
             break;
         }
     }
     return repetido;
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Expresion = new javax.swing.JTextArea();
        jButton1_Analiza = new javax.swing.JButton();
        jLabel1_Instruccion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2_Tabla_Simbolos = new javax.swing.JTable();
        jLabel1_name_simbol = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2_Tabla_Error = new javax.swing.JTable();
        jButton1_archivo = new javax.swing.JButton();
        jButton1_Limpiar = new javax.swing.JButton();
        jLabel1_nameError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Expresion.setColumns(20);
        txt_Expresion.setRows(5);
        txt_Expresion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(txt_Expresion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 180));

        jButton1_Analiza.setText("Analiza");
        jButton1_Analiza.setBorder(null);
        jButton1_Analiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_AnalizaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1_Analiza, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 80, 40));

        jLabel1_Instruccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1_Instruccion.setText("Ingresa la expresión a analizar");
        jLabel1_Instruccion.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(jLabel1_Instruccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jTable2_Tabla_Simbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Lexema", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2_Tabla_Simbolos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 200, 330));

        jLabel1_name_simbol.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1_name_simbol.setText("Tabla de símbolos");
        jLabel1_name_simbol.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(jLabel1_name_simbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        jTable2_Tabla_Error.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Token Error", "Lexema", "Línea", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2_Tabla_Error);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 470, 330));

        jButton1_archivo.setText("Generar archivo de tokens");
        jButton1_archivo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_archivoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1_archivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 150, 20));

        jButton1_Limpiar.setText("Limpiar");
        jButton1_Limpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_LimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 380, -1, -1));

        jLabel1_nameError.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1_nameError.setText("Tabla de error");
        jLabel1_nameError.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(jLabel1_nameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int j=0;
    int t =0;
    
    String[] txt = new String[200];
    private void jButton1_AnalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_AnalizaActionPerformed
       t =0;
      
        if (txt_Expresion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite caracteres en el campo");
        }
       
        
    }//GEN-LAST:event_jButton1_AnalizaActionPerformed

    public void mostrar() {

        try {
            dtm.addColumn("Lexema");
            dtm.addColumn("Token");
            
            dtm2.addColumn("Lexema");
            dtm2.addColumn("Token error");
            dtm2.addColumn("Línea");
            dtm2.addColumn("Descripción");

            jTable2_Tabla_Error.setModel(dtm2);
            jTable2_Tabla_Simbolos.setModel(dtm);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "error mostrar" + ex);
        }
    }
     File archivo;
     PrintWriter token = null;
    private void jButton1_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_archivoActionPerformed
        
        archivo = new File ("C:\\Users\\fmedi\\Desktop\\archivo_de_tokens.txt");
        if(!archivo.exists()){
            
            JOptionPane.showMessageDialog(null, "Archivo de tokens creado");
            try{
                archivo.createNewFile();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "No se ha podido crear el archivo de tokens" +e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Archivo de tokens sobreescrito");
            try {
                token = new PrintWriter(archivo,"utf-8");
                
                for (int n = 0; n < t; n++) {
                    token.println(txt[n]);
                }
                token.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }

        }
        
    }//GEN-LAST:event_jButton1_archivoActionPerformed

    private void jButton1_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_LimpiarActionPerformed
         int a = dtm.getRowCount();
        int b= dtm2.getRowCount();
        for (int i = 0; i < a; i++) {
            dtm.removeRow(0);  
        }
        for (int i = 0; i < b; i++) {
            dtm2.removeRow(0);  
        }
         try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("");
            bw.close();
            } catch (IOException ex) {
            Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        
    }//GEN-LAST:event_jButton1_LimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Entrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1_Analiza;
    private javax.swing.JButton jButton1_Limpiar;
    private javax.swing.JButton jButton1_archivo;
    private javax.swing.JLabel jLabel1_Instruccion;
    private javax.swing.JLabel jLabel1_nameError;
    private javax.swing.JLabel jLabel1_name_simbol;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2_Tabla_Error;
    private javax.swing.JTable jTable2_Tabla_Simbolos;
    private javax.swing.JTextArea txt_Expresion;
    // End of variables declaration//GEN-END:variables
}
