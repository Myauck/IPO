

# Compte Rendu d'IPO

## Informations

<u>Nom du projet</u> : **Les gardiens de la cité enchantée**

<u>Auteur</u> : **Léo Gaillet** (E1-5)

<u>Scénario détaillé</u> : Dans un monde fantastique peuplé de créatures magiques, la cité enchantée était autrefois un lieu de paix et de prospérité. Cependant, un groupe maléfique de sorciers a lancé un sortilège qui a plongé la cité dans l'obscurité et la destruction.

Un jeune héros a été choisi pour devenir un gardien de la cité enchantée. Les gardiens sont des guerriers magiques qui ont juré de protéger la cité et ses habitants, et de combattre les forces du mal qui menacent l'équilibre de l'univers.

Le joueur doit explorer la cité enchantée et ses environs pour trouver des alliés, des armes magiques et des artefacts anciens qui peuvent aider à défaire le sortilège maléfique. Le joueur doit également affronter des ennemis dangereux, tels que des créatures magiques corrompues, des soldats maléfiques et des sorciers puissants.

Au fil de l'aventure, le joueur découvre des indices sur l'identité des sorciers maléfiques et leur plan diabolique. Les gardiens doivent alors rassembler leurs forces et lutter pour sauver la cité enchantée et ramener la paix dans le monde fantastique.

<u>Carte du jeu</u> :

![](C:\Users\leoga\Documents\rooms.drawio.png)

<u>Détails des lieux</u> :

- **Etages du roi** : "The King's Floor. You are certainly appreciated by the King."
- **Donjon du Fort** : "The Dungeon of the Fortress. It's a privilege to be here." 
- **Prison du Fort** : "The Prison of the Fortress. It can be some prisoners in there."
- **Sous-sol du Fort** : "The Basement of the Fortress. You may see some bats."
- **Salle des Artéfacts** : "The Artefacts Room. You can feel strange things coming from this place."
- **Salle des Portails** : "The portal room. Portals may teleport you, even if the way by foot is impossible"
- **Cours du Fort** : "The Fortress Yard. See how beautiful are flowers around here."
- **Entrée du Fort** : "The Entrance of The Fortress. Why is this door so tall ?"
- **La Rivière d'Argent** : "The Silver River. Is this silver or just the snow falling on it ?"
- **L'Avenue de la Bonté** : "The Joyful Avenue. This avenue goes so far away that I can't see the end."
- **La Forêt Interdite** : "The Forbidden Forest. The only thing that I can say about this place is it's make me goosebumps"
- **La Grotte de la Forêt Interdite** : "The Cave of The Forbidden Forest. This place is filled by the Darkness"
- **Les Cascades de Diamants** : "The Cascades of Diamonds, The water is so turquoise that I can't see the difference with the color of the diamond"
- **Salle secrète de la Cascade de Diamants** : "The Secret Place of the Cascade of Diamonds, Who could pretend that under the cascade it would be an door ?"

<u>Items disponibles</u> : [SOON]

<u>Personnages</u> :

- Le héro

- [SOON]

<u>Situations gagnantes</u> : Quand le héros tue le grand nécromancien ET libère la cité de son sortilège

<u>Situations perdantes</u> : Si le joueur meurt, si la phase principale du sortilège maléfique commence, si un sorcier touche le joueur avec une attaque "attrape rêve" qui l'enferme dans un rêve sans fin.

### Partie exercices

<hr>

###### Exercice 7.6

Dans `Room.java` Nous avons cette procédure:

```java
/**
 * Permet de définir les différentes sorties de la salle instanciée
 * 
 * @param northRoom Salle se trouvant au Nord de cette salle
 * @param southRoom Salle se trouvant au Sud de cette salle
 * @param eastRoom Salle se trouvant à l'Est de cette salle
 * @param westRoom Salle se trouvant à l'Ouest de cette salle
 */
public void setExits(final Room northRoom, final Room southRoom, final Room eastRoom, final Room westRoom) {
    this.northRoom = northRoom;
    this.southRoom = southRoom;
    this.eastRoom = eastRoom;
    this.westRoom = westRoom;
}
```

