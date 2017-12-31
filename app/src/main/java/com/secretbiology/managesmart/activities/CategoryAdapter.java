package com.secretbiology.managesmart.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.helpers.general.General;
import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.IconRelations;
import com.secretbiology.managesmart.database.ExpenseCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    private setOnCatClick onCatClick;
    private List<ExpenseCategory> categoryList;

    public CategoryAdapter(List<ExpenseCategory> categoryList, setOnCatClick onCatClick) {
        this.onCatClick = onCatClick;
        this.categoryList = categoryList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        ExpenseCategory category = categoryList.get(position);
        Animation pulse = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.pulse);

        holder.text.setText(category.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCatClick.clicked(holder.getAdapterPosition());
            }
        });

        if (position == categoryList.size() - 1) {
            holder.icon.startAnimation(pulse);
        } else {
            holder.icon.clearAnimation();
        }
        holder.icon.setImageResource(getIcon(category.getName()));

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView text;
        ConstraintLayout layout;

        Holder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.aef_cat_icon);
            text = itemView.findViewById(R.id.aef_cat_text);
            layout = itemView.findViewById(R.id.aef_cat_layout);
        }
    }

    interface setOnCatClick {
        void clicked(int position);
    }

    private int getIcon(String name) {
        for (IconRelations r : IconRelations.values()) {
            if (r.toString().toLowerCase().equals(name.toLowerCase().trim())) {
                return r.getIcon();
            }
            for (String s : r.getAssociation().split(",")) {
                if (s.toLowerCase().trim().equals(name.toLowerCase().trim())) {
                    return r.getIcon();
                }
            }
            for (String s : r.getAssociation().split(",")) {
                if (General.percentSimilarity(s.trim().toLowerCase(), name.trim().toLowerCase()) > 70) {
                    return r.getIcon();
                }
            }
        }
        return R.drawable.icon_unknwon;
    }
}
