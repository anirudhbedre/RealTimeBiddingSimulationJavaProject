/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.PublisherRole;

import Business.AdExchange.AuctionEvent;
import Business.AdExchange.AuctionEventDirectory;
import Business.Ecosystem;
import Business.Organization.PublisherOrganization;
import Business.Publisher.Tag;
import Business.Publisher.UserPotential;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
/**
 *
 * @author anirudhbedre
 */
public class ViewUserHabitsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewUserHabitsJPanel
     */
    private JPanel userProcessContainer;
    
    private PublisherOrganization organization;
    public ViewUserHabitsJPanel(JPanel upc, PublisherOrganization organization) {
        initComponents();
        this.userProcessContainer=upc;
        
        this.organization=organization;
        populateUserAccountsTable();
    }
public void populateUserAccountsTable(){
     int rowCount = userAccountsJTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel)userAccountsJTable.getModel();
        model.setRowCount(0);
      
        
        
        for(UserPotential userPotential:organization.getUserPotentialDirectory().getUserPotentialList()) {
          
            Object row[] = new Object[1];
            row[0] = userPotential;
            model.addRow(row);
            }
    
}
public void populateUserHabitsTable(UserPotential userPotential){
     int rowCount = userHabitsJTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel)userHabitsJTable.getModel();
        model.setRowCount(0);
      
        
        
        for(Tag tag:userPotential.getTagList().getTagList()){
          
            Object row[] = new Object[2];
            row[0] = tag.getTagName();
            row[1] = tag.getNumberOfVisits();
            model.addRow(row);
            }
    
}
 private void populateChart(PieDataset pieDataset)
    {
        
    JFreeChart salesPieChart = createChart(pieDataset);
        ChartPanel userHabitsPanel = new ChartPanel(salesPieChart);
        UserHabitsJPanel.setLayout(new java.awt.BorderLayout());
        UserHabitsJPanel.add(userHabitsPanel,BorderLayout.CENTER);    
  
    }
 
  private  PieDataset createDataset(UserPotential userPotential) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
         for(Tag tag:userPotential.getTagList().getTagList()){
             dataset.setValue(tag.getTagName(),tag.getNumberOfVisits());
         }
        
        

        return dataset;
    }
  
  
  private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
            "User Habits",  // chart title
            dataset,            // data
            false,              // no legend
            true,               // tooltips
            false               // no URL generation
        );

        // set a custom background for the chart
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), 
                new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

        // customise the title position and font
        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);

        // use gradients and white borders for the section colours
        plot.setSectionPaint("Others", createGradientPaint(new Color(200, 200, 255), Color.BLUE));
        plot.setSectionPaint("Samsung", createGradientPaint(new Color(255, 200, 200), Color.RED));
        plot.setSectionPaint("Apple", createGradientPaint(new Color(200, 255, 200), Color.GREEN));
        plot.setSectionPaint("Nokia", createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
        plot.setBaseSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));

        // customise the section label appearance
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);
        
        // add a subtitle giving the data source
        TextTitle source = new TextTitle(" ", 
                new Font("Courier New", Font.PLAIN, 12));
        source.setPaint(Color.WHITE);
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        return chart;

    }

    /**
     * A utility method for creating gradient paints.
     * 
     * @param c1  color 1.
     * @param c2  color 2.
     * 
     * @return A radial gradient paint.
     */
    private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
        Point2D center = new Point2D.Float(0, 0);
        float radius = 200;
        float[] dist = {0.0f, 1.0f};
        return new RadialGradientPaint(center, radius, dist,
                new Color[] {c1, c2});
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backjButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userHabitsJTable = new javax.swing.JTable();
        viewUserHabitsJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userAccountsJTable = new javax.swing.JTable();
        UserHabitsJPanel = new javax.swing.JPanel();

        backjButton2.setText("<<Back");
        backjButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButton2ActionPerformed(evt);
            }
        });

        userHabitsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tags of Websites visited", "Number of Visits"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userHabitsJTable);

        viewUserHabitsJButton.setText("View User Habits");
        viewUserHabitsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewUserHabitsJButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("User Habits");

        userAccountsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "User Accounts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(userAccountsJTable);

        javax.swing.GroupLayout UserHabitsJPanelLayout = new javax.swing.GroupLayout(UserHabitsJPanel);
        UserHabitsJPanel.setLayout(UserHabitsJPanelLayout);
        UserHabitsJPanelLayout.setHorizontalGroup(
            UserHabitsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        UserHabitsJPanelLayout.setVerticalGroup(
            UserHabitsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backjButton2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(378, 378, 378)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(UserHabitsJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel1))
                    .addComponent(viewUserHabitsJButton, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                .addGap(305, 305, 305))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(viewUserHabitsJButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(UserHabitsJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(backjButton2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backjButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButton2ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backjButton2ActionPerformed

    private void viewUserHabitsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewUserHabitsJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow=userAccountsJTable.getSelectedRow();
        if(selectedRow<0) {
            JOptionPane.showMessageDialog(null, "Please select a row to view!!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        UserPotential userPotential=(UserPotential)userAccountsJTable.getValueAt(selectedRow,0);
        populateUserHabitsTable(userPotential);
        PieDataset pieDataset=createDataset(userPotential);
         populateChart(pieDataset);
    }//GEN-LAST:event_viewUserHabitsJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel UserHabitsJPanel;
    private javax.swing.JButton backjButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable userAccountsJTable;
    private javax.swing.JTable userHabitsJTable;
    private javax.swing.JButton viewUserHabitsJButton;
    // End of variables declaration//GEN-END:variables
}
