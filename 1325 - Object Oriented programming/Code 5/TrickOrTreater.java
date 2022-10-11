package code5_1001821751;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
 *
 * @author Andy Lazaro 1001821751
 */
public class TrickOrTreater implements Runnable 
{ 
    public static int ImDone; 
     
    private String name; 
    private String path = "."; 
    private ArrayList<House>ListOfHouses = new ArrayList<>(); 
    private HashMap<String, Integer>Bucket = new HashMap<>(); 
    
    public TrickOrTreater(String name, ArrayList<House> Houses)
    {
        this.name = name;
        this.ListOfHouses = Houses;
    }
     
    public String getName()
    {
        return this.name;
    }
    
    public String getPath()
    {
        return this.path;
    }

    public void addToPath(String pog)
    {
        this.path = this.path + pog;
    }
    private void Walk(int speed)
    {
        for(int i = 0; i < 10; i ++)
        {
            path = path + ".";
            try
            {
                Thread.sleep(speed);
            }catch(InterruptedException d)
            {
                Thread.currentThread().interrupt();
            }           
        }
    }
    public String PrintBucket() 
    { 
        String Candy;
        String BucketContents; 
        int CandyCount = 0; 
         
        BucketContents = String.format("%-10s - ", name); 
 
        /*  use an enhanced for loop to loop over Bucket – Week12 – Slides 72-76 */ 
        for(Map.Entry Hi : Bucket.entrySet())
        { 
            Candy = (String)Hi.getKey();
            CandyCount = (int)Hi.getValue();
            /* set CandyCount equal to the iterator’s value */ 
            BucketContents += String.format("%15s %d ", Candy, CandyCount); 
        } 
        BucketContents += "\n"; 
        return BucketContents; 
    } 
     
    /* override run() from Runnable *. 
    
    */
    @Override
    public void run()
    { 
        /* create integer speed and count and set both to 0 */
        int speed = 0;
        int count = 0;
        /* create string named Candy */ 
        String Candy;
        Random num = new Random();
        /* enhanced for loop over ListOfHouses
        
        */
        for(House yay: ListOfHouses)
        { 
            speed = num.nextInt(1501) + 1;
            Walk(speed);
            Candy = yay.ringDoorbell(this);
            /* call ringDoorbell using the iterator and pass in this */ 
            /* store the return value of ringDoorbell() in Candy */ 
            if(Bucket.containsKey(Candy))
            {
                count = Bucket.get(Candy);
                count++;
                Bucket.put(Candy, count);
            }
            else
                Bucket.put(Candy, 1);
        } 
         
        /* add a synchronized block on object this to protect the update of ImDone */ 
        synchronized (this)
        {
            ImDone++;
        }
    } 
     
} 
