/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javax.swing.JOptionPane;


/**
 *
 * @author fmedi
 */
public class Entrada extends javax.swing.JFrame {

    public static String expresion = "";
    /**
     * Creates new form Inicio
     */
    public Entrada() {
        initComponents();
        setSize(475,390);
        setResizable(false);
        setTitle("Analizador Sintáctico");
        setLocationRelativeTo(null);
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Expresion = new javax.swing.JTextArea();
        jButton1_Analiza = new javax.swing.JButton();
        jLabel1_Instruccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Expresion.setColumns(20);
        txt_Expresion.setRows(5);
        txt_Expresion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(txt_Expresion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 310, 180));

        jButton1_Analiza.setText("Analiza");
        jButton1_Analiza.setBorder(null);
        jButton1_Analiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_AnalizaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1_Analiza, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 80, 40));

        jLabel1_Instruccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1_Instruccion.setText("Ingresa la expresión a analizar");
        jLabel1_Instruccion.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(jLabel1_Instruccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1_AnalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_AnalizaActionPerformed
       expresion = txt_Expresion.getText();
       
       if(!expresion.equals("") ){
          dispose();
          new Salida().setVisible(true);
       }else{
           JOptionPane.showMessageDialog(null, "Debes ingresar una expresión para analizarla");
        }
    }//GEN-LAST:event_jButton1_AnalizaActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Entrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1_Analiza;
    private javax.swing.JLabel jLabel1_Instruccion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_Expresion;
    // End of variables declaration//GEN-END:variables
}
