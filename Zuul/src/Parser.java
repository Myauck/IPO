import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + L.Gaillet
 * @version 2008.03.30 + 2013.09.15                        + 2023.02.19
 */
public class Parser 
{
    private CommandWords commands;
    private Scanner      reader;

    
    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.commands = new CommandWords();
        this.reader = new Scanner(System.in);
    }

    
    /**
     * @return Commande entrée par l'utilisateur
     */
    public Command getCommand() 
    {
        String vInputLine, vWord1 = null, vWord2 = null;

        System.out.print( "> " );
        vInputLine = this.reader.nextLine();

        Scanner vTokenizer = new Scanner( vInputLine );
        if(vTokenizer.hasNext()) {     
            vWord1 = vTokenizer.next();    
            if(vTokenizer.hasNext()) { 
                vWord2 = vTokenizer.next();
            }
        }

        // Permet de vérifier si aucune commande n'a été rentrée
        // en d'autres termes : Si vWord1 est null
        else
            return new Command(null, null);
        
        if ( this.commands.isCommand( vWord1 ) ) {
            return new Command( vWord1, vWord2 );
        }
        else {
            return new Command( null, null ); 
        }
    }
    
    
    public void showCommands() {
        this.commands.showAll();
    }
    
}