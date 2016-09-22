package core.demo.frag.sort;

public class ChoseSort extends AbsSort {

    @Override
    protected void doSort(int[] array) {
        for (int i = 0, len = array.length; i < len; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int tmp = array[minIdx];
                array[minIdx] = array[i];
                array[i] = tmp;
                addFrame(array);
            }
        }
    }

}
