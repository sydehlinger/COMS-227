package hw2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import api.Direction;
import api.Move;
import api.Result;
import api.TilePosition;

/**
 * The Game class contains the state and logic for an implementation of the
 * game 2048.  The basic underlying state is an n by n grid of tiles, 
 * represented by integer values.  A zero in a cell is considered to be
 * "empty", and all other cells contain some power of two.  The game is played
 * by calling the method <code>collapse()</code>, selecting one of the four
 * directions (LEFT, RIGHT, UP, DOWN). Each row or column is then collapsed
 * according to the algorithm described in the methods of <code>GameUtil</code>.
 * <p>
 * Whenever two cells are <em>merged</em>, the score is increased by the 
 * combined value of the two cells.
 *@author Sydney Ehlinger
 */
public class Game{

  /**
   * 2d array of the grid
   */
  private int[][] grid;
	
  /**
   * Size of the grid
   */
  private int size;
  
  /**
   * Game's instance of random
   */
  private Random rand;
  
  /**
   * Score of game
   */
  private int score;
  
  /**
   * Constructs a game with a grid of the given size, using a default
   * random number generator.  Initially there should be two
   * nonempty cells in the grid selected by the method <code>generate()</code>.
   * @param givenSize
   *   size of the grid for this game
   */
  public Game(int givenSize){
    size = givenSize;
    grid = new int[size][size];
    rand = new Random();
	for (int i = 0; i < grid.length; i += 1) {
		for (int j = 0; j < grid.length; j += 1) {
			setCell(j, j, grid[i][j]);
		}
	}
    generate();
    generate();
  }
  
  /**
   * Constructs a game with a grid of the given size, using the given
   * instance of <code>Random</code> for the random number generator.
   * Initially there should be two nonempty cells in the grid selected 
   * by the method <code>generate()</code>.
   * @param givenSize
   *   size of the grid for this game
   * @param givenRandom
   *   given instance of Random
   */
  public Game(int givenSize, Random givenRandom){
    size = givenSize;
    grid = new int[size][size];
    rand = givenRandom;
	for (int i = 0; i < grid.length; i += 1) {
		for (int j = 0; j < grid.length; j += 1) {
			setCell(i, j, grid[i][j]);
		}
	}
    generate();
    generate();
    }
  
   /**
   * Returns the value in the cell at the given row and column.
   * @param row
   *   given row
   * @param col
   *   given column
   * @return
   *   value in the cell at the given row and column
   */
  public int getCell(int row, int col){
    return grid[row][col];

  }
  
  /**
   * Sets the value of the cell at the given row and column. NOTE: This method should not be used by clients outside of a testing environment
   * @param row
   * 	given row
   * @param col
   * 	given column
   * @param value
   * 	value to be set
   */
  public void setCell(int row, int col, int value){
	  grid[row][col] = value;
  }
  
  /**
   * Returns the size of this game's grid.
   * @return
   *   size of the grid
   */
  public int getSize(){
    return size;
  }
  
  /**
   * Returns the current score.
   * @return
   *   score for this game
   */
  public int getScore(){
	return score;
  }

