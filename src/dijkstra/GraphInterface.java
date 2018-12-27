package dijkstra;

import java.util.ArrayList;

/**
 * Interface de la classe Maze.
 * @see maze.Maze
 */
public interface GraphInterface 
{
	/**
	 * Renvoie la liste de tous les sommets du graphe.
	 * <p>
	 * Ceci correspond en fait a la liste de toutes les cases du labyrinthe.
	 * @return ArrayList : contient toutes les cases du labyrinthe.
	 */
	public ArrayList<VertexInterface> getAllVertices();
	
	/**
	 * Renvoie les successeurs du sommet entre en parametre.
	 * @param v sommet.
	 * @return ArrayList : contient les successeurs du sommet.
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface v);
	
	/**
	 * Renvoie le poids de l'arete entre les sommets entres en parametres.
	 * @param vertex1 sommet.
	 * @param vertex2 sommet.
	 * @return int : valeur du poids de l'arete.
	 */
	public int getWeight(VertexInterface vertex1,VertexInterface vertex2);
	
	/**
	 * Recherche une case depart, donne le nombre de cases departs (0, 1 ou plus) au maze et renvoie une case depart.
	 * @return VertexInterface : renvoie la case depart si elle est unique, null sinon.
	 */
	public VertexInterface searchDeparture();
	
	/**
	 * Recherche une case d'arrivee, donne le nombre de cases d'arrivees (0, 1 ou plus) au maze et renvoie une case d'arrivee.
	 * @return VertexInterface : renvoie la case d'arrivee si elle est unique, null sinon.
	 */
	public VertexInterface searchArrival();
	
	/**
	 * Renvoie le chemin entre le depart et l'arrivee.
	 * @return ArrayList : renvoie le chemin entre le depart et l'arrivee s'il existe, null sinon.
	 */
	public ArrayList<VertexInterface> getPath();
}
