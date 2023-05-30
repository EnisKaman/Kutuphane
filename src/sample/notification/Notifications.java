package sample.notification;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import kutuphane.Baglanti;
import net.miginfocom.swing.MigLayout;
import sample.swing.ModernScrollBarUI;

/**
 *
 * @author RAVEN
 */
public class Notifications extends javax.swing.JPanel {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;

    public Notifications(String email) {
        initComponents();
        setOpaque(false);
        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        panel.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));
        loadNoti(email);
    }

    private void loadNoti(String email) {
        try {
            String bildirimturu, aciklama, durum = null, kitapadi = null, yayinevi = null, bildirimdurumu, duyurukonu;
            int ustid = 0;
            String sqlkitapadi = "(SELECT kitap_bildirim_kitap_adi FROM public.kitap_bildirim WHERE kitap_bildirim_id = b.bildirim_ustid)";
            String sqlyayinevi = "(SELECT kitap_bildirim_kitap_yayin_evi FROM public.kitap_bildirim WHERE kitap_bildirim_id = b.bildirim_ustid)";
            String sqlbildirimdurumu = "(SELECT kitap_bildirim_durum FROM public.kitap_bildirim WHERE kitap_bildirim_id = b.bildirim_ustid)";
            String sqlduyurubasligi = "(SELECT duyuru_konu FROM public.duyuru WHERE b.bildirim_turu = 'Duyuru' AND duyuru_id = b.bildirim_ustid)";
            String sql = "SELECT b.*," + sqlkitapadi + "," + sqlyayinevi + ","+sqlbildirimdurumu+","+sqlduyurubasligi+" FROM public.bildirim b WHERE b.bildirim_email = ? OR b.bildirim_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, "Herkes");
            rs = pst.executeQuery();
            while (rs.next()) {
                bildirimturu = rs.getString("bildirim_turu");
                ustid = rs.getInt("bildirim_ustid");
                aciklama = rs.getString("bildirim_aciklama");
                kitapadi = rs.getString("kitap_bildirim_kitap_adi");
                yayinevi = rs.getString("kitap_bildirim_kitap_yayin_evi");
                bildirimdurumu = rs.getString("kitap_bildirim_durum");
                duyurukonu = rs.getString("duyuru_konu");
                panel.add(new Item(bildirimdurumu, aciklama, kitapadi, yayinevi,bildirimturu,duyurukonu));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int header = 10;
        AffineTransform tran = new AffineTransform();
        tran.translate(getWidth() - 25, 5);
        tran.rotate(Math.toRadians(45));
        Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0, 0, 20, 20, 5, 5), tran);
        Area area = new Area(p);
        area.add(new Area(new RoundRectangle2D.Double(0, header, getWidth(), getHeight() - header, 10, 10)));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(117, 117, 117));
        jLabel1.setText("Bildirimler");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );

        scroll.setViewportView(panel);

        jButton1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(33, 140, 206));
        jButton1.setText("Show All");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
