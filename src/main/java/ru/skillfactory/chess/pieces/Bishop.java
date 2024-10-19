package ru.skillfactory.chess.pieces;

import ru.skillfactory.chess.board.ChessBoard;
import ru.skillfactory.chess.movable.DiagonalMovable;
import ru.skillfactory.chess.movable.EndPositionAvailable;
import ru.skillfactory.chess.movable.PathAvailable;

public class Bishop extends ChessPiece implements PathAvailable, EndPositionAvailable, DiagonalMovable {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка - фигура не может выйти за доску
        return !isMovementOutsideBoard(toLine, toColumn) &&
                // проверка - фигура не может сходить в точку, в которой она сейчас
                !isSamePosition(line, column, toLine, toColumn) &&
                // проверка - фигура движется как слон
                isBishopMoving(line, column, toLine, toColumn) &&
                // проверка доступности пути
                isPathFree(chessBoard, line, column, toLine, toColumn) &&
                // проверка конечной позиции на наличие фигуры
                isEndPositionAvailable(chessBoard, line, column, toLine, toColumn);
    }

    private boolean isBishopMoving(int line, int column, int toLine, int toColumn) {
        return isDiagonalMovement(line, column, toLine, toColumn);
    }
}
