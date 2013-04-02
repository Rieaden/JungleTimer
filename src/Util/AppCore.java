package Util;

public class AppCore {

	public static String TexteDragon = "Alive";
	public static String TexteBleu = "Alive";
	public static String TexteRouge = "Alive";
	public static String TexteBleuEnnemi = "Alive";
	public static String TexteRougeEnnemi = "Alive";
	public static String TexteNashor = "Alive";
	
	public static int CompteurDragon;
	public static int CompteurNashor;
	public static int CompteurBleu;
	public static int CompteurRouge;
	public static int CompteurBleuEnnemi;
	public static int CompteurRougeEnnemi;
	

	public static void refreshValues()
	{
		CompteurDragon = 360;
		CompteurNashor = 420;
		CompteurBleu   = 300;
		CompteurRouge  = 300;
		CompteurBleuEnnemi = 300;
		CompteurRougeEnnemi = 300;
		
		TexteDragon = "Alive";
		TexteBleu = "Alive";
		TexteRouge = "Alive";
		TexteBleuEnnemi = "Alive";
		TexteRougeEnnemi = "Alive";
		TexteNashor = "Alive";
	}
}

