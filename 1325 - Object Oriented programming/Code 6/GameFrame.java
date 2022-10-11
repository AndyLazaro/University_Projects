package code6_1001821751;

/**
 *
 * @author Andy Lazaro 1001821751
 */
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
public class GameFrame extends JFrame
{
    private final JLabel label1;
    private final JButton OKButton;
    private final JButton CancelButton;
    private final JTextField textField1;
    String answer;
    public GameFrame()
    {
        super("Welcome to my Guessing Game");
        setLayout(new FlowLayout());
        GameFrame.super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Random rnd = new Random();
        switch(rnd.nextInt(7))
        {
            case 1:
                answer = "isaac";
                break;
            case 2:
                answer = "eve";
                break;
            case 3:
                answer = "azazel";
                break;
            case 4:
                answer = "bethany";
                break;
            case 5:
                answer = "eden";
                break;
            case 6:
                answer = "keeper";
                break;
        }
        Icon CC = new ImageIcon(getClass().getResource(answer + ".png"));
        label1 = new JLabel();
        label1.setText("What is this Character's Name?");
        label1.setIcon(CC);
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        label1.setVerticalTextPosition(SwingConstants.BOTTOM);
        label1.setToolTipText("Binding of Isaac");
        add(label1);
        EventHandler handle = new EventHandler();
        
        textField1 = new JTextField("Enter text here");
        textField1.selectAll();
        textField1.addActionListener(handle);
        add(textField1);
        OKButton = new JButton("Ok");
        OKButton.addActionListener(handle);
        add(OKButton);
        CancelButton = new JButton("Cancel");
        CancelButton.addActionListener(handle);
        add(CancelButton);
    }
    private class EventHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String response = "";
            boolean yay = false;
            if(textField1.getText().equalsIgnoreCase(answer))
            {
                yay = true;
                response = "That is correct";                   
            }    
            else
                response = "That is not correct. Try again.";
            if(event.getSource() == OKButton || event.getSource() == textField1)
            {
                JOptionPane.showMessageDialog(null, response);
                if(yay == true)
                    System.exit(0);
            }
            else if (event.getSource() == CancelButton)
                System.exit(0);
        }          
    }
}

