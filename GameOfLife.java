public class GameOfLife {

    public static void main(String[] args) throws Exception {
        System.out.print("\033[?25l");

        char grid[][] = new char[20][20];

        grid[0][1] = '*';
        grid[1][2] = '*';
        grid[2][0] = '*';
        grid[2][1] = '*';
        grid[2][2] = '*';
        grid[7][8] = '*';
        grid[8][9] = '*';
        grid[9][8] = '*';
        grid[9][9] = '*';
        grid[9][10] = '*';
       

        

        while (true) {
            clearScreen();
            print(grid);
            int newgrid[][] = countedGrid(grid);
            update(newgrid, grid);
            Thread.sleep(100);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[2J\033[H");
    }

    public static void print(char grid[][]) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                char current = grid[i][j];
                System.out.print(current == '\u0000' ? " " : current);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static int[][] countedGrid(char grid[][]) {
        int count[][] = new int[20][20];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                count[i][j] = eightDirectionCount(grid, i, j);
            }
        }

        return count;
    }

    public static int eightDirectionCount(char grid[][], int i, int j) {
        int count = 0;
        int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni >= 0 && ni < 20 && nj >= 0 && nj < 20 && grid[ni][nj] == '*') {
                count++;
            }
        }

        return count;
    }

    public static void update(int ngrid[][], char fgrid[][]) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (ngrid[i][j] < 2 || ngrid[i][j] > 3) {
                    fgrid[i][j] = '\u0000';
                } else if (ngrid[i][j] == 3) {
                    fgrid[i][j] = '*';
                }
            }
        }
    }
}
