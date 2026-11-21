package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bishop extends Piece {
    public Bishop(int rank, int file, String color) {
        if (!Objects.equals(color, "Black") && !Objects.equals(color, "White")){
            throw new IllegalArgumentException("Фигура может быть только белого или чёрного цвета(Black, White)");
        }

        super(rank, file, color);
    }

    @Override
    List<int[]> listOfAttackingSquares(){
        List<int[]> list = new ArrayList<>();

        for(int i = 1; rank + i <= 8 && file + i <= 8; i++){
            list.add(new int[]{rank + i, file + i});
        }

        for(int i = 1; rank - i >= 1 && file + i <= 8; i++){
            list.add(new int[]{rank - i, file + i});
        }

        for(int i = 1; rank + i <= 8 && file - i >= 1; i++){
            list.add(new int[]{rank + i, file - i});
        }

        for(int i = 1; rank - i >= 1 && file - i >= 1; i++){
            list.add(new int[]{rank - i, file - i});
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

        if (rank > this.rank && file > this.file){
            for (int i = 1; this.rank + i < rank; i++){
                list.add(new int[]{this.rank + i, this.file + i});
            }
        }

        if(rank > this.rank && file < this.file){
            for (int i = 1; this.rank + i < rank; i++){
                list.add(new int[]{this.rank + i, this.file - i});
            }
        }

        if (rank < this.rank && file > this.file){
            for (int i = 1; this.rank - i > rank; i++){
                list.add(new int[]{this.rank - i , this.file + i});
            }
        }

        if (rank < this.rank && file < this.file){
            for (int i = 1; this.rank - i > rank; i++){
                list.add(new int[]{this.rank - i, this.file - i});
            }
        }

        return list;
    }
}
