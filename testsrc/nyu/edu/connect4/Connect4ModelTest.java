package nyu.edu.connect4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Connect4ModelTest {
  
  private Connect4Model model;
  private TestListener listener1;
  private TestListener listener2;
  
  @Before
  public void createModel(){
    model = new Connect4Model();
    listener1 = new TestListener(model);
    model.setPlayer1(listener1);
    listener2 = new TestListener(model);
    model.setPlayer2(listener2);
  }

  @Test
  public void testPlaceChip(){
    model.startGame();
    model.placeChip(4, listener1);
    assertEquals("Chip placed at column 4", 4, listener1.column);
    assertEquals("Chip placed at row 0", 0, listener1.row);
    assertEquals("Should be yellow chip", Chip.Color.YELLOW, 
        listener1.chip.getColor());
    assertEquals("Chip placed at column 4", 4, listener2.column);
    assertEquals("Chip placed at row 0", 0, listener2.row);
    assertEquals("Should be yellow chip", Chip.Color.YELLOW, 
        listener2.chip.getColor());
  }
  
  @Test
  public void testPlaceChip_hasWinner(){
    model.startGame();
    model.placeChip(4, listener1);
    model.placeChip(3, listener2);
    model.placeChip(4, listener1);
    model.placeChip(3, listener2);
    model.placeChip(4, listener1);
    model.placeChip(3, listener2);
    model.placeChip(4, listener1);
    assertEquals("Should have winner", true, listener1.hasWinner);
    assertEquals("Should have winner", true, listener2.hasWinner);
  }
  
  @Test
  public void testPlaceChip_isDraw(){
    model.startGame();
    for(int column = 0; column < 3; column ++){
      for(int row = 0; row < 3; row ++){
        model.placeChip(column, listener1);
        model.placeChip(column, listener2);
      }
    }
    for(int column = 4; column < 7; column ++){
      for(int row = 0; row < 3; row ++){
        model.placeChip(column, listener1);
        if( row != 2 || column != 6){
          model.placeChip(column, listener2);        
        }
      }
    }
    for(int row = 0; row < 3; row ++){
      model.placeChip(3, listener2);
      model.placeChip(3, listener1);
    }
    model.placeChip(6, listener2);
    assertEquals("Should be draw", true, listener1.isDraw);
    assertEquals("Should be draw", true, listener2.isDraw);
  }
  
  @Test
  public void testStartGame() {
    model.startGame();
    assertEquals("Game should start", true, listener1.isGameStarted);
    assertEquals("Game should start", true, listener2.isGameStarted);
    assertEquals("Listener 1's turn", true, listener1.isMyTurn);
    assertEquals("Not Listener 2's turn", false, listener2.isMyTurn);
  }
  
class TestListener implements Connect4Listener{
    
    public boolean isGameStarted = false;
    public boolean isMyTurn = false;
    public boolean isDraw = false;
    public boolean hasWinner = false;
    public int row = -1;
    public int column = -1;
    public Chip chip = null;
    
    public TestListener(Connect4Model model){
      model.addListener(this);
    }
    
    @Override
    public void placeChip(int row, int column, Chip chip) {
      // TODO Auto-generated method stub
      this.row = row;
      this.column = column;
      this.chip = chip;
    }

    @Override
    public void draw() {
      // TODO Auto-generated method stub
      isDraw = true;
    }

    @Override
    public void winner(Chip chip) {
      // TODO Auto-generated method stub
      hasWinner = true;
    }

    @Override
    public void gameStarted() {
      // TODO Auto-generated method stub
      isGameStarted = true;
    }

    @Override
    public void turnToPlay(Player player) {
      // TODO Auto-generated method stub
      if(player.listener == this){
        isMyTurn = true;
      }
    }
    
  }

}
