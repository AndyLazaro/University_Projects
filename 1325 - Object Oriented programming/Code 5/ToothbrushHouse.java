package code5_1001821751;


import java.util.HashMap;
/**
 *
 * @author Andy Lazaro 1001821751
 */
public class ToothbrushHouse extends House
{
    public ToothbrushHouse(String name, HashMap<String,Integer> CandyList)
    {
        super(name,CandyList);
    }
    @Override
    public synchronized String ringDoorbell(TrickOrTreater a) 
    {  
        a.addToPath("-"); 
        try
        {
            Thread.sleep(3000);
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        return "Toothbrush";
    } 

}
