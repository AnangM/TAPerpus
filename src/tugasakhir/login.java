/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.awt.event.KeyEvent;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URI;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
//select `tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari`;
/**
 *
 * @author AnangM
 */
public class login extends javax.swing.JFrame {
    adminCpanel cpanel = new adminCpanel();
    koneksi conn =new koneksi();
    ResultSet rs = null;
    PreparedStatement pst = null;
    
     
    public login() {
        initComponents();
        conn.openDB();
    }

    void Login(){
       String uname = login_u_in.getText();
       String upwd = login_up_in.getText();       
       try{
       Statement stm = conn.Con.createStatement();
       rs= stm.executeQuery("SELECT * FROM tbladmin WHERE uname ='" +uname+"' AND upwd ='" +upwd+"'");
       if(rs.next()){
           String un,up;
           un = rs.getString("uname");
           up = rs.getString("upwd");
           if(up.equals(upwd)&&un.equals(uname)){
           JOptionPane.showMessageDialog(null, "Welcome Admin!");
           cpanel.setVisible(true);
           this.hide();
           }else{
           JOptionPane.showMessageDialog(null, "BYPAS ANJING!");
           }       }
       else{
           JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
       }    }
       catch(Exception e){
           System.out.println(e);
       }    }
    
    
    void Bantuan(){
        URL url_ = getClass().getResource("/html/Login.html");
        String url = url_.toString();
     try
        {
            URI uri = new URL(url).toURI();
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
                desktop.browse(uri);
        }
    catch (Exception e)
        {
            /*
             *  I know this is bad practice 
             *  but we don't want to do anything clever for a specific error
             */
            e.printStackTrace();

            // Copy URL to the clipboard so the user can paste it into their browser
            StringSelection stringSelection = new StringSelection(url);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            // Notify the user of the failure
            JOptionPane.showMessageDialog(null,"Gagal membuak Browser, URL telah disalin di Clipboard : " + url);
        }
    }//end bantuan
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        login_u_in = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        login_up_in = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Selamat Datang di Aplikasi Index Buku");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("SMK NEGERI 2 YOGYAKARTA");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Username");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Password");

        login_u_in.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login_u_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                login_u_inKeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Login!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        login_up_in.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login_up_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                login_up_inKeyPressed(evt);
            }
        });

        jButton2.setText("Bantuan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(login_u_in, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                    .addComponent(login_up_in))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(login_u_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(login_up_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Login();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void login_up_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_login_up_inKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Login();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_login_up_inKeyPressed

    private void login_u_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_login_u_inKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            login_up_in.requestFocus();
        } 
    }//GEN-LAST:event_login_u_inKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Bantuan();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField login_u_in;
    private javax.swing.JPasswordField login_up_in;
    // End of variables declaration//GEN-END:variables
}
