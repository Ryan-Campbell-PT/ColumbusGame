package code;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.scene.image.ImageView;

public class ESchool implements Observer{

	private Map map;
	private static ArrayList<Eel> ez;
	private ImageView iv;
	int randle, randy, moveTime;
	boolean e, w;
	
	public ESchool()
	{
		moveTime= 0;
		randy = new Random().nextInt(4);//move time random val
		Ship.getInstance().addObserver(this);
		map = Map.getInstance();
		randle = new Random().nextInt(4);//number of eels random val
		ez = new ArrayList<Eel>();
		for(int i = 0; i<=randle; i++)
			ez.add(new Eel());
	}
	
	/*private void add(Eel s)
	{
		ez.add(s);
	}*/
	
	/*private void remove(int witch)
	{
		ez.remove(witch);
	}*/
	
	private Eel getChild(int witch)
	{
		return ez.get(witch);
	}
	public static ArrayList<Eel> getEels()
	{
		return ez;
	}
	
	private void swim()
	{
		int wagreed = 0;//get consesus on who can move in valid direction
		int eagreed = 0;
		for(int i = 0; i<getEels().size(); i++)
		{
			if(map.checkLocation(getChild(i).getCurrentLocation().x - 1, getChild(i).getCurrentLocation().y) == 0)
				wagreed++;
			else if(map.checkLocation(getChild(i).getCurrentLocation().x + 1, getChild(i).getCurrentLocation().y) == 0)
				eagreed++;
		}
		if(wagreed == getEels().size()-1)//if they can all move west
		{
			System.out.println("Agreed west");
			e = false;
			w = true;
			for(int i = 0; i<getEels().size(); i++)
				getChild(i).goWest();
		}
		else if(eagreed == getEels().size()-1)//if they can all move east
		{
			System.out.println("Agreed East");
			w = false;
			e = true;
			for(int i = 0; i<getEels().size(); i++)
				getChild(i).goEast();
		}
		moveTime = 0;
		System.out.println("movetTime at end of swim " + moveTime);
	}
	
	public void createSwarm()
	{
		Random rand = new Random();
		int x = rand.nextInt(Explorer.getDimensions());
		int y = rand.nextInt(Explorer.getDimensions());

		while(Map.getInstance().checkLocation(x, y) != 0)
		{
			x = rand.nextInt(Explorer.getDimensions());
			y = rand.nextInt(Explorer.getDimensions());
		}
		getChild(0).getCurrentLocation().setLocation(x, y);
		for(int i =1; i<getEels().size(); i++)
		{
			if(Map.getInstance().checkLocation(x, y+1) != 0)
				getChild(i).getCurrentLocation().setLocation(x, y-1);
			else
				getChild(i).getCurrentLocation().setLocation(x, y+1);
		}
	}
	
	public ImageView getImageView() {return iv;}
    public void setImageView(ImageView imageView) {this.iv = imageView;}

	@Override
	public void update(Observable o, Object arg)
	{
		System.out.println("Notified school");
		if(o instanceof Ship)
		{
			if(moveTime<randy)
			{
				moveTime++;
				System.out.println(moveTime + " our of " + randy);
			}
			else
			{
				swim();
				System.out.println("Swam");
			}
		}
	}
}