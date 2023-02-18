/* This class will establish the communication between Java and Arduino
 * Tem tr�s vari�veis internas: a porta de comunica��o escolhida e os dois valores recebidos
 */

import com.fazecast.jSerialComm.*;

public class Communication {
	
	SerialPort chosenPort;
	char ch1;
	char ch2;
	
	// Construtor de Communication
	public Communication() {
		// Escolha da porta
		SerialPort[] ports = SerialPort.getCommPorts();
		SerialPort p = ports[0];
		String portDescriptive = p.getSystemPortName();
		chosenPort = SerialPort.getCommPort(portDescriptive);
		// Defini��o dos timeouts de escrita e de leitura
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING | SerialPort.TIMEOUT_READ_BLOCKING, 5000, 200);
	}
	
	// Fun��o que envia sinal a Arduino e recebe o estado de ativa��o dos dois canais
	// Define a posi��o do utilizador no jogo conforme o recebido
	public char[] sendAndRecieve() {
		char[] recieved = new char[2];
		// Abertura da porta
		if (chosenPort.openPort())
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Envio de um byte
			byte[] send = new byte[]{(byte) 's'};
			if (chosenPort.writeBytes(send, (long) send.length) == send.length);
			{
				// Recebe dois bytes
				byte[] bytesRecieved = new byte[2];
				chosenPort.readBytes(bytesRecieved, 2);
				recieved[0] = (char) bytesRecieved[0];
				recieved[1] = (char) bytesRecieved[1];
			}
		}
		return recieved;
	}
	
	// Recebe char de Arduino
	public char recieveChar() {
		byte[] recieved = new byte[1];
		chosenPort.readBytes(recieved, 1);
		return ((char) recieved[0]);
	}
	
	// Envia char para Arduino
	public int sendChar() {
		byte[] send = new byte[]{(byte) 's'};
		return chosenPort.writeBytes(send, (long) send.length);
	}
	
	// Retorna a porta s�rie em uso
	public SerialPort getCommPort() {
		return chosenPort;
	}
}
