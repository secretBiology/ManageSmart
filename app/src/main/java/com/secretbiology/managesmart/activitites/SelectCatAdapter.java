package com.secretbiology.managesmart.activitites;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.database.ExpenseCategory;

import java.util.List;

class SelectCatAdapter extends RecyclerView.Adapter<SelectCatAdapter.Holder> {

    private OnCategoryClick onCategoryClick;
    private List<ExpenseCategory> categoryList;
    private int selection = 0;

    SelectCatAdapter(List<ExpenseCategory> categoryList, OnCategoryClick onCategoryClick) {
        this.onCategoryClick = onCategoryClick;
        this.categoryList = categoryList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.select_cat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if (position < categoryList.size()) {
            ExpenseCategory cat = categoryList.get(position);
            holder.text.setText(cat.getName());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCategoryClick.categoryClicked(holder.getAdapterPosition());
                }
            });
        } else {
            holder.text.setText("Add New");
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCategoryClick.categoryClicked(-1);
                }
            });
        }
        if (position == selection) {
            holder.layout.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorAccent));
        } else {
            holder.layout.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.transparent));
        }
    }

    public void setSelection(int selection) {
        this.selection = selection;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoryList.size() + 1;
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView text;
        ConstraintLayout layout;

        Holder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.sci_icon);
            text = itemView.findViewById(R.id.sci_text);
            layout = itemView.findViewById(R.id.sci_layout);
        }
    }

    interface OnCategoryClick {
        void categoryClicked(int pos);
    }
}