  /**
   * Copy a row or column from the grid into a new one-dimensional array.  
   * There are four possible actions depending on the given direction:
   * <ul>
   *   <li>LEFT - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array from left to right
   *   <li>RIGHT - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array in reverse (from right to left)
   *   <li>UP - the column indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array from top to bottom
   *   <li>DOWN - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array in reverse (from bottom to top)
   * </ul>
   * @param rowOrColumn
   *   index of the row or column
   * @param dir
   *   direction from which to begin copying
   * @return
   *   array containing the row or column
   */
  public int[] copyRowOrColumn(int rowOrColumn, Direction dir){
	if(dir == Direction.LEFT){
		//gets the specific row
		int[] arrayRow = grid[rowOrColumn];
		return arrayRow;
    }else if(dir == Direction.RIGHT){
    	int i = 0;
    	//gets the specific row
    	int[] arrayRow = grid[rowOrColumn];
    	//creates a empty array with the same length of the row
    	int[] arrayCopy = new int[arrayRow.length];
    	//copies the arrayRow backwards into the arrayCopy
    	for(int row = arrayRow.length - 1; row >= 0; row -= 1){
        		arrayCopy[i] = arrayRow[row];
        		i += 1;
    	}
    	return arrayCopy;
    }else if(dir == Direction.UP){
    	//creates an empty array with the same length of the grid
    	int[] arrayCol = new int[grid.length];
    	//copies the value at the same index in each row to produce an array for the column
    	for(int i = 0; i < grid.length; i += 1){
    		arrayCol[i] = grid[i][rowOrColumn];
    	}
    	return arrayCol;
    }else{
    	//gets an array column like in Direction.UP
    	int j = 0;
    	int[] arrayCol = new int[grid.length];
    	for(int i = 0; i < grid.length; i += 1){
    		arrayCol[i] = grid[i][rowOrColumn];
    	}
    	//copies the array backwards like in Direction.RIGHT
    	int[] arrayCopy = new int[arrayCol.length];
    	for(int row = arrayCol.length - 1; row >= 0; row -= 1){
        		arrayCopy[j] = arrayCol[row];
        		j += 1;
    	}
    	return arrayCopy;
    }
  }
  
  
  // copy from given 1d array into the grid in the given direction at the given row or column index
  /**
   * Updates the grid by copying the given one-dimensional array into
   * a row or column of the grid.
   * There are four possible actions depending on the given direction:
   * <ul>
   *   <li>LEFT - the given array is copied into the the row indicated by the 
   *   index <code>rowOrColumn</code> from left to right
   *   <li>RIGHT - the given array is copied into the the row indicated by the 
   *   index <code>rowOrColumn</code> in reverse (from right to left)
   *   <li>UP - the given array is copied into the column indicated by the 
   *   index <code>rowOrColumn</code> from top to bottom
   *   <li>DOWN - the given array is copied into the column indicated by the 
   *   index <code>rowOrColumn</code> in reverse (from bottom to top)
   * </ul>
   * @param arr
   *   the array from which to copy
   * @param rowOrColumn
   *   index of the row or column
   * @param dir
   *   direction from which to begin copying
   */
  public void updateRowOrColumn(int[] arr, int rowOrColumn, Direction dir){
		if(dir == Direction.LEFT){
			//copies the row array into the grid
			grid[rowOrColumn] = arr;
	    }else if(dir == Direction.RIGHT){
	    	//copies the row array backwards into the grid
	    	int i = 0;
	    	int[] arrayCopy = new int[grid.length];
	    	for(int row = grid.length - 1; row >= 0; row -= 1){
	        		arrayCopy[i] = arr[row];
	        		i += 1;
	    	}
	    	grid[rowOrColumn] = arrayCopy;
	    }else if(dir == Direction.UP){
	    	//copies the column array into the grid
	    	for(int i = 0; i < grid.length; i += 1){
	    		grid[i][rowOrColumn] = arr[i];
	    	}
	    }else{
	    	//copies the column array backwards into the grid
	    	int j = 0;
	    	int[] arrayCol = new int[grid.length];
	    	for(int i = 0; i < grid.length; i += 1){
	    		arrayCol[i] = grid[i][rowOrColumn];
	    	}
	    	for(int col = grid.length - 1; col >= 0; col -= 1){
	    		grid[j][rowOrColumn] = arr[col];
        		j += 1;
    	}
	    }
  }

