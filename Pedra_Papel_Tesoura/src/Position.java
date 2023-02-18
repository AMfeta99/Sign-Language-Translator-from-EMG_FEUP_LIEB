/* Class Position define a posi��o/gesto da m�o de um jogador
 * Tem 3 vari�vel internas: nome correspondente � posi��o e 2 m�dias que definem o estado dos canais recebidos
 */
 
public class Position {
	
	private static double mean_1 = 0.53; // M�dias acima das quais o canal � considerado ativo
	private static double mean_2 = 0.76;  
	private String name;  // Indica��o da posi��o/gesto
	
	// Construtor da posi��o apartir do m�dia do sinal de cada sensor
	public Position (double ch1, double ch2) {
		// Pedra = (desativo, desativo)
		if (ch1<=mean_1 && ch2<=mean_2) {
			name = ("Rock");
		}
		// Papel = (ativo, ativo)
		else if (ch1>=mean_1 && ch2>=mean_2) {
			name = ("Paper");
		}
		// Tesoura = (desativo, ativo)
		else if (ch1<=mean_1 && ch2>=mean_2) {
			name = ("Scissors");
		}
		else
		{
			name = ("UNRECOGNISED");
		}
	}
	
	// Construtor da posi��o apartir do estado de ativa��o de cada canal
	public Position(char ch1, char ch2) {
		// Pedra = (desativo, desativo)
		if (ch1=='d' && ch2=='d') {
			name = ("Rock");
		}
		// Papel = (ativo, ativo)
		else if (ch1=='a' && ch2=='a') {
			name = ("Paper");
		}
		// Tesoura = (desativo, ativo)
		else if (ch1=='d' && ch2=='a') {
			name = ("Scissors");
		}
		else
		{
			name = ("UNRECOGNISED");
		}
	}
	
	
	// Construtor de posi��o vazio
	public Position() { 
		name = ("NO NAME");
	}
	
	// Construtor de posi��o a partir de string
	public Position(String pos) { 
		name = (pos);
	}
	
	// Retorno da string que indica a posi��o (Rock,Paper,Scissors)
	public String getName() {
		return name;
	}
}
