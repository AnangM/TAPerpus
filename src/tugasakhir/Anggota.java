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
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnangM
 */
public class Anggota extends javax.swing.JFrame {

    koneksi konek = new koneksi();
    public Object [][] DatajTable1 = null;
    public String[] header  = {"ID Anggota","Nama","Alamat","No Telp","Tgl. Lahir"};
      void Bantuan(){
        URL url_ = getClass().getResource("/html/Anggota.html");
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
void lihatDaftar(){
    try{
    Statement stm = konek.Con.createStatement();
    ResultSet rs = stm.executeQuery("select * from `tblanggota`");
    ResultSetMetaData meta = rs.getMetaData();int row =0;
        int col = meta.getColumnCount();
        while(rs.next()){
        row = rs.getRow();}
        DatajTable1 = new Object[row][col];
        int x =0;
        rs.beforeFirst();
        while (rs.next()){
            DatajTable1 [x][0] = rs.getString("idAnggota");
            DatajTable1 [x][1] = rs.getString("nama");
            DatajTable1 [x][2] = rs.getString("alamat");
            DatajTable1 [x][3] = rs.getString("telp");
            DatajTable1 [x][4] = rs.getString("tglLahir");x++;}
        jTable1.setModel(new DefaultTableModel(DatajTable1, header));}
    catch (SQLException e){
        JOptionPane.showMessageDialog(null, e);System.out.println(e);}}

void update(){
    String id,nama,alamat, noTelp,tanggal;
    id = jTextField1.getText();
    nama = jTextField2.getText();
    alamat = jTextField3.getText();
    noTelp = jTextField4.getText();
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    jXDatePicker1.setFormats(dateFormat);
    DateFormat sysDate = new SimpleDateFormat("dd-MMM-yyyy");
    tanggal = sysDate.format(jXDatePicker1.getDate()).toString(); 
                try{
                    Statement stm;stm = konek.Con.createStatement();//UPDATE
                    String str = "UPDATE `tblanggota` SET `idAnggota` = '"+id+"', `nama` = '"+nama+"', `alamat` = '"+alamat+"', `telp` = '"+noTelp+"', `tglLahir` = '"+tanggal+"' WHERE `tblanggota`.`idAnggota` = "+id;
                    stm.executeUpdate(str);
                    stm.close();
                    tambah.setEnabled(true);
                    edit.setEnabled(false);
                    cancel.setVisible(false);
                    del.setEnabled(false);
                    jTextField1.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Update Berhasil");
                    lihatDaftar();
                    empty_all();}//end try
                catch(Exception e){
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, e);}}//end update

void tambah(){
    String id,nama,alamat, noTelp,tanggal;
    id = jTextField1.getText();
    nama = jTextField2.getText();
    alamat = jTextField3.getText();
    noTelp = jTextField4.getText();
     DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    jXDatePicker1.setFormats(dateFormat);
    DateFormat sysDate = new SimpleDateFormat("dd-MMM-yyyy");
    tanggal = sysDate.format(jXDatePicker1.getDate()).toString(); 
    if(!id.equals("")){try{
                    Statement stm;
                    stm = konek.Con.createStatement();// INPUT 
                    String str = "INSERT INTO `tblanggota` (`idAnggota`, `nama`, `alamat`, `telp`, `tglLahir`) VALUES ('"+id+"', '"+nama+"', '"+alamat+"', '"+noTelp+"', '"+tanggal+"')";
                    stm.executeUpdate(str);stm.close();
                    JOptionPane.showMessageDialog(null, "Input Berhasil");
                    lihatDaftar();
                    empty_all();}  catch(Exception e){
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, e);}}// end if
    else {JOptionPane.showMessageDialog(null, "ID Wajib Di Isi");}}//end del

void Del(){
    String id;id = jTextField1.getText();
    if(!id.equals("")){
                try{
                    Statement stm;
                    stm = konek.Con.createStatement();
                    String str = "DELETE FROM `tblanggota` WHERE `tblanggota`.`idAnggota` =" + id;
                    stm.executeUpdate(str);stm.close();
                    JOptionPane.showMessageDialog(null, "Hapus Berhasil");
                    lihatDaftar();empty_all();
                    tambah.setEnabled(true);
                    edit.setEnabled(false);
                    cancel.setVisible(false);
                    del.setEnabled(false);
                    jTextField1.setEditable(true);
                }//end try
                catch(Exception e){
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, e);}}//end if
            else{
                JOptionPane.showMessageDialog(null, "ID Wajib Di Isi!");}}//end tambah

void apdet(){
 del.setEnabled(true);
        jTextField1.setEditable(false);
        tambah.setEnabled(false);
        cancel.setVisible(true);
        edit.setEnabled(true);
        //mengambil data dari baris yang di pilih
        int TRow = jTable1.getSelectedRow();
        jTable1.setAutoCreateRowSorter(true);
        if (TRow >= 0){//Start If
            String idAnggota = jTable1.getModel().getValueAt(TRow, 0).toString();
            String Nama = jTable1.getModel().getValueAt(TRow, 1).toString();
            String Alamat = jTable1.getModel().getValueAt(TRow, 2).toString();
            String NoTelp = jTable1.getModel().getValueAt(TRow, 3).toString();
            String Tanggal = jTable1.getModel().getValueAt(TRow, 4).toString();
        jTextField1.setText(idAnggota);
        jTextField2.setText(Nama);      
        jTextField3.setText(Alamat);
        jTextField4.setText(NoTelp);
        try{
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Date date0 = df.parse(Tanggal);
        jXDatePicker1.setDate(date0);
        String cDate = date0.toString();
        System.out.println(cDate);}
        catch(java.text.ParseException e){
        JOptionPane.showMessageDialog(null, e);}}//End If
        else{
            JOptionPane.showMessageDialog(null, "Pilih Row Terlebih Dahulu!");}}//End apdet()

void empty_all(){
jTextField1.setText("");
jTextField2.setText("");
jTextField3.setText("");
jTextField4.setText("");
Date date = new Date();
jXDatePicker1.setDate(date);
}//end empty_all()
    public Anggota() {
        initComponents();
        konek.openDB();
        lihatDaftar();
        cancel.setVisible(false);
        edit.setEnabled(false);
        del.setEnabled(false);
        Date date = new Date();
        jXDatePicker1.setDate(date);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        jXDatePicker1.setFormats(dateFormat);
    } //end public Anggota()

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        edit = new javax.swing.JButton();
        del = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        tambah1 = new javax.swing.JButton();

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tambah.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Id Anggota");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tgl Lahir");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("No Telp");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Anggota", "Nama", "Alamat", "No Telp", "Tgl. Lahir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        edit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edit.setText("Update");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        del.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        del.setText("Delete");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Manajemen Anggota");

        jXDatePicker1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tambah1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tambah1.setText("Bantuan");
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(111, 111, 111)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
tambah();       
    }//GEN-LAST:event_tambahActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
       update();
    }//GEN-LAST:event_editActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
       Del();
    }//GEN-LAST:event_delActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
      empty_all();
      tambah.setEnabled(true);
      edit.setEnabled(false);
      cancel.setVisible(false);
      del.setEnabled(false);
      jTextField1.setEditable(true);
    }//GEN-LAST:event_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       apdet();
    }//GEN-LAST:event_jTable1MouseClicked

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
       Bantuan();
    }//GEN-LAST:event_tambah1ActionPerformed

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
            java.util.logging.Logger.getLogger(Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Anggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton del;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JButton tambah;
    private javax.swing.JButton tambah1;
    // End of variables declaration//GEN-END:variables
}
