package JungleTimerFenetre;





import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import Util.AppCore;
import Util.MiseEnFormeMinutesSecondes;


import javafx.scene.paint.Color;

import com.sun.javafx.tk.Toolkit;
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

	Label lblDragonOverlay = new Label(AppCore.TexteDragon);
	Label lblBleuOverlay = new Label("Bleu : ");
	Label lblRougeOverlay = new Label("Rouge : ");
	Label lblBleuEnnemiOverlay = new Label("Bleu Ennemi : ");
	Label lblRougeEnnemiOverlay = new Label("Rouge Ennemi : ");
	Label lblNashorOverlay = new Label("Nashor : ");

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

	boolean shiftKeyPressed = false;

	boolean signalStopF1 = false;
	boolean signalStopF2 = false;
	boolean signalStopF3 = false;
	boolean signalStopF4 = false;
	boolean signalStopF5 = false;
	boolean signalStopF6 = false;

	Point refPoint;

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

	JDialog jDialogReference;

	public JungleTimerFenetre(Stage primaryStage)
	{

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

		hbtotal.setStyle("-fx-background-image: url(\"file:///D:/Dev/github/JungleTimer/background.png\");");

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
		hboxBleu.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306344584.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150;  -fx-border-color: #00FF00; -fx-border-radius: 5;");
		grid.add(lblBleu, 0, 0);
		grid.add(hboxBleu, 0, 1);

		lblRouge.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblRouge.setPrefSize(150, 50);
		hboxRouge.setPrefSize(150, 150);
		hboxRouge.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306345852.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150;  -fx-border-color: #00FF00; -fx-border-radius: 5;");
		grid.add(lblRouge, 1, 0);
		grid.add(hboxRouge, 1, 1);

		lblRougeEnnemi.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblRougeEnnemi.setPrefSize(150, 50);
		hboxRougeEnnemi.setPrefSize(150, 150);
		hboxRougeEnnemi.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306345852.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150; -fx-border-color: #FF0000; -fx-border-radius: 5;");
		grid.add(lblRougeEnnemi, 1, 2);
		grid.add(hboxRougeEnnemi, 1, 3);

		lblBleuEnnemi.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblBleuEnnemi.setPrefSize(150, 50);
		hboxBleuEnnemi.setPrefSize(150, 150);
		hboxBleuEnnemi.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306344584.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150; -fx-border-color: #FF0000; -fx-border-radius: 5;");
		grid.add(lblBleuEnnemi, 0, 2);
		grid.add(hboxBleuEnnemi, 0, 3);

		lblNashor.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30pt; -fx-background-color: #000000; -fx-alignment: CENTER;");
		lblNashor.setPrefSize(150, 50);
		hboxNashor.setPrefSize(150, 150);
		hboxNashor.setStyle("-fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-96192-1267331204.jpg\"); -fx-background-repeat: no-repeat; -fx-background-size: 150, 150;");
		grid.add(lblNashor, 1, 4);
		grid.add(hboxNashor, 1, 5);

		lblBleuOverlay.setStyle("-fx-stroke: black; -fx-stroke-width: 5; -fx-alignment: CENTER; -fx-text-fill: #FFFFFF; -fx-font-size: 20pt; -fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306344584.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 50, 50; -fx-border-color: #00FF00; -fx-border-radius: 5;");
		lblBleuOverlay.setPrefSize(50, 50);
		lblRougeOverlay.setStyle("-fx-stroke: black; -fx-stroke-width: 5; -fx-alignment: CENTER; -fx-text-fill: #FFFFFF; -fx-font-size: 20pt; -fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306345852.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 50, 50; -fx-border-color: #00FF00; -fx-border-radius: 5;");
		lblRougeOverlay.setPrefSize(50, 50);
		lblBleuEnnemiOverlay.setStyle("-fx-text-stroke: black; -fx-text-stroke-width: 5; -fx-alignment: CENTER; -fx-text-fill: #FFFFFF; -fx-font-size: 20pt; -fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306344584.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 50, 50; -fx-border-color: #FF0000; -fx-border-radius: 5;");
		lblBleuEnnemiOverlay.setPrefSize(50, 50);
		lblRougeEnnemiOverlay.setStyle("-fx-stroke: black; -fx-stroke-width: 5; -fx-alignment: CENTER; -fx-alignment: CENTER; -fx-text-fill: #FFFFFF; -fx-font-size: 20pt; -fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-71856-1306345852.png\"); -fx-background-repeat: no-repeat; -fx-background-size: 50, 50; -fx-border-color: #FF0000; -fx-border-radius: 5;");
		lblRougeEnnemiOverlay.setPrefSize(50, 50);
		lblDragonOverlay.setStyle("-fx-stroke: black; -fx-stroke-width: 5; -fx-alignment: CENTER; -fx-alignment: CENTER; -fx-text-fill: #FFFFFF; -fx-font-size: 20pt; -fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-96192-1267331210.jpg\"); -fx-background-repeat: no-repeat; -fx-background-size: 50, 50;");
		lblDragonOverlay.setPrefSize(50, 50);
		lblNashorOverlay.setStyle("-fx-stroke: black; -fx-stroke-width: 5; -fx-alignment: CENTER; -fx-alignment: CENTER; -fx-text-fill: #FFFFFF; -fx-font-size: 20pt; -fx-background-image: url(\"http://www.gamereplays.org/community/uploads/post-96192-1267331204.jpg\"); -fx-alignment: CENTER; -fx-background-size: 50, 50;");
		lblNashorOverlay.setPrefSize(50, 50);




		hbtotal.getChildren().add(grid);



		AppCore.refreshValues();

		refreshLabels();

		setOnHidden(new EventHandler<WindowEvent>() {

			@SuppressWarnings("deprecation")
			@Override
			public void handle(WindowEvent arg0) {
				jDialogReference.dispose();
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
		final JDialog jDialog = new JDialog();

		jDialogReference = jDialog;

		final JFXPanel fxPanel = new JFXPanel();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				final Group root = new Group();  
				GridPane grid = new GridPane();
				grid.setHgap(10);
				grid.setVgap(10);
				grid.setPadding(new Insets(5, 5, 5, 5));

				grid.add(lblBleuOverlay, 0, 0);
				grid.add(lblRougeOverlay, 1, 0);
				grid.add(lblDragonOverlay, 2, 0);
				grid.add(lblNashorOverlay, 3, 0);
				grid.add(lblBleuEnnemiOverlay, 4, 0);
				grid.add(lblRougeEnnemiOverlay, 5, 0);

				root.setStyle("-fx-background-color: rgba(0,0,0,0);");
				grid.setStyle("-fx-background-color: rgba(0,0,0,0);");

				root.setOnMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) 
					{
						refPoint = new Point((int)(event.getX()), (int)(event.getY()));
					}
				});

				root.setOnMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) 
					{
						refPoint = null;
					}
				});

				root.setOnMouseDragged(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {

						if (refPoint == null) {
							refPoint = new Point((int)(event.getX()), (int)(event.getY()));
						}
						else 
						{
							int dx = (int) (event.getX() - refPoint.x);
							int dy = (int) (event.getY() - refPoint.y);
							jDialog.setLocation(jDialog.getX() + dx ,  jDialog.getY() + dy);
							refPoint.x = (int) event.getX();
							refPoint.y = (int) event.getY();
						}
					}
				});
				root.getChildren().add(grid);
				Scene scene = new Scene(root, 800, 800);
				scene.setFill(Color.TRANSPARENT);
				fxPanel.setScene(scene);
				fxPanel.getRootPane().setOpaque(false);
			}
		});
		jDialog.add(fxPanel);
		jDialog.setSize(360 + 150, 60);
		jDialog.setUndecorated(true);
		jDialog.getRootPane().setOpaque(false);
		jDialog.setBackground(new java.awt.Color(0, 0, 0, 0));
		jDialog.setAlwaysOnTop(true);
		System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth());
		jDialog.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth()/5), 0);
		jDialog.setVisible(true);
		jDialog.setFocusable(false);
		

	}




	public void refreshLabels()
	{
		lblDragon.setText(AppCore.TexteDragon);
		lblBleu.setText(AppCore.TexteBleu);
		lblRouge.setText(AppCore.TexteRouge);
		lblBleuEnnemi.setText(AppCore.TexteBleuEnnemi);
		lblRougeEnnemi.setText(AppCore.TexteRougeEnnemi);
		lblNashor.setText(AppCore.TexteNashor);
		lblBleuOverlay.setText(lblBleu.getText());
		lblRougeOverlay.setText(lblRouge.getText());
		lblBleuEnnemiOverlay.setText(lblBleuEnnemi.getText());
		lblRougeEnnemiOverlay.setText(lblRougeEnnemi.getText());
		lblDragonOverlay.setText(lblDragon.getText());
		lblNashorOverlay.setText(lblNashor.getText());
	}



	@Override
	public void nativeKeyPressed(final NativeKeyEvent e) {
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputDragon)) 
		{				

			if(shiftKeyPressed)
			{
				signalStopF5 = true;
			}
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
								if((MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(1) > 9)
									AppCore.TexteDragon = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(1);
								else
									AppCore.TexteDragon = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(0) + " : 0" + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurDragon)).get(1);


								if(AppCore.CompteurDragon == 10 && AppCore.stateSounds)
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
								if (signalStopF5) 
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
										signalStopF5 = false;
										this.cancel();

									}
								}
							}
						}, 0, 1000);
			}
		}
		System.out.println(AppCore.inputBleu);
		System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
		System.out.println("'" + AppCore.inputBleu + "' = '" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "'");

		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputBleu)) 
		{
			if(shiftKeyPressed)
			{
				signalStopF1 = true;
			}
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
								if((MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(1) > 9)
									AppCore.TexteBleu = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(1);
								else
									AppCore.TexteBleu = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(0) + " : 0" + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleu)).get(1);


								if(AppCore.CompteurBleu == 10 && AppCore.stateSounds)
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
								if (signalStopF1) 
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
										signalStopF1 = false;
										this.cancel();

									}
								}
							}
						}, 0, 1000);
			}
		}
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputRouge)) 
		{
			if(shiftKeyPressed)
			{
				signalStopF2 = true;
			}
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
								if((MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(1) > 9)
									AppCore.TexteRouge = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(1);
								else
									AppCore.TexteRouge = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(0) + " : 0" + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRouge)).get(1);


								if(AppCore.CompteurRouge == 10 && AppCore.stateSounds)
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
								if (signalStopF2) 
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
										signalStopF2 = false;
										this.cancel();

									}
								}
							}
						}, 0, 1000);
			}
		}
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputBleuEnnemi)) 
		{
			if(shiftKeyPressed)
			{
				signalStopF3 = true;
			}
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
								if((MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(1) > 9)
									AppCore.TexteBleuEnnemi = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(1);
								else
									AppCore.TexteBleuEnnemi = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(0) + " : 0" + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurBleuEnnemi)).get(1);


								if(AppCore.CompteurBleuEnnemi == 10 && AppCore.stateSounds)
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
								if (signalStopF3) 
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
										signalStopF3 = false;
										this.cancel();

									}
								}
							}
						}, 0, 1000);
			}
		}
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputRougeEnnemi)) 
		{
			if(shiftKeyPressed)
			{
				signalStopF4 = true;
			}
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
								if((MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(1) > 9)
									AppCore.TexteRougeEnnemi = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(1);
								else
									AppCore.TexteRougeEnnemi = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(0) + " : 0" + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurRougeEnnemi)).get(1);


								if(AppCore.CompteurRougeEnnemi == 10 && AppCore.stateSounds)
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
								if (signalStopF4) 
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
										signalStopF4 = false;
										this.cancel();

									}
								}
							}
						}, 0, 1000);
			};
		}
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputNashor)) 
		{
			if(shiftKeyPressed)
			{
				signalStopF6 = true;
			}
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
								if((MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(1) > 9)
									AppCore.TexteNashor = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(0) + " : " + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(1);
								else
									AppCore.TexteNashor = (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(0) + " : 0" + (MiseEnFormeMinutesSecondes.getArrayMinutesSecondes(AppCore.CompteurNashor)).get(1);

								if(AppCore.CompteurNashor == 10 && AppCore.stateSounds)
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
								if (signalStopF6) 
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
										signalStopF6 = false;
										this.cancel();

									}
								}
							}
						}, 0, 1000);
			}
		}

		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputAnnulateur)) 
		{
			System.out.println("input annulateur");
			shiftKeyPressed = true;
		}

		System.out.println("ANNULATEUR : " + AppCore.inputAnnulateur);
		System.out.println("GROS TEST ICI " + e.getRawCode());

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(AppCore.inputAnnulateur)) 
		{
			shiftKeyPressed = false;
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
		if(AppCore.stateSounds)
			this.systemVoice.speak(thingToSay);
	}

	public void dispose()
	{
		this.systemVoice.deallocate();
	}
}

