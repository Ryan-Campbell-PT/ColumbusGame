package code;

import java.util.ArrayList;

/**
 * The current set up is the way I want the factory to work
 * I want it to be done in a way that only ships implemented through the factory
 * will be rendered on the screen. This will all be done through the pirateList
 */
public class PirateShipFactory
{
    private static ArrayList<IPirateShip> pirateList;
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
        pirateList.add(pirateShip);

        //this makes it so only pirate ships added to the factory can be rendered
        pirateShip.getImageView().setX(pirateShip.getCurrentLocation().x * Explorer.getScaleFactor());
        pirateShip.getImageView().setY(pirateShip.getCurrentLocation().y * Explorer.getScaleFactor());

        Ship.getInstance().addObserver(pirateShip);
    }


    public static ArrayList<IPirateShip> getPirateList() { return pirateList; }
}
