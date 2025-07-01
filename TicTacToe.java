import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[9];
    boolean playerXTurn = true;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return;

        clicked.setText(playerXTurn ? "X" : "O");
        if (checkWin()) {
            JOptionPane.showMessageDialog(this,
                    (playerXTurn ? "X" : "O") + " wins!");
            resetGame();
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }

        playerXTurn = !playerXTurn;
    }

    private boolean checkWin() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++)
            board[i / 3][i % 3] = buttons[i].getText();

        for (int i = 0; i < 3; i++) {
            if (!board[i][0].isEmpty() &&
                    board[i][0].equals(board[i][1]) &&
                    board[i][1].equals(board[i][2])) return true;

            if (!board[0][i].isEmpty() &&
                    board[0][i].equals(board[1][i]) &&
                    board[1][i].equals(board[2][i])) return true;
        }

        return !board[0][0].isEmpty() &&
                board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2])
                || !board[0][2].isEmpty() &&
                board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0]);
    }

    private boolean isDraw() {
        for (JButton b : buttons)
            if (b.getText().isEmpty()) return false;
        return true;
    }

    private void resetGame() {
        for (JButton b : buttons) b.setText("");
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
