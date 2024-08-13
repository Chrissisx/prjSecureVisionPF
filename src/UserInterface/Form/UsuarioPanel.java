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
import BusinessLogic.RegistroBL;
import DataAccess.DTO.RegistroDTO;

public class UsuarioPanel extends JPanel implements ActionListener {
    private Integer rowNum = 0, IdMaxRegistro = 0;
    private RegistroBL RegistroBL = new RegistroBL();
    private RegistroDTO RegistroDAO = null;

    public UsuarioPanel() {
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
            rowNum = 1;
            RegistroDAO = RegistroBL.getBy(rowNum);
            IdMaxRegistro = 10; 
        } catch (Exception e) {
            SecureType.showMsg(e.getMessage());
        }
    }

    private void showRow() {
        boolean usuarioNull = (RegistroDAO == null);
        txtRowNum.setText((usuarioNull) ? " " : "ID"); 
        txtNombre.setText((usuarioNull) ? " " : "Username"); 
        txtPassword.setText((usuarioNull) ? " " : "Contraseña"); 
        lblTotalReg.setText(rowNum.toString() + " de " + IdMaxRegistro.toString());
    }

    private void btnNuevoClick() {
        RegistroDAO = null;
        showRow();
    } 

    private void btnGuardarClick() {
        boolean usuarioNull = (RegistroDAO == null);
        try {
            if (SecureType.showConfirmYesNo("¿Seguro que desea " + ((usuarioNull) ? "AGREGAR ?" : "ACTUALIZAR ?"))) {
                if (usuarioNull) {
                    RegistroDAO = new RegistroDTO(txtNombre.getText().trim(), txtPassword.getText().trim());
                } else {
                    RegistroDAO.setUsername(txtNombre.getText());
                    RegistroDAO.setPassword(txtPassword.getText());
                }
                if (!((usuarioNull) ? RegistroBL.add(RegistroDAO) : RegistroBL.update(RegistroDAO)))
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
                if (!RegistroBL.delete(RegistroDAO.getIdRegistro()))
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
            if (RegistroDAO == null)
                loadRow();
            showRow();
        } catch (Exception e) {}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            rowNum = 1;
        if (e.getSource() == btnRowAnt && (rowNum > 1))
            rowNum--;
        if (e.getSource() == btnRowSig && (rowNum < IdMaxRegistro))
            rowNum++;
        if (e.getSource() == btnRowFin)
            rowNum = IdMaxRegistro;
        try {
            RegistroDAO = RegistroBL.getBy(rowNum);
            showRow(); 
        } catch (Exception ex) {}
    }

    private void showTable() throws Exception {
        String[] header = {"ID", "Username", "Contraseña"};
        Object[][] data = new Object[RegistroBL.getAll().size()][3];
        int index = 0;
        for (RegistroDTO u : RegistroBL.getAll()) {
            data[index][0] = u.getIdRegistro();
            data[index][1] = u.getUsername();
            data[index][2] = u.getPassword();
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
                    String strRowNum = table.getModel().getValueAt(row, 0).toString();
                    rowNum = Integer.parseInt(strRowNum);
                    try {
                        RegistroDAO = RegistroBL.getBy(rowNum);
                        showRow();
                    } catch (Exception ignored) {}
                    System.out.println("Tabla.Selected: " + strRowNum);
                }
            }
        });
    }    
    

    /***************
     * FormDesing 
     ***************/
    private PatLabel 
            lblTitulo = new PatLabel("USUARIOS"),
            lblRowNum = new PatLabel(" ID:          "),
            lblNombre = new PatLabel("*Nombre:     "),
            lblPassword = new PatLabel("*Contraseña: "),
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
        pnlBtnRow.add(lblTotalReg);
        pnlBtnRow.add(btnRowSig);
        pnlBtnRow.add(btnRowFin);

        pnlBtnCRUD.add(btnNuevo);
        pnlBtnCRUD.add(btnGuardar);
        pnlBtnCRUD.add(btnCancelar);
        pnlBtnCRUD.add(btnEliminar);
        pnlBtnCRUD.setBorder(SecureType.createBorderRect());

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("■ Sección de datos: "), gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(pnlBtnPage, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.ipady = 150;
        gbc.ipadx = 450;
        pnlTabla.add(new Label("Loading data..."));
        add(pnlTabla, gbc);

        gbc.ipady = 1;
        gbc.ipadx = 1;

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(50, 0, 0, 0); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(Box.createRigidArea(new Dimension(0, 0)), gbc);

        gbc.insets = new Insets(10, 0, 0, 0);  

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("■ Sección de registro: "), gbc);
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(pnlBtnRow, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        add(lblRowNum, gbc);
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        add(txtRowNum, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        add(lblNombre, gbc);
        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        add(txtNombre, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        add(lblPassword, gbc);
        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(txtPassword, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlBtnCRUD, gbc);
    }
}
