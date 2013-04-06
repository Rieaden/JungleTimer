package FenetrePrincipale;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import CompoManager.CompoManagerFenetre;
import JungleTimerFenetre.JungleTimerFenetre;
import Util.AppCore;

public class FenetrePrincipale extends Application {


	public static void main(String[] args) throws Exception 
	{ 
		launch(args);	
	}
	@Override public void start(final Stage primaryStage) throws Exception {


/***************************HOOKING****************************/
//        try {
//                GlobalScreen.registerNativeHook();
//        }
//        catch (NativeHookException ex) {
//                System.err.println("There was a problem registering the native hook.");
//                System.err.println(ex.getMessage());
//                System.exit(1);
//        }
//
//        //Construct the example object and initialize native hook.
//        GlobalScreen.getInstance().addNativeKeyListener(new GlobalKeyListenerExample());
/***************************************************************/
		
		primaryStage.setTitle("LoLConqueror");  
		new KeyCombination() {};
		final Menu menuFichier = new Menu("Fichier");
		final Menu menuJungleTimer = new Menu("Timers Jungle");
		
		AppCore.loadParameters();
		
		menuFichier.getItems().add(MenuItemBuilder.create()
				.text("Option")
				.onAction(
						new EventHandler<ActionEvent>()
						{
							@Override public void handle(ActionEvent e)
							{
								new newFenetreParametre(primaryStage);
							}
						}).accelerator( KeyCombination.keyCombination("ctrl+o")).build());

		menuFichier.getItems().add(MenuItemBuilder.create()
				.text("Quitter")
				.onAction(
						new EventHandler<ActionEvent>()
						{
							@Override public void handle(ActionEvent e)
							{
								primaryStage.close();
							}
						}).accelerator( KeyCombination.keyCombination("ctrl+q")).build());
		
		menuJungleTimer.getItems().add(MenuItemBuilder.create()
				.text("Ouvrir")
				.onAction(
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent e) 
							{
								new JungleTimerFenetre(primaryStage);
							}
						}).build());
		
		menuJungleTimer.getItems().add(MenuItemBuilder.create()
				.text("Paramètres")
				.onAction(
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent e) 
							{
								new newFenetreParametre(primaryStage);
							}
						}).build());
		
		
		final Group root = new Group();  
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(30, 5, 5, 5));
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuFichier, menuJungleTimer);
		

		root.getChildren().add(grid);
		root.getChildren().add(menuBar);
		Scene MyScene = new Scene(root, 800, 800);
		grid.prefWidthProperty().bind(primaryStage.widthProperty());
		grid.prefHeightProperty().bind(primaryStage.heightProperty());
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		primaryStage.setScene(MyScene);
		
		String fichier = "nomchampions.txt";
		
		if(!(new File(fichier)).exists())
		{
			(new File(fichier)).createNewFile();
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
//		int i = 0;
//		for(int j = 0; j < (listChampions.size()/10)+1; j++)
//		{
//			for(int k = 0; k < 10; k++)
//			{
//				if(i < listChampions.size())
//				grid.add(new ButtonChampion(listChampions.get(i)), k, j);
//				i++;
//			}
//		}
		/**
		 * ************************************
		 */
		
		Text TitrePrincipal = new Text("LEAGUE of LEGENDS \n    CONQUEROR");
		TitrePrincipal.setFont(Font.font("FrizQuadrata", 70));
		TitrePrincipal.setFill(Color.GOLDENROD);
		TitrePrincipal.setStroke(Color.BLACK);
		grid.add(TitrePrincipal, 0, 0, 3, 1);
		System.out.println(FenetrePrincipale.class.getResource("./images/").toExternalForm());
		String image = FenetrePrincipale.class.getResource("./images/iconeJungle.png").toExternalForm();
		
		Button boutonJungleTimer = new Button();
		boutonJungleTimer.setStyle("-fx-padding: 0;");
		boutonJungleTimer.setPrefWidth(200);
		boutonJungleTimer.setPrefHeight(200);
		Image tempImageJungleTimer = new Image(image, 200, 200, true, true, true);
		ImageView tempImageViewJungleTimer = new ImageView(tempImageJungleTimer);
		boutonJungleTimer.setGraphic(tempImageViewJungleTimer);
		
		grid.add(boutonJungleTimer, 0, 1);
		
		image = FenetrePrincipale.class.getResource("./images/iconeCompo.jpg").toExternalForm();
		
		Button boutonCompoManager = new Button();
		boutonCompoManager.setStyle("-fx-padding: 0;");
		boutonCompoManager.setPrefWidth(50);
		boutonCompoManager.setPrefHeight(50);
		Image tempImageCompoManager = new Image(image, 200, 200, true, true, true);
		ImageView tempImageViewCompoManager = new ImageView(tempImageCompoManager);
		boutonCompoManager.setGraphic(tempImageViewCompoManager);
		
		grid.add(boutonCompoManager, 1, 1);

		image = FenetrePrincipale.class.getResource("./images/random.png").toExternalForm();
		
		Button boutonOptionTrois = new Button();
		boutonOptionTrois.setStyle("-fx-padding: 0;");
		boutonOptionTrois.setPrefWidth(50);
		boutonOptionTrois.setPrefHeight(50);
		Image tempImageOptionTrois = new Image(image, 200, 200, true, true, true);
		ImageView tempImageViewOptionTrois = new ImageView(tempImageOptionTrois);
		boutonOptionTrois.setGraphic(tempImageViewOptionTrois);
		
		grid.add(boutonOptionTrois, 2, 1);
		
		boutonJungleTimer.setOnAction(new EventHandler<ActionEvent>() 
				{
			@Override
			public void handle(ActionEvent arg0) 
			{

				new JungleTimerFenetre(primaryStage);
				
			}
		});
		
		boutonCompoManager.setOnAction(new EventHandler<ActionEvent>() 
				{
					@Override
					public void handle(ActionEvent arg0) 
					{
						new CompoManagerFenetre(primaryStage);
					}
				});
		
		//primaryStage.setHeight(((listChampions.size()/10+1)*50) + ((listChampions.size()/10+1)*10) + 20 +25 + 10);
		//primaryStage.setWidth((10*50) + (10*10) +  10);
		primaryStage.setResizable(false);
		primaryStage.setWidth(650);
		primaryStage.setHeight(450);
		

		image = FenetrePrincipale.class.getResource("./images/background.jpg").toExternalForm();
		grid.setStyle("-fx-background-image: url(\"" + image +"\");");
		primaryStage.show();
	}
	
		
}