Ajoutons quelques éléments pour augmenter notre nombre de sorties possibles:

```java
public Room {
    
    // [...]
    
    public Room upRoom;
    public Room downRoom;
    
    // [...]
    
    public void setExits(final Room northRoom, final Room southRoom, final Room eastRoom, final Room westRoom, final Room upRoom, final Room downRoom) {
        this.northRoom = northRoom;
        this.southRoom = southRoom;
        this.eastRoom = eastRoom;
        this.westRoom = westRoom;
        this.upRoom = upRoom;
        this.downRoom = downRoom;
    }
    
}
```

<u>Explications</u> : 

Nous remarquons que nous avons une procédure qui à besoin maintenant de 6 paramètres, car ces 6 paramètres représentent les 6 sorties possibles. Notre code ne permet donc pas de dynamiser le nombre de sorties possible.

Et puisque nous avons 6 paramètre dans ce programme, nous devons forcément modifier notre procédure privée `createRooms()` dans le fichier `Game.java` puisque maintenant, il n'y a plus 4 sorties mais 6 sorties.

Dans la procédure `createRooms()` dans  `Game` remplaçons les instructions de la manière suivante:

```java
fortressEntrance.setExits(fortressYard, joyfulAvenue, null, null);
// maSortie     .setExits(Nord,         Sud,          Est,  Ouest);
```

par:

```java
fortressEntrance.setExits(fortressYard, joyfulAvenue, null,   null,  null, null);
// maSortie     .setExits(Nord,         Sud,          Est,    Ouest, Haut, Bas);
```

<hr>

###### Exercice 7.7

Dans la classe `Room` créons une méthode `getExitString()`

```java
/**
 * Permet de récupérer l'ensemble des sorties possible de la salle instanciée
 *
 * @return Ensemble des sorties possible
 */
public String getExitString() {
   String exits = "Available Exits : ";
    
   if(this.northExit != null) exits += "north ";
   if(this.southExit != null) exits += "south ";
   if(this.eastExit != null) exits += "east ";
   if(this.westExit != null) exits += "west ";
   
   // ainsi que les deux nouvelles sorties
   if(this.upExit != null) exits += "up ";
   if(this.downExit != null) exits += "down";
    
   return exits;
}
```

<hr>

###### Exercice 7.8

Dans le fichier `Room.java` Nous pouvons ajouter/remplacer :
```java
import java.util.HashMap;

public class Room {
    
    // [...]

    private final HashMap<String, Room> exits = new HashMap<String, Room>();   

    /**
     * Setter qui permet de définir une salle en fonction de la direction de sortie
     * Cette procédure n'est pas sensible à la casse ! 
     *
     * @param direction Direction de la sortie qu'on souhaite modifier
     * @param exitRoom Salle qui va être définie comme sortie à la direction indiquée
     */
    public void setExit(final String direction, final Room exitRoom) {
        // Vérifie si la salle n'est pas nul, ça reviendrait à dire
        // qu'on a pas de salle en argument
        if(exitRoom != null)
            return this.exits.get(direction.toLowerCase());
    }

    /**
     * Getter qui récupère la salle d'une sortie en fonction de la direction
     * de sortie. Cette procédure n'est pas sensible à la casse ! 
     *
     * @param direction Direction de la sortie qu'on cherche
     * @return Salle se trouvant à la sortie recherchée
     */
    public Room getExit(final String direction) {
        return this.exits.get(direction.toLowerCase());
    }

    // [...]
}
```

Ce qui permet de rendre plus modulable nos sorties et ainsi permettre à nos salles de ne pas dépendre uniquement de 6 sorties mais en définir une infinité et les dynamiser (*à condition que la RAM suive aussi*) 

<u>Explications</u> : 

