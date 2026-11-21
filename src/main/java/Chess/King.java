package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece{
    public King(int rank, int file, String color) {
        if (!Objects.equals(color, "Black") && !Objects.equals(color, "White")){
            throw new IllegalArgumentException("Фигура может быть только белого или чёрного цвета");
        }

        super(rank, file, color);
    }

    @Override
    List<int[]> listOfAttackingSquares(){
        List<int[]> list = new ArrayList<>();

        if (rank + 1 <= 8){
            list.add(new int[]{rank + 1, file});

            if (file + 1 <= 8){
                list.add(new int[]{rank + 1, file + 1});
            }

            if (file - 1 >= 1){
                list.add(new int[]{rank + 1, file - 1});
            }
        }

        if (rank - 1 >= 1){
            list.add(new int[]{rank - 1, file});

            if (file + 1 <= 8){
                list.add(new int[]{rank - 1, file + 1});
            }

            if (file - 1 >= 1){
                list.add(new int[]{rank - 1, file - 1});
            }
        }

        if (file + 1 <= 8){
            list.add(new int[]{rank, file + 1});
        }

        if (file - 1 >= 1){
            list.add(new int[]{rank, file - 1});
        }

        return list;
    }
}
