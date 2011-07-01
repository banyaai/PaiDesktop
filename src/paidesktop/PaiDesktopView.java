/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PaiDesktopView.java
 *
 * Created on Jun 27, 2011, 10:14:41 AM
 */
package paidesktop;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author banyaai
 */
public class PaiDesktopView extends javax.swing.JFrame {

    ArrayList<Candidate> candidatesList = new ArrayList<Candidate>();
    GetParameters getParameters;
    String filename = "/home/banyaai/paidesktop.config";


    /** Creates new form PaiDesktopView */
    public PaiDesktopView(){
        getParameters = new GetParameters();
        try {
            getParameters.loadParameters(filename);
        } catch (Exception ex) {
            Logger.getLogger(PaiDesktopView.class.getName()).log(Level.SEVERE, null, ex);
        }
        getParameters.startThread();
        candidatesList = getParameters.getCandidatesList();
        candidatesButtons = new JRadioButton[candidatesList.size()];
        for (int i = 0; i < candidatesList.size(); i++) {
            candidatesButtons[i] = new JRadioButton(candidatesList.get(i).getName());
//            jRadioButton1[i].addActionListener(actionListener);
        }
        initComponents();
        VotePanel.setVisible(true);


        javax.swing.GroupLayout VoteButtonCheckPanelLayout = new javax.swing.GroupLayout(VoteButtonCheckPanel);
        ParallelGroup parallelGroup1 = VoteButtonCheckPanelLayout.createParallelGroup();
        SequentialGroup sequentialGroup = VoteButtonCheckPanelLayout.createSequentialGroup();
        sequentialGroup.addGap(32, 32, 32);
        for (int i = 0; i < candidatesList.size(); i++) {
            candidatesButtons[i] = new JRadioButton(candidatesList.get(i).getName());
            parallelGroup1.addComponent(candidatesButtons[i]);
            sequentialGroup.addComponent(candidatesButtons[i]);
            sequentialGroup.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
            candidatesButtonGroup.add(candidatesButtons[i]);
        }

        sequentialGroup.addContainerGap(/*przesuwanie w zaleznosci od ilosci kandydatow*/445 - 26 * candidatesList.size(), Short.MAX_VALUE);

        VoteButtonCheckPanel.setLayout(VoteButtonCheckPanelLayout);
        VoteButtonCheckPanelLayout.setHorizontalGroup(
                VoteButtonCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                addGroup(VoteButtonCheckPanelLayout.createSequentialGroup().addContainerGap().addGroup(parallelGroup1).addContainerGap(175, Short.MAX_VALUE)));

        VoteButtonCheckPanelLayout.setVerticalGroup(
                VoteButtonCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(sequentialGroup));

        javax.swing.GroupLayout VotePanelLayout = new javax.swing.GroupLayout(VotePanel);
        VotePanel.setLayout(VotePanelLayout);
        VotePanelLayout.setHorizontalGroup(
                VotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(VotePanelLayout.createSequentialGroup().addComponent(VoteButtonCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(1025, Short.MAX_VALUE)));
        VotePanelLayout.setVerticalGroup(
                VotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(VoteButtonCheckPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VotePanel = new javax.swing.JPanel();
        VoteButtonCheckPanel = new javax.swing.JPanel();
        CandidatesLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        OKButton2 = new javax.swing.JButton();
        CancelButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("paidesktop/properties/PaiDesktopView"); // NOI18N
        CandidatesLabel.setText(bundle.getString("CandidatesLabel.Text")); // NOI18N

        javax.swing.GroupLayout VoteButtonCheckPanelLayout = new javax.swing.GroupLayout(VoteButtonCheckPanel);
        VoteButtonCheckPanel.setLayout(VoteButtonCheckPanelLayout);
        VoteButtonCheckPanelLayout.setHorizontalGroup(
            VoteButtonCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VoteButtonCheckPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CandidatesLabel)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        VoteButtonCheckPanelLayout.setVerticalGroup(
            VoteButtonCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VoteButtonCheckPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CandidatesLabel)
                .addContainerGap(428, Short.MAX_VALUE))
        );

        OKButton2.setText("OKButton");
        OKButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButton2ActionPerformed(evt);
            }
        });

        CancelButton2.setText("CancelButton");
        CancelButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(OKButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CancelButton2)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(383, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OKButton2)
                    .addComponent(CancelButton2))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout VotePanelLayout = new javax.swing.GroupLayout(VotePanel);
        VotePanel.setLayout(VotePanelLayout);
        VotePanelLayout.setHorizontalGroup(
            VotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VotePanelLayout.createSequentialGroup()
                .addComponent(VoteButtonCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(440, Short.MAX_VALUE))
        );
        VotePanelLayout.setVerticalGroup(
            VotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VoteButtonCheckPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(VotePanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VotePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VotePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButton2ActionPerformed
        // TODO add your handling code here:
        endVote(true);
    }//GEN-LAST:event_OKButton2ActionPerformed

    private void CancelButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButton2ActionPerformed
        // TODO add your handling code here:
        endVote(false);
    }//GEN-LAST:event_CancelButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PaiDesktopView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton2;
    private javax.swing.JLabel CandidatesLabel;
    private javax.swing.JButton OKButton2;
    private javax.swing.JPanel VoteButtonCheckPanel;
    private javax.swing.JPanel VotePanel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JRadioButton candidatesButtons[];
    ButtonGroup candidatesButtonGroup = new ButtonGroup();

    void endVote(boolean sendVote) {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("paidesktop.properties/PaiDesktopView");

        if (candidatesButtonGroup.getSelection() != null) {
            JOptionPane.showMessageDialog(null, bundle.getString("ThankYou.Text"));

            JRadioButton selectedButton = null;
            for (JRadioButton button : candidatesButtons) {
                if (button.isSelected()) {
                    selectedButton = button;
                    break;
                }
            }

            if (selectedButton != null) {
                for (Candidate c : candidatesList) {
                    if (c.getName().compareTo(selectedButton.getText()) == 0) {
                        getParameters.sendResults(c);
                        break;
                    }
                }
            }
            candidatesButtonGroup.clearSelection();

        } else {
            int choice = JOptionPane.showConfirmDialog(null, bundle.getString("AreYouSure.Text"));
            if (choice == 0) {
                JOptionPane.showMessageDialog(null, bundle.getString("ThankYou.Text"));
            }
        }
    }

    boolean checkText(String text) {

        //It can't contain only numbers if it's null or empty...
        if (text == null || text.length() == 0) {
            return false;
        }

        for (int i = 0; i < text.length(); i++) {

            //If we find a non-digit character we return false.

            if (!Character.isLetterOrDigit(text.charAt(i))) {
                return false;
            }

        }

        return true;
    }
}
