package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Random;

/**
 * This Pirate ship is the generic pirate ship that follows around the player
 * I've made it so it randomly goes in a wrong direction so as to make it more fair
 * (Originally PirateShip class
 */
public class FollowPirateShip extends IPirateShip
{
    private Random rand;

    FollowPirateShip() //needs to be public for Junit tests
    {
        setCurrentLocation(createLocation());
        setImageView(createImageView());
        rand = new Random();
    }

    @Override
    public ImageView createImageView()
    {
        javafx.scene.image.Image shipImage = new Image(new File("images\\pirateShip.png").toURI().toString(), Explorer.getScaleFactor(), Explorer.getScaleFactor(), true, true);
        ImageView imageView = new ImageView(shipImage);
        imageView.setX(this.getCurrentLocation().x * Explorer.getScaleFactor());
        imageView.setY(this.getCurrentLocation().y * Explorer.getScaleFactor());
        Explorer.getAp().getChildren().add(imageView);
        return imageView;
    }

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
            //this is so the follow ship doesnt laser directly at the player, giving it some fairness
            if(rand.nextInt(4) % 2 == 0)
                return;

            Ship ship = (Ship)o;

            //program locks itself to only completely the first action it was told to do
            if(ship.getCurrentLocation().x > getCurrentLocation().x)//if for horizontal movement. permits diagonal
                if(getMap().checkLocation(getCurrentLocation().x + 1, getCurrentLocation().y) == 0) //open space
                    this.goEast();

                else if(Map.getInstance().checkLocation(this.getCurrentLocation().x + 1, this.getCurrentLocation().y) == 5) //ship is here
                {
                    this.goEast();
                    Explorer.showLose();
                }

            if(ship.getCurrentLocation().x < getCurrentLocation().x)
                if(getMap().checkLocation(getCurrentLocation().x - 1, getCurrentLocation().y) == 0)
                        this.goWest();

                else if(Map.getInstance().checkLocation(this.getCurrentLocation().x - 1, this.getCurrentLocation().y) == 5) //ship is here
                {
                    this.goWest();
                    Explorer.showLose();
                }

            if(ship.getCurrentLocation().y < getCurrentLocation().y)
                if (getMap().checkLocation(getCurrentLocation().x, getCurrentLocation().y - 1) == 0)
                {
                    this.goNorth();
                }

                else if(Map.getInstance().checkLocation(this.getCurrentLocation().x, this.getCurrentLocation().y - 1) == 5) //ship is here
                {
                    this.goNorth();
                    Explorer.showLose();
                }

            if(ship.getCurrentLocation().y > getCurrentLocation().y)
                if(getMap().checkLocation(getCurrentLocation().x, getCurrentLocation().y + 1) == 0)
                {
                    this.goSouth();
                }

                else if(Map.getInstance().checkLocation(this.getCurrentLocation().x, this.getCurrentLocation().y + 1) == 5) //ship is here
                {
                    this.goSouth();
                    Explorer.showLose();
                }
        }
    }

    @Override
    public void goWest()
    {
        getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //set the point its currently at to untouched
        getMap().setPoint(getCurrentLocation().x - 1, getCurrentLocation().y, 2); //set the going to point to an enemy location
        getCurrentLocation().setLocation(getCurrentLocation().x - 1, getCurrentLocation().y); //update the GUI location of the ship
    }

    @Override
    public void goEast()
    {
        getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
        getMap().setPoint(getCurrentLocation().x + 1, getCurrentLocation().y, 2);
        getCurrentLocation().setLocation(getCurrentLocation().x + 1, getCurrentLocation().y);
    }

    @Override
    public void goNorth()
    {
        getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
        getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y - 1, 2);
        getCurrentLocation().setLocation(getCurrentLocation().x, getCurrentLocation().y - 1);
    }

    @Override
    public void goSouth()
    {
        getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
        getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y + 1, 2);
        getCurrentLocation().setLocation(getCurrentLocation().x, getCurrentLocation().y + 1);
    }
}
