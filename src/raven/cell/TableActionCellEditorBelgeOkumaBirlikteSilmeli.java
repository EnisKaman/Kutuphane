/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Enis
 */
public class TableActionCellEditorBelgeOkumaBirlikteSilmeli extends DefaultCellEditor{
    private TableActionEventBelgeOkumaBirlikteSilmeli event;

    public TableActionCellEditorBelgeOkumaBirlikteSilmeli(TableActionEventBelgeOkumaBirlikteSilmeli event) {
        super(new JCheckBox());
        this.event = event;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionBirlikteSilmeli action = new PanelActionBirlikteSilmeli();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
}
