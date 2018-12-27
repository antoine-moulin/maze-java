package maze;

/**
 * Classe heritant de la classe Exception. Represente les exceptions pouvant etre levees a l'issue d'instructions en lien avec un objet de la classe Maze.
 * @see Exception
 */
public class MazeReadingException extends Exception 
{
	private static final long serialVersionUID = 105866312343379837L;

	/**
	 * Constructeur. Affiche un message d'erreur pour preciser ou elle se trouve.
	 * @param fileName fichier contenant une erreur
	 * @param lineNumber ligne a laquelle se trouve l'erreur dans fileName
	 * @param message message expliquant l'erreur
	 */
	public MazeReadingException(String fileName, int lineNumber,String message)
	{
		super("Error in : " + fileName + " line number " + lineNumber + "," + message);
	}
}
