import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java implementation of the TicTacToe game, using the Swing framework.
 *
 * This quick-and-dirty implementation violates a number of software engineering
 * principles and needs a thorough overhaul to improve readability,
 * extensibility, and testability.
 */
public class TicTacToe {
    public JFrame gui = new JFrame("Tic Tac Toe");
    public JButton[][] blocks = new JButton[3][3];
    public JButton reset = new JButton("Reset");
    public JTextArea playerturn= new JTextArea();
    public int player = 1;
    public int movesLeft = 9;
    private class resetButtonHandler implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
            resetGame();
            }
    	}
    private class cellButtonHandler implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		Judge(e);
    		}
    	}

    /**
     * The main method that starts the game.
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.gui.setVisible(true);
    }

    /**
     * The default constructor, which initializes the GUI.
     */
    public TicTacToe() {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(3,3));
        gamePanel.add(game, BorderLayout.CENTER);

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerturn);
        playerturn.setText("Player 1 to play 'X'");

        reset.addActionListener(new resetButtonHandler());

        // Initialize a JButton for each cell of the 3x3 game board.
        for(int row = 0; row<3 ;row++) {
            for(int column = 0; column<3 ;column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75,75));
                blocks[row][column].setText("");
                game.add(blocks[row][column]);
                blocks[row][column].addActionListener(new cellButtonHandler());
            }
        }
    }

    /**
     * Reset the game and game board.
     */
    public void resetGame() {
        for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                blocks[row][column].setText("");
                blocks[row][column].setEnabled(true);
            }
        }
        player = 1;
        movesLeft = 9;
        playerturn.setText("Player 1 to play 'X'");
        
    }
    
//Judge function for each button
    public void Judge(ActionEvent e) {
        movesLeft--;
        if(movesLeft%2 == 1) {
            playerturn.setText("'X': Player 1");
        } else{
            playerturn.setText("'O': Player 2");
        }

        if(player==1) {
            // Check whether player 1 won
        	if(e.getSource()==blocks[0][0]) {
            	vertexJudge(0, 0, player, movesLeft);
            } else if(e.getSource()==blocks[0][1]) {
            	restJudge(0, 1, player, movesLeft);
            } else if(e.getSource()==blocks[0][2]) {
            	vertexJudge(0, 2, player, movesLeft);
            } else if(e.getSource()==blocks[1][0]) {
            	restJudge(1, 0, player, movesLeft);
            } else if(e.getSource()==blocks[1][1]) {
            	centerJudge(player, movesLeft);
            } else if(e.getSource()==blocks[1][2]) {
            	restJudge(1, 2, player, movesLeft);
            } else if(e.getSource()==blocks[2][0]) {
            	vertexJudge(2, 0, player, movesLeft);
            } else if(e.getSource()==blocks[2][1]) {
            	restJudge(2, 1, player, movesLeft);
            } else if(e.getSource()==blocks[2][2]) {
            	vertexJudge(2, 2, player, movesLeft);
            }
        	player = 2;
        } else {
            // Check whether player 2 won
            if(e.getSource()==blocks[0][0]) {
            	vertexJudge(0, 0, player, movesLeft);
            } else if(e.getSource()==blocks[0][1]) {
            	restJudge(0, 1, player, movesLeft);
            } else if(e.getSource()==blocks[0][2]) {
            	vertexJudge(0, 2, player, movesLeft);
            } else if(e.getSource()==blocks[1][0]) {
            	restJudge(1, 0, player, movesLeft);
            } else if(e.getSource()==blocks[1][1]) {
            	centerJudge(player, movesLeft);
            } else if(e.getSource()==blocks[1][2]) {
            	restJudge(1, 2, player, movesLeft);
            } else if(e.getSource()==blocks[2][0]) {
            	vertexJudge(2, 0, player, movesLeft);
            } else if(e.getSource()==blocks[2][1]) {
            	restJudge(2, 1, player, movesLeft);
            } else if(e.getSource()==blocks[2][2]) {
            	vertexJudge(2, 2, player, movesLeft);
            }
            player = 1;
        }
    }

    
//    check the vertex points
    public void vertexJudge(int m, int n, int player, int movesleft) {
    	String test = new String();
    	if(player == 1) {
    		test = "X";
    	}else {
  		  test = "O";
  		}
        blocks[m][n].setText(test);
        blocks[m][n].setEnabled(false);
        if(movesleft<7) {
        	if((blocks[m][0].getText().equals(blocks[m][1].getText()) &&
                    blocks[m][1].getText().equals(blocks[m][2].getText())) ||
                   (blocks[0][n].getText().equals(blocks[1][n].getText()) &&
                    blocks[1][n].getText().equals(blocks[2][n].getText())) ||
                   (blocks[m][n].getText().equals(blocks[1][1].getText()) &&
                    blocks[1][1].getText().equals(blocks[2-m][2-n].getText()))) {
                playerturn.setText("Player" + player +"wins!");
                for(int i = 0;i<3;i++) {
                    for(int j = 0;j<3;j++) {
                        blocks[i][j].setEnabled(false);
                    }
                }
            } else if(movesleft==0) {
                playerturn.setText("Game ends in a draw");
            }
        } 
//     	player = 3 - player;
    }
    
//  check the center point
  public void centerJudge(int player, int movesleft) {
	  String test = new String();
	  if(player == 1) {
		  test = "X";
	  }else {
		  test = "O";
	  }
      blocks[1][1].setText(test);
      blocks[1][1].setEnabled(false);
      if(movesleft<7) {
    	  if((blocks[1][1].getText().equals(blocks[1][0].getText()) &&
                  blocks[1][0].getText().equals(blocks[1][2].getText())) ||
                 (blocks[1][1].getText().equals(blocks[0][1].getText()) &&
                  blocks[0][1].getText().equals(blocks[2][1].getText())) ||
                 (blocks[1][1].getText().equals(blocks[0][0].getText()) &&
                  blocks[0][0].getText().equals(blocks[2][2].getText())) ||
                 (blocks[1][1].getText().equals(blocks[0][2].getText()) &&
                  blocks[0][2].getText().equals(blocks[2][0].getText()))) {
    		  playerturn.setText("Player" + player +"wins!");
              for(int i = 0;i<3;i++) {
                  for(int j = 0;j<3;j++) {
                      blocks[i][j].setEnabled(false);
                  }
              }
          } else if(movesleft==0) {
              playerturn.setText("Game ends in a draw");
          }
      }
//	  player = 3 - player;
  }
  
//check the rest points
  public void restJudge(int m, int n, int player, int movesleft) {
		String test = new String();
		if(player == 1) {
			test = "X";
		}else {
			  test = "O";
		}
      blocks[m][n].setText(test);
      blocks[m][n].setEnabled(false);
      if(movesleft<7) {
    	  if((blocks[m][0].getText().equals(blocks[m][1].getText()) &&
	             blocks[m][1].getText().equals(blocks[m][2].getText())) ||
	            (blocks[0][n].getText().equals(blocks[1][n].getText()) &&
	             blocks[1][n].getText().equals(blocks[2][n].getText()))) {
	          playerturn.setText("Player" + player +"wins!");
	          for(int i = 0;i<3;i++) {
	              for(int j = 0;j<3;j++) {
	                  blocks[i][j].setEnabled(false);
	              }
	          }
	      } else if(movesleft==0) {
	          playerturn.setText("Game ends in a draw");
	      }
      }
// 	 player = 3 - player;
 	}
}
