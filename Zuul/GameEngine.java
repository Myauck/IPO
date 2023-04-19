import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Gaillet Leo
 */
public class GameEngine {
    
    private Player player;
    private Parser parser;
    private UserInterface userInterface;

    /**
     * Constructeur par defaut de la classe Game
     */
    public GameEngine() {
        this.parser = new Parser();
        this.player = new Player("Arthur"); // On défini le nouveau joueur avant this.createRooms() car 'createRooms' compte utiliser player
        this.createRooms();
    }
    
    /**
     * Affiche les messages lors du debut du jeu
     */
    private void printWelcome() {
        this.userInterface.println("Welcome to the Enchanted City !");
        this.userInterface.println("");
        this.userInterface.println("You are one of these guardian that protect the city from ennemies. A mean witcher had done something bad to the city");
        this.userInterface.println("Type 'help' if you need help.");
        printLocationInfo();
    }

    /**
     * Permet d'instancier les differentes salles et les differents objets du jeu
     */
    private void createRooms() {
        
        // DÃ©finis les différents endroits possible dans le jeu
        Room kingPalace, fortressPrison, fortressDungeon, fortressUnderground, artefactsRoom, portalsRoom,
            fortressYard, fortressEntrance, silverRiver, joyfulAvenue, cascadesOfDiamonds, secretCascadeOfDiamonds,
            forbiddenForest, forbiddenForestCave;

        // Initialise les diffÃ©rentes Rooms
        kingPalace = new Room("The King's Floor. You are certainly appreciated by the King.", "etages-du-roi.jpg");
        fortressDungeon = new Room("The Dungeon of the Fortress. It's a privilege to be here.", "dongeon-du-fort.jpg");
        fortressPrison = new Room("The Prison of the Fortress. It can be some prisoners in there.", "prison-du-fort.jpg");
        fortressUnderground = new Room("The Basement of the Fortress. You may see some bats.", "sous-bassements-du-fort.jpg"); 
        artefactsRoom = new Room("The Artefacts Room. You can feel strange things coming from this place.", "salle-des-artefacts.jpg");
        portalsRoom = new Room("The portal room. Portals may teleport you, even if the way by foot is impossible", "salle-des-portails.jpg");
        fortressYard = new Room("The Fortress Yard. See how beautiful are flowers around here.", "jardin-du-fort.jpg");
        fortressEntrance = new Room("The Entrance of The Fortress. Why is this door so tall ?", "entree-du-fort.jpg");
        silverRiver = new Room("The Silver River. Is this silver or just the snow falling on it ?", "riviere-d-argent.jpg");
        joyfulAvenue = new Room("The Joyful Avenue. This avenue goes so far away that I can't see the end.", "avenue-de-la-joie.jpg");
        cascadesOfDiamonds = new Room("The Cascades of Diamondstones, The water is so turquoise that I can't see the difference with the color of the diamond", "cascade-aux-diamants.jpg");
        secretCascadeOfDiamonds = new Room("The Secret Place of the Cascade of Diamonds, Who could pretend that under the cascade it would be an door ?", "salle-secrete-cascade-aux-diamants.jpg");
        forbiddenForest = new Room("The Forbidden Forest. The only thing that I can say about this place is it's make me goosebumps", "foret-interdite.jpg");
        forbiddenForestCave = new Room("The Cave of The Forbidden Forest. This place is filled by the Darkness", "grotte-de-la-foret-interdite.jpg");


        // Affecte les diffÃ©rentes sorties de salles pour chaque salles
        kingPalace.setExit("down", fortressDungeon);

        fortressPrison.setExit("east", fortressDungeon);
        
        fortressDungeon.setExit("west", fortressPrison);
        fortressDungeon.setExit("up", kingPalace);
        fortressDungeon.setExit("down", fortressUnderground);
        fortressDungeon.setExit("south", fortressYard);

        fortressUnderground.setExit("up", fortressDungeon);

        artefactsRoom.setExit("east", fortressYard);

        fortressYard.setExit("south", fortressEntrance);
        fortressYard.setExit("north", fortressDungeon);
        fortressYard.setExit("west", artefactsRoom);
        fortressYard.setExit("east", portalsRoom);

        portalsRoom.setExit("west", fortressYard);

        fortressEntrance.setExit("north", fortressYard);
        fortressEntrance.setExit("south", joyfulAvenue);

        silverRiver.setExit("east", joyfulAvenue);

        joyfulAvenue.setExit("north", fortressEntrance);
        joyfulAvenue.setExit("east", cascadesOfDiamonds);
        joyfulAvenue.setExit("west", silverRiver);
        joyfulAvenue.setExit("south", forbiddenForest);

        cascadesOfDiamonds.setExit("west", joyfulAvenue);
        cascadesOfDiamonds.setExit("down", secretCascadeOfDiamonds);

        forbiddenForest.setExit("north", joyfulAvenue);
        forbiddenForest.setExit("down", forbiddenForestCave);

        secretCascadeOfDiamonds.setExit("up", cascadesOfDiamonds);

        forbiddenForestCave.setExit("up", forbiddenForest);
        
        this.player.setCurrentRoom(fortressEntrance, false);
        
        // Défini les différents item dans le jeu
        Item fleur, chaise, epee;
        
        fleur = new Item("Fleur", "Il y en a une dans la salle de début", 1);
        chaise = new Item("Chaise", "Bon, même si elle ne sert à rien, il faut quand même la mettre", 5);
        epee = new Item("Epee", "Qui dit, monde fantastique, dit aussi, épée stylée", 2);
        this.player.getCurrentRoom().addItem(fleur);
        
        fortressDungeon.addItem(chaise);
        fortressDungeon.addItem(epee);
    }
    
