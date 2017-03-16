package app.leiyu.com.weather;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.*;

import app.leiyu.com.weather.utill.GetData;


public class MainFragment extends Fragment {
    private List<String>datas=new ArrayList<>();
    private boolean flag=false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TextView tt=(TextView)view.findViewById(R.id.textView2);
        Mytask task=new Mytask();
        String []num={""};
        task.execute(num);
        try {
            Thread.sleep(5000);
        }catch(Exception e){}

        if(flag) {

            if (datas.size() == 1) {
                Toast.makeText(getActivity(), "check internet!", Toast.LENGTH_LONG).show();
                tt.setText("  Someting wrong in internet");
            } else {

                ListView list = (ListView) view.findViewById(R.id.list);
                list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas));
                tt.setText("Add datas");
            }

        }

        return view;
    }

    private  class  Mytask extends AsyncTask<String,Object,String>{


        @Override
        protected String doInBackground(String... params) {
            GetData dd=new GetData();
            datas=dd.deal_data(dd.get_data());
            flag=true;

            return  null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();


        }
    }
}