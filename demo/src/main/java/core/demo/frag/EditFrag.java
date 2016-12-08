package core.demo.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * @author DrkCore
 * @since 2016-12-08
 */
public class EditFrag extends Fragment {
    
    private EditText editText;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        editText = new EditText(getContext());
        
        
        return editText;
    }
}
