package com.imtiazaminsajid.coronaupdate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends Fragment {

    static AboutUsFragment instance;

    FragmentAboutUsBinding fragmentAboutUsBinding;

    View view;

    public AboutUsFragment() {

    }

    public static AboutUsFragment getInstance() {
        if (instance == null)
            instance = new AboutUsFragment();
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAboutUsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false);
        view = fragmentAboutUsBinding.getRoot();

        fragmentAboutUsBinding.emailToUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.putExtra(Intent.EXTRA_EMAIL  , new String []{"swapno.ekchiltehasirjonne@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "About donation/details");
                i.putExtra(Intent.EXTRA_TEXT   , "/// Please comment below this line. /// \n " +
                        "Dear স্বপ্ন-এক চিলতে হাসির জন্যে,"+
                        " \n " +
                        " \n " +
                        " \n " +
                        " \n " +
                        " \n ");
                i.setType("message/rfc822");
                i.setData(Uri.parse("mailto:"));

                try {
                    startActivity(Intent.createChooser(i, "Send Feedback"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fragmentAboutUsBinding.openFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1764126680479047"));
                    startActivity(intent);
                } catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/swapno.ekchiltehasirjonne/")));
                }
            }
        });

        Animation animation  = AnimationUtils.loadAnimation(getContext(), R.anim.sample_anim);
        fragmentAboutUsBinding.ourLogo.startAnimation(animation);


        Animation animationOpenFacebook  = AnimationUtils.loadAnimation(getContext(), R.anim.lefttoright);
        fragmentAboutUsBinding.openFacebook.startAnimation(animationOpenFacebook);

        Animation animationEmailToUs  = AnimationUtils.loadAnimation(getContext(), R.anim.righttoleft);
        fragmentAboutUsBinding.emailToUs.startAnimation(animationEmailToUs);



        return view;
    }

}
