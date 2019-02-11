package kz.uib.greencard.ui.companies;

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
import kz.uib.greencard.repository.model.Company;

public class CompanyAdapter extends BaseAdapter<CompanyAdapter.CompanyViewHolder, Company> {

    private Context mContext;
    public CompanyAdapter(@NonNull List<Company> items, Context context) {
        super(items);
        mContext = context;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_company, viewGroup, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Company company = getItem(position);
        holder.onBind(company);
    }

    class CompanyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ivComIcon)
        ImageView imageView;
        @BindView(R.id.tvComTitle)
        TextView textView;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void onBind(Company company){

            textView.setText(company.getName());
            Picasso.with(mContext)
                    .load(company.getIconUrl())
                    .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                    .centerInside()
                    .placeholder(R.drawable.image_place_holder)
                    .into(imageView);
        }
    }
}
