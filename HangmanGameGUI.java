import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGameGUI extends JFrame {
    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private Hangman hangman;
    private HangmanImagePanel hangmanImagePanel;

    public HangmanGameGUI() {
        // Ask the player for the word selection method
        String[] options = {"Random Word (1 Player)", "Custom Word (2 Player)"};
        int choice = JOptionPane.showOptionDialog(null, "Please select word selection method:", "Hangman",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        String word;
        if (choice == 0) {
            word = getRandomWord();
        } else {
            word = JOptionPane.showInputDialog(null, "Enter the word:").toUpperCase();
        }

        // Initialize the Hangman object
        hangman = new Hangman(word);

        // Set up the GUI components
        wordLabel = new JLabel(formatWord(hangman.getWordToShow()));
        attemptsLabel = new JLabel("Attempts: " + hangman.getAttempts());
        guessTextField = new JTextField(20); // Set the preferred width of the input field
        guessButton = new JButton("Guess");
        hangmanImagePanel = new HangmanImagePanel();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessTextField.getText().toUpperCase();

                if (guess.isEmpty() || guess.length() != 1) {
                    JOptionPane.showMessageDialog(null, "Please enter a single letter.");
                    guessTextField.setText("");
                    return; // Exit the action listener if the guess is not a single letter
                }

                boolean isCorrectGuess = hangman.guess(guess);
                wordLabel.setText(formatWord(hangman.getWordToShow()));
                attemptsLabel.setText("Attempts: " + hangman.getAttempts());
                hangmanImagePanel.setIncorrectGuesses(hangman.getIncorrectGuesses());

                if (hangman.isGameOver()) {
                    guessButton.setEnabled(false);
                    String message = isCorrectGuess ? "You win! Thanks for playing!" : "You lose! The correct word was: " + hangman.getWord();
                    JOptionPane.showMessageDialog(null, message);
                    showGameOverPopup();
                }

                // Clear the text field after each guess
                guessTextField.setText("");
            }
        });

        // Set up the JFrame
        setLayout(new GridBagLayout()); // Use GridBagLayout for precise control over component placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around the components
        add(wordLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH; // Allow the hangman image panel to expand both horizontally and vertically
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(hangmanImagePanel, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; // Reset the fill to none for the remaining components
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(attemptsLabel, gbc);

        gbc.gridy = 3;
        add(guessTextField, gbc);

        gbc.gridy = 4;
        add(guessButton, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Pack the components tightly
        setLocationRelativeTo(null); // Set the location of the window to the center of the screen
        setVisible(true);

        guessTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessButton.doClick();
            }
        });
    }

    private void showGameOverPopup() {
        String[] options = {"Exit", "Restart"};
        int choice = JOptionPane.showOptionDialog(this, "Game Over! Do you want to exit or restart?", "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            System.exit(0); // Exit the game
        } else {
            // Restart the game by creating a new instance of HangmanGameGUI
            SwingUtilities.invokeLater(HangmanGameGUI::new);
            dispose(); // Close the current game window
        }
    }

    private String getRandomWord() {
        List<String> words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("./src/Words.txt"));
            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    private String formatWord(String wordToShow) {
        return wordToShow.replace("", " ").trim();
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(HangmanGameGUI::new);
    }*/
}

class HangmanImagePanel extends JPanel {
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_HEIGHT = 300;

    private int incorrectGuesses;

    public HangmanImagePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    public void setIncorrectGuesses(int incorrectGuesses) {
        this.incorrectGuesses = incorrectGuesses;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the hangman image based on the number of incorrect guesses
        if (incorrectGuesses >= 1) {
            g.drawLine(50, PANEL_HEIGHT - 50, PANEL_WIDTH - 50, PANEL_HEIGHT - 50); // base
        }
        if (incorrectGuesses >= 2) {
            g.drawLine(100, PANEL_HEIGHT - 250, 100, PANEL_HEIGHT - 50); // vertical pole
        }
        if (incorrectGuesses >= 3) {
            g.drawLine(100, PANEL_HEIGHT - 250, PANEL_WIDTH - 100, PANEL_HEIGHT - 250); // horizontal pole
        }
        if (incorrectGuesses >= 4) {
            g.drawLine(PANEL_WIDTH - 100, PANEL_HEIGHT - 250, PANEL_WIDTH - 100, PANEL_HEIGHT - 225); // rope
        }
        if (incorrectGuesses >= 5) {
            g.drawOval(PANEL_WIDTH - 110, PANEL_HEIGHT - 225, 20, 20); // head
        }
        if (incorrectGuesses >= 6) {
            g.drawLine(PANEL_WIDTH - 100, PANEL_HEIGHT - 205, PANEL_WIDTH - 100, PANEL_HEIGHT - 120); // body
        }
        if (incorrectGuesses >= 7) {
            g.drawLine(PANEL_WIDTH - 100, PANEL_HEIGHT - 180, PANEL_WIDTH - 115, PANEL_HEIGHT - 190); // left arm
        }
        if (incorrectGuesses >= 8) {
            g.drawLine(PANEL_WIDTH - 100, PANEL_HEIGHT - 180, PANEL_WIDTH - 85, PANEL_HEIGHT - 190); // right arm
        }
        if (incorrectGuesses >= 9) {
            g.drawLine(PANEL_WIDTH - 100, PANEL_HEIGHT - 120, PANEL_WIDTH - 115, PANEL_HEIGHT - 100); // left leg
        }
        if (incorrectGuesses >= 10) {
            g.drawLine(PANEL_WIDTH - 100, PANEL_HEIGHT - 120, PANEL_WIDTH - 85, PANEL_HEIGHT - 100); // right leg
        }
    }
}

class Hangman {
    private String word;
    private StringBuilder wordToShow;
    private int attempts;
    private int incorrectGuesses;

    public Hangman(String word) {
        this.word = word;
        wordToShow = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            if (Character.isWhitespace(word.charAt(i))) {
                wordToShow.append(" "); // Add multiple spaces for whitespace characters

            } else {
                wordToShow.append("_");
            }
        }
        attempts = 10;
        incorrectGuesses = 0;
    }

    private void updateWordToShow(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (Character.toUpperCase(word.charAt(i)) == letter) {
                if (Character.isWhitespace(word.charAt(i))) {
                    wordToShow.setCharAt(i, ' '); // Update space character instead of underscore
                } else {
                    wordToShow.setCharAt(i, letter);
                }
            }
        }
    }

    public String getWordToShow() {
        return wordToShow.toString();
    }

    public int getAttempts() {
        return attempts;
    }

    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public boolean guess(String letter) {
        boolean isCorrectGuess = false;
        if (letter.length() == 1) {
            char guessedLetter = Character.toUpperCase(letter.charAt(0));
            for (int i = 0; i < word.length(); i++) {
                if (Character.toUpperCase(word.charAt(i)) == guessedLetter) {
                    wordToShow.setCharAt(i, guessedLetter);
                    isCorrectGuess = true;
                }
            }

            if (!isCorrectGuess) {
                attempts--;
                incorrectGuesses++;
            }
        }
        return isCorrectGuess;
    }

    public boolean isGameOver() {
        return attempts == 0 || wordToShow.indexOf("_") == -1;
    }

    public String getWord() {
        return word;
    }
}


