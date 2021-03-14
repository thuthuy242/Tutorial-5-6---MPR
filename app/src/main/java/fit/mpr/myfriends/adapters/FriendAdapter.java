package fit.mpr.myfriends.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fit.mpr.myfriends.R;
import fit.mpr.myfriends.models.Friend;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    private List<Friend> friends;

    public FriendAdapter(List<Friend> friends){
        this.friends= friends;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_friend, parent, false);

        return new FriendHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Friend friend = friends.get(position);

        holder.bind(friend);

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class FriendHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageButton btnSMS, btnCall, btnEmail, btnDelete, btnEdit;
        private Context context;

        public FriendHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            txtName = itemView.findViewById(R.id.txtName);
            btnSMS = itemView.findViewById(R.id.btnSMS);
            btnEmail = itemView.findViewById(R.id.btnEmail);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }

        public void bind(final Friend friend){
            txtName.setText(friend.getName());

            //handle click events
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + friend.getPhoneNo()));
                    context.startActivity(intent);
                }
            });

            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:" + friend.getEmail()));
                    context.startActivity(intent);
                }
            });

            btnSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:" + friend.getPhoneNo()));
                    context.startActivity(intent);
                }
            });

            //delete friend
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Confirm")
                            .setMessage("Are you sure to delete this friend?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int position = friends.indexOf(friend);
                                    friends.remove(friend);
                                    //notify the adapter that data changed
                                    //notifyDataSetChanged();
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();


                }
            });


        }
    }
}
