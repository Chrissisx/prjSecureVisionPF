package UserInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
public abstract class SecureType {
    public static final Color COLOR_FONT = new Color(70, 130, 180); 
public static final Color COLOR_FONT_LIGHT = new Color(135, 206, 250);

    public static final Color COLOR_CURSOR = Color.black;
    public static final Color COLOR_BORDER = Color.lightGray;
    public static final Font  FONT         = new Font("Bahnschrift", Font.PLAIN, 14);          
    public static final Font  FONT_BOLD    = new Font("Bahnschrift", Font.BOLD | Font.PLAIN, 14);
    public static final Font  FONT_SMALL   = new Font("Bahnschrift", Font.PLAIN| Font.PLAIN, 10);

    public static final int ALIGNMENT_LEFT  = SwingConstants.LEFT;
    public static final int ALIGNMENT_RIGHT = SwingConstants.RIGHT;
    public static final int ALIGNMENT_CENTER= SwingConstants.CENTER;

    public static final Cursor CURSOR_HAND    = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    public static final URL URL_LOGO  = SecureType.class.getResource("/UserInterface/Resource/Logo.jpeg");
    public static final URL URL_SPLASH= SecureType.class.getResource("/UserInterface/Resource/Splash.png");

    public static final CompoundBorder createBorderRect(){
        return BorderFactory.createCompoundBorder(  new LineBorder(Color.lightGray),
                                                    new EmptyBorder(5, 5, 5, 5));
    }

    public static final void showMsg(String msg){
        JOptionPane.showMessageDialog(null, msg, "😏 SecureVision", JOptionPane.INFORMATION_MESSAGE);
    }
    public static final void showMsgError(String msg){
        JOptionPane.showMessageDialog(null, msg, "💀 SecureVision", JOptionPane.OK_OPTION);
    }
    public static final boolean showConfirmYesNo(String msg){
        return (JOptionPane.showConfirmDialog(null, msg, "😞 SecureVision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
