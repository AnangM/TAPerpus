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
import java.util.Calendar;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnangM
 */
public class buku extends javax.swing.JFrame {

    public Object [][] DatajTable1 = null;
    public String[] header  = {"ID Buku","Judul Buku","Pengarang","Penerbit","Tahun Terbit","Jenis Buku","Nama Lemari"};
    koneksi conn = new koneksi();
    
    
    public void lihatBuku(){
    try{
        Statement stm = conn.Con.createStatement();
        ResultSet rs = stm.executeQuery("select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` order by idBuku asc");
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
    
        void tambah(){
        String id_buku, id_lemari, id_penerbit, id_pengarang, jenis_buku, judul_buku, tahun_terbit;
        id_buku = jTextField2.getText();
        id_lemari = jTextField7.getText();
        id_penerbit = jTextField5.getText();
        id_pengarang = jTextField4.getText();
        jenis_buku = (String) jComboBox1.getSelectedItem();
        judul_buku =jTextField3.getText();
        tahun_terbit = jTextField6.getText();
        int tth = Integer.parseInt(tahun_terbit);
        int th = Calendar.getInstance().get(Calendar.YEAR);
        if(tth < th){           if(!id_buku.equals("")){               try{
                    Statement stm;
                    stm = conn.Con.createStatement();
                    String str = "INSERT INTO `tblbuku` (`idBuku`, `judul`, `idPengarang`, `idPenerbit`, `tahunTerbit`, `jenisBuku`, `idLemari`) VALUES ('"+id_buku+"', '"+judul_buku+"', '"+id_pengarang+"', '"+id_penerbit+"', '"+tahun_terbit+"', '"+jenis_buku+"', '"+id_lemari+"')";
                    stm.executeUpdate(str);
                    stm.close();                
                    JOptionPane.showMessageDialog(null, "Input Berhasil");
                    lihatBuku();
                    clear();                }
                catch(Exception e){
                    System.out.println(e);
                }            }
            else{
                JOptionPane.showMessageDialog(null, "ID Buku Wajib Di Isi!");
            }       }
        else{
            JOptionPane.showMessageDialog(null, "Periksa Tahun Terbit Buku!");
        }     }
    
    void apdet(){      
        del.setEnabled(true);
        jTextField2.setEditable(false);
        tambah.setEnabled(false);
        cancel.setVisible(true);
        up.setEnabled(true);        //mengambil data dari baris yang di pilih
        int TRow = jTable1.getSelectedRow();
        jTable1.setAutoCreateRowSorter(true);
        if (TRow >= 0){
            String idBuku = jTable1.getModel().getValueAt(TRow, 0).toString();
            String judul = jTable1.getModel().getValueAt(TRow, 1).toString();
            String pengarang = jTable1.getModel().getValueAt(TRow, 2).toString();
            String penerbit = jTable1.getModel().getValueAt(TRow, 3).toString();
            String tahunTerbit = jTable1.getModel().getValueAt(TRow, 4).toString();
            String jenis = jTable1.getModel().getValueAt(TRow, 5).toString();
            String lemari = jTable1.getModel().getValueAt(TRow, 6).toString();          //menempatkan data yang barusan di ambil dari tabel ke text field
        jTextField2.setText(idBuku);
        jTextField3.setText(judul);      
        jTextField6.setText(tahunTerbit);
        jComboBox1.setSelectedItem(jenis);
        try{
       Statement stm = conn.Con.createStatement();
       ResultSet rs= stm.executeQuery("SELECT tblbuku.idPengarang, tblbuku.idPenerbit, tblbuku.idLemari FROM `tblbuku` WHERE tblbuku.idBuku ='"+idBuku+"'");
       if(rs.next()){
           jTextField4.setText(rs.getString("idPengarang"));
           jTextField5.setText(rs.getString("idPenerbit"));
           jTextField7.setText(rs.getString("idLemari"));    }
        }catch (Exception e){
            System.out.println(e);
        }       }       else{JOptionPane.showMessageDialog(null, "Pilih Row Terlebih Dahulu!");}    }// end apdet
    
    void cari(){
    String str = "", key_word;
    String kat = (String) combo_category.getSelectedItem();
    key_word = jTextField1.getText();
        switch (kat) {
            case "ID Buku":
                str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`idBuku` like '%"+key_word+"%'";
                break;
            case "Judul Buku":
                str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`judul` like '%"+key_word+"%'";
                break;
            case "Nama Pengarang":
                str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblpengarang`.`nama` like '%"+key_word+"%'";
                break;
            case "Nama Penerbit":
                str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblpenerbit`.`namaPenerbit` like '%"+key_word+"%'";
                break;
            case "Tahun Terbit":
                str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`tahunTerbit` like '%"+key_word+"%'";
                break;
            case "Jenis Buku":
                str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblbuku`.`jenisBuku` like '%"+key_word+"%'";
                break;
            case "Nama Lemari":
                str = "select `tblbuku`.`idBuku`,`tblbuku`.`judul`, `tblpengarang`.`nama`,`tblpenerbit`.`namaPenerbit`,`tblbuku`.`tahunTerbit`,`tblbuku`.`jenisBuku`,`tblrak`.`namaLemari` from `tblbuku` inner join `tblpengarang` on `tblbuku`.`idPengarang` = `tblpengarang`.`idPengarang` inner join `tblpenerbit` on `tblbuku`.`idPenerbit`=`tblpenerbit`.`idPenerbit` inner join `tblrak` on `tblbuku`.`idLemari` = `tblrak`.`idLemari` where `tblrak`.`namaLemari` like '%"+key_word+"%'";
                break;}
    try{
        Statement stm = conn.Con.createStatement();
        ResultSet rs = stm.executeQuery(str);
        ResultSetMetaData meta = rs.getMetaData();
        int row =0;
        int col = meta.getColumnCount();
        while(rs.next()){
        row = rs.getRow();}
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
            x++; }
        jTable1.setModel(new DefaultTableModel(DatajTable1, header));
        System.out.println("Muncul");
        System.out.println(str); }
    catch(SQLException e){
    System.out.println(e);
    System.out.println("Exception Thrown, error");
    }  }// end cari
    void delEntry(){
    String id = jTextField2.getText();
    Statement stm;
    try{ 
        stm = conn.Con.createStatement();
        String str = "DELETE FROM `tblbuku` WHERE `tblbuku`.`idBuku` = '"+id+"'";
        stm.executeUpdate(str);
        stm.close();    
        JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
                    clear();
                    lihatBuku();
                    del.setEnabled(false);
                    cancel.setVisible(false);
                    up.setEnabled(false);
                    tambah.setEnabled(true); }
    catch(Exception e ){
    JOptionPane.showMessageDialog(null, e);
    }}// end del_entry
    void save(){
        String id_buku, id_lemari, id_penerbit, id_pengarang, jenis_buku, judul_buku, tahun_terbit;
        id_buku = jTextField2.getText();
        id_lemari = jTextField7.getText();
        id_penerbit = jTextField5.getText();
        id_pengarang = jTextField4.getText();
        jenis_buku = (String) jComboBox1.getSelectedItem();
        judul_buku =jTextField3.getText();
        tahun_terbit = jTextField6.getText();
        int tth = Integer.parseInt(tahun_terbit);
        int th = Calendar.getInstance().get(Calendar.YEAR);
        if(tth < th){ try{
                Statement stm;
                stm = conn.Con.createStatement();
                String str = "UPDATE `tblbuku` SET `idBuku` = '"+ id_buku +"', `judul` = '"+ judul_buku +"', `idPengarang` = '"+id_pengarang+"', `idPenerbit` = '"+id_penerbit+"', `tahunTerbit` = '"+tahun_terbit+"', `jenisBuku` = '"+jenis_buku+"', `idLemari` = '"+id_lemari+"' WHERE `tblbuku`.`idBuku` ='"+id_buku+"'";
                stm.executeUpdate(str);
                stm.close();
                lihatBuku();
                JOptionPane.showMessageDialog(null, "Update Berhasil");
                clear(); 
                jTextField2.setEditable(true);
                del.setEnabled(false);
                up.setEnabled(false);
                tambah.setEnabled(true);
                cancel.setVisible(false);}
            catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null, e);}}
        else{
            JOptionPane.showMessageDialog(null, "Periksa Tahun Terbit Buku!");}}// end save
    
    void clear(){
    jComboBox1.setSelectedIndex(1);
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField6.setText("");
    jTextField7.setText("");
    }// end clear
    
    void Bantuan(){
        URL url_ = getClass().getResource("/html/Buku.html");
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
    
    public buku() {
        initComponents();
        cancel.setVisible(false);
        up.setEnabled(false);
        del.setEnabled(false);
        conn.openDB();
        lihatBuku();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pb_keyWord = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        combo_category = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        up = new javax.swing.JButton();
        del = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        Bantuan = new javax.swing.JButton();

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Buku", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit", "Jenis Buku", "Nama Lemari"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        combo_category.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_category.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID Buku", "Judul Buku", "Nama Pengarang", "Nama Penerbit", "Tahun Terbit", "Jenis Buku", "Nama Lemari" }));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Cari !");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID Buku");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Judul Buku");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ID Pengarang");

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ID Penerbit");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tahun Terbit");

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Jenis Buku");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Novel", "Tutorial", "Cerita", "Pelajaran", "Majalah", "Koran", "Fiksi", "Fiksi Ilmiah" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("ID Lemari");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        up.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        up.setText("Update");
        up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upActionPerformed(evt);
            }
        });

        del.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        del.setText("Delete");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        tambah.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Manajemen Buku");

        Bantuan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Bantuan.setText("Bantuan");
        Bantuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BantuanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Bantuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combo_category, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(up)
                            .addComponent(del)
                            .addComponent(tambah)
                            .addComponent(cancel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Bantuan)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pb_keyWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pb_keyWordActionPerformed

    }//GEN-LAST:event_pb_keyWordActionPerformed

    private void pb_keyWordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pb_keyWordKeyPressed
        
    }//GEN-LAST:event_pb_keyWordKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cari();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        apdet();
    }//GEN-LAST:event_jTable1MouseClicked

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
       jTextField2.setEditable(true);
        del.setEnabled(false);
        up.setEnabled(false);
        tambah.setEnabled(true);
        cancel.setVisible(false);
        clear();
    }//GEN-LAST:event_cancelActionPerformed

    private void upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upActionPerformed
       if(!jTextField2.isEditable()){
        if(!jTextField2.getText().equals("")){
            save();
      }
      else{
      JOptionPane.showMessageDialog(null, "Pilih Buku dari tabel di bawah untuk melakukan Update!");
      }}
      else{
      }
    }//GEN-LAST:event_upActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
       tambah();
    }//GEN-LAST:event_tambahActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        delEntry();
    }//GEN-LAST:event_delActionPerformed

    private void BantuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BantuanActionPerformed
       Bantuan();
    }//GEN-LAST:event_BantuanActionPerformed

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
            java.util.logging.Logger.getLogger(buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bantuan;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox combo_category;
    private javax.swing.JButton del;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField pb_keyWord;
    private javax.swing.JButton tambah;
    private javax.swing.JButton up;
    // End of variables declaration//GEN-END:variables
}
