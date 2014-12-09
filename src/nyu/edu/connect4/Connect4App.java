package nyu.edu.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connect4App {
  private JFrame frame = new JFrame();
  private JButton singlePlayerButton = new JButton("1 Player");
  private JButton doublePlayersButton = new JButton("2 Player");

  public void play(){
    GridLayout gridLayout = new GridLayout(0,1);
    JPanel contentPanel = new JPanel(gridLayout);
    frame.add(contentPanel);
    JLabel label = new JLabel("Play CONNECT FOUR !");
    label.setHorizontalAlignment(SwingConstants.CENTER);
    contentPanel.add(label);
    contentPanel.add(singlePlayerButton);
    contentPanel.add(doublePlayersButton);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setSize(200, 200);
    this.frame.setVisible(true);

    singlePlayerButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event) {
        Connect4App.this.singlePlayer();
      }
    });

    doublePlayersButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event) {
        Connect4App.this.doublePlayers();
      }
    });
  }

  private void singlePlayer(){
    this.frame.setVisible(false);
    Connect4Model model = new Connect4Model();
    Connect4View view1 = new Connect4View(model);
    model.setPlayer1(view1);
    ComputerView ai = new ComputerView(model);
    ai.setMyChip(model.setPlayer2(ai));
    model.startGame();
  }

  private void doublePlayers(){
    Connect4Model model = new Connect4Model();
    Connect4View view1 = new Connect4View(model);
    model.setPlayer1(view1);
    model.setPlayer2(view1);
    model.startGame();
  }

  public static void main(String[] args){
    Connect4App connect4 = new Connect4App();
    connect4.play();
  }

}