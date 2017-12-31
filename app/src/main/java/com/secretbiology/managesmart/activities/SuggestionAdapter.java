package com.secretbiology.managesmart.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretbiology.managesmart.R;

import java.util.List;


public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.Holder> {

    private List<SuggestionModel> modelList;
    private setOnSuggestionClick suggestionClick;

    public SuggestionAdapter(List<SuggestionModel> modelList, setOnSuggestionClick suggestionClick) {
        this.modelList = modelList;
        this.suggestionClick = suggestionClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final SuggestionModel model = modelList.get(position);
        holder.amount.setText(String.valueOf(model.getAmount()));
        holder.header.setText(model.getHeader());
        holder.footer.setText(model.getFooter());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suggestionClick.suggestionClicked(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView amount, header, footer;
        ConstraintLayout layout;

        Holder(View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.sg_amount);
            header = itemView.findViewById(R.id.sg_header);
            footer = itemView.findViewById(R.id.sg_footer);
            layout = itemView.findViewById(R.id.sg_layout);
        }
    }

    interface setOnSuggestionClick {
        void suggestionClicked(SuggestionModel model);
    }
}
