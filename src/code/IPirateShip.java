package code;

import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.Observer;
import java.util.Random;

interface IPirateShip extends Observer, NSMoving, EWMoving
{
    void addImageView(ImageView imageView); //adds the imageview associated with the ship
    //creates a location for the ship to be placed on

    Point createLocation();
    Point getLocation(); //get the ships location
    ImageView getImageView(); //get the image view associated with the ship
}
