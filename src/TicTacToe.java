//Rodrigo De Lama
//100451775@alumnos.uc3m.es
//@RDLF11

//Scanner imported to read user inputs
import java.util.Scanner;

//We're going for CPU mode bois - gonna get a 100%
//CPU Mode is AI Mode in my game

public class TicTacToe {

    //GLOBAL DECLARATIONS
    //Declared static Scanner for permament usage, avoiding a resouse leak if not closed later on
    static Scanner input = new Scanner(System.in);

    //Declared static boolean for global use in while loops
    //Boolean to check the while condition- if true, loops
    static boolean status = true;

    //global enter value to remain empty
    static String enter;


    public static void clear() {
        //https://www.edureka.co/community/4668/how-to-clear-the-console-in-java
        //Command used to clear the console-- for later use to reload the game board
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


    public static void launcher() {

        //Clear the users console before runtime
        clear();

        title();
        
        //Declaring before the loop to not redeclare the variables every time the while loop runs
        String explanationAns;
        
        //Making sure while loop always engages after rerun
        status = true;
        while (status) {

            //Asking the user if they want an explanation as to how the game works
            System.out.println("\nWould you like a quick explanation on how to play?");
                explanationAns = input.nextLine().toLowerCase();

            switch (explanationAns) {
                case "yes", "y" -> {
                    
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
                                     "\nTo win the game, get three chips in a row, column, or diagonally and the win is yours!");
                    //Press enter when the user is ready to continue
                    System.out.println("\nPress \"Enter\" when you're ready");
                        enter = input.nextLine();

                break;
                }

                case "no", "n" -> {
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

                //AI mode
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


    //Declared a global static game variables

    //Backend game board array
    //Declaring empty for reruns-- so that upong reloading the code it always starts empty
    static char[][] backendGameBoard = {
                                        {' ', ' ', ' '},
                                        {' ', ' ', ' '},
                                        {' ', ' ', ' '}
    };
    //User
    static char[][] userGameBoard = {
                                { '╔', '═', '═', '═', '═', '═', '╦', '═', '═', '═', '═', '═', '╦', '═', '═', '═', '═', '═', '╗'},
                                { '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║'},
                                { '╠', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╣'},
                                { '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║'},
                                { '╠', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╬', '═', '═', '═', '═', '═', '╣'},
                                { '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║', ' ', ' ', ' ', ' ', ' ', '║'},
                                { '╚', '═', '═', '═', '═', '═', '╩', '═', '═', '═', '═', '═', '╩', '═', '═', '═', '═', '═', '╝'}
    };

    //User info
    static byte p1Position, p2Position, aiPosition;
    
    //Player names
    static String p1tag, p2tag;

    //Player chips
    static String p1chip, p2chip;
    static final String aiChip = "X";

    //Player wins
    static byte p1Wins, p2Wins, aiWins;


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
            
            switch (chip) {
                case 'X','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Y','Z','+','*','-','@','1' -> {
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

    public static void chipPlecer(String tag, char chip) {

        //Declared outside to avoid redeclarations in loop
        String userInput;

        //implementing loop for one person placement
        status = true;
        while (status) {

            System.out.println("\nIn what square would you like to place your chip?");
                userInput = input.nextLine();

            switch (userInput) {

                case "1": //Used single commas in assignations because the arrays are char arrays
                          userGameBoard[1][3]  = chip; 
                          backendGameBoard[0][0] = chip;
                        break;
                case "2": userGameBoard[1][9]  = chip;
                          backendGameBoard[0][1] = chip;
                        break;
                case "3": userGameBoard[1][15] = chip;
                          backendGameBoard[0][2] = chip;
                        break;
                case "4": userGameBoard[3][3]  = chip; 
                          backendGameBoard[1][0] = chip;
                    break;
                case "5": userGameBoard[3][9]  = chip; 
                          backendGameBoard[1][1] = chip;
                        break;
                case "6": userGameBoard[3][15] = chip; 
                          backendGameBoard[1][2] = chip;
                    break;
                case "7": userGameBoard[5][3]  = chip; 
                          backendGameBoard[2][0] = chip;
                        break;
                case "8": userGameBoard[5][9]  = chip; 
                          backendGameBoard[2][1] = chip;
                        break;
                case "9": userGameBoard[5][15] = chip; 
                          backendGameBoard[2][2] = chip;
                        break;

                default: {
                    System.out.println("Please input a valid location, 1-9");

                    //Inform the user of an invalid input and loop
                    sleep(1500);

                break;
                }

            }
        }
        
    }

    public static void multiplayerTitle() {
        System.out.println("Multiplayer: 1v1\n");
    }


    //Multiplayer mode is for 1v1
    public static void multiplayer() {

        clear();

        //Informing the user about theur game mode selection 
        multiplayerTitle();

        //Player name selection
        System.out.println("\nPlayer 1, whats your tag?");
            p1tag = input.nextLine();
        System.out.println("\nPlayer 2, whats your tag?");
            p2tag = input.nextLine();
        
        //Player chip selection
        System.out.println("\nGamers, you have these chips to choose from:" +
                            "\nX, 0, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y, Z, +, *, -, @ or 1");
        
        //Player 1 chip selection 
        p1chip = Character.toString(chipSelector(p1tag));
    
        //Player 2 chip selection
        p2chip = Character.toString(chipSelector(p2tag));

        /*
        //Test print of names and tags
        System.out.println("Player 1 data:" +
                         "\nTag: " + p1tag +
                         "\nChip: " + p1chip);
        
        System.out.println("Player 2 data:" +
                         "\nTag: " + p2tag +
                         "\nChip: " + p2chip);
        */

        //Clear selections out to start the game
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





        //
        
            clear();

            //Placing the game mode once again
            multiplayerTitle();

            //Printing the modified user box
            for (int r = 0; r < userGameBoard.length; r++) {
                for (int c = 0; c < userGameBoard[0].length; c++) {
                    System.out.print(userGameBoard[r][c]);
                }
                System.out.println();
            }


//Implement code for a 3-in-a-row check or for a full board
            //Check for 3-in-a-row

            //Tried for loop-- broken
            /*
            for (int r = 0; r < backendGameBoard.length; r++){
                if (backendGameBoard[0][r] != ' '){
                    status = false;
                System.out.println("You won!");
                }

            }
            */

            //write an array with possible win values


            //Rows
            if (backendGameBoard[0][0] != ' ' && backendGameBoard[0][1] != ' ' && backendGameBoard[0][2] != ' ')
            {
                status = false;
                System.out.println("You won!");

            }
            else if (backendGameBoard[1][0] != ' ' && backendGameBoard[1][1] != ' ' && backendGameBoard[1][2] != ' ')
            {
                status = false;
                System.out.println("You won!");
            }

            else if (backendGameBoard[2][0] != ' ' && backendGameBoard[2][1] != ' ' && backendGameBoard[2][2] != ' ')
            {
                status = false;
                System.out.println("You won!");
            }

            //Columns
            else if (backendGameBoard[0][0] != ' ' && backendGameBoard[1][0] != ' ' && backendGameBoard[2][0] != ' ')
            {
                status = false;
                System.out.println("You won!");
            }
            else if (backendGameBoard[0][1] != ' ' && backendGameBoard[1][1] != ' ' && backendGameBoard[2][1] != ' ')
            {
                status = false;
                System.out.println("You won!");
            }
            else if (backendGameBoard[0][2] != ' ' && backendGameBoard[1][2] != ' ' && backendGameBoard[2][2] != ' ')
            {
                status = false;
                System.out.println("You won!");
            }

            //Diagonals
            else if (backendGameBoard[0][0] != ' ' && backendGameBoard[1][1] != ' ' && backendGameBoard[2][2] != ' ')
            {
                status = false;
                System.out.println("You won!");
            }
            else if (backendGameBoard[0][2] != ' ' && backendGameBoard[1][1] != ' ' && backendGameBoard[2][0] != ' ')
            {
                status = false;
                System.out.println("You won!");
            }

            //Full board
            else if (backendGameBoard[0][0] != ' ' && backendGameBoard[0][1] != 0 && backendGameBoard[0][2] != ' ' &&
                     backendGameBoard[1][0] != ' ' && backendGameBoard[1][1] != 0 && backendGameBoard[2][2] != ' ' &&
                     backendGameBoard[2][0] != ' ' && backendGameBoard[2][1] != 0 && backendGameBoard[2][2] != ' ' )
            {
                status = false;
                System.out.println("The board is full- Draw!");
            }

        }
        
//IMPLEMENT SCOREBOARD
        /*

        //Print the wins
        if (p1Wins > 0 || p2Wins > 0) {

            System.out.println("Player 1 has won " + p1Wins + " time");
        }
        
        */

    


    public static void aiTitle() {
        System.out.println("AI Deathmatch\n");
    }

    //ai mode is to play a game against a dumb (random) machine
    public static void ai() {

        clear();

        aiTitle();

        //Game mode salections  
        System.out.println("AI: Deathmatch against a dumb random computer\n");

        System.out.println("It works!");
        
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

                case "yes", "y" -> {
                    status = false;

                    System.out.println("\nOkay, comming right up!");

                    sleep(1500);
                    
                    clear();

                    //re-runs the game
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
        reRun();
        //Probably going to implement global counter values for wins
        //runCount();
    }

}
