package maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;

/**
 * Classe implementant l'interface GraphInterface. C'est celle-ci qui permet de modeliser (en terme de structure) un labyrinthe. Il s'agit d'un tableau de MBox.
 * @see GraphInterface
 * @see MBox
 */
public class Maze implements GraphInterface 
{
	/**
	 * Hauteur du labyrinthe, i.e nombre de lignes. Initialisee a 20.
	 */
	private int height = 20;

	/**
	 * Largeur du labyrinthe, i.e nombre de colonnes. Initialisee a 20.
	 */
	private int width = 20;

	/**
	 * On represente le labyrinthe sous la forme d'un tableau bidimensionnel de MBox.
	 * @see MBox
	 */
	private MBox[][] labyrinthe;

	/**
	 * Entier representant le nombre de case depart dans le labyrinthe. Vaut 0 s'il n'y en a pas, 1 s'il y en a une seule, et -1 sinon.
	 */
	private int dBoxState;

	/**
	 * Entier representant le nombre de case d'arrivee dans le labyrinthe. Vaut 0 s'il n'y en a pas, 1 s'il y en a une seule, et -1 sinon.
	 */
	private int aBoxState;

	/**
	 * Constructeur Maze : initialise le labyrinthe avec les dimensions donnees dans les attributs.
	 */
	public Maze()
	{	
		labyrinthe = new MBox[height][width];
	}

	/**
	 * Renvoie la hauteur du labyrinthe.
	 * @return int : nombre de lignes
	 */
	public int getHeight() 
	{
		return height;
	}

	/**
	 * Renvoie la largeur du labyrinthe.
	 * @return int : nombre de colonnes
	 */
	public int getWidth() 
	{
		return width;
	}

	/**
	 * Renvoie la case situee aux coordonnees indiquees.
	 * @param i numero de la ligne
	 * @param j numero de la colonne
	 * @return MBox : case situee en i,j
	 */
	public MBox getBox(int i, int j)
	{
		return labyrinthe[i][j];
	}

	/**
	 * Renvoie l'attribut labyrinthe.
	 * @return MBox[][]
	 */
	public final MBox[][] getBoxes() 
	{
		return labyrinthe;
	}

	/**
	 * Renvoie l'information sur le nombre de case depart.
	 * @return int
	 */
	public int getdBoxState() 
	{
		return dBoxState;
	}

	/**
	 * Renvoie l'information sur le nombre de case d'arrivee.
	 * @return int
	 */
	public int getaBoxState()
	{
		return aBoxState;
	}

	/**
	 * Modifie la hauteur du labyrinthe.
	 * @param height nouvelle hauteur
	 */
	public void setHeight(int height) 
	{
		this.height = height;
	}

	/**
	 * Modifie la largeur du labyrinthe.
	 * @param width nouvelle largeur
	 */
	public void setWidth(int width) 
	{
		this.width = width;
	}

	/**
	 * Modifie une case du labyrinthe.
	 * @param box nouvelle case. Celle-ci contenant les coordonnees auxquelles elle se situe, pas la peine de les prendre en parametre.
	 */
	public void setBox(MBox box) 
	{
		labyrinthe[box.getx()][box.gety()] = box;
	}

	/**
	 * Renvoie la liste de toutes les cases du labyrinthe.
	 * @see GraphInterface
	 */
	public ArrayList<VertexInterface> getAllVertices()
	{
		ArrayList<VertexInterface> Vertices = new ArrayList<VertexInterface>();

		// On parcourt tout le tableau de MBox et on les ajoute � la liste Vertices
		for (int i=0; i<labyrinthe.length; i++)
		{
			for (int j=0; j<labyrinthe[0].length; j++)
			{
				Vertices.add(labyrinthe[i][j]);
			}
		}
		return Vertices;
	}

