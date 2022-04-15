package ma.enset.MyContacts;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private  ArrayList<Contact> contacts;


    CustomAdapter(Activity activity, Context context, ArrayList contacts){
        this.activity = activity;
        this.context = context;
        this.contacts=contacts;
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
