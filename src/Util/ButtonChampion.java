package Util;

import ImageAccess.Images;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonChampion extends Button 
{
	String champion = new String();
	
	public ButtonChampion(String NomChampion)
	{
		champion = NomChampion;
		Image tempImage = new Image(Images.getLienImage(NomChampion), 70, 70, false, false);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setY(70);
		tempImageView.setX(70);
		this.setGraphic(tempImageView);
		setPrefSize(10, 10);
	}
}
