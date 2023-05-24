package raven.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditorBelgeOkumaPdf extends DefaultCellEditor{
    
    private TableActionEventBelgeOkumaPdf event;

    public TableActionCellEditorBelgeOkumaPdf(TableActionEventBelgeOkumaPdf event) {
        super(new JCheckBox());
        this.event = event;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionPdf action = new PanelActionPdf();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
}
