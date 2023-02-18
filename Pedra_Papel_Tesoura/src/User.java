/* User extende Player, ou seja, a class Player implementa o jogador Computer
 * Tem as mesmas vari�veis internas e m�todos que Player, acrescentando os seus pr�prios
 */

public class User extends Player {
	
	// Construtor de user
	// Este construtor chama o construtor de Player
	public User(String name) {
		super(name);
	}

	// Defini��o da posi��o do jogador
	// Como se trata do utilizador, a posi��o vai ser definida conforme os caracteres lidos de Arduino
	public void definePosition (char ch1, char ch2) {
		Position aux = new Position(ch1, ch2);
		setPosition(aux);
	}

}
