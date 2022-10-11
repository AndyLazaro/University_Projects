package code3_1001821751;

/**
 *
 * @author Andy Lazaro 1001821751
 */
public class CokeMachine
{
    public enum action
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT, NOINVENTORY
    }
    private String machineName;
    private int changeLevel;
    private int maxChangeCapacity = 5000;
    private int inventoryLevel;
    private int maxInventoryCapacity = 100;
    private int CokePrice;
    private int changeDispensed;
    private static int numberOfCokesSold = 0;
   
    public CokeMachine(String name, int cost, int change, int inventory)
    {
        this.machineName = name;
        this.CokePrice = cost;
        this.changeLevel = change;
        this.inventoryLevel = inventory;
    }
    public String getMachineName()
    {
        return machineName;
    }
    public int getChangeLevel()
    {
        return changeLevel;
    }
    public int getMaxChangeCapacity()
    {
        return maxChangeCapacity;
    }
    public int getInventoryLevel()
    {
        return inventoryLevel;
    }
    public int getMaxInventoryCapacity()
    {
        return maxInventoryCapacity;
    }
    public int getCokePrice()
    {
        return CokePrice;
    }
    public int getChangeDispensed()
    {
        return changeDispensed;
    }
    public int getNumberOfCokesSold()
    {
        return numberOfCokesSold;
    }
    public boolean incrementInventoryLevel(int amountToAdd)
    {
        if(this.inventoryLevel + amountToAdd > maxInventoryCapacity)
        {
            return false;
        }
        else
        {
            this.inventoryLevel = this.inventoryLevel + amountToAdd;
            return true;
        }
    }
    public boolean incrementChangeLevel(int amountToAdd)
    {
        if(this.changeLevel + amountToAdd > maxChangeCapacity)
        {
            return false;
        }
        else
        {
            this.changeLevel = this.changeLevel + amountToAdd;
            return true;
        }
    }
    public action buyACoke(int payment)
    {
        if(this.inventoryLevel == 0)
        {
            return action.NOINVENTORY;
        }
        if(payment < CokePrice)
        {
            return action.INSUFFICIENTFUNDS;
        }
        else if(payment == CokePrice&& this.changeLevel + CokePrice <= maxChangeCapacity)
        {
            numberOfCokesSold = numberOfCokesSold + 1;
            this.inventoryLevel = this.inventoryLevel - 1;
            this.changeDispensed = 0;
            this.changeLevel = this.changeLevel + CokePrice;
            return action.EXACTPAYMENT;
        }
        else if(payment > CokePrice && payment - CokePrice <= this.changeLevel && this.changeLevel + CokePrice <= maxChangeCapacity)
        {
                numberOfCokesSold = numberOfCokesSold + 1;
                this.inventoryLevel = this.inventoryLevel - 1;
                this.changeDispensed = payment - CokePrice;
                this.changeLevel = this.changeLevel + CokePrice;
                return action.DISPENSECHANGE;
        }
        else
        {
            return action.INSUFFICIENTCHANGE;
        }
    }
    public String toString()
    {
        return String.format("\n\nMachine Name\t\t%s\nCurrent Inventory Level %d\nCurrent Change Level %6d\nLast Change Dispensed   %3d\nInventory Capacity %8d\nChange Capacity %12d\nCoke Price %15d\nCokes Sold %14d\n",this.machineName,this.inventoryLevel,this.changeLevel,this.changeDispensed,maxInventoryCapacity,maxChangeCapacity,this.CokePrice,numberOfCokesSold);
    }
}
