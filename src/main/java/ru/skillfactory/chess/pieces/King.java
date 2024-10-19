package ru.skillfactory.chess.pieces;

import ru.skillfactory.chess.board.ChessBoard;
import ru.skillfactory.chess.movable.EndPositionAvailable;

public class King extends ChessPiece implements EndPositionAvailable {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка - фигура не может выйти за доску
        return !isMovementOutsideBoard(toLine, toColumn) &&
                // проверка - фигура не может сходить в точку, в которой она сейчас
                !isSamePosition(line, column, toLine, toColumn) &&
                // проверка - фигура движется как король
                isKingMoving(line, column, toLine, toColumn) &&
                // проверка - на шах
                !isUnderAttack(chessBoard, toLine, toColumn) &&
                // проверка конечной позиции на наличие фигуры
                isEndPositionAvailable(chessBoard, line, column, toLine, toColumn);
    }

    private boolean isKingMoving(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1;
    }

    private boolean isUnderAttack(ChessBoard chessBoard, int toLine, int toColumn) {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece piece = chessBoard.board[line][column];

                if(piece != null) {
                    boolean isAnotherPlayerColor = !piece.color.equals(this.color);
                    boolean isPieceCanMoving = piece.canMoveToPosition(chessBoard, line, column, toLine, toColumn);

                    if (isAnotherPlayerColor && isPieceCanMoving) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
