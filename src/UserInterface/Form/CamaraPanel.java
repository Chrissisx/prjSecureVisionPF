package UserInterface.Form;

import UserInterface.CustomerControl.PatButton;
import UserInterface.SecureType;
import CamaraIP.CamaraIP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CamaraPanel extends JPanel {
    private PatButton btnIniciarGrabacion;
    private PatButton btnDetenerGrabacion;
    private PatButton btnSalir;

    private JPanel videoPanel;
    private JPanel buttonPanel;
    private CamaraIP ipCamera;
    private String URLCamara = "rtsp://admin:camera.01P@192.168.100.139:554/cam/realmonitor?channel=1&subtype=0";

    public CamaraPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 800)); 
        setBorder(SecureType.createBorderRect());
        setBackground(Color.WHITE); 

        

        videoPanel = new JPanel();
        videoPanel.setPreferredSize(new Dimension(600, 500)); 
        videoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        videoPanel.setBackground(Color.BLACK);

    
        ipCamera = new CamaraIP(URLCamara);
        videoPanel.setLayout(new BorderLayout());
        videoPanel.add(ipCamera.getVideoComponent(), BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        btnIniciarGrabacion = createStyledButton("Iniciar Grabación", new Color(0, 150, 0), Color.BLACK);
        btnDetenerGrabacion = createStyledButton("Detener Grabación", new Color(255, 69, 58), Color.BLACK);
        btnSalir = createStyledButton("Salir", new Color(105, 105, 105), Color.WHITE); 

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(btnIniciarGrabacion);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(btnDetenerGrabacion);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(btnSalir);
        buttonPanel.add(Box.createHorizontalGlue());

        add(videoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnIniciarGrabacion.addActionListener(e -> ipCamera.startRecording());
        btnDetenerGrabacion.addActionListener(e -> ipCamera.stopRecording());
        btnSalir.addActionListener(e -> exitApplication());

        startStreaming();
    }

    private PatButton createStyledButton(String text, Color backgroundColor, Color foregroundColor) {
        PatButton button = new PatButton(text);
        button.setForeground(foregroundColor);
        button.setFont(SecureType.FONT_BOLD);
        button.setCursor(SecureType.CURSOR_HAND);
        button.setBackground(backgroundColor); 
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(foregroundColor.darker()),
            BorderFactory.createEmptyBorder(10, 20, 10, 20))); 
        button.setPreferredSize(new Dimension(200, 40)); 
        return button;
    }

    private void startStreaming() {
        SwingUtilities.invokeLater(() -> ipCamera.startStreaming());
    }

    private void exitApplication() {
        ipCamera.releaseResources();
        System.exit(0);
    }
}
