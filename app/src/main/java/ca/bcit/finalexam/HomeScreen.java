package ca.bcit.finalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class HomeScreen extends AppCompatActivity {
    private RecyclerView rView;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_main);
        rView = (RecyclerView) findViewById(R.id.recyclerview);
        back = (Button) findViewById(R.id.back);
        new FirebaseHelper().readMovies(new FirebaseHelper.DataStatus() {
            @Override
            public void isLoaded(List<Movie> movies, List<String> keys) {
                findViewById(R.id.loading).setVisibility(View.GONE);
                new MyRecyclerView().setConfig(rView, HomeScreen.this, movies, keys);
            }

            @Override
            public void isInserted() {

            }

            @Override
            public void isUpdated() {

            }

            @Override
            public void isDeleted() {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
