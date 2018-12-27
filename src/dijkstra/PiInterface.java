package dijkstra;

/**
 * Interface implementee par la classe Pi.
 * @see Pi
 */
public interface PiInterface 
{
	/**
	 * Donne la valeur de l'image du sommet par la fonction Pi (correspond a une distance).
	 * @param vertex sommet
	 * @return int : valeur de l'image du sommet par la fonction Pi.
	 */
	public int get(VertexInterface vertex);
	
	/**
	 * Modifie la valeur de l'image du sommet par la fonction Pi (correspond a une distance).
	 * @param vertex sommet
	 * @param i nouvelle valeur
	 */
	public void set(VertexInterface vertex,int i);
}
