package ihm;

import java.awt.GridLayout;

import javax.swing.JPanel;

import maze.Maze;

/**
 * Classe heritant de la classe JPanel. Cette classe permet de representer graphiquement le labyrinthe.
 * @see JPanel
 */
public class Labyrinthe extends JPanel 
{	
	private static final long serialVersionUID = 1L;

	/**
	 * Labyrinthe qui va etre represente.
	 */
	private Maze graph = new Maze();

	/**
	 * Tableau bidimensionnel de boutons qui contiendra les cases.
	 */
	private Bouton[][] bouton_tab;

	/**
	 * Constructeur : initialise un panneau Labyrinthe avec un labyrinthe predefini.
	 */
	public Labyrinthe() 
	{	
		graph.initFromTextFile("data/labyrinthe.txt");
		this.setLayout(new GridLayout(graph.getHeight(), graph.getWidth()));
		bouton_tab = new Bouton[graph.getHeight()][graph.getWidth()];
		for (int i = 0; i < graph.getHeight(); i++) 
		{
			for (int j = 0; j < graph.getWidth(); j++) 
			{
				Bouton bouton = new Bouton(graph, i,j);
				bouton_tab[i][j]=bouton;
				this.add(bouton);
			}
		}
		this.setVisible(true);
	}

	/**
	 * Constructeur : initialise un panneau Labyrinthe avec un labyrinthe donne en parametre.
	 * @param graph labyrinthe
	 */
	public Labyrinthe(Maze graph) 
	{	
		this.graph=graph;
		this.setLayout(new GridLayout(graph.getHeight(), graph.getWidth()));
		bouton_tab = new Bouton[graph.getHeight()][graph.getWidth()];
		for (int i = 0; i < graph.getHeight(); i++) 
		{
			for (int j = 0; j < graph.getWidth(); j++) 
			{
				Bouton bouton = new Bouton(graph, i,j);
				bouton_tab[i][j]=bouton;
				this.add(bouton);
			}
		}
		this.setVisible(true);
	}

	/**
	 * Renvoie l'attribut contenant le labyrinthe de la classe.
	 * @return Maze 
	 */
	public Maze getGraph() 
	{
		return graph;
	}

	/**
	 * Renvoie le tableau de boutons.
	 * @return Bouton[][]
	 */
	public Bouton[][] getBouton_tab() 
	{
		return bouton_tab;
	}

	/**
	 * Modifie l'attribut contenant le labyrinthe de la classe Maze
	 * @param graph nouveau labyrinthe
	 */
	public void setGraph(Maze graph) 
	{
		this.graph = graph;
	}

	/**
	 * Modifie le tableau de boutons.
	 * @param bouton_tab nouveau tableau
	 */
	public void setBouton_tab(Bouton[][] bouton_tab) 
	{
		this.bouton_tab = bouton_tab;
	}
}