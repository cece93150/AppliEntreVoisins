package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class UserProfileActivity extends AppCompatActivity {

    private Neighbour neighbour;
    private NeighbourApiService mApiService;
    public static final String PREF_KEY_FAVORITE_STATUS = "PREF_KEY_FAVORITE_STATUS";

    ImageView mPhoto;
    TextView mId;
    TextView mName;
    TextView mAddress;
    TextView mPhoneNumber;
    TextView mUrl;
    TextView mAboutMe;
    TextView mAboutMeDescription;
    FloatingActionButton mFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        //Action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mApiService = DI.getNeighbourApiService();

        //Referencing elements
        mPhoto = findViewById(R.id.photo_Profile);
        mId = findViewById(R.id.id_User);
        mName = findViewById(R.id.user_Profile_Name);
        mAddress = findViewById(R.id.user_Profile_Address);
        mPhoneNumber = findViewById(R.id.user_Profile_Phone);
        mUrl = findViewById(R.id.user_Profile_Link);
        mAboutMe = findViewById(R.id.about_Me_Title);
        mAboutMeDescription = findViewById(R.id.about_Me_Description);
        mFav = findViewById(R.id.fav_button);

        //Retrieve the intent from the neighbour list
        Intent userProfileActivityIntent = getIntent();
        //This method initializes the neighbor's view
        initView(userProfileActivityIntent);

        //Favorite button
        mFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (neighbour.isFavorite()) {
                    mFav.setImageResource(R.drawable.ic_baseline_star_border_24);
                } else {
                    mFav.setImageResource(R.drawable.ic_baseline_star_24);
                }

                mApiService.toggleNeighbourFavorite(neighbour);

            }
        });
    }

    public static void getNeighbourProfile(FragmentActivity activity, Neighbour neighbour) {
        Intent userProfileActivityIntent = new Intent(activity, UserProfileActivity.class);
        userProfileActivityIntent.putExtra(PREF_KEY_FAVORITE_STATUS, neighbour);
        ActivityCompat.startActivity(activity, userProfileActivityIntent, null);
    }

    private void initView(Intent userProfileActivityIntent) {
        neighbour = userProfileActivityIntent.getParcelableExtra(PREF_KEY_FAVORITE_STATUS);
        if (neighbour != null) {

            String avatarUrl = neighbour.getAvatarUrl();
            if (avatarUrl != null) {
                Glide.with(this).load(avatarUrl).timeout(60000).into(mPhoto);
            }
            String IdUser = neighbour.getName();
            if (IdUser != null) {
                mId.setText(IdUser);
            }
            String name = neighbour.getName();
            if (name != null) {
                mName.setText(name);
            }
            String address = neighbour.getAddress();
            if (address != null) {
                mAddress.setText(address);
            }
            String phoneNumber = neighbour.getPhoneNumber();
            if (phoneNumber != null) {
                mPhoneNumber.setText(phoneNumber);
            }
            String webLink = neighbour.getName();
            if (webLink != null) {
                mUrl.setText("www.facebook.com/" + name);
            }
            String aboutMe = neighbour.getAboutMe();
            if (aboutMe != null) {
                mAboutMeDescription.setText(aboutMe);
            }
            if (neighbour.isFavorite()) {
                mFav.setImageResource(R.drawable.ic_baseline_star_24);
            } else {
                mFav.setImageResource(R.drawable.ic_baseline_star_border_24);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}




