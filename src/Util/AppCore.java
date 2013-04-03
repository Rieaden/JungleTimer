package Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	
	public static String inputBleu = "F1";
	public static String inputRouge = "F2";
	public static String inputBleuEnnemi = "F3";
	public static String inputRougeEnnemi = "F4";
	public static String inputDragon = "F5";
	public static String inputNashor = "F6";
	public static String inputAnnulateur = "F7";
	public static boolean stateSounds = true;
	

	public static void refreshValues()
	{
		CompteurDragon = 20;
		CompteurNashor = 20;
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
	
	public static void loadParameters()
	{
		String fichier = "config.ini";
		ArrayList<String> listParam = new ArrayList<String>();
		
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
		
		inputBleu = (listParam.get(0).substring(listParam.get(0).lastIndexOf(" ")+1));
		inputRouge = (listParam.get(1).substring(listParam.get(1).lastIndexOf(" ")+1));
		inputBleuEnnemi = (listParam.get(2).substring(listParam.get(2).lastIndexOf(" ")+1));
		inputRougeEnnemi = (listParam.get(3).substring(listParam.get(3).lastIndexOf(" ")+1));
		inputDragon = (listParam.get(4).substring(listParam.get(4).lastIndexOf(" ")+1));
		inputNashor = (listParam.get(5).substring(listParam.get(5).lastIndexOf(" ")+1));
		inputAnnulateur = (listParam.get(6).substring(listParam.get(6).lastIndexOf(" ")+1));
		if(listParam.get(7).substring(listParam.get(7).lastIndexOf(" ")+1).contains("YES"))
		{
			stateSounds = true;
		}
		else
		{
			stateSounds = false;
		}
		
	}
	
	}


