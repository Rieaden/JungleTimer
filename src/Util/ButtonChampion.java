package Util;

import ImageAccess.Images;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ButtonChampion extends Button 
{
	String champion = new String();
	
	public ButtonChampion(String NomChampion)
	{
		champion = NomChampion;
		Image tempImage = new Image(Images.getLienImage(NomChampion), 50, 50, false, false);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setY(70);
		tempImageView.setX(70);
		this.setGraphic(tempImageView);
		setPrefSize(10, 10);
	}
}
