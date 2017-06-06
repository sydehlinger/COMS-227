package mini3;

import java.util.ArrayList;
import java.util.Random;

public class MyPlayer implements Player {
	
	private String name;
	private Random rand;
	private int otherIndex;
	
	public MyPlayer(){
		name = "You";
	}
	
	public MyPlayer(Random givenRand){
		name = "You";
		rand = givenRand;
	}

	@Override
	public Move play(TicTacToeGame g){
		char[][] board = g.getBoard();
		int index = g.getTurn();
		if(index == 0){
			otherIndex = 1;
		}else{
			otherIndex = 0;
		}
		// to win
		for(int i = 0; i < 3; i += 1){
			if(board[i][0] == TicTacToeGame.MARKER[index] && board[i][2] == TicTacToeGame.MARKER[index]){
				Move m = new Move(i,1);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[i][0] == TicTacToeGame.MARKER[index] && board[i][1] == TicTacToeGame.MARKER[index]){
				Move m = new Move(i,2);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[i][1] == TicTacToeGame.MARKER[index] && board[i][2] == TicTacToeGame.MARKER[index]){
				Move m = new Move(i,0);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][i] == TicTacToeGame.MARKER[index] && board[2][i] == TicTacToeGame.MARKER[index]){
				Move m = new Move(1,i);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][i] == TicTacToeGame.MARKER[index] && board[1][i] == TicTacToeGame.MARKER[index]){
				Move m = new Move(2,i);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[1][i] == TicTacToeGame.MARKER[index] && board[2][i] == TicTacToeGame.MARKER[index]){
				Move m = new Move(0,i);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][0] == TicTacToeGame.MARKER[index] && board[2][2] == TicTacToeGame.MARKER[index] || board[0][2] == TicTacToeGame.MARKER[index] && board[2][0] == TicTacToeGame.MARKER[index]){
				Move m = new Move(1,1);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][0] == TicTacToeGame.MARKER[index] && board[1][1] == TicTacToeGame.MARKER[index]){
				Move m = new Move(2,2);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][2] == TicTacToeGame.MARKER[index] && board[1][1] == TicTacToeGame.MARKER[index]){
				Move m = new Move(0,2);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[1][1] == TicTacToeGame.MARKER[index] && board[2][2] == TicTacToeGame.MARKER[index]){
				Move m = new Move(0,0);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[2][0] == TicTacToeGame.MARKER[index] && board[1][1] == TicTacToeGame.MARKER[index]){
				Move m = new Move(0,2);	
				if(g.isLegal(m)){
					return m;
				}
			}
		}
		// to block
		for(int j = 0; j < 3; j += 1){
			if(board[j][0] == TicTacToeGame.MARKER[otherIndex] && board[j][2] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(j,1);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[j][0] == TicTacToeGame.MARKER[otherIndex] && board[j][1] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(j,2);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[j][1] == TicTacToeGame.MARKER[otherIndex] && board[j][2] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(j,0);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][j] == TicTacToeGame.MARKER[otherIndex] && board[2][j] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(1,j);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][j] == TicTacToeGame.MARKER[otherIndex] && board[1][j] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(2,j);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[1][j] == TicTacToeGame.MARKER[otherIndex] && board[2][j] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(0,j);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][0] == TicTacToeGame.MARKER[otherIndex] && board[2][2] == TicTacToeGame.MARKER[otherIndex] || board[0][2] == TicTacToeGame.MARKER[otherIndex] && board[2][0] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(1,1);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][0] == TicTacToeGame.MARKER[otherIndex] && board[1][1] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(2,2);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[0][2] == TicTacToeGame.MARKER[otherIndex] && board[1][1] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(0,2);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[1][1] == TicTacToeGame.MARKER[otherIndex] && board[2][2] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(0,0);	
				if(g.isLegal(m)){
					return m;
				}
			}else if(board[2][0] == TicTacToeGame.MARKER[otherIndex] && board[1][1] == TicTacToeGame.MARKER[otherIndex]){
				Move m = new Move(0,2);	
				if(g.isLegal(m)){
					return m;
				}
			}
		}
		if(g.isLegal(new Move(1,1))){
			return new Move(1,1);
		}else{
			ArrayList<Move> legal = g.getAllLegalMoves();
			return legal.get(0);
		}
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public void gameOver(TicTacToeGame g){
			g.printCurrentStatus();
			g.getWinner();
	}

}
