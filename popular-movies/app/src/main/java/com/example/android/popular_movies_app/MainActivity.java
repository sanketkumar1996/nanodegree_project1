package com.example.android.popular_movies_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.popular_movies_app.models.Movie;
import com.example.android.popular_movies_app.utils.Constants;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.BunldeCallback {

    private static final String DETAIL_FRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailActivityFragment(), DETAIL_FRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(Constants.MOVIE_TAG, movie);

            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, DETAIL_FRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .putExtra(Constants.MOVIE_TAG, movie);
            startActivity(intent);
        }
    }

}
/*<LinearLayout
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/movie_release_date"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:textColor="#333"
                    android:textSize="18sp" />

                <TextView
                    android:id="@+id/movie_rating"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:paddingTop="10dp"
                    android:textColor="#333" />

                <Button
                    android:id="@+id/favorite"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="10dp"
                    android:background="#009688"
                    android:padding="5dp"
                    android:text="@string/mark_favorite"
                    />

            </LinearLayout>*/
