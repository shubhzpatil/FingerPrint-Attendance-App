package com.example.mega2.FingerPrint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class ModifyAdapter extends RecyclerView.Adapter<ModifyAdapter.Holder> implements  Filterable  {

    Context context;
    ArrayList<MyPojo> arrayList;
    String data;

    ModifyAdapter(Context context, ArrayList<MyPojo> arrayList ,String data) {
        this.context = context;
        this.arrayList = arrayList;
        this.data=data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Holder holder;
        View view = LayoutInflater.from(context).inflate(R.layout.existing_user_report, parent, false);
        holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final MyPojo pojo= arrayList.get(position);
        holder.uid.setText(pojo.getUserid());
        holder.name.setText(pojo.getName());
        if(pojo.getDate().trim().equals(""))
        {}else {
            holder.date_of_birth.setText(pojo.getDate());
        }
        holder.fcnt.setText(String.valueOf(pojo.getfcnt()));
        if (data!=null){holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }else {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Newuser.class);
                    i.putExtra("data", pojo);
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView uid, name,date_of_birth,fcnt;
        CardView card;

        public Holder(View itemView) {
            super(itemView);
            uid = (TextView) itemView.findViewById(R.id.uid);
            name = (TextView) itemView.findViewById(R.id.name);
            date_of_birth=(TextView) itemView.findViewById(R.id.date_of_birth);
            fcnt=(TextView)itemView.findViewById(R.id.fcnt);
            card = (CardView) itemView.findViewById(R.id.cardview);
        }

        @Override
        public void onClick(View view) {  }
    }
    @Override
    public Filter getFilter() {
        DataFilter filter = null;
        if (filter == null)
            filter = new DataFilter();
        return filter;
    }

    ArrayList<MyPojo> datavalues;

    private class DataFilter extends Filter {
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub
            arrayList = (ArrayList<MyPojo>) results.values;
            notifyDataSetChanged();
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults r = new FilterResults();
            if (datavalues == null) {
                synchronized (arrayList) {
                    datavalues = new ArrayList<>(arrayList);
                }
            }
            if (constraint == null || constraint.length() == 0) {
                synchronized (arrayList) {
                    ArrayList<MyPojo> list = new ArrayList<>(datavalues);
                    r.values = list;
                    r.count = list.size();
                }
            } else {
                ArrayList<MyPojo> values = datavalues;
                int count = values.size();
                ArrayList<MyPojo> list = new ArrayList<>();
                String prefix = constraint.toString().toLowerCase();
                for (int i = 0; i < count; i++) {
                    if ((values.get(i).name.toLowerCase().contains(prefix))||(values.get(i).userid.toLowerCase().contains(prefix))) {
                        list.add(values.get(i));
                    }
                }
                r.values = list;
                r.count = list.size();
            }
            return r;
        }
    }
}