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

import java.lang.StringBuilder;

public class CommandWords
{
    private final String[] registeredCommands;

    /**
     * Constructeur qui permet d'initialiser les diff�rentes commandes possibles.
     */
    public CommandWords() {
        this.registeredCommands = new String[]  {
            "go", "help", "quit", "look", "eat", "back", "test", "take", "drop"
        };
    }

    
    /**
     * V�rifie si la cha�ne donn�e en param�tre correspond � une commande
     * @param commandString Commande que l'on cherche a verifier
     * @return Si la cha�ne est bien une commande
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
     * Permet d'afficher l'ensemble des commandes enregistr�es.
     */
    public void showAll() {
        for(String command : registeredCommands) {
            System.out.print(command + " ");
        }
        System.out.println("");
    }
    
    /**
     * Permet de r�cup�rer l'ensemble des commandes disponibles dans le jeu
     * @return Commandes disponibles
     */
    public String getCommandList() {
        StringBuilder strB = new StringBuilder();
        for(String command : registeredCommands) {
            strB.append(command);
            strB.append(" ");
        }
        return strB.toString().trim();
    }
}