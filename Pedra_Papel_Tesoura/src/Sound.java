import java.applet.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Sound extends JFrame {

	JButton play = new JButton("sound");
	
	public Sound() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200,200);
		setLocationRelativeTo(null);
		setVisible(false);
		
		add(play);
		play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			//	play(name);
			}
		});
	}
	
	public void play (String name)
	{
		URL url = Sound.class.getResource(name);
		AudioClip audio =Applet.newAudioClip(url);
		audio.play();
	}
	
	
	public static void main(String[] args) {
		String name ="ola";
		Sound sound=new Sound();
	}
	
	
	
}
