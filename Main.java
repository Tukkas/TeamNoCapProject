import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main implements ActionListener {

    private JButton snakeButton;
    private JButton ticTaCToeButton;
    private JButton hangManButton;
    private JButton viewHighScore;

    public Main() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel chooseGameLabel = new JLabel("Choose a game:");
        chooseGameLabel.setBounds(125, 20, 100 ,25);
        panel.add(chooseGameLabel);

        snakeButton = new JButton("Snake");
        snakeButton.addActionListener(this);
        snakeButton.setBounds(110, 80, 120, 25);
        panel.add(snakeButton);

        ticTaCToeButton = new JButton("Tic Tac Toe");
        ticTaCToeButton.addActionListener(this);
        ticTaCToeButton.setBounds(110, 130, 120, 25);
        panel.add(ticTaCToeButton);

        hangManButton = new JButton("Hangman");
        hangManButton.addActionListener(this);
        hangManButton.setBounds(110, 180, 120, 25);
        panel.add(hangManButton);

        viewHighScore = new JButton("View high score");
        viewHighScore.setBounds(105, 270, 130, 25);
        panel.add(viewHighScore);

        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == snakeButton) {
            snakeButtonClicked();
        } else if (e.getSource() == ticTaCToeButton) {
            ticTacToeButtonClicked();
        } else if (e.getSource() == hangManButton) {
            hangManButtonClicked();
        } else{
            viewHighScoreButtonClicked();
        }
    }

    public void snakeButtonClicked() {
        new SnakeFrame();
    }

    public void ticTacToeButtonClicked() {
        new TicTacToe();
    }

    public void hangManButtonClicked() {
        SwingUtilities.invokeLater(HangmanGameGUI::new);
    }

    public void viewHighScoreButtonClicked() {

    }

    public static void main(String[] args) {
        new Main();
    }
}