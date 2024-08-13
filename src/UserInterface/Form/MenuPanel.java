package UserInterface.Form;

import UserInterface.CustomerControl.PatButton;
import UserInterface.SecureType;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel {
    public PatButton   
            btnHome     = new PatButton("Home"),
            btnCamara  = new PatButton("Cámara"),
            btnGrabaciones = new PatButton("Ver Grabaciones"),
            btnUsuarios = new PatButton("Usuarios");

    public MenuPanel() {
        customizeComponent();
    }

    private void customizeComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, getHeight())); 

        try {
            Image logo = ImageIO.read(SecureType.URL_LOGO);
            logo = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(logo)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addButton(btnHome);
        addButton(btnCamara);
        addButton(btnGrabaciones);
        addButton(btnUsuarios);

        JLabel copyright = new JLabel("\u00A9 2024 SecureVision");
        copyright.setFont(SecureType.FONT_SMALL); 
        copyright.setForeground(SecureType.COLOR_FONT); 
        add(Box.createRigidArea(new Dimension(0, 10))); 
        add(copyright);
    }

    private void addButton(PatButton button) {
        button.setFont(SecureType.FONT); 
        button.setForeground(SecureType.COLOR_FONT); 
        button.setBackground(SecureType.COLOR_FONT_LIGHT); 
        button.setBorder(new EmptyBorder(5, 15, 5, 15));
        button.setCursor(SecureType.CURSOR_HAND); 
        
        button.setPreferredSize(new Dimension(200, 40)); 

        add(button);
        add(Box.createRigidArea(new Dimension(0, 10))); 
    }
}
