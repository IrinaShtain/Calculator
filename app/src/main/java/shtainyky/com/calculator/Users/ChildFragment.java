package shtainyky.com.calculator.Users;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import shtainyky.com.calculator.R;

public class ChildFragment extends Fragment {
    View view;
    EditText eName, eDate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child, null);
        eName = (EditText) view.findViewById(R.id.child_first_name);
        eDate = (EditText) view.findViewById(R.id.child_birthday);

        return view;
    }
    public EditText getNameChild()
    {
        return eName;
    }
    public EditText getBdayChild()
    {
        return eDate;
    }

}
