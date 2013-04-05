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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import JungleTimerFenetre.JungleTimerFenetre;
import Util.AppCore;
import Util.ButtonChampion;

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


		//		primaryStage.setScene(new Scene(
		//				StackPaneBuilder.create()
		//				.children(toggle)
		//				.style("-fx-padding:10; -fx-background-color: cornsilk;")
		//				.build()
		//				));
		//		

		//grid.add(toggle, 0, 0);

		/*******************Modèle de Bouton************************/
		//		final Button buttonAnnie = new Button();						//
		//		Image tempImage = new Image(Images.getLienImage("annie"));		//
		//		buttonAnnie.setGraphic(new ImageView(tempImage));				//
		//		grid.add(buttonAnnie, 0, 1);									//
		/******************Fin Modèle de button*********************/

		/*grid.add(new ButtonChampion("ahri"), 1, 0);
		grid.add(new ButtonChampion("akali"), 2, 0);
		grid.add(new ButtonChampion("alistar"), 3, 0);
		grid.add(new ButtonChampion("amumu"), 4, 0);
		grid.add(new ButtonChampion("anivia"), 5, 0);
		grid.add(new ButtonChampion("annie"), 6, 0);
		grid.add(new ButtonChampion("ashe"), 7, 0);
		grid.add(new ButtonChampion("blitzcrank"), 8, 0);
		grid.add(new ButtonChampion("Brand"), 9, 0);
		grid.add(new ButtonChampion("caitlyn"), 10, 0);

		grid.add(new ButtonChampion("cassiopeia"), 1, 1);
		grid.add(new ButtonChampion("chogath"), 2, 1);
		grid.add(new ButtonChampion("corki"), 3, 1);
		grid.add(new ButtonChampion("darius"), 4, 1);
		grid.add(new ButtonChampion("diana"), 5, 1);
		grid.add(new ButtonChampion("dr-mundo"), 6, 1);
		grid.add(new ButtonChampion("draven"), 7, 1);
		grid.add(new ButtonChampion("elise"), 8, 1);
		grid.add(new ButtonChampion("evelynn"), 9, 1);
		grid.add(new ButtonChampion("ezreal"), 10, 1);

		grid.add(new ButtonChampion("fiddlesticks"), 1, 2);
		grid.add(new ButtonChampion("fiora"), 2, 2);
		grid.add(new ButtonChampion("fizz"), 3, 2);
		grid.add(new ButtonChampion("galio"), 4, 2);
		grid.add(new ButtonChampion("gangplank"), 5, 2);
		grid.add(new ButtonChampion("garen"), 6, 2);
		grid.add(new ButtonChampion("gragas"), 7, 2);
		grid.add(new ButtonChampion("graves"), 8, 2);
		grid.add(new ButtonChampion("hecarim"), 9, 2);
		grid.add(new ButtonChampion("heimerdinger"), 10, 2);

		grid.add(new ButtonChampion("irelia"), 1, 3);
		grid.add(new ButtonChampion("janna"), 2, 3);
		grid.add(new ButtonChampion("jarvan-iv"), 3, 3);
		grid.add(new ButtonChampion("jax"), 4, 3);
		grid.add(new ButtonChampion("jayce"), 5, 3);
		grid.add(new ButtonChampion("karma"), 6, 3);
		grid.add(new ButtonChampion("karthus"), 7, 3);
		grid.add(new ButtonChampion("kassadin"), 8, 3);
		grid.add(new ButtonChampion("katarina"), 9, 3);
		grid.add(new ButtonChampion("kayle"), 10, 3);

		grid.add(new ButtonChampion("kennen"), 1, 4);
		grid.add(new ButtonChampion("khazix"), 2, 4);
		grid.add(new ButtonChampion("kogmaw"), 3, 4);
		grid.add(new ButtonChampion("leblanc"), 4, 4);
		grid.add(new ButtonChampion("lee-sin"), 5, 4);
		grid.add(new ButtonChampion("leona"), 6, 4);
		grid.add(new ButtonChampion("lulu"), 7, 4);
		grid.add(new ButtonChampion("lux"), 8, 4);
		grid.add(new ButtonChampion("malphite"), 9, 4);
		grid.add(new ButtonChampion("malzahar"), 10, 4);

		grid.add(new ButtonChampion("maokai"), 1, 5);
		grid.add(new ButtonChampion("master-yi"), 2, 5);
		grid.add(new ButtonChampion("miss-fortune"), 3, 5);
		grid.add(new ButtonChampion("mordekaiser"), 4, 5);
		grid.add(new ButtonChampion("morgana"), 5, 5);
		grid.add(new ButtonChampion("nami"), 6, 5);
		grid.add(new ButtonChampion("nasus"), 7, 5);
		grid.add(new ButtonChampion("nautilus"), 8, 5);
		grid.add(new ButtonChampion("nidalee"), 9, 5);
		grid.add(new ButtonChampion("nocturne"), 10, 5);

		grid.add(new ButtonChampion("nunu"), 1, 6);
		grid.add(new ButtonChampion("olaf"), 2, 6);
		grid.add(new ButtonChampion("orianna"), 3, 6);
		grid.add(new ButtonChampion("pantheon"), 4, 6);
		grid.add(new ButtonChampion("poppy"), 5, 6);
		grid.add(new ButtonChampion("quinn"), 6, 6);
		grid.add(new ButtonChampion("rammus"), 7, 6);
		grid.add(new ButtonChampion("renekton"), 8, 6);
		grid.add(new ButtonChampion("rengar"), 9, 6);
		grid.add(new ButtonChampion("riven"), 10, 6);

		grid.add(new ButtonChampion("rumble"), 1, 7);
		grid.add(new ButtonChampion("ryze"), 2, 7);
		grid.add(new ButtonChampion("sejuani"), 3, 7);
		grid.add(new ButtonChampion("shaco"), 4, 7);
		grid.add(new ButtonChampion("shen"), 5, 7);
		grid.add(new ButtonChampion("shyvana"), 6, 7);
		grid.add(new ButtonChampion("singed"), 7, 7);
		grid.add(new ButtonChampion("sion"), 8, 7);
		grid.add(new ButtonChampion("sivir"), 9, 7);
		grid.add(new ButtonChampion("skarner"), 10, 7);

		grid.add(new ButtonChampion("sona"), 1, 8);
		grid.add(new ButtonChampion("soraka"), 2, 8);
		grid.add(new ButtonChampion("swain"), 3, 8);
		grid.add(new ButtonChampion("syndra"), 4, 8);
		grid.add(new ButtonChampion("talon"), 5, 8);
		grid.add(new ButtonChampion("taric"), 6, 8);
		grid.add(new ButtonChampion("teemo"), 7, 8);
		grid.add(new ButtonChampion("thresh"), 8, 8);
		grid.add(new ButtonChampion("tristana"), 9, 8);
		grid.add(new ButtonChampion("trundle"), 10, 8);

		grid.add(new ButtonChampion("tryndamere"), 1, 9);
		grid.add(new ButtonChampion("twisted-fate"), 2, 9);
		grid.add(new ButtonChampion("twitch"), 3, 9);
		grid.add(new ButtonChampion("udyr"), 4, 9);
		grid.add(new ButtonChampion("urgot"), 5, 9);
		grid.add(new ButtonChampion("varus"), 6, 9);
		grid.add(new ButtonChampion("vayne"), 7, 9);
		grid.add(new ButtonChampion("veigar"), 8, 9);
		grid.add(new ButtonChampion("vi"), 9, 9);
		grid.add(new ButtonChampion("viktor"), 10, 9);

		grid.add(new ButtonChampion("vladimir"), 1, 10);
		grid.add(new ButtonChampion("volibear"), 2, 10);
		grid.add(new ButtonChampion("warwick"), 3, 10);
		grid.add(new ButtonChampion("wukong"), 4, 10);
		grid.add(new ButtonChampion("xerath"), 5, 10);
		grid.add(new ButtonChampion("xin-zhao"), 6, 10);
		grid.add(new ButtonChampion("yorick"), 7, 10);
		grid.add(new ButtonChampion("zac"), 8, 10);
		grid.add(new ButtonChampion("zed"), 9, 10);
		grid.add(new ButtonChampion("ziggs"), 10, 10);


		grid.add(new ButtonChampion("zilean"), 1, 11);
		grid.add(new ButtonChampion("zyra"), 2, 11);*/
		
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
		
		
		
		int i = 0;
		for(int j = 0; j < (listChampions.size()/10)+1; j++)
		{
			for(int k = 0; k < 10; k++)
			{
				if(i < listChampions.size())
				grid.add(new ButtonChampion(listChampions.get(i)), k, j);
				i++;
			}
		}
		
		
		primaryStage.setHeight(((listChampions.size()/10+1)*50) + ((listChampions.size()/10+1)*10) + 20 +25 + 10);
		primaryStage.setWidth((10*50) + (10*10) +  10);
		primaryStage.setResizable(false);
		

		String image = FenetrePrincipale.class.getResource("background.jpg").toExternalForm();
		grid.setStyle("-fx-background-image: url(\"" + image +"\");");
		primaryStage.show();
	}
	
		
}