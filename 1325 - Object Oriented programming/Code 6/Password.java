package code6_1001821751;

/**
 *
 * @author Andy Lazaro 1001821751
 */
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Andy Lazaro 1001821751
 */
public class Password extends JFrame
{
    private final JPasswordField passwordField;

    String Upass;
    final String Fpass = "pog";
    public Password()
    {
        super("Enter a password to continue");
        setLayout(new FlowLayout());
        passwordField = new JPasswordField("Hidden Text");
        passwordField.setEchoChar('X');
        passwordField.selectAll();
        add(passwordField);
        
        EventHandler handle = new EventHandler();
        passwordField.addActionListener(handle);
    }
    private class EventHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String string = "";
            if (event.getSource() == passwordField)
                Upass = event.getActionCommand();
            if(Upass.equals(Fpass) == true)
            {
                setVisible(false);
                GameFrame hi = new GameFrame();
                hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                hi.setSize(500, 200);
                hi.setVisible(true);
            }
            else if(Upass.equals(Fpass) == false)
                JOptionPane.showMessageDialog(null, "invalid password entered");
        }
    }
}
