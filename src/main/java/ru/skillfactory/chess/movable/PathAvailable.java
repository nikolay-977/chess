package ru.skillfactory.chess.movable;

import ru.skillfactory.chess.board.ChessBoard;

public interface PathAvailable {

    default boolean isPathFree(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // определение направления движения
        int lineDirection = Integer.signum(toLine - line);
        int columnDirection = Integer.signum(toColumn - column);

        // добавляем 1 к line и column, чтобы избежать проверки начальной позиции
        int currentLine = line + lineDirection;
        int currentColumn = column + columnDirection;

        // вычитаем 1 от line и column, чтобы избежать проверки конечной позиции
        int endLine = toLine - lineDirection;
        int endColumn = toColumn + columnDirection;

        // проверка отсутствия фигур на пути
        while (currentLine != endLine && currentColumn != endColumn) {
            boolean isDiagonalPathNotFree = chessBoard.board[currentLine][currentColumn] != null;
            if (isDiagonalPathNotFree) {
                return false;
            }
            currentLine += lineDirection;
            currentColumn += columnDirection;
        }
        return true;
    }
}
