package es.dev_burnchat.burnchat;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Alvaro on 26/1/16.
 */
public class InboxFragment extends ListFragment{
    protected List<ParseObject>mMessages;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView=inflater.inflate(R.layout.fragment_inbox,container,false);
        ProgressBar spinner=(ProgressBar) rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        return rootView;
     }
    MessageAdapter adapter=new MessageAdapter(
    getListView().getContext(),mMessages);
    //setListAdapter(adapter);



}

