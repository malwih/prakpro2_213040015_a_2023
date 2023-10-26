/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7;

import Pertemuan6.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author malwi
 */

//Untuk mengatur data yang akan ditampilkan di dalam tabel.
public class MyTableStudyCase7 extends AbstractTableModel {
    private String[] columnNames = {"Nama", "No.HP", "Jenis Kelamin", "Alamat"};
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    // Mengembalikan jumlah kolom dalam tabel
    public int getColumnCount() {
        return columnNames.length;
    }

    // Mengembalikan jumlah baris dalam tabel
    public int getRowCount() {
        return data.size();
    }

    // Mengembalikan nama kolom untuk indeks kolom tertentu
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Mengembalikan nilai sel di baris dan kolom tertentu dari data tabel
    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        return data.get(row).get(col);
    }

    // Mengembalikan false, yang menunjukkan bahwa sel dalam tabel tidak dapat diedit
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    // Untuk menambahkan baris data ke tabel
    public void add(ArrayList<String> value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Mengembalikan nilai sel di baris dan kolom tertentu dari data tabel
    public void setValueAt(Object aValue, int row, int col) {
        List<String> rowItem = data.get(row);
        rowItem.set(col, (String) aValue);
    }

    // Method untuk menghapus data dari model
    public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    void removeRow(int selectedRow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
