/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7;

import Pertemuan6.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

/**
 *
 * @author malwi
 */
public class StudyCase7 extends JFrame {

    private boolean checkBoxSelected;

    public StudyCase7() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat Label Nama
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        // Mengisi Inputan Nama
        JTextField textField = new JTextField();
        textField.setBounds(15, 60, 350, 30);

        // Membuat Label Nomor HP
        JLabel labelInputNo = new JLabel("Nomor HP:");
        labelInputNo.setBounds(15, 100, 350, 10);

        // Mengisi Inputan Nomor HP
        JTextField textFieldNo = new JTextField();
        textFieldNo.setBounds(15, 120, 350, 30);

        // Membuat RadioButton untuk Jenis Kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 160, 350, 10);

        // Pilihan untuk Laki-laki
        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        radioButton1.setBounds(15, 185, 350, 30);

        // Pilihan untuk Perempuan
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 215, 350, 30);

        // Membuat Label Alamat
        JLabel labelInputAlamat = new JLabel("Alamat:");
        labelInputAlamat.setBounds(15, 250, 350, 10);
        
        // TextArea untuk memasukkan alamat
        JTextArea textAreaAlamat = new JTextArea("");
        textAreaAlamat.setBounds(15,270,350,100);

        // Menampilkan radioButton
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Membuat tombol "Simpan"
        JButton button = new JButton("Simpan");
        button.setBounds(15, 380, 100, 40);
        
        // Membuat tombol "Edit"
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(120, 380, 100, 40);
        
        // Membuat tombol "Simpan Edit"
        JButton buttonSimpanEdit = new JButton("Simpan Edit");
        buttonSimpanEdit.setBounds(225, 380, 130, 40);
        
        // Membuat tombol "Hapus"
        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(360, 380, 100, 40);
        
        // Membuat tombol "Simpan Ke File"
        JButton buttonFile = new JButton("Simpan Ke File");
        buttonFile.setBounds(465, 380, 130, 40);

        // Membuat Table dan Scrollpane
        javax.swing.JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 440, 550, 200);

        // Membuat model table
        MyTableStudyCase7 tableModel = new MyTableStudyCase7();
        table.setModel(tableModel);

        // Membuat fungsi pada button "Simpan"
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                 // Logic untuk radioButton(jenisKelamin)
                String jenisKelamin = "";
                if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                if (radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }

                // Logic untuk nama, nomor hp dan alamat agar ditampilkan di Table
                String nama = textField.getText();
                String noHp = textFieldNo.getText();
                String alamat = textAreaAlamat.getText();
                
                if(nama.equalsIgnoreCase("") && noHp.equalsIgnoreCase("") && alamat.equalsIgnoreCase(""))
                {
                    JOptionPane.showMessageDialog(StudyCase7.this,"Nama, Nomor HP dan Alamat Wajib diisi!", "WARNING!",JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    if(nama.equalsIgnoreCase(""))
                    {
                    JOptionPane.showMessageDialog(StudyCase7.this,"Nama belum terisi!", "WARNING!",JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                    if(noHp.equalsIgnoreCase(""))
                    {
                    JOptionPane.showMessageDialog(StudyCase7.this,"Nomor HP belum terisi!", "WARNING!",JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                    if(alamat.equalsIgnoreCase(""))
                    {
                    JOptionPane.showMessageDialog(StudyCase7.this,"Alamat belum terisi!", "WARNING!",JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                }
                
                // Menampilkan dialog konfirmasi dengan dua tombol pilihan (YES dan NO)
                // dan menyimpan hasilnya dalam variabel confirmation
                int confirmation = JOptionPane.showConfirmDialog(StudyCase7.this,
                        "Apakah anda yakin ingin menyimpan data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                // Percabangan yang memeriksa apakah pengguna memilih tombol "YES" dalam dialog konfirmasi
                if (confirmation == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showMessageDialog(StudyCase7.this,"Data anda sudah disimpan.");
                    // Memasukkan objek nama, nomorHp, jenisKelamin, wnaCheck kedalam array
                    // yang kemudian ditampilkan ke Table
                    tableModel.add(new ArrayList<>(Arrays.asList(nama, noHp, jenisKelamin, alamat)));
                    textAreaAlamat.setText("");
                    textField.setText("");
                    textFieldNo.setText("");
                } else{
                    // Menampilkan dialog pesan dengan informasi bahwa pengguna telah menekan tombol "NO"
                    JOptionPane.showMessageDialog(StudyCase7.this,"Data anda tidak disimpan.");
                    textAreaAlamat.setText("");
                    textField.setText("");
                    textFieldNo.setText("");
                }
            }            
        });
        
        // Membuat ActionListener untuk tombol "Edit"
        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Mendapatkan baris yang dipilih dalam tabel
                if (selectedRow >= 0) {
                    String nama = textField.getText();
                    String noHp = textFieldNo.getText();
                    String alamat = textAreaAlamat.getText();
                    String jenisKelamin = "";
                    if (radioButton1.isSelected()) {
                        jenisKelamin = radioButton1.getText();
                    }
                    if (radioButton2.isSelected()) {
                        jenisKelamin = radioButton2.getText();
                    }

                    // Memperbarui nilai-nilai komponen GUI dengan data dari baris yang dipilih
                    textField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    textFieldNo.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    textAreaAlamat.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    
                    if (jenisKelamin.equals(radioButton1.getText())) {
                        radioButton1.setSelected(true);
                    } else if (jenisKelamin.equals(radioButton2.getText())) {
                        radioButton2.setSelected(true);
                    }
                }
            }
        });

        // Membuat ActionListener untuk tombol "Simpan Edit"
        buttonSimpanEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Mendapatkan baris yang dipilih dalam tabel
                if (selectedRow >= 0) {
                    String nama = textField.getText();
                    String noHp = textFieldNo.getText();
                    String alamat = textAreaAlamat.getText();
                    String jenisKelamin = "";
                    if (radioButton1.isSelected()) {
                        jenisKelamin = radioButton1.getText();
                    }
                    if (radioButton2.isSelected()) {
                        jenisKelamin = radioButton2.getText();
                    }

                    // Hapus baris yang ada di tabel sebelum menambahkan yang baru
                    tableModel.remove(selectedRow);

                    // Menambahkan data yang diperbarui ke model tabel
                    tableModel.add(new ArrayList<>(Arrays.asList(nama, noHp, jenisKelamin, alamat)));

                    // Memperbarui komponen GUI
                    textField.setText("");
                    textFieldNo.setText("");
                    textAreaAlamat.setText("");
                    radioButton1.setSelected(true);
                    radioButton2.setSelected(false);
                }
            }
        });

        // Membuat ActionListener untuk tombol "Hapus"
        buttonHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmation = JOptionPane.showConfirmDialog(StudyCase7.this,
                        "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        // Hapus data yang terkait dengan baris yang dihapus dari file.txt
                        try {
                            File inputFile = new File("C:/Users/malwi/OneDrive/Documents/NetBeansProjects/Swing/src/Pertemuan7/file.txt");
                            File tempFile = new File("temp.txt");

                            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                            String lineToRemove = tableModel.getValueAt(selectedRow, 0).toString() + ","
                                + tableModel.getValueAt(selectedRow, 1).toString() + ","
                                + tableModel.getValueAt(selectedRow, 2).toString() + ","
                                + tableModel.getValueAt(selectedRow, 3).toString();

                            String currentLine;

                            while ((currentLine = reader.readLine()) != null) {
                                String trimmedLine = currentLine.trim();
                                if (trimmedLine.equals(lineToRemove)) continue;
                                writer.write(currentLine + System.getProperty("line.separator"));
                            }
                            writer.close();
                            reader.close();

                            tempFile.renameTo(inputFile);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(StudyCase7.this, "Gagal menghapus data dari file.txt", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Hapus baris yang dipilih dari model tabel
                        tableModel.remove(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(StudyCase7.this, "Pilih baris yang ingin dihapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Membuat ActionListener untuk tombol "Simpan Ke File"
        buttonFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ganti path ke drive yang diinginkan:
                    FileWriter fileWriter = new FileWriter("C:/Users/malwi/OneDrive/Documents/NetBeansProjects/Swing/src/Pertemuan7/file.txt");

                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String nama = tableModel.getValueAt(i, 0).toString();
                        String nomorHP = tableModel.getValueAt(i, 1).toString();
                        String jenisKelamin = tableModel.getValueAt(i, 2).toString();
                        String alamat = tableModel.getValueAt(i, 3).toString();

                        fileWriter.write(nama + "  |  " + nomorHP + "  |  " + jenisKelamin + "  |  " + alamat + "\n");
                    }

                    fileWriter.close();
                    JOptionPane.showMessageDialog(StudyCase7.this, "Data telah disimpan.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(StudyCase7.this, "Gagal menyimpan data!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Menambahkan windows listener untuk memunculkan dialog ketika keluar aplikasi
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(StudyCase7.this,
                        "Apakah anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    dispose(); // Close the window
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // Menampilkan objek-objek kedalam frame
        this.add(button);
        this.add(textField);
        this.add(textFieldNo);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelInput);
        this.add(labelInputNo);
        this.add(labelInputAlamat);
        this.add(scrollableTable);
        this.add(textAreaAlamat);
        this.add(buttonEdit);
        this.add(buttonHapus);
        this.add(buttonFile);
        this.add(buttonSimpanEdit);

        // Untuk mengatur ukuran frame
        this.setSize(700, 700);
        this.setLayout(null);
    }

    // Main Program
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StudyCase7 h = new StudyCase7();
                h.setVisible(true);
            }
        });
    }
}
