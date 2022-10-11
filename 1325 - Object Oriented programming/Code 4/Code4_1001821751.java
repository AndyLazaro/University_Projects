package code4_1001821751;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;      
/**
 *
 * @author Andy Lazaro 1001821751
 */
public class Code4_1001821751
{


    
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);
        int chosen = 1;
        int payment = 0;
        int machineChoice = 1;
        String Name;
        CokeMachine Machine = new CokeMachine("CSE 1325 Coke Machine", 50, 500, 100);
        ArrayList<CokeMachine> SetOfCokeMachines = new ArrayList<>();
        String outputFileName = null;
        if (args.length == 2)
        {
            String inputFileName = args[0].substring(args[0].indexOf("=")+ 1, args[0].length());
            outputFileName = args[1].substring(args[1].indexOf("=")+ 1,args[1].length());
            ReadFile(inputFileName,SetOfCokeMachines);    
        }
        else
        {
            System.out.println("Missing command line parameters – - Usage : INPUTFILENAME= OUTPUTFILENAME=" );
            System.exit(0);
        }           
        machineChoice = MachineMenu(SetOfCokeMachines);
        while(machineChoice != 0)
        {
            if(machineChoice == SetOfCokeMachines.size() + 1)
            {
                SetOfCokeMachines.add(new CokeMachine());
            }
            Machine = SetOfCokeMachines.get(machineChoice - 1);
            chosen = CokeMenu(Machine.getMachineName());
            switch(chosen)
            {
                case 0:
                    String bokes = (Machine.getNumberOfCokesSold() > 0) ? "\nBye - enjoy your coke\n" : "\nAre you sure you aren't really THIRSTY and need a Coke?\n";
                    System.out.printf(bokes + "\n");
                    machineChoice = MachineMenu(SetOfCokeMachines);
                    break;
                case 1:
                    System.out.printf("a coke costs : %s\n", displayMoney(Machine.getCokePrice()));
                    System.out.printf("Insert payment: ");
                    payment = in.nextInt();
                    switch(Machine.buyACoke(payment))
                    {
                        case INSUFFICIENTFUNDS:
                            System.out.println("\n\nInsufficient Payment... Returning your payment");
                            break;
                        case EXACTPAYMENT:
                            System.out.printf("\n\nThank you for exact payment.\nHere's your coke\n");
                            break;
                        case DISPENSECHANGE:
                            System.out.printf("\n\nHere's your Coke and your change of %s", displayMoney(payment - Machine.getCokePrice()));
                            break;
                        case INSUFFICIENTCHANGE:
                            System.out.println("\n\nUnable to sell a coke - call 1800WELIKECOKE to register a complaint... returning your payment");
                            break;
                        case NOINVENTORY:
                            System.out.println("\n\nNo Cokes are left in the machine to sell, please restock the machine to continue");
                            break;
                        default:
                            System.out.println("\n\nSomething Unexpected has occured, try again");
                            break;
                    }
                    break;
                case 2:
                    System.out.printf("\nHow much product are you adding to the machine? ");
                    if(Machine.incrementInventoryLevel(in.nextInt()) == true)
                    {
                        System.out.printf("\nYour machine has been restocked\n");
                    }
                    else
                    {
                        System.out.printf("\nYou have exceeded the machine's max capacity - no inventory was added\n");                        
                    }
                    System.out.printf("\nYour inventory level is %d\n", Machine.getInventoryLevel());
                    break;
                case 3:
                    System.out.printf("\nHow much change are you adding to the machine? ");
                    if(Machine.incrementChangeLevel(in.nextInt()) == true)
                    {
                        System.out.println("Your change level has been updated");                       
                    }
                    else
                    {
                        System.out.println("You have exceeded your machine's max change capacity - no change added");
                    }
                    System.out.printf("\nYour change level is %d with a max capacity of %d\n", Machine.getChangeLevel(), Machine.getMaxChangeCapacity());
                    break;
                case 4:
                    System.out.println(Machine); 
                    break;
                case 5:
                    System.out.println("Enter a new machine name");
                    Machine.setMachineName(in.nextLine());
                    System.out.println("\nMachine Name has been updated\n");
                    break;
                case 6:
                    System.out.println("Enter a new Coke price");
                    Machine.setCokePrice(in.nextInt());
                    System.out.println("\nCoke Price has been updated\n");
                    break;
                default:               
                    System.out.println("something unexpected has occured, seriously how did you get here\n");
                    chosen = 0;    
                    break;
            }
        }
        PrintFile(outputFileName, SetOfCokeMachines);
        System.out.printf("\n%d cokes were sold today!\n", CokeMachine.getNumberOfCokesSold());
    }
    
    
     public static int CokeMenu(String name)
    {
        Scanner choice = new Scanner(System.in);
        int chosen = 0;     
        System.out.printf("\nMachine: %s\n\n",name);
        System.out.println("0. Walk away");
        System.out.println("1. Buy a Coke");
        System.out.println("2. Restock Machine");
        System.out.println("3. Add Change");
        System.out.println("4. Display Machine Info");
        System.out.println("5. Set Machine Name");
        System.out.println("6. Set Coke Price");
        System.out.print("Choice: ");
        try
        {
            chosen = choice.nextInt();
            
        }catch (Exception e)
        {
            chosen = -1;
            choice.nextLine();
        }
        while(chosen < 0 || chosen > 6)
        {
            System.out.println("\nInvalid choice entered");
            System.out.print("\n\nPlease choose from the following options\n");
            System.out.println("0. Walk away");
            System.out.println("1. Buy a Coke");
            System.out.println("2. Restock Machine");
            System.out.println("3. Add Change");
            System.out.println("4. Display Machine Info");
            System.out.println("5. Set Machine Name");
            System.out.print("Choice: ");
            
            try
            {
                chosen = choice.nextInt();
            }catch (Exception e)
            {
                chosen = -1;
                choice.nextLine();
            }
        }
        return chosen;
        
    }
     
     
    public static String displayMoney(int i)
    {        
        String dollar = String.valueOf(i / 100);
        String cents = String.valueOf(i % 100);
        if((i % 100) < 10 )
        {
            cents = '0' + cents;
        }
        String amount = '$' + dollar + '.' + cents;
        return amount;
    }
    public static void ReadFile(String FileName,ArrayList<CokeMachine> SetOfCokeMachines) 
    {
        File file = new File(FileName);
        Scanner fileScan = null;
        String Data[] = new String[4];
        String line;
        try
        {     
            fileScan = new Scanner(file);
            
        } catch(FileNotFoundException exc)
        {
            System.out.printf("File %s does not exist... exiting program\n",FileName);
            System.exit(0);
        }
        while(fileScan.hasNextLine())
        {
            line = fileScan.nextLine();
            Data = line.split("\\|");
            SetOfCokeMachines.add(new CokeMachine(Data[0],Integer.parseInt(Data[1]),Integer.parseInt(Data[2]),Integer.parseInt(Data[3]))); 
            //System.out.printf("%s|%d|%d|%d\n\n",Data[0],Integer.parseInt(Data[1]),Integer.parseInt(Data[2]),Integer.parseInt(Data[3]));       
        }    
        fileScan.close();
    }
    public static int MachineMenu(ArrayList<CokeMachine> LotaMachines)
    {
        Scanner choice = new Scanner(System.in);
        int chosen = -1;
        System.out.printf("Pick a Coke Machine\n\n");
        while(chosen < 0 || chosen > LotaMachines.size() + 1)
        {
            
            System.out.println("0. Exit");
            for (int i = 0; i < LotaMachines.size(); i++)
            {
                System.out.printf("%d. %s\n",i + 1, LotaMachines.get(i).getMachineName());
                //System.out.print("Choice: ");
            }
            System.out.printf("%d. Add New Machine\n",LotaMachines.size() + 1);
            try
            {
                System.out.print("Choice: ");
                chosen = choice.nextInt();
            }catch (Exception e)
            {
                chosen = -1;
                choice.nextLine();               
            }
            if(chosen < 0 || chosen > LotaMachines.size() + 1)
                System.out.println("\nInvalid Choice entered\n");
        }
        return chosen;
        
    }
 
    public static void PrintFile(String FileName, ArrayList<CokeMachine> Frog) throws FileNotFoundException
    {
        File file = new File(FileName);
        PrintWriter write = null;
        try
        { 
            write = new PrintWriter(file);       
        } catch(FileNotFoundException exc)
        {
            System.out.printf("Unable to write to output file\n\n");
            System.exit(0);
        }
        for(CokeMachine it: Frog)
        {
            write.printf("%s|%d|%d|%d\n",it.getMachineName(),it.getCokePrice(),it.getChangeLevel(),it.getInventoryLevel());
        }
        write.close();
    }
}  

    
