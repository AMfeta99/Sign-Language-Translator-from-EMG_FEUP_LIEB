/* Esta classe implementa o jogo Pedra-Papel-Tesoura na interface com duas opções: 
 * 1. sem dispositivo: escolha da posição através de botões;
 * 2. com dispositivo: fazer o gesto e através de EMG reconhecê-lo e mostrá-lo.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.border.*;
import java.util.*;
import com.jgoodies.forms.layout.*;

public class Play extends JFrame {

	private static final long serialVersionUID = 1L;
	static Scanner Input = new Scanner(System.in);
    private String nameInput;
    private int roundsInput;
    private Game game = new Game();
	public static int numRound = 1;
	private int winner;
	
	// Lançar o jogo
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Play frame = new Play();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor de Play
	public Play() {
		// Cria a frame principal
		setResizable(false);
		setTitle("Rock-Paper-Scissors");
		getContentPane().setLayout(new CardLayout(0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 480);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
	    
	    //--------------------------------------------------------------------------------------------------------------		
	    // Menu do jogo
		JLayeredPane gameMenu = new JLayeredPane();
		gameMenu.setBackground(new Color(240, 255, 255));
		contentPane.add(gameMenu, "name_35689998086200");
		gameMenu.setLayout(new CardLayout(0, 0));
		
		// Adição de título ao menu
		JLabel title = new JLabel("Rock Paper Scissors");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Viner Hand ITC", Font.PLAIN, 48));
		title.setBackground(new Color(240, 255, 255));
		
		// Adição do botão de voltar ao menu principal
		JButton back = new JButton("Back");
		back.setBackground(new Color(211, 211, 211));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				App.Menu.setVisible(true);}
			});	
		
		//--------------------------------------------------------------------------------------------------------------		
		// Inicializar o jogador - nome e número de rondas do jogo
		JPanel user = new JPanel();
		user.setBackground(new Color(240, 240, 240));
		gameMenu.add(user, "User");
					
		JLabel playerName = new JLabel("Player's Name: ");
		playerName.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
		JTextField InputName = new JTextField();
		InputName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		InputName.setBackground(Color.WHITE);
		InputName.setColumns(10);
							
		JLabel numberRounds = new JLabel("Number of Rounds: ");
		numberRounds.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
		JTextField InputRounds = new JTextField();
		InputRounds.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		InputRounds.setBackground(Color.WHITE);
		InputRounds.setColumns(10);
		
		// Adição do botão para avançar no jogo
		// Lê do input o nome do utilizador e o número de rondas, formando o jogo
		JButton next = new JButton("Next");
		next.setBackground(new Color(211, 211, 211));
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nameInput = InputName.getText();
					roundsInput = Integer.parseInt(InputRounds.getText());
					if (nameInput.isEmpty())
					{
						JOptionPane.showMessageDialog(gameMenu, "Please insert a name.");
						return;
					}
					else if (roundsInput > 5) // Número máximo de rondas
					{
						JOptionPane.showMessageDialog(gameMenu, "Maximum of 5 rounds.");
						return;
					}
					else if (roundsInput<=0)
					{
						JOptionPane.showMessageDialog(gameMenu, "The number of rounds isn't valid.");
						return;
					}
				}
				catch(NumberFormatException ex) { // Caso o número de rondas não tenha formato válido
					JOptionPane.showMessageDialog(user, "Invalid format.");
					return;
				}
				CardLayout c=(CardLayout) ((gameMenu.getLayout()));
				c.show(gameMenu,"Menu Type Of Game");
				game.redefineGame(nameInput, roundsInput) ;
				}  
			});
			
		GroupLayout gl_InicialPlayer = new GroupLayout(user);
		gl_InicialPlayer.setHorizontalGroup(
			gl_InicialPlayer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_InicialPlayer.createSequentialGroup()
					.addGap(60)
					.addComponent(back, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 506, Short.MAX_VALUE)
					.addComponent(next, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
				.addGroup(gl_InicialPlayer.createSequentialGroup()
					.addGroup(gl_InicialPlayer.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_InicialPlayer.createSequentialGroup()
							.addGap(143)
							.addComponent(title, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_InicialPlayer.createSequentialGroup()
							.addGap(92)
							.addComponent(playerName, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(InputName, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_InicialPlayer.createSequentialGroup()
							.addGap(92)
							.addComponent(numberRounds, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(InputRounds)))
					.addContainerGap(124, Short.MAX_VALUE))
		);
		gl_InicialPlayer.setVerticalGroup(
			gl_InicialPlayer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_InicialPlayer.createSequentialGroup()
					.addGap(5)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(64)
					.addGroup(gl_InicialPlayer.createParallelGroup(Alignment.LEADING)
						.addComponent(playerName, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(InputName, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_InicialPlayer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_InicialPlayer.createSequentialGroup()
							.addComponent(numberRounds, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(79)
							.addGroup(gl_InicialPlayer.createParallelGroup(Alignment.TRAILING)
								.addComponent(back, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
								.addComponent(next, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(31))
						.addGroup(gl_InicialPlayer.createSequentialGroup()
							.addComponent(InputRounds, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		user.setLayout(gl_InicialPlayer);		
					
		//--------------------------------------------------------------------------------------------------------------		
	    // Menu do tipo de jogo
		JPanel typeGame = new JPanel();                 
		typeGame.setBackground(new Color(240, 240, 240));
		gameMenu.add(typeGame, "Menu Type Of Game");
					
		JLabel titleType = new JLabel("Rock Paper Scissors");
		titleType.setBackground(new Color(240, 255, 255));
		titleType.setHorizontalAlignment(SwingConstants.CENTER);
		titleType.setFont(new Font("Viner Hand ITC", Font.PLAIN, 48));
					
		// Botão para jogar sem dispositivo		
		JButton gameButtons = new JButton("Play without EMG device");
		gameButtons.setFont(new Font("Tahoma", Font.BOLD, 13));
		gameButtons.setBackground(new Color(211, 211, 211));
		gameButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c=(CardLayout) ((gameMenu.getLayout()));
				c.show(gameMenu,"Game using buttons");
			}
		});
		
		// Inicializa a comunicação série
		Communication Arduino = new Communication();
		
		// Botão para jogar com dispositivo		
		JButton gameEmg = new JButton("Play with EMG device");
		gameEmg.setFont(new Font("Tahoma", Font.BOLD, 13));
		gameEmg.setBackground(new Color(211, 211, 211));
		gameEmg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arduino.getCommPort().openPort();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException f) {
					f.printStackTrace();
				}
				CardLayout c=(CardLayout) ((gameMenu.getLayout()));
				c.show(gameMenu,"Game using EMG sensors");
			}
		});
		
		// Botão para voltar para trás		
		JButton back1 = new JButton("Back");
		back1.setBackground(new Color(211, 211, 211));
		back1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c=(CardLayout) ((gameMenu.getLayout()));
				c.show(gameMenu,"User");
			}
		});
		
		GroupLayout gl_typeMenu = new GroupLayout(typeGame);
		gl_typeMenu.setHorizontalGroup(
			gl_typeMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_typeMenu.createSequentialGroup()
					.addGap(101)
					.addComponent(gameButtons, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
					.addComponent(gameEmg, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addGap(101))
				.addGroup(gl_typeMenu.createSequentialGroup()
					.addGap(143)
					.addComponent(titleType, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(124, Short.MAX_VALUE))
				.addGroup(gl_typeMenu.createSequentialGroup()
					.addGap(37)
					.addComponent(back1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(679, Short.MAX_VALUE))
		);
		gl_typeMenu.setVerticalGroup(
			gl_typeMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_typeMenu.createSequentialGroup()
					.addGap(5)
					.addComponent(titleType, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(86)
					.addGroup(gl_typeMenu.createParallelGroup(Alignment.BASELINE)
						.addComponent(gameEmg, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addComponent(gameButtons, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addGap(119)
					.addComponent(back1)
					.addGap(22))
		);
		typeGame.setLayout(gl_typeMenu);
		
		//---------------------------------------------------------------------------------------------------------------	
		// Jogo sem dispositivo: usando combobox
		
		JLayeredPane playButtons = new JLayeredPane();  
		gameMenu.add(playButtons, "Game using buttons");
		playButtons.setLayout(new CardLayout(0, 0));
		
		// Painel do jogo propriamente dito
		JDesktopPane gamePanel = new JDesktopPane();
		gamePanel.setBorder(null);
		playButtons.add(gamePanel, "playing");
		JLabel background1 = new JLabel(" ");
		gamePanel.setLayer(background1, 1);
		background1.setIcon(new ImageIcon("Background1.png"));
		background1.setBounds(-15, -13, 865, 470);
		gamePanel.add(background1);
		
		// Identificação do campo do User e do Computer no painel do jogo
		JLabel labelUser = new JLabel("User");
		gamePanel.setLayer(labelUser, 2);
		labelUser.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelUser.setBounds(145, 32, 66, 25);
		gamePanel.add(labelUser);
		
		JLabel labelPc = new JLabel("Computer");
		gamePanel.setLayer(labelPc, 2);
		labelPc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelPc.setBounds(584, 32, 135, 25);
		gamePanel.add(labelPc);
		
		// Campo onde vai ser mostrado a ronda atual
		JTextField round = new JTextField();
		gamePanel.setLayer(round, 2);
		round.setFont(new Font("Tahoma", Font.PLAIN, 17));
		round.setEditable(false);
		round.setBounds(372, 13, 76, 35);
		gamePanel.add(round);
		
		// Painel onde vai ser mostrado o gesto do utilizador
		Panel userPanel = new Panel();
		gamePanel.setLayer(userPanel, 2);
		userPanel.setBackground(new Color(245, 245, 245));
		userPanel.setBounds(10, 63, 377, 339);
		gamePanel.add(userPanel);
		JLabel image = new JLabel("");
		GroupLayout gl_userPanel = new GroupLayout(userPanel);
		
		// Onde o utilizador vai poder escolher o gesto
		JComboBox choiceBox = new JComboBox();
		gamePanel.setLayer(choiceBox, 3);
		choiceBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		choiceBox.setBounds(10, 13, 92, 44);
		choiceBox.setModel(new DefaultComboBoxModel(new String[] {"[choose]", "Rock", "Paper", "Scissors"}));
		gamePanel.add(choiceBox);
		
		// Painel onde vai ser mostrado o gesto do pc
		Panel pcPanel = new Panel();
		gamePanel.setLayer(pcPanel, 2);
		pcPanel.setBackground(new Color(245, 245, 245));
		pcPanel.setBounds(416, 63, 365, 339);
		gamePanel.add(pcPanel);
		
		gl_userPanel.setHorizontalGroup(
			gl_userPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_userPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_userPanel.setVerticalGroup(
			gl_userPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_userPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(image, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
		);
		userPanel.setLayout(gl_userPanel);
		
		// Painel para mostrar resultado entre rondas
		JDesktopPane resultPane = new JDesktopPane();
		playButtons.setLayer(resultPane, 0);
		resultPane.setBorder(null);
		playButtons.add(resultPane, "Round Result");
		JLabel background2 = new JLabel(" ");
		resultPane.setLayer(background2, 1);
		background2.setIcon(new ImageIcon("Background1.png"));
		background2.setBounds(-15, -15, 865, 472);
		resultPane.add(background2);
				
		JTextField roundResult = new JTextField();
		resultPane.setLayer(roundResult, 2);
		roundResult.setFont(new Font("Tahoma", Font.PLAIN, 17));
		roundResult.setEditable(false);
		roundResult.setBounds(362, 13, 76, 35);
		resultPane.add(roundResult);
		
		// Identificação do campo do User e do Computer no painel dos resultados
		JLabel labelResultsUser = new JLabel("User");
		resultPane.setLayer(labelResultsUser, 2);
		labelResultsUser.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelResultsUser.setBounds(165, 36, 66, 25);
		resultPane.add(labelResultsUser);
				
		JLabel labelResultsPc = new JLabel("Computer");
		resultPane.setLayer(labelResultsPc, 2);
		labelResultsPc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelResultsPc.setBounds(561, 36, 135, 25);
		resultPane.add(labelResultsPc);
		
		// Adição do painel onde vai ser mostrado se o utilizador venceu a ronda ou não
		JTextField userWin = new JTextField();
		resultPane.setLayer(userWin, 2);
		userWin.setEditable(false);
		userWin.setHorizontalAlignment(SwingConstants.CENTER);
		userWin.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 50));
		userWin.setBounds(32, 63, 338, 212);
		resultPane.add(userWin);
		userWin.setColumns(10);
				
		// Adição do painel onde vai ser mostrado se o pc venceu a ronda ou não
		JTextField pcWin = new JTextField();
		resultPane.setLayer(pcWin, 2);
		pcWin.setEditable(false);
		pcWin.setHorizontalAlignment(SwingConstants.CENTER);
		pcWin.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 50));
		pcWin.setBounds(421, 63, 344, 212);
		resultPane.add(pcWin);
		pcWin.setColumns(10);
				
		// Mostra a pontuação atual do utilizador
		JTextField userScore = new JTextField();
		resultPane.setLayer(userScore, 2);
		userScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userScore.setEditable(false);
		userScore.setBounds(139, 288, 119, 41);
		resultPane.add(userScore);
		userScore.setColumns(10);
				
		// Mostra a pontuação atual do pc
		JTextField pcScore = new JTextField();
		resultPane.setLayer(pcScore, 2);
		pcScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pcScore.setEditable(false);
		pcScore.setColumns(10);
		pcScore.setBounds(530, 288, 146, 41);
		resultPane.add(pcScore);
		
		// Botão de avançar para resultados
		JButton showResults = new JButton("Show Round Results");
		gamePanel.setLayer(showResults, 2);
		showResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.setVisible(false);
				resultPane.setVisible(true);
				roundResult.setText("Round " + String.valueOf(numRound));
				CardLayout c=(CardLayout) ((playButtons.getLayout()));
				c.show(playButtons,"Round result");
				displayWinner(winner, userWin, pcWin, userScore, pcScore, game);
			}
		});
		showResults.setBounds(613, 408, 168, 25);
		gamePanel.add(showResults);
		showResults.setEnabled(false);
		
		// Adição do botão para jogar ronda
		JButton playRound = new JButton("Play Round");
		gamePanel.setLayer(playRound, 2);
		playRound.setEnabled(true);
		playRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				round.setText("Round " + String.valueOf(numRound));
				String sign = (String) choiceBox.getSelectedItem();
				game.getUser().setPosition(new Position(sign));
				if (sign == "[choose]")
				{
					JOptionPane.showMessageDialog(gamePanel, "Please choose a sign.");
				}
				else
				{		
					// Display da jogada do user
					displayUserSign(sign, image, userPanel);
					// Display da jogada do pc
					displayPCSign(game, pcPanel);
					winner = game.roundWinner();
					playRound.setEnabled(false);
					showResults.setEnabled(true);
				}
				}
			});
		playRound.setBounds(351, 407, 97, 25);
		gamePanel.add(playRound);
		
		// Criar painel para mostrar vencedor global
		JDesktopPane finalPanel = new JDesktopPane();
		finalPanel.setBackground(new Color(100, 149, 237));
		finalPanel.setBorder(null);
		playButtons.add(finalPanel, "final");
		JLabel background3 = new JLabel(" ");
		finalPanel.setLayer(background3, 1);
		background3.setIcon(new ImageIcon("Background1.png"));
		background3.setBounds(-13, -17, 862, 483);
		finalPanel.add(background3);
		
		// Adição de informação sob pontuações finais
		JLabel finalScores = new JLabel("Final Scores:");
		finalPanel.setLayer(finalScores, 2);
		finalScores.setFont(new Font("Tahoma", Font.PLAIN, 21));
		finalScores.setBounds(225, 205, 144, 27);
		finalPanel.add(finalScores);
		
		JTextField userFinal = new JTextField();
		finalPanel.setLayer(userFinal, 2);
		userFinal.setEditable(false);
		userFinal.setForeground(Color.BLACK);
		userFinal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userFinal.setBounds(223, 242, 170, 36);
		finalPanel.add(userFinal);
		userFinal.setColumns(10);
		
		JTextField pcFinal = new JTextField();
		finalPanel.setLayer(pcFinal, 2);
		pcFinal.setEditable(false);
		pcFinal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pcFinal.setColumns(10);
		pcFinal.setBounds(225, 289, 170, 36);
		finalPanel.add(pcFinal);
		
		// Adição de informação sobre o vencedor
		JTextField displayWinner = new JTextField();
		finalPanel.setLayer(displayWinner, 2);
		displayWinner.setFont(new Font("Tempus Sans ITC", Font.BOLD, 23));
		displayWinner.setEditable(false);
		displayWinner.setBounds(225, 95, 377, 47);
		finalPanel.add(displayWinner);
		displayWinner.setColumns(10);
		
		// Adição do botão para avançar entre rondas
		JButton NextRound = new JButton("Next");
		resultPane.setLayer(NextRound, 2);
		NextRound.setBounds(342, 407, 119, 25);
		resultPane.add(NextRound);
		NextRound.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CardLayout c=(CardLayout) ((playButtons.getLayout()));
			c.show(playButtons,"playing");
			nextRound(resultPane, gamePanel, showResults, round, image, pcPanel, playRound, finalPanel, userFinal, pcFinal, displayWinner);
		}
		});
		
		// Opção de jogar novamente ou voltar atrás
		JLabel playAgain = new JLabel("Do you want to play again?");
		playAgain.setForeground(new Color(255, 255, 255));
		finalPanel.setLayer(playAgain, 2);
		playAgain.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		playAgain.setBounds(29, 403, 272, 37);
		finalPanel.add(playAgain);
		
		JButton no = new JButton("No");
		no.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		no.setForeground(new Color(255, 0, 0));
		finalPanel.setLayer(no, 2);
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numRound = 1;
				setVisible(false);
				App.Menu.setVisible(true);
			}
		});
		no.setBounds(433, 411, 97, 25);
		finalPanel.add(no);
		
		JButton yes = new JButton("Yes");
		yes.setForeground(new Color(100, 149, 237));
		yes.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		finalPanel.setLayer(yes, 2);
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showResults.setEnabled(false);
				game.resetGame();
				numRound = 1;
				round.setText(" ");
				image.setIcon(new ImageIcon("default.png"));
				pcPanel.removeAll();
				playRound.setEnabled(true);
				CardLayout c=(CardLayout) ((playButtons.getLayout()));
				c.show(playButtons,"playing");
			}
		});
		yes.setBounds(311, 411, 97, 25);
		finalPanel.add(yes);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Ana Maria\\Desktop\\tt2.png"));
		lblNewLabel.setBounds(80, 0, 626, 402);
		finalPanel.add(lblNewLabel);
		
		
		//--------------------------------------------------------------------------------------------------------------		
		// Jogo com dispositivo: usando EMG

		JLayeredPane playEMG = new JLayeredPane();  
		gameMenu.add(playEMG, "Game using EMG sensors");
		playEMG.setLayout(new CardLayout(0, 0));
		
		// Painel do jogo propriamente dito
		JDesktopPane gameEMGPanel = new JDesktopPane();
		gameEMGPanel.setBorder(null);
		playEMG.add(gameEMGPanel, "playing EMG");
		JLabel backgroundEMG1 = new JLabel(" ");
		gameEMGPanel.setLayer(backgroundEMG1, 1);
		backgroundEMG1.setIcon(new ImageIcon("Background1.png"));
		backgroundEMG1.setBounds(-15, -13, 865, 470);
		gameEMGPanel.add(backgroundEMG1);
		
		// Identificação do campo do User e do Computer no painel do jogo
		JLabel labelEMGUser = new JLabel("User");
		gameEMGPanel.setLayer(labelEMGUser, 2);
		labelEMGUser.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelEMGUser.setBounds(145, 32, 66, 25);
		gameEMGPanel.add(labelEMGUser);
		
		JLabel labelEMGPc = new JLabel("Computer");
		gameEMGPanel.setLayer(labelEMGPc, 2);
		labelEMGPc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelEMGPc.setBounds(584, 32, 135, 25);
		gameEMGPanel.add(labelEMGPc);
		
		// Campo onde vai ser mostrado a ronda atual
		JTextField roundsEMG = new JTextField();
		gameEMGPanel.setLayer(roundsEMG, 2);
		roundsEMG.setHorizontalAlignment(SwingConstants.CENTER);
		roundsEMG.setFont(new Font("Tahoma", Font.PLAIN, 17));
		roundsEMG.setEditable(false);
		roundsEMG.setBounds(372, 13, 76, 35);
		gameEMGPanel.add(roundsEMG);
		
		// Painel onde vai ser mostrado o gesto do utilizador
		Panel userEMGPanel = new Panel();
		gameEMGPanel.setLayer(userEMGPanel, 2);
		userEMGPanel.setBackground(new Color(245, 245, 245));
		userEMGPanel.setBounds(10, 63, 377, 339);
		gameEMGPanel.add(userEMGPanel);
		JLabel imageEMG = new JLabel("");
		GroupLayout gl_userEMGPanel = new GroupLayout(userEMGPanel);
		
		gl_userEMGPanel.setHorizontalGroup(
				gl_userEMGPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_userEMGPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(imageEMG, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			gl_userEMGPanel.setVerticalGroup(
				gl_userEMGPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_userEMGPanel.createSequentialGroup()
						.addGap(5)
						.addComponent(imageEMG, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
			);
			userEMGPanel.setLayout(gl_userEMGPanel);
		
		// Painel onde vai ser mostrado o gesto do pc
		Panel pcEMGPanel = new Panel();
		gameEMGPanel.setLayer(pcEMGPanel, 2);
		pcEMGPanel.setBackground(new Color(245, 245, 245));
		pcEMGPanel.setBounds(416, 63, 365, 339);
		gameEMGPanel.add(pcEMGPanel);
		
		// Painel para mostrar resultado entre rondas
		JDesktopPane resultEMGPane = new JDesktopPane();
		resultEMGPane.setBorder(null);
		playEMG.add(resultEMGPane, "Round EMG Result");
		JLabel backgroundEMG2 = new JLabel(" ");
		resultEMGPane.setLayer(backgroundEMG2, 1);
		backgroundEMG2.setIcon(new ImageIcon("Background1.png"));
		backgroundEMG2.setBounds(-15, -13, 865, 470);
		resultEMGPane.add(backgroundEMG2);
				
		JTextField roundEMGResult = new JTextField();
		resultEMGPane.setLayer(roundEMGResult, 2);
		roundEMGResult.setFont(new Font("Tahoma", Font.PLAIN, 17));
		roundEMGResult.setEditable(false);
		roundEMGResult.setBounds(362, 13, 76, 35);
		resultEMGPane.add(roundEMGResult);
		
		// Identificação do campo do User e do Computer no painel dos resultados
		JLabel labelEMGResultsUser = new JLabel("User");
		resultEMGPane.setLayer(labelEMGResultsUser, 2);
		labelEMGResultsUser.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelEMGResultsUser.setBounds(165, 36, 66, 25);
		resultEMGPane.add(labelEMGResultsUser);
		
		JLabel labelEMGResultsPc = new JLabel("Computer");
		resultEMGPane.setLayer(labelEMGResultsPc, 2);
		labelEMGResultsPc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelEMGResultsPc.setBounds(561, 36, 135, 25);
		resultEMGPane.add(labelEMGResultsPc);
		
		// Adição do painel onde vai ser mostrado se o utilizador venceu a ronda ou não
		JTextField userEMGWin = new JTextField();
		resultEMGPane.setLayer(userEMGWin, 2);
		userEMGWin.setEditable(false);
		userEMGWin.setHorizontalAlignment(SwingConstants.CENTER);
		userEMGWin.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 50));
		userEMGWin.setBounds(32, 63, 338, 212);
		resultEMGPane.add(userEMGWin);
		userEMGWin.setColumns(10);
				
		// Adição do painel onde vai ser mostrado se o pc venceu a ronda ou não
		JTextField pcEMGWin = new JTextField();
		resultEMGPane.setLayer(pcEMGWin, 2);
		pcEMGWin.setEditable(false);
		pcEMGWin.setHorizontalAlignment(SwingConstants.CENTER);
		pcEMGWin.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 50));
		pcEMGWin.setBounds(421, 63, 344, 212);
		resultEMGPane.add(pcEMGWin);
		pcEMGWin.setColumns(10);
						
		// Mostra a pontuação atual do utilizador
		JTextField userEMGScore = new JTextField();
		resultEMGPane.setLayer(userEMGScore, 2);
		userEMGScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userEMGScore.setEditable(false);
		userEMGScore.setBounds(139, 288, 119, 41);
		resultEMGPane.add(userEMGScore);
		userEMGScore.setColumns(10);
				
		// Mostra a pontuação atual do pc
		JTextField pcEMGScore = new JTextField();
		resultEMGPane.setLayer(pcEMGScore, 2);
		pcEMGScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pcEMGScore.setEditable(false);
		pcEMGScore.setColumns(10);
		pcEMGScore.setBounds(530, 288, 146, 41);
		resultEMGPane.add(pcEMGScore);
		
		// Botão de avançar para resultados
		JButton showEMGResults = new JButton("Show Round Results");
		gameEMGPanel.setLayer(showEMGResults, 2);
		showEMGResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameEMGPanel.setVisible(false);
				resultEMGPane.setVisible(true);
				roundEMGResult.setText("Round " + String.valueOf(numRound));
				CardLayout c=(CardLayout) ((playEMG.getLayout()));
				c.show(playEMG,"Round EMG Result");
				displayWinner(winner, userEMGWin, pcEMGWin, userEMGScore, pcEMGScore, game);
			}
		});
		showEMGResults.setBounds(613, 408, 168, 25);
		gameEMGPanel.add(showEMGResults);
		showEMGResults.setEnabled(false);	
		
		// Adição do botão para jogar ronda
		JButton playEMGRound = new JButton("Play Round");
		gameEMGPanel.setLayer(playEMGRound, 2);
		playEMGRound.setEnabled(true);
		playEMGRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				roundsEMG.setText("Round " + String.valueOf(numRound)); 
				// Recebe os caracteres de Arduino e define a posição do utilizador de acordo
				char[] recieved = Arduino.sendAndRecieve();
				char ch1 = recieved[0];                                          
				char ch2 = recieved[1];
				game.getUser().definePosition(ch1, ch2);
				if (game.getUser().getPositionName()=="UNRECOGNISED")
				{
					JOptionPane.showMessageDialog(gameEMGPanel, "Sign not recognised! Please try again.");
				}
				else
				{	
					String sign = game.getUser().getPositionName();
					// Display da jogada do user
					displayUserSign(sign, imageEMG, userEMGPanel);
					// Display da jogada do pc
					displayPCSign(game, pcEMGPanel);
					winner = game.roundWinner();
					playEMGRound.setEnabled(false);
					showEMGResults.setEnabled(true);
				}
			}
		});
		playEMGRound.setBounds(351, 407, 97, 25);
		gameEMGPanel.add(playEMGRound);
		
		// Criar painel para mostrar vencedor global
		JDesktopPane finalEMGPanel = new JDesktopPane();
		finalEMGPanel.setBackground(new Color(100, 149, 237));
		finalEMGPanel.setBorder(null);
		playEMG.add(finalEMGPanel, "final EMG");
		JLabel backgroundEMG3 = new JLabel(" ");
		finalEMGPanel.setLayer(backgroundEMG3, 1);
		backgroundEMG3.setIcon(new ImageIcon("Background1.png"));
		backgroundEMG3.setBounds(-15, -13, 865, 470);
		finalEMGPanel.add(backgroundEMG3);
		
		// Adição de informação sob pontuações finais
		JLabel finalEMGScores = new JLabel("Final Scores:");
		finalEMGPanel.setLayer(finalEMGScores, 2);
		finalEMGScores.setFont(new Font("Tahoma", Font.PLAIN, 21));
		finalEMGScores.setBounds(225, 205, 144, 27);
		finalEMGPanel.add(finalEMGScores);
		
		JTextField userEMGFinal = new JTextField();
		finalEMGPanel.setLayer(userEMGFinal, 2);
		userEMGFinal.setEditable(false);
		userEMGFinal.setForeground(Color.BLACK);
		userEMGFinal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userEMGFinal.setBounds(225, 243, 170, 36);
		finalEMGPanel.add(userEMGFinal);
		userEMGFinal.setColumns(10);
		
		JTextField pcEMGFinal = new JTextField();
		finalEMGPanel.setLayer(pcEMGFinal, 2);
		pcEMGFinal.setEditable(false);
		pcEMGFinal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pcEMGFinal.setColumns(10);
		pcEMGFinal.setBounds(225, 290, 170, 36);
		finalEMGPanel.add(pcEMGFinal);
		
		// Adição de informação sobre o vencedor
		JTextField displayEMGWinner = new JTextField();
		finalEMGPanel.setLayer(displayEMGWinner, 2);
		displayEMGWinner.setFont(new Font("Tempus Sans ITC", Font.BOLD, 23));
		displayEMGWinner.setEditable(false);
		displayEMGWinner.setBounds(229, 88, 375, 45);
		finalEMGPanel.add(displayEMGWinner);
		displayEMGWinner.setColumns(10);
		
		// Adição do botão para avançar entre rondas
		JButton NextEMGRound = new JButton("Next");
		resultEMGPane.setLayer(NextEMGRound, 2);
		NextEMGRound.setBounds(342, 407, 119, 25);
		resultEMGPane.add(NextEMGRound);
		
		NextEMGRound.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CardLayout c=(CardLayout) ((playEMG.getLayout()));
			c.show(playEMG,"playing EMG");
			nextRound(resultEMGPane, gameEMGPanel, showEMGResults, roundsEMG, imageEMG, pcEMGPanel, playEMGRound, finalEMGPanel, userEMGFinal, pcEMGFinal, displayEMGWinner);
		}
		});
		
		// Opção de jogar novamente ou voltar atrás
		JLabel playEMGAgain = new JLabel("Do you want to play again?");
		playEMGAgain.setForeground(new Color(255, 255, 255));
		finalEMGPanel.setLayer(playEMGAgain, 2);
		playEMGAgain.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		playEMGAgain.setBounds(25, 406, 263, 37);
		finalEMGPanel.add(playEMGAgain);
		
		JButton no1 = new JButton("No");
		no1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		no1.setForeground(new Color(255, 0, 0));
		finalEMGPanel.setLayer(no1, 2);
		no1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arduino.getCommPort().closePort();
				numRound = 1;
				setVisible(false);
				App.Menu.setVisible(true);
			}
		});
		no1.setBounds(417, 412, 97, 25);
		finalEMGPanel.add(no1);
		
		JButton yes1 = new JButton("Yes");
		yes1.setForeground(new Color(100, 149, 237));
		yes1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		finalEMGPanel.setLayer(yes1, 2);
		yes1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEMGResults.setEnabled(false);
				game.resetGame();
				numRound = 1;
				roundsEMG.setText(" ");
				imageEMG.setIcon(new ImageIcon("default.png"));
				pcEMGPanel.removeAll();
				playEMGRound.setEnabled(true);
				
				CardLayout c=(CardLayout) ((playEMG.getLayout()));
				c.show(playEMG,"playing EMG");
			}
		});
		yes1.setBounds(298, 412, 97, 25);
		finalEMGPanel.add(yes1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Ana Maria\\Desktop\\tt2.png"));
		lblNewLabel_1.setBounds(86, 0, 635, 392);
		finalEMGPanel.add(lblNewLabel_1);
	}
	
	// Função que no final de uma ronda mostra o vencedor e os pontos atuais de cada jogador
	private void displayWinner(int winner, JTextField userWin, JTextField pcWin, JTextField userScore, JTextField pcScore, Game game) {
		if(winner==1)
		{
			userWin.setText("Winner :)");
			pcWin.setText("Loser :(");
			userScore.setText("Score: " + game.getUser().getScore());
			pcScore.setText("Score: " + game.getComputer().getScore());
		}
		else if(winner==-1)
		{
			userWin.setText("Loser :(");
			pcWin.setText("Winner :)");
			userScore.setText("Score: " + game.getUser().getScore());
			pcScore.setText("Score: " + game.getComputer().getScore());
		}
		else
		{
			userWin.setText("It's a tie!");
			pcWin.setText("It's a tie!");
			userScore.setText("Score: " + game.getUser().getScore());
			pcScore.setText("Score: " + game.getComputer().getScore());
		}
	}
	
	// Função que faz o display da jogada do user conforme a string recebida
	private void displayUserSign(String sign, JLabel image, Panel userPanel) {
		switch (sign) {
		case "Scissors": 
		{
			image.setIcon(new ImageIcon("Tesoura.gif"));
			break;
		}
		case "Rock":
		{
			image.setIcon(new ImageIcon("Pedra.gif"));
			break;
		}
		case "Paper":
		{
			image.setIcon(new ImageIcon("Papel.gif"));
			break;
		}}
		userPanel.add(image);
	}
	
	// Função que faz o display da jogada aleatória do pc
	private void displayPCSign(Game game, Panel pcPanel) {
		do
		{
			game.getComputer().definePosition();
		}while(game.getComputer().getPositionName()=="UNRECOGNISED");
		CanvasPC handpc=new CanvasPC(game.getComputer().getPositionName()); 
		pcPanel.add(handpc);
	    pcPanel.setVisible(true);
		pcPanel.setLayout(new CardLayout(0, 0));
	}
	
	// Função que avalia se já se atingiu o número final de rondas e age de acordo
	private void nextRound(JDesktopPane resultPane, JDesktopPane gamePanel, JButton showResults, JTextField round, JLabel image, Panel pcPanel, JButton playRound, JDesktopPane finalPanel, JTextField userFinal, JTextField pcFinal, JTextField displayWinner) {
		numRound++;
		if(numRound<=game.getRounds())
		{
			resultPane.setVisible(false);
			gamePanel.setVisible(true);
			showResults.setEnabled(false);
			round.setText(" ");
			image.setIcon(new ImageIcon("default.png"));
			pcPanel.removeAll();
			playRound.setEnabled(true);
		}
		else
		{
			Sound sound = new Sound();
			resultPane.setVisible(false);
			gamePanel.setVisible(false);
			finalPanel.setVisible(true);
			userFinal.setText("   User: " + String.valueOf(game.getUser().getScore()));
			pcFinal.setText("   Computer: " + String.valueOf(game.getComputer().getScore()));
			
			if (game.getWinner()==1)
			{
				displayWinner.setText("	 The winner is " + game.getUser().getName() + "!");
				sound.play("win.wav");
				
			}
			else if (game.getWinner()==-1)
			{
				displayWinner.setText("	   The winner is " + game.getComputer().getName() + "!");
				sound.play("loss.wav");
			}
			else
			{
				displayWinner.setText("	  The game ended with a tie!");
			}
		}
	}
} 