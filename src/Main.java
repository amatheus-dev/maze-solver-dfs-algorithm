public class Main {
    public static void main(String[] args) {
        Labirinto.showLabirinto();
        boolean findTheX = Labirinto.cellValidation(2, 0);
        if (findTheX){
            Labirinto.showPathMaze();
        }
    }
}
class Labirinto {
    private static final int[][] labirinto = {
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
            {2, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 1, 0, 1, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 3, 1, 1, 1, 1}
    };
    private static final boolean[][] isVisited = new boolean[labirinto.length][labirinto[0].length];
    private static final String[][] pathMaze = new String[labirinto.length][labirinto[0].length];
    private static final int[] findExit = new int[2];

    public static void showLabirinto() {
        int controw = -1;
        for (int[] rows : labirinto) {
            controw++;
            if (labirinto.length - controw == 1) {
                System.out.print("");
            }
            for (int columns : rows) {
                System.out.print(columns);
                System.out.print("    ");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < isVisited.length; i++) {
            for (int j = 0; j < isVisited[i].length; j++) {
                isVisited[i][j] = false;
                pathMaze[i][j] = "wall";

            }
        }

    }
    public static void setPathMaze(int row, int column){
        pathMaze[row][column] = " *  ";
        if (labirinto[row][column] == 2){
            pathMaze[row][column] = "head";
        }
        if (labirinto[row][column] == 3){
            pathMaze[row][column] = "exit";
        }
    }
    public static boolean cellValidation(int rows, int column) {
        boolean isXfound;
        setPathMaze(rows, column);
        int upSide = rows - 1;
        int rightSide = column + 1;
        int downSide = rows + 1;
        int leftSide = column - 1;
        boolean isUpNeighbour = isValid(upSide, column);
        boolean isRightNeighbour = isValid(rows, rightSide);
        boolean isDownNeighbour = isValid(downSide, column);
        boolean isLeftNeighbour = isValid(rows, leftSide);
        if (labirinto[rows][column] == 3) {
            findExit[0] = rows;
            findExit[1] = column;
            return true;
        }
        else {
            if (isUpNeighbour) {
                isVisited[upSide][column] = true;
                isXfound = cellValidation(upSide, column);
                return isXfound;

            }
            if (isRightNeighbour) {
                isVisited[rows][rightSide] = true;
                isXfound = cellValidation(rows, rightSide);
                return isXfound;

            }
            if (isDownNeighbour) {
                isVisited[downSide][column] = true;
                isXfound = cellValidation(downSide, column);
                return isXfound;

            }
            if (isLeftNeighbour) {
                isVisited[rows][leftSide] = true;
                isXfound = cellValidation(rows, leftSide);
                return isXfound;
            }
            return false;
        }
    }
    public static boolean isValid(int rows, int column) {
        if (rows < labirinto.length && rows >= 0 && column < labirinto[rows].length && column >= 0) {
            if (labirinto[rows][column] == 0 || labirinto[rows][column] == 3) {
                return !isVisited[rows][column];
            }
        }
        return false;
    }
    public static void showPathMaze(){
        System.out.println();
        for(String[] row : pathMaze){
            for (String column : row){
                System.out.print(column + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\nSaída: " + findExit[0] + ", " + findExit[1]);
    }

}