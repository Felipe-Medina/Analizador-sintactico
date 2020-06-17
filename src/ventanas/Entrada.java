/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import Clases.Tipo;
import Clases.Token;
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
    public Entrada() {
        initComponents();
        setSize(1000, 450);
        setResizable(false);
        setTitle("Analizador Léxico");
        setLocationRelativeTo(null);
        mostrar();
            
    }
    private ArrayList<Token> lex(String input) {
        final ArrayList<Token> tokens = new ArrayList<Token>();
        final StringTokenizer st = new StringTokenizer(input);

        while (st.hasMoreTokens()) {
            String palabra = st.nextToken();
            boolean matched = false;

            for (Tipo tokenTipo : Tipo.values()) {
                Pattern patron = Pattern.compile(tokenTipo.patron);
                Matcher matcher = patron.matcher(palabra);
                if (matcher.matches()) {
                    Token tk = new Token();
                    tk.setTipo(tokenTipo);
                    tk.setValor(palabra);
                    tokens.add(tk);
                    matched = true;
                }
            }
            if (!matched) {
                errores.add(palabra);
                
                for (int i = 0; i < errores.size(); i++) {
                     Pattern pat = Pattern.compile("(in|it|sring|sting|strng|strig|strin|foat|flat|flot|floa|duble|doble|doule|doube|doubl|sort|shrt|shot|"
                             + "shor|lng|log|lon|bolean|booean|boolan|"
                             + "boolan|boolen|boolea|vid|vod|voi|car|chr|cha)");
                     Matcher mat = pat.matcher(palabra);
                     if (mat.matches()){
                         dtm2.addRow(new Object[]{errores.get(i),"ERLX_TD", "Linea...", "Incorrecto el TD"});
                     }
                     if(!matched){
<<<<<<< HEAD
                         Pattern pa = Pattern.compile("[A-Z]([a-z]*['|@|`|~]+)");
=======
                         Pattern pa = Pattern.compile("[a-z]['|@|`|~]+");
>>>>>>> feature-regex
                         Matcher ma = pa.matcher(palabra);
                        if(ma.matches()){
                            dtm2.addRow(new Object[]{errores.get(i),"ERLX_ID", "Linea...", "Incorrecto el ID"});
                        }
                        if(!matched){
                            Pattern p = Pattern.compile("[-]?[0-9]+([a-zA-Z]+|\\.[a-zA-Z]+)");
                            Matcher m = p.matcher(palabra);
                            if(m.matches()){
                                dtm2.addRow(new Object[]{errores.get(i),"ERLX_CNEPF", "Linea...", "Incorrecto el numero"});
                            }
                            if(!matched){
                               Pattern pt = Pattern.compile("[(|)|{|}]+");
                               Matcher mt = pt.matcher(palabra);
                               if(mt.matches()){
                                dtm2.addRow(new Object[]{errores.get(i),"ERLX_DEL", "Linea...", "Incorrecto el delimitador"});
                                }
                               if(!matched){
                                 Pattern patr = Pattern.compile("[,|;]+");
                                 Matcher matc = patr.matcher(palabra);
                                 if(matc.matches()){
                                    dtm2.addRow(new Object[]{errores.get(i),"ERLX_SEP", "Linea...", "Incorrecto el SEP"});
                                 }
                                 if(!matched){
                                     Pattern ptr = Pattern.compile("[*|/|+|-|%|]+");
                                     Matcher mtch = ptr.matcher(palabra);
                                     if(mtch.matches()){
                                     dtm2.addRow(new Object[]{errores.get(i),"ERLX_OA", "Linea...", "Incorrecto el operador "});
                                 }

                                 }
                               }
                            }
                        }
                     }
                    
                }
                errores.clear();
            }
        }
        return tokens;
        
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
    int n = 0;
    int f =0;
    int g =0;
    int s =0;
    String[] txt = new String[200];
    private void jButton1_AnalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_AnalizaActionPerformed
       t =0;
       n = 1;
       f=1;
       g=1;
       s=1;
        if (txt_Expresion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite caracteres en el campo");
        }
        ArrayList<Token> tokens = lex(txt_Expresion.getText());
        for (Token token : tokens) {
            String a = ("" + token.getValor());
            String b = ("" + token.getTipo());
            tipo.add(a);
            if("ID".equals(b)){
                b = b+(n);
                n++;
            }else if("CNEPF".equals(b)){
                b = b+(f);
                f++;
            }else if("TD".equals(b)){
                b = b+(g);
                g++;
            }else if("OA".equals(b)){
                b = b+(s);
                s++;
            }
            tipotoken.add(b);
            txt[t]=b;
            dtm.addRow(new Object[]{tipo.get(j), tipotoken.get(j)});
            j++;
            t++;
            
            
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
