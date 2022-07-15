import java.sql.Array;
import java.util.Arrays;

class ArrayOperations {

    public static int[][][] createCube() {
        int[][][] array = new int[3][3][3];
        int[] inside = new int[]{1, 2, 3};

        for (int i = 0; i < 3; i++) {
            int n = 0;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++, n++) {
                    array[i][j][k] = n;
                }
            }
        }
        return array;
    }
}