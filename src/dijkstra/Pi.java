package dijkstra;

import java.util.Hashtable;

/**
 * Classe implementant l'interface PiInterface et qui est utilisee dans Dijkstra.
 * @see PiInterface
 * @see Dijkstra
 */
public class Pi implements PiInterface
{
	/**
	 * @see Hashtable
	 */
	private Hashtable<VertexInterface,Integer> ht;

	/**
	 * Constructeur, initialisation de ht.
	 */
	public Pi() 
	{
		ht = new Hashtable<VertexInterface,Integer>();
	}

	/**
	 * Modifie la valeur de l'image du sommet par la fonction Pi (correspond a une distance).
	 * @param vertex sommet
	 * @param i nouvelle valeur
	 */
	public void set(VertexInterface vertex, int i) 
	{
		ht.put(vertex, new Integer(i));
	}

	/**
	 * Donne la valeur de l'image du sommet par la fonction Pi (correspond a une distance).
	 * @param vertex sommet
	 * @return int : valeur de l'image du sommet par la fonction Pi.
	 */
	public int get(VertexInterface vertex) 
	{
		return ht.get(vertex).intValue();
	}
}