	/**
	 * Renvoie tous les successeurs de la case donnee en parametre.
	 * @see GraphInterface
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex)
	{
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
		MBox box = (MBox) vertex ; // On convertit vertex en une MBox pour pouvoir utiliser les methodes de cette classe
		int x = box.getx();
		int y = box.gety();

		// On tient compte des effets de bord
		if (x < height - 1)
		{
			MBox sommet = labyrinthe[x+1][y];
			if (sommet.getLabel()=="E" || sommet.getLabel()=="A")
			{
				successors.add(sommet);
			} 
		}
		if (x > 0)
		{
			MBox sommet = labyrinthe[x-1][y];
			if (sommet.getLabel()=="E" || sommet.getLabel()=="A")
			{
				successors.add(sommet);
			} 
		}
		if (y < width - 1)
		{
			MBox sommet = labyrinthe[x][y+1];
			if (sommet.getLabel()=="E" || sommet.getLabel()=="A")
			{
				successors.add(sommet);
			} 
		}
		if (y > 0)
		{
			MBox sommet = labyrinthe[x][y-1];
			if (sommet.getLabel()=="E" || sommet.getLabel()=="A")
			{
				successors.add(sommet);
			}
		}
		return successors;
	}

	/**
	 * Renvoie le poids d'une arete entre deux cases. Il est toujours egal a 1.
	 * @see GraphInterface
	 * @return int : vaut 1.
	 */
	public int getWeight(VertexInterface src,VertexInterface dst) 
	{
		return 1;
	}

	/**
	 * Methode qui parcourt le labyrinthe a la recherche d'une case depart, modifie l'attribut dBoxState selon le resultat puis renvoie une case.
	 * @return VertexInterface : renvoie la case depart si celle-ci existe et est unique, null sinon.
	 */
	public VertexInterface searchDeparture() 
	{
		int count = 0; // Entier permettant de compter le nombre de departs
		MBox departure = null;

		// On parcourt tout le labyrinthe a la recherche de depart(s)
		for (MBox[] x : this.getBoxes()) 
		{
			for (MBox y : x) 
			{
				if (y.getLabel()=="D") // Si on en trouve un, on le stocke dans departure et on incremente le compteur 
				{
					departure = y;
					count++;
				}
			}
		}

		if (count == 0) // Permet dans la classe Fenetre d'afficher une erreur (pas de depart)
		{
			dBoxState = 0;
			return null;
		}
		else if (count == 1) // Pas de probleme
		{
			dBoxState = 1;
			return departure;
		}
		else // Permet dans la classe Fenetre d'afficher une erreur (trop de departs)
		{
			dBoxState = -1;
			return null;
		}
	}

	/**
	 * Methode qui parcourt le labyrinthe a la recherche d'une case d'arrivee, modifie l'attribut aBoxState selon le resultat puis renvoie une case.
	 * @return VertexInterface : renvoie la case d'arrivee si celle-ci existe et est unique, null sinon.
	 */
	public VertexInterface searchArrival() 
	{
		int count = 0; // Entier permettant de compter le nombre d'arrivee
		MBox arrival = null;

		// On parcourt tout le labyrinthe a la recherche d'arrivees
		for (MBox[] x : getBoxes()) 
		{
			for (MBox y : x) 
			{
				if (y.getLabel()=="A") // Si on en trouve une, on la stocke dans arrival et on incremente le compteur 
				{
					arrival = y;
					count++;
				}
			}
		}

		if (count == 0) // Permet dans la classe Fenetre d'afficher une erreur (pas d'arrivee)
		{
			aBoxState = 0;
			return null;
		}
		else if (count == 1) // Pas de probleme
		{
			aBoxState = 1;
			return arrival;
		}
		else // Permet dans la classe Fenetre d'afficher une erreur (trop d'arrivees)
		{
			aBoxState = -1;
			return null;
		}
	}

