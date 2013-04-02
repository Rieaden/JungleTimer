package Util;

import java.util.ArrayList;

public class MiseEnFormeMinutesSecondes 
{
	private MiseEnFormeMinutesSecondes(){};
	
	public static ArrayList<Integer> getArrayMinutesSecondes(int Secondes)
	{
		ArrayList<Integer> coupleMinutesSecondes = new ArrayList<Integer>();
		coupleMinutesSecondes.add(Secondes/60);
		coupleMinutesSecondes.add(Secondes-(coupleMinutesSecondes.get(0)*60));
		return coupleMinutesSecondes;
		
	}
}
