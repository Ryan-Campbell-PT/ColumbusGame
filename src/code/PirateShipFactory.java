package code;

import java.util.ArrayList;

public class PirateShipFactory
{
    private ArrayList<IPirateShip> pirateList;
    private static PirateShipFactory instance;

    public static PirateShipFactory getInstance()
    {
        if(instance == null)
            instance = new PirateShipFactory();

        return instance;
    }

    private PirateShipFactory()
    {
        pirateList = new ArrayList<>();
    }

    public void addPirateShip(IPirateShip pirateShip)
    {
        this.pirateList.add(pirateShip);
    }

}
