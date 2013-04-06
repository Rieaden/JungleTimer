package CompoManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Util.AppCore;
import Util.ButtonChampion;


public class CompoManagerFenetre extends Stage
{

	Stage thisStage;
	
	public CompoManagerFenetre(Stage primaryStage)
	{
		thisStage = this;
		setTitle("Composition Manager"); 
		final Group root = new Group(); 
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 5, 5, 5));
		
		final Menu menuFichier = new Menu("Fichier");

		menuFichier.getItems().add(MenuItemBuilder.create()
				.text("Quitter")
				.onAction(
						new EventHandler<ActionEvent>()
						{
							@Override public void handle(ActionEvent e)
							{
								thisStage.close();
							}
						}).accelerator( KeyCombination.keyCombination("ctrl+q")).build());
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuFichier);
		
		
String fichier = "nomchampions.txt";
		
		if(!(new File(fichier)).exists())
		{
			try {
				(new File(fichier)).createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				FileWriter fw = new FileWriter (fichier);
				BufferedWriter bw = new BufferedWriter (fw);
				PrintWriter fichierSortie = new PrintWriter (bw); 

				for(int i = 0; i < AppCore.listChampionsDefault.size(); i++)
				{
					fichierSortie.println(AppCore.listChampionsDefault.get(i));
				}

				fichierSortie.close();
				System.out.println("Le fichier " + fichier + " a été créé!"); 
			}
			catch (Exception e1){
				System.out.println(e1.toString());
			}
		}
		
		ArrayList<String> listChampions = new ArrayList<String>();
		
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				listChampions.add(ligne);
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		
		/**
		 * Création d'une grille de boutons
		 */
		int i = 0;
		for(int j = 0; j < (listChampions.size()/10)+1; j++)
		{
			for(int k = 0; k < 10; k++)
			{
				if(i < listChampions.size())
				grid.add(new ButtonChampion(listChampions.get(i)), k, j);
				//System.out.println(grid.getChildren().get(i).getProperties().get("gridpane-row"));
				i++;
			}
		}
		/**
		 * ************************************
		 */
		
		/**
		 * Creation de la liste de participants
		 */
			VBox vbTotalListeParticipants = new VBox(10);
			Text titreListeParticipants = new Text("Liste des joueurs : ");
			titreListeParticipants.setFont(Font.font("FrizQuadrata", 30));
			titreListeParticipants.setFill(Color.GOLDENROD);
			titreListeParticipants.setStroke(Color.BLACK);
			vbTotalListeParticipants.getChildren().add(titreListeParticipants);
			/**
			 * Récupération de la liste des participants
			 */
			
			
//			if(!(new File(fichierListeParticipants)).exists())
//			{
//				try {
//					(new File(fichierListeParticipants)).createNewFile();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					
//					FileWriter fw = new FileWriter (fichierListeParticipants);
//					BufferedWriter bw = new BufferedWriter (fw);
//					PrintWriter fichierSortie = new PrintWriter (bw); 
//
//					for(int i = 0; i < AppCore.listChampionsDefault.size(); i++)
//					{
//						fichierSortie.println(AppCore.listChampionsDefault.get(i));
//					}
//
//					fichierSortie.close();
//					System.out.println("Le fichier " + fichierListeParticipants + " a été créé!"); 
//				}
//				catch (Exception e1){
//					System.out.println(e1.toString());
//				}
//			}
			/**
			 * Recuperation de la liste des participants
			 */
			String fichierListeParticipants = "./data/listeParticipants.txt";
			
			ArrayList<String> listParticipants = new ArrayList<String>();
			try{
				InputStream ips=new FileInputStream(fichierListeParticipants); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					System.out.println(ligne);
					listParticipants.add(ligne);
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			
			
			
			
			
			
			HBox hbtemp;
			ComboBoxCustom comboboxtemp;
			ObservableList<String> itemstemp;
			String fichierListeDuParticipant;
			
			
			for(int j = 0; j < listParticipants.size(); j++)
			{
				hbtemp = new HBox(10);
				hbtemp.getChildren().add(new Text(listParticipants.get(j) + " : "));
				comboboxtemp = new ComboBoxCustom(listParticipants.get(j));	
				
				/**
				 * récupération du contenu de la liste d'un participant
				 */
				System.out.println(listParticipants.get(j));
				fichierListeDuParticipant = "./data/" + listParticipants.get(j) + ".txt";
				
				ArrayList<String> listDuParticipant = new ArrayList<String>();
				try{
					InputStream ips=new FileInputStream(fichierListeDuParticipant); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						System.out.println(ligne);
						listDuParticipant.add(ligne);
					}
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
				
				itemstemp = FXCollections.observableArrayList (listDuParticipant);
				comboboxtemp.setItems(itemstemp);
				
				
				hbtemp.getChildren().add(comboboxtemp);
				vbTotalListeParticipants.getChildren().add(hbtemp);
			}
			
		/**
		 * *************************************
		 */
			
		grid.add(vbTotalListeParticipants, 10, 0, 1, 12);	
			
		String image = CompoManagerFenetre.class.getResource("./images/background.jpg").toExternalForm();
		grid.setStyle("-fx-background-image: url(\"" + image + "\");");
		
		root.getChildren().add(grid);
		root.getChildren().add(menuBar);
		this.setScene(new Scene(root, 850,	750));
		grid.prefWidthProperty().bind(thisStage.widthProperty());
		grid.prefHeightProperty().bind(thisStage.heightProperty());
		menuBar.prefWidthProperty().bind(thisStage.widthProperty());
		this.show();
	} 

}
