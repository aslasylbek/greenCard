package kz.uib.greencard.ui.history.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kz.uib.greencard.R;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {


    class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionLabel;
        private RecyclerView itemRecyclerView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            sectionLabel =  itemView.findViewById(R.id.section_label);
            itemRecyclerView =  itemView.findViewById(R.id.item_recycler_view);
        }
    }

    private Context context;
    private List<SectionModel> sectionModelArrayList = new ArrayList<>();
    private ItemRecyclerViewAdapter.HistoryClickListener listener;

    public SectionRecyclerViewAdapter(Context context, ItemRecyclerViewAdapter.HistoryClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void changeData(List<SectionModel> newArray){
        sectionModelArrayList.clear();
        sectionModelArrayList.addAll(newArray);
        notifyDataSetChanged();
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_header, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        final SectionModel sectionModel = sectionModelArrayList.get(position);
        holder.sectionLabel.setText(sectionModel.getSectionLabel());

        //recycler view for items
        holder.itemRecyclerView.setHasFixedSize(true);
        holder.itemRecyclerView.setNestedScrollingEnabled(false);

        /* set layout manager on basis of recyclerview enum type */

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.itemRecyclerView.setLayoutManager(linearLayoutManager);

        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(context, sectionModel.getItemArrayList(), listener);
        holder.itemRecyclerView.setAdapter(adapter);

        //show toast on click of show all button


    }

    @Override
    public int getItemCount() {
        return sectionModelArrayList.size();
    }
}
