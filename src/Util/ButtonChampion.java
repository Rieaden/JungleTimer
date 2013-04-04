package Util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ImageAccess.Images;

public class ButtonChampion extends Button 
{
	String champion = new String();
	
	ButtonChampion bouton;
	
	public ButtonChampion(String NomChampion)
	{
		bouton = this;
		champion = NomChampion;
	    setPrefWidth(50);
	    setPrefHeight(50);
		setMaxWidth(50);
		setMaxHeight(50);
		setStyle("-fx-padding: 0;");
		Image tempImage = new Image(Images.getLienImage(NomChampion), 50, 50, true, true, true);
		ImageView tempImageView = new ImageView(tempImage);
		this.setGraphic(tempImageView);
		
		
		setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println(bouton.getPrefWidth());
				System.out.println(bouton.getWidth());
			}
		});
	}
}
