package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.User;
import com.pllug.course.ivankiv.courseproject.Model.data.source.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.UserRepository;
import com.pllug.course.ivankiv.courseproject.Presenter.ProfilePresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Activity.PersonActivity;
import com.pllug.course.ivankiv.courseproject.View.CircularTransformation;
import com.pllug.course.ivankiv.courseproject.View.Interface.ProfileContract;
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

    private TextView name, email, phone, site, city, job;
    private ImageView avatar;
    private int userId, albumId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.profile_fragment, container, false);
        initView();
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
    private void showProgressLoaderWithBackground(boolean visibility, String text) {
        if (text == null)
            text = "";
        ((TextView)root.findViewById(R.id.progress_bar_text)).setText(text);

        root.findViewById(R.id.container_progress_bar).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setUser(List<User> users) {
        user = users.get(0);
        showUserData();
    }

    @Override
    public void setPhotos(List<PhotoModel> photos) {
        avatarUrl = photos.get(0).getUrl();
        showAvatar();
        ((PersonActivity)getActivity()).setUserAndUrl(user, avatarUrl);
    }

    @Override
    public void showProgress() {
        showProgressLoaderWithBackground(true, " loading data...");
    }

    @Override
    public void hideProgress() {
        showProgressLoaderWithBackground(false, " loading data...");
    }

    @Override
    public void setAlbum(List<AlbumModel> albums) {
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
