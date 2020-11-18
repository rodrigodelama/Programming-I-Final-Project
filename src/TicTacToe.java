//Rodrigo De Lama
//100451775@alumnos.uc3m.es
//@RDLF11

//Scanner imported to read user keyboard inputs
import java.util.Scanner;

//We're going for CPU mode bois - gonna get a 100%

public class TicTacToe {

    //Declared static Scanner for permament usage, avoiding a resouse leak (if not closed)
    static Scanner input = new Scanner(System.in);

    public static void mainMenu() {

        //Game Intro
        System.out.println("\n\nHello, welcome to TicTacToe JAVA edition!" +
                           "\nBy Rodrigo De Lama - Nov 2020\n\n");
        
        String explanationAns;
        boolean status = true;
        while (status) {

            System.out.println("Would you like a quick explanation on how to play?");
                explanationAns = input.nextLine().toLowerCase();


            switch (explanationAns) {
                case "yes" -> {
                    status = false;
                    System.out.println("It works!");
                break;
                }

                case "no" -> {
                    status = false;
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
    
    }


    //reRun is used to ak if the user wants to restart the game
    public static void reRun() {
        
        //Asking the user to 
        System.out.println("Would you like to play another game?" + "\nType in Yes or No");
            //used toLowerCase() to format the users answer
            String userAnswer = input.nextLine().toLowerCase();
        
        boolean status = true;

        //Try to fix, if not make a switch
        while (status) {

            if (userAnswer == "yes") {
                //re-runs the game
                mainMenu();

                status = false;
            }
            else if (userAnswer == "no") {
                //Thank the user and end the program
                System.out.print("Okay, thanks for playing!");

                status = true;
            }
            else {

                status = true;
                System.out.println("Sorry, I didn't catch that. Please try again:");
            }
        }
    }

    public static void main(String[] args) {

        mainMenu();
        //runCount();
        //reRun();
    }
}
