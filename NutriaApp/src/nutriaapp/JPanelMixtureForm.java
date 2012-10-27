package NutriaApp;

import NutriaDao.MixtureDaoImpl;
import NutriaModel.Mixture;
import NutriaModel.QuantifiedIngredient;
import NutriaTableModel.QuantifiedIngredientTableModel;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Ariel
 */
public class JPanelMixtureForm extends javax.swing.JPanel {

    /**
     * Creates new form JPanelMixtureForm
     */
    
    private JDialog container;
    private Boolean success = false;
    private Mixture mixture;
    private MixtureDaoImpl mixtureDao;
    private QuantifiedIngredientTableModel qIngredientTableModel;
    
    public JPanelMixtureForm(JDialog container) {
        this.container = container;
        initComponents();
        initCustomComponents();
    }
    
    public JPanelMixtureForm(JDialog container, Mixture mixture) {
        this.container = container;
        this.mixture = mixture;
        initComponents();
        initCustomComponents();
    }
    
    public JPanelMixtureForm(Mixture mixture) {
        this.mixture = mixture;
        try{
            mixtureDao = new MixtureDaoImpl();
        } catch(SQLException ex) {
            //TODO: handle when cannot create mixtureDao
            ex.printStackTrace();
        }
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    private void initCustomComponents() {
        try{
            jComboBoxIngredientsType.addItem("Ingredientes Húmedos");
            jComboBoxIngredientsType.addItem("Ingredientes Secos");
            jComboBoxIngredientsType.setSelectedIndex(0);
            qIngredientTableModel = new QuantifiedIngredientTableModel();
            qIngredientTableModel.setColumnEditable(4, true);
            jTableIngredients.setModel(qIngredientTableModel);
            jTableIngredients.removeColumn(jTableIngredients.getColumn("id"));
            mixtureDao = new MixtureDaoImpl();
            if(mixture != null) {
                mixtureDao.populateMixture(mixture);
                mappMixtureToForm();
            } else {
                mixture = new Mixture();
            }
        } catch(SQLException ex) {
            //TODO: handle init exception
            ex.printStackTrace();
        }
    }
    
    private void mappMixtureToForm() {
        jTextName.setText(mixture.getName());
        jTextAreaDescription.setText(mixture.getDescription());
        qIngredientTableModel.setQuantifiedIngredientList(mixture.getIngredientList());
        qIngredientTableModel.fireTableDataChanged();
        if(mixture.getWetIngredients())
            jComboBoxIngredientsType.setSelectedIndex(0);
        else
            jComboBoxIngredientsType.setSelectedIndex(1);
        
    }
    
    private void mappFormToMixture() {
        mixture.setName(jTextName.getText());
        mixture.setDescription(jTextAreaDescription.getText());
        mixture.setIngredientList(qIngredientTableModel.getQuantifiedIngredientList());
        if(jComboBoxIngredientsType.getSelectedIndex() == 0)
            mixture.setWetIngredients(Boolean.TRUE);
        else
            mixture.setWetIngredients(Boolean.FALSE);
    }
    
    private void processSave() {
        mappFormToMixture();
        MixtureHelper.processMixture(mixture);
        try {
            mixtureDao.createOrUpdate(mixture);
            success = true;
        } catch(SQLException ex) {
            //TODO: hangle save exception
        }
    }
    
    public void deleteMixture() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que desea eliminar la Mezcla: " + mixture.getName() + "?",
            "Eliminar Mezcla",
            JOptionPane.YES_NO_OPTION);
        
        if (JOptionPane.YES_OPTION == confirm) {
            try {
                int result = mixtureDao.deleteById(mixture.getId());
                if(result > 0) {
                    success = true;
                } else {
                    //TODO: validate if no success delete
                    System.out.println("Mixture could not be deleted");
                }
            } catch(SQLException ex) {
                //TODO: handle Delete nutrient exception
                ex.printStackTrace();
            }
        } else {
            //TODO: validate in case NO opntion selected in delete Nutrient
            System.out.println("User cancel delettion of Mixture");
        }
    }
    
    private void openFindIngredientForm() {
        JDialogFindIngredient findIngredientForm = new JDialogFindIngredient(container, "Buscar Ingrediente", true);
        findIngredientForm.setVisible(true);
        if (findIngredientForm.getSelectedIngredient() != null) {
            System.out.println("Successfully got Ingredient");
            qIngredientTableModel.getQuantifiedIngredientList().add(
                    new QuantifiedIngredient(findIngredientForm.getSelectedIngredient(), 0D)
                );
            qIngredientTableModel.fireTableDataChanged();
        }
    }
    
    private void removeIngredient() {
        int rowIndex = jTableIngredients.getSelectedRow();
        qIngredientTableModel.getQuantifiedIngredientList().remove(rowIndex);
        qIngredientTableModel.fireTableDataChanged();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelName = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jLabelDescription = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableIngredients = new javax.swing.JTable();
        jComboBoxIngredientsType = new javax.swing.JComboBox();
        jLabelIngredientsType = new javax.swing.JLabel();
        jButtonAddIngredient = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonRemoveIngredient = new javax.swing.JButton();

        jLabelName.setText("Nombre:");

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setRows(2);
        jScrollPane1.setViewportView(jTextAreaDescription);

        jLabelDescription.setText("Descripción:");

        jTableIngredients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableIngredients);

        jLabelIngredientsType.setText("Tipo de Entrada de Ingredientes:");

        jButtonAddIngredient.setText("Agregar Ingrediente");
        jButtonAddIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddIngredientActionPerformed(evt);
            }
        });

        jButtonSave.setText("Guardar");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancelar");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");

        jButtonRemoveIngredient.setText("Quitar Ingrediente");
        jButtonRemoveIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveIngredientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addGap(50, 50, 50)
                .addComponent(jButtonCancel)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(91, 91, 91))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelDescription)
                            .addComponent(jLabelName)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelIngredientsType)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(jComboBoxIngredientsType, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextName)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonAddIngredient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonRemoveIngredient)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDescription)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxIngredientsType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIngredientsType))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAddIngredient)
                            .addComponent(jButtonRemoveIngredient))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSave)
                            .addComponent(jButtonCancel)))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        container.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        processSave();
        if(success) {
            container.setVisible(false);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonAddIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddIngredientActionPerformed
        openFindIngredientForm();
    }//GEN-LAST:event_jButtonAddIngredientActionPerformed

    private void jButtonRemoveIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveIngredientActionPerformed
        removeIngredient();
    }//GEN-LAST:event_jButtonRemoveIngredientActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddIngredient;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonRemoveIngredient;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxIngredientsType;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelIngredientsType;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableIngredients;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextName;
    // End of variables declaration//GEN-END:variables
}
