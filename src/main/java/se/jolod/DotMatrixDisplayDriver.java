package se.jolod;

class DotMatrixDisplayDriver {
    private final boolean[][] buffer;

    public DotMatrixDisplayDriver(int height, int width) {
        buffer = new boolean[height][width];
    }

    public void update(boolean[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                buffer[row][col] = matrix[row][col];
            }
        }
        flush();
    }

    private void flush() {
        for (int row = 0; row < buffer.length; row++) {
            for (int col = 0; col < buffer[row].length; col++) {
                System.out.print(buffer[row][col] ? "#" : " ");
            }
            System.out.println();
        }
    }
}
