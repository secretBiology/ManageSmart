package com.secretbiology.managesmart.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.List;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

public class MediumFragmentAdapter extends RecyclerView.Adapter<MediumFragmentAdapter.Holder> {

    private List<ExpenseMedium> mediumList;
    private OnClick onMediumClick;

    MediumFragmentAdapter(List<ExpenseMedium> mediumList, OnClick onMediumClick) {
        this.mediumList = mediumList;
        this.onMediumClick = onMediumClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.medium_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.text.setText(mediumList.get(position).getName());
        final int res = getIcon(mediumList.get(position).getName());
        holder.icon.setImageResource(res);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMediumClick.clicked(holder.getAdapterPosition(), res);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mediumList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView text;

        Holder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.mf_icon);
            text = itemView.findViewById(R.id.mf_text);
        }
    }

    interface OnClick {
        void clicked(int position, int icon);
    }

    private int getIcon(String name) {
        switch (name.toLowerCase().trim()) {
            case "cash":
                return R.drawable.icon_note;
            case "debit card":
                return R.drawable.icon_card;
            case "credit card":
                return R.drawable.icon_card;
            case "internet banking":
                return R.drawable.icon_bank;
            default:
                return R.drawable.icon_money;
        }
    }
}
