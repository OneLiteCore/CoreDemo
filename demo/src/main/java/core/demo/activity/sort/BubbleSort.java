package core.demo.activity.sort;

public class BubbleSort extends AbsSort {

    @Override
    protected void doSort(int[] array) {
        for (int i = 0, len = array.length; i < len; i++) {
            for (int j = 1; j < len - i; j++) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;

                    addFrame(array);
                }
            }
        }
    }

}
