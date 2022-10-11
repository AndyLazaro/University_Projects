package code1_1001821751;
import java.util.Scanner;

/**
 *
 * @author Andy Lazaro 1001821751
 */
public class Code1_1001821751
{
    enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
    
    public static void main(String[] args)
    {
        System.out.printf("Welcome to my pencil machine\n\n");
        final int price = 60;
        int inventory = 100,change = 500;
        int quantityWanted, payment, total;
        int data[] = {inventory, change};
        String dollar[] = new String[10];
        Scanner scan = new Scanner(System.in);
        int chosen = 1;
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
                                System.out.println("Here's your pencils, No change is given");
                                //copying back to the original variables
                                inventory = data[0];
                                change = data[1];
                                break;
                            case DISPENSECHANGE:
                                System.out.printf("Here's your pencils and your change of %s", dollar[0]);
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
            
        }
        
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
        int chosen;        
        System.out.print("\n\nPlease choose from the following options\n");
        System.out.println("0. No pencils for me today");
        System.out.println("1. Purchase pencils");
        System.out.println("2. Check inventroy level");
        System.out.println("3. Check change level");
        System.out.print("Choice: ");
        chosen = choice.nextInt();
        while(chosen < 0 || chosen > 3)
        {
            System.out.println("Invalid choice entered");
            System.out.print("\n\nPlease choose from the following options\n");
            System.out.println("0. No pencils for me today");
            System.out.println("1. Purchase pencils");
            System.out.println("2. Check inventroy level");
            System.out.println("3. Check change level");
            System.out.print("Choice: ");
            chosen = choice.nextInt();
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
                System.out.printf("\n____%d and %d___\n", arr[0], arr[1]);
                return ACTION.DISPENSECHANGE;
            }
        }
        else if(payment < total)
        {
            return ACTION.INSUFFICIENTFUNDS;
        }        
        return null;
    }
}
