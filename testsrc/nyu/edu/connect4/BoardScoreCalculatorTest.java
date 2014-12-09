package nyu.edu.connect4;

import static org.junit.Assert.*;
import nyu.edu.connect4.Connect4ModelTest.TestListener;

import org.junit.Before;
import org.junit.Test;

public class BoardScoreCalculatorTest {
  
  private BoardScoreCalculator calculator = 
      BoardScoreCalculator.getCalculator();
  int[][] board = new int[6][7];
  
  @Test
  public void testHasWinner_horizonal() {
    for(int i=0; i<4; i++){
      board[0][i] = 1;
    }
    assertEquals("Should have winner", true, 
        calculator.hasWinner(board, 0, 3));
  }
  
  @Test
  public void testHasWinner_vertical() {
    for(int i=0; i<4; i++){
      board[i][0] = 1;
    }
    assertEquals("Should have winner", true, 
        calculator.hasWinner(board, 0, 0));
  }
  
  @Test
  public void testHasWinner_backDiagonal() {
    for(int i=0; i<4; i++){
      board[i][4-i] = 1;
    }
    assertEquals("Should have winner", true, 
        calculator.hasWinner(board, 0, 4));
  }
  
  @Test
  public void testHasWinner_forwardDiagonal() {
    for(int i=0; i<4; i++){
      board[i][i] = 1;
    }
    assertEquals("Should have winner", true, 
        calculator.hasWinner(board, 0, 0));
  }
  
  @Test
  public void testHasWinner_none() {
    for(int i=0; i<3; i++){
      board[i][i] = 1;
    }
    assertEquals("Should have winner", false, 
        calculator.hasWinner(board, 0, 0));
  }
  
  @Test
  public void testTotalSocore_four_in_a_row(){
    int[][] board = {{1,1,1,1}};
    assertEquals("Should have winner", 10000, 
        calculator.totalScore(board, 0, 0));
  }
  
  @Test
  public void testTotalSocore_three_in_a_row(){
    int[][] board = {{1,1,1,0}};
    assertEquals("Should have winner", 100, 
        calculator.totalScore(board, 0, 0));
    int[][] board2 = {{0,1,1,1}};
    assertEquals("Should have winner", 100, 
        calculator.totalScore(board2, 0, 1));
  }
  
  @Test
  public void testTotalSocore_two_in_a_row(){
    int[][] board = {{1,1,0,0}};
    assertEquals("Should have winner", 10, 
        calculator.totalScore(board, 0, 0));
    int[][] board2 = {{0,1,1,0}};
    assertEquals("Should have winner", 10, 
        calculator.totalScore(board2, 0, 1));
    int[][] board3 = {{0,0,1,1}};
    assertEquals("Should have winner", 10, 
        calculator.totalScore(board3, 0, 2));
  }
  
  @Test
  public void testTotalSocore_one_in_a_row(){
    int[][] board = {{1,0,0,0}};
    assertEquals("Should have winner", 1, 
        calculator.totalScore(board, 0, 0));
    int[][] board2 = {{0,1,0,0}};
    assertEquals("Should have winner", 1, 
        calculator.totalScore(board2, 0, 1));
    int[][] board3 = {{0,0,1,0}};
    assertEquals("Should have winner", 1, 
        calculator.totalScore(board3, 0, 2));
    int[][] board4 = {{0,0,0,1}};
    assertEquals("Should have winner", 1, 
        calculator.totalScore(board4, 0, 3));
  }

}
