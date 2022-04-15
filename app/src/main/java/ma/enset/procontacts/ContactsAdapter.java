package ma.enset.procontacts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private Activity activity;
    private  ArrayList<Contact> contacts;
    private  ArrayList<Contact> allcontacts;


    ContactsAdapter(Activity activity, Context context, ArrayList contacts){
        this.activity = activity;
        this.context = context;
        this.contacts=contacts;
        this.allcontacts=new ArrayList<>(contacts);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        holder.contact_id_txt.setText(String.valueOf(contact_id.get(position)));
        holder.contact_name_txt.setText(String.valueOf(contacts.get(position).getName()));
        holder.contact_job_txt.setText(String.valueOf(contacts.get(position).getJob()));
        holder.contact_phone_txt.setText(String.valueOf(contacts.get(position).getPhone()));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(contacts.get(position).getId()));
                intent.putExtra("name", String.valueOf(contacts.get(position).getName()));
                intent.putExtra("job", String.valueOf(contacts.get(position).getJob()));
                intent.putExtra("phone", String.valueOf(contacts.get(position).getPhone()));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Contact> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(allcontacts);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Contact item : allcontacts) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contacts.clear();
                contacts.addAll((ArrayList) results.values);
                notifyDataSetChanged();
            }
        };
    }





    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contact_id_txt, contact_name_txt, contact_job_txt, contact_phone_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_name_txt = itemView.findViewById(R.id.contact_name_txt);
            contact_job_txt = itemView.findViewById(R.id.contact_job_txt);
            contact_phone_txt = itemView.findViewById(R.id.contact_phone_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
