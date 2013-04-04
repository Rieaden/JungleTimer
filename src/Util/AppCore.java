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
	public static boolean stateOverlay = true;
	

	public static ArrayList<String>  configDefault = new ArrayList<String>() {/**
	 * 
	 */
		private static final long serialVersionUID = 8597913846290863636L;

		{ 
			add("Bleu : F1"); 
			add("Rouge : F2");
			add("BleuEnnemi : F5");
			add("RougeEnnemi : F6");
			add("Dragon : F3");
			add("Nashor : F4");
			add("Annulateur : Maj");
			add("Sounds : YES");
			add("Overlay : YES");
		}};

		public static ArrayList<String>  listChampionsDefault = new ArrayList<String>() {/**
		 * 
		 */
			private static final long serialVersionUID = 8597913846290863636L;
			{ 
				add("ahri"); 
				add("akali"); 
				add("alistar"); 
				add("amumu"); 
				add("anivia"); 
				add("annie"); 
				add("ashe"); 
				add("blitzcrank"); 
				add("Brand"); 
				add("caitlyn"); 
				add("cassiopeia"); 
				add("chogath"); 
				add("corki"); 
				add("darius"); 
				add("diana"); 
				add("dr-mundo"); 
				add("draven"); 
				add("elise"); 
				add("evelynn"); 
				add("ezreal"); 
				add("fiddlesticks"); 
				add("fiora"); 
				add("fizz"); 
				add("galio"); 
				add("gangplank"); 
				add("garen"); 
				add("gragas"); 
				add("graves"); 
				add("hecarim"); 
				add("heimerdinger"); 
				add("irelia"); 
				add("janna"); 
				add("jarvan-iv"); 
				add("jax"); 
				add("jayce"); 
				add("karma"); 
				add("karthus"); 
				add("kassadin"); 
				add("katarina"); 
				add("kayle"); 
				add("kennen"); 
				add("khazix"); 
				add("kogmaw"); 
				add("leblanc"); 
				add("lee-sin"); 
				add("leona"); 
				add("lulu"); 
				add("lux"); 
				add("malphite"); 
				add("malzahar"); 
				add("maokai"); 
				add("master-yi"); 
				add("miss-fortune"); 
				add("mordekaiser"); 
				add("morgana"); 
				add("nami"); 
				add("nasus"); 
				add("nautilus"); 
				add("nidalee"); 
				add("nocturne"); 
				add("nunu"); 
				add("olaf"); 
				add("orianna"); 
				add("pantheon"); 
				add("poppy"); 
				add("quinn"); 
				add("rammus"); 
				add("renekton"); 
				add("rengar"); 
				add("riven"); 
				add("rumble"); 
				add("ryze"); 
				add("sejuani"); 
				add("shaco"); 
				add("shen"); 
				add("shyvana"); 
				add("singed"); 
				add("sion"); 
				add("sivir"); 
				add("skarner"); 
				add("sona"); 
				add("soraka"); 
				add("swain"); 
				add("syndra"); 
				add("talon"); 
				add("taric"); 
				add("teemo"); 
				add("thresh"); 
				add("tristana"); 
				add("trundle"); 
				add("tryndamere"); 
				add("twisted-fate"); 
				add("twitch"); 
				add("udyr"); 
				add("urgot"); 
				add("varus"); 
				add("vayne"); 
				add("veigar"); 
				add("vi"); 
				add("viktor"); 
				add("vladimir"); 
				add("volibear"); 
				add("warwick"); 
				add("wukong"); 
				add("xerath"); 
				add("xin-zhao"); 
				add("yorick"); 
				add("zac"); 
				add("zed"); 
				add("ziggs"); 
				add("zilean"); 
				add("zyra"); }};

				public static void refreshValues()
				{
					CompteurDragon = 15;
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


