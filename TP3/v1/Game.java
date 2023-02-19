package v1;

/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Léo GAILLET
 * @version 1.0.0
 */
public class Game
{
    
    private Parser aParser;
    private Room aCurrentRoom;
    
    
    /**
     * Constructeur de la classe Game
     */
    public Game() {
        this.aParser = new Parser();
        createRooms();
    }
    
    /**
     * Permet de lancer la boucle principale du jeu
     */
    public void play() {
        printWelcome();
        
        boolean finish = true;
        while(!finish) {
            
            Command writtenCommand = this.aParser.getCommand();
            
            finish = processCommand(writtenCommand);
            
            
        }
        
    }
    
    
    /**
     * Permet d'initialiser les salles
     */
    public void createRooms() {
        Room vOutside = new Room("outside the main entrance of the university");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room("in the campus pub");
        Room vLab = new Room("in a computing lab");
        Room vOffice = new Room("in the computing admin office");
        
        this.aCurrentRoom = vOutside;
        this.aOutside = vOutside;
        this.aTheatre = vTheatre;
        this.aPub = vPub;
        this.aOffice = vOffice;
    }
    
    public void goRoom(final Command pCommand) {
        
        Room vNextRoom = null;
        String vDirection = pCommand.getSecondWord();
        
        switch(vDirection.toLowerCase()) {
            case "south":
                vNextRoom = this.aCurrentRoom.aSouthExit;
                break;
            case "north":
                vNextRoom = this.aCurrentRoom.aNorthExit;
                break;
            case "east":
                vNextRoom = this.aCurrentRoom.aEastExit;
                break;
            case "west":
                vNextRoom = this.aCurrentRoom.aWestExit;
                break;
            default:
                System.out.println("Unknown direction !");
                return;
        }
        
        if(vNextRoom == null) {
            System.out.println("There is no door !");
            return;
        }
        
        this.aCurrentRoom = vNextRoom;
    }
    
    public void printWelcome() {
        printMessage("Welcome to the World of Zuul !", "World of Zuul is a new, incredibly boring adventure game.", "Type 'help' if you need help");
    }
    
    private void printHelp() {
        printMessage("You are lost. You are alone.", "You wanter around at the university");
    }
    
    private void printMessage(String... messages) {
        for(String str : messages) {
            System.out.println(str);
        }
    }
        
    private boolean quit(final Command command) {
        final boolean statement = command.hasSecondWord();
        if(statement)
            printMessage("Quit what ?");
        else
            printMessage("Thank you for playing. Good bye.");
        return !statement;
    }
    
    private boolean processCommand(final Command command) {
        if(command.isUnknown()) {
            printMessage("I don't know what you mean...");
            return false;
        }
        
        else {
            switch(command.getCommandWord().toLowerCase()) {
                case "quit":
                    return quit(command);
                default:
                    return false;
            }
        }
    }
    
} // Game