  /**
   * Plays one step of the game by collapsing the grid in the given direction.
   * Returns a <code>Result</code> object containing a list of Move objects describing
   * all moves performed, and a <code>TilePosition</code> object describing
   * the position and value of a newly added tile, if any.  All Move objects must
   * include a valid value for <code>getRowOrColumn()</code> and <code>getDirection()</code>.
   * If no move actually occurs, the returned <code>Result</code> object contains an 
   * empty list and has a null value for the new <code>TilePosition</code>.
   * <p>
   * If any tiles are moved or merged, a new tile is selected according to the 
   * <code>generate()</code> method and is added to the grid.
   * <p>
   * The collapse of an individual row or column must be performed by the 
   * method <code>collapseArray</code> of <code>GameUtil</code>.  
   * 
   * @param dir
   *   direction in which to collapse the grid
   * @return
   *   Result object containing moves and new tile position
   */
  public Result collapse(Direction dir){
	  //creates a new result
	 Result result = new Result();
	  for(int i = 0; i < grid.length; i += 1){
		  //creates temporary array of the specific row or column
		  int[] temp = copyRowOrColumn(i, dir);
		  //creates an ArrayList of moves of all the temporary collapsed arrays
		  ArrayList<Move> move = GameUtil.collapseArray(temp);
		  //iterates through the ArrayList 
		  for(int j = 0; j < move.size(); j += 1){
			  //checks if move at index j is merged
			  if(move.get(j).isMerged()){
				  //add the value times 2 at the index j of move
				  score += move.get(j).getValue() * 2; 
			  }
		  }
		  //sets the direction
		  for(Move m: move){
			  m.setDirection(i, dir);
		  }
		  //updates the row or column in the grid
		  updateRowOrColumn(temp, i, dir);
		  //adds the moves to the result
		  result.addMoves(move);
		}
	  	//generates a new value and sets it in the result
		result.setNewTile(generate());
		return result;
  }


  /**
   * Use this game's instance of <code>Random</code> to generate
   * a new tile.  The tile's row and column must be an empty cell
   * of the grid, and the tile's value is either 2 or 4. 
   * The tile is selected in such a way that
   * <ul>
   *   <li>All empty cells of the grid are equally likely
   *   <li>The tile's value is a 2 with 90% probability and a 4 with 10% probability
   * </ul>
   * This method does not modify the grid.  If the grid has no
   * empty cells, returns null.
   * @return
   *   a new TilePosition containing the row, column, and value of the
   *   selected new tile, or null if the grid has no empty cells
   */
  public TilePosition generate(){
	  int tileValue = 0;
	  int emptyCells = 0; 
		//iterates through the grid adding up all the cells that equal 0
	    for(int i = 0; i < size; i += 1){
	    	for(int j = 0; j < size; j += 1){
	    		if(grid[i][j] == 0){
	    			emptyCells += 1;
	    		}
	    	}
	    }
	    //checks if there are actually any empty cells
	    if(emptyCells != 0){
	    	//creates a variable for a random number between 1-10
	    	int prob = rand.nextInt(10) + 1;
	    	//if the value equals 10 set the value of the tile to 0, otherwise have it be 2 (allow it so 4 has a probability of 10% and 2 of 90%)
	    	if(prob == 10){
	    		tileValue = 4;
	    	}else{
	    		tileValue = 2;
	    	}
	    	//creates a random integer for the row and column
    		int row = rand.nextInt(grid.length);
    		int col = rand.nextInt(grid.length);
    		//checks if the grid is a 0 at the random instances of row and col
	    	while(grid[row][col] != 0){
	    		//if the grid is not open at the random instances, creates new random instances to try again
	    		row = rand.nextInt(grid.length);
	    		col = rand.nextInt(grid.length);
	    	}
	    	//checks if the grid equals 0 at the random instances
	    	if(grid[row][col] == 0){
	    		//create a tile with the random row and column and the tileValue found earlier in the method
	    		TilePosition tile = new TilePosition(row, col, tileValue);
	    		//sets the tile in the grid
	    		setCell(tile.getRow(), tile.getCol(), tile.getValue());
	    		return tile;	
	    	}
	    }
	//if there is no open cells, returns null 
	return null;
  }
}