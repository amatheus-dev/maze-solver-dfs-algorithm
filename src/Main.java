public class Main {
    public static void main(String[] args) {
        Labirinto labirinto = new Labirinto();
        labirinto.showLabirinto();
        boolean findTheX = labirinto.validBit(0, 0);
        if (findTheX){
            labirinto.showPathMaze();
        }
    }
}
class Labirinto {
    private final int[][] labirinto = {
            {2, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 1, 0, 1, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 3}
    };
    private final boolean[][] isVisited = new boolean[labirinto.length][labirinto[0].length];
    String[][] pathMaze = new String[labirinto.length][labirinto[0].length];
    public void showLabirinto() {
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
        System.out.println();
        for (int i = 0; i < isVisited.length; i++) {
            for (int j = 0; j < isVisited[i].length; j++) {
                isVisited[i][j] = false;
                pathMaze[i][j] = "  1  ";
            }
            System.out.print("\n");
        }

    }

    public boolean isValid(int rows, int column) {
        if (rows < labirinto.length && rows >= 0 && column < labirinto[rows].length && column >= 0) {
            if (labirinto[rows][column] == 0 || labirinto[rows][column] == 3) {
                return !isVisited[rows][column];
            }
        }
        return false;
    }
    public String[][] setPathMaze(int row, int column){
        pathMaze[row][column] = "  *  ";
        return pathMaze;
    }

    public void showPathMaze(){
        for(String[] row : pathMaze){
            for (String column : row){
                System.out.print(column);
            }
            System.out.print("\n");
        }
    }

    public boolean validBit(int rows, int column) {
        boolean findTheX;
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
            System.out.println(rows + ", " + column);
            return true;
        }
        else {
            if (isUpNeighbour) {
                isVisited[upSide][column] = true;
                findTheX = validBit(upSide, column);
                if(findTheX){
                    return true;
                }

            }
            if (isRightNeighbour) {
                isVisited[rows][rightSide] = true;
                findTheX = validBit(rows, rightSide);
                if(findTheX){
                    return true;
                }

            }
            if (isDownNeighbour) {
                isVisited[downSide][column] = true;
                findTheX = validBit(downSide, column);
                if (findTheX){
                    return true;
                }

            }
            if (isLeftNeighbour) {
                isVisited[rows][leftSide] = true;
                findTheX = validBit(rows, leftSide);
                if(findTheX){
                    return true;
                }
            }
            return false;
        }
    }
}