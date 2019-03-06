package com.chemutai.personal.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chemutai.personal.R;
import com.chemutai.personal.models.Route;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomRouteAdapter  extends RecyclerView.Adapter<CustomRouteAdapter.MyViewHolder>{
    public  int selected=-1;
    Context mContext;
    ArrayList<Route> list;

    public CustomRouteAdapter(Context context, ArrayList<Route> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.route_item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Route r= list.get(position);
        holder.txtRouteName.setText(r.getName());
        holder.txtRouteCost.setText("KES "+r.getCost());
        holder.txtRouteTime.setText(r.getTime());
        holder.txtRouteSeats.setText(r.getSeats());
        holder.checkboxConfirm.setChecked(position==selected);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtRouteName)
        TextView txtRouteName;
        @BindView(R.id.txtRouteCost)
        TextView txtRouteCost;
        @BindView(R.id.txtRouteTime)
        TextView txtRouteTime;
        @BindView(R.id.txtRouteSeats)
        TextView txtRouteSeats;
        @BindView(R.id.checkboxConfirm)
        RadioButton checkboxConfirm;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            final View.OnClickListener listener=new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  selected=getAdapterPosition();
                  notifyDataSetChanged();
                  Route x= list.get(selected);

                }
            };
           checkboxConfirm.setOnClickListener(listener);
        }
    }
}
