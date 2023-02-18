/* Interface grafica de uma aplicação que corresponde a um protótipo de um tradutor de linguagem gestual
 * Esta pode ser dividida em 2 grandes partes:
 * 1. Tradutor:
 * - "Word - Gesture" que ao escrever a palavra, este mostra o gesto correspondente
 * - "Gesture - Word" que ao escolher o gesto, quer a partir de uma combobox quer por identificação de EMG escreve a sua tradução e respetivo som
 * 
 * 2. Jogo Pedra-Papel-Tesoura contra pc:
 *- Identificando gesto pelo EMG recolhido
 *- Escolhendo o gesto a partir de uma combobox
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class App {

	public static JFrame Menu;	

	// Lançar a aplicação
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.Menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor da aplicação
	public App() {		
		// Inicializa o menu principal da app
		Menu = new JFrame();
		Menu.setIconImage(Toolkit.getDefaultToolkit().getImage("Symbol.jpg"));
		Menu.setResizable(false);
		Menu.setBounds(100, 100, 801, 507);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menu.getContentPane().setLayout(null);
				
		JDesktopPane inicialMenu = new JDesktopPane();
		inicialMenu.setBounds(0, 0, 795, 478);
		inicialMenu.setBorder(null);
		inicialMenu.setBackground(new Color(245, 255, 250));
		Menu.getContentPane().add(inicialMenu);
		
		// Adiciona imagem ao menu da app
		JLabel label = new JLabel(" ");
		inicialMenu.setLayer(label, 0);
		label.setIcon(new ImageIcon("MenuBackground.png"));
		label.setBounds(0, 78, 798, 400);
		inicialMenu.add(label);
		
		// Adiciona botão que redireciona ao tradutor
		JButton btnTranslator = new JButton("Translator");
		inicialMenu.setLayer(btnTranslator, 1);
		btnTranslator.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTranslator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Translator frame = new Translator();
				frame.setVisible(true);
				Menu.setVisible(false);} 
		});
		btnTranslator.setBackground(SystemColor.control);
		btnTranslator.setBounds(70, 418, 159, 34);
		inicialMenu.add(btnTranslator);
		
		// Adiciona botão que redireciona ao menu do jogo
		JButton btnPlay = new JButton("Play");
		inicialMenu.setLayer(btnPlay, 1);
		btnPlay.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Play frame = new Play();
				frame.setVisible(true);
				Menu.setVisible(false);} 
		});
		btnPlay.setBackground(SystemColor.control);
		btnPlay.setBounds(591, 418, 159, 34);
		inicialMenu.add(btnPlay);
		
		// Adiciona título ao menu
		JTextPane titleApp = new JTextPane();
		titleApp.setToolTipText("");
		titleApp.setText("			    Sign Language");
		titleApp.setFont(new Font("Yu Gothic Medium", Font.BOLD, 34));
		titleApp.setEditable(false);
		titleApp.setBackground(SystemColor.control);
		titleApp.setBounds(0, 0, 798, 78);
		inicialMenu.add(titleApp);
	}
}

