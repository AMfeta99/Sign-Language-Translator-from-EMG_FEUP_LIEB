/* Class Player implementa um jogador 
 * Tem 3 campos/variáveis internas: nome, pontuação e posição da mão do jogador
 */

public class Player {
	
	private Position p; 
	private int score;
	private String name;
	
	// Construtor de player
	public Player(String name) { 
		score=0; // Pontuação inicialmente nula
		this.name = name; // Nome do jogador
		p=new Position(); // Nova posição da mão de um jogador
	}
	
	// Atualização da pontuação ao ganhar uma ronda
	public void winRound() {
		score++; 
	}
	
	// Retorno da pontuação total do jogador
	public int getScore() {
		return score;
	}
	
	// Retorno do nome do jogador 
	public String getName() {
		return name;
	}
	
	// Retorno da posição/gesto 
	public Position getPosition() {
		return p;
	}
	
	// Retorno do nome da posição/gesto 
	public String getPositionName() {
		return p.getName();
	}
	
	// Atualização do nome do jogador 
	public void setName(String newName) {
		name = newName;
	}
	
	// Atualização da posição/gesto 
	public void setPosition(Position aux) {
		p = aux;
	}
	
	// Atualização da pontuação
	public void setScore(int newScore) {
		score = newScore;
	}
}