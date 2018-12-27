package maze;

/**
 * Classe representant une case libre. Elle herite de la classe MBox.
 * @see MBox
 */
public class EBox extends MBox
{
	/**
	 * Constructeur : cree une case de type EBox dans le labyrinthe maze, a la ligne x et a la colonne y.
	 * @param maze labyrinthe dans lequel se trouve la case
	 * @param x ligne
	 * @param y colonne
	 */
	public EBox(Maze maze, int x, int y) 
	{
		super(maze, x, y);
	}

	/**
	 * Renvoie le type de case sous forme de chaine de caractere.
	 * @return String
	 */
	public String getLabel()
	{
		return("E");	
	}
}
