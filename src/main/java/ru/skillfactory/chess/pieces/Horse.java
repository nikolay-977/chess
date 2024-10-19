package ru.skillfactory.chess.pieces;

import ru.skillfactory.chess.board.ChessBoard;
import ru.skillfactory.chess.movable.EndPositionAvailable;

public class Horse extends ChessPiece implements EndPositionAvailable {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка - фигура не может выйти за доску
        return !isMovementOutsideBoard(toLine, toColumn) &&
                // проверка - фигура не может сходить в точку, в которой она сейчас
                !isSamePosition(line, column, toLine, toColumn) &&
                // проверка - фигура движется как конь
                isHorseMoving(line, column, toLine, toColumn) &&
                // проверка конечной позиции на наличие фигуры
                isEndPositionAvailable(chessBoard, line, column, toLine, toColumn);
    }

    private boolean isHorseMoving(int line, int column, int toLine, int toColumn) {
        int lines = Math.abs(toLine - line);
        int columns = Math.abs(toColumn - column);
        return (lines == 2 && columns == 1) || (lines == 1 && columns == 2);
    }
}
