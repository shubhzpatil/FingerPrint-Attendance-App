package com.example.mega2.FingerPrint;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;

import static android.widget.Toast.LENGTH_SHORT;


public class Report_adapter extends RecyclerView.Adapter<Report_adapter.Holder> implements Filterable{

    Context context;
    ArrayList<MyPojo> arrayList;
    Button b1;
    Report_adapter(Context context, ArrayList<MyPojo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.report1, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        try {
            final MyPojo pojo= arrayList.get(position);
            holder.uid.setText(pojo.getUserid());
            holder.name.setText(pojo.getName());
            holder.is_sync.setText("P");
            if(pojo.getSync().charAt(0)=='N')
            {
                holder.uid.setTextColor(Color.BLACK);
                holder.name.setTextColor(Color.BLACK);
                holder.is_sync.setTextColor(Color.BLACK);
            }
            else {
            }
        }catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView  uid, date,is_sync,name;
        CardView card;


        public Holder(View itemView) {
            super(itemView);
            uid = (TextView) itemView.findViewById(R.id.uid_atd);
            name=(TextView)itemView.findViewById(R.id.name_atd);
            is_sync = (TextView) itemView.findViewById(R.id.is_sync);
            card=(CardView)itemView.findViewById(R.id.cardview);
        }

        @Override
        public void onClick(View view) {

        }
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
                    if ((values.get(i).userid.toLowerCase().contains(prefix))||(values.get(i).name.toLowerCase().contains(prefix))) {
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