    /**
     * Affiche les commandes possibles
     */
    private void printHelp() {
        this.userInterface.println("Your commands are : " + parser.getCommandString());
    }
    
    
    /**
     * Affiche la piece actuelle et ses sorties
     */
    private void printLocationInfo() {
        this.userInterface.println(this.player.getCurrentRoom().getLongDescription());
        if(this.player.getCurrentRoom().getImageName() != null)
            this.userInterface.showImage(player.getCurrentRoom().getImageName());
    }
    
    
    /**
     * Permet d'acceder a une piece selon la direction et affiche la salle ou nous sommes
     * @param command Direction dans laquelle le joueur souhaite aller
     */
    private void goRoom(final Command command) {
        if (!command.hasSecondWord()){
            this.userInterface.println("Go where ?");
            return;
        }
        
        Room nextRoom = this.player.getCurrentRoom().getExit(command.getSecondWord());
        
        if (nextRoom == null){
            this.userInterface.println("There is no door !");
            return;
        }
        
        this.player.setCurrentRoom(nextRoom, true);
        printLocationInfo();
    }
    
    
    /**
     * Permet de quitter le jeu
     * @param command Commande ecrite par l'utilisateur
     * @return Vraie si le joueur ecrit quitter ou faux si il ya un second mot
     */
    private boolean quit(final Command command) {
        if (command.hasSecondWord()){
            this.userInterface.println("Quit what?");
            return false;
        }
        
        this.userInterface.println("Thank you for playing.  Good bye.");
        this.userInterface.enable(false);
        return true;
    } 
    
    /**
     * Permet d'afficher la description complete de la salle dans laquelle nous sommes actuellement
     * @param command Commande look
     */
    private void look(Command command) {
        if(command.hasSecondWord()) {
            String commandWord = command.getSecondWord();
            this.userInterface.println(
                this.player.getCurrentRoom().getItem(commandWord) != null ? 
                    this.player.getCurrentRoom().getItem(commandWord).getLongDescription() :
                    "Item not found");
            return;
        }
        
        this.userInterface.println(this.player.getCurrentRoom().getLongDescription());
    }
     
    /**
     * Permet d'afficher que nous avons mange
     */
    private void eat() {
        this.userInterface.println("You have eaten now and you are not hungry any more.");
    }

    /**
     * Permet d'executer une commande saisie par l'utilisateur
     * @param rawCommand Commande que l'utilisateur a saisie
     */
    public void interpretCommand(final String rawCommand) {
        this.userInterface.println( "> " + rawCommand );
        Command command = this.parser.getCommand( rawCommand.toLowerCase() );

        if (command.isUnknown()) {
            this.userInterface.println("I don't know what you mean ...");
            return;
        }
        
        switch(command.getCommandWord().toLowerCase()) {
            case "go":
                goRoom(command);
                return;
            case "quit":
                quit(command);
                return;
            case "help":
                printHelp();
                return;
            case "look":
                look(command);
                return;
            case "eat":
                eat();
                return;
            case "back":
                back(command);
                return;
            case "test":
                test(command);
                return;
            default:
                this.userInterface.println("Unknown command !");
                return;
        }
    }
    
    
    /**
     * Permet de tester un ensemble de commandes enregistrées dans un fichier et les exécute
     * @param command Commande test lors de l'exécution
     */
    public void test(final Command command) {

        if(!command.hasSecondWord()) {
            this.userInterface.println("You need to choose a file to test commands !");
            return;
        }
        
        File file = new File(command.getSecondWord() + ".txt");

        if(!file.exists()) {
            this.userInterface.println("file named " + file.getName() + " is not found !");
            return;
        }

        try {
            Scanner commandScanner = new Scanner(file);

            while(commandScanner.hasNextLine()) {
                String rawCommand = commandScanner.nextLine();
                this.interpretCommand(rawCommand);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            this.userInterface.println("Unable to read file " + file.getName() + "!");
            return;
        }
    }
    
    /**
     * Permet de retourner dans la salle precedente
     * @param command Commande back
     */
    public void back(final Command command) {
        if(command.hasSecondWord()) {
            this.userInterface.println("Back what ?");
            return;
        }
        
        if(!this.player.hasPreviousRoom()) {
            this.userInterface.println("There is any previous room !");
            return;
        }
        
        this.player.goPreviousRoom();
        printLocationInfo();
    }
    
    /**
     * Permet de définir une interface utilisateur au moteur du jeu
     * @param userInterface Interface utilisateur necessaire au moteur du jeu
     */
    public void setGUI(final UserInterface userInterface) {
        this.userInterface = userInterface;
        this.printWelcome();
    }
}
