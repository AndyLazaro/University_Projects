package code5_1001821751;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
/**
 * @author Andy Lazaro 1001821751
 */
public class Code5_1001821751
{
    public static void main(String[] args)
    {
        HashMap<String,Integer> CandyList = new HashMap<>();
        ArrayList<House> Houses = new ArrayList<>();
        ArrayList<TrickOrTreater> TOT = new ArrayList<>();
        String FCandy = null, FHouse = null, FTot = null;
        String ScreenOutput;
        String HouseHeading;
        if(args.length == 3)
        {
            FCandy = args[0].substring(args[0].indexOf("=") + 1,args[0].length());
            FHouse = args[1].substring(args[1].indexOf("=") + 1,args[1].length());
            FTot = args[2].substring(args[2].indexOf("=") + 1,args[2].length());
        }
        else
        {
            System.out.printf("Program does not have all files required to run.... Exiting\n");
            System.exit(0);
        }
        CreateCandyList(FCandy,CandyList);
        HouseHeading = CreateHouses(FHouse, Houses,CandyList);
        //System.out.println(HouseHeading);
        CreateTOTs(FTot,TOT, Houses);
        ExecutorService executorService = Executors.newCachedThreadPool(); 
        for(TrickOrTreater it: TOT)
        {
            executorService.execute(it);
        }
        TextAreaFrame TAF = new TextAreaFrame(); 
        TAF.setVisible(true); 
        //System.out.printf("%d\n", TrickOrTreater.ImDone);
        while(TrickOrTreater.ImDone != TOT.size())
        {
            ScreenOutput = String.format("%s", HouseHeading);
            for(TrickOrTreater it: TOT)
            {
                ScreenOutput += String.format("%s%s\n", it.getPath(), it.getName()); 
            }
            TAF.textArea.setText(ScreenOutput);
        }
        executorService.shutdown(); 
        String BucketContents = "Candy!!\n\n";
        for(TrickOrTreater it: TOT)
        {
            BucketContents = BucketContents + it.PrintBucket();
        }
        TAF.textArea.setText(BucketContents); 
    }
    public static void CreateCandyList( String FCandy, HashMap<String,Integer> CandyList)
    {
        File file = new File(FCandy);
        Scanner in = null;
        String Data[] = new String[2];
        try
        {
            in = new Scanner(file);
        } catch(FileNotFoundException b)
        {
            System.out.printf("%s file cannot be found.... Exiting\n", FCandy);
            System.exit(0);
        }
        while(in.hasNextLine())
        {
            Data = in.nextLine().split("\\|");
            //System.out.printf("%-15s %d\n",Data[0],Integer.parseInt(Data[1]));
            CandyList.put(Data[0],Integer.parseInt(Data[1]));
        }
        in.close();
    }
    
    public static String CreateHouses(String FHouse, ArrayList<House> HouseList, HashMap<String,Integer> CandyList)
    {
        File file = new File(FHouse);
        String HouseHeading = "           ";
        Scanner in = null;
        String FileLine = null;
        Random rand = new Random();
        try
        {
            in = new Scanner(file);
        } catch(FileNotFoundException a)
        {
            System.out.printf("%s file cannot be found.... Exiting\n", FHouse);
            System.exit(0);
        }
        while(in.hasNextLine())
        {
            FileLine = in.nextLine();
            HouseHeading = HouseHeading + FileLine;
            for (int i = 0; i < 11-FileLine.length(); i++) 
            { 
                HouseHeading = HouseHeading + " "; 
            }
            if(rand.nextInt(2) == 0)
                HouseList.add(new CandyHouse(FileLine,CandyList));
            else
                HouseList.add(new ToothbrushHouse(FileLine,CandyList));
        }
        in.close();
        HouseHeading = HouseHeading + "\n\n";
        return HouseHeading;
    }
    public static void CreateTOTs(String FTot, ArrayList<TrickOrTreater> TOT, ArrayList<House> Houses)
    {
        File file = new File(FTot);
        Scanner in = null;
        try
        {
            in = new Scanner(file);
        }catch(FileNotFoundException c)
        {
            System.out.printf("%s file cannot be found.... Exiting\n", FTot);
            System.exit(0);
        }
        while(in.hasNextLine())
        {
            TOT.add(new TrickOrTreater(in.nextLine(), Houses));
            
        }
        /*
        for(int i = 0; i < TOT.size(); i++)
        {
            System.out.printf("%s\n",TOT.get(i));
        }
        */
        in.close();
    }
}
