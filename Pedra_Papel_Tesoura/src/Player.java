/* Class Player implementa um jogador 
 * Tem 3 campos/vari�veis internas: nome, pontua��o e posi��o da m�o do jogador
 */

public class Player {
	
	private Position p; 
	private int score;
	private String name;
	
	// Construtor de player
	public Player(String name) { 
		score=0; // Pontua��o inicialmente nula
		this.name = name; // Nome do jogador
		p=new Position(); // Nova posi��o da m�o de um jogador
	}
	
	// Atualiza��o da pontua��o ao ganhar uma ronda
	public void winRound() {
		score++; 
	}
	
	// Retorno da pontua��o total do jogador
	public int getScore() {
		return score;
	}
	
	// Retorno do nome do jogador 
	public String getName() {
		return name;
	}
	
	// Retorno da posi��o/gesto 
	public Position getPosition() {
		return p;
	}
	
	// Retorno do nome da posi��o/gesto 
	public String getPositionName() {
		return p.getName();
	}
	
	// Atualiza��o do nome do jogador 
	public void setName(String newName) {
		name = newName;
	}
	
	// Atualiza��o da posi��o/gesto 
	public void setPosition(Position aux) {
		p = aux;
	}
	
	// Atualiza��o da pontua��o
	public void setScore(int newScore) {
		score = newScore;
	}
}