package UserInterface.CustomerControl;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import UserInterface.SecureType;

public class PatTextBox extends JTextField {

    public PatTextBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        setBorderRect();
        setFont(SecureType.FONT);  
        setForeground(SecureType.COLOR_FONT_LIGHT);  
        setCaretColor(SecureType.COLOR_CURSOR);  
        setMargin(new Insets(5, 5, 5, 5));      
        setOpaque(false);                      
    }

    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(SecureType.COLOR_BORDER);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5); 
        setBorder( new CompoundBorder(lineBorder, emptyBorder));
    }

    public void setBorderLine(){
        int thickness = 1;
        setBorder(  BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 0, thickness, 0),
                    BorderFactory.createMatteBorder(0, 0, thickness, 0, SecureType.COLOR_BORDER) 
        ));
    }
}
