package kz.uib.greencard.ui.main;

import android.content.Context;
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
import kz.uib.greencard.repository.model.Category;
import kz.uib.greencard.ui.menu.MenuActivity;

public class CategoryAdapter extends BaseAdapter<CategoryAdapter.CategoryViewHolder, Category> {

    private Context mContext;
    public CategoryAdapter(@NonNull List<Category> items, Context context) {
        super(items);
        mContext = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Category category = getItem(position);
        holder.onBind(category);

    }

    @Override
    public void onClick(View v) {

    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.mIconCat)
        ImageView mImage;
        @BindView(R.id.tvTitleCat)
        TextView mTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Category category){
            mTitle.setText(category.getName());
            Picasso.with(mContext)
                    .load(category.getIconUrl())
                    .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                    .centerInside()
                    .placeholder(R.drawable.image_place_holder)
                    .into(mImage);

        }
    }
}
