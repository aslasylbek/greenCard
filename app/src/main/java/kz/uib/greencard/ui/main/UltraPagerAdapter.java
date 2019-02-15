package kz.uib.greencard.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kz.uib.greencard.R;
import kz.uib.greencard.repository.model.Combo;

public class UltraPagerAdapter extends LoopingPagerAdapter<Combo> {

    private static final int VIEW_TYPE_NORMAL = 100;
    private static final int VIEW_TYPE_SPECIAL = 101;

    private static final String TAG = "UltraPagerAdapter";
    private ComboClickListener listener;

    public UltraPagerAdapter(Context context, ComboClickListener listener) {
        super(context, new ArrayList<Combo>(), true);
        this.listener = listener;
    }

    @Override
    protected int getItemViewType(int listPosition) {
        return VIEW_TYPE_NORMAL;
    }


    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.layout_child, container, false);
    }

    @Override
    protected void bindView(View convertView, final int listPosition, int viewType) {
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onComboPageClicked(itemList.get(listPosition));
            }
        });
        ImageView imageView = convertView.findViewById(R.id.imageComboBox);
        Picasso.with(context)
                .load(itemList.get(listPosition).getIconUrl())
                .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                .placeholder(R.drawable.image_place_holder)
                .into(imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.pager_textview);
        textView.setText(String.valueOf(itemList.get(listPosition).getName()));
    }

    public interface ComboClickListener{
        void onComboPageClicked(Combo combo);
    }


}
