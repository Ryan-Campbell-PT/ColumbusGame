package code;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * The idea behind WhirlpoolFactory is to keep track of all whirlpools being created
 * and to never create a whirlpool that isnt necessary
 *
 * randomNum is a variable that decides when a new whirlpool should be created
 * when iter becomes == to randomNum (iterates each ship movement), it will either create a new whirlpool if the list is empty
 * or pull from the list the "oldest" whirlpool in the list to save memory
 *
 * FACTORY PATTERN
 */
public class WhirlpoolFactory implements Observer
{
    private LinkedList<Whirlpool> list; //this will record all created whirlpools that arent on the screen
    private int randomNum, counter;

    //I want to change these in the future. Will do later

    WhirlpoolFactory()
    {
        list = new LinkedList<>();
        randomNum = new Random().nextInt(15);
        Ship.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(o instanceof Ship)
        {
            if(counter < randomNum)
                counter++;

            else
            {
                if(counter >= randomNum)
                {
                    if(list.size() == 0)
                    {
                        Whirlpool pool = new Whirlpool();
                    }


                    else
                    {
                        Whirlpool pool = list.removeFirst();
                        pool.create();
                    }

                    randomNum = new Random().nextInt(15);
                    counter = 0;
                }
            }
        }
    }

    /**
     * Whirlpool class is a private class that is what the user will be seeing on the grid
     *
     * Whirlpools have their own variables that decide how long the whirlpools are existing on the grid
     * Once iter becomes == to randomNum (a variable created on the whirlpools creation), the whirlpool
     * will then remove itself from the scene and add itself to the Whirlpool.list
     */
    private class Whirlpool implements Observer
    {
        private int randomNum, counter;
        private Point location1, location2;
        private ImageView imageView1, imageView2; //used for removal later

        Whirlpool()
        {
            create();
        }

        @Override
        public void update(Observable o, Object arg)
        {
            if(o instanceof Ship)
            {
                if(counter < randomNum)
                    counter++; //as long as the whirlpool should still be around

                else
                    remove(); //if its time is up, remove it from the map
            }
        }

        private void create()
        {
            //create two random locations on the map
            Random rand = new Random();
            int x = rand.nextInt(Explorer.getDimensions());
            int y = rand.nextInt(Explorer.getDimensions());

            while(Map.getInstance().checkLocation(x, y) != 0) //to make sure its always a place to be put on
            {
                x = rand.nextInt(Explorer.getDimensions());
                y = rand.nextInt(Explorer.getDimensions());
            }
            location1 = new Point(x, y);

            x = rand.nextInt(Explorer.getDimensions());
            y = rand.nextInt(Explorer.getDimensions());

            while(Map.getInstance().checkLocation(x, y) != 0)
            {
                x = rand.nextInt(Explorer.getDimensions());
                y = rand.nextInt(Explorer.getDimensions());
            }
            location2 = new Point(x, y);

            counter = 0; //reset the counter
            randomNum = rand.nextInt(30); //decide the lifespan of the whirlpool
            Ship.getInstance().addObserver(this); //add the following to it

            loadImages();
        }

        private void loadImages()
        {
            if(imageView1 == null) //if its already created, no need to keep creating more
            {
                Image image = new Image(new File("images\\whirlpool.jpg").toURI().toString(), 50, 50, true, true);
                imageView1 = new ImageView(image);
            }

            if (imageView2 == null)
            {
                Image image = new Image(new File("images\\whirlpool.jpg").toURI().toString(), 50, 50, true, true);
                imageView2 = new ImageView(image);
            }

            imageView1.setX(location1.x * Explorer.getScaleFactor());
            imageView1.setY(location1.y * Explorer.getScaleFactor());

            imageView2.setX(location2.x * Explorer.getScaleFactor());
            imageView2.setY(location2.y * Explorer.getScaleFactor());

            Explorer.getAp().getChildren().add(imageView1);
            Explorer.getAp().getChildren().add(imageView2);
        }

        //whenever the whirlpool is removed from the scene, its imageview and observering status is removed,
        //and the actual whirlpool is added to the list of inactive whirlpools
        private void remove()
        {
            //remove the images from the screen
            Explorer.getAp().getChildren().remove(imageView1);
            Explorer.getAp().getChildren().remove(imageView2);

            list.add(this); //add it back to the factory for use later
            Ship.getInstance().deleteObserver(this); //stop following the ship observing
        }
    }
}
