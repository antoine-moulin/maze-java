package dijkstra;

import maze.Maze;

/**
 * Interface implementee par la classe MBox. Les sommets representeront des cases.
 * @see maze.MBox
 */
public interface VertexInterface 
{
	/**
	 * Renvoie le labyrinthe qui contient la case.
	 * @return Maze
	 */
	public Maze getMaze();
	
	/**
	 * Renvoie le numero de la ligne sur laquelle se situe la case.
	 * @return int : numero de la ligne.
	 */
	public int getx();
	
	/**
	 * Renvoie le numero de la colonne sur laquelle se situe la case.
	 * @return int : numero de la colonne.
	 */
	public int gety();
	
	/**
	 * Renvoie le genre de case dont il s'agit (EBox, WBox, etc.).
	 * @return String : E pour EBox, W pour WBox, D pour DBox, A pour ABox.
	 */
	public String getLabel();
}
