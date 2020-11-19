//Rodrigo De Lama
//100451775@alumnos.uc3m.es
//@RDLF11

//Scanner imported to read user keyboard inputs
import java.util.Scanner;

//We're going for CPU mode bois - gonna get a 100%
//CPU Mode is AI Mode in my game

public class TicTacToe {

    //Declared static Scanner for permament usage, avoiding a resouse leak (if not closed)
    static Scanner input = new Scanner(System.in);

    //Declared static boolean for global use in while loops
    //Boolean to check the while condition- if true, loops
    static boolean status = true;
    
    //Scondary status for deeply nested loops
    //static boolean status2 = true;

    public static void mainMenu() {

        //Game Intro
        System.out.println("\n\nHello, welcome to TicTacToe JAVA edition!" +
                           "\nBy Rodrigo De Lama - Nov 2020\n");
        
        //Declaring before the loop to not redeclare the variables every time the while loop runs
        String explanationAns;
        String enter;
        
        while (status) {

            //Asking the user if they want an explanation as to how the game works
            System.out.println("\nWould you like a quick explanation on how to play?");
                explanationAns = input.nextLine().toLowerCase();

            switch (explanationAns) {
                case "yes" -> {
                    
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

                    System.out.println("This is the game board. To place your chip on the board, just type a position's number and hit \"Enter\"" +
                                     "\nTo win the game, get three chips in a row, column, or diagonally and the win is yours!");
                    //Press enter when the user is ready to continue
                    System.out.println("\nPress \"Enter\" when you're ready");
                        enter = input.nextLine();

                break;
                }

                case "no" -> {
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

        System.out.println("In which mode would you like to play?");

        //Present the available game modes
        System.out.println("(A) Multiplayer: 1 versus 1\n" +
                           "(B) AI: a game against a random dumb computer");
        
        //Declared out of the loop to avoid redeclaration
        String gameModeAns;

        //Reusing the same global static status variable
        status = true;
        while (status) {

            //Asking the user if they want an explanation as to how the game works
            System.out.println("\nType A or B to select the game mode:");
                gameModeAns = input.nextLine().toLowerCase();
            
            switch (gameModeAns) {
                    
                case "a": {
                    status = false;
                    System.out.print("Some one on one action comming your way!\n\n\n");
                    multiplayer();

                break;
                }
                case "b": {
                    status = false;
                    System.out.println("Get ready to be destroyed by our super dumb but kinda smart AI...\n\n\n");
                    ai();

                break;
                }
                default: {
                    status = true;
                    System.out.println("Sorry, I didn't catch that. Please type A or B");

                break;
                }
            }

        }
    
    }

    //Declared a global static variables 
    //game board array
    static int[] gameBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static byte p1Position, p2Position, aiPosition;

    //Multiplayer mode is for 1v1
    public static void multiplayer() {

        //Command used to clear the console-- for later use to reload the game board
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        /*

        //edu 7x13
        char[][] = {
                    {'╔', '═', '═', '═', '═', '═', '╦','═', '═', '═', '═', '═','╦','═', '═', '═', '═', '═','╗'},
                    {'║', '' , '' , 'X', '' , '' , '║', '' , '' , 'X' , '' ,'','║', '' , '' , 'X' , '' ,'','║',},
                    {'║', '' , '' , '' , '' , '' , '╬', '' , '' , '' , '' ,'', '╬', '' , '' , '' , '' , '','║',},
                    {'║', '' , '' , 'X' , '' , '' ,'║', '' , '' , 'X' , '' ,'','║', '' , '' , 'X' , '' ,'','║',},
                    {'║', '' , '' , '' , '' , '' , '╬', '' , '' , '' , '' ,'', '╬', '' , '' , '' , '' , '','║',},
                    {'║', '' , '' , 'X' , '' , '' ,'║', '' , '' , 'X' , '' ,'','║', '' , '' , 'X' , '' ,'','║',},
                    {'╚', '═', '═', '═', '═', '═', '╩','═', '═', '═', '═', '═','╩','═', '═', '═', '═', '═','╝'}
                   }

        */

        int userIput;

        System.out.println("In what square would you like to place your chip?");

        System.out.println("It works!");

    }
    
    //ai mode is to play a game against a dumb (random) machine
    public static void ai() {

        System.out.println("It works!");
        
    }


    //reRun is used to ak if the user wants to restart the game
    public static void reRun() {
        
        //Declared beforehand to avoid looped string declaration
        String userAnswer;

        //Try to fix, if not make a switch
        while (status) {

            //Asking the user if they would like to play another game
            System.out.println("Would you like to play another game?" + "\nType in Yes or No");
            //used toLowerCase() to format the users answer
                userAnswer = input.nextLine().toLowerCase();

            if (userAnswer == "yes") {
                //re-runs the game
                mainMenu();

                status = false;
            }
            else if (userAnswer == "no") {
                //Thank the user and ends the program
                System.out.print("Okay, thanks for playing!");

                status = false;
            }
            else {

                status = true;
                System.out.println("Sorry, I didn't catch that. Please try again:");
            }
        }
    
    }

    public static void main(String[] args) {

        mainMenu();
        reRun();
        //runCount();
    }

}
