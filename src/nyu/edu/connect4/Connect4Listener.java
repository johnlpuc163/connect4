package nyu.edu.connect4;

public interface Connect4Listener {
  public void placeChip(int row, int column, Chip chip);
  public void draw();
  public void winner(Chip chip);
  public void gameStarted();
  public void turnToPlay(Player player);
}
