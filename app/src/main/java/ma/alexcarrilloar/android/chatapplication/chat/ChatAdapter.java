package ma.alexcarrilloar.android.chatapplication.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import ma.alexcarrilloar.android.chatapplication.contactlist.adapters.ContactListAdapter;
import ma.alexcarrilloar.android.chatapplication.entities.ChatMessage;

/**
 * Created by alexcarrilloar on 9/26/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void add(ChatMessage msg){

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

