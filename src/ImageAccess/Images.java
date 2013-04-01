package ImageAccess;

public class Images {
	
	private Images(){}
	
	public static String getLienImage(String nomChampion)
	{
		return "http://edge1.mobafire.com/images/champion/icon/" + nomChampion + ".png";	
	}
}
