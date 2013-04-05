package FenetrePrincipale;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Util.AppCore;

public class newFenetreParametre extends Stage
{

	Label labelParamBleu = new Label("Toggle Blue Buff : ");
	Label labelParamRouge = new Label("Toggle Red Buff : ");
	Label labelParamBleuEnnemi = new Label("Toggle Ennemy Blue Buff : ");
	Label labelParamRougeEnnemi = new Label("Toggle Enemy Red Buff : ");
	Label labelParamDragon = new Label("Toggle Dragon : ");
	Label labelParamNashor = new Label("Toggle Nashor : ");
	Label labelParamAnnulateur = new Label("Canceling Timer Input : ");
	Label labelParamSounds = new Label("Sounds : ");
	Label labelParamOverlay = new Label("Overlay : ");

	TextField tfParamBleu = new TextField();
	TextField tfParamRouge = new TextField();
	TextField tfParamBleuEnnemi = new TextField();
	TextField tfParamRougeEnnemi = new TextField();
	TextField tfParamDragon = new TextField();
	TextField tfParamNashor = new TextField();
	TextField tfParamAnnulateur = new TextField();

	CheckBox checkboxSounds = new CheckBox();
	CheckBox checkboxOverlay = new CheckBox();



	public newFenetreParametre(Stage primaryStage) 
	{
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(primaryStage);

		String fichier = "config.ini";
		final ArrayList<String> listParam = new ArrayList<String>();

		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				listParam.add(ligne);
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		tfParamBleu.setText(listParam.get(0).substring(listParam.get(0).lastIndexOf(" ")+1));
		tfParamRouge.setText(listParam.get(1).substring(listParam.get(1).lastIndexOf(" ")+1));
		tfParamBleuEnnemi.setText(listParam.get(2).substring(listParam.get(2).lastIndexOf(" ")+1));
		tfParamRougeEnnemi.setText(listParam.get(3).substring(listParam.get(3).lastIndexOf(" ")+1));
		tfParamDragon.setText(listParam.get(4).substring(listParam.get(4).lastIndexOf(" ")+1));
		tfParamNashor.setText(listParam.get(5).substring(listParam.get(5).lastIndexOf(" ")+1));
		tfParamAnnulateur.setText(listParam.get(6).substring(listParam.get(6).lastIndexOf(" ")+1));
		checkboxSounds.setSelected(listParam.get(7).substring(listParam.get(7).lastIndexOf(" ")+1).contains("YES"));
		checkboxOverlay.setSelected(listParam.get(8).substring(listParam.get(8).lastIndexOf(" ")+1).contains("YES"));

		tfParamBleu.setEditable(false);
		tfParamBleu.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) 
			{
				System.out.println(e.getCode());
				tfParamBleu.setText(e.getCode().toString());
			}
		});
		tfParamRouge.setEditable(false);
		tfParamRouge.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) 
			{
				System.out.println(e.getCode());
				tfParamRouge.setText(e.getCode().toString());
			}
		});
		tfParamBleuEnnemi.setEditable(false);
		tfParamBleuEnnemi.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) 
			{
				System.out.println(e.getCode());
				tfParamBleuEnnemi.setText(e.getCode().toString());
			}
		});
		tfParamRougeEnnemi.setEditable(false);
		tfParamRougeEnnemi.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) 
			{
				System.out.println(e.getCode());
				tfParamRougeEnnemi.setText(e.getCode().toString());
			}
		});
		tfParamDragon.setEditable(false);
		tfParamDragon.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) 
			{
				if(!e.getCode().isKeypadKey())
				{
					System.out.println(e.getCode());
					tfParamDragon.setText(e.getCode().toString());
				}
			}
		});
		tfParamNashor.setEditable(false);
		tfParamNashor.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) 
			{

				tfParamNashor.setText(e.getCode().toString());

			}
		});
		tfParamAnnulateur.setEditable(false);
		tfParamAnnulateur.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) 
			{
				if(e.isShiftDown())
				{
					tfParamAnnulateur.setText("Maj");
				}
				else
				{
					tfParamAnnulateur.setText(e.getCode().toString());
				}
			}
		});

		HBox hbEnrAnn = new HBox(5);
		Button boutonEnregistrer = new Button("Enregistrer");
		boutonEnregistrer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) 
			{
				try {
					String fichier = "config.ini";
					FileWriter fw = new FileWriter (fichier);
					BufferedWriter bw = new BufferedWriter (fw);
					PrintWriter fichierSortie = new PrintWriter (bw); 

					fichierSortie.println ("Bleu : " + tfParamBleu.getText());
					fichierSortie.println ("Rouge : " + tfParamRouge.getText());
					fichierSortie.println ("BleuEnnemi : " + tfParamBleuEnnemi.getText());
					fichierSortie.println ("RougeEnnemi : " + tfParamRougeEnnemi.getText());
					fichierSortie.println ("Dragon : " + tfParamDragon.getText());
					fichierSortie.println ("Nashor : " + tfParamNashor.getText());
					fichierSortie.println ("Annulateur : " + tfParamAnnulateur.getText());
					if(checkboxSounds.isSelected())
					{
						fichierSortie.println ("Sounds : YES");
					}
					else
					{
						fichierSortie.println ("Sounds : NO");
					}
					if(checkboxOverlay.isSelected())
					{
						fichierSortie.println ("Overlay : YES");
					}
					else
					{
						fichierSortie.println ("Overlay : NO");
					}



					fichierSortie.close();
					System.out.println("Le fichier " + fichier + " a été créé!"); 
				}
				catch (Exception e1){
					System.out.println(e1.toString());
				}
				AppCore.inputBleu = tfParamBleu.getText();
				AppCore.inputRouge = tfParamRouge.getText();
				AppCore.inputBleuEnnemi = tfParamBleuEnnemi.getText();
				AppCore.inputRougeEnnemi = tfParamRougeEnnemi.getText();
				AppCore.inputDragon = tfParamDragon.getText();
				AppCore.inputNashor = tfParamNashor.getText();
				AppCore.inputAnnulateur = tfParamAnnulateur.getText();
				if(checkboxSounds.isSelected())
				{
					AppCore.stateSounds = true;
				}
				else
				{
					AppCore.stateSounds = false;
				}
				if(checkboxOverlay.isSelected())
				{
					AppCore.stateOverlay = true;
				}
				else
				{
					AppCore.stateOverlay = false;
				}
				close();
			}

		});
		Button boutonAnnuler = new Button("Annuler");
		boutonAnnuler.setOnAction(new EventHandler<ActionEvent>() 
				{

			@Override
			public void handle(ActionEvent e0) 
			{
				close();
			}

				});


		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(labelParamBleu, 0, 0);
		grid.add(labelParamRouge, 0, 1);
		grid.add(labelParamBleuEnnemi, 0, 2);	
		grid.add(labelParamRougeEnnemi, 0, 3);	
		grid.add(labelParamDragon, 0, 4);	
		grid.add(labelParamNashor, 0, 5);	
		grid.add(labelParamAnnulateur, 0, 6);	
		grid.add(labelParamSounds, 0, 7);
		grid.add(labelParamOverlay, 0, 8);

		grid.add(tfParamBleu, 1, 0);
		grid.add(tfParamRouge, 1, 1);
		grid.add(tfParamBleuEnnemi, 1, 2);	
		grid.add(tfParamRougeEnnemi, 1, 3);	
		grid.add(tfParamDragon, 1, 4);	
		grid.add(tfParamNashor, 1, 5);	
		grid.add(tfParamAnnulateur, 1, 6);	
		grid.add(checkboxSounds, 1, 7);
		grid.add(checkboxOverlay, 1, 8);

		hbEnrAnn.getChildren().add(boutonAnnuler);
		hbEnrAnn.getChildren().add(boutonEnregistrer);
		grid.add(hbEnrAnn, 1, 9);

		setOnHiding(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) 
			{

			}
		});

		this.setScene(new Scene(grid, 315, 315));
		setResizable(false);
		this.show();

	}

}
