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
                    System.out.println("\nThis is how the board game looks:\n");

                    System.out.println("╔═════╦═════╦═════╗" + 
                                     "\n║  1  ║  2  ║  3  ║" +
                                     "\n╠═════╬═════╬═════╣" +
                                     "\n║  4  ║  5  ║  6  ║" +
                                     "\n╠═════╬═════╬═════╣" +
                                     "\n║  7  ║  8  ║  9  ║" +
                                     "\n╚═════╩═════╩═════╝" );

                    //Press enter when the user is ready to continue
                    System.out.println("Press \"Enter\" when you're ready");
                        enter = input.nextLine();

                break;
                }

                case "no" -> {
                    status = false;
                    System.out.println("\nOkay, lets get onto it!\n");

                break;
                }

                default -> {
                    System.out.println("Sorry, I didn't catch that. Please type yes or no");
                    status = true;
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
        //Reusing the same status variable
        while (status) {

            //Asking the user if they want an explanation as to how the game works
            System.out.println("\nWould you like a quick explanation on how to play?");
                gameModeAns = input.nextLine().toLowerCase();



        }
    
    }

    //Declared a global static variables 
    //game board array
    static int[] gameBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static byte p1Position, p2Position, aiPosition;

    //Multiplayer mode is for 1v1
    public static void multiplayer() {




    }
    
    //ai mode is to play a game against a dumb (random) machine
    public static void ai() {

        
    }


    //reRun is used to ak if the user wants to restart the game
    public static void reRun() {
        
        //Declared beforehand to avoid looped string declaration
        String userAnswer;

        //Try to fix, if not make a switch
        while (status) {

            //Asking the user to 
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
        multiplayer();
        //ai();
        //runCount();
        //reRun();
    }

}
