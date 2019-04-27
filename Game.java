import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Matthew O'Neil
 * Testing: David 
 * Assignment #
 * Date: 
 */
public class Game
{
    private boolean playing;
    private boolean running;
    private boolean trying;
    private boolean trying2;
    private boolean trying3;
    private int playerNumber;
    private int numPlayers;
    private final Board board = new Board();
    private ArrayList<Player> players = new ArrayList<>();
    Scanner scn = new Scanner(System.in);
    
    /**
     * Blank constructor.
     */
    public Game() {}
    
    /**
     * This method starts to program and ask the user whether they
     * want to start a new game or load a game. From there it will direct the
     * program to either the newGame method or the loadGame method.
     * @throws InterruptedException
     * @throws FileNotFoundException 
     */
    public void startUp() throws InterruptedException, FileNotFoundException
    {
        running = true;
        trying = true;
        System.out.println("____________________________________________________________");
        System.out.println("    ____                                                    ");
        System.out.println("   /  0 |                                                   ");
        System.out.println("  /  ___|~                                                  ");
        System.out.println(" /  /   *****   *   *    **    *  *  ****   *****      *    ");
        System.out.println(" |  |  *        **  *   *  *   * *   *     *           *    ");
        System.out.println(" |  |   *****   * * *  ******  **    ***    *****   ******* ");
        System.out.println(" |  |        *  *  **  *    *  * *   *           *   * *    ");
        System.out.println("/  /    *****   *   *  *    *  *  *  ****   *****     **    ");
        System.out.println("__/                                                         ");
        System.out.println("  *        **    ****   ****   ****  ****    *****  |---|   ");
        System.out.println("  *       *  *   *   *  *   *  *     *   *  *       |---|   ");
        System.out.println("  *      ******  *   *  *   *  ***   ****    *****  |---|   ");
        System.out.println("  *      *    *  *   *  *   *  *     *  *         * |---|   ");
        System.out.println("  *****  *    *  ****   ****   ****  *   *   *****  |---|   ");
        System.out.println("                                                    |---|   ");
        System.out.println("____________________________________________________________");
        
        while(trying == true)
        {
            System.out.print("               1: New game"+"\n"
                            +"               2: Load game"+"\n"
                            +"             1/2: ");
            String answer = scn.nextLine();
            if ("1".equals(answer)){
                this.newGame();
                trying = false;
            }
            else if("2".equals(answer)){
                this.loadGame();
                trying = false;
            }
            else if(!"1".equals(answer) && !"2".equals(answer)){
                System.out.println("INVAILD INPUT(Neither of the displayed choices).. Try again.");
                trying = true;
            }
        }
    }
    
    /**
     * Here, since the player wanted to start a new game, the program ask for the
     * number of players and creates an ArrayList of Players from the Player class.
     * From here the program will be directed towards the startGame method.
     * @throws InterruptedException
     * @throws FileNotFoundException 
     */
    public void newGame() throws InterruptedException, FileNotFoundException{
        
        trying2 = true;
        
        while(trying2 == true)
        {
            try{
                System.out.print("Please enter the number of players: ");
                numPlayers = scn.nextInt();
                trying2 = false;
            }
            catch(InputMismatchException e){
                System.out.println("INVAILD INPUT(Not an integer).. Try again.");
            }
            scn.nextLine();
        }
        for(int i = 0; i < numPlayers; i++){
            System.out.print("Player "+(i+1)+" name: ");
            String name = scn.next();
            players.add(new Player(i,name));
        }
        this.startGame();
    }
    
    /**
     * This method reads a file to retrieve the number of players, building the Player
     * ArrayList using a player number, name, and score from the file. From here the program is directed towards
     * the startGame method since it now has a player base to continue a game.
     * @throws FileNotFoundException
     * @throws InterruptedException 
     */
    public void loadGame() throws FileNotFoundException, InterruptedException
    {
        
        
            String grabbedFile = null;
            System.out.println("What file would you like to load? (Include the .txt)");
            
            File fileCeption = new File("src\\files");
            File[] fileArray = fileCeption.listFiles();
            for(int i = 0; i < fileArray.length; i++)
            {
                System.out.println(fileArray[i].getName());
            }
            
            boolean fileLoadingRepeat = true;
            
            Scanner fileGrabber = new Scanner(System.in);
            
            while(fileLoadingRepeat)
            {
                
                grabbedFile = fileGrabber.next();
                for(int i = 0; i < fileArray.length; i++)
                {
                    if(grabbedFile.equals(fileArray[i].getName()))
                    {
                        fileLoadingRepeat = false;
                    }
                    else if(grabbedFile.equals(fileArray[i].getName() + ".txt"))
                    {
                        fileLoadingRepeat = false;
                    }
                }
                if(fileLoadingRepeat)
                {
                    System.out.println("The input does not match any of the files.");
                }
            }
            
            File loadGameFile = new File("src\\files\\" + grabbedFile);
            
            Scanner in = new Scanner(loadGameFile);
            
            int loop = 0;
            int number = 0;
            int score = 0;
            int wins = 0;
            String name;
            String status = "";
            while(in.hasNext())
            {
                if(loop == 0)
                {
                    status = in.next();
                    numPlayers = in.nextInt();
                    loop++;
                }
                else
                {
                    if(status.equals("Continue"))
                    {
                        number = in.nextInt();
                        name = in.next();
                        score = in.nextInt();
                        wins = in.nextInt();
                        players.add(new Player(number, name, score, wins, "Continue"));
                    }
                    else
                    {
                        number = in.nextInt();
                        name = in.next();
                        score = in.nextInt();
                        wins = in.nextInt();
                        players.add(new Player(number, name, score, wins, "End"));
                    }
                    
                }
            
                
            }
            this.startGame();
        
    
    }

