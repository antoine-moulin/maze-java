package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import maze.Maze;
import maze.WBox;
import maze.EBox;
import maze.ABox;
import maze.DBox;

/**
 * Classe heritant de JButton et implementant l'interface MouseListener. Cette classe permet de representer graphiquement une case.
 * @see JButton
 * @see MouseListener
 */
public class Bouton extends JButton implements MouseListener
{
	private static final long serialVersionUID = 1L;

	/**
	 * Attribut donnant la couleur de la case. EBox : blanche. WBox : noire. ABox : bleue. DBox : jaune.
	 */
	private Color color;

	/**
	 * Ligne a laquelle se situe la case.
	 */
	private int x;

	/**
	 * Colonne a laquelle se situe la case.
	 */
	private int y;

	/**
	 * Labyrinthe auquel appartient la case.
	 */
	private Maze maze;

	/**
	 * Constructeur : cree un Bouton appartenant a maze et situe a la ligne x et a la colonne y.
	 * @param maze labyrinthe
	 * @param x ligne
	 * @param y colonne
	 */
	public Bouton(Maze maze, int x , int y) 
	{
		super();
		this.x=x;
		this.y=y;
		this.maze = maze;
		paint();

		this.addMouseListener(this);
	}

	/**
	 * Colorie la case selon son type.
	 */
	private void paint() 
	{
		if (this.maze.getBox(x,y) instanceof EBox)
			color = Color.WHITE;
		else if (this.maze.getBox(x,y) instanceof WBox)
			color = Color.BLACK;
		else if (this.maze.getBox(x,y) instanceof DBox)
			color = Color.YELLOW;
		else if (this.maze.getBox(x,y) instanceof ABox)
			color = Color.BLUE;
		this.repaint();
	}

	/**
	 * Change l'attribut maze.
	 * @param maze labyrinthe
	 */
	public void changeMaze(Maze maze) 
	{
		this.maze=maze;
		paint();
	}

	/**
	 * Renvoie la ligne a laquelle se situe la case.
	 * @return int : numero de ligne
	 */
	public int getx() 
	{
		return x;
	}

	/**
	 * Renvoie la colonne a laquelle se situe la case.
	 * @return int : numero de colonne
	 */
	public int gety() 
	{
		return y;
	}

	/**
	 * Renvoie la couleur de la case.
	 * @return Color
	 */
	public Color getColor() 
	{
		return color;
	}

	/**
	 * Modifie la couleur de la case.
	 * @param color nouvelle couleur
	 */
	public void setColor(Color color) 
	{
		this.color = color;
	}

	/**
	 * Dessine la case.
	 */
	public void paintComponent(Graphics g) 
	{
		g.setColor(color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Permet de modifier le type de la case lorsque l'on clique dessus.
	 */
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (color == Color.WHITE) // Si la case est libre ...
		{ 
			color = Color.BLACK; // ... on met un mur ...
			maze.setBox(new WBox(maze, x, y)); // ... et on change le type de la case pour indiquer qu'il s'agit maintenant d'un mur
		}
		else if (color == Color.BLACK) // Si la case est un mur ...
		{
			color = Color.YELLOW; // ... on met un depart ...
			maze.setBox(new DBox(maze, x, y)); // ... et on change le type de la case
		}
		else if (color == Color.YELLOW) // Si la case est un depart ...
		{
			color = Color.BLUE; // ... on met une arrivee ...
			maze.setBox(new ABox(maze, x, y)); // ... et on change le type de la case
		}
		else if (color == Color.BLUE) // Si la case est une arrivee ...
		{
			color = Color.WHITE; // ... on met une case libre ...
			maze.setBox(new EBox(maze, x, y)); // ... et on change le type de la case
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override 
	public String toString() {
		return "x = "+x;
	}
}
