package com.inreadyworkgroup.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.inreadyworkgroup.newsapp.Adapter.NewsAdapter;
import com.inreadyworkgroup.newsapp.Model.GetNews;
import com.inreadyworkgroup.newsapp.Model.News;
import com.inreadyworkgroup.newsapp.Rest.ApiClient;
import com.inreadyworkgroup.newsapp.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;

        mAdapter.setOnChangeItem(new NewsAdapter.lonceng() {
            @Override
            public void onClickItem(News data) {
                Intent keDetail = new Intent(MainActivity.this, DetailActivity.class);
                keDetail.putExtra("judul", data.getTitle());
                keDetail.putExtra("desc", data.getDescription());
                keDetail.putExtra("img", data.getUrlToImage());
                startActivity(keDetail);
            }
        });

        refresh();
    }

    public void refresh() {
        Call<GetNews> kontakCall = mApiInterface.getNews("id", "9e2edb9742b649c78eb9c1e40938b1b5");
        kontakCall.enqueue(new Callback<GetNews>() {
            @Override
            public void onResponse(Call<GetNews> call, Response<GetNews>
                    response) {
                List<News> KontakList = response.body().getArticles();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(KontakList.size()));

                mAdapter = new NewsAdapter(KontakList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetNews> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}