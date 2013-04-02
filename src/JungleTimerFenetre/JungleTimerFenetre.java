package JungleTimerFenetre;





import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.sun.speech.freetts.VoiceManager;

import Util.*;


public class JungleTimerFenetre extends Stage implements NativeKeyListener
{ 

	Voice voix = new Voice("kevin16");

	Label lblDragon = new Label(AppCore.TexteDragon);
	Label lblBleu = new Label("Bleu : ");
	Label lblRouge = new Label("Rouge : ");
	Label lblBleuEnnemi = new Label("Bleu Ennemi : ");
	Label lblRougeEnnemi = new Label("Rouge Ennemi : ");
	Label lblNashor = new Label("Nashor : ");
	

	boolean alreadyStartedF1 = false;
	boolean alreadyStartedF2 = false;
	boolean alreadyStartedF3 = false;
	boolean alreadyStartedF4 = false;
	boolean alreadyStartedF5 = false;
	boolean alreadyStartedF6 = false;
	
	HBox hboxDragon = new HBox();
	HBox hboxBleu = new HBox();
	HBox hboxRouge = new HBox();
	HBox hboxBleuEnnemi = new HBox();
	HBox hboxRougeEnnemi = new HBox();
	HBox hboxNashor = new HBox();

	int TimerDragon = 300;

	public boolean flagDragon = false;

	final Object stage = this;

	Stage master = new Stage();

	public JungleTimerFenetre(Stage primaryStage)
	{
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(primaryStage);



		//MyTimerActionListener timertest = new MyTimerActionListener(5, 0);

		/***************************HOOKING****************************/
		//		try {
		//			GlobalScreen.registerNativeHook();
		//		}
		//		catch (NativeHookException ex) {
		//			System.err.println("There was a problem registering the native hook.");
		//			System.err.println(ex.getMessage());
		//			System.exit(1);
		//		}
		//
		//		//Construct the example object and initialize native hook.
		//		GlobalScreen.getInstance().addNativeKeyListener(new GlobalKeyListener());
		/***************************************************************/
		master = primaryStage;
		this.setOnShowing(new EventHandler<WindowEvent>() 
				{
			@Override
			public void handle(WindowEvent we) 
			{
				//Initialze native hook.
				try {
					GlobalScreen.registerNativeHook();
				}
				catch (NativeHookException ex) {
					System.err.println("There was a problem registering the native hook.");
					System.err.println(ex.getMessage());
					ex.printStackTrace();

					System.exit(1);
				}

				GlobalScreen.getInstance().addNativeKeyListener((NativeKeyListener) stage);

			}
				});

		this.setOnHiding(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) 
			{
				GlobalScreen.unregisterNativeHook();
			}
		});

		final Stage stage = this;
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		lblDragon.setStyle("-fx-font-color: #FFFFFF; -fx-font: 40px \"Arial\";");
		hboxDragon.setPrefSize(1000, 150);
	    hboxDragon.getChildren().add(lblDragon);
	    grid.add(hboxDragon, 0, 0);
	    
		hboxBleu.setPrefSize(1000, 150);
	    hboxBleu.getChildren().add(lblBleu);
	    grid.add(hboxBleu, 0, 1);
	    
		hboxRouge.setPrefSize(1000, 150);
	    hboxRouge.getChildren().add(lblRouge);
	    grid.add(hboxRouge, 0, 2);
	    
	    
		grid.add(lblBleuEnnemi, 0, 3);
		grid.add(lblRougeEnnemi, 0, 4);
		grid.add(lblNashor, 0, 5);
		
		refreshLabels();
		this.setScene(new Scene(grid, 1000, 800));
		this.show();
	}

	public void refreshLabels()
	{
		lblDragon.setText(AppCore.TexteDragon);
		lblBleu.setText(AppCore.TexteBleu);
		lblRouge.setText(AppCore.TexteRouge);
		lblBleuEnnemi.setText(AppCore.TexteBleuEnnemi);
		lblRougeEnnemi.setText(AppCore.TexteRougeEnnemi);
		lblNashor.setText(AppCore.TexteNashor);
	}

	
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VK_F1) 
		{
			
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteDragon = "Dragon : Tué";

			String[] thingsToSay = new String[]
					{
					"The blue buff has been killed",
					};

			voix.say(thingsToSay);

			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurDragon--;
							AppCore.TexteDragon = "Dragon : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(1);

							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
						}
					}, 0, 1000);
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F2) 
		{
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteBleu = "Bleu : Tué";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurBleu--;
							AppCore.TexteBleu = "Bleu : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(1);

							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
						}
					}, 0, 1000);
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F3) 
		{
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteRouge = "Rouge : Tué";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurRouge--;
							AppCore.TexteRouge = "Rouge : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(1);

							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
						}
					}, 0, 1000);
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F4) 
		{
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteBleuEnnemi = "BleuEnnemi : Tué";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurBleuEnnemi--;
							AppCore.TexteBleuEnnemi = "BleuEnnemi : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(1);

							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
						}
					}, 0, 1000);
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F5) 
		{
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteRougeEnnemi = "RougeEnnemi : Tué";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurRougeEnnemi--;
							AppCore.TexteRougeEnnemi = "RougeEnnemi : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(1);

							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
						}
					}, 0, 1000);
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F6) 
		{
			if(!alreadyStartedF6)
			{
			alreadyStartedF6 = true;
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteNashor = "Nashor : Tué";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurNashor--;
							AppCore.TexteNashor = "Nashor : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(1);

							
							if(AppCore.CompteurNashor == 10)
							{
								String[] thingsToSay = new String[]
										{
										"Nashor respawns in ten seconds",
										};

								voix.say(thingsToSay);

							}
							if(AppCore.CompteurNashor == 0)
							{
								String[] thingsToSay = new String[]
										{
										"Nashor has respawnd",
										};

								voix.say(thingsToSay);
								this.cancel();
								alreadyStartedF6 = false;
								AppCore.CompteurNashor = 420;
								AppCore.TexteNashor = "Nashor : Respawned";
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
						}
					}, 0, 1000);
			}
		}



	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}




}

class Voice
{
	private String name;
	private com.sun.speech.freetts.Voice systemVoice;

	public Voice(String name)
	{
		this.name = name;
		this.systemVoice = VoiceManager.getInstance().getVoice(this.name);
		this.systemVoice.allocate();
	}

	public void say(String[] thingsToSay)
	{
		for (int i = 0; i < thingsToSay.length; i++)
		{
			this.say( thingsToSay[i] );
		}
	}

	public void say(String thingToSay)
	{
		this.systemVoice.speak(thingToSay);
	}

	public void dispose()
	{
		this.systemVoice.deallocate();
	}
}

