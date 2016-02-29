package es.dev_burnchat.burnchat;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class EditFriendsActivity extends Activity {


    private static final String TAG = "Error Users";

    List<ParseUser>mUsers;
    ArrayList<String> usernames;
    ArrayAdapter<String> adapter;
    ProgressBar spinner;
    private ParseRelation<ParseUser> mFriendsRelation;
    private ParseUser mCurrentUser;
    protected GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_grid);
        spinner=(ProgressBar) findViewById(R.id.progressBar);

        mGridView = (GridView)findViewById(R.id.friendGrid);
        mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);


        TextView emptyTextView =(TextView)findViewById(android.R.id.empty);
        mGridView.setEmptyView(emptyTextView);

    }


    @Override
    protected void onResume() {
        super.onResume();

        mCurrentUser = ParseUser.getCurrentUser();
        mFriendsRelation = mCurrentUser.getRelation(ParseConstants.FRIENDS_RELATION);

        setProgressBarIndeterminateVisibility(true);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.orderByAscending(ParseConstants.USERNAME);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                setProgressBarIndeterminateVisibility(false);

                if (e == null) {
                    // Success
                    mUsers = users;
                    String[] usernames = new String[mUsers.size()];
                    int i = 0;
                    for(ParseUser user : mUsers) {
                        usernames[i] = user.getUsername();
                        i++;
                    }

                    if (mGridView.getAdapter() == null) {
                        UserAdapter adapter = new UserAdapter(EditFriendsActivity.this,mUsers);

                        mGridView.setAdapter(adapter);
                    } else {

                        ((UserAdapter)mGridView.getAdapter()).refill(mUsers);
                    }

                    addFriendCheckmarks();
                }
                else {
                    Log.e(TAG, e.getMessage());
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditFriendsActivity.this);
                    builder.setMessage(e.getMessage())
                            .setTitle(R.string.error_message)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }



  /*  @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Context context = getApplicationContext();
        CharSequence text = "Amigo añadido!";
        CharSequence text2 = "Amigo borrado!";
        int duration = Toast.LENGTH_SHORT;

        if (getListView().isItemChecked(position)) {
            // add the friend
            mFriendsRelation.add(mUsers.get(position));
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            // remove the friend
            mFriendsRelation.remove(mUsers.get(position));
            Toast toast = Toast.makeText(context, text2, duration);
            toast.show();
        }

        mCurrentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });
    }
*/
    private void addFriendCheckmarks() {
        mFriendsRelation.getQuery().findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> friends, ParseException e) {
                if (e == null) {
                    // list returned - look for a match
                    for (int i = 0; i < mUsers.size(); i++) {
                        ParseUser user = mUsers.get(i);

                        for (ParseUser friend : friends) {
                            if (friend.getObjectId().equals(user.getObjectId())) {
                               mGridView.setItemChecked(i, true);
                            }
                        }
                    }
                    spinner.setVisibility(View.INVISIBLE);

                }
                else {
                    Log.e(TAG, e.getMessage());
                }
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
