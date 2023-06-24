import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel button = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    private int x;
    private int o;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(15, 15, 15));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(200, 200, 100));
        textfield.setFont(new Font("Serif", Font.TYPE1_FONT, 70));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-tac-toe");
        textfield.setOpaque(true);

        panel.setLayout(new BorderLayout());
        panel.setBounds(0,0,1000,1000);

        panel.add(textfield);


        button.setLayout(new GridLayout(3,3));
        button.setBackground(new Color(25,25,25));
        button.setBounds(0,0,1000,1000);

        for(int i = 0; i < 9; i++){
            buttons[i]= new JButton();
            button.add(buttons[i]);
            buttons[i].setFont(new Font("Serif", Font.TYPE1_FONT, 70));
            buttons[i].setBackground(new Color(25,25,25));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        frame.add(button);
        frame.add(panel, BorderLayout.NORTH);
        //frame.add(buttons);
        firstTurn();

    }
    public void resetGame(){
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(true);
            buttons[i].setText("");
            buttons[i].setBackground(new Color(25,25,25));
        }
        firstTurn();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i =0; i < 9; i++){
            if (e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(200,200,100));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
//                        if(checkWin("X")){
//                            int choice = JOptionPane.showConfirmDialog(frame, "X wins! Play again?", "Game over!", JOptionPane.YES_NO_OPTION);
//                            if(choice == JOptionPane.YES_NO_OPTION){
//                                resetGame();
//                            }else {
//                                System.exit(0);
//                            }
//                        }else if(checkDraw()){
//                            int choice = JOptionPane.showConfirmDialog(frame, "Draw! Play again?", "Game Over!", JOptionPane.YES_NO_OPTION);
//                            if(choice == JOptionPane.YES_NO_OPTION){
//                                resetGame();
//                            }else {
//                                System.exit(0);
//                            }
//                        }
                      check();
                    }
                } else{
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(200, 200, 100));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
//                        if(checkWin("O")) {
//                            int choice = JOptionPane.showConfirmDialog(frame, "O wins! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
//                            if (choice == JOptionPane.YES_OPTION) {
//                                resetGame();
//                            } else {
//                                System.exit(0);  // Exit the game
//                            }
//                        }else if(checkDraw()){
//                                int choice = JOptionPane.showConfirmDialog(frame, "It's a draw! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
//                                if(choice == JOptionPane.YES_NO_OPTION){
//                                    resetGame();
//                            }else {
//                                    System.exit(0);
//                                }
//                        }
                        check();
                    }
                }
            }

        }
//        JButton resetButton = new JButton("Reset");
//        resetButton.setFont(new Font("Serif", Font.TYPE1_FONT, 30));
//        resetButton.setFocusable(false);
//        resetButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                resetGame();
//            }
//        });
//
//        button.add(resetButton);
    }


    public void firstTurn(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            player1_turn = true;
            textfield.setText("X Turn");
        }
        else {
            player1_turn = false;
            textfield.setText("O Turn");
        }

    }
    public void check(){
        if((buttons[0].getText()=="X"&&buttons[1].getText()=="X")&&buttons[2].getText()=="X"){
            xWon(0,1,2);
        }
        if((buttons[3].getText()=="X"&&buttons[4].getText()=="X")&&buttons[5].getText()=="X"){
            xWon(3,4,5);
        }
        if((buttons[6].getText()=="X"&&buttons[7].getText()=="X")&&buttons[8].getText()=="X"){
            xWon(6,7,8);
        }
        if((buttons[0].getText()=="X"&&buttons[3].getText()=="X")&&buttons[6].getText()=="X"){
            xWon(0,3,6);
        }
        if((buttons[1].getText()=="X"&&buttons[4].getText()=="X")&&buttons[7].getText()=="X"){
            xWon(1,4,7);
        }
        if((buttons[3].getText()=="X"&&buttons[5].getText()=="X")&&buttons[8].getText()=="X"){
            xWon(3,5,8);
        }
        if((buttons[0].getText()=="X"&&buttons[4].getText()=="X")&&buttons[8].getText()=="X"){
            xWon(0,4,8);
        }
        if((buttons[2].getText()=="X"&&buttons[4].getText()=="X")&&buttons[6].getText()=="X"){
            xWon(2,4,6);
        }
        if((buttons[0].getText()=="O"&&buttons[1].getText()=="O")&&buttons[2].getText()=="O"){
            oWon(0,1,2);
       }
        if((buttons[3].getText()=="O"&&buttons[4].getText()=="O")&&buttons[5].getText()=="O"){
            oWon(3,4,5);
        }
        if((buttons[6].getText()=="O"&&buttons[7].getText()=="O")&&buttons[8].getText()=="O"){
            oWon(6,7,8);
        }
        if((buttons[0].getText()=="O"&&buttons[3].getText()=="O")&&buttons[6].getText()=="O"){
            oWon(0,3,6);
        }
        if((buttons[1].getText()=="O"&&buttons[4].getText()=="O")&&buttons[7].getText()=="O"){
            oWon(1,4,7);
        }
        if((buttons[2].getText()=="O"&&buttons[5].getText()=="O")&&buttons[8].getText()=="O"){
            oWon(2,5,8);
        }
        if((buttons[0].getText()=="O"&&buttons[4].getText()=="O")&&buttons[8].getText()=="O"){
            oWon(0,4,8);
        }
        if((buttons[2].getText()=="O"&&buttons[4].getText()=="O")&&buttons[6].getText()=="O"){
            oWon(2,4,6);
        }
    }
    public int xWon(int a, int b, int c){
        buttons[a].setBackground(new Color(200,200,10));
        buttons[b].setBackground(new Color(200,200,10));
        buttons[c].setBackground(new Color(200,200,10));

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        int x = 0;
        x++;
        textfield.setText("X wins");
        return x;


    }
    public int oWon(int a, int b, int c){
        buttons[a].setBackground(new Color(200,200,10));
        buttons[b].setBackground(new Color(200,200,10));
        buttons[c].setBackground(new Color(200,200,10));
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        int o = 0;
        o++;
        textfield.setText("O wins");
        return o;

    }
    public void printScore (int x, int o){
        textfield.setText(x + "-" + o);

    }
    public boolean checkWin(String player) {
        if ((buttons[0].getText().equals(player) && buttons[1].getText().equals(player) && buttons[2].getText().equals(player))
                || (buttons[3].getText().equals(player) && buttons[4].getText().equals(player) && buttons[5].getText().equals(player))
                || (buttons[6].getText().equals(player) && buttons[7].getText().equals(player) && buttons[8].getText().equals(player))
                || (buttons[0].getText().equals(player) && buttons[3].getText().equals(player) && buttons[6].getText().equals(player))
                || (buttons[1].getText().equals(player) && buttons[4].getText().equals(player) && buttons[7].getText().equals(player))
                || (buttons[2].getText().equals(player) && buttons[5].getText().equals(player) && buttons[8].getText().equals(player))
                || (buttons[0].getText().equals(player) && buttons[4].getText().equals(player) && buttons[8].getText().equals(player))
                || (buttons[2].getText().equals(player) && buttons[4].getText().equals(player) && buttons[6].getText().equals(player))) {
            return true;
        }
        return false;
    }
    public boolean checkDraw() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }
}
