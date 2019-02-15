package kz.uib.greencard.ui.qr;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kz.uib.greencard.base.BaseAdapter;
import kz.uib.greencard.repository.model.ComboQr;

public class ComboDialogAdapter extends BaseAdapter<ComboDialogAdapter.ComboViewHolder, ComboQr> {

    public ComboDialogAdapter(@NonNull List<ComboQr> items) {
        super(items);
    }

    @NonNull
    @Override
    public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new ComboViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComboViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.textView.setText(getItem(position).getComboName());
        holder.itemView.setTag(getItem(position).getId());
    }

    @Override
    public void onClick(View v) {
        Log.e("AAA", "onClick: ");
    }

    class ComboViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ComboViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }


}
