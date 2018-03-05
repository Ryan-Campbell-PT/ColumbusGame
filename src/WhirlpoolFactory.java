import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * The idea behind WhirlpoolFactory is to keep track of all whirlpools being created
 * and to never create a whirlpool that isnt necessary
 */
public class WhirlpoolFactory implements Observer
{
    Queue<Whirlpool> list; //this will record all created whirlpools
    private int randomNum, iter;

    //I want to change these in the future. Will do later
    private Point shipLocation;
    private Ship ship;

    WhirlpoolFactory(Ship ship)
    {
        list = new LinkedList<>();
        this.shipLocation = ship.getLocation();
        randomNum = new Random().nextInt(15);
        ship.addObserver(this);
        this.ship = ship;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(o instanceof Ship)
        {
            if(iter < randomNum)
                iter++;

            else
            {
                if(iter >= randomNum)
                {

                        if(list.size() == 0)
                        {
                            Whirlpool pool = new Whirlpool();
                            list.add(pool);
                            ship.addObserver(pool);
                        }

                        else
                        {
                            Whirlpool pool = list.remove();
                            pool.create();
                        }
                }
            }
        }
    }

    /**
     * Whirlpool class is a private class that is what the user will be seeing on the grid
     *
     * Whirlpools have their own variables that decide how long the whirlpools are existing on the grid
     */
    private class Whirlpool implements Observer
    {
        private int randomNum, iter;
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
                if(iter < randomNum)
                    iter++;

                else
                    remove();
            }
        }

        private void create()
        {
            System.out.println("Created");
            Random rand = new Random();
            int x = rand.nextInt(Explorer.getDimensions());
            int y = rand.nextInt(Explorer.getDimensions());

            while(Map.getInstance().checkLocation(x, y) != 0)
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

            iter = 0;
            randomNum = rand.nextInt(30);

            loadImage1();
            loadImage2();
        }

        private void remove()
        {
            Explorer.getAp().getChildren().remove(imageView1);
            Explorer.getAp().getChildren().remove(imageView2);

            list.add(this); //add it back to the factory for use later
        }

        //definitely can turn these into one
        private void loadImage1()
        {
            Image image = new Image(new File("images\\whirlpool.jpg").toURI().toString(), 50, 50, true, true);
            imageView1 = new ImageView(image);
            imageView1.setX(location1.x * Explorer.getScaleFactor());
            imageView1.setY(location1.y * Explorer.getScaleFactor());
            Explorer.getAp().getChildren().add(imageView1);
        }

        private void loadImage2()
        {
            Image image = new Image(new File("images\\whirlpool.jpg").toURI().toString(), 50, 50, true, true);
            imageView2 = new ImageView(image);
            imageView2.setX(location2.x * Explorer.getScaleFactor());
            imageView2.setY(location2.y * Explorer.getScaleFactor());
            Explorer.getAp().getChildren().add(imageView2);
        }
    }

}
