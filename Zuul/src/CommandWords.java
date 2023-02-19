/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + L.Gaillet
 * @version 2008.03.30 + 2019.09.25                        + 2023.02.19
 */
public class CommandWords
{
    private final String[] registeredCommands;

    /**
     * Constructeur qui permet d'initialiser les différentes commandes possibles.
     */
    public CommandWords() {
        this.registeredCommands = new String[5];
        this.registeredCommands[0] = "go";
        this.registeredCommands[1] = "help";
        this.registeredCommands[2] = "quit";
        this.registeredCommands[3] = "look";
        this.registeredCommands[4] = "eat";
    }

    
    /**
     * Vérifie si la chaîne donnée en paramètre correspond à une commande
     * 
     * @return Si la chaîne est bien une commande
     */
    public boolean isCommand( final String commandString )
    {
        for(int i = 0; i<this.registeredCommands.length; i++ ) {
            if(commandString.equals(this.registeredCommands[i]))
                return true;
        }
        return false;
    } 
    
    
    /**
     * Permet d'afficher l'ensemble des commandes enregistrées.
     */
    public void showAll() {
        for(String command : registeredCommands) {
            System.out.print(command + " ");
        }
        System.out.println("");
    }
}