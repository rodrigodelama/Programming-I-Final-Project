//Rodrigo De Lama
//100451775@alumnos.uc3m.es
//@RDLF11

//Scanner imported to read user keyboard inputs
import java.util.Scanner;

//We're going for CPU mode bois - gonna get a 100%

public class TicTacToe {

    //Declared static Scanner for permament usage, avoiding a resouse leak (if not closed)
    static Scanner input = new Scanner(System.in);

    public static void game() {

        System.out.println("Hello!");
    
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
                game();

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

        game();
        //runCount();
        reRun();
    }
}
