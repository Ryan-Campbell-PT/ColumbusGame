import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Random;

public class WhirlpoolFactory implements Observer
{
    Queue<Whirlpool> list; //this will record all created whirlpools
    private int randomNum, iter;
    Point shipLocation;

    WhirlpoolFactory(Point shipLocation)
    {
        this.shipLocation = shipLocation;
        randomNum = new Random().nextInt(15);
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
                            list.add(new Whirlpool());

                        else
                        {
                            Whirlpool pool = list.remove();
                            pool.create();
                        }
                }
            }
        }
    }


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
            randomNum = 0;
            iter = 0;

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

            loadImage1();
            loadImage2();
        }

        private void remove()
        {
            Explorer.getAp().getChildren().remove(imageView1);
            Explorer.getAp().getChildren().remove(imageView2);
        }

        //definitely can turn these into one
        private void loadImage1()
        {
            javafx.scene.image.Image image = new javafx.scene.image.Image(new File("images\\whirlpool.jpg").toURI().toString(), 50, 50, true, true);
            imageView1 = new ImageView(image);
            imageView1.setX(location1.x * Explorer.getScaleFactor());
            imageView1.setY(location1.y * Explorer.getScaleFactor());
            Explorer.getAp().getChildren().add(imageView1);
        }

        private void loadImage2()
        {
            javafx.scene.image.Image image = new Image(new File("images\\whirlpool.jpg").toURI().toString(), 50, 50, true, true);
            imageView2 = new ImageView(image);
            imageView2.setX(location2.x * Explorer.getScaleFactor());
            imageView2.setY(location2.y * Explorer.getScaleFactor());
            Explorer.getAp().getChildren().add(imageView2);
        }
    }

}
