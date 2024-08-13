package UserInterface.Form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import BusinessLogic.RegistroBL;
import DataAccess.DTO.RegistroDTO;
import UserInterface.SecureType;

import java.util.List;

public class LoginPanel {

    private RegistroBL registroBL = new RegistroBL();

    public void login() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); 
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        frame.add(panel);

        JPanel loginPanel = createLoginPanel(frame, panel);
        JPanel registerPanel = createRegisterPanel(frame, panel);

        panel.add(loginPanel, "Login");
        panel.add(registerPanel, "Register");

        CardLayout cl = (CardLayout) (panel.getLayout());
        cl.show(panel, "Login");

        frame.setVisible(true);
    }

    private JPanel createLoginPanel(JFrame frame, JPanel panel) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER; 

        ImageIcon imageIcon = new ImageIcon(SecureType.URL_LOGO);
        JLabel imageLabel = new JLabel(imageIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(imageLabel, gbc);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(SecureType.FONT); 
        userLabel.setForeground(SecureType.COLOR_FONT); 
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE; 
        loginPanel.add(userLabel, gbc);

        JTextField userText = new JTextField(25);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        loginPanel.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(SecureType.FONT);
        passwordLabel.setForeground(SecureType.COLOR_FONT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(25); 
        gbc.gridx = 1;
        loginPanel.add(passwordText, gbc);

        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(SecureType.FONT_BOLD);
        loginButton.setCursor(SecureType.CURSOR_HAND);
        loginButton.setBackground(SecureType.COLOR_FONT_LIGHT); 
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        loginPanel.add(loginButton, gbc);

        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(SecureType.FONT_BOLD);
        registerButton.setCursor(SecureType.CURSOR_HAND);
        registerButton.setBackground(SecureType.COLOR_FONT_LIGHT);
        registerButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        loginPanel.add(registerButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = userText.getText().toUpperCase();  // Convertir a mayúsculas
                    String password = new String(passwordText.getPassword());

                    List<RegistroDTO> registros = registroBL.getAll();
                    boolean loginSuccess = false;

                    for (RegistroDTO registro : registros) {
                        if (registro.getUsername().equals(username) && registro.getPassword().equals(password)) {
                            loginSuccess = true;
                            break;
                        }
                    }

                    if (loginSuccess) {
                        frame.dispose(); // Cerrar el frame de login
                        showMainFrame(); // Mostrar el panel principal
                    } else {
                        JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error en el login: " + ex.getMessage());
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panel.getLayout());
                cl.show(panel, "Register");
            }
        });

        return loginPanel;
    }

    private JPanel createRegisterPanel(JFrame frame, JPanel panel) {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(SecureType.FONT);
        userLabel.setForeground(SecureType.COLOR_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(userLabel, gbc);

        JTextField userText = new JTextField(25);
        gbc.gridx = 1;
        registerPanel.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(SecureType.FONT);
        passwordLabel.setForeground(SecureType.COLOR_FONT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(25);
        gbc.gridx = 1;
        registerPanel.add(passwordText, gbc);

        JButton registerButton = new JButton("Registrar");
        registerButton.setFont(SecureType.FONT_BOLD);
        registerButton.setCursor(SecureType.CURSOR_HAND);
        registerButton.setBackground(SecureType.COLOR_FONT_LIGHT);
        registerButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        registerPanel.add(registerButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = userText.getText().toUpperCase();  
                    String password = new String(passwordText.getPassword()); 

                    RegistroDTO registroDTO = new RegistroDTO(0, username, password, "A", java.time.LocalDateTime.now().toString(), null);
                    boolean success = registroBL.add(registroDTO);

                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Registro exitoso");
                        CardLayout cl = (CardLayout) (panel.getLayout());
                        cl.show(panel, "Login");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error al registrar");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error en el registro: " + ex.getMessage());
                }
            }
        });

        return registerPanel;
    }

    private void showMainFrame() {
        SwingUtilities.invokeLater(() -> {
            new MainForm("Sistema de Seguridad con Cámaras IP");
        });
    }
}