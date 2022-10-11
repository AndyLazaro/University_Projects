package code5_1001821751;


import java.util.HashMap;
/**
 *
 * @author Andy Lazaro 1001821751
 */
public abstract class House
{
    private String houseName;
    HashMap<String,Integer> CandyList = new HashMap<>();
    public House(String name,HashMap<String,Integer> list)
    {
        this.houseName = name;
        this.CandyList = list;
    }
    public abstract String ringDoorbell(TrickOrTreater a);  
}
