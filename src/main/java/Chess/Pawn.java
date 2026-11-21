package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    public Pawn(int rank, int file, String color) {
        if (rank == 1 || rank == 8){
            throw new IllegalArgumentException("Пешка не может стоять на краевых горизонталях");
        }

        if (!Objects.equals(color, "Black") && !Objects.equals(color, "White")){
            throw new IllegalArgumentException("Фигура может быть только белого или чёрного цвета");
        }

        super(rank, file, color);
    }

    @Override
    List<int[]> listOfAttackingSquares(){
        List<int[]> list = new ArrayList<>();

        if (Objects.equals(this.color, "Black")){
            list.add(new int[]{this.rank - 1, this.file + 1});
            list.add(new int[]{this.rank - 1, this.file - 1});
        }else {
            list.add(new int[]{this.rank + 1, this.file + 1});
            list.add(new int[]{this.rank + 1, this.file - 1});
        }

        return list;
    }

    int[] moveSquare(){
        if (Objects.equals(this.color, "Black")){
            return new int[]{rank - 1, file};
        }else{
            return new int[]{rank + 1, file};
        }
    }
}
