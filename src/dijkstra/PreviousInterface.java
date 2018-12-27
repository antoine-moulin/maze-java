package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Interface implementee par la classe Previous. 
 * @see Previous
 */
public interface PreviousInterface 
{
	/**
	 * Modifie le pere d'un sommet.
	 * @param vertex sommet
	 * @param pivot sommet
	 */
	public void set(VertexInterface vertex, VertexInterface pivot);
	
	/**
	 * Renvoie le pere d'un sommet.
	 * @param vertex sommet
	 * @return VertexInterface 
	 */
	public VertexInterface get(VertexInterface vertex);
	
	/**
	 * Renvoie le chemin (une liste de sommets) le plus court vers un sommet.
	 * @param vertex sommet
	 * @return ArrayList : contient le chemin le plus court vers le sommet vertex.
	 */
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
	
	/**
	 * Renvoie l'attribut ht
	 * @return Hashtable
	 */
	public Hashtable<VertexInterface, VertexInterface> getHashtable();
}
