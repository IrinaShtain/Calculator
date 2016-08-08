package shtainyky.com.calculator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import shtainyky.com.calculator.R;

public class Palindrome extends Fragment {
    private View view;
    private EditText number_for_palindrome;
    private Button button_calculate;
    private TextView palindrome_result;


    public static Palindrome newInstance() {
        Bundle args = new Bundle();
        Palindrome fragment = new Palindrome();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_palindrome, container, false);
        number_for_palindrome = (EditText)view.findViewById(R.id.list_of_pairs);
        palindrome_result = (TextView)view.findViewById(R.id.palindrome_result);

        button_calculate = (Button)view.findViewById(R.id.button_calculate);
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(number_for_palindrome.getText().toString())) return;
                int number = Integer.parseInt(number_for_palindrome.getText().toString());
                if (isPalindrome(number))
                    palindrome_result.setText(String.valueOf("Palindrome"));
                else
                    palindrome_result.setText(String.valueOf("Not Palindrome"));

            }
        });
        return view;
    }
    private static boolean isPalindrome(int number) {
        String str = String.valueOf(number);
        StringBuilder strBuilder = new StringBuilder(str);
        strBuilder.reverse();
        return strBuilder.toString().equals(str);
    }
}
