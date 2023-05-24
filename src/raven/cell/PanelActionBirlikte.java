
package raven.cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelActionBirlikte extends javax.swing.JPanel {

    public PanelActionBirlikte() {
        initComponents();
    }

    public void initEvent (TableActionEventBelgeOkumaBirlikte event, int row){
      btnPdf.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
              event.PdfOkuma(row);
          }
          
      });
      btnTara.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
              event.TaranmisOkuma(row);
          }
          
      });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPdf = new raven.cell.ActionButton();
        btnTara = new raven.cell.ActionButton();

        btnPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/cell/icons8_adobe_acrobat_reader_32px.png"))); // NOI18N

        btnTara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/cell/icons8_file_32px.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTara, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTara, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.cell.ActionButton btnPdf;
    private raven.cell.ActionButton btnTara;
    // End of variables declaration//GEN-END:variables
}
