package code;
import javafx.scene.image.ImageView;

import java.awt.Point;

public interface Moving 
{
	Point getCurrentLocation();
	ImageView getImageView();
	ImageView createImageView();
}
