package com.example.ashish.passwardmanager;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ashish.passwardmanager.data.PasswordContract;
import com.example.ashish.passwardmanager.data.PasswordContract.PasswordEntry;

/**
 * Created by ashish on 5/7/2018.
 */

public class PasswordRecyclerViewAdaptor extends RecyclerView.Adapter<PasswordRecyclerViewAdaptor.MyViewHolder> {
    private Context ctx;
    private boolean showPassword = false;
    private Cursor cursor;

    public PasswordRecyclerViewAdaptor(Context ctx, Cursor cursor) {
        this.ctx = ctx;
        this.cursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(ctx).inflate(R.layout.pass_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       /* holder.username.setText("");
        holder.password.setText("");
        holder.account.setText("");
    */
        if (!cursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String usernameText = cursor.getString(cursor.getColumnIndex(PasswordEntry.COLUMN_USERNAME));
        String passwordText = cursor.getString(cursor.getColumnIndex(PasswordEntry.COLUMN_PASSWORD));
        String accountText = cursor.getString(cursor.getColumnIndex(PasswordEntry.COLUMN_ACCOUNT));
        // COMPLETED (6) Retrieve the id from the cursor and
        long id = cursor.getLong(cursor.getColumnIndex(PasswordEntry._ID));


        holder.username.setText(usernameText);

        holder.password.setText(passwordText);
        holder.account.setText(accountText);
        holder.itemView.setTag(id);
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous cursor first
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView username, password, account;
        Button showPass;

        public MyViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            password = itemView.findViewById(R.id.pass);
            account = itemView.findViewById(R.id.account);

        }
    }
}
