package es.dev_burnchat.burnchat;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class EditFriendsActivity extends ListActivity {


    private static final String TAG = "Error Users";

    List<ParseUser>mUsers;
    ArrayList<String> usernames;
    ArrayAdapter<String> adapter;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friends);
        spinner=(ProgressBar) findViewById(R.id.progressBar2);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //spinner.setVisibility(View.VISIBLE);
        usernames=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,usernames);
        setListAdapter(adapter);

        ParseQuery query= ParseUser.getQuery();
        query.orderByAscending(ParseConstants.USERNAME);
        query.setLimit(ParseConstants.MAX_USERS);
        query.findInBackground(new FindCallback<ParseUser>(){
            @Override
                    public void done(List<ParseUser>users,ParseException e){
                if(e==null){
//sucess
                    mUsers=users;
                    for(ParseUser user:mUsers){
                        adapter.add(user.getUsername());
                    }
                }
                else{
                    Log.e(TAG,"ParseExceptioncaught:",e);
                    errorEditFriendsdDialog(getString(R.string.error_message));
                }
                spinner.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void errorEditFriendsdDialog(String string) {
        AlertDialog.Builder builder=new AlertDialog.Builder(EditFriendsActivity.this);
        String message = string;
        builder.setMessage(message);
        builder.setTitle("Error Friends Dialog");
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


}
