import java.lang.Integer;
import java.util.Scanner;
import java.util.*;

public class Game
{   
    Player playerHuman = new Player();
    Player playerComputer = new Player();   
    private int leftNum;
    private int rightNum;
    private int times;
    randomNumber computerRandom = new randomNumber();

    public Game()
    {
        leftNum = 1;
        rightNum = 100;
    }

    /**
     * check the player's name.
     */
    private void checkName()
    {             
        Scanner inputName = new Scanner(System.in);       
        System.out.println("Please input your name: ");  
        String name = inputName.nextLine(); 
        playerHuman.setName(name);
        while (true)  //check whether the player's name is under the rule.
        {
            if (playerHuman.getName().trim().length() == 0 || playerHuman.getName().trim().length() > 8 )
            {
                System.out.println("Your name is illegal character(within 8 characters). Please enter another name: ");  
                String newName = inputName.nextLine(); 
                playerHuman.setName(newName);
            }
            else 
            {
                System.out.println("Hello," + playerHuman.getName().trim());
                break;
            }
        }
    }

    /**
     * Player's guess method. Give player time to guess a number and check whether it is a number
     */
    public void guessNum1()
    {
        int guessNum = 0;       
        while (true)  //keep checking whether it is a number, if it is a number, change the type from String to int.
        {
            System.out.println("Your guess number is:");
            Scanner inputNumber = new Scanner(System.in);
            if (inputNumber.hasNextInt()) // if it is a number, turn String to int and save the name.
            {
                guessNum = inputNumber.nextInt();
                playerHuman.setGuess(guessNum);

                if (playerHuman.getGuesses() < 0 || playerHuman.getGuesses() > 100)
                { 
                    System.out.println("Your guess number is illegal! Your guess number must between 1 and 100!");
                    System.out.println("Please enter another number!");
                    continue;
                }

                else 
                {
                    System.out.println("Your guess number is:" + playerHuman.getGuesses());
                    break;
                }
            }
            
            else
            {
                System.out.print("Warning!!");
                System.out.println("Your last input is not a number, please enter again!!");
                continue;
            }

        }
    }

    /**
     * computer uses random method to random a number in its turns.
     * judge whether computer's guess is in the right range.
     * if it is not, let computer guess the other number.
     * All the things will not display int screen, except the right number.
     */
    private void guessNum2()
    {
        int guessNum2 = 0;
        System.out.println("Now it is computer's turn to guess!");
        Random guessNumber = new Random();      
        guessNum2 = guessNumber.nextInt((rightNum - leftNum) + 1 ) + leftNum;
        if (computerRandom.getrandomNum() == 1)
        {
            while (guessNum2 >= rightNum)
            {
                guessNum2 = guessNumber.nextInt((rightNum - leftNum) ) + leftNum;
                continue;
            }
        }
        else if (computerRandom.getrandomNum() == 100)
        {
            while (guessNum2 <= leftNum)
            {
                guessNum2 = guessNumber.nextInt((rightNum - leftNum) + 1 ) + leftNum;
                continue;
            }
        }
        else
        {
            while (guessNum2 <= leftNum || guessNum2 >= rightNum)
            { 
                guessNum2 = guessNumber.nextInt((rightNum - leftNum) + 1 ) + leftNum;
                continue;
            }
        }
        playerComputer.setGuess(guessNum2);
        System.out.println("Computer's guess number is " + playerComputer.getGuesses());
    }

