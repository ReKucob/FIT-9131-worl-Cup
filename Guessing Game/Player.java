import java.util.Scanner;

public class Player
{   
    private String name; //Player's name;
    
    private int score;//player's score;
    
    private int guesses; //The last number guesses for the current;
    
    public Player()
    {        
    }
    
    public Player(String playerName, int score, int guesses)
    {  
       score = 0;
       
       guesses = 0;
       
       name = null;
    }
    
    public String getName()
    {  
        return name;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getGuesses()
    {
      return guesses;
    }
    
    public void setName(String playerName)
    {       
       this.name = playerName;
    }
    
    public void setGuess(int number)
    {
        this.guesses = number;
    }
    
    public void setScore(int newScore)
    {       
      score = score + newScore;  
    }
    
}