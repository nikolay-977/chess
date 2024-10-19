package ru.skillfactory.chess.movable;

import ru.skillfactory.chess.board.ChessBoard;

public interface EndPositionAvailable {

    default boolean isEndPositionAvailable(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.board[toLine][toColumn] == null) {
            return true;
        } else {
            // проверка фигуры на конечной позиции на принадлежность
            return !chessBoard.board[toLine][toColumn].getColor().equals(chessBoard.nowPlayerColor());
        }
    }
}
