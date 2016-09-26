package core.demo.frag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DrkCore
 * @since 2016-09-27
 */
public class FragFactory {

	public static List<Class> getFrags(){
		List<Class> frags = new ArrayList<>();
		frags.add(OverWatchFrag.class);
		frags.add(SortFrag.class);
		frags.add(WaveFrag.class);
		return frags;
	}
}
