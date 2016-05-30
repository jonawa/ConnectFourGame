package db;

import java.util.Map;

import util.Helper;

import java.util.HashMap;

/**
 * 
 * @author jcawa
 *
 */
public class TestDB {
	
	private static TestDB testDB;
	private  Map<int[][], HashMap<Integer, Integer>> db;
	
	private TestDB() {
		db = new HashMap<int[][], HashMap<Integer,Integer>>();
	}
	
	/**
	 * Singelton Implementaion, hiermit auf die DB zugreifen.
	 * @return
	 */
	public static TestDB getDB(){
		if (testDB == null)
			testDB = new TestDB();
		return testDB;
	}
	
	public void put(int[][] state, int action, int value){
		//TODO Das kann doch eigentlich nicht effizient sein oder?
		
		
		
		
		//Wenn der Zustand/ das aktuelle Board bereits in der DB gespeichert ist:
		if (db.containsKey(state)){
			
			 HashMap<Integer, Integer> key;
			 key = db.get(state);
			 if (key == null){
				 //Wenn kein Spielzug für Boardstate vorhanden, füge den aktuellen hinzu:
				System.out.println("Sollte nicht auftreten, wenn Boardstate vorhanden dann ist auch erster Spielzug vorhanden");
			 }
			 else{
				 if (key.containsKey(action)){
					 //do nothing, weil hier ansonsten nur der value verändert werden könnte, Zug ist schon vorhanden
					 //und muss der DB nicht mehr hinzugefügt werden
				 }
				 else {
					 key.put(action, value);
				 }
			 }
		}
		
		//Wenn der aktuelle Zustand noch nicht in der DB gespeichert ist.
		else{
			//neue HashMap erzeugen:
			//TODO könnte man mit max mögliche Züge initialisieren
			HashMap<Integer, Integer> key = new HashMap<Integer,Integer>();
			key.put(action, value);
			db.put(state, key);
			
			
			
		}
			
		
	}

	/**
	 * is currently used to either print out all data points or just the database size
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
//		for(int[][] key : db.keySet()){
//
//			
//			HashMap<Integer, Integer> value = db.get(key);
//			
//			
//			for(Integer intKey: value.keySet()){
//				sb.append(key);
//				/* sb.append(Helper.convertIntBoardTo1DString(key)); */
//				sb.append("\t");
//				
//				sb.append(intKey);
//				sb.append("\t");
//				
//				sb.append("value"); //bisher noch nicht drin
//				sb.append("\n");
//				
//
//				
//			}
//			
//			
//		}
		
		sb.append("Die Menge der Keys in der DB: " + db.size());
		return sb.toString();
	}
}
