package ru.skillfactory.chess.board;

import ru.skillfactory.chess.pieces.ChessPiece;

public class ChessBoard {

    public ChessPiece[][] board = new ChessPiece[8][8]; // создаем доску для игры
    private String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            ChessPiece chessPiece = board[startLine][startColumn];

            if (!nowPlayer.equals(chessPiece.getColor())) return false;

            if (chessPiece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                if (chessPiece.isCheck()) {
                    chessPiece.setCheck(false);
                }

                board[endLine][endColumn] = chessPiece; // выполняем перемещение
                board[startLine][startColumn] = null; // устанавливаем null для предыдущей позиции

                // переключаем игрока
                turnPlayer();

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  // выводим доску в консоль
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        return castling("White", 0);

    }

    public boolean castling7() {
        return castling("Black", 7);
    }

    private boolean castling(String color, int line) {
        if (!nowPlayer.equals(color)) return false;
        if (board[line][1] != null || board[line][2] != null || board[line][3] != null) return false;
        if (!board[line][0].isCheck() || !board[line][4].isCheck()) return false;
        ChessPiece rook = board[line][0];
        ChessPiece king = board[line][4];
        board[line][3] = rook;
        board[line][2] = king;
        board[line][0] = null;
        board[line][4] = null;
        turnPlayer();
        return true;
    }

    private void turnPlayer() {
        this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
    }
}
