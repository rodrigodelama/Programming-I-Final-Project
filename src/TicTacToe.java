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

    //GLOBAL DECLARATIONS
    //Declared static Scanner for permanent usage, avoiding a resource leak if not closed later on
    static Scanner input = new Scanner(System.in);

    //Declared static boolean for global use in while loops
    //Boolean to check the while condition- if true, loops
    static boolean status = true;

    //global enter value to remain empty
    static String enterToContinue;

    //adapt clear() for eclipse
    public static void clear() {
        //https://www.edureka.co/community/4668/how-to-clear-the-console-in-java
        //Command used to clear the console-- for later use to reload the game board
        //ANSI escape code
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void title() {
        //Title method to avoid repetition
        System.out.println("Hello, welcome to TicTacToe JAVA edition!" +
                           "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    public static void sleep(int i) {
        //https://stackoverflow.com/questions/2517022/wait-function-in-java
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

    //Scoreboard presented to the user only if times run (with wins)> 1
    public static void scoreboard () {

        if (timesRun > 0) {
            System.out.println("\nSCOREBOARD\n");

            System.out.println("Here's how the scoreboard is looking...\n");

            //time in miliseconds
            sleep(1500);

            System.out.println("Player 1 wins: " + p1Wins +
                             "\nPlayer 2 wins: " + p2Wins +
                             "\nAI wins: " + aiWins +
                             "\nThere have been " + draws + " draws");

            //structure to determine the leader
            String leader = " ";

            if ( p1Wins > p2Wins && p1Wins > aiWins ) {

                leader = p1tag; //assign leader variable to player X
            }
            else if ( p2Wins > p1Wins && p2Wins > aiWins ) {

                leader = p2tag;
            }
            else if ( aiWins > p1Wins && aiWins > p2Wins ) {

                leader = ai;
            }
            //display only if there's actually someone winning
            if (leader != " ") System.out.println("\n" + leader + " you're dominating!");
            
            //wait and display loading animation
            sleep(1500);
                System.out.println(); //spacing
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
            sleep(1000);
            }

            //Press enter when the user is ready to continue
            System.out.println("\n\nPress \"Enter\" when you're ready");
                enterToContinue = input.nextLine();
        }
    }

    static int explanation;
    static int launcherRun;
    public static void launcher() {

        //Clear the users console before runtime
        clear();

        //Present title
        title();

        //if we have already seen the scoreboard dont show it again unless requestes by the user, see below
        if (launcherRun == 0) {
            //if timesRun++ was actuated in a previous game, scoreboard should run
            scoreboard();
        }

        //Declaring before the loop to not redeclare the variables every time the while loop runs
        String explanationAns;
        
        if (explanation == 0) {
            //status = false making sure while loop always engages after rerun
            status = true;
            while (status) {

                //Asking the user if they want an explanation as to how the game works
                System.out.println("\nWould you like a quick explanation on how to play?");
                //System.out.println("(You will only be able to see the instructions one time," +
                //                   " but you may go forth and check them out later)");
                    explanationAns = input.nextLine().toLowerCase();
    
                switch (explanationAns) {
                    case "yes", "y" -> {
                        
                        //status to false because we want to break off from the while loop after a valid input has been detected
                        status = false;
                        System.out.println("\nThis is how the game board looks:\n");
                        System.out.println("╔═════╦═════╦═════╗" + 
                                         "\n║  1  ║  2  ║  3  ║" +
                                         "\n╠═════╬═════╬═════╣" +
                                         "\n║  4  ║  5  ║  6  ║" +
                                         "\n╠═════╬═════╬═════╣" +
                                         "\n║  7  ║  8  ║  9  ║" +
                                         "\n╚═════╩═════╩═════╝" );
    
                        System.out.println("To place a chip on the board, just type a position's number and hit \"Enter\"" +
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
    
                        //dont show the explanation in the future
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
                        //status = true to reloop
                        status = true;
                            System.out.println("Sorry, I didn't catch that. Please type yes or no");
                    break;
                    }
                }
            }
        }

        clear();

        //Mantain the title
        title();

        System.out.println("\nIn which mode would you like to play?");

        //Present the available game modes
        System.out.println("(A) Multiplayer: 1 versus 1\n" +
                           "(B) AI: a game against a random dumb computer");
        
        //Declared outside the loop to avoid redeclaration
        String gameModeAns;

        //Reusing the same global static status variable
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
                    System.out.print("\nSome one on one action comming your way!");

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
                    //gives the user the possibility to go back for an explanation
                    explanation = 0;
                    launcherRun = 1;
                    launcher();
                    break;
                }

                case "exit" -> {
                    status = false;
                        System.exit(0);
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

    //Declared a global static game variables

    //Backend game board array
    static char[][] backendGameBoard = {
                                        {' ', ' ', ' '},
                                        {' ', ' ', ' '},
                                        {' ', ' ', ' '}
    };

    public static void cleanBackEnd() {
        // clean the backend matrix
        for (int r = 0; r < backendGameBoard.length; r++) {

            for (int c = 0; c < backendGameBoard[0].length; c++) {

                backendGameBoard[r][c] = ' ';
            }
        }
    }

    //User facing board (front end game board)
    // 5 spaces between columns
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
//This method is fine end effective, but attempt to put positions in an array

        //easy clean
        //could be done with arrays for future userGameBoard size flexibility, but I couldn't figure it out

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

    //Game resources
    //Player names
    static String p1tag, p2tag;
    static final String ai = "AI";

    //Player chips
    static char p1chip, p2chip;
    static final char aiChip = 'X';

    public static char chipSelector(String tag, boolean ai) {

        char chip = ' ';

        status = true;
        while (status) {

            //Chip selection
            System.out.println("\n" + tag + " select your chip:");
                chip = input.next().charAt(0);

                //convert char to UpperCase
                chip = Character.toUpperCase(chip);

            switch (chip) {

                case 'X','0','A','B','C','D','E','F','G','H','I','J','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','Y','Z','+','*','-','@','1' -> {

                    //Is the game mode AI?
                    if (ai == true && chip == 'X') {
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
                System.out.println("That space is alreay taken..." +
                                   "\nTry again!");
            }
            else { System.out.println("\nOoops, our AI selected a place that was taken, our bad"); }
        }
    
    return taken;
    }

    public static void fillIn(int back0, int back1, int front0, int front1, char chip) {

        //Place chips
        backendGameBoard[back0][back1] = chip;
        userGameBoard[front0][front1] = chip;
    }
    
    //Check what position was chosen
    public static void posSelected(String num) {

        System.out.println(num);
    }

    public static void chipPlacer(String tag, char chip, int avoidFirstError) {

        //add ai differentiator
        //boolean is always initialized as false
        boolean checkAI = false;
        if (tag == ai) checkAI = true;

        //defined outside to avoid redeclarations inside loop
        //ai value for generator
        int randomValue;
    
        //real person input
        String userInput = "";

        //global input initialized in case no codition was met
        String inputChoice = "";

        //implementing chip placement
        int back0, back1, front0, front1;
        status = true;
        while (status) {

            //Real player case
            if ( checkAI == false ) {

                System.out.println("\n" + tag + " in what square would you like to place your chip?"); //1-9

                //couldnt figure out how to avoid the error of the scanner reading "enter" value from last input
                //I made a counter to avoid displaying the first error message

                //Wait for the next input
                while ( input.hasNext("\n") ) {
                    input.nextLine();
                }

                while (status) {
                    status = true;

                    if ( input.hasNextLine() ) {
                        //if there's an input, exit the loop
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
			    // ThreadLocalRandom is EXCLUSIVE at its top value- so add 1 to make it inclusive
                // ThreadLocalRandom is INCLUSIVE in its lower value
                randomValue = ThreadLocalRandom.current().nextInt(1, 10);
                //calculates a random number bewteen 1 and 9
            
            inputChoice = String.valueOf(randomValue);
            }
            //else wouldnt make sense / not possible --> reloop
            else {
                status = true;
            }

            switch (inputChoice) {

                case "1": //Used double commas since the user input is declared as a string
                //If if is not fulfilled, it must reloop
                status = true;

                    //Positions in the frontend and backend matrix
                    back0 = 0; back1 = 0;
                    front0 = 1; front1 = 3;

                    //Check backend
                    if ( checkFull(back0, back1, checkAI) == false ) { //If backend is empty 

                        //Fill both the backend and the user game board
                        fillIn(back0, back1, front0, front1, chip);

                    //Exit loop command
                    status = false;
                    }
                break;

                case "2":
                status = true;

                    //Positions
                    back0 = 0; back1 = 1;
                    front0 = 1; front1 = 9;

                    //Check backend
                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        //Fill both boards
                        fillIn(back0, back1, front0, front1, chip);

                    status = false;
                    }
                break;

                case "3":
                status = true;

                    back0 = 0; back1 = 2;
                    front0 = 1; front1 = 15;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);

                    status = false;
                    }
                break;

                case "4":
                status = true;

                    back0 = 1; back1 = 0;
                    front0 = 3; front1 = 3;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);

                    status = false;
                    }
                break;

                case "5":
                status = true;

                    back0 = 1; back1 = 1;
                    front0 = 3; front1 = 9;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);

                    status = false;
                    }
                break;

                case "6":
                status = true;

                    back0 = 1; back1 = 2;
                    front0 = 3; front1 = 15;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);

                    status = false;
                    }
                break;

                case "7":
                status = true;

                    back0 = 2; back1 = 0;
                    front0 = 5; front1 = 3;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);

                    status = false;
                    }
                break;

                case "8":
                status = true;

                    back0 = 2; back1 = 1;
                    front0 = 5; front1 = 9;

                    if ( checkFull(back0, back1, checkAI) == false ) { 

                        fillIn(back0, back1, front0, front1, chip);

                    status = false;
                    }
                break;

                case "9":
                status = true;

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

                    //hacky way of fixing it
                    if (avoidFirstError == 0) break;

                    System.out.println("Please input a valid location, 1-9");

                    //Inform the user of an invalid input and loop
                    sleep(1500);
                break;
            }
        }

        clear();

        //Placing the game mode once again
        if ( checkAI == false ) {
            multiplayerTitle();
        }
        else if ( checkAI == true ) {
            aiTitle();
        }
        else {/* ignore don't print */}

        //Printing the modified user box
        for (int r = 0; r < userGameBoard.length; r++) {

            //selects the row to grab its length
            for (int c = 0; c < userGameBoard[0].length; c++) {
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }

        //Check chosen place both AI and Player
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

        int avoidFirstError = 0;

        clear();

        //Informing the user about theur game mode selection 
        multiplayerTitle();

        //Player 1 name (tag) selection
        System.out.println("\nPlayer 1, whats your name?");
            p1tag = input.nextLine();
        
        //do while to check that the chip is not already being utilized
        do {
            //Player 2 tag selection
            System.out.println("\nPlayer 2, whats your name?");
                p2tag = input.nextLine();

            //If both are the same, throws error
            if (p2tag == p1tag) System.out.println("Error!" + "\nPlease choose another name");

        } while (p2tag == p1tag); //Do while that condition is true

        //Player chip selection
        System.out.println("\nGamers, you have these chips to choose from:" +
                            "\nX, 0, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y, Z, +, *, -, @ or 1");
        
        //Player 1 chip selection + false for multiplayer mode
        p1chip = chipSelector( p1tag, false );
        
        //Same check as with the tag to avoid repeat use
        do {
            //Player 2 chip selection + false for multiplayer mode
            p2chip = chipSelector( p2tag, false );

            //If both are the same, throws error
            if (p2chip == p1chip) System.out.println("Error!" + "\nPlease choose another chip");

        } while (p2chip == p1chip);

        clear();

        //Game mode selection 
        multiplayerTitle();

        //First print of the empty user end game board
        for (int r = 0; r < userGameBoard.length; r++) {
            for (int c = 0; c < userGameBoard[0].length; c++) {
                //+ "(" + r + "," + c + ")" + "\t"
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }

        status = true;
        while (status) {

            //User 1 input
            chipPlacer(p1tag, p1chip, avoidFirstError);
            
            //add to display error messages after first input
            avoidFirstError++;

            //Check if player 1 has won
            status = checkWin(p1tag, p1chip);

                //Check if player 1 has already won
                if (status == false) break;

            //User 2 input
            chipPlacer(p2tag, p2chip, avoidFirstError);

            //Check if player 2 has won
            status = checkWin(p2tag, p2chip);

                //Check if player 2 has won-- not necessary since its the last move
                if (status == false) break;
        }
        
        //Clean Arrays for possible next play
        cleanBackEnd();
        cleanFrontEnd();
        
        //times run for scoreboard displayed is taken into account inside

        reRun();
    }

    public static void aiTitle() {
        System.out.println("AI Deathmatch against a dumb random computer" +
                         "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    //ai mode is to play a game against a dumb (random) machine
    public static void ai() {

        int avoidFirstError = 0;

        clear();

        //Game mode salection
        aiTitle();
        
        //Player 1 name (tag) selection
        System.out.println("\nPlayer 1, whats your name?");
            p1tag = input.nextLine();

        //Player chip selection
        System.out.println("\nYou have these chips to choose from:" +
                            "\n0, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y, Z, +, *, -, @ or 1");
        
        //Player 1 chip selection + ture for ai mode
        p1chip = chipSelector( p1tag, true );

        //Clear selections out to start the game
        clear();

        //Game mode selection 
        aiTitle();

        //First print of the empty user end game board
        for (int r = 0; r < userGameBoard.length; r++) {
            for (int c = 0; c < userGameBoard[0].length; c++) {
                //+ "(" + r + "," + c + ")" + "\t"
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }

        status = true;
        while (status) {

            //User 1 input
            chipPlacer(p1tag, p1chip, avoidFirstError);

            avoidFirstError++;

            //Check if player 1 has won
            status = checkWin(p1tag, p1chip);

                //Check if player 1 has already won
                if (status == false) break;

            //AI input
            chipPlacer(ai, aiChip, avoidFirstError);

            //Check if AI has won
            status = checkWin(ai, aiChip);

                //Check if AI has won-- not necessary since its the last move
                if (status == false) break;
        }

        cleanBackEnd();
        cleanFrontEnd();

        reRun();
    }

    //reRun is used to ak if the user wants to restart the game
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

                case "yes", "yea", "yuh", "ye", "yessir", "y" -> {
                    status = false;

                    System.out.println("\nOkay, comming right up!");

                    sleep(1500);
                    clear();

                    //relaunch the game
                    launcher();

                break;
                }

                case "no", "nah", "nope", "n" -> {
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
