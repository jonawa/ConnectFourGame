package db;


/**
 * Interface f�r unsere Datenbank. 
 * Idee ist folgende:
 * Dadurch das nichts von der Implementierung nach au�en kommt, k�nnen wir die Datenbank intern beliebig ver�ndern,
 * ohne das im restlichen Programm was gemacht werden muss.
 * 
 * Eingef�gt in die Datenbank wird:
 * 2D Array mit dem Spielzustand (Board State), einen m�glichen Zug (action) und die Bewertung (value)
 * 
 * Board State als 2D Array, intern wird diese entweder so eingespeichert oder bin�r codiert, k�nnen wir dann beliebig ver�ndern
 * 
 * 
 * Datenbank bietet weiterhin die M�glichkeiten:
 * 1. Einen Spielzustand zu finden und einen weiteren Zug hinzuzuf�gen
 * 2. Einen Spielzustand anzugeben und den h�chsten bewerten Zug zur�ckzubekommen
 * 3. ..
 * 
 * 
 * Intern benutzt die Datenbank erstmal folgendes Schema: Map<int[][],Map<int[],int>> also: Map<state,Map<action,value>>
 * @author jcawa
 *
 */
public interface IDatabase {
	
	

}
