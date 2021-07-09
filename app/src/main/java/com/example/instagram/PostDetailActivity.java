package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.instagram.models.Post;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetailActivity extends AppCompatActivity {

    private Post post;
    private TextView tvUsername;
    private TextView tvCaption;
    private TextView tvCreatedAt;
    private ImageView ivPostImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        tvUsername = findViewById(R.id.tvUsername);
        tvCaption = findViewById(R.id.tvCaption);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        ivPostImage = findViewById(R.id.ivPostImage);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        tvUsername.setText("@" + post.getUser().getUsername());
        tvCaption.setText(post.getDescription());
        tvCreatedAt.setText(ParseRelativeDate.getRelativeTimeAgo(post.getCreatedAt().toString()));
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(post.getImage().getUrl())
                    .transform(new CenterCrop(), new RoundedCorners(16))
                    .into(ivPostImage);
        }
    }



}