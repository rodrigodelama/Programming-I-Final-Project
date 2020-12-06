//Rodrigo De Lama
//100451775@alumnos.uc3m.es
//@RDLF11
//GitHub repository page- https://github.com/RDLF11/Programming-I-Final-Project

//Scanner imported to read user inputs
import java.util.Scanner;

//Used to generate random numbers without JAVA Math class
import java.util.concurrent.ThreadLocalRandom;

//delete below
//We're going for CPU mode bois - gonna get a 100%

public class TicTacToe {

    //GLOBAL DECLARATIONS
    //Declared static Scanner for permanent usage, avoiding a resource leak if not closed later on
    static Scanner input = new Scanner(System.in);

    //Declared static boolean for global use in while loops
    //Boolean to check the while condition- if true, loops
    static boolean status = true;

    //global enter value to remain empty
    static String enter;

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
        System.out.println("\n\nHello, welcome to TicTacToe JAVA edition!" +
                           "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    public static void sleep(int i) {
        //https://stackoverflow.com/questions/2517022/wait-function-in-java
        try {Thread.sleep(i);} catch(InterruptedException intrx) {/* handle the exception */}
    }

    //runtime counter, resets on program restart
    static int timesRun = 0;

    //IMPLEMENT SCOREBOARD
    //Player wins
    static byte p1Wins = 0, p2Wins = 0, aiWins = 0;

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

    //scoreboard presented to the user only if times run (with wins)> 1
    public static void scoreboard () {

        if (timesRun == 0);
        else {
            System.out.println("\nSCOREBOARD\n");

            System.out.println("Here's how the scoreboard is looking...\n");

            //time in miliseconds
            sleep(1500);

            System.out.println("Player 1 wins: " + p1Wins +
                             "\nPlayer 2 wins: " + p2Wins +
                             "\nAI wins: " + aiWins);

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
            System.out.println("\n" + leader + " you're dominating!\n");
        }
    }

