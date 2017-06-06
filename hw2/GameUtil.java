package hw2;

import java.util.ArrayList;

import api.Move;

/**
 * Utility class containing the basic logic for performing moves in a
 * game of 2048.  All methods operate on a one-dimensional array
 * of integers representing the tiles.  A cell with zero is referred to
 * as "empty" and a nonzero cell is "nonempty".  Tiles are only collapsed
 * to the left; that is, tiles that are moved or merged can only move to the 
 * left.  The Game class can use these methods to collapse a row or column
 * in any direction by copying that row or column, forward or backward,
 * into a temporary one-dimensional array.
 * @author Sydney Ehlinger
 */
public class GameUtil{
  /**
   * Returns the index of the first nonempty cell that is on or after the 
   * given index <code>start</code>, or -1 if there is none.
   * @param arr
   *   given array
   * @param start
   *   index at which to start looking 
   * @return
   *   index of the first nonempty cell, or -1 if none is found
   */

  public static int findNextNonemptyCell(int[] arr, int start){
	//iterates through the array
    for(int i = start; i < arr.length; i +=1 ){
    	//checks if there is a value at the index i in the array
	    if(arr[i] != 0){
	    	//returns the value if there the cell is not empty
	    	return i;
    	}
    }
    //returns -1 if the cell is empty
    return -1;
  }
  
  /**
   *  Given an array and a starting index, finds a move that
   *  would merge or move a tile to that index, if such a move 
   *  exists. This method does not modify the array.  The returned Move object
   *  will have unspecified row/column and direction.  If there is no move
   *  to the given index, returns null.  This method is not required to 
   *  examine cells to the left of <code>index</code>.  Note that in 
   *  case of a merge move, the value of the Move is the <em>current</em>
   *  value on the tile or tiles, not the new value that it would
   *  have after the merge takes place.
   *  
   *  The logic of this method can be described as follows:
   *  <pre>
   *  if cell at index is occupied (nonzero)
   *      find next occupied cell c to the right of 'index'
   *      if there is one and it is the same value
   *            create a move to merge c with cell at 'index'
   *  else
   *      find next occupied cell c to the right of 'index'
   *      if there is one
   *           find next occupied cell c2 to the right of c
   *           if there is one, and if they are the same value
   *                create a move to merge c and c2 into cell at index
   *           else
   *                create a move that just moves c to 'index'
   *  return the move
   *  
   *  </pre>
   * @param arr
   *   array in which to search for move
   * @param index
   *   index for destination of move
   * @return  
   *   Move object describing the move, or null if there is no move
   */
  public static Move findNextMove(int[] arr, int index){
	  //checks if value at index is 0
	  if(arr[index] != 0){
		  //finds the next nonempty cell if the value at index is not 0
		  int nonEmpty = findNextNonemptyCell(arr, index + 1);
		  //checks if the nonempty value exists and if the value at index and the value at nonempty equal each other 
		  if(nonEmpty != -1 && arr[index] == arr[nonEmpty]){
			  //creates move
			  Move move = new Move(index, nonEmpty, index, arr[index]);
			  return move;
		  //checks if the nonempty value exists and if the value at index and the value at nonempty do not equal each other 
		  }else if(nonEmpty != -1 && arr[index] != arr[nonEmpty]){
			  //finds next nonempty
			  int nonEmpty2 = findNextNonemptyCell(arr, nonEmpty + 1);
			  //checks if the nonempty value exists and if the value at nonempty and the value at the second nonempty do not equal each other 
			  if(nonEmpty2 != -1 && arr[nonEmpty] == arr[nonEmpty2]){
				  //creates move
				  Move move = new Move(nonEmpty, nonEmpty2, nonEmpty, arr[nonEmpty]);
				  return move;
			  //checks if the value at index + 1 equals 0
			  }else if(arr[index + 1] == 0){
				  //creates move
				  Move move = new Move(index + 1, nonEmpty, index + 1, arr[nonEmpty]);
				  return move;
			  }
		  }
	  //checks if value at index equals 0
	  }else if(arr[index] == 0){
		  //finds the next nonempty cell
		  int nonEmpty = findNextNonemptyCell(arr, index);
		  //checks if the nonempty value exists
		  if(nonEmpty != -1){
			  //finds next nonempty cell
			  int nonEmpty2 = findNextNonemptyCell(arr, nonEmpty + 1);
			  //checks if the second nonempty exists and if the the value at the first nonempty equals the value at the second nonempty 
			  if(nonEmpty2 != -1 && arr[nonEmpty] == arr[nonEmpty2]){
				  //creates move
				  Move move = new Move(nonEmpty, nonEmpty2, index, arr[nonEmpty]);
				  return move;
			  }else{
				  //creates single move
				  Move move = new Move(nonEmpty, index, arr[nonEmpty]);
				  return move;
			  }
		  }
	  }
	  //return null if there are no moves
	  return null;
  }

  /**
   * Updates the array according to the given Move.  This
   * method is not required to check whether the given Move describes
   * a move or merge that is correct in the given array.
   * @param arr
   *   given array to be modified
   * @param move
   *   the move to be applied to the array
   */
  public static void applyOneMove(int[] arr, Move move){
	  //gets the gets the indexes from the move
	  int oldIndex = move.getOldIndex();
	  int oldIndex2 = move.getOldIndex2();
	  int newIndex = move.getNewIndex();
	  //checks if oldIndex2 has a value or not
	  if(oldIndex2 != -1){
		  //adds the two old indexes together to get the value
		  int index = arr[oldIndex] + arr[oldIndex2];
		  //sets the old indexes to 0
		  arr[oldIndex] = 0;
		  arr[oldIndex2] = 0;
		  //sets the added old added indexes to the newIndex
		  arr[newIndex] = index;
	  }else{
		  //sets the oldIndex to the newIndex
		  arr[newIndex] = arr[oldIndex];
		  //puts a 0 in place of the oldIndex
		  arr[oldIndex] = 0;
	  }
  }
  
  /**
   * Collapses the array to the left by performing a sequence of moves, 
   * and returns a list of moves that were performed.  All of the 
   * returned Move objects will have unspecified row/column and direction.
   * <p>
   * The idea is to iterate over the array indices from left to right, 
   * finding a move to that index and (if one exists) applying it to the array.
   * Note that according to this logic, moves do not "cascade": once a cell is merged 
   * with another cell, the resulting cell is not merged again during this operation.  
   * For example, when this method is applied to the array [2, 2, 4], the end result 
   * is [4, 4, 0], not [8, 0, 0].
   * @param arr
   *   array to be collapsed
   * @return
   *   list of all moves performed in the collapse
   */
  public static ArrayList<Move> collapseArray(int[] arr){
	  //creates a new ArrayList
	  ArrayList<Move> list = new ArrayList<>();
	  //iterates through the array to find and apply moves
	  for(int i = 0; i < arr.length; i += 1){
		  //finds the next move in the array at the index i
		  Move move = findNextMove(arr, i);
		  //checks if the move is null or not
		  if(move !=  null){
			  //if the move is not null apply the move to the array
			  applyOneMove(arr, move);
			  //add the move to the ArrayList
			  list.add(move);
	      }else{ 
	    	  //add 1 to the index
	    	  i += 1;
	    	  //checks to see if the index is larger than the array
	    	  if(i > arr.length){
	    		  //if the index is larger than the array it returns null
	    		  return null;
	    	  }
	      }
	  }
	  return list;
  }
}
