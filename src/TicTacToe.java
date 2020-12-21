//Rodrigo De Lama
//100451775@alumnos.uc3m.es
//@RDLF11
//GitHub repository page- https://github.com/RDLF11/Programming-I-Final-Project

//Scanner imported to read user inputs
import java.util.Scanner;

//Used to generate random numbers without JAVA Math class
import java.util.concurrent.ThreadLocalRandom;

//Main container of my methods
public class TicTacToe {

    //Declared a static Scanner for permanent usage, avoiding a resource leak if not closed later on
    static Scanner input = new Scanner(System.in);

    //Declared static boolean for global use in while loops
    //Boolean to check the while condition- if true, re-loops
    static boolean status = true;

    //Global enter value- to remain empty (for click enter to continue)
    static String enterToContinue;

    public static void clear() {
        //https://www.edureka.co/community/4668/how-to-clear-the-console-in-java
        //Command used to clear the console
            //Couldn't use ANSI escape codes
            //System.out.print("\033[H\033[2J");  
            //System.out.flush();

        //Changed clear method to printing lines
        for(int i = 0 ; i < 100; i++) System.out.println();
    }

    public static void title() {
        //Created a title method to avoid repetition
        System.out.println("Welcome to TicTacToe JAVA Edition!" +
                           "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    public static void sleep(int i) {
        //https://stackoverflow.com/questions/2517022/wait-function-in-java
        //Used to pause the execution for a set time in milliseconds
        try {Thread.sleep(i);} catch(InterruptedException intrx) {/* handle the exception */}
    }

    //runtime counter, resets on program restart
    static int timesRun = 0;

    //Wins and draws count
    static byte p1Wins = 0, p2Wins = 0, aiWins = 0, draws = 0;

    public static void scoreboardUpdate(int winIdentifier) {
        if (winIdentifier == 0) {
            p1Wins++;
        }
        else if (winIdentifier == 1) {
            p2Wins++;
        }
        else if (winIdentifier == 2) {
            aiWins++;
        }
    }

    //Scoreboard presented to the user only if times run (with wins) > 1
    public static void scoreboard () {

        if (timesRun > 0) {
            System.out.println("\nSCOREBOARD\n");
            System.out.println("Here's how the scoreboard is looking...\n");

            sleep(1500);

            System.out.println("Player 1 (" + p1tag + ") wins: " + p1Wins +
                             "\nPlayer 2 (" + p2tag + ") wins: " + p2Wins +
                             "\nAI wins: " + aiWins +
                             "\nThere have been " + draws + " draw(s)");

            //Structure to determine the leader
            String leader = " ";

            if ( p1Wins > p2Wins && p1Wins > aiWins ) {

                leader = p1tag; //Assign leader variable to player X
            }
            else if ( p2Wins > p1Wins && p2Wins > aiWins ) {

                leader = p2tag;
            }
            else if ( aiWins > p1Wins && aiWins > p2Wins ) {

                leader = ai;
            }
            //Display only if there's actually someone winning
            if (leader != " ") System.out.println("\n" + leader + " you're dominating!");
            
            //Wait and display loading animation
            sleep(1500);
                System.out.println(); //Spacing
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
            sleep(1000);
            }

            //Press enter when the user is ready to continue
            System.out.println("\n\nPress \"Enter\" when you're ready");
                enterToContinue = input.nextLine();
        }
    }

    //Used to determine if the user has already seen the explanation, to not prompt them the question again in a future run
    static int explanation;
    static int launcherRun;

