package ca.bcit.finalexam;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private List<Movie> movies = new ArrayList<>();

    public interface DataStatus {
        void isLoaded(List<Movie> movies, List<String> keys);
        void isInserted();
        void isUpdated();
        void isDeleted();
    }

    public FirebaseHelper() {
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("Movies");
    }

    public void readMovies(final DataStatus status) {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                movies.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot key: dataSnapshot.getChildren()) {
                    keys.add(key.getKey());
                    Movie movie = key.getValue(Movie.class);
                    movies.add(movie);
                }
                status.isLoaded(movies, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addMovie(Movie movie, final DataStatus status) {
        String key = databaseRef.push().getKey();
        databaseRef.child(key).setValue(movie)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        status.isInserted();
                    }
                });
    }
}
