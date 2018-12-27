package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import dijkstra.VertexInterface;
import maze.Maze;

/**
 * Classe heritant de la classe JFrame et implementant l'interface ActionListener. 
 * <p>
 * Un objet de cette classe contient un menu (de type JMenuBar) avec les fonctionnalites et un panneau (de type Labyrinthe) affichant le labyrinthe.
 * @see JFrame
 * @see JMenuBar
 * @see JMenu
 * @see JMenuItem
 * @see Labyrinthe
 * @see ActionListener
 * @see MouseListener
 */
public class Fenetre extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	/**
	 * Labyrinthe de la classe Maze.
	 */
	private Maze maze = new Maze();

	/**
	 * Panneau qui va afficher le labyrinthe.
	 */
	private Labyrinthe labyrinthe = new Labyrinthe();

	/**
	 * Menu qui permet d'acceder aux fonctionnalites.
	 */
	private JMenuBar menu = new JMenuBar();

	/**
	 * Onglets du menu : File pour gerer le fichier (sauvegarder, charger, nouveau etc.), Run pour resoudre le labyrinthe ou l'editer et Size pour modifier la taille.
	 */
	private JMenu fichier = new JMenu("File"),
			run = new JMenu("Run"),
			size = new JMenu("Size")
			;
	
	/**
	 * Differentes fonctionnalites
	 */
	private JMenuItem 
	editer = new JMenuItem("Edit"),
	save = new JMenuItem("Save"),
	charger = new JMenuItem("Load"),
	lancer = new JMenuItem("Launch"),
	regles = new JMenuItem("Rules"),
	nouveau = new JMenuItem("New"),
	newLine = new JMenuItem("New Line"),
	newColumn = new JMenuItem("New Column"),
	delColumn = new JMenuItem("Delete Column"),
	delLine = new JMenuItem("Delete Line")
	;


	/**
	 * Constructeur : initialise une fenetre contenant deux panneaux, le menu et le labyrinthe.
	 */
	public Fenetre() 
	{
		maze.initFromTextFile("data/labyrinthe.txt");
		labyrinthe = new Labyrinthe(maze);
		this.setSize(800,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Labyrinthe");
		this.setLocationRelativeTo(null);
		BorderLayout layout = new BorderLayout();
		this.getContentPane().setLayout(layout);

		nouveau.addActionListener(this);
		editer.addActionListener(this);
		save.addActionListener(this);
		charger.addActionListener(this);
		regles.addActionListener(this);
		lancer.addActionListener(this);
		newLine.addActionListener(this);
		newColumn.addActionListener(this);
		delLine.addActionListener(this);
		delColumn.addActionListener(this);

		menu.add(fichier);
		menu.add(run);
		menu.add(size);
		fichier.add(nouveau);
		run.add(lancer);
		run.add(editer);
		fichier.add(save);
		fichier.add(charger);
		fichier.add(regles);
		size.add(newLine);
		size.add(newColumn);
		size.add(delLine);
		size.add(delColumn);

		this.setJMenuBar(menu); 
		this.getContentPane().add(labyrinthe,BorderLayout.CENTER);

		this.setVisible(true);	
	}

	/**
	 * Methode qui regarde d'ou vient l'action effectuee et agit en consequence. Permet aux boutons du menu de remplir leur fonction.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Object bouton = arg0.getSource();
		if (bouton == nouveau)
			this.newMaze();
		if (bouton == editer)
			this.editMaze();
		if (bouton == save)
			this.saveMaze();
		if (bouton == charger)
			this.loadMaze();
		if (bouton == regles)
			this.displayRules();
		if (bouton == lancer)
			this.launchMaze();
		if (bouton==newLine)
			this.newLine();
		if (bouton == newColumn)
			this.newColumn();
		if (bouton == delLine)
			this.delLine();
		if (bouton ==delColumn)
			this.delColumn();
	}

	/**
	 * Methode permettant de lancer l'algorithme pour resoudre le labyrinthe. Executee lorsque l'on clique sur le bouton lancer.
	 */
	private void launchMaze() 
	{
		// On declare les variables depart et arrivee pour actualiser les variables aBoxState et dBoxState, qu'on recupere ensuite
		@SuppressWarnings("unused")
		VertexInterface depart = labyrinthe.getGraph().searchDeparture(), arrivee = labyrinthe.getGraph().searchArrival(); 
		int aBoxState = labyrinthe.getGraph().getaBoxState(), dBoxState = labyrinthe.getGraph().getdBoxState();

		// On distingue tous les cas possibles et on affiche le chemin entre le depart et l'arrivee ou on renvoie une erreur  
		if (aBoxState == 1 && dBoxState == 1) 
		{	
			ArrayList<VertexInterface> path = labyrinthe.getGraph().getPath();
			if (path == null)
				JOptionPane.showMessageDialog(null, "Il n'y a pas de chemin possible.", "Erreur", JOptionPane.ERROR_MESSAGE);
			else
			{
				int n = path.size();

				// On retire le depart et l'arrivee pour ne colorier que les cases intermediaires
				ArrayList<VertexInterface> subpath = path;
				subpath.remove(n-1);
				subpath.remove(0);

				// On arrete l'algorithme un instant pour que le chemin s'affiche progressivement apres avoir clique sur "Lancer"
				new Thread(new Runnable() {
					public void run() {
						for (VertexInterface box : subpath) 
						{
							int i = box.getx();
							int j = box.gety();
							labyrinthe.getBouton_tab()[i][j].setColor(Color.RED); // On dessine le chemin en rouge
							labyrinthe.getBouton_tab()[i][j].repaint();
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		}
		else if (aBoxState == 1 && dBoxState == 0)
			JOptionPane.showMessageDialog(null, "Il n'y a pas de depart.", "Erreur", JOptionPane.ERROR_MESSAGE);
		else if (aBoxState == 1 && dBoxState == -1)
			JOptionPane.showMessageDialog(null, "Il y a trop de departs.", "Erreur", JOptionPane.ERROR_MESSAGE);
		else if (aBoxState == 0 && dBoxState == 0)
			JOptionPane.showMessageDialog(null, "Il n'y a ni depart ni arrivee.", "Erreur", JOptionPane.ERROR_MESSAGE);
		else if (aBoxState == 0 && dBoxState == 1)
			JOptionPane.showMessageDialog(null, "Il n'y a pas d'arrivee.", "Erreur", JOptionPane.ERROR_MESSAGE);
		else if (aBoxState == 0 && dBoxState == -1)
			JOptionPane.showMessageDialog(null, "Il y a trop de departs et il n'y a pas d'arrivee.", "Erreur", JOptionPane.ERROR_MESSAGE);
		else if (aBoxState == -1 && dBoxState == 0)
			JOptionPane.showMessageDialog(null, "Il n'y a pas de depart et il y a trop d'arrivees.", "Erreur", JOptionPane.ERROR_MESSAGE);
		else if (aBoxState == -1 && dBoxState == 1)
			JOptionPane.showMessageDialog(null, "Il y a trop d'arrivees.", "Erreur", JOptionPane.ERROR_MESSAGE);
		else if (aBoxState == -1 && dBoxState == -1)
			JOptionPane.showMessageDialog(null, "Il y a trop de departs et d'arrivees.", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Methode permettant de modifier le labyrinthe. Executee lorsque l'on clique sur le bouton editer.
	 */
	private void editMaze() 
	{
		// On parcourt tout le labyrinthe pour effacer le chemin, afin de pouvoir modifier le labyrinthe par la suite
		int n = labyrinthe.getBouton_tab().length, m = labyrinthe.getBouton_tab()[0].length;
		for (int i=0; i<n; i++) 
		{
			for (int j=0; j<m; j++) 
			{
				if (labyrinthe.getBouton_tab()[i][j].getColor() == Color.RED)
				{
					labyrinthe.getBouton_tab()[i][j].setColor(Color.WHITE);
					labyrinthe.getBouton_tab()[i][j].repaint();
				}
			}
		}
	}

	/**
	 * Methode permettant de sauvegarder le labyrinthe. Executee lorsque l'on clique sur le bouton sauvegarder.
	 */
	private void saveMaze() 
	{
		labyrinthe.getGraph().saveToTextFile("data/labyrinthe2");
	}

	/**
	 * Methode permettant de creer un nouveau labyrinthe. Executee lorsque l'on clique sur le bouton lancer.
	 */
	private void newMaze() 
	{
		maze.initFromTextFile("data/vide.txt");
		this.getContentPane().remove(labyrinthe);
		labyrinthe= new Labyrinthe(maze);

		this.getContentPane().add(labyrinthe,BorderLayout.CENTER);
		this.setVisible(true);
	}

	/**
	 * Methode permettant de charger le labyrinthe sauvegarde au prealable. Executee lorsque l'on clique sur le bouton charger.
	 */
	private void loadMaze() 
	{
		maze.initFromTextFile("data/labyrinthe2");
		this.getContentPane().remove(labyrinthe);
		labyrinthe= new Labyrinthe(maze);

		this.getContentPane().add(labyrinthe,BorderLayout.CENTER);
		this.setVisible(true);
	}

	/**
	 * Methode permettant d'afficher les regles du jeu. Executee lorsque l'on clique sur le bouton regles.
	 */
	private void displayRules() 
	{
		JOptionPane.showMessageDialog(null,  "Voici les regles du jeu : \n"
				+ "1. Une case blanche (E) represente une case libre. Une case noire (W) represente une case avec un mur. Une case jaune (D) represente un depart. Une case bleue (A) represente une arrivee. \n"
				+ "2. En cliquant sur une case, vous pouvez changer l'etat de cette derniere dans l'ordre suivant : E > W > D > A > E. \n"
				+ "3. Le bouton \" New \" dans \" File \" permet de reinitialiser votre labyrinthe. \n"
				
				+ "4. Le bouton \" Save \" dans \" File \" permet de sauvegarder votre labyrinthe. \n"
				+ "5. Le bouton \" Load \" dans \" File \" permet de charger le labyrinthe sauvegarde. \n"
				+ "6. Le bouton \" Rules \" dans \" File \" permet d'afficher la fenetre que vous etes en train de lire. \n"
				+ "7. Le bouton \" Launch \" dans \" Run \" permet de tracer le chemin allant du depart jusqu'a l'arrivee. \n"
				+ "8. Le bouton \" Edit \" dans \" Run \" permet de modifier votre labyrinthe apres qu'il ait ete lance. \n"
				+ "9. Le bouton \" New Line \" dans \" Size \" permet d'ajouter une ligne de WBox au labyrinthe. \n"
				+ "10. Le bouton \" New Column \" dans \" Size \" permet d'ajouter une colonne de WBox au labyrinthe. \n"
				+ "11. Le bouton \" Delete Line \" dans \" Size \" permet de supprimer une ligne du labyrinthe. \n"
				+ "12. Le bouton \" Delete Column \" dans \" Size \" permet de supprimer une colonne du labyrinthe. \n"
				, "Regles du jeu", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Rajoute une ligne dans le labyrinthe avec une methode de la classe Maze et actualise l'affichage.
	 */
	public void newLine() 
	{	
		maze.newLine();
		this.getContentPane().remove(labyrinthe);
		labyrinthe = new Labyrinthe(maze);
		this.getContentPane().add(labyrinthe,BorderLayout.CENTER);
		this.setVisible(true);
	}

	/**
	 * Rajoute une colonne dans le labyrinthe avec une methode de la classe Maze et actualise l'affichage.
	 */
	public void newColumn() 
	{
		maze.newColumn();
		this.getContentPane().remove(labyrinthe);
		labyrinthe = new Labyrinthe(maze);
		this.getContentPane().add(labyrinthe,BorderLayout.CENTER);
		this.setVisible(true);
	}

	/**
	 * Supprime une ligne dans le labyrinthe avec une methode de la classe Maze et actualise l'affichage.
	 */
	public void delLine() 
	{
		maze.delLine();
		this.getContentPane().remove(labyrinthe);
		labyrinthe = new Labyrinthe(maze);
		this.getContentPane().add(labyrinthe,BorderLayout.CENTER);
		this.setVisible(true);
	}

	/**
	 * Supprime une colonne dans le labyrinthe avec une methode de la classe Maze et actualise l'affichage.
	 */
	public void delColumn() 
	{
		maze.delColumn();
		this.getContentPane().remove(labyrinthe);
		labyrinthe = new Labyrinthe(maze);
		this.getContentPane().add(labyrinthe,BorderLayout.CENTER);
		this.setVisible(true);
	}
}
