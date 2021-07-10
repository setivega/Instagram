package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.instagram.models.Post;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class PostDetailActivity extends AppCompatActivity {

    public static final String PROFILE_IMAGE = "profileImage";
    private Post post;
    private ParseUser user;
    private TextView tvUsername;
    private TextView tvCaption;
    private TextView tvCreatedAt;
    private ImageView ivPostImage;
    private ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        tvUsername = findViewById(R.id.tvUsername);
        tvCaption = findViewById(R.id.tvCaption);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        ivPostImage = findViewById(R.id.ivPostImage);
        ivProfileImage = findViewById(R.id.ivProfileImage);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        user = post.getUser();

        tvUsername.setText(post.getUser().getUsername());
        tvCaption.setText(post.getDescription());
        tvCreatedAt.setText(ParseRelativeDate.getRelativeTimeAgo(post.getCreatedAt().toString()));
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(post.getImage().getUrl())
                    .transform(new CenterCrop())
                    .into(ivPostImage);
        }

        ParseFile profileImage = (ParseFile) user.get(PROFILE_IMAGE);
        if (profileImage != null) {
            Glide.with(this).load(profileImage.getUrl())
                    .transform(new CircleCrop())
                    .into(ivProfileImage);
        } else {
            Glide.with(this).load(R.drawable.default_profile_image)
                    .transform(new CircleCrop())
                    .into(ivProfileImage);
        }
    }



}