    /**
     * The main game progress. include all game methods.
     * all of game progress except the welcome display and inpu name methood.
     */
    private void gameProgress()
    {   
        int round = 1;
        while (round < 5)
        {
            System.out.println("===================================");
            System.out.println("The New Round Game Start!");
            System.out.println("This is the round " + round + "!");
            waitCommand();
            int startNumber = computerRandom.sideNum();
            times = 1;
            leftNum = 1;
            rightNum = 100;
            computerRandom.setrandomNum();
            computerRandom.getrandomNum();
            int abandom = computerRandom.abandonNum1();
            if (startNumber == 0)
            {
                while (times < 7)
                {
                    System.out.println("Now it is your turn to guess.");
                    guessNum1();
                    gameRules1();
                    scoreRules(playerHuman, playerComputer);
                    times++;
                    waitCommand();

                    if (times != 9)
                    {
                        if (abandom == computerRandom.abandonNum2())
                        {
                            System.out.println("Computer abandon this round!");
                            times = 9;
                            scoreRules(playerHuman, playerComputer);                           
                            System.out.println("Please click the Enter to continue.");
                            waitCommand();
                        }

                        else                       
                        {   
                            guessNum2();
                            gameRules2();
                            scoreRules(playerHuman, playerComputer);
                            times++;   
                            waitCommand();
                        }
                    }

                    continue;
                }
                System.out.println("The real hidden number is:" + computerRandom.getrandomNum());
                System.out.println("***************************************************");
                System.out.println("Now,your current score is:" + playerHuman.getScore());
                System.out.println("Now,Computer's current score is:" + playerComputer.getScore());
            }

            if (startNumber == 1)
            {

                while (times < 7)
                {   

                    if (abandom == computerRandom.abandonNum2())
                    {
                        System.out.println("Computer abandon this round!");
                        System.out.println("No one can get a score in this round! :(");
                        System.out.println("The round" + round +" is end! The hidden number is:" + computerRandom.getrandomNum());
                        times = 9;
                        scoreRules(playerHuman, playerComputer);
                        System.out.println("===================================");
                        waitCommand();
                    }

                    else
                    {   
                        guessNum2(); 
                        gameRules2();
                        scoreRules(playerHuman, playerComputer);
                        times++; 
                        waitCommand();
                    }

                    if (times != 9 && playerComputer.getGuesses() != computerRandom.getrandomNum())
                    {
                        System.out.println("Now it is your turn to guess.");
                        guessNum1();
                        gameRules1();
                        scoreRules(playerHuman, playerComputer);
                        times++;
                        waitCommand();

                    } 

                    continue;
                }
                System.out.println("The real hidden number is:" + computerRandom.getrandomNum());
                System.out.println("***************************************************");
                System.out.println("Now,your current score is:" + playerHuman.getScore());
                System.out.println("Now,Computer's current score is:" + playerComputer.getScore());
            }
            round++;           
            continue;
        }

        resultOfGame();
    }  

    /**
     * the game rule for player human.
     */
    private void gameRules1()
    {
        if (playerHuman.getGuesses() == 999)
        {
            times = 8;
            System.out.println("You abandon this round!");
        }

        else if (playerHuman.getGuesses() < leftNum || playerHuman.getGuesses() > rightNum)
        {
            System.out.println("Your guess number must in the range!");
            System.out.println("You lost a chance!Please notice your next guessNum must in the range!");
        }
        else if (playerHuman.getGuesses() == computerRandom.getrandomNum())
        {    System.out.println("Congratulation! Your guess is right!");
            scoreRules(playerHuman, playerComputer);          
            times = 8;
        }

        else if (playerHuman.getGuesses() < computerRandom.getrandomNum())
        {   System.out.println("Sorry! Your guess number is lower.");
            leftNum = playerHuman.getGuesses();
            System.out.println("The new range is between:" + leftNum + " and " + rightNum );
        }
        else 
        {   System.out.println("Sorry! Your guess number is higher."); 
            rightNum = playerHuman.getGuesses();
            System.out.println("The new range is between:" + leftNum + " and " + rightNum );
        }
    }

    /**
     * the game rule for player computer.
     */
    private void gameRules2()
    {
        if (playerComputer.getGuesses() == computerRandom.getrandomNum())
        {   System.out.println("Congratulation! computer guess is right!");
            scoreRules(playerHuman, playerComputer);
            times = 8;
        }
        else if (playerComputer.getGuesses() < computerRandom.getrandomNum())
        {   System.out.println("Computer guess number is lower.");
            leftNum = playerComputer.getGuesses();
            System.out.println("The new range is between:" + leftNum + " and " + rightNum );
        }
        else    
        {   System.out.println("Computer guess number is higher."); 
            rightNum = playerComputer.getGuesses();
            System.out.println("The new range is between:" + leftNum + " and " + rightNum );
        }
    }

