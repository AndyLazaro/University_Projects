package code2_1001821751;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author Andy Lazaro 1001821751
 */
public class Code2_1001821751
{
    enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
    public static void main(String[] args) 
    {
        System.out.printf("Welcome to my pencil machine\n\n");
        ArrayList<String> Color = new ArrayList<>();
        String FileName = args[0].substring(args[0].indexOf('=') + 1,args[0].length());
        final int price = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1,args[1].length()));
        int inventory = 100,change = 500;
        int quantityWanted, payment, total;
        int data[] = {inventory, change};        
        String dollar[] = new String[10];
        Scanner scan = new Scanner(System.in);
        int chosen = 1;
        ReadFile(data,FileName,Color);
        inventory = data[0];
        change = data[1];
        while(chosen != 0)
        {
            chosen = PencilMenu();
            switch(chosen)
            {
                case 0:
                    break;
                case 1:
                    if(inventory != 0)
                    {
                        System.out.printf("\nA pencil costs %s", displayMoney(price));                        
                        System.out.printf("\nHow many pencils would you like to purchase? ");
                        quantityWanted = scan.nextInt();
                        while(quantityWanted > inventory || quantityWanted < 0 || quantityWanted == 0)
                        {
                            System.out.printf("Cannot sell that quantity of pencils. Please reenter quantity ");
                            quantityWanted = scan.nextInt();
                        }
                        total = price * quantityWanted;
                        System.out.printf("\nYour total is %s", displayMoney(total));                        
                        System.out.print("\nEnter your payment (in cents) ");
                        payment = scan.nextInt();
                        
                        switch(buyPencils(data, quantityWanted, payment, price, dollar))
                        {
                            case EXACTPAYMENT:
                                System.out.printf("Here's your %s pencils, No change is given", ColorTime(Color));
                                //copying back to the original variables
                                inventory = data[0];
                                change = data[1];
                                break;
                            case DISPENSECHANGE:
                                System.out.printf("Here's your %s pencils and your change of %s",ColorTime(Color), dollar[0]);
                                //copying back to the original variables
                                inventory = data[0];
                                change = data[1];
                                break;
                            case INSUFFICIENTCHANGE:
                                System.out.println("This pencil dispenser does not have enough change and cannot accept your payment.");
                                break;
                            case INSUFFICIENTFUNDS:
                                System.out.println("You did not enter a sufficient payment, no pencils for you :(");
                                break;
                            default:
                                System.out.println("Something unexpected has occured, please try again");
                                break;
                        }
                    }
                    else
                    {
                        System.out.println("This pencil machine is out of inventory\n");
                    }
                    break;
                case 2:
                    System.out.printf("\nCurrent inventory level is: %d",inventory);
                    break;
                case 3:
                    System.out.printf("\nCurrent change is: %s", displayMoney(change));                   
                    break;
                default:
                    System.out.println("invalid choice entered\n");
                    chosen = 0;    
                    break;
            }
            PrintFile(FileName, Color,data);           
        }
        
    }
    public static void PrintFile(String FileName, ArrayList<String> Color, int data[])
    {
        File file = new File(FileName);
        PrintWriter write = null;
        try
        {
            write = new PrintWriter(file);            
        }catch(FileNotFoundException exc)
        {
            System.exit(0);
        }
        write.println(data[1] + " " + data[0]);
        for(String it: Color)
        {
            write.print(it + " ");
        }
        write.close();
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
    
    public static int PencilMenu()
    {
        Scanner choice = new Scanner(System.in);
        int chosen = 0;        
        System.out.print("\n\nPlease choose from the following options\n");
        System.out.println("0. No pencils for me today");
        System.out.println("1. Purchase pencils");
        System.out.println("2. Check inventroy level");
        System.out.println("3. Check change level");
        System.out.print("Choice: ");
        try
        {
            chosen = choice.nextInt();
            
        }catch (Exception e)
        {
            chosen = -1;
            choice.nextLine();
        }
        while(chosen < 0 || chosen > 3)
        {
            System.out.println("Invalid choice entered");
            System.out.print("\n\nPlease choose from the following options\n");
            System.out.println("0. No pencils for me today");
            System.out.println("1. Purchase pencils");
            System.out.println("2. Check inventroy level");
            System.out.println("3. Check change level");
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
    
    private static ACTION buyPencils(int arr[], int quant, int payment, final int price, String[] dollar)
    {
        int total = quant * price;
        int changeNeeded = payment - total;
        int inventory = arr[0];
        int change = arr[1];
        dollar[0] = displayMoney(changeNeeded);
        if(total == payment)
        {
            inventory = inventory - quant;
            arr[0] = inventory;
            return ACTION.EXACTPAYMENT;
        }
        else if(payment > total)
        {
            if(changeNeeded > change)
            {
                return ACTION.INSUFFICIENTCHANGE;
            }
            else
            {
                arr[0] = inventory - quant;
                if(change > changeNeeded)
                {
                    change = (change - changeNeeded) + payment;                    
                }
                else
                {
                    change = (changeNeeded - change) + payment;
                }
                arr[1] = change;
                return ACTION.DISPENSECHANGE;
            }
        }
        else if(payment < total)
        {
            return ACTION.INSUFFICIENTFUNDS;
        }        
        return null;

    }  
    public static void ReadFile(int data[],String FileName,ArrayList<String> Color) 
    {
        File file = new File(FileName);
        Scanner fileScan = null;
        try
        {     
            fileScan = new Scanner(file);
            
        } catch(FileNotFoundException exc)
        {
            System.out.println("File entered does not exist... exiting program");
            System.exit(0);
        }
        String fileLines[] = new String[2];
        fileLines[0] = fileScan.nextLine();
        //System.out.println(fileLines[0]);
        fileLines[1] = fileScan.nextLine();
        //System.out.println(fileLines[1]);
        String info[] = new String[2];
        String color[] = new String[10];
        info = fileLines[0].split(" ");
        data[0] = Integer.parseInt(info[1]);
        data[1] = Integer.parseInt(info[0]);
        //System.out.printf("%d %d\n", data[0],data[1]);
        color = fileLines[1].split(" ");
        for(String pog:color)
        {
            Color.add(pog);
            //System.out.println(pog);
        }
        fileScan.close();
    }
    public static String ColorTime(ArrayList<String> Color)
    {
        String[] PenColor = new String[1];
        Random Gen = new Random();       
        PenColor[0] = Color.get(Gen.nextInt(Color.size()));
        return PenColor[0];
    }
}

    

