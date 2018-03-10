package code;

import java.util.ArrayList;

/**
 * The current set up is the way I want the factory to work
 * I want it to be done in a way that only ships implemented through the factory
 * will be rendered on the screen. This will all be done through the pirateList
 */
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
        Ship.getInstance().addObserver(pirateShip);
    }


    public ArrayList<IPirateShip> getPirateList() { return pirateList; }
}
