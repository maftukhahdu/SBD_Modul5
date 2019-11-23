package modul6;
import TabelData.DataGame;
import TabelData.TabelData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author PraktikumModul6
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    private String mf_idgame;
    private String mf_judulgame;
    private String mf_genre;
    private String mf_rilis;
    private String mf_developer;
    private String mf_kodeplatform;
    private String mf_platform;
    private String mf_search;
    private String output;
    private TabelData tabeldata;
    private dbconnections c;
    private Statement script;
    
    private void Tampil(){
        try {
            int row = tabel.getRowCount();
            for(int i=0;i<row;i++){
                tabeldata.delete(0, row);
            }
            String sql="SELECT * from DB_GAME";
            ResultSet rsShow = c.script.executeQuery(sql);
            while(rsShow.next()){
                DataGame d = new DataGame();
                d.setIdgame(rsShow.getString("IDGAME"));
                d.setJudulgame(rsShow.getString("JUDULGAME"));
                d.setGenre(rsShow.getString("GENRE"));
                d.setRilis(rsShow.getString("RILIS"));
                d.setDeveloper(rsShow.getString("DEVELOPER"));
                d.setPlatform(rsShow.getString("PLATFORM"));
                d.setKodeplatform(rsShow.getString("KODEPLATFORM"));
                tabeldata.add(d);
            }
        }catch(Exception e){
            System.err.print(e);    
        }
    } 
    public String showData(DataGame dm){
        idgame.setText(dm.getIdgame());
        judulgame.setText(dm.getJudulgame());
        genre.setText(dm.getGenre());
        rilis.setText (dm.getRilis());
        developer.setText (dm.getDeveloper());
        kodeplatform.setText (dm.getKodeplatform());
        insert.setEnabled(false);
        return dm.getIdgame();
    }
    private void insertData(){
        mf_judulgame = judulgame.getText();
        mf_genre = genre.getText();
        mf_rilis = rilis.getText();
        mf_developer = developer.getText();
        mf_kodeplatform = kodeplatform.getText();
        if(judulgame!=null&&genre!=null&&rilis!=null){
            try{
                String sql = "INSERT INTO GAME (idgame, judulgame, genre, rilis, developer, kodeplatform) values (SEQ_MODUL6.nextval+1,'" +mf_judulgame+ "','"+mf_genre+"','"+mf_rilis+"','" +mf_developer+ "',"+mf_kodeplatform+")";
                c.script.executeUpdate(sql);
            }catch(SQLException e){
                System.err.print(e);
            }
            Tampil();
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            clearForm();
        }else{
            JOptionPane.showMessageDialog(null,"Setiap kolom harus diisi!");
        }
    }
    private void deleteData(){
    int app;
    mf_idgame = idgame.getText();
    if ((app=JOptionPane.showConfirmDialog(null,"Yakin ingin hapus data?","Hapus Data", JOptionPane.YES_NO_OPTION))==0){
    try {
        String sqlid = "SELECT IDGAME from GAME where idgame="+mf_idgame+"";
        ResultSet rsShow = c.script.executeQuery(sqlid);
        while (rsShow.next()){
	output = rsShow.getString(1);
}
        String sql = "DELETE from GAME where idgame ="+mf_idgame+"";
        c.script.executeUpdate(sql);
        Tampil();
        JOptionPane.showMessageDialog (null,"Berhasil dihapus");
        clearForm();
        tampil1();
    }
    catch (SQLException e){
        System.err.print(e);
            }
        }
    }
    private void updateData(String mf_idgame){
        int app;
                       
        if((app = JOptionPane.showConfirmDialog(null, "Yakin ingin update date?","Ubah Data",JOptionPane.YES_NO_OPTION))==0){
        	try{ //Query untuk update pada table database
                    mf_idgame = idgame.getText();
                    mf_judulgame = judulgame.getText();
                    mf_genre = genre.getText();
                    mf_rilis = rilis.getText();
                    mf_developer = developer.getText();
                    mf_kodeplatform = kodeplatform.getText();	
                             
                    String sqlid = "SELECT IDGAME from GAME where idgame=" +mf_idgame+ "";
                    ResultSet rsShow = c.script.executeQuery(sqlid);
                    while (rsShow.next()){
                    output = rsShow.getString(1);
                    } 
                        String sql = "UPDATE GAME SET judulgame='"+mf_judulgame+"',genre='"+mf_genre+"',rilis='"+mf_rilis+"',developer='"+mf_developer+"',kodeplatform="+mf_kodeplatform+" where idgame="+mf_idgame+"" ;
       			c.script.executeUpdate(sql);
        		Tampil();
                        tampil1();
		//menampilkan message dialog bahwa data telah update
        	JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        		clearForm();
        	}
        	catch(SQLException ex){
        	System.err.print(ex);
        }
    }}
    
    private void search(){
        mf_search = search.getText();
        try {
            int row = tabel.getRowCount();
            for(int i=0;i<row;i++){
                tabeldata.delete(0, row);
            }
            String sql = "SELECT * FROM DB_GAME WHERE lower(JUDULGAME) like lower ('%"+mf_search+"%')" + "or IDGAME like ('%"+mf_search+"%')" + "or GENRE like lower ('%"+mf_search+"%')" + "or RILIS like LOWER ('%"+mf_search+"%')" + "or DEVELOPER like LOWER('%"+mf_search+"%')" + "or PLATFORM like LOWER('%"+mf_search+"%')";
            ResultSet rsShow = c.script.executeQuery(sql);
            while(rsShow.next()){
                DataGame dg = new DataGame();
                dg.setIdgame(rsShow.getString("IDGAME"));
                dg.setJudulgame(rsShow.getString("JUDULGAME"));
                dg.setGenre(rsShow.getString("GENRE"));
                dg.setRilis(rsShow.getString("RILIS"));
                dg.setDeveloper(rsShow.getString("DEVELOPER"));
                dg.setPlatform(rsShow.getString("PLATFORM"));
                dg.setKodeplatform(rsShow.getString("KODEPLATFORM"));
                tabeldata.add(dg);
            }
        }catch(Exception e){
            System.err.print(e);
        }
        
    }
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        c = new dbconnections();
        tabeldata = new TabelData();
        tabel.setModel(tabeldata);
        Tampil();

    }
    public void clearForm(){
        judulgame.setText(null);
        genre.setText(null);
        rilis.setText(null);
        developer.setText (null);
        kodeplatform.setText (null);
        idgame.setText(null);
        
    }
    public void tampil1(){
    	insert.setEnabled(true);
    	delete.setEnabled(true);
    	update.setEnabled(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        judulgame = new javax.swing.JTextField();
        rilis = new javax.swing.JTextField();
        developer = new javax.swing.JTextField();
        kodeplatform = new javax.swing.JTextField();
        genre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        idgame = new javax.swing.JLabel();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        form_tambahplatform = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Game", "Judul Game", "Genre", "Tanggal Rilis", "Pengembang", "Platform"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jLabel1.setText("Judul Game");

        jLabel2.setText("Kode Platform");

        jLabel3.setText("Genre");

        jLabel4.setText("Tanggal Rilis");

        jLabel5.setText("Pengembang");

        jLabel6.setText("ID :");

        idgame.setText("0");

        insert.setBackground(new java.awt.Color(51, 255, 51));
        insert.setText("INSERT");
        insert.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        update.setBackground(new java.awt.Color(0, 255, 255));
        update.setText("UPDATE");
        update.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(204, 204, 204));
        delete.setText("DELETE");
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        form_tambahplatform.setBackground(new java.awt.Color(255, 102, 102));
        form_tambahplatform.setText("TAMBAH PLATFORM");
        form_tambahplatform.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        form_tambahplatform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                form_tambahplatformActionPerformed(evt);
            }
        });

        jLabel7.setText("Search");

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rilis, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kodeplatform, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(developer, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(form_tambahplatform, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(27, 27, 27)
                                        .addComponent(search))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(judulgame, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(idgame)
                        .addGap(180, 180, 180))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(judulgame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(idgame))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rilis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(developer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(kodeplatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addComponent(form_tambahplatform, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        insertData();
    }//GEN-LAST:event_insertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
    mf_idgame = idgame.getText();
    updateData(mf_idgame);
        
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    deleteData();        
    }//GEN-LAST:event_deleteActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        if(evt.getClickCount()==2){
         showData(this.tabeldata.get(tabel.getSelectedRow()));
         insert.setEnabled(false);
         delete.setEnabled(true);
         update.setEnabled(true);
    }                           

    }//GEN-LAST:event_tabelMouseClicked

    private void form_tambahplatformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_form_tambahplatformActionPerformed
    new TambahPlatform().setVisible(true);
    this.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_form_tambahplatformActionPerformed

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
    search();    // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyPressed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JTextField developer;
    private javax.swing.JButton form_tambahplatform;
    private javax.swing.JTextField genre;
    private javax.swing.JLabel idgame;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField judulgame;
    private javax.swing.JTextField kodeplatform;
    private javax.swing.JTextField rilis;
    private javax.swing.JTextField search;
    private javax.swing.JTable tabel;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
