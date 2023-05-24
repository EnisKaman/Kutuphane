package raven.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditorBelgeOkumaTarama extends DefaultCellEditor{
    
    private TableActionEventBelgeOkumaTarama event;

    public TableActionCellEditorBelgeOkumaTarama(TableActionEventBelgeOkumaTarama event) {
        super(new JCheckBox());
        this.event = event;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionTarama action = new PanelActionTarama();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
}
