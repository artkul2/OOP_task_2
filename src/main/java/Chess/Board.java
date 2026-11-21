package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    String turn = "White";
    Piece[][] board;
    List<Piece> piecesWhite = new ArrayList<>();
    List<Piece> piecesBlack = new ArrayList<>();

    public Board(){
        this.board = new Piece[8][8];
    }

    public static Board createBlankBoard(){
        Board board = new Board();

        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                board.board[i - 1][j - 1] = new BlankSquare(i, j , "");
            }
        }

        return board;
    }

    public static Board createStandartBoard(){
        Board board = new Board();

        board.board[7][0] = new Rook(8, 1, "Black");
        board.board[7][7] = new Rook(8, 8, "Black");

        board.board[7][1] = new Knight(8, 2, "Black");
        board.board[7][6] = new Knight(8, 7, "Black");

        board.board[7][2] = new Bishop(8, 3, "Black");
        board.board[7][5] = new Bishop(8, 6, "Black");

        board.board[7][3] = new Queen(8, 4, "Black");
        board.board[7][4] = new King(8, 5, "Black");

        board.board[0][0] = new Rook(1, 1, "White");
        board.board[0][7] = new Rook(1, 8, "White");

        board.board[0][1] = new Knight(1, 2, "White");
        board.board[0][6] = new Knight(1, 7, "White");

        board.board[0][2] = new Bishop(1, 3, "White");
        board.board[0][5] = new Bishop(1, 6, "White");

        board.board[0][3] = new Queen(1, 4, "White");
        board.board[0][4] = new King(1, 5, "White");

        for(int i = 1; i <= 8; i++){
            board.board[6][i - 1] = new Pawn(7, i, "Black");
            board.board[1][i - 1] = new Pawn(2, i , "White");
        }

        for(int i = 0; i < 8; i++){
            board.piecesWhite.add(board.board[0][i]);
            board.piecesWhite.add(board.board[1][i]);

            board.piecesBlack.add(board.board[7][i]);
            board.piecesBlack.add(board.board[6][i]);
        }

        for (int i = 3; i <= 6; i++){
            for (int j = 1; j <= 8; j++){
                board.board[i - 1][j - 1] = new BlankSquare(i, j, "");
            }
        }

        return board;
    }

    public void move(int rankStart, int fileStart, int rankFinal, int fileFinal){
        Piece figure = board[rankStart - 1][fileStart - 1];

        if (figure.getClass() == BlankSquare.class){
            throw new IllegalArgumentException("Нельзя ходить из пустого поля");
        }

        /*if (!figure.color.equals(turn)){
            throw new IllegalArgumentException("Ход другой команды");
        }*/

        if (!isMovePossible(rankStart, fileStart, rankFinal, fileFinal)){
            throw new IllegalArgumentException("Невозможный ход");
        }

        placePiece(figure.getClass(), rankFinal, fileFinal, figure.color);
        board[rankStart - 1][fileStart - 1] = new BlankSquare(rankStart, fileStart, "");
        changeTurn();
    }

    private boolean isMovePossible(int rankStart, int fileStart, int rankFinal, int fileFinal){
        Piece figure = board[rankStart - 1][fileStart - 1];

        if(figure.getClass() == Pawn.class){
            Pawn pawn = (Pawn) figure;

            if(pawn.moveSquare()[0] == rankFinal && pawn.moveSquare()[1] == fileFinal && board[pawn.moveSquare()[0] - 1][pawn.moveSquare()[1] - 1].getClass() == BlankSquare.class){
                return true;
            }

            if (Objects.equals(pawn.color, "White") && pawn.rank == 2 && rankFinal == 4 && pawn.moveSquare()[1] == fileFinal && board[pawn.moveSquare()[0]][pawn.moveSquare()[1] - 1].getClass() == BlankSquare.class && board[pawn.moveSquare()[0] - 1][pawn.moveSquare()[1] - 1].getClass() == BlankSquare.class){
                return true;
            }

            if (Objects.equals(pawn.color, "Black") && pawn.rank == 7 && rankFinal == 5 && pawn.moveSquare()[1] == fileFinal && board[pawn.moveSquare()[0] - 2][pawn.moveSquare()[1] - 1].getClass() == BlankSquare.class && board[pawn.moveSquare()[0] - 1][pawn.moveSquare()[1] - 1].getClass() == BlankSquare.class){
                return true;
            }

            if (pawn.isAttackingSquare(rankFinal, fileFinal) && board[rankFinal - 1][fileFinal - 1].color.equals(pawn.invertedColor())){
                return true;
            }
        }

        if (figure.getClass() == Knight.class){
            if (board[rankFinal - 1][fileFinal - 1].getClass() == BlankSquare.class || Objects.equals(board[rankFinal - 1][fileFinal - 1].color, figure.invertedColor())){
                return figure.isAttackingSquare(rankFinal, fileFinal);
            }
        }

        if (figure.getClass() == King.class){
            if (board[rankFinal - 1][fileFinal - 1].getClass() == BlankSquare.class || Objects.equals(board[rankFinal - 1][fileFinal - 1].color, figure.invertedColor())){
                return figure.isAttackingSquare(rankFinal, fileFinal);
            }
        }


        if (figure.getClass() == Bishop.class){
            Bishop bishop = (Bishop) figure;

            for (int[] square: bishop.pathToSquare(rankFinal, fileFinal)){
                if (!isBlank(square)){
                    return false;
                }
            }

            if (board[rankFinal - 1][fileFinal - 1].getClass() == BlankSquare.class || Objects.equals(board[rankFinal - 1][fileFinal - 1].color, bishop.invertedColor())){
                return bishop.isAttackingSquare(rankFinal, fileFinal);
            }
        }

        if (figure.getClass() == Rook.class){
            Rook rook = (Rook) figure;

            for (int[] square: rook.pathToSquare(rankFinal, fileFinal)){
                if (!isBlank(square)){
                    return false;
                }
            }

            if (board[rankFinal - 1][fileFinal - 1].getClass() == BlankSquare.class || Objects.equals(board[rankFinal - 1][fileFinal - 1].color, rook.invertedColor())){
                return rook.isAttackingSquare(rankFinal, fileFinal);
            }
        }

        if (figure.getClass() == Queen.class){
            Queen queen = (Queen) figure;

            for (int[] square: queen.pathToSquare(rankFinal, fileFinal)){
                if (isBlank(square)){
                    return false;
                }
            }

                if (board[rankFinal - 1][fileFinal - 1].getClass() == BlankSquare.class || Objects.equals(board[rankFinal - 1][fileFinal - 1].color, queen.invertedColor())){
                return queen.isAttackingSquare(rankFinal, fileFinal);
            }
        }

        return false;
    }



    private boolean isBlank(int rank, int file){
        if (rank < 1 || rank > 8 || file < 1 || file >8){
            throw new IllegalArgumentException("Координаты клетки за пределами доски");
        }

        return board[rank - 1][file - 1].getClass() == BlankSquare.class;
    }

    private boolean isBlank(int[] square){
        if (square.length != 2){
            throw new IllegalArgumentException("Количество координат клетки должно быть ровно 2");
        }

        return board[square[0] - 1][square[1] - 1].getClass() == BlankSquare.class;
    }

    public void placePiece(Class<? extends Piece> piece, int rank, int file, String color){
        if (piece == Piece.class){
            throw new IllegalArgumentException("Невозможно создать фигуру типа Piece");
        }

        if (piece == Pawn.class){
            board[rank - 1][file - 1] = new Pawn(rank, file, color);
        }

        if (piece == Knight.class){
            board[rank - 1][file - 1] = new Knight(rank, file, color);
        }

        if (piece == Bishop.class){
            board[rank - 1][file - 1] = new Bishop(rank, file, color);
        }

        if (piece == Rook.class){
            board[rank - 1][file - 1] = new Rook(rank, file, color);
        }

        if (piece == Queen.class){
            board[rank - 1][file - 1] = new Queen(rank, file, color);
        }

        if (piece == King.class){
            if (hasKingOnBoard(color)){
                throw new IllegalArgumentException("Король такого цвета уже есть на доске");
            }
            board[rank - 1][file - 1] = new King(rank, file, color);
        }
    }

    private boolean hasKingOnBoard(String color){
        if (!Objects.equals(color, "Black") && !Objects.equals(color, "White")){
            throw new IllegalArgumentException("Фигура может быть только белого или чёрного цвета");
        }

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board[i][j].getClass() == King.class && board[i][j].color == color){
                    return true;
                }
            }
        }

        return false;
    }

    private void changeTurn(){
        if (turn.equals("White")){
            turn = "Black";
        }else{
            turn = "White";
        }
    }

    public void print(){
        final String RESET = "\u001B[0m";
        final String BLUE = "\u001B[34m";
        final String RED = "\u001B[31m";
        System.out.println("- 1 2 3 4 5 6 7 8 -");
        for (int i = 0; i < 8; i++){
            System.out.print(i + 1);
            System.out.print(" ");
            for(int j = 0; j < 8; j++){
                if(board[i][j].getClass() == BlankSquare.class){
                    System.out.print("-");
                }

                if(board[i][j].color.equals("White")){
                    System.out.print(BLUE);
                }

                if(board[i][j].color.equals("Black")){
                    System.out.print(RED);
                }

                if(board[i][j].getClass() == Pawn.class){
                    System.out.print("p");
                }

                if(board[i][j].getClass() == Bishop.class){
                    System.out.print("b");
                }

                if(board[i][j].getClass() == Knight.class){
                    System.out.print("k");
                }

                if(board[i][j].getClass() == Rook.class){
                    System.out.print("r");
                }

                if(board[i][j].getClass() == Queen.class){
                    System.out.print("Q");
                }

                if(board[i][j].getClass() == King.class){
                    System.out.print("K");
                }
                System.out.print(" ");
                System.out.print(RESET);
            }
            System.out.print(" ");
            System.out.print(i + 1);
            System.out.println();
        }
        System.out.println("- 1 2 3 4 5 6 7 8 -");
        System.out.println();
        System.out.println();
    }
}
