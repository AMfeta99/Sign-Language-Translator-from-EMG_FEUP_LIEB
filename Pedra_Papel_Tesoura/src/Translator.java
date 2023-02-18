/* O tradutor tem duas funções distintas: 
 * 1. escrever a palavra e ele mostra o gesto;
 * 2. fazer o gesto e através de EMG ou pela utilização de butões reconhecê-lo e mostrá-lo.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import java.awt.event.*;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Translator extends JFrame {

	private static final long serialVersionUID = 1L;

	// Lançar o tradutor
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Translator frame = new Translator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor do tradutor
	public Translator() {
		setTitle("Translator");
		// Cria a frame principal
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 481);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//---------------------------------------------------------------------------------------------------------------
		// Cria as duas opções do tradutor: passar de palavra para gesto e vice-versa
		// Gesto para palavra pode ser com combobox ou com leitor de EMG
		JLayeredPane translator = new JLayeredPane();
		contentPane.add(translator, "Translator");
		translator.setLayout(new CardLayout(0, 0));
		
		JDesktopPane word_gesture = new JDesktopPane();
		translator.add(word_gesture, "Word - Gesture");
		JLabel background1 = new JLabel(" ");
		word_gesture.setLayer(background1, 0);
		background1.setIcon(new ImageIcon("Background1.png"));
		background1.setBounds(0, 0, 850, 457);
		word_gesture.add(background1);
		
		JDesktopPane gesture_word = new JDesktopPane();
		translator.add(gesture_word, "Gesture - Word");
		JLabel background2 = new JLabel(" ");
		gesture_word.setLayer(background2, 0);
		background2.setIcon(new ImageIcon("Background2.png"));
		background2.setBounds(0, 0, 850, 457);
		gesture_word.add(background2);
		
		JDesktopPane gesture_word_emg = new JDesktopPane();
		translator.add(gesture_word_emg, "Gesture - Word with EMG");
		JLabel background3 = new JLabel(" ");
		gesture_word_emg.setLayer(background3, 0);
		background3.setIcon(new ImageIcon("Background3.png"));
		background3.setBounds(0, 0, 850, 457);
		gesture_word_emg.add(background3);
			
		//---------------------------------------------------------------------------------------------------------------	
		// Opção Word - Gesture
		// Cria opção de voltar atrás para o menu principal
		JButton back = new JButton("Back");
		word_gesture.setLayer(back, 1);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				App.Menu.setVisible(true);
				}
		});
		back.setBounds(39, 396, 89, 23);
		word_gesture.add(back);
		
		// Cria opção de mudar automaticamente de Word-Gesture para Gesture-Word
		JButton change = new JButton("<->");
		word_gesture.setLayer(change, 1);
		change.setFont(new Font("Tahoma", Font.PLAIN, 13));
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c=(CardLayout) ((translator.getLayout()));
				c.show(translator,"Gesture - Word");
			}
		});
		change.setBounds(372, 58, 89, 23);
		word_gesture.add(change);
		
		// Adiciona painel onde vai ser mostrada a tradução
		Panel gesturePanel = new Panel();
		word_gesture.setLayer(gesturePanel, 1);
		gesturePanel.setBackground(new Color(192, 192, 192));
		gesturePanel.setBounds(492, 101, 325, 265);
		word_gesture.add(gesturePanel);
		JLabel image = new JLabel(" ");
		
		// Insere o texto para ser traduzido		
		JTextField text = new JTextField();
		word_gesture.setLayer(text, 1);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 45));
		text.setBounds(39, 101, 301, 265);
		word_gesture.add(text);
		text.setColumns(10);
		
		// Cria as 2 etiquetas que vão definir os locais onde se insere a palavra e onde se vê o gesto, respetivamente
		JLabel wordLabel = new JLabel("Word");
		word_gesture.setLayer(wordLabel, 1);
		wordLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		wordLabel.setBounds(156, 46, 69, 38);
		word_gesture.add(wordLabel);
				
		JLabel gestureLabel = new JLabel("Gesture");
		word_gesture.setLayer(gestureLabel, 1);
		gestureLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		gestureLabel.setBounds(617, 54, 99, 23);
		word_gesture.add(gestureLabel);
		
		// Insere botão que vai fazer a tradução
		JButton translate = new JButton("Translate");
		word_gesture.setLayer(translate, 1);
		translate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = text.getText();
				// Passa a mensagem para minúsculas
				name = name.toLowerCase(); 
				// Identifica a mensagem e mostra imagem correspondente
				switch (name) {
				case "scissors": case "tesoura": 
				{
					image.setIcon(new ImageIcon("Tesourapc.gif"));
					break;
				}
				case "rock": case "pedra":
				{
					image.setIcon(new ImageIcon("Pedrapc.gif"));
					break;
				}
				case "paper": case "papel":
				{
					image.setIcon(new ImageIcon("Papelpc.gif"));
					break;
				}	
				default:
				{
					image.setIcon(new ImageIcon("NotRecognised.PNG"));
					break;
				}}
			}
		});
		translate.setBounds(372, 218, 89, 23);
		word_gesture.add(translate);
		
		GroupLayout gl_gesturePanel = new GroupLayout(gesturePanel);
		gl_gesturePanel.setHorizontalGroup(
			gl_gesturePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gesturePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_gesturePanel.setVerticalGroup(
			gl_gesturePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_gesturePanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gesturePanel.setLayout(gl_gesturePanel);

		//---------------------------------------------------------------------------------------------------------------	
		// Opção Gesture - Word
		// Cria opção de voltar atrás para o menu principal
		JButton back1 = new JButton("Back");
		gesture_word.setLayer(back1, 1);
		back1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				App.Menu.setVisible(true);
				}
		});
		back1.setBounds(39, 396, 89, 23);
		gesture_word.add(back1);
		
		// Cria opção de mudar automaticamente de Gesture-Word para Word-Gesture
		JButton change1 = new JButton("<->");
		gesture_word.setLayer(change1, 1);
		change1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		change1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c=(CardLayout) ((translator.getLayout()));
				c.show(translator,"Word - Gesture");
			}
		});
		change1.setBounds(400, 56, 89, 23);
		gesture_word.add(change1);
		
		// Cria as 2 etiquetas que vão definir os locais onde se vê a palavra e onde se apresneta o gesto, respetivamente
		JLabel labelWord = new JLabel("Word");
		gesture_word.setLayer(labelWord, 1);
		labelWord.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelWord.setBounds(631, 56, 69, 38);
		gesture_word.add(labelWord);
		
		JLabel labelGesture = new JLabel("Gesture");
		gesture_word.setLayer(labelGesture, 1);
		labelGesture.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelGesture.setBounds(151, 64, 99, 23);
		gesture_word.add(labelGesture);
		
		// Insere o local onde vai ser apresentado o texto traduzido
		JTextField translation = new JTextField();
		gesture_word.setLayer(translation, 1);
		translation.setHorizontalAlignment(SwingConstants.CENTER);
		translation.setEditable(false);
		translation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		translation.setBounds(494, 107, 323, 278);
		gesture_word.add(translation);
		translation.setColumns(10);
		
		// Insere o local em que vai ser escolhido o gesto
		Panel gesture = new Panel();
		gesture_word.setLayer(gesture, 1);
		gesture.setBackground(new Color(192, 192, 192));
		gesture.setBounds(28, 107, 366, 278);
		gesture_word.add(gesture);
		gesture.setLayout(new CardLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBorder(null);
		gesture.add(panel, "name_52679171794700");
		JLabel lbImage = new JLabel(" ");
		
		// Adiciona combo box com nomes dos 3 gestos possíveis, para efeitos de teste não tendo o dispositivo EMG
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rock", "Paper", "Scissors"}));
		
		// Inicializa o som que vai ser tocado ao mostrar a palavra
		Sound sound = new Sound();
		
		// Botão responsável por ler da combobox, apresentar gesto e respetiva tradução
		JButton translateButton = new JButton("Translate");
		gesture_word.setLayer(translateButton, 1);
		translateButton.setBounds(400, 234, 89, 23);
		translateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign = (String) comboBox.getSelectedItem();
				displayGesture(sign, translation, lbImage, sound, gesture_word);				
			}
		});
		gesture_word.add(translateButton);
		
		// Inicializa a comunicação série
		Communication Arduino = new Communication();
		
		// Botão para usar dispositivo de EMG em vez da combo box
		JButton useDevice = new JButton("Use EMG device");
		gesture_word.setLayer(useDevice, 1);
		useDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arduino.getCommPort().openPort();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException f) {
					f.printStackTrace();
				}
				CardLayout c=(CardLayout) ((translator.getLayout()));
				c.show(translator,"Gesture - Word with EMG");
			}
		});
		useDevice.setBounds(382, 13, 130, 23);
		gesture_word.add(useDevice);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(lbImage, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(127)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(137, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addComponent(lbImage, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
		//---------------------------------------------------------------------------------------------------------------	
		// Opção Gesture - Word mas com sinal EMG recolhido
		// Adiciona painel e label em que vai ser mostrado o gesto feito pelo utilizador
		Panel gestureEMG = new Panel();
		gesture_word_emg.setLayer(gestureEMG, 1);
		gestureEMG.setBackground(Color.LIGHT_GRAY);
		gestureEMG.setBounds(28, 107, 366, 278);
		gesture_word_emg.add(gestureEMG);
		gestureEMG.setLayout(new CardLayout(0, 0));
		JLabel imageEMG = new JLabel("");
		gestureEMG.add(imageEMG, "name_1948088075000");
		
		// Adiciona botão de voltar atrás para o menu principal
		JButton back2 = new JButton("Back");
		gesture_word_emg.setLayer(back2, 1);
		back2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arduino.getCommPort().closePort();
				setVisible(false);
				App.Menu.setVisible(true);
			}
		});
		back2.setBounds(39, 396, 89, 23);
		gesture_word_emg.add(back2);
		
		// Adiciona botão para voltar a Word - Gesture
		JButton change2 = new JButton("<->");
		gesture_word_emg.setLayer(change2, 1);
		change2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		change2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Arduino.getCommPort().closePort();
				imageEMG.setIcon(new ImageIcon("default.png"));
				translation.setText(" ");
				CardLayout c=(CardLayout) ((translator.getLayout()));
				c.show(translator,"Word - Gesture");
			}
		});
		change2.setBounds(400, 56, 89, 23);
		gesture_word_emg.add(change2);
		
		// Label que indica sítio da palavra e do gesto, respetivamente
		JLabel label = new JLabel("Word");
		gesture_word_emg.setLayer(label, 1);
		label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		label.setBounds(631, 56, 69, 38);
		gesture_word_emg.add(label);
		
		JLabel label1 = new JLabel("Gesture");
		gesture_word_emg.setLayer(label1, 1);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		label1.setBounds(151, 64, 99, 23);
		gesture_word_emg.add(label1);
		
		// Sítio onde vai aparecer a palavra traduzida
		JTextField translation2 = new JTextField();
		gesture_word_emg.setLayer(translation2, 1);
		translation2.setHorizontalAlignment(SwingConstants.CENTER);
		translation2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		translation2.setEditable(false);
		translation2.setColumns(10);
		translation2.setBounds(494, 107, 323, 278);
		gesture_word_emg.add(translation2);
		
		// Botão que inicia a tradução
		JButton translate2 = new JButton("Translate");
		gesture_word_emg.setLayer(translate2, 1);
		translate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Apaga traduções que já pudessem existir
				translation2.setText(" ");
				imageEMG.setIcon(new ImageIcon("default.PNG"));
				// Recebe os caracteres de Arduino e define a posição do utilizador de acordo
				char[] recieved = Arduino.sendAndRecieve();
				char ch1 = recieved[0];                                          
				char ch2 = recieved[1];
				// Define a nova posição e faz o seu display com respetiva tradução
				Position position = new Position(ch1, ch2);
				displayGesture(position.getName(), translation2, imageEMG, sound, gesture_word_emg);
		}});
		translate2.setBounds(400, 234, 89, 23);
		gesture_word_emg.add(translate2);
		
		// Botão para voltar a Gesture - Word sem dispositivo
		JButton withoutEmgDevice = new JButton("Without EMG device");
		gesture_word_emg.setLayer(withoutEmgDevice, 1);
		withoutEmgDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arduino.getCommPort().closePort();
				imageEMG.setIcon(new ImageIcon("default.png"));
				translation.setText(" ");
				CardLayout c=(CardLayout) ((translator.getLayout()));
				c.show(translator,"Gesture - Word");
			}
		});
		withoutEmgDevice.setBounds(365, 13, 161, 23);
		gesture_word_emg.add(withoutEmgDevice);
	}

	// Função que faz display do gesto conforme a string que recebe como parâmetro
	private void displayGesture(String gesture, JTextField translation, JLabel image, Sound sound, JDesktopPane pane) {
		switch (gesture) {
		case "Scissors": 
		{
			translation.setText("  Scissors / Tesoura");
			image.setIcon(new ImageIcon("Tesoura.gif"));
			sound.play("Scissors.wav");
			break;
		}
		case "Rock":
		{
			translation.setText("  Rock / Pedra");
			image.setIcon(new ImageIcon("Pedra.gif"));
			sound.play("Rock.wav");
			break;
		}
		case "Paper":
		{
			translation.setText("  Paper / Papel");
			image.setIcon(new ImageIcon("Papel.gif"));
			sound.play("Paper.wav");
			break;
		}
		default:
		{
			JOptionPane.showMessageDialog(pane, "Sign not recognised! Please try again.");
			break;
		}}
	}
}