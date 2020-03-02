package com.example.ajkamal.mplay;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
public class BlankFragment extends ListFragment implements AdapterView.OnItemClickListener {
    public Button B1;
    public Button B2;
    public Player mPlayer = new Player();
    static int posi=0;
    ArrayList<String> al=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView lv;
    int tempp=3;
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        B1= (Button)view.findViewById(R.id.B1);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean connected=mPlayer.search(getContext());
                if (connected){
                    Toast.makeText(getContext(), "Internet", Toast.LENGTH_SHORT).show();
                    new Thread(new Runnable() {
                        public void run() {
                            String url="http://faculty.iiitd.ac.in/~mukulika/s1.mp3";
                            mPlayer.DownloadFiles(getContext(),url);
                        }
                    }).start();
                    Toast.makeText(getContext(), "Download Finished", Toast.LENGTH_SHORT).show();
                    String temp="sample3";
                    if(tempp==3) {
                        al.add(temp);
                        adapter.notifyDataSetChanged();
                        tempp=4;
                    }
                    else{
                        Toast.makeText(getContext(), "Already added!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        B2= (Button)view.findViewById(R.id.B2);
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent svc=new Intent(getActivity(),Player.class);
                mPlayer.stopService(svc);*/
                getActivity().stopService(new Intent(getActivity(),Player.class));

                //mPlayer.stopp();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        al.add("sample1");
        al.add("sample2");
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,al);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        posi=position;
        Log.i("dd","before"+posi);
        getContext().startService(new Intent(getContext(),Player.class));
    }

}
