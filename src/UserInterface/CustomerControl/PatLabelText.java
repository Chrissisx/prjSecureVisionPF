package UserInterface.CustomerControl;

import javax.swing.*;

import UserInterface.SecureType;

import java.awt.*;

public class PatLabelText extends JPanel{
    private PatLabel    lblEtiqueta = new PatLabel();
    private PatTextBox  txtContenido= new PatTextBox();

    public PatLabelText(String etiqueta) {
        setLayout(new BorderLayout());

        lblEtiqueta.setCustomizeComponent(  etiqueta, 
                                            SecureType.FONT_SMALL, 
                                            SecureType.COLOR_FONT_LIGHT, 
                                            SecureType.ALIGNMENT_LEFT); 
        txtContenido.setBorderLine();
        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
    }
}