    /**
     * From either the newGame or loadGame method the program is directed here with a 
     * Player ArrayList. The program then starts the turn system where an enhanced for-loop
     * is used to cycle through the player giving them each a turn to roll the dice using the
     * rollDice() method. When a player reaches 100 or above, that player is crowned the winner and the 
     * 'player' boolean is set to false, then completing the game.
     * @throws InterruptedException
     * @throws FileNotFoundException 
     */
    public void startGame() throws InterruptedException, FileNotFoundException
    {
        playing = true;
        
        System.out.println("Press enter to roll or enter 'P' to pause.. Good Luck.");
        Thread.sleep(2000);
        
        while(playing)
        {
            for(Player n : players)
            {
                if(playing)
                {
                    int rolledNum = board.rollDice();
                    System.out.println(n.getName()+" has rolled a "+rolledNum+".");
                    n.addToScore(rolledNum);
                    
                    if(n.getScore() >= 100)
                    {
                        n.setScore(100);
                    }
                    
                    System.out.println(n.getName()+" is now on tile "+n.getScore()+".");

                    //This is where the program checks if the player has landed on a chute or ladder.
                    int oldScore = n.getScore();
                    n.setScore(board.squareCheck(n.getScore()));

                    if(oldScore != n.getScore())
                    {
                        String extension = board.getExtension(oldScore, n.getScore());
                        System.out.println(n.getName()+" has landed on a "+ extension +
                                "\n" + " and is now on tile +"+n.getScore()+".");
                    }
                    
                    if(n.getScore() == 100)
                    {
                        System.out.println("Congratulations "+n.getName()+" you have won!");
                        n.setWins(n.getWins() + 1);
                        Thread.sleep(1000);
                        for (int j = 0; j < players.size(); j++) 
                        {
                            players.get(j).setTotalScore(players.get(j).getTotalScore() + players.get(j).getScore());
                        }
                        saveGame("End");
                        playing = false;
                    }
                    
                    if(playing)
                    {
                        String option = scn.nextLine();
                        if("P".equals(option) || "p".equals(option))
                        {
                        this.pauseGame();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * This method stops the game and displays a pause menu(as shown below). From there
     * the user can quit, resume, or save and quit which then uses the saveGame() method to 
     * save the game data to the file.
     * @throws FileNotFoundException
     * @throws InputMismatchException 
     */
    public void pauseGame() throws FileNotFoundException, InputMismatchException
    {
        playing = false;
        trying3 = true;
        
        while(trying3)
        {
            System.out.print(":s:--|Save&Quit|-- "+"\n"
                            +":q:  --|Quit|--    "+"\n"
                            +":r: --|Resume|--   "+"\n"
                            +":");
            String anw = scn.nextLine();
            if("s".equals(anw)){
                this.saveGame("Continue");
                trying3 = false;
            }
            else if("q".equals(anw)){
                running = false;
                trying3 = false;
            }
            else if("r".equals(anw)){
                playing = true;
                trying3 = false;
            }
            else{
                System.out.println("Invalid Input: Please enter the corresonding letter.");
                trying3 = true;
            }
        }
    }
    
    /**
     * This prints the number of players as well as the player's player-number, name,
     * and score to a file, and number of times they've won a game, then quits the game.
     * @throws FileNotFoundException 
     * @param status tells whether the game saved is in the middle of a game or at the end of a game. 
     */
    public void saveGame(String status) throws FileNotFoundException{
        System.out.println("What would you like to name the file?");
        Scanner scan = new Scanner(System.in);
        String fileNamedByUser = scan.nextLine();
        if(fileNamedByUser.equals(""))
        {
            fileNamedByUser = "SavedGame";
        }
        try (PrintWriter out = new PrintWriter("src\\files\\" + fileNamedByUser + ".txt")) {
            out.println(status + " " + numPlayers);
            if(status.equals("Continue"))
            {
                players.forEach((f) -> {
                out.println(f.displayInfo());
                });
            }
            else
            {
                players.forEach((f) -> {
                out.println(f.displayEndgameInfo());
                });
            }
            
        }
    }
}


