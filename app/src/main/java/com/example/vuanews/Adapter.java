package com.example.vuanews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuanews.response.Article;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private Context context;
    private int layout;
    private List<Article> getArraylist;

    public Adapter(Context context, int layout, List<Article> getArraylist) {
        this.context = context;
        this.layout = layout;
        this.getArraylist = getArraylist;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.title.setText(getArraylist.get(position).getTitle());
        holder.subtitle.setText(getArraylist.get(position).getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Article article = getArraylist.get(position);
                Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("article",article);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getArraylist.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView title,subtitle;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.maintitle);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}
