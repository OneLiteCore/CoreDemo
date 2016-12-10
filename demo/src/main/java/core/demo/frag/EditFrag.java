package core.demo.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import core.demo.R;
import core.demo.frag.edit.EditableText;

/**
 * @author DrkCore
 * @since 2016-12-08
 */
public class EditFrag extends Fragment implements View.OnClickListener {
    
    private Button undoBtn;
    private Button redoBtn;
    private EditableText editText;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_edit, container, false);
        
        editText = (EditableText) view.findViewById(R.id.editableText_frag_edit);
        undoBtn = (Button) view.findViewById(R.id.button_frag_edit_undo);
        redoBtn = (Button) view.findViewById(R.id.button_frag_edit_redo);
        
        undoBtn.setOnClickListener(this);
        redoBtn.setOnClickListener(this);
        
        return view;
    }
    
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_frag_edit_undo:
                editText.undo();
                break;
            
            case R.id.button_frag_edit_redo:
                editText.redo();
                break;
            
        }
    }
}
