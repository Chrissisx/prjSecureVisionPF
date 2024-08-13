
package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PatButton extends JButton {

    public PatButton(String text) {
        super(text);
        setBorderPainted(false); 
        setFocusPainted(false);  
        setContentAreaFilled(false);
        setOpaque(false); 
        setHorizontalAlignment(SwingConstants.LEFT); 
        setFont(new Font("Arial", Font.PLAIN, 16)); 
        setForeground(Color.BLACK); 
        setBorder(new EmptyBorder(10, 10, 10, 10)); 
    }
}

