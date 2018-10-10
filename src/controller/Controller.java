package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import view.View;
import model.Model;
import java.util.Scanner;

public class Controller{
	private Model model;
	private View view;
	private boolean terminalControl;
	public Controller(Model model, View view, boolean terminalControl) {
		this.model = model;
		this.view = view;
		this.terminalControl = terminalControl;
		initController(this.terminalControl);
	}
	
	
	public boolean getTerminalControl() {
		return terminalControl;
	}

	public void setTerminalControl(boolean terminalControl) {
		this.terminalControl = terminalControl;
	}
	private class resetButtonHandler implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
            resetGame();
            }
    	}
	
    private class cellButtonHandler implements ActionListener{
    	public boolean terminalControl;
    	public void actionPerformed(ActionEvent e){
//    		terminalControl("X", 1);
    		Judge(e);
    		}
    	} 
    public void initView() {
    	view.getPlayerturn().setText("Player 1 to play '" + model.getLabel1() + "'");
    }
    
    public void initController(boolean terminalControl) {
    	view.getReset().addActionListener(new resetButtonHandler());
    	for(int row = 0; row<3 ;row++) {
            for(int column = 0; column<3 ;column++) {
                view.getBlocks()[row][column] = new JButton();
                view.getBlocks()[row][column].setPreferredSize(new Dimension(75,75));
                view.getBlocks()[row][column].setText("");
                view.getGame().add(view.getBlocks()[row][column]);
                view.getBlocks()[row][column].addActionListener(new cellButtonHandler());
            }
        }
    	view.getGui().setVisible(true);
    	if(terminalControl) {
    		terminalControl(model.getLabel1(), 1, view.getBlocks());
    		for(;;) {
    	        if(view.getMovesLeft()%2 == 1) {
    	        	terminalControl(model.getLabel1(), 1, view.getBlocks());
    	        } else{
    	        	terminalControl(model.getLabel2(), 2, view.getBlocks());
    	        }
        	}
    	}
    }
    
    public void resetGame() {
        for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                view.getBlocks()[row][column].setText("");
                view.getBlocks()[row][column].setEnabled(true);
            }
        }
        view.setPlayer(1);
        view.setMovesLeft(9);
        view.getPlayerturn().setText("Player 1 to play '" + model.getLabel1() + "'");
        
    }
    
