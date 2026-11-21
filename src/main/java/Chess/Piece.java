package Chess;

import java.util.ArrayList;
import java.util.List;

abstract class Piece{
    int rank;
    int file;
    String color;

    Piece(int rank, int file, String color){
        if (rank < 1 || rank > 8 || file < 1 || file >8){
            throw new IllegalArgumentException("Координаты фигуры должны быть между 1 и 8 включительно");
        }

        this.rank = rank;
        this.file = file;
        this.color = color;
    }

    boolean isAttackingSquare(int rank, int file){
        if (rank < 1 || rank > 8 || file < 1 || file >8){
            throw new IllegalArgumentException("Координаты поля должны быть между 1 и 8 включительно");
        }

        return contains(listOfAttackingSquares(), new int[]{rank, file});
    }

    List<int[]> listOfAttackingSquares(){
        return new ArrayList<>();
    }

    void move(int rank, int file){
        this.rank = rank;
        this.file = file;
    }

    String invertedColor(){
        if (color.equals("Black")){
            return "White";
        }
        if (color.equals("White")){
            return "Black";
        }
        return "";
    }

    static boolean contains (List<int[]> list, int[] arr){
        boolean temp = false;
        for (int[] temp_arr : list){
            temp = temp_arr[0] == arr[0] && temp_arr[1] == arr[1];
            if (temp){
                return temp;
            }
        }
        return temp;
    }
}