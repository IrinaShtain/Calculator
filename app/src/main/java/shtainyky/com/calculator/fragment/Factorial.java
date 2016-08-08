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

import java.math.BigInteger;

import shtainyky.com.calculator.R;

public class Factorial extends Fragment {
    private View view;
    private EditText number_for_factorial;
    private Button button_calculate;
    private TextView factorial_result;

    public static Factorial newInstance() {
        Bundle args = new Bundle();
        Factorial fragment = new Factorial();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_factorial, container, false);

        number_for_factorial = (EditText)view.findViewById(R.id.list_of_pairs);
        factorial_result = (TextView)view.findViewById(R.id.longestSubList_result);

        button_calculate = (Button)view.findViewById(R.id.button_calculate);
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(number_for_factorial.getText().toString())) return;
                int number = Integer.parseInt(number_for_factorial.getText().toString());
                factorial_result.setText(String.valueOf(sumOfTheDigitsInTheNumber(factorial(number))));
            }
        });
        return view;
    }
    private static BigInteger factorial(int n)
    {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    private static int sumOfTheDigitsInTheNumber(BigInteger number)
    {
        String s = String.valueOf(number);
        char[] strings = s.toCharArray();
        int result = 0;
        for (int i = 0; i < strings.length ; i++) {
            result = result + Integer.parseInt(String.valueOf(strings[i]));
        }
        return result;
    }
}
