package com.pllug.course.ivankiv.courseproject.ui.fragment.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.pllug.course.ivankiv.courseproject.data.model.Album;
import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.model.User;
import com.pllug.course.ivankiv.courseproject.data.source.album.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.data.source.photos.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.data.source.user.UserRepository;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.ui.activity.ProfileActivity;
import com.pllug.course.ivankiv.courseproject.ui.CircularTransformation;
import com.pllug.course.ivankiv.courseproject.ui.fragment.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by iw97d on 14.01.2018.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View{
    View root;
    private UserRepository userRepository;
    private AlbumRepository albumRepository;
    private PhotoRepository photoRepository;
    private ProfileContract.Presenter presenter;

    private User user;
    private String avatarUrl;

    private TextView name, email, phone, site, city, job, progressText;
    private LinearLayout progressContainer;
    private ImageView avatar;
    private int userId, albumId;
    private BaseFragment baseFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.profile_fragment, container, false);
        initView();
        initBaseFragment();
        initData();
        initPresenter();
        getUserIdFromPersonActivity();
        presenter.onGetUser();
        presenter.onGetAlbum();

        return root;
    }

    private void initView() {
        avatar = root.findViewById(R.id.profile_fragment_avatar);
        name = root.findViewById(R.id.profile_fragment_name);
        email = root.findViewById(R.id.profile_fragment_email);
        phone = root.findViewById(R.id.profile_fragment_phone);
        site = root.findViewById(R.id.profile_fragment_website);
        city = root.findViewById(R.id.profile_fragment_city);
        job = root.findViewById(R.id.profile_fragment_job);

        progressText = root.findViewById(R.id.progress_bar_text);

        progressContainer = root.findViewById(R.id.container_progress_bar);
    }

    private void initBaseFragment() {
        baseFragment = new BaseFragment(progressText, progressContainer);
    }

    private void initData() {
        userRepository = UserRepository.getInstance();
        albumRepository = AlbumRepository.getInstance();
        photoRepository = PhotoRepository.getInstance();
    }


    private void initPresenter() {
        presenter = new ProfilePresenter(userRepository, albumRepository, photoRepository, this);
    }

    private void getUserIdFromPersonActivity() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getInt("userId");
        } else {
            userId = 1;
        }
    }

    private void showUserData() {
        name.setText(user.getName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        site.setText(user.getWebsite());
        city.setText(user.getAddress().getCity());
        job.setText(user.getCompany().getName());
    }


    @Override
    public void setUser(List<User> users) {
        user = users.get(0);
        showUserData();
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        avatarUrl = photos.get(0).getUrl();
        showAvatar();
        ((ProfileActivity)getActivity()).setUserAndUrl(user, avatarUrl);
    }

    @Override
    public void showProgress() {
        baseFragment.showProgress();
    }

    @Override
    public void hideProgress() {
        baseFragment.hideProgress();
    }

    @Override
    public void setAlbum(List<Album> albums) {
        albumId = albums.get(0).getId();
        presenter.onGetPhotos();
    }

    @Override
    public int getAlbumId() {
        return albumId;
    }



    private void showAvatar() {
        Picasso.with(getActivity())
                .load(avatarUrl)
                .transform(new CircularTransformation(250))
                .into(avatar);
    }

    @Override
    public int getUserId() {
        return userId;
    }
}
