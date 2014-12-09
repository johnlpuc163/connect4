package nyu.edu.connect4;

/**
 * Use Builder class instead of constructor to create an instance of player
 */
public class Player {
  public final Connect4Listener listener;
  public final Chip chip;

  private Player(Builder builder){
    this.chip = builder.chip;
    this.listener = builder.listener;
  }

  public static class Builder {
    private Chip chip;
    private Connect4Listener listener;

    public Builder(){
    }

    public Builder chip(Chip chip){
      this.chip = chip;
      return this;
    }

    public Builder listener(Connect4Listener listener){
      this.listener = listener;
      return this;
    }

    public Player build(){
      return new Player(this);
    }
  }
}
