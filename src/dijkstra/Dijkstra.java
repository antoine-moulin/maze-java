package dijkstra;

/**
 * Classe implementant l'algorithme de Dijkstra a partir des interfaces VertexInterface, GraphInterface, ASetInterface, PreviousInterface et PiInterface.
 * @see VertexInterface
 * @see GraphInterface
 * @see ASetInterface
 * @see PreviousInterface
 * @see PiInterface
 */
public class Dijkstra 
{
	/**
	 * Execute l'algorithme de Dijkstra.
	 * @param g Graphe de type GraphInterface.
	 * @param r Depart.
	 * @param a Ensemble de type ASetInterface.
	 * @param pi Fonction Pi.
	 * @param previous Fonction pere.
	 * @return PreviousInterface
	 */
	private static PreviousInterface dijkstra(GraphInterface g,
			VertexInterface r,
			ASetInterface a,
			PiInterface pi,
			PreviousInterface previous)
	{
		a.add(r); // On ajoute r a l'ensemble de depart 

		VertexInterface arrival;
		arrival = g.searchArrival();

		// int n=  g.getAllVertices().size(); On recupere le nombre de cases (semble inutile)

		// On initialise toutes les distances a + infini sauf r, qu'on initialise a 0
		for (VertexInterface x : g.getAllVertices()) 
		{
			pi.set(x, Integer.MAX_VALUE);
		}
		pi.set(r, 0);

		VertexInterface pivot = r; // On definit le pivot comme etant egal a r

		while(!a.contains(arrival))
		{
			for (VertexInterface y : g.getSuccessors(pivot)) 
			{
				// Si le sommet n'est pas encore dans a
				if (!a.contains(y)) 
				{
					// On determine la distance minimale parmi les distances du pivot a chacun de ses successeurs
					if (pi.get(pivot)+g.getWeight(pivot, y)< pi.get(y)) 
					{
						pi.set(y,pi.get(pivot)+g.getWeight(pivot, y));
						previous.set(y,pivot);
					}
				}
			}

			// On determine un sommet qui n'est pas dans a et tel que la distance au pivot soit minimale
			VertexInterface min= null;
			int i = Integer.MAX_VALUE;
			for(VertexInterface x : g.getAllVertices()) 
			{
				// Si le sommet n'est pas encore dans a
				if(!a.contains(x)) 
				{
					// On determine le sommet min dont la distance au pivot est minimale
					if (pi.get(x) < i) 
					{
						i = pi.get(x);
						min = x;
					}
				}
			}

			// Ce sommet devient alors le nouveau pivot
			pivot = min;

			if (pivot == null) // S'il n'y a pas de nouveau pivot
				return previous;
			else // Sinon, on l'ajoute a l'ensemble a
				a.add(pivot);
		}
		return previous;
	}

	/**
	 * Permet d'effectuer l'algorithme Dijkstra depuis l'exterieur.
	 * @param g Graphe de type GraphInterface.
	 * @param r Depart.
	 * @return PreviousInterface
	 */
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) 
	{
		ASet a = new ASet();
		Pi pi = new Pi();
		Previous previous = new Previous();
		return dijkstra(g,r, a, pi,previous);
	}
}
