package code;

import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.Observer;
import java.util.Random;

abstract class IPirateShip implements Observer, NSMoving, EWMoving
{
    private Point currentLocation;
    private ImageView imageView;
    private Map map = Map.getInstance();

    //adds the imageview associated with the ship
    public void addImageView(ImageView imageView) { this.imageView = imageView; }

    //creates a location for the ship to be placed on
    Point createLocation()
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
    public Point getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(Point currentLocation) { this.currentLocation = currentLocation; }
    public Map getMap() { return map; }
    public ImageView getImageView() { return imageView; }

}
