package ru.skillfactory.chess.pieces;

import ru.skillfactory.chess.board.ChessBoard;
import ru.skillfactory.chess.movable.MovableInsideBoard;

public abstract class ChessPiece implements MovableInsideBoard {

    protected String color;
    protected boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
