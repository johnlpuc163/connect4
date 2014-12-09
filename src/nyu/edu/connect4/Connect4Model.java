package nyu.edu.connect4;

import java.util.LinkedList;
import java.util.List;

public class Connect4Model {
  public static final int ROW = 6;
  public static final int COLUMN = 7;
  private BoardScoreCalculator calculator = 
      BoardScoreCalculator.getCalculator();
  private List<Connect4Listener> listeners = 
      new LinkedList<Connect4Listener>();
  private Player player1;
  private Player player2;
  // game state
  private int[][] board = new int[ROW][COLUMN];
  private int[] chipsInColumn = new int[COLUMN];
  private Player nextPlayer;
  private Player idlePlayer;
  private boolean isGameOver = false;

  Chip setPlayer1(Connect4Listener listener){
    player1 = new Player.Builder().listener(listener).
        chip(ChipFactory.getChip(Chip.Color.YELLOW)).build();
    nextPlayer = player1;
    return player1.chip;
  }

  Chip setPlayer2(Connect4Listener listener){
    player2 = new Player.Builder().listener(listener).
            chip(ChipFactory.getChip(Chip.Color.RED)).build();
    idlePlayer = player2;
    return player2.chip;
  }

  public void addListener(Connect4Listener listener){
    listeners.add(listener);
  }

  public void placeChip(int column, Connect4Listener listener){
    if(isGameOver || listener != nextPlayer.listener){
      return;
    }
    // ignore if column is full
    if( chipsInColumn[column] >= ROW ){
      fireTurnToPlayEvent(nextPlayer);
      return;
    }
    // place chip
    int row = placeOnBoard(column, nextPlayer);
    firePlaceChipEvent(row, column, nextPlayer.chip);
    // check if has winner
    if(hasWinner(row, column)){
      fireWinnerEvent(nextPlayer.chip);
      return;
    }
    // check if draw
    if(isDraw()){
      fireDrawEvent();
      return;
    }
    // switch player
    switchPlaer();
  }

  public void startGame(){
    initializeState();
    fireGameStartedEvent();
    fireTurnToPlayEvent(nextPlayer);
  }
  
  private void switchPlaer(){
    Player tempPlayer = nextPlayer;
    nextPlayer = idlePlayer;
    idlePlayer = tempPlayer;
    fireTurnToPlayEvent(nextPlayer);
  }

  private void initializeState() {
    board = new int[ROW][COLUMN];
    chipsInColumn = new int[COLUMN];
    isGameOver = false;
    nextPlayer = player1;
    idlePlayer = player2;
  }

  private void firePlaceChipEvent(int row, int column, Chip chip){
    for(Connect4Listener listener : listeners){
      listener.placeChip(row, column, chip);
    }
  }

  private void fireWinnerEvent(Chip chip){
    for(Connect4Listener listener : listeners){
      listener.winner(chip);
    }
  }

  private void fireDrawEvent(){
    for(Connect4Listener listener : listeners){
      listener.draw();
    }
  }

  private void fireGameStartedEvent(){
    for(Connect4Listener listener : listeners){
      listener.gameStarted();
    }
  }

  private void fireTurnToPlayEvent(Player player){
    for(Connect4Listener listener : listeners){
      listener.turnToPlay(player);
    }
  }

  private int placeOnBoard(int column, Player player){
    int row = chipsInColumn[column];
    chipsInColumn[column]++;
    board[row][column] = player == player1 ? 1 : 2;
    return row;
  }

  private boolean hasWinner(int row, int column){
    isGameOver = calculator.hasWinner(board, row, column);
    return isGameOver;
  }

  private boolean isDraw(){
    for(int col = 0; col < COLUMN; col++){
      if(chipsInColumn[col] != ROW){
        return false;
      }
    }
    isGameOver = true;
    return true;
  }
  
  
  
}