    public static void launcher() {

        //Clear the users console before runtime
        clear();

        //Present title
        title();

        //Present the scoreboard to the user before each run
        if (launcherRun == 0) {
            //if timesRun++ was actuated in a previous game, scoreboard should run
            scoreboard();
            clear();
        }

        title();

        //Declaring before the loop to not redeclare the variables every time the loop runs
        String explanationAns;
        
        if (explanation == 0) {
            //status = false making sure while loop always engages after a rerun
            status = true;
            while (status) {

                //Asking the user if they want an explanation as to how the game works
                System.out.println("\nWould you like a quick explanation on how to play?");
                    explanationAns = input.nextLine().toLowerCase();
    
                switch (explanationAns) {
                    case "yes", "y" -> {
                        //status to false because we want to break off from the while loop after a valid input has been detected
                        status = false;
                        System.out.println("\nThis is how the game board looks:\n");                                        
                        System.out.println("      1     2     3     x" +
                                         "\n   ╔═════╦═════╦═════╗"    + 
                                         "\n1  ║     ║     ║     ║"    +
                                         "\n   ╠═════╬═════╬═════╣"    +
                                         "\n2  ║     ║     ║     ║"    +
                                         "\n   ╠═════╬═════╬═════╣"    +
                                         "\n3  ║     ║     ║     ║"    +
                                         "\n   ╚═════╩═════╩═════╝"    + 
                                         "\ny"                         );
    
                        System.out.println("\nTo place a chip on the board, just type a position's coordinates in the format \"(x,y)\" and hit \"Enter\"" +
                                           "\nTo win the game, get three chips in a row, column, or diagonally, and the win is yours!\n");
    
                        sleep(3000);
                        
                        System.out.println("\nThis is an example of a winning move:\n");
                        System.out.println("╔═════╦═════╦═════╗" + 
                                         "\n║  X  ║     ║  O  ║" +
                                         "\n╠═════╬═════╬═════╣" +
                                         "\n║     ║  X  ║     ║" +
                                         "\n╠═════╬═════╬═════╣" +
                                         "\n║  O  ║  O  ║  X  ║" +
                                         "\n╚═════╩═════╩═════╝" );
                        
                        System.out.println("\nPlayer X would win!\n");
    
                        System.out.println("\nYou may type \"exit\" in-game at any point to head back to the main menu");
    
                        System.out.println("\nPress \"Enter\" when you're ready");
                            enterToContinue = input.nextLine();
    
                        //don't show the explanation in the future unless requested by the user
                        explanation++;
                    break;
                    }
                    case "no", "n" -> {
                        status = false;
                            System.out.println("\nOkay, lets get onto it!\n");
                    break;
                    }
                    case "exit" -> {
                        //exit loop
                        status = false;
                            //exit program
                            System.exit(0); //exit(0) ends the program with no error code
                    break;
                    }
                    default -> {
                        //status = true to re-loop
                        status = true;
                            System.out.println("Sorry, I didn't catch that. Please type yes or no");
                    break;
                    }
                }
            }
        }
        clear();

        title();

        System.out.println("\nIn which mode would you like to play?");
        //Present the available game modes
        System.out.println("(A) Multiplayer: 1 versus 1\n" +
                           "(B) AI: a game against a random dumb computer");
        
        String gameModeAns;

        //Reusing the same global static status variable, resetting before each use
        status = true;
        while (status) {

            //Asking the user if they want an explanation as to how the game works
            System.out.println("\n\nType A or B to select the game mode:");
            System.out.println("\nIf you would like to check the instructions again, just type \"back\"\n");
                gameModeAns = input.nextLine().toLowerCase();
            
            switch (gameModeAns) {
                //Multiplayer
                case "a" -> {
                    status = false;
                    System.out.print("\nSome one on one action coming your way!");

                    sleep(2000);
                    multiplayer();
                break;
                }
                //AI mode (CPU Mode in my game)
                case "b" -> {
                    status = false;
                    System.out.println("\nGet ready to be destroyed by our super dumb but kinda smart AI...");

                    sleep(2000);
                    ai();
                break;
                }
                case "back" -> {
                    //Gives the user the possibility to go back for an explanation
                    explanation = 0;
                    launcherRun = 1;
                    launcher();
                break;
                }
                case "exit" -> {
                    status = false;
                        System.exit(0); //Exit without any error codes
                break;
                }

                default -> {
                    status = true;
                    System.out.println("Sorry, I didn't catch that. Please type A or B");
                break;
                }
            }
        }
    }

