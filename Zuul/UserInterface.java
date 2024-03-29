import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;

/**
 * This class implements a simple graphical user interface with a 
 * text entry area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2023)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    helpButton;
    
    private static final String imageFolder = "Images";

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine gameEngine )
    {
        this.aEngine = gameEngine;
        this.createGUI();
    }

    /**
     * Print out some text into the text area.
     * @param pText Text that we want to show to the console
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    }

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText Text tgat we want to show to the console
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    }

    /**
     * Show an image file in the interface.
     * @param pImageName image path we want to show
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = imageFolder + "/" + pImageName;
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            System.out.println("reading image " + vImagePath);
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            System.out.println("finaly image " + vImagePath);
            this.aMyFrame.pack();
        }
    }

    /**
     * Enable or disable input in the entry field.
     * @param pOnOff enable or disable the field
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 );
            this.aEntryField.addActionListener( this );
            return;
        }
        this.aEntryField.getCaret().setBlinkRate( 0 );
        this.aEntryField.removeActionListener( this );
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Pas de titre d�cid� pour le moment" );
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        this.aImage = new JLabel();
        
        this.helpButton = new JButton("Help");

        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BorderLayout() );
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add( this.helpButton, BorderLayout.EAST );

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        this.helpButton.addActionListener(this);
        this.aEntryField.addActionListener(this);
        
        this.aMyFrame.addWindowListener(
            new WindowAdapter() {
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    }

    /**
     * Actionlistener interface for entries.
     * @param pE Event we want to perform
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        if(pE.getSource() == this.aEntryField) {
            this.processCommand();
        }
        else if(pE.getSource() == this.helpButton) {
            if(pE.getActionCommand().equalsIgnoreCase("help")) {
                this.aEngine.interpretCommand("help");
            }
        }
    }

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    }
}
