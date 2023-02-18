/* Classe que permite fazer o display da imagem apartir da string correspondente à posição
 * Tem como variável interna o nome da posição que vai desenhar
 */

import java.awt.*;  
import javax.swing.*;
 
public class CanvasPC extends Canvas{  
	

	private String name; // Nome da posição/gesto
	
	// Construtor de Canvas a partir de uma string com o nome da posição
	public CanvasPC(String name)
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
        	Image i=t1.getImage("Pedrapc.gif"); 
        	g1.drawImage(i, 7,10,this);
        	break;
        }  
        case("Paper"):
        { 
        	Image i=t1.getImage("Papelpc.gif"); 
            g1.drawImage(i, 7,10,this); 
            break;
        }  
        case("Scissors"):
        {
        	Image i=t1.getImage("Tesourapc.gif");
            g1.drawImage(i, 7,10,this);
            break;
        }
        default:
        {
        	Image i=t1.getImage("default.png");
            g1.drawImage(i, 7,10,this);
            break;
        }
        }
    } 
    
    // Teste da classe Canvas
    public static void main(String[] args) { 
    	String userName = "Test";
    	Computer pc = new Computer(userName);
    	do {
			pc.definePosition(); 
		} while(pc.getPositionName()=="UNRECOGNISED");
    	
        CanvasPC gesture = new CanvasPC(pc.getPositionName());
        JFrame f = new JFrame(); 
        f.add(gesture);  
        f.setSize(400,400);  
        f.setVisible(true);   
    }  
  
}  