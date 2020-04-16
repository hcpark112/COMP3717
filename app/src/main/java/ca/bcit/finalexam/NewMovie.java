package ca.bcit.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewMovie extends AppCompatActivity {

        private EditText title_editTxt;
        private EditText desc_editTxt;
        private EditText link_editTxt;
        private Button add;
        private Button catalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movie);

        title_editTxt = (EditText) findViewById(R.id.title);
        desc_editTxt = (EditText) findViewById(R.id.desc);
        link_editTxt = (EditText) findViewById(R.id.link);

        add = (Button) findViewById(R.id.add);
        catalogue = (Button) findViewById(R.id.catalogue);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                movie.setTitle(title_editTxt.getText().toString());
                movie.setDesc(desc_editTxt.getText().toString());
                movie.setLink(link_editTxt.getText().toString());

                new FirebaseHelper().addMovie(movie, new FirebaseHelper.DataStatus() {
                    @Override
                    public void isLoaded(List<Movie> movies, List<String> keys) {

                    }

                    @Override
                    public void isInserted() {
                        Toast.makeText(NewMovie.this,
                                "Your book has been successfully added to the catalogue.",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void isUpdated() {

                    }

                    @Override
                    public void isDeleted() {

                    }
                });
            }
        });
        catalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewMovie.this, HomeScreen.class);
                startActivity(i);
            }
        });
    }
}
