package com.mxx.multitypeadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 98179 on 2019/8/1.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.ItemViewHolder>{

    public interface IItem {
        // should directly return layout
        int getType();

        // if you want to the variable name in xml configurable, define following method
        int getVariableId();
    }

    private List<IItem> items = new ArrayList<>();

    @NonNull
    @Override
    public MultiTypeAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return ItemViewHolder.creat(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiTypeAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.bindTo(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public List<IItem> getItems() {
        return items;
    }

    public int findPos(IItem item) {
        return items.indexOf(item);
    }

    public void setItem(IItem item) {
        clearItems();
        addItem(item);
    }

    public void setItems(List<IItem> items) {
        clearItems();
        addItems(items);
    }

    public void addItem(IItem item) {
        items.add(item);
    }

    public void addItem(IItem item, int index) {
        items.add(index, item);
    }

    public void addItems(List<IItem> items) {
        this.items.addAll(items);
    }

    // we don't need updateItem() method
    // public void updateItem(item) {
    // }

    public int removeItem(IItem item) {
        int pos = findPos(item);
        items.remove(item);
        return pos;
    }

    public void clearItems() {
        items.clear();
    }



    static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding binding;

        public ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static ItemViewHolder creat(ViewGroup parent,int viewType){
            ViewDataBinding binding  = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),viewType,parent,false);
            return new ItemViewHolder(binding);
        }

        void bindTo(IItem item){
            binding.setVariable(item.getVariableId(),item);
            binding.executePendingBindings();
        }

    }
}
