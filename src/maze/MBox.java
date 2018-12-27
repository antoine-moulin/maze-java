package maze;

import dijkstra.VertexInterface;

/**
 * Classe implementant l'interface VertexInterface. C'est une classe abstraite qui represente une case d'un labyrinthe. C'est la classe mere de ABox, DBox, EBox, WBox.
 * @see VertexInterface
 * @see ABox
 * @see DBox
 * @see EBox
 * @see WBox
 */
public abstract class MBox implements VertexInterface
{
	/**
	 * Represente la ligne a laquelle se situe la case. Celle-ci n'est pas destinee a changer.
	 */
	private final int x;
	
	/**
	 * Represente la colonne a laquelle se situe la case. Celle-ci n'est pas destinee a changer.
	 */
	private final int y;
	
	/**
	 * Represente le labyrinthe dans lequel se situe la case. Celui-ci n'est pas destine a changer.
	 */
	private final Maze maze;

	/**
	 * Constructeur MBox : cree une case de type ABox dans le labyrinthe maze, a la ligne x et a la colonne y.
	 * @param maze labyrinthe dans lequel se trouve la case
	 * @param x ligne
	 * @param y colonne
	 */
	public MBox(Maze maze, int x, int y )
	{
		this.maze = maze;
		this.x = x;
		this.y = y;
	}

	/**
	 * Renvoie le labybyrinthe qui contient la case et cela independamment de son type, d'ou la declaration final. 
	 * @return Maze
	 */
	public final Maze getMaze() 
	{
		return maze;
	}
	
	/**
	 * Renvoie la ligne de la case et cela independamment de son type, d'ou la declaration final. 
	 * @return int : numero de la ligne
	 */
	public final int getx()
	{
		return x;
	}

	/**
	 * Renvoie la colonne de la case et cela independamment de son type, d'ou la declaration final. 
	 * @return int : numero de la colonne
	 */
	public final int gety()
	{
		return y;
	}
	
	/**
	 * Renvoie le type de case sous forme d'une chaine de caractere. 
	 */
	abstract public String getLabel();
}
