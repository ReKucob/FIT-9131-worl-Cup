import java.util.*;

public class randomNumber
{
    private int abandonNum1;
    
    private int abandonNum2;
    
    private int number;// give a random number
    
    private int sideNum;
    
    public randomNumber()
    {
        number = 0;
        abandonNum1 = 0;
        abandonNum2 = 0;
    }

    public int abandonNum1()
    {
        Random abandon1 = new Random();
        abandonNum1 = abandon1.nextInt(20) + 1;
        return abandonNum1;
    }
    
    public int abandonNum2()
    {
        Random abandon2 = new Random();
        abandonNum2 = abandon2.nextInt(20) + 1;
        return abandonNum2;
    }
    
    public int getrandomNum()
    {
       return number;
    }
    
    public void setrandomNum()
    {
        Random random = new Random();
        number = random.nextInt(100) + 1;
    } 
    
    public int sideNum()
    {
     Random sideNumber = new Random();
     sideNum = sideNumber.nextInt(2);
     return sideNum;
    }
}