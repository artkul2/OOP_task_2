import Chess.Bishop;
import Chess.Board;
import Chess.Pawn;

void main(){
    Board board = Board.createStandartBoard();
    board.move(2, 2, 4, 2);
    board.print();
    board.move(7, 3, 5, 3);
    board.print();
    board.move(4, 2, 5, 3);
    board.print();
    board.move(8, 7, 6, 6);
    board.print();
    board.move(6, 6, 4, 5);
    board.print();
    board.move(4, 5, 5, 3);
    board.print();
    board.move(1, 3, 3, 1);
    board.print();
    board.move(3, 1, 5, 3);
    board.print();
    board.move(5, 3, 6, 2);
    board.print();
    board.move(7, 1, 6, 2);
    board.print();
    board.move(8, 1, 2, 1);
    board.print();
    board.move(2, 1, 5, 1);
    board.print();
}