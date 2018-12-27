package maze;

/**
 * Classe representant une case avec un mur. Elle herite de la classe MBox.
 * @see MBox
 */
public class WBox extends MBox
{
	/**
	 * Constructeur : cree une case de type WBox dans le labyrinthe maze, a la ligne x et a la colonne y.
	 * @param maze labyrinthe dans lequel se trouve la case
	 * @param x ligne
	 * @param y colonne
	 */
	public WBox(Maze maze, int x, int y) 
	{
		super(maze, x, y);
	}

	/**
	 * Renvoie le type de case sous forme de chaine de caractere.
	 * @return String
	 */
	public String getLabel()
	{
		return("W");	
	}
}
