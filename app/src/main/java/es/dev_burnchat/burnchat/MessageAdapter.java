package es.dev_burnchat.burnchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Vaio on 05/02/2016.
 */
public class MessageAdapter extends ArrayAdapter<ParseObject> {
    protected Context mContext;
    protected List<ParseObject>mMessages;


    public MessageAdapter (Context context,List<ParseObject>messages){
        super(context,R.layout.message_item,messages);
        mContext=context;
        mMessages=messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.message_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.img_photo);
            holder.nameLabel = (TextView) convertView.findViewById(R.id.senderLabel);

        }
        else{
              holder=(ViewHolder)convertView.getTag();

        }
        ParseObject message=mMessages.get(position);

        if(message.getString(ParseConstants.KEY_FILE_TYPE).equals(ParseConstants.TYPE_IMAGE)){
            holder.iconImageView.setImageResource(R.drawable.ic_photo);
        } else{

            holder.iconImageView.setImageResource(R.drawable.ic_video);

        }
        holder.nameLabel.setText(message.getString(ParseConstants.KEY_SENDER_NAME));
        //holder.iconImageView.setImageResource(R.drawable.ic_photo);
        return convertView;
    }



    private static class ViewHolder{
        ImageView iconImageView;
        TextView nameLabel;
    }

}
