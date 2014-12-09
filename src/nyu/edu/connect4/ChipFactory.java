package nyu.edu.connect4;

import java.util.HashMap;
import java.util.Map;


class ChipFactory {
  private static Map<Chip.Color, Chip> chips = new HashMap<Chip.Color, Chip>();

  static Chip getChip(Chip.Color color){
    if( chips.containsKey(color) ){
      return chips.get(color);
    }
    Chip newChip;
    switch (color){
      case RED:
        newChip = new ChipClass(Chip.Color.RED);
        chips.put(color, newChip);
        return newChip;
      case YELLOW:
        newChip = new ChipClass(Chip.Color.YELLOW);
        chips.put(color, newChip);
        return newChip;
      default:
        throw new IllegalArgumentException("No such chip color");
    }
  }

  private static class ChipClass implements Chip{
    private Color color;

    ChipClass(Color color){
      this.color = color;
    }

    public Color getColor(){
      return color;
    }
  }
}
