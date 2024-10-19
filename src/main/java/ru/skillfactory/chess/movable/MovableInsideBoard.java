package ru.skillfactory.chess.movable;

public interface MovableInsideBoard {

    default boolean isMovementOutsideBoard(int toLine, int toColumn) {
        return toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7;
    }

    default boolean isSamePosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }
}
