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
		randy = new Random().nextInt(3);
		Ship.getInstance().addObserver(this);
		map = Map.getInstance();
		randle = new Random().nextInt(4);
		ez = new ArrayList<Eel>();
		for(int i = 0; i<=randle; i++)
			ez.add(new Eel());
	}
	
	private void add(Eel s)
	{
		ez.add(s);
	}
	
	private void remove(int witch)
	{
		ez.remove(witch);
	}
	
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
		for(int i = 0; i<getEels().size() - 1; i++)
		{
			if(map.checkLocation(getChild(i).getCurrentLocation().x - 1, getChild(i).getCurrentLocation().y) == 0)
				wagreed++;
			else if(map.checkLocation(getChild(i).getCurrentLocation().x + 1, getChild(i).getCurrentLocation().y) == 0)
				eagreed++;
		}
			if(wagreed == getEels().size()-1)//if they can all move west
			{
				e = false;
				w = true;
				for(int i = 0; i<getEels().size() - 1; i++)
					getChild(i).goWest();
			}
			else if(eagreed == getEels().size()-1)//if they can all move east
			{
				w = false;
				e = true;
				for(int i = 0; i<getEels().size() - 1; i++)
					getChild(i).goEast();
			}
	}
	
	private void createSwarm()
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
			getChild(i).getCurrentLocation().setLocation(x, y-1);
		}
	}
	
	public ImageView getImageView() { return iv; }
    public void setImageView(ImageView imageView) { this.iv = imageView; }

	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof Ship)
		{
			if(moveTime<randy)
				moveTime++;
			else
				swim();
		}
	}
}
