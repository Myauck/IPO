import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Gaillet Leo
 * @version 1.0.0
 */
public class GameEngine {
    
    private Player player;
    private Parser parser;
    private UserInterface userInterface;

    private int commandsLeft = 100;

    /**
     * Constructeur par defaut de la classe Game
     */
    public GameEngine() {
        this.parser = new Parser();
        this.player = new Player("Arthur", 2);
        this.createRooms();
    }
    
    /**
     * Permet de d�finir une interface utilisateur au moteur du jeu
     * @param userInterface Interface utilisateur necessaire au moteur du jeu
     */
    public void setGUI(final UserInterface userInterface) {
        this.userInterface = userInterface;
        this.printWelcome();
    }

    public void endGame() {
        this.userInterface.enable(false);
    }

    /**
     * Permet d'instancier les differentes salles et les differents objets du jeu
     */
    private void createRooms() {
        
        // Définis les diff�rents endroits possible dans le jeu
        Room kingPalace, fortressPrison, fortressDungeon, fortressUnderground, artefactsRoom, portalsRoom,
            fortressYard, fortressEntrance, silverRiver, joyfulAvenue, cascadesOfDiamonds, secretCascadeOfDiamonds,
            forbiddenForest, forbiddenForestCave;

        // Initialise les différentes Rooms
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


        // Affecte les différentes sorties de salles pour chaque salles
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
        
        // D�fini les diff�rents item dans le jeu
        Item fleur, chaise, epee, cookie;

        cookie = new Item("Cookie", "Cookie magique", 1);
        fleur = new Item("Fleur", "Il y en a une dans la salle de d�but", 1);
        chaise = new Item("Chaise", "Bon, m�me si elle ne sert � rien, il faut quand m�me la mettre", 5);
        epee = new Item("Epee", "Qui dit, monde fantastique, dit aussi, �p�e styl�e", 2);
        this.player.getCurrentRoom().getItemList().addItem(fleur);
        
        fortressEntrance.getItemList().addItem(cookie);
        fortressDungeon.getItemList().addItem(chaise);
        fortressDungeon.getItemList().addItem(epee);
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

    private void printMovesLeft() {
        this.userInterface.println("You have " + this.commandsLeft + " commands lefts !");
    }

    private void printGameOver() {
        this.userInterface.println("----- ! GAME OVER ! -----");
    }

    /**
     * Permet d'executer une commande saisie par l'utilisateur
     * @param rawCommand Commande que l'utilisateur a saisie
     */
    public void interpretCommand(final String rawCommand) {
        this.userInterface.println( "> " + rawCommand );
        
        Command command = this.parser.getCommand(rawCommand.toLowerCase());
        CommandWord commandWord = this.parser.getCommandWords().getCommand(command.getCommandWord());
        
        boolean successCommand = true;

        switch(commandWord) {
            case GO:
                successCommand = goRoom(command);
                break;
            case QUIT:
                successCommand = quit(command);
                return;
            case HELP:
                successCommand = help(command);
                break;
            case LOOK:
                successCommand = look(command);
                break;
            case EAT:
                successCommand = eat(command);
                break;
            case BACK:
                successCommand = back(command);
                break;
            case TEST:
                successCommand = test(command);
                break;
            case TAKE:
                successCommand = take(command);
                break;
            case DROP:
                successCommand = drop(command);
                break;
            case INVENTORY:
                successCommand = inventory(command);
                break;
            default:
                this.userInterface.println("Unknown command !");
                break;
        }

        if(successCommand) {
            this.commandsLeft--;
            printMovesLeft();
            if(this.commandsLeft == 0) {
                endGame();
                printGameOver();
            }
        }
    }
    
    
    /**
     * Permet d'acceder a une piece selon la direction et affiche la salle ou nous sommes
     * @param command Direction dans laquelle le joueur souhaite aller
     * @return Si la commande a fonctionné
     */
    private boolean goRoom(final Command command) {
        if (!command.hasSecondWord()){
            this.userInterface.println("Go where ?");
            return false;
        }
        
        Room nextRoom = this.player.getCurrentRoom().getExit(command.getSecondWord());
        
        if (nextRoom == null){
            this.userInterface.println("There is no door !");
            return false;
        }
        
        this.player.setCurrentRoom(nextRoom, true);
        printLocationInfo();
        return true;
    }

    public boolean help(final Command command) {
        this.printHelp();
        return true;
    }
    
    
    /**
     * Permet de quitter le jeu
     * @param command Commande ecrite par l'utilisateur
     * @return Si la commande a fonctionné
     */
    private boolean quit(final Command command) {
        if (command.hasSecondWord()){
            this.userInterface.println("Quit what?");
            return false;
        }
        
        this.userInterface.println("Thank you for playing.  Good bye.");
        this.endGame();
        return true;
    } 
    
    /**
     * Permet d'afficher la description complete de la salle dans laquelle nous sommes actuellement
     * @param command Commande look
     * @return Si la commande a fonctionné
     */
    private boolean look(Command command) {
        if(command.hasSecondWord()) {
            String commandWord = command.getSecondWord();
            this.userInterface.println(this.player.getCurrentRoom().lookForItem(commandWord));
        }
        else
            this.userInterface.println(this.player.getCurrentRoom().getLongDescription());
        return true;
    }
     
    /**
     * Permet d'afficher que nous avons mange
     * @param command Commande eat
     * @return Si la commande a fonctionné
     */
    private boolean eat(final Command command) {
        if(command.hasSecondWord()) {
            if(!command.getSecondWord().equalsIgnoreCase("cookie")) {
                this.userInterface.println("You can't eat something else than cookie.");
                return false;
            }
        }

        if(!this.player.getInventory().hasItem("cookie")) {
            this.userInterface.println("You haven't cookie in your inventory !");
            return false;
        }

        final Item cookie = this.player.getInventory().getItem("cookie");
        this.player.getInventory().removeItem(cookie);
        this.player.setMaxWeight(this.player.getMaxWeight() * 2);

        this.userInterface.println("You have eaten " + cookie.getName() + " ! You are able to carry 2 times more !");
        return true;
    }
    
    /**
     * Permet de retourner dans la salle precedente
     * @param command Commande back
     */
    private boolean back(final Command command) {
        if(command.hasSecondWord()) {
            this.userInterface.println("Back what ?");
            return false;
        }
        
        if(!this.player.hasPreviousRoom()) {
            this.userInterface.println("There is any previous room !");
            return false;
        }
        
        this.player.goPreviousRoom();
        printLocationInfo();
        return true;
    }
    
    
    /**
     * Permet de tester un ensemble de commandes enregistr�es dans un fichier et les ex�cute
     * @param command Commande test lors de l'ex�cution
     * @return Si la commande a fonctionné
     */
    private boolean test(final Command command) {

        if(!command.hasSecondWord()) {
            this.userInterface.println("You need to choose a file to test commands !");
            return false;
        }
        
        File file = new File(command.getSecondWord() + ".txt");

        if(!file.exists()) {
            this.userInterface.println("file named " + file.getName() + " is not found !");
            return false;
        }

        try {
            Scanner commandScanner = new Scanner(file);

            while(commandScanner.hasNextLine()) {
                String rawCommand = commandScanner.nextLine();
                this.interpretCommand(rawCommand);
            }

            commandScanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            this.userInterface.println("Unable to read file " + file.getName() + "!");
            return false;
        }

        return true;
    }
    
    /**
     * Permet d'afficher la r�ponse du jeu quand le joueur veut r�cup�rer un objet dans une pi�ce
     * @param command Commande "take" au moment de l'ex�cution
     * @return Si la commande a fonctionné
     */
    private boolean take(final Command command) {
        if(!command.hasSecondWord()) {
            this.userInterface.println("Take what ?");
            return false;
        }
        else {
            this.userInterface.println(this.player.takeItem(command.getSecondWord()));
            return true;
        }
    }   
    
    /**
     * Permet d'afficher la r�ponse du jeu quand le joueur veut d�poser un objet dans la pi�ce
     * @param command Commande "drop" que le joueur a saisi
     * @return Si la commande a fonctionné
     */
    private boolean drop(final Command command) {
        if(!command.hasSecondWord()) {
            this.userInterface.println("Drop what ?");
            return false;
        }
        else {
            this.userInterface.println(this.player.dropItem(command.getSecondWord()));
            return true;
        }
    }
    
    /**
     * Permet d'afficher l'inventaire du joueur
     * @param command Commande "inventory" pour afficher la commande
     * @return Si la commande a fonctionné
     */
    private boolean inventory(final Command command) {
        if(command.hasSecondWord()) {
            this.userInterface.println("I dont understand what do you want...");
            return false;
        }
        else {
            this.userInterface.println("My Inventory : " + this.player.getInventoryContent());
            return true;
        }
    }
}
