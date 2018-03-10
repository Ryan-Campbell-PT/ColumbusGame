package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Random;

/**
 * This pirate ship simply moves around and has no specific algorithm for moving
 * It will just float around and, as it says, get lost
 */
public class LostPirateShip extends IPirateShip
{


    LostPirateShip()
    {
        setCurrentLocation(createLocation());
        setImageView(createPirateImage());
    }

    private ImageView createPirateImage()
    {
        javafx.scene.image.Image shipImage = new Image(new File("images\\pirateShip.png").toURI().toString(), Explorer.getScaleFactor(), Explorer.getScaleFactor(), true, true);
        ImageView imageView = new ImageView(shipImage);
        imageView.setX(this.getCurrentLocation().x * Explorer.getScaleFactor());
        imageView.setY(this.getCurrentLocation().y * Explorer.getScaleFactor());
        Explorer.getAp().getChildren().add(imageView);
        return imageView;
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

    @Override
    public void update(Observable o, Object arg)
    {
        if(o instanceof Ship)
        {
            Random rand = new Random();

            switch(rand.nextInt(4))
            {
                case 0:
                    if(this.getCurrentLocation().x + 1 < Explorer.getDimensions())
                        this.goEast();
                    break;

                case 1:
                    if(this.getCurrentLocation().x - 1 > 0)
                        this.goWest();
                    break;

                case 2:
                    if(this.getCurrentLocation().y + 1 < Explorer.getDimensions())
                        this.goSouth();
                    break;

                case 3:
                    if(this.getCurrentLocation().y - 1 > 0)
                        this.goNorth();
                    break;
            }
        }
    }
}
