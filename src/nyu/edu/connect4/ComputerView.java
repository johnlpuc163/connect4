package nyu.edu.connect4;

class ComputerView implements Connect4Listener {
  private Connect4Model model;
  private Chip myChip;
  private static final int ROW = Connect4Model.ROW;
  private static final int COLUMN = Connect4Model.COLUMN;
  private BoardScoreCalculator calculator = 
      BoardScoreCalculator.getCalculator();
  // game state
  private int[] chipsInColumn = new int[COLUMN];
  private int[][] board = new int[ROW][COLUMN];

  public ComputerView(Connect4Model model) {
    this.model = model;
    model.addListener(this);
  }

  @Override
  public void placeChip(int row, int column, Chip chip) {
    chipsInColumn[column]++;
    board[row][column] = chip == myChip ? 1 : 2;
  }

  @Override
  public void draw() {

  }

  @Override
  public void winner(Chip chip) {

  }

  @Override
  public void gameStarted() {
    board = new int[ROW][COLUMN];
    chipsInColumn = new int[COLUMN];
  }

  @Override
  public void turnToPlay(Player player) {
    if(player.listener == this){
      int column = makeMove();
      model.placeChip(column, this);
    }
  }

  public void setMyChip(Chip chip){
    myChip = chip;
  }
 
  private int makeMove(){
    int bestColumn = 0;
    int maxScore = -1;
    for (int column = 0; column < COLUMN; column++){
      if(chipsInColumn[column] >= ROW){
        continue;
      }
      int row = chipsInColumn[column];
      board[row][column] = 1;
      int score = calculator.totalScore(board, row, column);
      if(score > maxScore){
        bestColumn = column;
        maxScore = score;
      }
      board[row][column] = 0;
    }
    return bestColumn;
  }

}
