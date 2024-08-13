package UserInterface.Form;

import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;
import UserInterface.SecureType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import BusinessLogic.GrabacionesBL;
import DataAccess.DTO.GrabacionesDTO;

public class GrabacionesPanel extends JPanel implements ActionListener {
    private Integer IdGrabaciones = 0, IdMaxGrabaciones = 0;
    private GrabacionesBL GrabacionesBL = new GrabacionesBL();
    private GrabacionesDTO GrabacionesDAO = null;

    public GrabacionesPanel() {
        try {
            customizeComponent();
            loadRow();
            showRow();
            showTable();

            btnRowIni.addActionListener(this);
            btnRowAnt.addActionListener(this);
            btnRowSig.addActionListener(this);
            btnRowFin.addActionListener(this);
            
            btnNuevo.addActionListener(e -> btnNuevoClick());
            btnGuardar.addActionListener(e -> btnGuardarClick());
            btnEliminar.addActionListener(e -> btnEliminarClick());
            btnCancelar.addActionListener(e -> btnCancelarClick());
        } catch (Exception e) {
            SecureType.showMsg(e.getMessage());
        }
    }

   
    private void loadRow() {
        try {
            IdGrabaciones = 1;
            GrabacionesDAO = GrabacionesBL.getBy(IdGrabaciones);
            IdMaxGrabaciones = 10; 
        } catch (Exception e) {
            SecureType.showMsg(e.getMessage());
        }
    }

    private void showRow() {
        boolean grabacionNull = (GrabacionesDAO == null);
        txtRowNum.setText((grabacionNull) ? " " : "ID");
        txtNombre.setText((grabacionNull) ? " " : "Grabacion"); 
        lblTotalReg.setText(IdGrabaciones.toString() + " de " + IdMaxGrabaciones.toString());
    }

    private void btnNuevoClick() {
        GrabacionesDAO = null;
        showRow();
    } 

