package com.imtiazaminsajid.coronaupdate.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentCoronaHotLineBinding;

public class CoronaHotLineFragment extends Fragment {

    static CoronaHotLineFragment instance;

    FragmentCoronaHotLineBinding fragmentCoronaHotLineBinding;

    private int CALL_PERMISSION_CODE = 2;

    View view;

    public CoronaHotLineFragment() {
        // Required empty public constructor
    }

    public static CoronaHotLineFragment getInstance() {
        if (instance == null)
            instance = new CoronaHotLineFragment();
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCoronaHotLineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_corona_hot_line, container, false);
        view = fragmentCoronaHotLineBinding.getRoot();

        makeCall();

        Animation animation  = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        fragmentCoronaHotLineBinding.iedcrIcon.startAnimation(animation);
        fragmentCoronaHotLineBinding.healthCenterIcon.startAnimation(animation);
        fragmentCoronaHotLineBinding.healthLineIcon.startAnimation(animation);
        fragmentCoronaHotLineBinding.nationalCallCenterIcon.startAnimation(animation);
        fragmentCoronaHotLineBinding.nationalHelplineIcon.startAnimation(animation);
        fragmentCoronaHotLineBinding.iedcr2Icon.startAnimation(animation);
        fragmentCoronaHotLineBinding.iedcr3Icon.startAnimation(animation);
        fragmentCoronaHotLineBinding.iedcr4Icon.startAnimation(animation);

        return view;
    }


    private void makeCall() {

        fragmentCoronaHotLineBinding.nationalCallCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "333";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });

        fragmentCoronaHotLineBinding.healthCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "16263";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });

        fragmentCoronaHotLineBinding.iedcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "10655";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });

        fragmentCoronaHotLineBinding.healthLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "09611677777";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });

        fragmentCoronaHotLineBinding.nationalHelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "109";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });

        fragmentCoronaHotLineBinding.iedcr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "+8801944333222";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });

        fragmentCoronaHotLineBinding.iedcr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "+8801937000011";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });
        fragmentCoronaHotLineBinding.iedcr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    String number = "+8801937110011";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } else {
                    requestStoragePermission();
                }
            }
        });
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {

            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Needed")
                    .setMessage("This Permission is Needed For Call")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
        }
    }
}

