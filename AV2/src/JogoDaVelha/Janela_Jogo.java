package JogoDaVelha;

import javax.swing.JFrame;

public class Janela_Jogo {
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame("Jogo da Velha AV2");
		frame.setSize(600, 630);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		Jogo_da_velha velha = new Jogo_da_velha();
		velha.setBounds(0,0,600,600);
		frame.add(velha);
		
		frame.addMouseListener(velha);
		
		
	}
	
}