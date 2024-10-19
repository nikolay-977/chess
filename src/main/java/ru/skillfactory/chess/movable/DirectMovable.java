package ru.skillfactory.chess.movable;

public interface DirectMovable {

    default boolean isMovementHorizontal(int line, int column, int toLine, int toColumn) {
        return line != toLine && column == toColumn;
    }

    default boolean isMovementVertical(int line, int column, int toLine, int toColumn) {
        return line == toLine && column != toColumn;
    }
}
