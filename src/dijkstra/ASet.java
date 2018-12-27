package dijkstra;

import java.util.HashSet;

/**
 * Classe implementant l'interface ASetInterface et qui est utilisee dans Dijkstra.
 * @see ASetInterface 
 * @see Dijkstra
 */

public class ASet implements ASetInterface 
{
	/**
	 * @see HashSet
	 */
	private HashSet<VertexInterface> a;

	/**
	 * Constructeur, initialisation de a.
	 */
	public ASet() 
	{
		this.a = new HashSet<VertexInterface>();
	}

	/**
	 * Permet d'ajouter un sommet a l'ensemble a.
	 * @param vertex sommet
	 */
	public void add(VertexInterface vertex) 
	{
		this.a.add(vertex);
	}

	/**
	 * Verifie si vertex est dans l'ensemble a.
	 * @param vertex sommet
	 * @return Booleen (true si le sommet est dans l'ensemble, false sinon)
	 */
	public boolean contains(VertexInterface vertex) 
	{
		return a.contains(vertex);
	}
}