    public static int playerWinCheck(String tag) {

        int win = 0;

        if (tag == p1tag) {
            win = 0;
        }
        else if (tag == p2tag) {
            win = 1;
        }
        else if (tag == ai) {
            win = 2;
        }
    return win;
    }

    //Game Assets
    //Back-end game board array
    static char[][] backendGameBoard = {
                                        {' ', ' ', ' '},
                                        {' ', ' ', ' '},
                                        {' ', ' ', ' '}
    };

    public static void cleanBackEnd() {
        //Clean the back-end matrix
        for (int r = 0; r < backendGameBoard.length; r++) {
            for (int c = 0; c < backendGameBoard[0].length; c++) {
                backendGameBoard[r][c] = ' ';
            }
        }
    }

    //User facing board (front-end game board)
    //5 spaces between columns
    static char[][] userGameBoard = {
                                { '╔', '═', '═', '═', '═', '═', '╦', '═', '═', '═', '═', '═', '╦', '═', '═', '═', '═', '═', '╗'},
                                { '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║'},
                                { '╠', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╣'},
                                { '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║'},
                                { '╠', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╣'},
                                { '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║'},
                                { '╚', '═', '═', '═', '═', '═', '╩', '═', '═', '═', '═', '═', '╩', '═', '═', '═', '═', '═', '╝'}
    };

    public static void cleanFrontEnd() {
        // Position 1
        userGameBoard[1][3]  = ' ';
        // Position 2...
        userGameBoard[1][9]  = ' ';
        userGameBoard[1][15] = ' ';
        userGameBoard[3][3]  = ' ';
        userGameBoard[3][9]  = ' ';
        userGameBoard[3][15] = ' ';
        userGameBoard[5][3]  = ' ';
        userGameBoard[5][9]  = ' ';
        userGameBoard[5][15] = ' ';
    }

    //Player names
    static String p1tag = "", p2tag = "", pXtag = "";
    static final String ai = "AI";

    //Player chips
    static char p1chip = ' ', p2chip = ' ', pXchip = ' ';
    static final char aiChip = '0';

    //Player selection identifier (in AI mode)
    static int playerAssignment = 0; //value is initialized for error reporting

    public static char chipSelector(String tag, boolean ai) {

        char chip = ' ';

        status = true;
        while (status) {

            //Chip selection
            System.out.println("\n" + tag + " select your chip:");
                //Select chip and convert char to UpperCase
                chip = Character.toUpperCase(input.next().charAt(0));

            switch (chip) {
                case 'X','0','A','B','C','D','E','F','G','H','I','J','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','Y','Z','+','*','-','@','1' -> {

                    //Is the game mode AI?
                    if (ai == true && chip == '0') {
                        status = true;
                        System.out.println("Please introduce a valid chip:");
                    }
                    else status = false;
                break;
                }
                default -> {
                    status = true;
                    System.out.println("Please introduce a valid chip:");
                break;
                }
            }
        }
    return chip;
    }

    public static boolean checkFull(int back0, int back1, boolean checkAI) {
        //Check if the position is taken already
        boolean taken = false;

        if (backendGameBoard[back0][back1] != ' ') {
            taken = true; 
            if (checkAI == false) {
                System.out.println("That space is already taken..." +
                                   "\nTry again!");
            }
            else { System.out.println("\nOoops, our AI selected a place that was taken, our bad"); }
        }
    return taken;
    }

    public static void fillIn(int back0, int back1, int front0, int front1, char chip) {
        //Place chips in available squares
        backendGameBoard[back0][back1] = chip;
        userGameBoard[front0][front1] = chip;
    }
    
    //Show the user what position was chosen in the last move
    public static void posSelected(String num) {
        System.out.println(num);
    }

    public static void chipPlacer(String tag, char chip, int avoidFirstError) {

        //Boolean is always initialized as false
        boolean checkAI = false;
        if (tag == ai) checkAI = true;

        //AI value for value generator
        int randomValue;
    
        String userInput = "";

        //Global input initialized in case no condition was met
        String inputChoice = "";

        //Implementing chip placement
        int back0, back1, front0, front1;

        status = true;
        while (status) {
            //Real player case
            if ( checkAI == false ) {

                System.out.println("\n" + tag + " in what square would you like to place your chip?");
                //Couldn't figure out how to avoid the error of the scanner reading "enter" value from last input
                //I made a counter to avoid displaying the first error message

                //Wait for the next input -- still briefly shows first error without avoidFirstError fix
                while ( input.hasNext("\n") ) {
                    input.nextLine();
                }

                while (status) {
                    status = true;

                    if ( input.hasNextLine() ) {
                        //If there's an input, exit the loop
                        userInput = input.nextLine();
                        status = false;
                    }
                }
            inputChoice = userInput;
            }
            //AI case
            else if ( checkAI == true ) {

                System.out.println("\nOur stupid smart AI is thinking about it's move...");
                sleep(2500);

                // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
			    // ThreadLocalRandom is EXCLUSIVE at its top value- so add 1 to make it inclusive, and its INCLUSIVE in its lower value (dont modify)
                randomValue = ThreadLocalRandom.current().nextInt(1, 10); //calculates a random number between 1 and 9
            
            inputChoice = String.valueOf(randomValue);
            }
            //else wouldn't make sense / not possible --> re-loop
            else {
                status = true;
            }

            switch (inputChoice) {

                case "(1,1)", "1": //Used double commas since the user input is declared as a string
                //If if is not fulfilled, it must re-loop
                status = true;
                    //Changing the inputChoice to be shown correctly to the user when AI selects a number position
                    inputChoice = "(1,1)";

                    //Positions in the front-end and back-end matrix
                    back0 = 0; back1 = 0;
                    front0 = 1; front1 = 3;

                    //Check back-end
                    if ( checkFull(back0, back1, checkAI) == false ) { //If back-end is empty 

                        //Fill both the back-end and the user game board
                        fillIn(back0, back1, front0, front1, chip);
                    //Exit loop command
                    status = false;
                    }
                break;

                case "(1,2)", "2":
                status = true;
                    inputChoice = "(1,2)";

                    //Positions
                    back0 = 0; back1 = 1;
                    front0 = 1; front1 = 9;

                    //Check back-end
                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        //Fill both boards
                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "(1,3)", "3":
                status = true;
                    inputChoice = "(1,3)";

                    back0 = 0; back1 = 2;
                    front0 = 1; front1 = 15;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "(2,1)", "4":
                status = true;
                    inputChoice = "(2,1)";

                    back0 = 1; back1 = 0;
                    front0 = 3; front1 = 3;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "(2,2)", "5":
                status = true;
                    inputChoice = "(2,2)";

                    back0 = 1; back1 = 1;
                    front0 = 3; front1 = 9;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "(2,3)", "6":
                status = true;
                    inputChoice = "(2,3)";

                    back0 = 1; back1 = 2;
                    front0 = 3; front1 = 15;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "(3,1)", "7":
                status = true;
                    inputChoice = "(3,1)";

                    back0 = 2; back1 = 0;
                    front0 = 5; front1 = 3;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "(3,2)", "8":
                status = true;
                    inputChoice = "(3,2)";

                    back0 = 2; back1 = 1;
                    front0 = 5; front1 = 9;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "(3,3)", "9":
                status = true;
                    inputChoice = "(3,3)";

                    back0 = 2; back1 = 2;
                    front0 = 5; front1 = 15;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);
                    status = false;
                    }
                break;

                case "exit":
                status = false;
                    launcher();
                break;

                default: 
                status = true;
                    //This is hacky fix
                
                	//I know it's not really a good way of avoiding the scanner from reading the first input
                    //(the enter key value carried over from the last scanner interaction), but it the only
                    //way I could figure how to do it cleanly and reliably
                    if (avoidFirstError == 0) break;
                    //To see the effect of this first error and to understand why I added it, just comment out the line above
                    //It is a brief error, but its not optimal whatsoever
                    
                    //Inform the user of an invalid input and loop
                    System.out.println("Please input a valid position in the correct format: (x,y)");
                    sleep(1500);
                break;
            }
        }

        clear();

        //Place the game mode again
        if ( checkAI == false ) {
            multiplayerTitle();
        }
        else if ( checkAI == true ) {
            aiTitle();
        }

        //Printing the modified user box
        for (int r = 0; r < userGameBoard.length; r++) {

            //selects each row to use its length
            for (int c = 0; c < userGameBoard[0].length; c++) {
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }

        //Show the last move
        posSelected("\n" + tag + " placed their chip on square " + inputChoice);
    }

    public static boolean checkWin (String tag, char chip) {

        //Full board- don't advance
        if (backendGameBoard[0][0] != ' ' && backendGameBoard[0][1] != ' ' && backendGameBoard[0][2] != ' ' &&
            backendGameBoard[1][0] != ' ' && backendGameBoard[1][1] != ' ' && backendGameBoard[2][2] != ' ' &&
            backendGameBoard[2][0] != ' ' && backendGameBoard[2][1] != ' ' && backendGameBoard[2][2] != ' ' )
        {
            System.out.println("\nThe board is full: Draw!");

            //times run is added to show scoreboard with draws
            timesRun++;
            draws++;
        
        return false;
        }

        //Diagonals (else try diagonals first)
        else if ( (backendGameBoard[0][0] == chip && backendGameBoard[1][1] == chip && backendGameBoard[2][2] == chip)
            ||    (backendGameBoard[0][2] == chip && backendGameBoard[1][1] == chip && backendGameBoard[2][0] == chip) )
        {

            System.out.println("\n" + tag  + " you won!");
                scoreboardUpdate( playerWinCheck(tag) ); //assign win to the correct user and return an identifier to update the scoreboard
            //add to times run
            timesRun++; //game has been won by someone

        return false;
        }
        //If no diagonals are full, try rows and columns

        //Rows and Columns
        for (int i = 0; i < backendGameBoard.length; i++) {

            //Either rows or columns
            if (( (backendGameBoard[i][0] == chip && backendGameBoard[i][1] == chip && backendGameBoard[i][2] == chip)
               || (backendGameBoard[0][i] == chip && backendGameBoard[1][i] == chip && backendGameBoard[2][i] == chip) ))
            {
                System.out.println("\n" + tag  + " you won!");
                    scoreboardUpdate( playerWinCheck(tag) );
                timesRun++;

            return false;
            }
        }
    //If no win situation is encountered
    return true;
    }

    public static void multiplayerTitle() {
        System.out.println("Multiplayer: 1v1" +
                         "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    //Multiplayer mode is for 1v1
    public static void multiplayer() {

        //Clean Arrays
        cleanBackEnd();
        cleanFrontEnd();

        int avoidFirstError = 0;

        clear();

        multiplayerTitle();

        //Only let users select their name once (while the name is empty)
        if ( p1tag.isEmpty() || p2tag.isEmpty() ) {
            
            if ( p1tag.isEmpty() ) {
                do {
                    do {
                        //Player 1 name (tag) selection
                            System.out.println("\nPlayer 1, whats your name?");
                            System.out.println("You may only choose your name once, choose wisely");
                                p1tag = input.nextLine();
        
                            //make sure the tag is not blank
                            if (p1tag.isBlank()) System.out.println("Error!" + "\nPlease choose a valid name");
        
                        //If blank re-loop until there's a valid name
                    } while (p1tag.isBlank());

                    //If both are the same, throws error
                    //use .equals() to compare strings
                    if (p1tag.equals(p2tag)) System.out.println("Error!" + "\nPlease choose another name");
                } while (p1tag.equals(p2tag));
            }

            if ( p2tag.isEmpty() ) {
                //do while to check that the chip is not already being utilized
                do {
                    do {
                        //Player 2 tag selection
                        System.out.println("\nPlayer 2, whats your name?");
                        System.out.println("You may only choose your name once, choose wisely");
                            p2tag = input.nextLine();

                            if (p2tag.isBlank()) System.out.println("Error!" + "\nPlease choose a valid name");
                    } while (p2tag.isBlank());

                    if (p2tag.equals(p1tag)) System.out.println("Error!" + "\nPlease choose another name");
                } while (p2tag.equals(p1tag));
            }
        }

        if ( p1chip == ' ' ||  p2chip == ' ' ) {
            //Player chip selection
            System.out.println("\nYou have these chips to choose from:" +
                               "\nX, 0, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y, Z, +, *, -, @ or 1");
            if ( p1chip == ' ' ) {
                //Same check as with the tag to avoid repeat use
                do {
                    //Player 1 chip selection + false for multiplayer mode
                    p1chip = chipSelector( p1tag, false );

                    //If both are the same, throws error
                    if (p1chip == p2chip) System.out.println("Error!" + "\nPlease choose another chip");
                } while (p1chip == p2chip);
            }
            
            if ( p2chip == ' ' ) {
                do {
                    //Player 2 chip selection + false for multiplayer mode
                    p2chip = chipSelector( p2tag, false );

                    if (p2chip == p1chip) System.out.println("Error!" + "\nPlease choose another chip");
                } while (p2chip == p1chip);
            }
        }

        clear();
 
        multiplayerTitle();

        //First print of the empty user end game board
        for (int r = 0; r < userGameBoard.length; r++) {
            for (int c = 0; c < userGameBoard[0].length; c++) {
                //+ "(" + r + "," + c + ")" + "\t"
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }

        //Actual game is played here
        status = true;
        while (status) {

            //User 1 input
            chipPlacer(p1tag, p1chip, avoidFirstError);
                //add to correctly display error messages after first "error" is thrown
                avoidFirstError++;

            //Update status to continue to re-loop until status = false
            status = checkWin(p1tag, p1chip);
                //Check if player 1 has already won
                if (status == false) break;

            //User 2 input
            chipPlacer(p2tag, p2chip, avoidFirstError);

            status = checkWin(p2tag, p2chip);
                //Check if player 2 has won
                if (status == false) break;
        }

        reRun();
    }

    public static void aiTitle() {
        System.out.println("AI Deathmatch against a dumb random computer" +
                         "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    //AI mode is to play a game against a dumb (random) machine
    public static void ai() {

        //Clean Arrays
        cleanBackEnd();
        cleanFrontEnd();

        int avoidFirstError = 0;

        clear();

        aiTitle();
        
        //Always prompt the user which player they would like to use
        String playerSelection;
        status = true;
        while (status) {

            System.out.println("\nWould you like to play as Player 1 or Player 2");
                playerSelection = input.nextLine();

            switch (playerSelection) {
                case "Player 1", "P1", "p1", "1": 
                status = false; //exit loop
                    System.out.println("You have chosen Player 1");
                        playerAssignment = 1;
                    //Check if the player has already been named
                    if ( p1tag.length() != 0 ) {
                        pXtag = p1tag;
                        pXchip = p1chip;
                    }

                break;

                case "Player 2", "P2", "p2", "2":
                status = false;
                    System.out.println("You have chosen Player 2");
                        playerAssignment = 2;
                    if ( p2tag.length() != 0 ) {
                        pXtag = p2tag;
                        pXchip = p2chip;
                    }
                
                break;

                default:
                status = true; //re-loop
                    System.out.println("Please choose a valid player in the format: Player X, PX, pX or X");
                break;
            }
        }
            
        if ( p1tag.isEmpty() || p2tag.isEmpty() ) {

            status = true;
            while (status) {
                do {
                    //Player name (tag) selection
                    System.out.println("\nWhat's your name?");
                    System.out.println("You may only choose your name once, choose wisely");
                        pXtag = input.nextLine();
    
                        if (pXtag.trim().isBlank()) System.out.println("Error!" + "\nPlease choose a valid name");
                } while (pXtag.isBlank());

                if (playerAssignment == 1) {

                    if (pXtag.equals(p2tag)) System.out.println("Error!" + "\nPlease choose a valid name");
                    else status = false;
                } else if (playerAssignment == 2) {

                    if (pXtag.equals(p1tag)) System.out.println("Error!" + "\nPlease choose a valid name");
                    else status = false;
                } 
            }
        }

        if ( p1chip == ' ' || p2chip == ' ' ) {

            status = true;
            while (status) {
                do {
                    //Player chip selection
                    System.out.println("\nYou have these chips to choose from:" +
                                       "\nX, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y, Z, +, *, -, @ or 1");

                    //Player 1 chip selection + true for AI mode
                    pXchip = chipSelector( pXtag, true );
                } while (pXchip == ' ');

                if (playerAssignment == 1) {

                    if (pXchip == p2chip) System.out.println("Error!" + "\nPlease choose a valid chip");
                    else status = false;
                } else if (playerAssignment == 2) {

                    if (pXchip == p1chip) System.out.println("Error!" + "\nPlease choose a valid chip");
                    else status = false;
                }
            }
        }

        //Clear selections out to start the game
        clear();

        aiTitle();

        //First print of the empty user end game board
        for (int r = 0; r < userGameBoard.length; r++) {
            for (int c = 0; c < userGameBoard[0].length; c++) {
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }

        //The actual game is run here
        status = true;
        while (status) {

            if (playerAssignment == 1) {

                //Data assignment in case there weren't previous Player 1 or 2 data
                p1tag = pXtag;
                p1chip = pXchip;
                
                //Player input
                chipPlacer(p1tag, p1chip, avoidFirstError); avoidFirstError++; //added to prompt errors after the first scanner (enter key carry over) input

                //Update status to latest
                status = checkWin(p1tag, p1chip);
                    //Check if player X has won
                    if (status == false) break;
    
            } else if (playerAssignment == 2) {

                p2tag = pXtag;
                p2chip = pXchip;

                chipPlacer(p2tag, p2chip, avoidFirstError); avoidFirstError++;

                status = checkWin(p2tag, p2chip);
                    if (status == false) break;

            } else { System.out.println("We couldn't determine which player was selected, so the result of this game was lost :("); sleep(2000); /* added for catastrophic error */ }

            //AI input
            chipPlacer(ai, aiChip, avoidFirstError);

            status = checkWin(ai, aiChip);
                //Check if AI has won
                if (status == false) break;
        }

        reRun();
    }

    //reRun is used to ask if the user wants to restart the game
    public static void reRun() {
        
        //Declared beforehand to avoid looped string declaration
        String userAns;

        //status to true again to enable while loop
        status = true;
        while (status) {

            //Asking the user if they would like to play another game
            System.out.println("\n\nWould you like to play another game?" + "\nType in Yes or No");
            //used toLowerCase() to format the users answer
                userAns = input.nextLine().toLowerCase();

            switch (userAns) {

                case "yes", "y" -> {
                status = false;

                    System.out.println("\nOkay, coming right up!");
                    sleep(1500);
                    clear();

                    //re-launch the game
                    launcher();
                break;
                }

                case "no", "n" -> {
                status = false;
                    //Thank the user and ends the program
                    System.out.println("\nOkay, thanks for playing!");
                    sleep(1500);
                    clear();
                break;
                }

                default -> {
                status = true;
                    System.out.println("\nSorry, I didn't catch that. Please try again:");
                break;
                }
            }
        }
    }

    public static void main(String[] args) {

        launcher();
    }
}
