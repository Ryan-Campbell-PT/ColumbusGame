package code;

import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.Observable;
import java.util.Random;

/**
 * This Pirate ship is the generic pirate ship that follows around the player
 * I've made it so it randomly goes in a wrong direction so as to make it more fair
 * (Originally PirateShip class
 */
public class FollowPirateShip extends IPirateShip
{
    private Map map;
    private Point currentLocation;
    private ImageView imageView;

    //TODO: Implement pirate ship factory

    public FollowPirateShip() //needs to be public for Junit tests
    {
        map = Map.getInstance();
        currentLocation = createLocation();
    }

    @Override
    public void addImageView(ImageView imageView) { this.imageView = imageView; }

    @Override
    public Point createLocation()
    {
        Random rand = new Random();
        int x = rand.nextInt(Explorer.getDimensions());
        int y = rand.nextInt(Explorer.getDimensions());

        while(Map.getInstance().checkLocation(x, y) != 0)
        {
            x = rand.nextInt(Explorer.getDimensions());
            y = rand.nextInt(Explorer.getDimensions());
        }

        return new Point(x, y);
    }

    @Override
    public void update(Observable o, Object args)
    {
        if(o instanceof Ship)
        {
            Ship ship = (Ship)o;

            //program locks itself to only completely the first action it was told to do
            if(ship.getCurrentLocation().x > currentLocation.x)//if for horizontal movement. permits diagonal
                //for some reason, segmenting this single method in more brackets makes the whole thing work.
                if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
                    this.goEast();

            if(ship.getCurrentLocation().x < currentLocation.x)
                if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
                    this.goWest();

            if(ship.getCurrentLocation().y < currentLocation.y)//an if for all vertical movement. permits diagonal
                if (map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0) //open space
                    this.goNorth();

            if(ship.getCurrentLocation().y > currentLocation.y)
                if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
                    this.goSouth();
        }
    }

    @Override
    public void goWest()
    {
        map.setPoint(currentLocation.x, currentLocation.y, 0); //set the point its currently at to untouched
        map.setPoint(currentLocation.x - 1, currentLocation.y, 2); //set the going to point to an enemy location
        currentLocation.setLocation(currentLocation.x - 1, currentLocation.y); //update the GUI location of the ship
    }

    @Override
    public void goEast()
    {
        map.setPoint(currentLocation.x, currentLocation.y, 0); //^^
        map.setPoint(currentLocation.x + 1, currentLocation.y, 2);
        currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);
    }

    @Override
    public void goNorth()
    {
        map.setPoint(currentLocation.x, currentLocation.y, 0); //^^
        map.setPoint(currentLocation.x, currentLocation.y - 1, 2);
        currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
    }

    @Override
    public void goSouth()
    {
        map.setPoint(currentLocation.x, currentLocation.y, 0); //^^
        map.setPoint(currentLocation.x, currentLocation.y + 1, 2);
        currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);

    }



    @Override public Point getCurrentLocation() { return currentLocation;}
    @Override public ImageView getImageView() { return imageView; }
}
