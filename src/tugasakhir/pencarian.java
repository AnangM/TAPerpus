/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnangM
 */
public class pencarian extends javax.swing.JFrame {
    public Object [][] DatajTable1 = null;
    public String[] header  = {"ID Buku","Judul Buku","Pengarang","Penerbit","Tahun Terbit","Jenis Buku","Nama Lemari"};
    koneksi conn = new koneksi();
    
    void Bantuan(){
        URL url_ = getClass().getResource("/html/Pencarian.html");
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
    
    void cari(){
    String str = "", key_word;
    String kat = (String) combo_category.getSelectedItem();
    key_word = pb_keyWord.getText();
    if(kat.equals("ID Buku")){
        str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`idBuku` like '%"+key_word+"%'";
    }
    else if(kat.equals("Judul Buku")){
        str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`judul` like '%"+key_word+"%'";
    }
    else if(kat.equals("Nama Pengarang")){
        str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblpengarang`.`nama` like '%"+key_word+"%'";
    }
    else if(kat.equals("Nama Penerbit")){
        str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblpenerbit`.`namaPenerbit` like '%"+key_word+"%'";
    }
    else if(kat.equals("Tahun Terbit")){
        str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`tahunTerbit` like '%"+key_word+"%'";
    }
    else if(kat.equals("Jenis Buku")){
        str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`jenisBuku` like '%"+key_word+"%'";
    }
    else if(kat.equals("Nama Lemari")){
        str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblrak`.`namaLemari` like '%"+key_word+"%'";
    }
    
    try{
        Statement stm = conn.Con.createStatement();
        ResultSet rs = stm.executeQuery(str);
        ResultSetMetaData meta = rs.getMetaData();
        int row =0;
        int col = meta.getColumnCount();
        while(rs.next()){
        row = rs.getRow();
        }
        DatajTable1 = new Object[row][col];
        int x =0;
        rs.beforeFirst();
        while (rs.next()){
            DatajTable1 [x][0] = rs.getString("idBuku");
            DatajTable1 [x][1] = rs.getString("judul");
           DatajTable1 [x][2] = rs.getString("nama");
            DatajTable1 [x][3] = rs.getString("namaPenerbit");
            DatajTable1 [x][4] = rs.getString("tahunTerbit");
            DatajTable1 [x][5] = rs.getString("jenisBuku");
            DatajTable1 [x] [6] = rs.getString("namaLemari");
            x++;
        }
        jTable1.setModel(new DefaultTableModel(DatajTable1, header));
    
    }
    catch(SQLException e){
    System.out.println(e);
    }
    }
    void show_all(){
    try{
        Statement stm = conn.Con.createStatement();
        ResultSet rs = stm.executeQuery("select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari`");
        ResultSetMetaData meta = rs.getMetaData();
        int row =0;
        int col = meta.getColumnCount();
        while(rs.next()){
        row = rs.getRow();
        }
        DatajTable1 = new Object[row][col];
        int x =0;
        rs.beforeFirst();
        while (rs.next()){
            DatajTable1 [x][0] = rs.getString("idBuku");
            DatajTable1 [x][1] = rs.getString("judul");
           DatajTable1 [x][2] = rs.getString("nama");
            DatajTable1 [x][3] = rs.getString("namaPenerbit");
            DatajTable1 [x][4] = rs.getString("tahunTerbit");
            DatajTable1 [x][5] = rs.getString("jenisBuku");
            DatajTable1 [x] [6] = rs.getString("namaLemari");
            x++;
        }
        jTable1.setModel(new DefaultTableModel(DatajTable1, header));
    
    }
    catch(SQLException e){
    System.out.println(e);
    }}
    public pencarian() {
        initComponents();
        conn.openDB();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combo_category = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        pb_keyWord = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Pencarian Buku");

        combo_category.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_category.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID Buku", "Judul Buku", "Nama Pengarang", "Nama Penerbit", "Tahun Terbit", "Jenis Buku", "Nama Lemari" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Cari Berdasarkan");

        pb_keyWord.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pb_keyWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pb_keyWordActionPerformed(evt);
            }
        });
        pb_keyWord.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pb_keyWordKeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Cari !");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Buku", "Judul Buku", "Nama Pengarang", "Nama Penerbit", "Tahun Terbit", "Jenis Buku", "Nama Lemari"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Show All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Bantuan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(combo_category, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(pb_keyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(354, 354, 354))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(pb_keyWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       cari();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        show_all();
        pb_keyWord.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pb_keyWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pb_keyWordActionPerformed

    }//GEN-LAST:event_pb_keyWordActionPerformed

    private void pb_keyWordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pb_keyWordKeyPressed
     
    if (evt.getKeyCode()==KeyEvent.VK_ENTER){
        cari();
    }
    }//GEN-LAST:event_pb_keyWordKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    // adminCpanel cpanel = new adminCpanel();
    // cpanel.show();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       //adminCpanel cpanel = new adminCpanel();
     //cpanel.show(); // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        adminCpanel cpanel = new adminCpanel();
     cpanel.show();
     this.hide();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
   Bantuan();
   
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pencarian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox combo_category;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pb_keyWord;
    // End of variables declaration//GEN-END:variables
}
