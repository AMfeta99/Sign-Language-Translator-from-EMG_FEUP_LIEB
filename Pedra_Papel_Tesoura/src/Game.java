/* Implementação do jogo pedra-papel-tesoura
 * Através dos dois jogadores: user e computer e do número de rondas
 * Implementa as rondas fazendo o display do vencedor no final
 */

import java.util.*;

public class Game {
	private User user;
	private Computer computer;
	private int numRounds;
	// Para receber informação como input
	static Scanner in = new Scanner(System.in);
	
	// Construtor de Game vazio
	public Game() { 
		user = new User("NO NAME");
		computer = new Computer("PC");
		numRounds = 0;
	}
	
	// Construtor de Game
	// Faz a inicialização dos jogadores e do número de rondas
	public Game(String nameUser, int numRounds) { 
		user = new User(nameUser);
		computer = new Computer("PC");
		this.numRounds = numRounds;
	}
	
	// Retorna o utilizador
	public User getUser() {
		return user;
	}
	
	// Retorna o computador
	public Computer getComputer() {
		return computer;
	}
	
	// Retorna o número de rondas
	public int getRounds() {
		return numRounds;
	}
	
	
	// Redefine o jogo para um novo jogador e um novo número de rondas
	public void redefineGame(String nameUser,int numRounds) {
		user.setName(nameUser);
		this.numRounds = numRounds;
	}
	
	public int roundWinner() {
		int wins = 0;
		if(user.getPositionName()==computer.getPositionName())
		{
			wins = 0;
		}
		else if(user.getPositionName()=="Scissors")
		{
			if(computer.getPositionName()=="Rock")
			{
				computer.winRound();
				wins = -1;
			}
			else if(computer.getPositionName()=="Paper")
			{
				user.winRound();
				wins = 1;
			}
		}
		else if(user.getPositionName()=="Rock")
		{
			if(computer.getPositionName()=="Scissors")
			{
				user.winRound();
				wins = 1;
			}
			else if(computer.getPositionName()=="Paper")
			{
				computer.winRound();
				wins = -1;
			}
		}
		else if(user.getPositionName()=="Paper")
		{
			if(computer.getPositionName()=="Rock")
			{
				user.winRound();
				wins = 1;
			}
			else if(computer.getPositionName()=="Scissors")
			{	
				computer.winRound();
				wins = -1;
			}
		}
		return wins;
	}
	
	// Implementação de uma ronda
	public void nextRound() {

		// Gera as posições tanto do utilizador como do computador
		do {
			Random rand1 = new Random(); 
			int value1 = rand1.nextInt(2);
			Random rand2 = new Random();
			int value2 = rand2.nextInt(2);
			char ch1 = 'a';
			char ch2 = 'a';
			if (value1<0.53)
			{
			    ch1 = 'd';
			}
			if (value2<0.76)
			{
			    ch2 = 'd';
			}
			user.definePosition(ch1, ch2); 
		} while(user.getPositionName()=="UNRECOGNISED");
		do {
			computer.definePosition(); 
		} while(computer.getPositionName()=="UNRECOGNISED");
		
		// Display das posições
		System.out.println(user.getName() + ": " + user.getPositionName());
		System.out.println(computer.getName() + ": " + computer.getPositionName());
		
		// Determinação do vencedor
		roundWinner();
	}
	
	// Display do vencedor do jogo no seu final
	public int getWinner() {
		int wins = 0;
		System.out.println(" ");
		if (user.getScore()==computer.getScore())
		{
			wins = 0;
		}
		else if (user.getScore()>computer.getScore())
		{
			wins = 1;
		}
		else if (user.getScore()<computer.getScore())
		{
			wins = -1;
		}
		return wins;
	}
	
	// Implementação de todas as rondas de um jogo
	public void allRounds() {
		for(int i=1; i<=numRounds; i++) 
		{
			System.out.println(" ");
			System.out.println("Round " + i);
			nextRound();
			System.out.println(" ");
			System.out.println(user.getName() + " current score: " + user.getScore());
			System.out.println(computer.getName() + " current score: " + computer.getScore());
		}
	}
	
	// Limpa as pontuações, mantendo os jogadores
	public void resetGame()
	{
		user.setScore(0);
		computer.setScore(0);
	}
	
	// Função main para teste da class
	public static void main(String[] args) {
		
		System.out.println("User Name: ");
		String UserName = in.nextLine();
		System.out.println("Number of rounds: ");
		int rounds= in.nextInt();
		
		Game game = new Game(UserName, rounds);
		game.allRounds();
		game.getWinner();
	}
}
