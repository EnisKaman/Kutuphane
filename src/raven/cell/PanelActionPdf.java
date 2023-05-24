package raven.cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelActionPdf extends javax.swing.JPanel {

    public PanelActionPdf() {
        initComponents();
    }

    public void initEvent(TableActionEventBelgeOkumaPdf event, int row){
        btnPdf.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
              event.PdfOkuma(row);
          }
          
      });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPdf = new raven.cell.ActionButton();

        btnPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/cell/icons8_adobe_acrobat_reader_32px.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.cell.ActionButton btnPdf;
    // End of variables declaration//GEN-END:variables
}
