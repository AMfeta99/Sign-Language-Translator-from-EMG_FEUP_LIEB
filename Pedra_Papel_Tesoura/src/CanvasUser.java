/* Classe que permite fazer o display da imagem apartir da string correspondente à posição
 * Tem como variável interna o nome da posição que vai desenhar
 */

import java.awt.*;
import java.util.Random;

import javax.swing.*; 

public class CanvasUser extends Canvas{  
	
	private String name; // Nome da posição/gesto
	
	// Construtor de Canvas a partir de uma string com o nome da posição
	public CanvasUser(String name)
	{
		this.name=name;
	}
	
	// Função que desenha a imagem do gesto de acordo com o seu nome
    public void paint(Graphics g) {
    	Graphics g1= (Graphics)g;
        Toolkit t1=Toolkit.getDefaultToolkit(); 

        switch(name)
        {
        case("Rock"):
        { 
        	Image i=t1.getImage("Pedra.gif"); 
        	g1.drawImage(i, 20,10,this);
        	break;
        }  
        case("Paper"):
        { 
        	Image i=t1.getImage("Papel.gif"); 
            g1.drawImage(i, 20,10,this); 
            break;
        }  
        case("Scissors"):
        {
        	Image i=t1.getImage("Tesoura.gif");
            g1.drawImage(i, 20,10,this);
            break;
        }
        }
    } 
    
    // Teste da classe Canvas
    public static void main(String[] args) { 
    	String userName = "Test";
    	User user = new User(userName);
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
        CanvasUser gesture = new CanvasUser(user.getPositionName());
        JFrame f = new JFrame();  
        f.add(gesture);  
        f.setSize(400,400);  
        f.setVisible(true);   
    }  
}  