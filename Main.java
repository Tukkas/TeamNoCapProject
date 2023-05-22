import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel chooseGameLabel = new JLabel("Choose a game:");
        chooseGameLabel.setBounds(125, 20, 100 ,25);
        panel.add(chooseGameLabel);

        JButton snakeButton = new JButton("Snake");
        snakeButton.setBounds(110, 80, 120, 25);
        panel.add(snakeButton);

        JButton ticTaCToeButton = new JButton("Tic Tac Toe");
        ticTaCToeButton.setBounds(110, 130, 120, 25);
        panel.add(ticTaCToeButton);

        JButton hangManButton = new JButton("Hangman");
        hangManButton.setBounds(110, 180, 120, 25);
        panel.add(hangManButton);

        JButton viewHighScore = new JButton("View high score");
        viewHighScore.setBounds(105, 270, 130, 25);
        panel.add(viewHighScore);

        frame.setVisible(true);


    }
}