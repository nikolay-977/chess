package ru.skillfactory.chess.movable;

public interface DiagonalMovable {

    default boolean isDiagonalMovement(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }
}