La fonction `toLowerCase()` permet de remplacer les caractères en majuscules dans la chaîne et les mettre en minuscule, permettant ainsi d'empêcher de créer deux directions qui sont similaires (*par exemple East, EaST et eAsT ici appartiendraient à la même direction : east*)

<hr>

###### Exercice 7.8.1

Pour ajouter dans le scénario le déplacement vertical, nous avons ajouter les deux directions nécessaire : `up` et `down`

Pour les représenter, j'ai décidé de définir leur direction verticale, j'ai représenté leurs flèches sous forme de pointiés : 

- **up** : flèche en pointillés qui commence vers la droite et pars vers la gauche OU commence vers le bas pour aller vers le haut (par rapport à la position relative de la salle)
- **down** : flèche en pointillés qui commence vers la gauche et pars vers la droite OU commencer vers le haut pour aller vers le bas (par rapport à la position relative de la salle)

###### Exercice 7.9

Maintenant que nous avons dynamiser l'ajout de sorties dans notre classe `Room` Nous devons maintenant récupérer les sorties disponibles de ces sorties

Dans la classe `Room` nous pouvons remplacer le contenu de la méthode `getExitString`:

```java
/**
 * Getter qui récupère les sorties disponibles dans la salle instanciée
 * 
 * @return Chaîne comportant la liste des sorties disponibles
 */
public String getExitString() {
    String availableExits = "Available Exits : ";
    for(String keys : this.exits.keySet())
        availableExits += keys + " ";
    return availableExits;
}
```

<u>Explications</u> : 

`this.exists.keySet()` permet de récupérer un `Set<T>`  (un ensemble) avec `T`le type des variables de référence en entrée du "dictionnaire". En effet, les HashMap sont un peu comme des dictionnaires (car clé => valeur). Ici la Map à une clé de type **`String`** (*la direction*) et renvoie une valeur de type **`Room`** (la salle en sortie) car nous avons écrit `HashMap<String, Room>`.

<hr>

###### Exercice 7.10.1

*Documentation Java complètée*

<hr>

###### Exercice 7.10.2

*Documentation Java générée*

<hr>

###### Exercice 7.11

Dans notre classe `Room`, pour ajouter une description complète de notre salle, nous allons ajouter une méthode nommée `getLongDescription()` permettant de récupérer les informations de notre salle :

```java
/**
 * Permet de récupérer l'intégralité des informations de notre salle:
 * La description de notre salle ainsi que les sorties disponible
 *
 * @return Description complète de la salle instanciée
 */
public String getLongDescription() {
    return "You are in " + this.getDescription() + "\n" + this.getExitString();
}
```

<hr>

###### Exercice 7.12

![](C:\Users\leoga\Documents\diagrammes-programme.drawio.png)

<hr>

###### Exercice 7.13

*Comment change le diagramme des objets instanciés pendant au moment de l'exécution de la commande go ?*

Lorsque nous regardons plus attentivement les étapes du programme quand on entre la commande "go" nous savons déjà que nous avons récupéré une nouvelle instance d'une commande (en effet, dans la classe `Parser` au moment où nous appelons `getCommand()`, nous observons à la fin de la méthode , un `return new Command(parametre1, parametre2);` ce qui montre bien que nous avons un nouvel objet de type `Command`.

qui dit commande "go" dit appel de la procédure `goRoom()` Nous pouvons observer que chaque fois que nous appelons `goRoom()`, nous définissons une nouvelle variable locale `vNextRoom` (celle-ci sera détruite une fois la méthode finie). Or tout ce que nous faisons c'est récupérer une salle qui se trouve stockée elle-même dans la salle où nous sommes actuellement.

<hr>

###### Exercice 7.14

Dans la classe `Game` ajoutons une nouvelle procédure :

```java
/**
 * Permet d'afficher la description complète de la salle dans laquelle nous sommes actuellement
 */
private void look(Command command) {
    if(command.hasSecondWord()) 
        System.out.println("I don't know how to look at something in particular yet");
    else
    	System.out.println(this.currentRoom.getLongDescription());
}
```