    //IMPORTANT
    public static void launcher() {

        //Clear the users console before runtime
        clear();

        //Present title
        title();

        //if runtime++ was actuated, scoreboard should run
        scoreboard();
        
        //Declaring before the loop to not redeclare the variables every time the while loop runs
        String explanationAns;
        
        //Making sure while loop always engages after rerun
        status = true;
        while (status) {

            //Asking the user if they want an explanation as to how the game works
            System.out.println("\nWould you like a quick explanation on how to play?");
                explanationAns = input.nextLine().toLowerCase();

            switch (explanationAns) {
                case "yes", "yep", "yas", "sure", "y" -> {
                    
                    //status to false because we want to break off from the while loop
                    //after a valid input has been detected
                    status = false;
                    System.out.println("\nThis is how the game board looks:\n");
                    System.out.println("╔═════╦═════╦═════╗" + 
                                     "\n║  1  ║  2  ║  3  ║" +
                                     "\n╠═════╬═════╬═════╣" +
                                     "\n║  4  ║  5  ║  6  ║" +
                                     "\n╠═════╬═════╬═════╣" +
                                     "\n║  7  ║  8  ║  9  ║" +
                                     "\n╚═════╩═════╩═════╝" );

                    System.out.println("This is your game board. To place a chip on the board, just type a position's number and hit \"Enter\"" +
                                     "\nTo win the game, get three chips in a row, column, or diagonally and the win is yours!\n");

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

                    //Press enter when the user is ready to continue
                    System.out.println("\nPress \"Enter\" when you're ready");
                        enter = input.nextLine();

                break;
                }

                case "no", "nope", "nah", "n" -> {
                    status = false;
                        System.out.println("\nOkay, lets get onto it!\n");

                break;
                }

                default -> {
                    //status = true to rerun loop
                    status = true;
                        System.out.println("Sorry, I didn't catch that. Please type yes or no");

                break;
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
        
        //Declared out of the loop to avoid redeclaration
        String gameModeAns;

        //Reusing the same global static status variable
        status = true;
        while (status) {

            //Asking the user if they want an explanation as to how the game works
            System.out.println("\n\nType A or B to select the game mode:");
                gameModeAns = input.nextLine().toLowerCase();
            
            switch (gameModeAns) {
                
                //Multiplayer
                case "a" -> {
                    status = false;
                    System.out.print("\nSome one on one action comming your way!\n\n\n");

                    sleep(1000);
                    
                    multiplayer();

                break;
                }

                //AI mode (CPU Mode in my game)
                case "b" -> {
                    status = false;
                    System.out.println("\nGet ready to be destroyed by our super dumb but kinda smart AI...\n\n\n");

                    sleep(1000);

                    ai();

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
    //Declaring empty for reruns-- so that upong reloading the code it always starts empty
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

    //User facing board
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
//This method is fine end effective, attempt to put positions in an array

//write an array with the positions to quickly go over the replacing of the blank spacess
/*
        final int[][] emptyUserGameBoard = {
                                            {1, 3},
                                            {1, 9},
                                            {1, 15},
                                            {3, 3},
                                            {3, 9},
                                            {3, 15},
                                            {5, 3},
                                            {5, 9},
                                            {5, 15}
        };

        //declared vars for subtituting in backendGameBoard positions
        int i, j;

        for (int r = 0; r < emptyUserGameBoard.length; r++) {

            //assigning position value to our variable to clean
            i = emptyUserGameBoard[r][0];
            j = emptyUserGameBoard[r][1];

            backendGameBoard[i][j] = ' ';
            
//            for (int c = 0; c < emptyUserGameBoard[0].length; c++) {
//                
//                for (int k = 1; k < emptyUserGameBoard[0].length; k++) {

//                }
//            }
            
        }
*/        
        //easy clean
        // clean the frontend matrix
        //case 1
        userGameBoard[1][3]  = ' ';
        //case 2
        userGameBoard[1][9]  = ' ';
        //case 3
        userGameBoard[1][15] = ' ';
        //case 4
        userGameBoard[3][3]  = ' ';
        //case 5
        userGameBoard[3][9]  = ' ';
        //case 6
        userGameBoard[3][15] = ' ';
        //case 7
        userGameBoard[5][3]  = ' ';
        //case 8
        userGameBoard[5][9]  = ' ';
        //case 9
        userGameBoard[5][15] = ' ';
   
    }


    //Game resources
    //Player names
    static String p1tag, p2tag;
    static final String ai = "AI";

    //Player chips
    static char p1chip, p2chip;
    static final char aiChip = 'X';


    //Extrapolated method to avoid repetition
    public static char chipSelector(String tag) {
        char chip = ' ';
        status = true;
        while (status) {
            //Chip selection
            System.out.println("\n" + tag + " select your chip:");
                chip = input.next().charAt(0);

                //convert character to UpperCase
                chip = Character.toUpperCase(chip);

//            char[] possibleChips = {'X','0','A','B','C','D','E','F','G','H','I','J','K','L','M',
//            'N','O','P','Q','R','S','T','U','V','W','Y','Z','+','*','-','@','1'};

//            for (int i = 0; i < possibleChips.length; i++) {
//                chips = possibleChips[i];
//            }

            switch (chip) {
                //Maybe try to put in the options inside of an array
                //case 'X','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Y','Z','+','*','-','@','1' -> {

                case 'X','0','A','B','C','D','E','F','G','H','I','J','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','Y','Z','+','*','-','@','1' -> {
                    status = false;
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

/*
    //boolean to feed to chip placer to delete X from the array of possible values
    static boolean ISai;

    public static void chipPlacingMethod(int b, char chip, int frontendRow, int frontendColumn, boolean ai) {

        if (backendGameBoard[b][b] == ' ') {
            backendGameBoard[b][b] = chip;
            userGameBoard[5][15] = chip; 
        }
        else {

            //if were working with ai
            if (ai = false) {
                System.out.println("That space is alreay taken..." +
                                           "\nTry again!");
            }
            
            status = true;
            break;
        }
        
    }
*/

    public static void chipPlacer(String tag, char chip) {

        //add ai differentiator
        boolean checkAI = false;
        if (tag == ai) checkAI = true;

        //Declared outside to avoid redeclarations in loop
        String userInput;

        //implementing loop for one person placement
        status = true;
        while (status) {

            System.out.println("\n" + tag + " in what square would you like to place your chip?");
                //1-9
                userInput = input.nextLine();
                
//ignore empty spaces, wait for actual input

            switch (userInput) {

                case "1": //Used double commas since tghe user input is declared as a string- 
                status = false;

                    //Extrapolate to a 4 var function with the backend and frontend positions

                                                                //row1
                    //vars will be positions inside an array { {{0,0},{1,3}} }
                    //maybe even 3d


                    //extrapolate this
                    //below

                    //Check if the place is not taken already
                    if (backendGameBoard[0][0] == ' ') {
                        //If empty, fill it
                        backendGameBoard[0][0] = chip;
                        userGameBoard[1][3] = chip;
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                    //above

                case "2":
                status = false;
                    if (backendGameBoard[0][1] == ' ') {
                        backendGameBoard[0][1] = chip;
                        userGameBoard[1][9] = chip;
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case "3":
                status = false;
                    if (backendGameBoard[0][2] == ' ') {
                        backendGameBoard[0][2] = chip;
                        userGameBoard[1][15] = chip;
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case "4":
                status = false;
                    if (backendGameBoard[1][0] == ' ') {
                        backendGameBoard[1][0] = chip;
                        userGameBoard[3][3] = chip; 
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case "5":
                status = false;
                    if (backendGameBoard[1][1] == ' ') {
                        backendGameBoard[1][1] = chip;
                        userGameBoard[3][9] = chip; 
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case "6":
                status = false;
                    if (backendGameBoard[1][2] == ' ') {
                        backendGameBoard[1][2] = chip;                        
                        userGameBoard[3][15] = chip; 
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case "7":
                status = false;
                    if (backendGameBoard[2][0] == ' ') {
                        backendGameBoard[2][0] = chip;
                        userGameBoard[5][3] = chip; 
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case "8":
                status = false;
                    if (backendGameBoard[2][1] == ' ') {
                        backendGameBoard[2][1] = chip;
                        userGameBoard[5][9] = chip; 
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case "9":
                status = false;
                    if (backendGameBoard[2][2] == ' ') {
                        backendGameBoard[2][2] = chip;
                        userGameBoard[5][15] = chip; 
                        break;
                    }
                    else {
                        if (checkAI == true) {
                            //Do nothing
                        break;
                        }
                        else {
                            System.out.println("That space is alreay taken..." +
                                               "\nTry again!");
                            //Invalid option, reloop switch
                            status = true;
                        break;
                        }
                    }

                case " ":        
                //if the inputed value isempty, reloop
                status = true;
                break;

                default: {
                status = true;
                    System.out.println("Please input a valid location, 1-9");

                    //Inform the user of an invalid input and loop
                    sleep(1500);

                break;
                }
            }
        }

        clear();

        //Placing the game mode once again
        multiplayerTitle();

        //Printing the modified user box
        for (int r = 0; r < userGameBoard.length; r++) {

            //selects the row to grab its length
            for (int c = 0; c < userGameBoard[0].length; c++) {
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }
        
    }

//Attempt to do it by scanning arrays

    public static boolean checkWin (String tag, char chip) {
            
//write an array with possible win values

        //try to do things with rows and columns

        //every time a users array, row or column is filles (3/3), or diagonals i = j
        //array for row and column positions occipued

        //Full board- don't advance
        if (backendGameBoard[0][0] != ' ' && backendGameBoard[0][1] != ' ' && backendGameBoard[0][2] != ' ' &&
            backendGameBoard[1][0] != ' ' && backendGameBoard[1][1] != ' ' && backendGameBoard[2][2] != ' ' &&
            backendGameBoard[2][0] != ' ' && backendGameBoard[2][1] != ' ' && backendGameBoard[2][2] != ' ' )
        {
            System.out.println("\nThe board is full: Draw!");
            //no one won the game so we wont add to timesRun
        
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
                    scoreboardUpdate( playerWinCheck(tag) ); //assign win to the correct user and return an identifier to update the scoreboard
                //add to times run
                timesRun++; //game has been won by someone

            return false;
            }
        }

        //If none reloop
        return true;
    }

    public static void multiplayerTitle() {
        System.out.println("Multiplayer: 1v1" +
                         "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    //Multiplayer mode is for 1v1
    public static void multiplayer() {

        clear();

        //Informing the user about theur game mode selection 
        multiplayerTitle();

        //Player 1 name (tag) selection
        System.out.println("\nPlayer 1, whats your tag?");
            p1tag = input.nextLine();
        
        //do while to check that the chip is not already being utilized
        do {
            //Player 2 tag selection
            System.out.println("\nPlayer 2, whats your tag?");
                p2tag = input.nextLine();

            //If both are the same, throws error
            if (p2tag == p1tag) System.out.println("Error!" + "\nPlease choose another tag");

        } while (p2tag == p1tag); //Do while that condition is true

        //Player chip selection
        System.out.println("\nGamers, you have these chips to choose from:" +
                            "\nX, 0, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y, Z, +, *, -, @ or 1");
        
        //Player 1 chip selection 
        p1chip = chipSelector( p1tag );
        
        //Same check as with the tag to avoid repeat use
        do {
            //Player 2 chip selection
            p2chip = chipSelector( p2tag );

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
            chipPlacer(p1tag, p1chip);

            //Check if player 1 has won
            status = checkWin(p1tag, p1chip);

                //Check if player 1 has already won
                if (status == false) break;

            //User 2 input
            chipPlacer(p2tag, p2chip);

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

    public static void aiRandomPlacer(String tag, char chip) {

        //defined outside to avoid redeclarations
        int randomValue;
        String randomValueSTR;

        //implementing loop for random chip placement
        status = true;
        while (status) {

            System.out.println("Our stupid smart AI is thinking about it's move...");
            sleep(2500);

            // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
			// nextInt is EXCLUSIVE at its top value- so add 1 to make it inclusive
            // nextInt is INCLUSIVE in its lower value
            randomValue = ThreadLocalRandom.current().nextInt(1, 10);
                //calculates a random number bewteen 1 and 9
            
            randomValueSTR = String.valueOf(randomValue);

            switch (randomValueSTR) {

                case "1": //Integer cases
                status = false;
                    //Check if the place is not taken already
                    if (backendGameBoard[0][0] == ' ') {
                        backendGameBoard[0][0] = chip;
                        userGameBoard[1][3] = chip;
                        break;
                    }
                    else {
                        //reloop
                        status = true;
                        break;
                    }

                case "2":
                status = false;
                    if (backendGameBoard[0][1] == ' ') {
                        backendGameBoard[0][1] = chip;
                        userGameBoard[1][9] = chip;
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }

                case "3":
                status = false;
                    if (backendGameBoard[0][2] == ' ') {
                        backendGameBoard[0][2] = chip;
                        userGameBoard[1][15] = chip;
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }

                case "4":
                status = false;
                    if (backendGameBoard[1][0] == ' ') {
                        backendGameBoard[1][0] = chip;
                        userGameBoard[3][3] = chip; 
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }

                case "5":
                status = false;
                    if (backendGameBoard[1][1] == ' ') {
                        backendGameBoard[1][1] = chip;
                        userGameBoard[3][9] = chip; 
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }

                case "6":
                status = false;
                    if (backendGameBoard[1][2] == ' ') {
                        backendGameBoard[1][2] = chip;                        
                        userGameBoard[3][15] = chip; 
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }

                case "7":
                status = false;
                    if (backendGameBoard[2][0] == ' ') {
                        backendGameBoard[2][0] = chip;
                        userGameBoard[5][3] = chip; 
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }

                case "8":
                status = false;
                    if (backendGameBoard[2][1] == ' ') {
                        backendGameBoard[2][1] = chip;
                        userGameBoard[5][9] = chip; 
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }

                case "9":
                status = false;
                    if (backendGameBoard[2][2] == ' ') {
                        backendGameBoard[2][2] = chip;
                        userGameBoard[5][15] = chip; 
                        break;
                    }
                    else {
                        status = true;
                        break;
                    }
                
                case " ":
                //if empty reloop
                status = true;
                break;

                default: {
                status = true;
                break;
                }
            }
        }

        clear();

        //Placing the game mode once again
        aiTitle();

        //Printing the modified user box
        for (int r = 0; r < userGameBoard.length; r++) {
            for (int c = 0; c < userGameBoard[0].length; c++) {
                System.out.print(userGameBoard[r][c]);
            }
            System.out.println();
        }
        
    }

    //Doesn't include X
    public static char AIchipSelector(String tag) {
        char chip = ' ';
        status = true;
        while (status) {
            //Chip selection
            System.out.println("\n" + tag + " select your chip:");
                chip = input.next().charAt(0);

                //convert character to UpperCase
                chip = Character.toUpperCase(chip);
            
            switch (chip) {
                case '0','A','B','C','D','E','F','G','H','I','J','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','Y','Z','+','*','-','@','1' -> {
                    status = false;
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

    public static void aiTitle() {
        System.out.println("AI Deathmatch against a dumb random computer" +
                         "\nBy Rodrigo De Lama - Nov 2020\n");
    }

    //ai mode is to play a game against a dumb (random) machine
    public static void ai() {

        clear();

        //Game mode salection
        aiTitle();
        
        //Player 1 name (tag) selection
        System.out.println("\nPlayer 1, whats your tag?");
            p1tag = input.nextLine();

        //Player chip selection
        System.out.println("\nGamer, you have these chips to choose from:" +
                            "\n0, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y, Z, +, *, -, @ or 1");
        
        //Player 1 chip selection 
        p1chip = AIchipSelector(p1tag);

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
            chipPlacer(p1tag, p1chip);

            //Check if player 1 has won
            status = checkWin(p1tag, p1chip);

                //Check if player 1 has already won
                if (status == false) break;

            //User 2 input
            aiRandomPlacer(ai, aiChip);

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
