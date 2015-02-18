package com.knn.rssreader.adapter;

import java.util.List;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.knn.rssreader.R;
import com.knn.rssreader.interfaces.OnItemTouchListener;
import com.squareup.picasso.Picasso;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder>
{
	private List<News> news;
	private OnItemTouchListener onItemTouchListener;
    private Activity activity;

	public CardViewAdapter(OnItemTouchListener onItemTouchListener, Activity activity)
	{
		this.activity=activity;
		this.onItemTouchListener = onItemTouchListener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int i)
	{
		viewHolder.title.setText(""+news.get(i).headline);
        viewHolder.time.setText(""+news.get(i).time);
        Picasso.with(this.activity).load(""+news.get(i).thumbnail).into( viewHolder.image);
	}

	public void addAll(List<News> news)
	{
		this.news = news;
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount()
	{
		return news == null ? 0 : news.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder
	{
		private TextView title, time;
        private ImageView image;

		// private Button button1;

		public ViewHolder(View itemView)
		{
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.card_view_title);
            time = (TextView) itemView.findViewById(R.id.card_view_time);
            image = (ImageView) itemView.findViewById(R.id.imageView);
			// button1 = (Button) itemView.findViewById(R.id.card_view_button1);
			// button1.setOnClickListener(new View.OnClickListener() {
			// @Override
			// public void onClick(View v) {
			// onItemTouchListener.onButton1Click(v, getPosition());
			// }
			// });

			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					onItemTouchListener.onCardViewTap(v, getPosition());
				}
			});
		}
	}
}