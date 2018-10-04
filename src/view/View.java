package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controller.Controller;
import model.Model;

public class View {
	private JFrame gui;
    private JButton[][] blocks;
    private JButton reset;
    private JTextArea playerturn;
    private JPanel gamePanel;
    private JPanel game;
    private JPanel options;
    private JPanel messages;
    private int player;
    private int movesLeft;
	public View() {
		gui = new JFrame("Tic Tac Toe");
	    blocks = new JButton[3][3];
	    reset = new JButton("Reset");
	    playerturn= new JTextArea();
	    player = 1;
	    movesLeft = 9;
//	    Model model = new Model();
//	    Controller controller = new Controller(player, movesLeft, playerturn, blocks, model);
	    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        gamePanel = new JPanel(new FlowLayout());
        game = new JPanel(new GridLayout(3,3));
        gamePanel.add(game, BorderLayout.CENTER);

        options = new JPanel(new FlowLayout());
        options.add(reset);
        messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerturn);
//        gui.setVisible(true);
//        playerturn.setText("Player 1 to play 'X'");
	}
	
	public JFrame getGui() {
		return gui;
	}

	public void setGui(JFrame gui) {
		this.gui = gui;
	}

	public JButton[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(JButton[][] blocks) {
		this.blocks = blocks;
	}

	public JButton getReset() {
		return reset;
	}

	public void setReset(JButton reset) {
		this.reset = reset;
	}

	public JTextArea getPlayerturn() {
		return playerturn;
	}

	public void setPlayerturn(JTextArea playerturn) {
		this.playerturn = playerturn;
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JPanel getGame() {
		return game;
	}

	public void setGame(JPanel game) {
		this.game = game;
	}

	public JPanel getOptions() {
		return options;
	}

	public void setOptions(JPanel options) {
		this.options = options;
	}

	public JPanel getMessages() {
		return messages;
	}

	public void setMessages(JPanel messages) {
		this.messages = messages;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getMovesLeft() {
		return movesLeft;
	}

	public void setMovesLeft(int movesLeft) {
		this.movesLeft = movesLeft;
	}
}
