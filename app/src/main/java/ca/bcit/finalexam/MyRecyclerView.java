package ca.bcit.finalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerView {
    private Context context;
    private MovieAdapter adapter;

    public void setConfig(RecyclerView rView, Context context, List<Movie> movies, List<String> keys) {
        this.context = context;
        adapter = new MovieAdapter(movies, keys);
        rView.setLayoutManager(new LinearLayoutManager(context));
        rView.setAdapter(adapter);
    }

    class MovieItemView extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private TextView link;

        private String key;

        public MovieItemView(ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false));

            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            link = (TextView) itemView.findViewById(R.id.link);
        }

        public void bind(Movie movie, String key) {
            title.setText(movie.getTitle());
            desc.setText(movie.getDesc());
            link.setText(movie.getLink());
            this.key = key;
        }
    }

    class MovieAdapter extends RecyclerView.Adapter<MovieItemView> {
        private List<Movie> movieList;
        private List<String> keys;

        public MovieAdapter(List<Movie> movieList, List<String> keys) {
            this.movieList = movieList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public MovieItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MovieItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieItemView holder, int position) {
            holder.bind(movieList.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }
}
