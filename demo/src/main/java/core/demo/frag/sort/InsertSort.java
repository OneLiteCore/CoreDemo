package core.demo.frag.sort;

public class InsertSort extends AbsSort {

    @Override
    protected void doSort(int[] array) {
        for (int i = 1, len = array.length; i < len; i++) {
            int value = array[i];
            for (int j = 0; j < i; j++) {
                if (array[j] > value) {
                    moveRight(array, j, i);
                    array[j] = value;

                    addFrame(array);
                    break;
                }
            }
        }
    }

    private void moveRight(int[] array, int start, int end) {
        for (int i = end; i - 1 >= start; i--) {
            array[i] = array[i - 1];
        }
    }

}