	/**
	 * Permet de creer un labyrinthe a partir d'un fichier texte.
	 * @param fileName fichier texte
	 */
	public final void initFromTextFile(String fileName)
	{
		BufferedReader reader = null;
		try 
		{
			reader = new BufferedReader(new FileReader(fileName));

			/* On pose une marque au d�but du fichier texte qui ne s'effacera que 2500 caract�res apr�s (taille maximale 
			 * du fichier, decision arbitraire). */
			if (reader.markSupported()) 
			{
				reader.mark(2500);
			}
			// On parcourt alors le fichier afin de compter le nombre de lignes
			int numberOfLines = 0;
			while (reader.readLine() != null)
				numberOfLines ++;
			// Puis on retourne sur la marque
			reader.reset();

			// On reparcourt ensuite le fichier texte pour construire le labyrinthe
			String line= reader.readLine();
			int numberOfColumns = line.length();
			labyrinthe = new MBox[numberOfLines][numberOfColumns];
			height = numberOfLines;
			width = numberOfColumns;

			for (int i=0; i<numberOfLines; i++) 
			{
				for (int j=0; j<numberOfColumns; j++)
				{
					switch(line.charAt(j))
					{
					case 'A':
						labyrinthe[i][j]= new ABox(this,i,j);
						break;
					case 'E' :
						labyrinthe[i][j]= new EBox(this,i,j);
						break;
					case 'W' :
						labyrinthe[i][j]= new WBox(this,i,j);
						break;
					case 'D' :
						labyrinthe[i][j]= new DBox(this,i,j);
						break;
					}
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Permet de sauvegarder un labyrinthe dans un fichier texte.
	 * @param fileName fichier texte
	 */
	public final void saveToTextFile(String fileName)
	{
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);

			// On parcourt le labyrinthe et on recopie le type de chaque case dans le fichier texte
			for(int i=0; i<height; i++)
			{
				for(int j=0; j<width; j++)
				{
					pw.print(labyrinthe[i][j].getLabel());
				}
				pw.println();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
			} catch(Exception e) {
			}
		}
	}

	/**
	 * Renvoie, s'il existe, le plus court chemin entre le depart et l'arrivee.
	 * @return ArrayList : contient les cases du chemin s'il existe, null sinon
	 */
	public ArrayList<VertexInterface> getPath()
	{
		VertexInterface depart  = this.searchDeparture();
		VertexInterface arrivee = this.searchArrival();
		PreviousInterface previous = Dijkstra.dijkstra(this,depart);

		// On verifie qu'il y a un chemin possible, sinon on retourne null, ce qui permettra dans la classe Fenetre d'afficher une erreur
		if (previous.getHashtable().containsKey(arrivee))
			return previous.getShortestPathTo(arrivee);
		else
			return null;
	}

	/**
	 * Rajoute une ligne de WBox dans l'attribut labyrinthe.
	 */
	public void newLine() 
	{

		MBox[][] labyrinthe2 = new MBox[height+1][width];
		for (int i=0;i<height;i++) 
		{
			for (int j=0;j<width;j++) 
			{
				labyrinthe2[i][j]=labyrinthe[i][j];
			}
		}
		for (int i=0;i<width;i++) 
		{
			labyrinthe2[height][i]= new WBox(this,height,i);
		}
		labyrinthe = labyrinthe2;
		height +=1;
	}

	/**
	 * Rajoute une colonne de WBox dans l'attribut labyrinthe.
	 */
	public void newColumn() 
	{
		MBox[][] labyrinthe2 = new MBox[height][width+1];
		for (int i=0;i<height;i++) 
		{
			for (int j=0;j<width;j++) 
			{
				labyrinthe2[i][j]=labyrinthe[i][j];
			}
		}
		for (int i=0;i<height;i++) 
		{
			labyrinthe2[i][width]= new WBox(this,i,width);
		}
		labyrinthe = labyrinthe2;
		width +=1;
	}

	/**
	 * Supprime une ligne dans l'attribut labyrinthe.
	 */
	public void delLine() 
	{
		MBox[][] labyrinthe2 = new MBox[height-1][width];
		for (int i=0;i<height-1;i++) 
		{
			for (int j=0;j<width;j++) 
			{
				labyrinthe2[i][j]=labyrinthe[i][j];
			}
		}
		labyrinthe = labyrinthe2;
		height -=1;
	}

	/**
	 * Supprime une colonne dans l'attribut labyrinthe.
	 */
	public void delColumn() 
	{
		MBox[][] labyrinthe2 = new MBox[height][width-1];
		for (int i=0;i<height;i++) 
		{
			for (int j=0;j<width-1;j++) 
			{
				labyrinthe2[i][j]=labyrinthe[i][j];
			}
		}
		labyrinthe = labyrinthe2;
		width -=1;
	}

	/**
	 * Renvoie des informations sur le labyrithe sous forme de chaine de caracteres.
	 */
	@Override
	public String toString() 
	{
		return "[Maze " + width + "x" + height + " first: " + labyrinthe[0][0] + "]";
	}
}
