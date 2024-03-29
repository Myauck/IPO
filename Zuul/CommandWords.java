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

import java.util.HashMap;

public class CommandWords {
    
    private final HashMap<String, CommandWord> registeredCommandWords = new HashMap<String, CommandWord>();
    
    /**
     * Constructeur qui permet d'initialiser les différentes commandes possibles.
     */
    public CommandWords() {
        this.registeredCommandWords.put("go", CommandWord.GO);
        this.registeredCommandWords.put("help", CommandWord.HELP);
        this.registeredCommandWords.put("quit", CommandWord.QUIT);
        this.registeredCommandWords.put("look", CommandWord.LOOK);
        this.registeredCommandWords.put("eat", CommandWord.EAT);
        this.registeredCommandWords.put("back", CommandWord.BACK);
        this.registeredCommandWords.put("test", CommandWord.TEST);
        this.registeredCommandWords.put("take", CommandWord.TAKE);
        this.registeredCommandWords.put("drop", CommandWord.DROP);
        this.registeredCommandWords.put("inventory", CommandWord.INVENTORY);
        this.registeredCommandWords.put("charge", CommandWord.CHARGE);
        this.registeredCommandWords.put("fire", CommandWord.FIRE);
    }

    
    /**
     * Vérifie si la chaéne donnée en paramétre correspond é une commande
     * @param commandString Commande que l'on cherche a verifier
     * @return Si la chaéne est bien une commande
     */
    public boolean isCommand(final String commandString ) {
        return this.registeredCommandWords.containsKey(commandString.toLowerCase());
    }

    public CommandWord getCommand(final String commandString) {
        return this.registeredCommandWords.get(commandString);
    }
    
    /**
     * Permet de récupérer l'ensemble des commandes disponibles dans le jeu
     * @return Commandes disponibles
     */
    public String getCommandList() {
        String commands = "Available commands : ";
        for(String command : this.registeredCommandWords.keySet()) {
            commands += command + " ";
        }
        return commands;
    }
}