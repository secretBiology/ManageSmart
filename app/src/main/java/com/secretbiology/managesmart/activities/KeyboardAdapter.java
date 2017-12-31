package com.secretbiology.managesmart.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.managesmart.R;

import java.util.List;

public class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.Holder> {

    private List<String> keyList;
    private onKeyPress keyPress;

    KeyboardAdapter(List<String> keyList, onKeyPress keyPress) {
        this.keyList = keyList;
        this.keyPress = keyPress;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.amount_keyboard_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if (keyList.get(position).equals("enter")) {
            holder.image.setVisibility(View.VISIBLE);
            holder.text.setVisibility(View.GONE);
        } else {
            holder.image.setVisibility(View.GONE);
            holder.text.setVisibility(View.VISIBLE);
            holder.text.setText(keyList.get(position));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyPress.keyPressed(keyList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return keyList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text;
        ConstraintLayout layout;
        ImageView image;

        Holder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.aef_key_text);
            layout = itemView.findViewById(R.id.aef_key_layout);
            image = itemView.findViewById(R.id.aef_key_image);
        }
    }

    interface onKeyPress {
        void keyPressed(String key);
    }
}
