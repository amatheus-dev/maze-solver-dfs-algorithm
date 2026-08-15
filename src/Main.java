import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[][] matriz = {
                {0, 0, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int controw = -1;
        for (int[] rows : matriz){
            int contColums = -1;
            controw++;
            if (matriz.length - controw == 1){
                System.out.print("");
            }
            for (int columns : rows){
                contColums++;
                if (controw == 0 && contColums == 0){
                    System.out.print("S ");
                }
                System.out.print(columns);
                if(matriz.length - controw == 1 && rows.length - contColums == 1){
                    System.out.print(" E");
                }
                System.out.print("  ");
            }
            System.out.print("\n  ");
        }





        System.out.println();
    }
}