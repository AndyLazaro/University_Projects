package code5_1001821751;


import java.util.Random;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Andy Lazaro 1001821751
 */
public class CandyHouse extends House
{
    public CandyHouse(String name, HashMap<String,Integer> CandyList)
    {
        super(name,CandyList);
    }
    @Override
    public synchronized String ringDoorbell(TrickOrTreater a)
    { 
        String Candy = null; 
        a.addToPath("+"); 
        Random Rnum = new Random();
        int num = 0;
        try
        {
           Thread.sleep(3000);  
        }catch(InterruptedException b)
        {
            Thread.currentThread().interrupt();
        }      
        num = Rnum.nextInt(CandyList.size()) + 1;
        for(Map.Entry Frog: CandyList.entrySet())
        { 
            if((int)Frog.getValue() == num)
                Candy = (String)Frog.getKey();
        } 
 
        return Candy;
    } 


 


}
