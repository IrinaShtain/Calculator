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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shtainyky.com.calculator.R;

public class Pairs extends Fragment {
    private View view;
    private EditText list_of_pairs;
    private Button button_calculate;
    private TextView longestSubList_result;

    public static Pairs newInstance() {
        Bundle args = new Bundle();
        Pairs fragment = new Pairs();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pairs, container, false);
        list_of_pairs = (EditText)view.findViewById(R.id.list_of_pairs);
        longestSubList_result = (TextView)view.findViewById(R.id.longestSubList_result);

        button_calculate = (Button)view.findViewById(R.id.button_calculate);
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(list_of_pairs.getText().toString())) return;
                String list_of_pairs_str  = list_of_pairs.getText().toString();
                longestSubList_result.setText(String.valueOf(outputSubList(longestSubList(listOfPairs(list_of_pairs_str)))));
            }
        });
        return view;
    }
    private static List<Map<Integer, Integer>> listOfPairs(String string)
    {
        try {
            List<Map<Integer, Integer>> list = new ArrayList<>();
            String[] numbers = string.split(" ");
            for (int i = 0; i < numbers.length; i++) {
                if (i % 2 == 0) {
                    list.add(pair(Integer.parseInt(numbers[i]), Integer.parseInt(numbers[i + 1])));
                }
            }
            return list;
        }
        catch (Throwable throwable)
        {
            return null;
        }

    }
    //output sub-list
    private static String outputSubList(List<Map<Integer, Integer>> subList)
    {
        String s = "";
        if (subList != null) {
            for (int i = 0; i < subList.size(); i++) {
                for (Map.Entry entry : subList.get(i).entrySet()) {
                    int key = (int) entry.getKey();
                    int value = (int) entry.getValue();
                    s = s + "(" + key + ", " + value + ") ";
                }
            }
            return s;
        }
        else
            return "No list of specified order";
    }
    //return pair for list item
    private static Map<Integer, Integer> pair(int key, int value)
    {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
    /* return the longest sub-list that has the pairs sorted
     * by the first entry in ascending order by the second in descending
     * order
    */
    private static List<Map<Integer, Integer>> longestSubList(List<Map<Integer, Integer>> list)
    {
        if (list != null)
        {
            if (isCorrectOrder(list))return list;
            int listCapasity = list.size()- 1;
            while (listCapasity > 1)
            {
                for (int i = 0; i < list.size() + 1 - listCapasity; i++) {
                    List<Map<Integer, Integer>> subList = new ArrayList<>(listCapasity);
                    for (int j = i; j < listCapasity + i; j++) {
                        subList.add(list.get(j));
                    }
                    if (isCorrectOrder(subList)) return subList;
                }
                listCapasity--;
            }
        }

        return null;
    }
    /*
     * check list that has the pairs sorted by the first entry in ascending order
     * by the second in descending order
    */
    private static boolean isCorrectOrder(List<Map<Integer, Integer>> list)
    {
        if (list != null)
        {
            List<Integer> listOfKeys = new ArrayList<>(); //list of first item from the pair
            List<Integer> listOfValues = new ArrayList<>(); //list of second item from the pair

            for (int i = 0; i < list.size(); i++) {
                for (Map.Entry entry: list.get(i).entrySet()) {
                    int key = (int) entry.getKey();
                    listOfKeys.add(key);
                    int value = (int) entry.getValue();
                    listOfValues.add(value);
                }
            }

            return isInAscendingOrder(listOfKeys)&&isInDescendingOrder(listOfValues);
        }
        return false;

    }
    //check list that sorted in descending order
    private static boolean isInDescendingOrder(List<Integer> list)
    {
        for (int i = 0; i < list.size() - 1 ; i++) {
            if (list.get(i) < list.get(i+1)) return false;
        }
        return true;
    }
    //check list that sorted in ascending order
    private static boolean isInAscendingOrder(List<Integer> list)
    {
        for (int i = 0; i < list.size() - 1 ; i++) {
            if (list.get(i) > list.get(i+1)) return false;
        }
        return true;
    }
}
