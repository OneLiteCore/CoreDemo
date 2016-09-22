package core.demo.frag.sort;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsSort {

    private List<int[]> frames = new ArrayList<>();

    protected final void addFrame(int[] array) {
        int len = array.length;
        int[] frame = new int[len];
        System.arraycopy(array, 0, frame, 0, len);
        this.frames.add(frame);
    }

    public final List<int[]> sort(int[] array) {
        frames.clear();
        doSort(array);
        return frames;
    }

    protected abstract void doSort(int[] array);

}
