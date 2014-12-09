package nyu.edu.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Connect4View implements Connect4Listener{

  private static final int ROW = Connect4Model.ROW;
  private static final int COLUMN = Connect4Model.COLUMN;
  private static final int CHIP_SIZE = 50;
  private static final int ORIGIN = 50;
  private Connect4Model model;
  private boolean isMyTurn = false;
  private Chip[][] chips = new Chip[ROW][COLUMN];
  private JFrame frame;
  private Board board;
  private JLabel label;
  private JPanel panel;
  private JButton replayButton = new JButton("Click to Replay");

  public Connect4View(Connect4Model model){
    this.model = model;
    model.addListener(this);
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    board = new Board();
    board.setLayout(null);
    board.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        Connect4View.this.mouseClicked(mouseEvent);
      }
    });
    panel = new JPanel(new BorderLayout());
    panel.add(board, "Center");
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
    label = new JLabel(" ");
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setVerticalAlignment(SwingConstants.CENTER);
    panel.add(label, "North");
    panel.add(replayButton, "South");
    replayButton.setVisible(false);
    replayButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event) {
        Connect4View.this.replayButtonClicked();
      }
    });
  }

  private void mouseClicked(MouseEvent mouseEvent){
    if(!isMyTurn){
      return;
    }
    Point p = mouseEvent.getPoint();
    double row = (ORIGIN + ROW * CHIP_SIZE - p.getY()) / CHIP_SIZE;
    double column =  (p.getX() - ORIGIN) / CHIP_SIZE;
    if(row > 0 && row < ROW && column > 0 && column < COLUMN 
        && chips[ROW - 1][ (int) column] == null){
      isMyTurn = false;
      model.placeChip((int) column, this);
    }
  }

  private void replayButtonClicked(){
    model.startGame();
  }

  @Override
  public void placeChip(int row, int column, Chip chip) {
    chips[row][column] = chip;
    board.repaint();
  }

  @Override
  public void draw() {
    label.setText("TIE !!");
    replayButton.setVisible(true);
  }

  @Override
  public void winner(Chip chip) {
    label.setText(chip.getColor() + " is WINNER !!");
    replayButton.setVisible(true);
  }

  @Override
  public void gameStarted() {
    label.setText(" ");
    replayButton.setVisible(false);
    chips = new Chip[ROW][COLUMN];
    isMyTurn = false;
    board.repaint();
  }

  @Override
  public void turnToPlay(Player player) {
    label.setText(player.chip.getColor() + "'s turn to play");
    if(player.listener == this){
      isMyTurn = true;
    }
  }

  class Board extends JPanel {

    @Override
    public void paintComponent(Graphics g) {

      super.paintComponent(g);
      drawBoard(g);
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(ORIGIN * 2 + COLUMN * CHIP_SIZE,
          ORIGIN * 2 + ROW * CHIP_SIZE);
    }

    private void drawBoard(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      drawGrid(g2d);
      drawChips(g2d);
    }

    private void drawChips(Graphics2D g2d){
      for(int row = 0; row < ROW; row++){
        for(int col = 0; col < COLUMN; col++){
          if(chips[row][col] != null){
            drawSingleChip(row, col, chips[row][col], g2d);
          }
        }
      }
    }

    private void drawSingleChip(int row, int col, Chip chip, Graphics2D g2d){
      switch (chip.getColor()){
      case RED:
        g2d.setColor(Color.RED);
        break;
      case YELLOW:
        g2d.setColor(Color.YELLOW);
        break;
      default:
        g2d.setColor(Color.GRAY);
      }
      g2d.fillOval(ORIGIN + col * CHIP_SIZE, ORIGIN + CHIP_SIZE * (ROW - 1) - 
          row * CHIP_SIZE,CHIP_SIZE ,CHIP_SIZE);
    }

    private void drawGrid(Graphics2D g2d){
      int width = COLUMN * CHIP_SIZE;
      int height = ROW * CHIP_SIZE;

      for(int i=0; i <= ROW; i++){
        g2d.drawLine(ORIGIN, ORIGIN + i * CHIP_SIZE,
            ORIGIN + width, ORIGIN + i * CHIP_SIZE);
      }

      for(int i=0; i <= COLUMN; i++){
        g2d.drawLine(ORIGIN + i * CHIP_SIZE, ORIGIN, ORIGIN + i * CHIP_SIZE,
            ORIGIN + height);
      }
    }

  }
}
