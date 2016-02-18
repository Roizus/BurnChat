package es.dev_burnchat.burnchat;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvaro on 26/1/16.
 */
public class InboxFragment extends ListFragment{

    private ArrayList<String> messages;
    private ArrayAdapter adapter;
    protected List<ParseObject> mMessages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView=inflater.inflate(R.layout.fragment_inbox,container,false);
        ProgressBar spinner=(ProgressBar) rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setProgressBarIndeterminateVisibility(true);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(ParseConstants.CLASS_MESSAGES);
        query.whereEqualTo(ParseConstants.KEY_RECIPIENT_IDS, ParseUser.getCurrentUser().getObjectId());
        query.addDescendingOrder(ParseConstants.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> messages, ParseException e) {
                getActivity().setProgressBarIndeterminateVisibility(false);

                if (e == null) {
                    // We found messages!
                    mMessages = messages;

                    String[] usernames = new String[mMessages.size()];
                    int i = 0;
                    for(ParseObject message : mMessages) {
                        usernames[i] = message.getString(ParseConstants.KEY_SENDER_NAME);
                        i++;
                    }
                    if (getListView().getAdapter() == null) {
                        adapter=new
                                ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,messages);
                        setListAdapter(adapter);
                        setListAdapter(adapter);
                    }

                }
            }
        });
    }
    }

