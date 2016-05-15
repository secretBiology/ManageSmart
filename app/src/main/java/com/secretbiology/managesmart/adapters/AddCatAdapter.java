package com.secretbiology.managesmart.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.secretbiology.managesmart.R;

import java.util.List;

public class AddCatAdapter extends RecyclerView.Adapter<AddCatAdapter.MyViewHolder> {

    private List<String> entryList;
    View currentview;
    private ClickListener myClickListener;
    private addSubCat addSubCatListner;
    private textWatcher textWatcherListener;
    private upClick upClickListener;
    private downClick downClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public EditText editName;
        public ImageView closeButton, addButton, up, down;
        public LinearLayout holder;


        public MyViewHolder(final View view){
            super(view);
            currentview = view;
            editName = (EditText) view.findViewById(R.id.cat_add_name);
            closeButton = (ImageView) view.findViewById(R.id.cat_add_delete);
            addButton = (ImageView) view.findViewById(R.id.cat_add_item);
            up = (ImageView) view.findViewById(R.id.cat_Add_moveUp);
            down = (ImageView) view.findViewById(R.id.cat_add_moveDown);
            editName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    textWatcherListener.onTextChanged(s, getLayoutPosition());
                    entryList.set(getLayoutPosition(),s.toString());
                }
            });

            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upClickListener.onItemClick(getLayoutPosition(),editName);
                }
            });
            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downClickListener.onItemClick(getLayoutPosition(),editName);
                }
            });
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getLayoutPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public void setAddSubCatListener(addSubCat subCatListener){
        this.addSubCatListner = subCatListener;
    }

    public void onTextChange (textWatcher textWatcher){
        this.textWatcherListener = textWatcher;

    }
    public void onUpClick (upClick upClick){
        this.upClickListener = upClick;
    }
    public void onDownClick (downClick downClick){
        this.downClickListener = downClick;
    }



    public AddCatAdapter(List<String> entryList) {
        this.entryList = entryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_category_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String entry = entryList.get(position);
        holder.editName.setText(entry);
        holder.addButton.setVisibility(View.INVISIBLE);
        holder.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onItemClick(position,v);
            }
        });

        if(position==(entryList.size()-1)){
            holder.addButton.setVisibility(View.VISIBLE);
        }
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubCatListner.onItemClick(position, v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public interface addSubCat{
        void onItemClick (int position, View v);
    }

    public interface textWatcher {
        void onTextChanged(Editable charSequence,int position);
    }

    public interface upClick{
        void onItemClick(int position, View v);
    }

    public interface downClick{
        void onItemClick(int position, View v);
    }


}