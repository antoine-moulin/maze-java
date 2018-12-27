package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Classe implementant l'interface PreviousInterface et qui est utilisee dans Dijkstra.
 * @see PreviousInterface
 * @see Dijkstra
 */
public class Previous implements PreviousInterface
{	
	/**
	 * @see Hashtable
	 */
	private Hashtable<VertexInterface,VertexInterface> ht;

	/**
	 * Constructeur, initialisation de ht.
	 */
	public Previous()
	{
		ht = new Hashtable<VertexInterface, VertexInterface>(); 
	}

	/**
	 * Renvoie l'attribut ht.
	 * @return Hashtable
	 */
	public Hashtable<VertexInterface, VertexInterface> getHashtable() 
	{
		return ht;
	}

	/**
	 * Modifie le pere d'un sommet.
	 * @param vertex sommet
	 * @param pivot sommet
	 */
	public void set(VertexInterface vertex, VertexInterface pivot) 
	{
		ht.put(vertex, pivot);
	}

	/**
	 * Renvoie le pere d'un sommet.
	 * @param vertex sommet
	 * @return VertexInterface 
	 */
	public VertexInterface get(VertexInterface vertex) 
	{
		return ht.get(vertex);
	}

	/**
	 * Renvoie le chemin (une liste de sommets) le plus court vers un sommet.
	 * @param vertex sommet
	 * @return ArrayList : contient le chemin le plus court vers le sommet vertex.
	 */
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex)
	{
		ArrayList<VertexInterface> path = new ArrayList<VertexInterface>();

		while (vertex != null)
		{
			path.add(0,vertex);
			vertex = get(vertex);
		}
		return path;
	}
}
