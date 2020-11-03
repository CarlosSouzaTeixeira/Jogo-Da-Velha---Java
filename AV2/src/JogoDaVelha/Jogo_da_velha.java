package JogoDaVelha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Jogo_da_velha extends JPanel implements MouseListener {

	// configuracao das fontes utilizadas
	Font minhaFont = new Font("Kristen ITC", Font.BOLD, 65);
	Font minhaFont2 = new Font("Kristen ITC", Font.BOLD, 30);
	Font minhaFont3 = new Font("Kristen ITC", Font.BOLD, 20);

	// variaveis utilizadas
	int matriz[][];
	int jogador;
	int ganhador;
	boolean JogarDnv;
	int Vitoria1;
	int Vitoria2;

	// personalizacao de cores
	Color cor1;
	Color cor2;
	Color cor3;
	Color cor4;

	public Jogo_da_velha() {

		// definicao das variaveis
		matriz = new int[3][3];
		jogador = 1;
		ganhador = 0;
		JogarDnv = false;
		Vitoria1 = 0;
		Vitoria2 = 0;

		// personalizacao de cores em r,g,b
		cor1 = new Color(0, 160, 0);
		cor2 = new Color(80, 80, 80);
		cor3 = new Color(0, 255, 255);
		cor4 = new Color(255, 140, 26);
	}

	@Override
	public void paintComponent(Graphics g2) {

		Graphics2D g = (Graphics2D) g2.create();
		System.out.println();
		for (int linha = 0; linha < 3; linha++) {

			for (int coluna = 0; coluna < 3; coluna++) {
				System.out.print(matriz[linha][coluna]);
				
			}
			System.out.println();
			
		}
		// tela perguntando se deseja jogar novamente
		if (JogarDnv) {
			int jogarNovamente = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?");
			if (jogarNovamente == JOptionPane.OK_OPTION) {
				JogarDnv = false;
				Reiniciar();
			} else {
				System.exit(1);
			}
		}

		g.setStroke(new BasicStroke(18));
		g.setFont(minhaFont);
		g.setColor(cor2);
		g.fillRect(0, 0, 600, 600);

		g.setColor(Color.black);
		g.drawLine(0, 200, 600, 200);
		g.drawLine(0, 400, 600, 400);

		g.drawLine(200, 0, 200, 600);
		g.drawLine(400, 0, 400, 600);

		for (int linha = 0; linha < 3; linha++) {

			for (int coluna = 0; coluna < 3; coluna++) {

				if (matriz[linha][coluna] == 1) {
					g.setColor(cor4);
					g.drawString("x", 85 + coluna * 200, 125 + linha * 200);
				} else if (matriz[linha][coluna] == 2) {
					g.setColor(cor3);
					g.drawString("o", 85 + coluna * 200, 125 + linha * 200);
				}

			}

		}

		if (ganhador != 0) {
			g.setFont(minhaFont2);
			if (ganhador == 3) {
				g.setColor(Color.white);
				g.drawString("Empate", 250, 180);
			} else {
				if (ganhador == 1)
					g.setColor(cor4);
				else if (ganhador == 2)
					g.setColor(cor3);

				g.setFont(minhaFont2);
				g.drawString("O jogador " + ganhador + " venceu", 150, 180);
			}
			JogarDnv = true;
			repaint();

		}
		g.setFont(minhaFont3);

		g.setColor(cor4);
		g.drawString("Vitórias: " + Vitoria1, 40, 25);

		g.setColor(cor3);
		g.drawString("Vitórias: " + Vitoria2, 440, 25);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int linha = e.getY() / 220;
		int coluna = e.getX() / 220;
		if (linha == 3) {
			linha = 2;
		}
		if (coluna == 3) {
			coluna = 2;
		}
		System.out.println("");
		System.out.println("O jogador: " +jogador +" clicou na linha: " + linha + " e Coluna: "+ coluna);
		

		if (jogador == 1 && matriz[linha][coluna] == 0) {
			matriz[linha][coluna] = 1;
			jogador = 2;
		} else if (jogador == 2 && matriz[linha][coluna] == 0) {
			matriz[linha][coluna] = 2;
			jogador = 1;

		}

		Ganhador();

		repaint();

	}

	// reiniciar o jogo
	private void Reiniciar() {

		for (int linha = 0; linha < 3; linha++) {

			for (int coluna = 0; coluna < 3; coluna++) {
				matriz[linha][coluna] = 0;
				ganhador = 0;

			}
		}
	}

	// verificacao do ganhador
	private void Ganhador() {
		// verificacao das linhas
		for (int linha = 0; linha < 3; linha++) {
			if (matriz[linha][0] == matriz[linha][1] && matriz[linha][0] == matriz[linha][2] && matriz[linha][0] != 0) {
				System.out.println("Teve um ganhador");
				ganhador = matriz[linha][0];
				break;

			}
		}
		// verificacao das colunas
		for (int coluna = 0; coluna < 3; coluna++) {
			if (matriz[0][coluna] == matriz[1][coluna] && matriz[0][coluna] == matriz[2][coluna]
					&& matriz[0][coluna] != 0) {
				System.out.println("Teve um ganhador");
				ganhador = matriz[0][coluna];
				break;

			}

		}
		// verificacao da primeira diagonal
		if (matriz[0][0] == matriz[1][1] && matriz[0][0] == matriz[2][2] && matriz[0][0] != 0) {
			System.out.println("Teve um ganhador");
			ganhador = matriz[0][0];
		}
		// verificacao da segunda diagonal
		if (matriz[0][2] == matriz[1][1] && matriz[0][2] == matriz[2][0] && matriz[0][2] != 0) {
			System.out.println("Teve um ganhador");
			ganhador = matriz[0][2];
		}

		if (ganhador == 1) {
			Vitoria1++;

		} else if (ganhador == 2) {
			Vitoria2++;
		} else {
			boolean cheia = true;
			for (int linha = 0; linha < 3; linha++) {

				for (int coluna = 0; coluna < 3; coluna++) {
					if (matriz[linha][coluna] == 0)
						cheia = false;

				}
			}
			if (cheia)
				ganhador = 3;

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
