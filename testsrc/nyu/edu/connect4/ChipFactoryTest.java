package nyu.edu.connect4;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChipFactoryTest {

  @Test
  public void testGetChip() {
    assertEquals("Should be red chip", Chip.Color.RED, 
        ChipFactory.getChip(Chip.Color.RED).getColor());
    assertEquals("Should be yellow chip", Chip.Color.YELLOW, 
        ChipFactory.getChip(Chip.Color.YELLOW).getColor());
  }
  
  @Test
  public void testGetChip_sameChip() {
    assertEquals("Should be same chip", ChipFactory.getChip(Chip.Color.RED), 
        ChipFactory.getChip(Chip.Color.RED));
  }
  

}
