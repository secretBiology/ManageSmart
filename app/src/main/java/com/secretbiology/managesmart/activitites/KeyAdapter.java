package com.secretbiology.managesmart.activitites;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretbiology.managesmart.R;


public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.Holder> {

    public static final String EXTRA_KEY = "CLR";
    public static final String INITIAL_AMOUNT = "00.00";

    private onKeyClick keyClick;

    KeyAdapter(onKeyClick keyClick) {
        this.keyClick = keyClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.key_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        switch (position) {
            case 9:
                holder.text.setText(".");
                break;
            case 10:
                holder.text.setText("0");
                break;
            case 11:
                holder.text.setText(EXTRA_KEY);
                break;
            default:
                holder.text.setText(String.valueOf(position + 1));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyClick.keyClicked(holder.text.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text;
        ConstraintLayout layout;

        Holder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.ki_text);
            layout = itemView.findViewById(R.id.ki_layout);
        }
    }

    public interface onKeyClick {
        void keyClicked(String s);
    }
}
