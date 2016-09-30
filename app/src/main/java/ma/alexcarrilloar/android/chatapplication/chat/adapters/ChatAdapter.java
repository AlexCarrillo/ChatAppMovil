package ma.alexcarrilloar.android.chatapplication.chat.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ma.alexcarrilloar.android.chatapplication.R;
import ma.alexcarrilloar.android.chatapplication.entities.ChatMessage;

/**
 * Created by alexcarrilloar on 9/26/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<ChatMessage> chatMessages;

    public ChatAdapter(Context context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        String msg = chatMessage.getMsg();
        holder.txtMessage.setText(msg);
        int color = ContextCompat.getColor(context, R.color.color_chat_sender);
        int gravity = Gravity.RIGHT;

        if(!chatMessage.isSentByMe())
        {
            color = ContextCompat.getColor(context, R.color.color_chat_receiver);

            gravity = Gravity.LEFT;
        }

        holder.txtMessage.setBackgroundColor(color);
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams)holder.txtMessage.getLayoutParams();
        params.gravity = gravity;
        holder.txtMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public void add(ChatMessage msg){
        if (!chatMessages.contains(msg))
        {
            chatMessages.add(msg);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtMessage)
        TextView txtMessage;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

