package nyu.edu.connect4;

/**
 * Singleton Class. Use getCalculator method instead of constructor to
 * get an instance of BoardScoreCalculator
 */
public class BoardScoreCalculator {
  private static final int FOUR_IN_A_ROW = 10000;
  private static final int THREE_IN_A_ROW = 100;
  private static final int TWO_IN_A_ROW = 10;
  private static final int ONE_IN_A_ROW = 1;
  
  private static final BoardScoreCalculator INSTANCE = 
      new BoardScoreCalculator();
  
  private BoardScoreCalculator(){
    
  }
  
  public static BoardScoreCalculator getCalculator(){
    return INSTANCE;
  }
  
  public boolean hasWinner(int[][] board, int row, int column){
    return totalScore(board, row, column) >= FOUR_IN_A_ROW;
  }

  public int totalScore(int[][] board, int row, int column){
    int score = 0;
    String target = "" + board[row][column];
    score += calculateScore(horizontalString(board, row, column), target);
    score += calculateScore(verticalString(board, row, column), target);
    score += calculateScore(backDiagonalString(board, row, column), target);
    score += calculateScore(forwardDiagonalString(board, row, column), target);
    return score;
  }

  private String horizontalString(int[][] board, int row, int column){
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<board[0].length; i++){
      sb.append(board[row][i]);
    }
    return sb.toString();
  }

  private String verticalString(int[][] board, int row, int column){
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<board.length; i++){
      sb.append(board[i][column]);
    }
    return sb.toString();
  }

  private String backDiagonalString(int[][] board, int row, int column){
    int height = board.length;
    int width = board[0].length;
    StringBuilder sb = new StringBuilder();
    int rowBegin = row;
    int colBegin = column;
    while(rowBegin > 0 && colBegin < width-1){
      rowBegin--;
      colBegin++;
    }
    while(rowBegin < height && colBegin >= 0){
      sb.append(board[rowBegin][colBegin]);
      rowBegin++;
      colBegin--;
    }
    return sb.toString();
  }

  private String forwardDiagonalString(int[][] board, int row, int column){
    int height = board.length;
    int width = board[0].length;
    StringBuilder sb = new StringBuilder();
    int rowBegin = row;
    int colBegin = column;
    while(rowBegin > 0 && colBegin > 0){
      rowBegin--;
      colBegin--;
    }
    while(rowBegin < height && colBegin < width){
      sb.append( board[rowBegin][colBegin]);
      rowBegin++;
      colBegin++;
    }
    return sb.toString();
  }
  
  private int calculateScore(String input, String target){
    int sum = 0;
    // four in a row
    if( input.contains(target + target + target + target) ){
      sum = FOUR_IN_A_ROW;
      return sum;
    }
    // three in a row
    String three = target + target + target;
    if( input.contains("0" + three) || input.contains( three + "0" )){
      sum += THREE_IN_A_ROW;
    }
    // two in a row
    String two = target + target;
    if( input.contains("00" + two) || input.contains( two + "00" ) ||
        input.contains( "0" + two + "0" )){
      sum += TWO_IN_A_ROW;
    }

    // one in a row
    if( input.contains("000" + target) || input.contains( target + "000" ) ||
        input.contains( "00" + target + "0" ) ||
        input.contains( "0" + target + "00" ) ){
      sum += ONE_IN_A_ROW;
    }

    return sum;
  }

}
