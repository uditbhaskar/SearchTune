package com.example.searchtune.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.searchtune.R;
import com.example.searchtune.services.model.Root;
import com.example.searchtune.view.adapters.SearchListAdapter;
import com.example.searchtune.viewModel.TunesInfoViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText et_search;
    ImageView btn_iv_search;
    String searchQuery;
    TunesInfoViewModel infoViewModel;
    SearchListAdapter searchListAdapter;
    FrameLayout frameLayout_No_result;
    FrameLayout frameLayout_progress_bar;
    FrameLayout frameLayoutEmptyResult;
    Boolean noDataInDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        searchListAdapter = new SearchListAdapter(MainActivity.this);
        recyclerView.setAdapter(searchListAdapter);
    }

    private void displaySearchedItems(String searchQuery) {


        infoViewModel.setTunesInfo(searchQuery, MainActivity.this);
        infoViewModel.songsInfo.observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                if (root == null) {
                    fetchFromDB(searchQuery);
                    frameLayout_progress_bar.setVisibility(View.GONE);


                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    if (root.getResultCount() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        frameLayout_progress_bar.setVisibility(View.GONE);
                        frameLayout_No_result.setVisibility(View.GONE);
                        searchListAdapter.setItems(root.getResults());

                    } else {
                        recyclerView.setVisibility(View.GONE);
                        frameLayout_progress_bar.setVisibility(View.GONE);
                        frameLayout_No_result.setVisibility(View.VISIBLE);
                        et_search.setText("");
                    }
                }
            }
        });
    }

    public void dismissKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void init() {

        recyclerView = findViewById(R.id.favRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        et_search = findViewById(R.id.et_search);
        btn_iv_search = findViewById(R.id.btn_iv_search);
        frameLayout_No_result = findViewById(R.id.frame_layout_no_result);
        infoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(TunesInfoViewModel.class);
        frameLayout_progress_bar = findViewById(R.id.frame_layout_progress);
        frameLayoutEmptyResult = findViewById(R.id.frame_layout_empty_screen);
        btn_iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayoutEmptyResult.setVisibility(View.GONE);
                searchQuery = et_search.getText().toString();
                displaySearchedItems(searchQuery);
                frameLayout_progress_bar.setVisibility(View.VISIBLE);
                dismissKeyboard();
            }
        });

    }

    public void fetchFromDB(String searchQuery) {
        infoViewModel.setDatabaseQuery(searchQuery, this);
        infoViewModel.songsInfoFromDatabase.observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                if (root != null) {
                    searchListAdapter.setItems(root.getResults());
                    frameLayout_No_result.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    et_search.setText("");
                    frameLayout_No_result.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }
        });

    }
}