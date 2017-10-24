/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Penjualan_Motor_Sport;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.io.*;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ArieDZ
 */
public final class Frm_menu extends javax.swing.JFrame implements ActionListener {

    public DefaultTableModel dtm = new DefaultTableModel();
    private JLabel jLabel66;
    private JTextField tf_id_motor;
    private JTextField tf_merk_motor;
    private JTextField tf_nama_motor;
    private JLabel jLabel77;
    private JLabel jLabel88;
    private JLabel jLabel99;
    private JLabel jLabel100;
    private JButton jbSimpann;
    private JButton jbResett;
    private JButton jbBatall;
    private JComboBox<Object> combojenis_;
    private JComboBox<Object> jCariMenu_;
    private JTextField tfCarii;
    private JButton jbCarii;
    private JButton jbTambahh;
    private JButton jbHapuss;
    private JButton jbUbahh;
    private JScrollPane jScrollPane22;
    private JTable jTable22;
    private Object jcombojebnis;
    private final JSpinner spinner = new JSpinner();

    private ComboBoxModel[] models = new ComboBoxModel[6];

    public Frm_menu() {

        initComponents();
        ImageIcon image = new ImageIcon("Icon/icon.png");
        this.setIconImage(image.getImage());
        this.setTitle("DATA PENJUALAN MOTOSPORT");
        tampilTabel();
        disabled();

        //menghilangkan fungsi edit doubleclick pada tabel
        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    jTable2.getCellEditor().stopCellEditing();
                }
            }
        });

        tfMerk_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Pilih Merk", "Yamaha", "Honda", "Suzuki", "Kawasaki", "Ducati"}));
        ComboTest();

        /* jTable2=(new javax.swing.JTable(){
   @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }});*/
    }

    public void ComboTest() {
        models[0] = new DefaultComboBoxModel(
                new String[]{"Pilih Motor"});
        //YAMAHA
        models[1] = new DefaultComboBoxModel(
                new String[]{"1000cc-YZF-R1", "1000cc-YZF-R1M", "1000cc-YZF-R1s", "0600cc-YZF-R6", "0250cc-YZF-R25",
                    "1000cc-FZ-10", "0900cc-FZ-09", "0700cc-FZ-07", "0600cc-FZ-6R",
                    "0900cc-MT09", "0600cc-MT06", "0250cc-MT25"});
        //HONDA
        models[2] = new DefaultComboBoxModel(
                new String[]{"1000cc-RC213V-s",
                    "1000cc-CBR1000RR", "0600cc-CBR600RR",
                    "0500cc-CBR500R", "0250cc-CBR250RR"});
        //SUZUKI
        models[3] = new DefaultComboBoxModel(
                new String[]{"1400-HAYABUSA", "1000cc-GSXR1000R",
                    "1000cc-GSX-R1000 ABS", "1000cc-GSX-R1000", "0750cc-GSX-R750",
                    "0600cc-GSX-R600", "0250cc-GSX250R"});
        //KAWASAKI
        models[4] = new DefaultComboBoxModel(
                new String[]{"1000cc-NINJA H2R", "1400cc-ZX-14R ABS SE", "1400cc-ZX-14R ABS",
                    "1000cc-ZX-10RR", "1000cc-ZX-10R ABS KRT",
                    "1000cc-ZX-10R ABS", "0600cc-ZX-6R ABS KRT",
                    "0600cc-ZX-6R ABS", "0600cc-NINJA 650 ABS KRT",
                    "0600cc-NINJA 650 ABS KRT",
                    "0250cc-NINJA ABS KRT",
                    "0250cc-NINJA ABS"});
        //DUCATI
        models[5] = new DefaultComboBoxModel(
                new String[]{"1299cc-Panigale S", "1299cc-Panigale", "0959cc-Panigale S",
                    "0959cc-Panigale", "0858cc-Panigale S", "0858cc-Panigale"});

        tfNamaMotor_combo.setModel(models[0]);
        tfMerk_combobox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int i = tfMerk_combobox.getSelectedIndex();
        tfNamaMotor_combo.setModel(models[i]);
    }

    public void tampilTabel() {
        String columns[] = {"ID", "Merk", "Nama Motor", "Nama Pembeli"};
        dtm.setColumnIdentifiers(columns);

        String line;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("database_motorSport.txt"));
            while ((line = reader.readLine()) != null) {
                dtm.addRow(line.split("#"));
            }
            jTable2.setModel(dtm);
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
    }

    public static void cetakKeFile(String pNamaFile, StringBuffer pDataTabel) throws IOException {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(pNamaFile))) {
            output.write(pDataTabel.toString());
            output.flush();
        }
    }

    private void saveTable() throws IOException {
        StringBuffer sbTableData = new StringBuffer();

        for (int row = 0; row < jTable2.getRowCount(); row++) {
            for (int column = 0; column < jTable2.getColumnCount(); column++) {
                sbTableData.append(jTable2.getValueAt(row, column)).append("#");
            }

            sbTableData.append(System.getProperty("line.separator"));

        }
        cetakKeFile("database_motorSport.txt", sbTableData);
        ClearTextField();
    }

    public void ClearTextField() { //method ini digunakan untuk membersihkan jTextField
        // tfIDMotor.setText(null);
        tfMerk_combobox.setSelectedIndex(0);
        tfNamaMotor_combo.setSelectedIndex(0);
        tfNamaPembeli.setText(null);
    }

    public void disabled() {
//        tfIDMotor.setEnabled(false);
        tfMerk_combobox.setEnabled(false);
        tfNamaPembeli.setEnabled(false);
        tfNamaMotor_combo.setEnabled(false);
        jbSimpan_.setEnabled(false);
        jbReset_.setEnabled(false);
        jbBatal.setEnabled(false);
        jbHapus_.setEnabled(true);

        jbTambah.setEnabled(true);
    }

    public void enabled() {
//        tfIDMotor.setEnabled(true);
        tfMerk_combobox.setEnabled(true);
        tfNamaPembeli.setEnabled(true);
        jbSimpan_.setEnabled(true);
        jbReset_.setEnabled(true);
        jbBatal.setEnabled(true);
        tfNamaMotor_combo.setEnabled(true);
        jbTambah.setEnabled(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfNamaPembeli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfMerk_combobox = new javax.swing.JComboBox<>();
        tfNamaMotor_combo = new javax.swing.JComboBox<>();
        jbTambah = new javax.swing.JButton();
        jbSimpan_ = new javax.swing.JButton();
        jbBatal = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jCari_Menu = new javax.swing.JComboBox<>();
        jbCari_ = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        tfCari_ = new javax.swing.JTextField();
        jbReset = new javax.swing.JButton();
        jbHapus_ = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jbUbah = new javax.swing.JButton();
        jbReset_ = new javax.swing.JButton();

        jLabel1.setBackground(new java.awt.Color(204, 0, 204));
        jLabel1.setFont(new java.awt.Font("DokChampa", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Copyright  © 2016 Muhamad Rijal Baihaqi ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 255, 204));

        jLabel9.setFont(new java.awt.Font("DokChampa", 1, 12)); // NOI18N
        jLabel9.setText("Nama Motor");

        jLabel10.setFont(new java.awt.Font("DokChampa", 1, 12)); // NOI18N
        jLabel10.setText("Nama Pembeli");

        jLabel8.setFont(new java.awt.Font("DokChampa", 1, 12)); // NOI18N
        jLabel8.setText("Merk");

        tfNamaPembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaPembeliActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Data Penjualan MotoSport");

        tfMerk_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Merk", " ", " " }));
        tfMerk_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMerk_comboboxActionPerformed(evt);
            }
        });

        tfNamaMotor_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Motor", " " }));
        tfNamaMotor_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaMotor_comboActionPerformed(evt);
            }
        });

        jbTambah.setText("Tambah");
        jbTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTambahActionPerformed(evt);
            }
        });

        jbSimpan_.setText("Simpan");
        jbSimpan_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSimpan_ActionPerformed(evt);
            }
        });

        jbBatal.setText("Batal");
        jbBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBatalActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));

        jCari_Menu.setFont(new java.awt.Font("DokChampa", 1, 10)); // NOI18N
        jCari_Menu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "MERK", "PEMILIK" }));
        jCari_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCari_MenuActionPerformed(evt);
            }
        });

        jbCari_.setFont(new java.awt.Font("DokChampa", 1, 10)); // NOI18N
        jbCari_.setText("CARI");
        jbCari_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCari_ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBackground(new java.awt.Color(0, 255, 204));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Merk", "Nama Motor", "Nama Pembeli"
            }
        ));
        jTable2.setCellSelectionEnabled(true);
        jTable2.setDoubleBuffered(true);
        jTable2.setDragEnabled(true);
        jTable2.setInheritsPopupMenu(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        tfCari_.setFont(new java.awt.Font("DokChampa", 1, 10)); // NOI18N
        tfCari_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCari_ActionPerformed(evt);
            }
        });

        jbReset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbReset.setText("RESET");
        jbReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbResetActionPerformed(evt);
            }
        });

        jbHapus_.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbHapus_.setText("Hapus Data");
        jbHapus_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbHapus_ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 204, 0));

        jbUbah.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbUbah.setForeground(new java.awt.Color(255, 255, 255));
        jbUbah.setBorderPainted(false);
        jbUbah.setContentAreaFilled(false);
        jbUbah.setDefaultCapable(false);
        jbUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUbahActionPerformed(evt);
            }
        });

        jbReset_.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbReset_.setForeground(new java.awt.Color(255, 255, 255));
        jbReset_.setBorderPainted(false);
        jbReset_.setContentAreaFilled(false);
        jbReset_.setDefaultCapable(false);
        jbReset_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbReset_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jbUbah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbReset_))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jbUbah)
                .addComponent(jbReset_))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jCari_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCari_, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCari_, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbHapus_, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCari_)
                    .addComponent(tfCari_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCari_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbReset)
                    .addComponent(jbHapus_))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(tfNamaMotor_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(tfMerk_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(tfNamaPembeli)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbSimpan_, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jbTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSimpan_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbBatal))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfMerk_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfNamaMotor_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCari_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCari_ActionPerformed
        DefaultTableModel dtm1 = new DefaultTableModel();

        String columns[] = {"ID", "Merk", "Nama Motor", "Nama Pembeli"};
        dtm1.setColumnIdentifiers(columns);

        String line;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("database_motorSport.txt"));
            int i;
            while ((line = reader.readLine()) != null) {
                String asd[] = {};
                asd = line.split("#");
                //System.out.println(asd[1]);
                String searchOption = jCari_Menu.getSelectedItem().toString();
                String searchValue = tfCari_.getText().toLowerCase();
                if (searchOption.equalsIgnoreCase("MERK") && !searchValue.isEmpty()) {
                    if (asd[1].toLowerCase().contains(searchValue)) {
                        dtm1.addRow(line.split("#"));
                    }
                } else if (searchOption.equalsIgnoreCase("ID") && !searchValue.isEmpty()) {
                    if (asd[0].toLowerCase().equals(searchValue)) {
                        dtm1.addRow(line.split("#"));
                    }
                } else if (searchOption.equalsIgnoreCase("PEMILIK") && !searchValue.isEmpty()) {
                    if (asd[3].toLowerCase().contains(searchValue)) {
                        dtm1.addRow(line.split("#"));
                    }
                } else {
                    dtm1.addRow(line.split("#"));
                }

            }
            jTable2.setModel(dtm1);
            if (dtm1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan !", "", JOptionPane.INFORMATION_MESSAGE);
                tfCari_.setText(null);
                jTable2.setModel(dtm);

            }

            reader.close();
        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }

    }//GEN-LAST:event_jbCari_ActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int btnDialog = JOptionPane.showConfirmDialog(null, "Anda yakin ingin keluar?", "PERHATIAN!", JOptionPane.YES_NO_OPTION);

        if (btnDialog != JOptionPane.YES_OPTION) {
            remove(btnDialog);
        } else {
            System.exit(0);
        }
    }

    private void jCari_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCari_MenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCari_MenuActionPerformed

    private void tfCari_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCari_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCari_ActionPerformed

    private void jbReset_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbReset_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbReset_ActionPerformed

    private void tfNamaPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaPembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaPembeliActionPerformed

    private void tfMerk_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMerk_comboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMerk_comboboxActionPerformed

    private void jbResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbResetActionPerformed
        // TODO add your handling code here:
        tfCari_.setText(null);
        jTable2.setModel(dtm);
    }//GEN-LAST:event_jbResetActionPerformed

    private void jbTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTambahActionPerformed
        // TODO add your handling code here:
        enabled();
        jbUbah.setEnabled(false);
    }//GEN-LAST:event_jbTambahActionPerformed

    private void jbBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBatalActionPerformed
        // TODO add your handling code here:
        disabled();
        jbTambah.setEnabled(true);
        jbUbah.setEnabled(true);
        jbHapus_.setEnabled(true);
    }//GEN-LAST:event_jbBatalActionPerformed

    private void jbSimpan_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSimpan_ActionPerformed
        // TODO add your handling code here:
        if (jbSimpan_.isEnabled()) {
            try {
                String a = null;
                String b = tfMerk_combobox.getSelectedItem().toString();
                String c = tfNamaMotor_combo.getSelectedItem().toString();
                String d = tfNamaPembeli.getText().trim();
                String temp = null;
                int idx = tfMerk_combobox.getSelectedIndex();
                switch (idx) {
                    case 1:
                        a = "YMH";
                        break;
                    case 2:
                        a = "HDA";
                        break;
                    case 3:
                        a = "SZK";
                        break;
                    case 4:
                        a = "KWS";
                        break;
                    case 5:
                        a = "DCT";
                        break;

                }
                a += c.substring(0, 4);

                //a=a.substring(7, 10);
                String line;

                int counter_ = 0;
                String angka;
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader("database_motorSport.txt"));
                    int i;
                    while ((line = reader.readLine()) != null) {
                        String asd[] = {};
                        asd = line.split("#");

                        if (a.substring(0, 7).equalsIgnoreCase(asd[0].substring(0, 7))) {

                            angka = asd[0].substring(7, 10);
                            counter_ = Integer.valueOf(angka);
                        }
                    }

                    if (++counter_ < 10) {
                        a += "00" + String.valueOf(counter_);
                    } else if (counter_ < 100) {
                        a += "0" + String.valueOf(counter_);
                    } else if (counter_ >= 100) {
                        a += String.valueOf(counter_);
                    }

                    reader.close();
                } catch (IOException e) {
                }

                Object[] o = {a, b, c, d};

                if (b.equals("") || c.equals("") || d.equals("")) {
                    JOptionPane.showMessageDialog(null, "Data belum diisi !", "WARNING!", JOptionPane.WARNING_MESSAGE);
                } else {
                    dtm.addRow(o);
                    saveTable();
                }
            } catch (HeadlessException | IOException e) {
            }

            jTable2.setModel(dtm);
        } else if (jbUbah.isEnabled()) {
            try {
                //String a = tfIDMotor.getText().trim();
                String a = null;
                String b = tfMerk_combobox.getSelectedItem().toString();
                String c = tfNamaMotor_combo.getSelectedItem().toString();
                String d = tfNamaPembeli.getText().trim();

                if (b.equals("") || c.equals("") || d.equals("")) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan !", "WARNING!", JOptionPane.WARNING_MESSAGE);
                } else {
                    int konfirmasi = JOptionPane.showConfirmDialog(null, "Ingin mengubah data ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        int baris = jTable2.getSelectedRow();
                        jTable2.getModel().setValueAt(a, baris, 0);
                        jTable2.getModel().setValueAt(b, baris, 1);
                        jTable2.getModel().setValueAt(c, baris, 2);
                        jTable2.getModel().setValueAt(d, baris, 3);
                        ClearTextField();
                        saveTable();
                    }
                    JOptionPane.showMessageDialog(null, "Data telah diubah ^_^", "BERHASIL", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (HeadlessException | IOException e) {
            }
        }

        disabled();

    }//GEN-LAST:event_jbSimpan_ActionPerformed

    private void jbHapus_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHapus_ActionPerformed
        // TODO add your handling code here:
        try {
            int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                int x = jTable2.getSelectedRow();
                dtm.removeRow(x);
                JOptionPane.showMessageDialog(null, "Data telah di hapus ", "BERHASIL", JOptionPane.INFORMATION_MESSAGE);
            }
            saveTable();
        } catch (HeadlessException | IOException e) {
        }
    }//GEN-LAST:event_jbHapus_ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable2MouseClicked

    private void jbUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUbahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbUbahActionPerformed

    private void tfNamaMotor_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaMotor_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaMotor_comboActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_menu().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jCari_Menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton jbBatal;
    private javax.swing.JButton jbCari_;
    private javax.swing.JButton jbHapus_;
    private javax.swing.JButton jbReset;
    private javax.swing.JButton jbReset_;
    private javax.swing.JButton jbSimpan_;
    private javax.swing.JButton jbTambah;
    private javax.swing.JButton jbUbah;
    private javax.swing.JTextField tfCari_;
    private javax.swing.JComboBox<String> tfMerk_combobox;
    private javax.swing.JComboBox<String> tfNamaMotor_combo;
    private javax.swing.JTextField tfNamaPembeli;
    // End of variables declaration//GEN-END:variables
}
