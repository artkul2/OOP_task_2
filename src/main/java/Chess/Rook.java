package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rook extends Piece{
    public Rook(int rank, int file, String color) {
        if (!Objects.equals(color, "Black") && !Objects.equals(color, "White")){
            throw new IllegalArgumentException("Фигура может быть только белого или чёрного цвета");
        }

        super(rank, file, color);
    }

    @Override
    List<int[]> listOfAttackingSquares(){
        List<int[]> list = new ArrayList<>();

        for(int i = 1; rank + i <= 8; i++){
            list.add(new int[]{rank + i, file});
        }

        for(int i = 1; rank - i >= 1; i++){
            list.add(new int[]{rank - i, file});
        }

        for(int i = 1; file + i <= 8; i++){
            list.add(new int[]{rank, file + i});
        }

        for(int i = 1; file - i >= 1; i++){
            list.add(new int[]{rank, file - i});
        }

        return list;
    }

    List<int[]> pathToSquare(int rank, int file){
        if (rank < 1 || rank > 8 || file < 1 || file >8){
            throw new IllegalArgumentException("Координаты клетки за пределами доски");
        }

        if (!contains(listOfAttackingSquares(), new int[]{rank, file})){
            throw new IllegalArgumentException("Не является битым полем фигуры");
        }

        List<int[]> list = new ArrayList<>();

        if (this.rank == rank){
            if (this.file > file){
                for (int i = 1; this.file - i > file; i++){
                    list.add(new int[]{this.rank, this.file - i - 1});
                }
            }

            if (this.file < file){
                for (int i = 1; this.file + i < file; i++){
                    list.add(new int[]{this.rank, this.file + i - 1});
                }
            }
        }

        if (this.file == file){
            if (this.rank > rank){
                for (int i = 1; this.rank - i > rank; i++){
                    list.add(new int[]{this.rank - i - 1, this.file});
                }
            }

            if (this.rank < rank){
                for (int i = 1; this.rank + i < rank; i++){
                    list.add(new int[]{this.rank + i - 1, this.file});
                }
            }
        }

        return list;
    }
}
