package kz.uib.greencard.ui.bills.combo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseAdapter;
import kz.uib.greencard.repository.model.Item;

public class BillComboAdapter extends BaseAdapter<BillComboAdapter.ComboViewHolder, Item> {

    private Context mContext;
    public BillComboAdapter(@NonNull List<Item> items, Context context) {
        super(items);
        mContext = context;
    }

    @NonNull
    @Override
    public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_combo, viewGroup, false);
        return new ComboViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComboViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Item transcript = getItem(position);
        holder.bind(transcript);
    }

    @Override
    public void onClick(View v) {

    }

    public class ComboViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView mTitle;
        @BindView(R.id.tv_description)
        TextView mDesc;
        @BindView(R.id.tv_access)
        TextView mAccess;
        @BindView(R.id.iv_thumbnail)
        ImageView mImage;

        public ComboViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Item repository) {
            mTitle.setText(repository.getProviderName());
            mDesc.setText(repository.getName());
            if(repository.getConfirmed()!=null) {
                if (repository.getConfirmed().equals("1")) {
                    mAccess.setText("Использовано");
                    //mAccess.setTextColor(mContext.getResources().getColor(R.color.redColor));
                    mAccess.setTextColor(Color.RED);
                } else {
                    mAccess.setText("Доступно");
                    mAccess.setTextColor(Color.GREEN);
                    //mAccess.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }
            }
            Picasso.with(mContext)
                    .load(repository.getPhoto())
                    .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                    .centerInside()
                    .placeholder(R.drawable.image_place_holder)
                    .into(mImage);

        }


    }
}
