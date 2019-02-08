package kz.uib.greencard.ui.history.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kz.uib.greencard.R;
import kz.uib.greencard.repository.model.History;
import retrofit2.http.POST;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;
        private TextView mDate;
        private TextView mSum;
        private TextView mDiscount;
        private TextView mSale;
        private ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
            mDate = itemView.findViewById(R.id.tv_time);
            mSale = itemView.findViewById(R.id.tv_sale);
            mDiscount = itemView.findViewById(R.id.tv_discount);
            mSum = itemView.findViewById(R.id.tv_sum);
            imageView =  itemView.findViewById(R.id.historyImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MyTag data = (MyTag)v.getTag();
            listener.onItemClick(data);
        }
    }

    public interface HistoryClickListener{
        void onItemClick(MyTag data);
    }

    public class MyTag{
        private String id;
        private String isCombo;

        public MyTag(String id, String isCombo) {
            this.id = id;
            this.isCombo = isCombo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsCombo() {
            return isCombo;
        }

        public void setIsCombo(String isCombo) {
            this.isCombo = isCombo;
        }
    }

    private Context context;
    private List<History> arrayList;
    private HistoryClickListener listener;

    public ItemRecyclerViewAdapter(Context context, List<History> arrayList, HistoryClickListener listener) {
        this.context = context;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        History history = arrayList.get(position);
        holder.mTitle.setText(history.getCompanyName());
        holder.mDate.setText(history.getDate());
        holder.mDiscount.setText("-"+history.getAmountFinal()+"тг");
        if(history.getIsCombo().equals("1")){
            holder.mSum.setText("Комбо");
            holder.mSale.setText("");
        }
        else{
            holder.mSum.setText(history.getAmount()+"тг");
            holder.mSale.setText(history.getSalePercent()+"%");
        }
        holder.itemView.setTag(new MyTag(history.getId(), history.getIsCombo()));

        Picasso.with(context)
                .load(history.getCompanyIconUrl())
                .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                .centerInside()
                .placeholder(R.drawable.image_place_holder)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
