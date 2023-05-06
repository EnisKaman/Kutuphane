/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kutuphane;

/**
 *
 * @author Enis
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class TableButtonExample extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public TableButtonExample() {
        setTitle("Table Button Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        // Tablo modelini oluştur
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Ad");
        tableModel.addColumn("Soyad");
        tableModel.addColumn("Düğme");

        // Tabloyu oluştur
        table = new JTable(tableModel);

        // Düğme sütununu oluştur
        TableColumn buttonColumn = new TableColumn(tableModel.getColumnCount() - 1);
        buttonColumn.setHeaderValue("Düğme");
        buttonColumn.setCellRenderer(new ButtonRenderer());
        buttonColumn.setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumnModel().addColumn(buttonColumn);

        // Tabloyu panele ekle
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Veri ekle
        tableModel.addRow(new Object[]{"Ahmet", "Yılmaz", "Düğme 1"});
        tableModel.addRow(new Object[]{"Ayşe", "Demir", "Düğme 2"});

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TableButtonExample();
            }
        });
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {

        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);

            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    // Düğmeye tıklandığında yapılması gereken işlemleri buraya yazabilirsiniz
                    JOptionPane.showMessageDialog(null, "Tıklanan düğme: " + button.getText());
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString());
            return button;
        }
    }
}