//Judge function for each button
    public void Judge(ActionEvent e) {
        view.setMovesLeft(view.getMovesLeft()-1);
        if(view.getMovesLeft()%2 == 1) {
            view.getPlayerturn().setText("'" + model.getLabel1() + "': Player 1");
        } else{
        	view.getPlayerturn().setText("'" + model.getLabel2() + "': Player 2");
        }

        if(view.getPlayer()==1) {
            // Check whether player 1 won
        	if(e.getSource()==view.getBlocks()[0][0]) {
            	vertexJudge(0, 0, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[0][1]) {
            	restJudge(0, 1, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[0][2]) {
            	vertexJudge(0, 2, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[1][0]) {
            	restJudge(1, 0, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[1][1]) {
            	centerJudge(view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[1][2]) {
            	restJudge(1, 2, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[2][0]) {
            	vertexJudge(2, 0, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[2][1]) {
            	restJudge(2, 1, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[2][2]) {
            	vertexJudge(2, 2, view.getPlayer(), view.getMovesLeft());
            }
        	view.setPlayer(2);
        } else {
            // Check whether player 2 won
            if(e.getSource()==view.getBlocks()[0][0]) {
            	vertexJudge(0, 0, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[0][1]) {
            	restJudge(0, 1, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[0][2]) {
            	vertexJudge(0, 2, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[1][0]) {
            	restJudge(1, 0, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[1][1]) {
            	centerJudge(view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[1][2]) {
            	restJudge(1, 2, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[2][0]) {
            	vertexJudge(2, 0, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[2][1]) {
            	restJudge(2, 1, view.getPlayer(), view.getMovesLeft());
            } else if(e.getSource()==view.getBlocks()[2][2]) {
            	vertexJudge(2, 2, view.getPlayer(), view.getMovesLeft());
            }
            view.setPlayer(1);
        }
    }
//  check the vertex points
    public void vertexJudge(int m, int n, int player, int movesleft) {
		  String test = new String();
		  if(player == 1) {
				test = model.getLabel1();
			}else {
				  test = model.getLabel2();
			}
		  view.getBlocks()[m][n].setText(test);
	      view.getBlocks()[m][n].setEnabled(false);
	      if(movesleft<7) {
		      	if((view.getBlocks()[m][0].getText().equals(view.getBlocks()[m][1].getText()) &&
		                  view.getBlocks()[m][1].getText().equals(view.getBlocks()[m][2].getText())) ||
		                 (view.getBlocks()[0][n].getText().equals(view.getBlocks()[1][n].getText()) &&
		                  view.getBlocks()[1][n].getText().equals(view.getBlocks()[2][n].getText())) ||
		                 (view.getBlocks()[m][n].getText().equals(view.getBlocks()[1][1].getText()) &&
		                  view.getBlocks()[1][1].getText().equals(view.getBlocks()[2-m][2-n].getText()))) {
		      		view.getPlayerturn().setText("Player " + player +" wins!");
		  		for(int i = 0;i<3;i++) {
		          for(int j = 0;j<3;j++) {
		              view.getBlocks()[i][j].setEnabled(false);
		          }
		      }
		  }else if(movesleft==0) {
		      view.getPlayerturn().setText("Game ends in a draw");
		  }
	   } 
	//   	player = 3 - player;
	}
  
//check the center point
	public void centerJudge(int player, int movesleft) {
		String test = new String();
		if(player == 1) {
			test = model.getLabel1();
		}else {
			  test = model.getLabel2();
		}
	    view.getBlocks()[1][1].setText(test);
	    view.getBlocks()[1][1].setEnabled(false);
	    if(movesleft<7) {
	  	  if((view.getBlocks()[1][1].getText().equals(view.getBlocks()[1][0].getText()) &&
	                view.getBlocks()[1][0].getText().equals(view.getBlocks()[1][2].getText())) ||
	               (view.getBlocks()[1][1].getText().equals(view.getBlocks()[0][1].getText()) &&
	                view.getBlocks()[0][1].getText().equals(view.getBlocks()[2][1].getText())) ||
	               (view.getBlocks()[1][1].getText().equals(view.getBlocks()[0][0].getText()) &&
	                view.getBlocks()[0][0].getText().equals(view.getBlocks()[2][2].getText())) ||
	               (view.getBlocks()[1][1].getText().equals(view.getBlocks()[0][2].getText()) &&
	                view.getBlocks()[0][2].getText().equals(view.getBlocks()[2][0].getText()))) {
	  		  view.getPlayerturn().setText("Player " + player +" wins!");
	            for(int i = 0;i<3;i++) {
	                for(int j = 0;j<3;j++) {
	                    view.getBlocks()[i][j].setEnabled(false);
	                }
	            }
	        } else if(movesleft==0) {
	            view.getPlayerturn().setText("Game ends in a draw");
	        }
	    }
	//	  player = 3 - player;
	}

//check the rest points
	public void restJudge(int m, int n, int player, int movesleft) {
		String test = new String();
		if(player == 1) {
			test = model.getLabel1();
		}else {
			  test = model.getLabel2();
		}
	  view.getBlocks()[m][n].setText(test);
	  view.getBlocks()[m][n].setEnabled(false);
      if(movesleft<7) {
    	  if((view.getBlocks()[m][0].getText().equals(view.getBlocks()[m][1].getText()) &&
	             view.getBlocks()[m][1].getText().equals(view.getBlocks()[m][2].getText())) ||
	            (view.getBlocks()[0][n].getText().equals(view.getBlocks()[1][n].getText()) &&
	             view.getBlocks()[1][n].getText().equals(view.getBlocks()[2][n].getText()))) {
	          view.getPlayerturn().setText("Player " + player +" wins!");
	          for(int i = 0;i<3;i++) {
	              for(int j = 0;j<3;j++) {
	                  view.getBlocks()[i][j].setEnabled(false);
	              }
	          }
	      } else if(movesleft==0) {
	          view.getPlayerturn().setText("Game ends in a draw");
	      }
       }
	//	 player = 3 - player;
	}
	public void terminalControl(String label, int player, JButton[][] button){
		Scanner scanner = new Scanner(System.in);
	    System.out.printf("Player %d plays %s: Insert row and column index (between 0 and 2) to play: \n", player, label);
	    System.out.printf("Column index: ");
	    int column = Integer.parseInt(scanner.next());
	    System.out.printf("Row index: ");
	    int row = Integer.parseInt(scanner.next());
//	    System.out.println(column);
//	    System.out.println(row);
	    button[row][column].doClick();	    
	}
}
