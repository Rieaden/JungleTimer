package JungleTimerFenetre;





import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import Util.AppCore;
import Util.MiseEnFormeMinutesSecondes;

import com.sun.speech.freetts.VoiceManager;


public class JungleTimerFenetre extends Stage implements NativeKeyListener
{ 

	Voice voix = new Voice("kevin16");

	Label lblDragon = new Label(AppCore.TexteDragon);
	Label lblBleu = new Label("Bleu : ");
	Label lblRouge = new Label("Rouge : ");
	Label lblBleuEnnemi = new Label("Bleu Ennemi : ");
	Label lblRougeEnnemi = new Label("Rouge Ennemi : ");
	Label lblNashor = new Label("Nashor : ");
	
	boolean F7KeyPressed = false;
	boolean F8KeyPressed = false;
	boolean F9KeyPressed = false;
	boolean F10KeyPressed = false;
	boolean F11KeyPressed = false;
	boolean F12KeyPressed = false;

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

		
		GridPane grid = new GridPane();
		
		HBox hbtotal = new HBox(1);
		
		Image tempImage = new Image("file://D:\\Dev\\github\\JungleTimer\\src\\JungleTimerFenetre\\images\\background.png");
		
		hbtotal.setStyle("-fx-background-image: url(\"" + tempImage.toString() + "\"); -fx-background-repeat: stretch;");
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));

		lblDragon.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblDragon.setPrefSize(150, 50);
		hboxDragon.setPrefSize(150, 150);
		hboxDragon.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-96192-1267331210.jpg\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150;");
	    grid.add(lblDragon, 0, 4);
	    grid.add(hboxDragon, 0, 5);
	    
	    lblBleu.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER; ");
		lblBleu.setPrefSize(150, 50);
	    hboxBleu.setPrefSize(150, 150);
		hboxBleu.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306344584.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150;  -fx-border-color: #00FF00;");
	    grid.add(lblBleu, 0, 0);
	    grid.add(hboxBleu, 0, 1);
	    
	    lblRouge.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblRouge.setPrefSize(150, 50);
	    hboxRouge.setPrefSize(150, 150);
		hboxRouge.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306345852.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150;  -fx-border-color: #00FF00;");
	    grid.add(lblRouge, 1, 0);
		grid.add(hboxRouge, 1, 1);
	    
		lblRougeEnnemi.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblRougeEnnemi.setPrefSize(150, 50);
	    hboxRougeEnnemi.setPrefSize(150, 150);
		hboxRougeEnnemi.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306345852.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150; -fx-border-color: #FF0000;");
	    grid.add(lblRougeEnnemi, 1, 2);
	    grid.add(hboxRougeEnnemi, 1, 3);
	    
	    lblBleuEnnemi.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblBleuEnnemi.setPrefSize(150, 50);
	    hboxBleuEnnemi.setPrefSize(150, 150);
		hboxBleuEnnemi.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306344584.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150; -fx-border-color: #FF0000;");
	    grid.add(lblBleuEnnemi, 0, 2);
	    grid.add(hboxBleuEnnemi, 0, 3);
	    
	    lblNashor.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblNashor.setPrefSize(150, 50);
	    hboxNashor.setPrefSize(150, 150);
		hboxNashor.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-96192-1267331204.jpg\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150;");
	    grid.add(lblNashor, 1, 4);
		grid.add(hboxNashor, 1, 5);
	    
	    hbtotal.getChildren().add(grid);
	    
	    
		
		AppCore.refreshValues();
		
		refreshLabels();
		
		setOnHidden(new EventHandler<WindowEvent>() {

			@SuppressWarnings("deprecation")
			@Override
			public void handle(WindowEvent arg0) {
				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
				Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
				int i = 0;
				while(i < threadArray.length)
				{
				
				if(threadArray[i].toString().contains("Timer"))
				{
					threadArray[i].stop();
				}
				i++;
				}
			}
		});
		this.setScene(new Scene(hbtotal, 315, 630));
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
	public void nativeKeyPressed(final NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VK_F5) 
		{
			
			if(!alreadyStartedF5)
			{
			alreadyStartedF5 = true;
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteDragon = "Killed";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurDragon--;
							AppCore.TexteDragon = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(1);

							
							if(AppCore.CompteurDragon == 10)
							{
								String[] thingsToSay = new String[]
										{
										"Dragon respawns in ten seconds",
										};

								voix.say(thingsToSay);

							}
							if(AppCore.CompteurDragon == 0)
							{
								String[] thingsToSay = new String[]
										{
										"Dragon has respawnd",
										};

								voix.say(thingsToSay);
								this.cancel();
								alreadyStartedF5 = false;
								AppCore.CompteurDragon = 360;
								AppCore.TexteDragon = "Alive";
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
							if (F11KeyPressed) 
							{
										
								if(alreadyStartedF5)
								{
								alreadyStartedF5 = false;
								System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
								AppCore.CompteurDragon = 360;
								AppCore.TexteDragon = "Alive";
								Platform.runLater(new Runnable() {
									@Override
									public void run() 
									{
										refreshLabels();
									}
								});
								this.cancel();
								
								}
							}
						}
					}, 0, 1000);
			}
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F1) 
		{
			if(!alreadyStartedF1)
			{
			alreadyStartedF1 = true;
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteBleu = "Killed";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurBleu--;
							AppCore.TexteBleu = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(1);

							
							if(AppCore.CompteurBleu == 10)
							{
								String[] thingsToSay = new String[]
										{
										"Your blue buff respawns in ten seconds",
										};

								voix.say(thingsToSay);

							}
							if(AppCore.CompteurBleu == 0)
							{
								String[] thingsToSay = new String[]
										{
										"Your blue buff has respawnd",
										};

								voix.say(thingsToSay);
								this.cancel();
								alreadyStartedF1 = false;
								AppCore.CompteurBleu = 300;
								AppCore.TexteBleu = "Alive";
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
							if (F7KeyPressed) 
							{
										
								if(alreadyStartedF1)
								{
								alreadyStartedF1 = false;
								System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
								AppCore.CompteurBleu = 300;
								AppCore.TexteBleu = "Alive";
								Platform.runLater(new Runnable() {
									@Override
									public void run() 
									{
										refreshLabels();
									}
								});
								this.cancel();
								
								}
							}
						}
					}, 0, 1000);
			}
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F2) 
		{
			if(!alreadyStartedF2)
			{
			alreadyStartedF2 = true;
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteRouge = "Killed";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurRouge--;
							AppCore.TexteRouge =(MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(1);

							
							if(AppCore.CompteurRouge == 10)
							{
								String[] thingsToSay = new String[]
										{
										"Your red buff respawns in ten seconds",
										};

								voix.say(thingsToSay);

							}
							if(AppCore.CompteurRouge == 0)
							{
								String[] thingsToSay = new String[]
										{
										"Your red buff has respawnd",
										};

								voix.say(thingsToSay);
								this.cancel();
								alreadyStartedF2 = false;
								AppCore.CompteurRouge = 300;
								AppCore.TexteRouge = "Alive";
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
							if (F8KeyPressed) 
							{
										
								if(alreadyStartedF2)
								{
								alreadyStartedF2 = false;
								System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
								AppCore.CompteurRouge = 300;
								AppCore.TexteRouge = "Alive";
								Platform.runLater(new Runnable() {
									@Override
									public void run() 
									{
										refreshLabels();
									}
								});
								this.cancel();
								
								}
							}
						}
					}, 0, 1000);
			}
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F3) 
		{
			if(!alreadyStartedF3)
			{
			alreadyStartedF3 = true;
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteBleuEnnemi = "Killed";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurBleuEnnemi--;
							AppCore.TexteBleuEnnemi = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(1);

							
							if(AppCore.CompteurBleuEnnemi == 10)
							{
								String[] thingsToSay = new String[]
										{
										"Ennemy blue buff respawns in ten seconds",
										};

								voix.say(thingsToSay);

							}
							if(AppCore.CompteurBleuEnnemi == 0)
							{
								String[] thingsToSay = new String[]
										{
										"Ennemy blue buff has respawnd",
										};

								voix.say(thingsToSay);
								this.cancel();
								alreadyStartedF3 = false;
								AppCore.CompteurBleuEnnemi = 300;
								AppCore.TexteBleuEnnemi = "Alive";
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
							if (F9KeyPressed) 
							{
										
								if(alreadyStartedF3)
								{
								alreadyStartedF3 = false;
								System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
								AppCore.CompteurBleuEnnemi = 300;
								AppCore.TexteBleuEnnemi = "Alive";
								Platform.runLater(new Runnable() {
									@Override
									public void run() 
									{
										refreshLabels();
									}
								});
								this.cancel();
								
								}
							}
						}
					}, 0, 1000);
			}
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F4) 
		{
			if(!alreadyStartedF4)
			{
			alreadyStartedF4 = true;
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteRougeEnnemi = "Killed";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							AppCore.CompteurRougeEnnemi--;
							AppCore.TexteRougeEnnemi = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(1);

							
							if(AppCore.CompteurRougeEnnemi == 10)
							{
								String[] thingsToSay = new String[]
										{
										"Enemy red buff respawns in ten seconds",
										};

								voix.say(thingsToSay);

							}
							if(AppCore.CompteurRougeEnnemi == 0)
							{
								String[] thingsToSay = new String[]
										{
										"Enemy red buff has respawnd",
										};

								voix.say(thingsToSay);
								this.cancel();
								alreadyStartedF4 = false;
								AppCore.CompteurRougeEnnemi = 300;
								AppCore.TexteRougeEnnemi = "Alive";
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
							if (F10KeyPressed) 
							{
										
								if(alreadyStartedF4)
								{
								alreadyStartedF4 = false;
								System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
								AppCore.CompteurRougeEnnemi = 300;
								AppCore.TexteRougeEnnemi = "Alive";
								Platform.runLater(new Runnable() {
									@Override
									public void run() 
									{
										refreshLabels();
									}
								});
								this.cancel();
								
								}
							}
						}
					}, 0, 1000);
			};
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F6) 
		{
					
			if(!alreadyStartedF6)
			{
			alreadyStartedF6 = true;
			System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			AppCore.TexteNashor = "Killed";
			new Timer().schedule(
					new TimerTask() {

						@Override
						public void run() {
							
							
							AppCore.CompteurNashor--;
							AppCore.TexteNashor = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(1);

							
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
								AppCore.TexteNashor = "Alive";
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() 
								{
									refreshLabels();
								}
							});
							if (F12KeyPressed) 
							{
										
								if(alreadyStartedF6)
								{
								alreadyStartedF6 = false;
								System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
								AppCore.CompteurNashor = 420;
								AppCore.TexteNashor = "Alive";
								Platform.runLater(new Runnable() {
									@Override
									public void run() 
									{
										refreshLabels();
									}
								});
								this.cancel();
								
								}
							}
						}
					}, 0, 1000);
			}
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F7) 
		{
			F7KeyPressed = true;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F8) 
		{
			F8KeyPressed = true;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F9) 
		{
			F9KeyPressed = true;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F10) 
		{
			F10KeyPressed = true;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F11) 
		{
			F11KeyPressed = true;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F12) 
		{
			F12KeyPressed = true;
		}
		
		

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VK_F7) 
		{
			F7KeyPressed = false;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F8) 
		{
			F8KeyPressed = false;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F9) 
		{
			F9KeyPressed = false;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F10) 
		{
			F10KeyPressed = false;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F11) 
		{
			F11KeyPressed = false;
		}
		if (e.getKeyCode() == NativeKeyEvent.VK_F12) 
		{
			F12KeyPressed = false;
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
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

