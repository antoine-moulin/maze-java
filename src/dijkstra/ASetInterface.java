package dijkstra;

/**
 * Interface implementee par la classe ASet.
 * @see ASet
 */

public interface ASetInterface 
{
	/**
	 * Ajoute le sommet à l'ensemble. 
	 * @param vertex sommet
	 */
	void add(VertexInterface vertex);
	
	/**
	 * Indique si le sommet est dans l'ensemble.
	 * @param vertex sommet
	 * @return boolean : true si le sommet est dans l'ensemble, false sinon.
	 */
	public boolean contains(VertexInterface vertex);
}
