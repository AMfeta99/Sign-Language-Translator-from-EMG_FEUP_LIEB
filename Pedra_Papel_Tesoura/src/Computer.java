/* Computer extende Player, ou seja, a class Player implementa o jogador Computer
 * Tem as mesmas variáveis internas e métodos que Player, acrescentando os seus próprios
 */

import java.util.Random;

public class Computer extends Player {
	
	// Construtor de computer
	// Este construtor chama o construtor de Player
	public Computer(String name) {
		super(name);
	}
	
	// Definição da posição do computador
	// Como se trata do computador, a posição vai ser definida aleatoriamente
	public void definePosition () { 
		Random rand1 = new Random(); 
		int value1 = rand1.nextInt(2) ; // Gera valores aleatórios de 0 a 1 (vai corresponder a desativo ou ativo)
		
		Random rand2 = new Random();
		int value2 = rand2.nextInt(2); 
		
		Position aux = new Position(value1, value2);
		setPosition(aux);
	}	
}