    private void btnGuardarClick() {
        boolean grabacionNull = (GrabacionesDAO == null);
        try {
            if (SecureType.showConfirmYesNo("¿Seguro que desea " + ((grabacionNull) ? "AGREGAR ?" : "ACTUALIZAR ?"))) {
                if (grabacionNull) 
                    GrabacionesDAO = new GrabacionesDTO(txtNombre.getText().trim());
                 else {
                    GrabacionesDAO.setGrabacion(txtNombre.getText());
                }
                if (!((grabacionNull) ? GrabacionesBL.add(GrabacionesDAO) : GrabacionesBL.update(GrabacionesDAO)))
                     SecureType.showMsgError("Error al guardar...!");

                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            SecureType.showMsgError(e.getMessage());
        }
    }

    private void btnEliminarClick() {
        try {
            if (SecureType.showConfirmYesNo("¿Seguro que desea Eliminar?")) {
                if (!GrabacionesBL.delete(GrabacionesDAO.getIdGrabaciones()))
                     throw new Exception("Error al eliminar...!");
                
                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            SecureType.showMsgError(e.getMessage());
        }
    }

    private void btnCancelarClick() {
        try {
            if (GrabacionesDAO == null)
                loadRow();
                showRow();
        } catch (Exception e) {}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            IdGrabaciones = 1;
        if (e.getSource() == btnRowAnt && (IdGrabaciones > 1))
            IdGrabaciones--;
        if (e.getSource() == btnRowSig && (IdGrabaciones < IdMaxGrabaciones))
            IdGrabaciones++;
        if (e.getSource() == btnRowFin)
            IdGrabaciones = IdMaxGrabaciones;
        try {
            GrabacionesDAO = GrabacionesBL.getBy(IdGrabaciones);
            showRow(); 
        } catch (Exception ex) {}
    }

    private void showTable() throws Exception {
        String[] header = {"ID", "Grabacion"};
        Object[][] data = new Object[GrabacionesBL.getAll().size()][3];
        int index = 0;
        for (GrabacionesDTO g : GrabacionesBL.getAll()) {
        data[index][0] = g.getIdGrabaciones();
        data[index][1] = g.getGrabacion();
        index++;
        }

        JTable table = new JTable(data, header);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.lightGray);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        table.setPreferredScrollableViewportSize(new Dimension(800, 400)); 
        table.setFillsViewportHeight(true);

        pnlTabla.removeAll();
        pnlTabla.add(new JScrollPane(table));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String strIdGrabaciones = table.getModel().getValueAt(row, 0).toString();
                    IdGrabaciones = Integer.parseInt(strIdGrabaciones);
                    try {
                        showRow();
                    } catch (Exception ignored) {
                    }
                    System.out.println("Tabla.Selected: " + strIdGrabaciones);
                }
            }
        });
    }

    /************************
     * FormDesing : pat_mic
     ************************/ 
    private PatLabel 
            lblTitulo = new PatLabel("GRABACIONES"),
            lblRowNum = new PatLabel(" ID:          "),
            lblNombre = new PatLabel("*Nombre:     "),
            lblPassword = new PatLabel("*Detalles:   "),
            lblTotalReg = new PatLabel(" 0 de 0 ");
    private PatTextBox 
            txtRowNum = new PatTextBox(),
            txtNombre = new PatTextBox(),
            txtPassword = new PatTextBox();
    private PatButton 
            btnPageIni = new PatButton(" |< "),
            btnPageAnt = new PatButton(" << "),
            btnPageSig = new PatButton(" >> "),
            btnPageFin = new PatButton(" >| "),

            btnRowIni = new PatButton(" |< "),
            btnRowAnt = new PatButton(" << "),
            btnRowSig = new PatButton(" >> "),
            btnRowFin = new PatButton(" >| "),

            btnNuevo = new PatButton("Nuevo"),
            btnGuardar = new PatButton("Guardar"),
            btnCancelar = new PatButton("Cancelar"),
            btnEliminar = new PatButton("Eliminar");
    private JPanel 
            pnlTabla = new JPanel(),
            pnlBtnRow = new JPanel(new FlowLayout()),
            pnlBtnPage = new JPanel(new FlowLayout()),
            pnlBtnCRUD = new JPanel(new FlowLayout());

    public void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        txtRowNum.setEnabled(false);
        txtRowNum.setBorderLine();
        txtNombre.setBorderLine();
        txtPassword.setBorderLine();

        pnlBtnPage.add(btnPageIni);
        pnlBtnPage.add(btnPageAnt);
        pnlBtnPage.add(new PatLabel(" Page:( "));
        pnlBtnPage.add(lblTotalReg); 
        pnlBtnPage.add(new PatLabel(" ) "));
        pnlBtnPage.add(btnPageSig);
        pnlBtnPage.add(btnPageFin);

        pnlBtnRow.add(btnRowIni);
        pnlBtnRow.add(btnRowAnt);
        pnlBtnRow.add(new PatLabel(" Row:( "));
        pnlBtnRow.add(lblTotalReg);
        pnlBtnRow.add(new PatLabel(" ) "));
        pnlBtnRow.add(btnRowSig);
        pnlBtnRow.add(btnRowFin);

        pnlBtnCRUD.add(btnNuevo);
        pnlBtnCRUD.add(btnGuardar);
        pnlBtnCRUD.add(btnEliminar);
        pnlBtnCRUD.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(5, 5, 5, 5);
        add(lblTitulo, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        add(lblRowNum, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtRowNum, gbc);
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        add(lblNombre, gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtNombre, gbc);

        gbc.gridy = 2; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE;
        add(lblPassword, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtPassword, gbc);

        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlTabla, gbc);

        gbc.gridy = 4; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlBtnCRUD, gbc);

        gbc.gridy = 5; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlBtnRow, gbc);
    }
}
