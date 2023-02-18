/* Class Position define a posição/gesto da mão de um jogador
 * Tem 3 variável internas: nome correspondente à posição e 2 médias que definem o estado dos canais recebidos
 */
 
public class Position {
	
	private static double mean_1 = 0.53; // Médias acima das quais o canal é considerado ativo
	private static double mean_2 = 0.76;  
	private String name;  // Indicação da posição/gesto
	
	// Construtor da posição apartir do média do sinal de cada sensor
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
	
	// Construtor da posição apartir do estado de ativação de cada canal
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
	
	
	// Construtor de posição vazio
	public Position() { 
		name = ("NO NAME");
	}
	
	// Construtor de posição a partir de string
	public Position(String pos) { 
		name = (pos);
	}
	
	// Retorno da string que indica a posição (Rock,Paper,Scissors)
	public String getName() {
		return name;
	}
}
