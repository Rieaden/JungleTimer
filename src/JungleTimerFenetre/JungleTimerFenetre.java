package JungleTimerFenetre;





import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.sun.scenario.effect.impl.prism.PrImage;

import Util.AppCore;

public class JungleTimerFenetre extends Stage implements NativeKeyListener
{ 

	Label lblDragon = new Label(AppCore.TexteDragon);
	Label lblBleu = new Label("Bleu : ");
	Label lblRouge = new Label("Rouge : ");
	Label lblBleuEnnemi = new Label("Bleu Ennemi : ");
	Label lblRougeEnnemi = new Label("Rouge Ennemi : ");
	Label lblNashor = new Label("Nashor : ");

	int TimerDragon = 300;

	public boolean flagDragon = false;

	final Object stage = this;
	
	Stage master = new Stage();
	
	public JungleTimerFenetre(Stage primaryStage)
	{

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

		//		Label lblDragon = new Label("Dragon : ");
		//		Label lblBleu = new Label("Bleu : ");
		//		Label lblRouge = new Label("Rouge : ");
		//		Label lblBleuEnnemi = new Label("Bleu Ennemi : ");
		//		Label lblRougeEnnemi = new Label("Rouge Ennemi : ");
		//		Label lblNashor = new Label("Nashor : ");

		grid.add(lblDragon, 0, 0);
		grid.add(lblBleu, 0, 1);
		grid.add(lblRouge, 0, 2);
		grid.add(lblBleuEnnemi, 0, 3);
		grid.add(lblRougeEnnemi, 0, 4);
		grid.add(lblNashor, 0, 5);

		this.setScene(new Scene(grid, 300, 200));
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
			 refreshLabels();
		 }
		 if (e.getKeyCode() == NativeKeyEvent.VK_F2) 
		 {
			 System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			 AppCore.TexteDragon = "Bleu : Tué";
			 refreshLabels();
		 }
		 if (e.getKeyCode() == NativeKeyEvent.VK_F3) 
		 {
			 System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			 AppCore.TexteDragon = "Rouge : Tué";
			 refreshLabels();
		 }
		 if (e.getKeyCode() == NativeKeyEvent.VK_F4) 
		 {
			 System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			 AppCore.TexteDragon = "Bleu ennemi : Tué";
			 refreshLabels();
		 }
		 if (e.getKeyCode() == NativeKeyEvent.VK_F5) 
		 {
			 System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			 AppCore.TexteDragon = "Rouge ennemi : Tué";
			 refreshLabels();
		 }
		 if (e.getKeyCode() == NativeKeyEvent.VK_F6) 
		 {
			 System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
			 AppCore.TexteDragon = "Nashor : Tué";
			 refreshLabels();
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
