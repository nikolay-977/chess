package ru.skillfactory.chess.pieces;

import ru.skillfactory.chess.board.ChessBoard;

public class Pawn extends ChessPiece {

    private int direction;

    public Pawn(String color) {
        super(color);
        // определение направления движения
        direction = getColor().equals("White") ? 1 : -1;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка - фигура не может выйти за доску
        return !isMovementOutsideBoard(toLine, toColumn) &&
                // проверка - фигура не может сходить в точку, в которой она сейчас
                !isSamePosition(line, column, toLine, toColumn) &&
                // проверка - фигура движется как пешка
                isPawnMoving(line, column, toLine, toColumn) &&
                // проверка пути и конечной позиции
                isPathAndEndpositionAvailable(chessBoard, line, column, toLine, toColumn);
    }

    private int getDirection() {
        return direction;
    }

    private boolean isPawnMovingOneField(int line, int column, int toLine, int toColumn) {
        return toLine == line + getDirection() && column == toColumn;
    }

    private boolean isPawnMovingTwoField(int line, int column, int toLine, int toColumn) {
        return check && toLine == line + 2 * getDirection() && column == toColumn;
    }

    private boolean isPawnAttacking(int line, int column, int toLine, int toColumn) {
        return toLine == line + getDirection() && Math.abs(toColumn - column) == 1;
    }

    private boolean isPawnMoving(int line, int column, int toLine, int toColumn) {
        return isPawnMovingOneField(line, column, toLine, toColumn) ||
                isPawnMovingTwoField(line, column, toLine, toColumn) ||
                isPawnAttacking(line, column, toLine, toColumn);
    }

    private boolean isPathAndEndpositionAvailable(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isOneFieldFree = chessBoard.board[line + getDirection()][column] == null;
        boolean isTwoFieldFree = chessBoard.board[line + 2 * getDirection()][column] == null;

        // проверка отсутствия фигур на пути
        if (isPawnMovingOneField(line, column, toLine, toColumn)) {
            return isOneFieldFree;
        }

        // проверка отсутствия фигур на пути
        if (isPawnMovingTwoField(line, column, toLine, toColumn)) {
            return isOneFieldFree && isTwoFieldFree;
        }

        if (isPawnAttacking(line, column, toLine, toColumn)) {
            // проверка конечной позиции на наличие фигуры
            return chessBoard.board[toLine][toColumn] != null &&
                    // проверка фигуры на конечной позиции на принадлежность
                    !chessBoard.board[toLine][toColumn].color.equals(getColor());
        }
        return false;
    }
}