    public void gameStart()
    { 
        welcome();
        checkName();
        gameProgress();       
    }

    /**
     * display the final result for 4 rounds game.
     * check which score is higher to judge who wins the game.
     */
    private void resultOfGame()
    {
        System.out.println("***********************************************************************");
        System.out.println("The final score of human player is:" + playerHuman.getScore());
        System.out.println("The final score of computer player is:" + playerComputer.getScore());
        System.out.println("***********************************************************************");
        if (playerHuman.getScore() == playerComputer.getScore())

            System.out.println("The game is a draw!");

        else if (playerHuman.getScore() < playerComputer.getScore())

            System.out.println("The computer won this game!");

        else
            System.out.println("Congratulaton!You beat computer player!") ;   

        System.out.println("If you want to try again, please close the window and start a new game!");
        System.out.println("***********************************************************************");
    }

    /**
     * Calculate each turn's score for both player human and player computer.
     */
    private void scoreRules(Player playerHuman, Player playerComputer)
    {
        int score = 0;   
        int abs1 = java.lang.Math.abs(playerHuman.getGuesses() - computerRandom.getrandomNum());
        int abs2 = java.lang.Math.abs(playerComputer.getGuesses() - computerRandom.getrandomNum());

        if (times < 7 && playerHuman.getGuesses() == computerRandom.getrandomNum())
        {

            switch (times)
            {
                case 1: score = 20; break;
                case 2: score = 15; break;
                case 3: score = 11; break;
                case 4: score = 8; break;
                case 5: score = 6; break;
                case 6: score = 5; break;
            }
            playerHuman.setScore(score);
            playerComputer.setScore(0);
        }

        else if (times < 7 && playerComputer.getGuesses() == computerRandom.getrandomNum())
        {
            switch (times)
            {
                case 1: score = 20; break;
                case 2: score = 15; break;
                case 3: score = 11; break;
                case 4: score = 8; break;
                case 5: score = 6; break;
                case 6: score = 5; break;
            }
            playerHuman.setScore(0);
            playerComputer.setScore(score);
        }

        else if (times == 6 && playerComputer.getGuesses() != computerRandom.getrandomNum() && playerHuman.getGuesses() != computerRandom.getrandomNum())
        {
            if (abs1 < 10)
            {

                if (abs2  < 10)
                {
                    playerHuman.setScore(10 - abs1);
                    playerComputer.setScore(10 - abs2);
                }
                else 
                {
                    playerHuman.setScore(10 - abs1);
                    playerComputer.setScore(0);
                }   
            }

            else if (abs2 < 10)
            {
                if (abs1 < 10)
                {
                    playerHuman.setScore(10 - abs1);
                    playerComputer.setScore(10 - abs2);
                }
                else 
                {
                    playerHuman.setScore(0);
                    playerComputer.setScore(10 - abs2);
                }
            }
        }
        else
        {   
            playerHuman.setScore(0);
            playerComputer.setScore(0);
        }
    }

    /**
     * just for slow down the speed of game progress.
     */
    private void waitCommand()
    {
        System.out.println("===================================");
        System.out.println("Please click <Enter> to continue...");
        System.out.println("===================================");
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
    }

    /**
     * A dislay to introduce the game rule to player.
     */
    private void welcome()
    {
        Scanner record = new Scanner(System.in);
        System.out.println("********************************");
        System.out.println("");
        System.out.println("*Welcome to the Gue55ing Game!!*");
        System.out.println("");
        System.out.println("********************************");
        System.out.println("*          Game Rules          *");
        System.out.println("********************************");
        System.out.println("You will aginst computer player to guess a number between 1 and 100.");
        System.out.println("");
        System.out.println("Each of player will have 3 chances to guess the number in one round.");
        System.out.println("");
        System.out.println("If one player's guess is right, the other one will got 0 in this round.");
        System.out.println("");
        System.out.println("If nobody guess right, we have a special rule to calculate the score.");
        System.out.println("");
        System.out.println("There are four rounds in a whole game.");
        System.out.println("");
        System.out.println("If you are ready, you can start the game.");
        System.out.println("");
        System.out.println("Good luck! xD");
        System.out.println("********************************");
    }
}