Cette méthode, si nous observons bien, elle permet de récupérer la description complète de la salle dans laquelle nous somme et de nous l'afficher.

Et puisqu'il s'agit d'une nouvelle commande, il faut l'ajouter dans le tableau de commandes disponibles dans `CommandWords` et dans les commandes à vérifier dans la procédure `processCommand` dans la classe `Game`.

Nous avons donc comme procédure `processCommand `:

```java
/**
 * Permet d'exécuter une commande saisir par l'utilisateur
 * 
 * @param command Commande que l'utilisateur a écrit
 * @return Si la commande saisie arrête le jeu
 */
private boolean processCommand(final Command command)
{
    if (command.isUnknown()) {
        System.out.println("I don't know what you mean ...");
        return false;
    }
    
    switch(command.getCommandWord().toLowerCase()) {
        case "go":
            goRoom(command);
            return false;
        case "quit":
            return this.quit(command);
        case "help":
            printHelp();
            return false;
        case "look":
            look(command);
            return false;
        default:
            System.out.println("Unknown command !");
            return false;
    }
}
```

<hr>

###### Exercice 7.15

Faisons la même chose pour la procédure `eat()`, ajoutons là dans la liste dans `CommandWords` et dans la méthode `processCommand()` :

Ecrivons la procédure :

```java
/**
 * Permet d'afficher qu'on a mangé
 */
private void eat() {
    System.out.println();
}
```

Dans la méthode `processCommand()` :

```java
/**
 * Permet d'exécuter une commande saisir par l'utilisateur
 * 
 * @param command Commande que l'utilisateur a écrit
 * @return Si la commande saisie arrête le jeu
 */
private boolean processCommand(final Command command)
{
    if (command.isUnknown()) {
        System.out.println("I don't know what you mean ...");
        return false;
    }
    
    switch(command.getCommandWord().toLowerCase()) {
        case "go":
            goRoom(command);
            return false;
        case "quit":
            return this.quit(command);
        case "help":
            printHelp();
            return false;
        case "look":
            look(command);
            return false;
        case "eat":
            eat();
            return false;
        default:
            System.out.println("Unknown command !");
            return false;
    }
}
```

<hr>

Pour dynamiser et rendre plus accessibles nos commandes, nous pouvons afficher l'ensemble des commandes sous forme de boucle.

Puisque les commandes enregistrées se trouvent dans la classe, nous allons l'écrire dans la classe `CommandWords` :

```java
/**
 * Permet d'afficher l'ensemble des commandes enregistrées.
 */
public void showAll() {
    for(String command : this.registeredCommands) {
        System.out.print(command + " ");
    }
    System.out.println("");
}
```

Faire la liaison de cette méthode dans `Parser`

```java
/**
 * Permet d'afficher les commandes disponibles
 */
public void showCommands() {
   this.commands.showAll();
}
```

Et appeler cette procédure dans `printHelp()` dans `Game`

```java
/**
 * Permet d'afficher la liste d'aide pour le joueur
 */
private void printHelp() {
    System.out.println("Available Commands : ");
    this.parser.showCommands();
}
```

<u>Explications</u> : la boucle `foreach` est de la forme `for(T type : Collection<T>)`  ou de la forme `for(T type : T[])` car il faut noter qu'une classe qui implémente l'interface générique `Collection` n'est pas un tableau de `T` mais peut représenter un tableau de `T`.

<hr>

###### Exercice 7.17

Lorsque nous ajouterons une nouvelle commande, ce ne sera plus à `Game`de les gérer mais bel et bien à `CommandWords` qui enregistrera toutes les commandes possibles.

Donc quand nous ajouterons une nouvelle commande, nous écrirons dans la classe `CommandWords` dans son constructeur : 

`this.registeredCommands[X] = "ma_nouvelle_commande";` 

avec `X` qui correspond à l'indice suivant le dernier a être utilisé pour stocker une commande.





