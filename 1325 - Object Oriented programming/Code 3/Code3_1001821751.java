package code3_1001821751;

import java.util.Scanner;

/**
 *
 * @author Andy Lazaro 1001821751
 */
public class Code3_1001821751
{


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int chosen = 1;
        int payment = 0;
        CokeMachine Machine = new CokeMachine("CSE 1325 Coke Machine", 50, 500, 100);
        while(chosen != 0)
        {
            System.out.printf("\n%s\n\n",Machine.getMachineName());
            chosen = CokeMenu();
            switch(chosen)
            {
                case 0:
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
                default:               
                    System.out.println("something unexpected has occured, seriously how did you get here\n");
                    chosen = 0;    
                    break;
            }
        }
    }
    
    
     public static int CokeMenu()
    {
        Scanner choice = new Scanner(System.in);
        int chosen = 0;        
        System.out.println("0. Walk away");
        System.out.println("1. Buy a Coke");
        System.out.println("2. Restock Machine");
        System.out.println("3. Add Change");
        System.out.println("4. Display Machine Info");
        System.out.print("Choice: ");
        try
        {
            chosen = choice.nextInt();
            
        }catch (Exception e)
        {
            chosen = -1;
            choice.nextLine();
        }
        while(chosen < 0 || chosen > 4)
        {
            System.out.println("\nInvalid choice entered");
            System.out.print("\n\nPlease choose from the following options\n");
            System.out.println("0. Walk away");
            System.out.println("1. Buy a Coke");
            System.out.println("2. Restock Machine");
            System.out.println("3. Add Change");
            System.out.println("4. Display Machine Info");
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
}
