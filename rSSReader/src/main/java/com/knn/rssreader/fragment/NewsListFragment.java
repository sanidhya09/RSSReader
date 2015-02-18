package com.knn.rssreader.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knn.rssreader.R;
import com.knn.rssreader.adapter.CardViewAdapter;
import com.knn.rssreader.adapter.News;
import com.knn.rssreader.adapter.NewsHeadlinesAdapter;
import com.knn.rssreader.core.MyApplication;
import com.knn.rssreader.databases.CreateNewsListTable;
import com.knn.rssreader.interfaces.ChangeFragmentListener;
import com.knn.rssreader.interfaces.OnItemTouchListener;
import com.knn.rssreader.network.MyEventCallBack;
import com.knn.rssreader.network.MyResponse;
import com.knn.rssreader.network.NetworkStateReceiver.NetworkStateChanged;
import com.knn.rssreader.network.RestApiConfiguration;
import com.knn.rssreader.utility.BusProvider;
import com.knn.rssreader.utility.Constants;
import com.knn.rssreader.utility.Utility;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    protected MyApplication myApplication;
    private FragmentActivity activity;
    private NewsListFragment context;
    private RecyclerView mRecyclerView;
    private CardViewAdapter mAdapter;
    private List<News> newsList;
    private CreateNewsListTable createNewsListTable;
    private ChangeFragmentListener i_ChangeFragmentListener;
    private Cursor newsListCursor;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        context = NewsListFragment.this;
        BusProvider.getInstance().register(context);
        myApplication = (MyApplication) activity.getApplication();
        i_ChangeFragmentListener = (ChangeFragmentListener) activity;
        createNewsListTable = new CreateNewsListTable(activity);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.knn.rssreader.R.layout.frag_news_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        View view = getView();
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        ListItemTouchListener listItemTouchListener = new ListItemTouchListener();
        newsList = new ArrayList<News>();
        mAdapter = new CardViewAdapter(listItemTouchListener, activity);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        createNewsListTable.open();
        newsListCursor = createNewsListTable.selectAll();
        if (newsListCursor != null && newsListCursor.getCount() > 0) {

            Cursor c = newsListCursor;
            c.moveToFirst();
            RestApiConfiguration.getInstance().getNewsHeadlines(
                    new MyEventCallBack<NewsHeadlinesAdapter>(myApplication, Constants.SERVICE_MODE.NEWSLIST, true, false,
                            Constants.NetworkLoaderMessage.LOADING_NEWS_MSG));
            do {
                // do what you need with the cursor here

                News news = new News();

                String newsId = c.getString(c.getColumnIndexOrThrow(CreateNewsListTable.TB_NEWSID));
                String headLine = c.getString(c.getColumnIndexOrThrow(CreateNewsListTable.TB_HEADLINE));
                String thumbnail = c.getString(c.getColumnIndexOrThrow(CreateNewsListTable.TB_THUMBNAIL));
                String original = c.getString(c.getColumnIndexOrThrow(CreateNewsListTable.TB_ORIGINAL));
                String time = c.getString(c.getColumnIndexOrThrow(CreateNewsListTable.TB_TIME));

                news.newsid = newsId;
                news.headline = headLine;
                news.thumbnail = thumbnail;
                news.original = original;
                news.time = time;

                newsList.add(news);

            } while (c.moveToNext());


            mAdapter.addAll(newsList);
        } else {
            RestApiConfiguration.getInstance().getNewsHeadlines(
                    new MyEventCallBack<NewsHeadlinesAdapter>(myApplication, Constants.SERVICE_MODE.NEWSLIST, true, true,
                            Constants.NetworkLoaderMessage.LOADING_NEWS_MSG));
        }

    }

    @Subscribe
    public void getResponse(MyResponse<?> myResponse) {
        // switch (myResponse.mode)
        // {
        // case Constants.SERVICE_MODE.NEWSLIST:
        //
        // break;
        //
        // default:
        // break;
        // }
        if(createNewsListTable.selectAll().getCount()>0){
            CreateNewsListTable.truncate();
        }
        NewsHeadlinesAdapter newsHeadlinesAdapter = (NewsHeadlinesAdapter) myResponse.responseFromServer;
        newsList = newsHeadlinesAdapter.news;

        for (News news : newsList) {

            createNewsListTable.insert(news.newsid, news.original, news.thumbnail, news.headline, news.time);
        }

        mAdapter.addAll(newsList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {

        try {
            BusProvider.getInstance().unregister(context);
        } catch (Exception ex) {
        }
        super.onDestroy();
    }

    // method that will be called when NetworkStateChanged
    @Subscribe
    public void onNetworkStateChanged(NetworkStateChanged event) {
        if (null != activity) {
            if (event.isInternetConnected()) {
                // perform some network operation
                // Toast.makeText(activity, " Internet connection!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onRefresh() {
        RestApiConfiguration.getInstance().getNewsHeadlines(
                new MyEventCallBack<NewsHeadlinesAdapter>(myApplication, Constants.SERVICE_MODE.NEWSLIST, true, false,
                        Constants.NetworkLoaderMessage.LOADING_NEWS_MSG));
    }

    class ListItemTouchListener implements OnItemTouchListener {

        @Override
        public void onCardViewTap(View view, int position) {
            Bundle bundle = Utility.bundle(new String[]{"newsID"}, "" + newsList.get(position).newsid);
            i_ChangeFragmentListener.onChangeFragment(Constants.ChangeFragments.NEWS_DETAIL_FREAGMENT, bundle);
        }

        @Override
        public void onButton1Click(View view, int position) {

        }

    }

}
