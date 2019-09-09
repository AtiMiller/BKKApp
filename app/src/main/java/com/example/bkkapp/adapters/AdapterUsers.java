package com.example.bkkapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bkkapp.ChatActivity;
import com.example.bkkapp.R;
import com.example.bkkapp.models.ModelUsers;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder>{

    Context context;
    List<ModelUsers> userList;

    //constructor


    public AdapterUsers(Context context, List<ModelUsers> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout (row_user.xml)

        View view = LayoutInflater.from(context).inflate(R.layout.row_users,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //get data
        final String hisUID = userList.get(position).getUid();
        String userImage =userList.get(position).getImage();
        String userName =userList.get(position).getName();
        final String userEmail =userList.get(position).getEmail();

        //set data

        holder.mNameTv.setText(userName);
        holder.mEmailTv.setText(userEmail);

        try {

            Picasso.get().load(userImage)
                    .placeholder(R.drawable.ic_default_img_purple)
                    .into(holder.mAvatarIv);

        }catch (Exception e){

        }

        //handle item clicked

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, ChatActivity.class);
               intent.putExtra("hisUID", hisUID);
               context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    //ViewHolder class

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView mAvatarIv;
        TextView mNameTv, mEmailTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            mAvatarIv = itemView.findViewById(R.id.person_avatarIv);
            mNameTv = itemView.findViewById(R.id.person_nameTv);
            mEmailTv = itemView.findViewById(R.id.person_emailTv);
        }
    }
}
