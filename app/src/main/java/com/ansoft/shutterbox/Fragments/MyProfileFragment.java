package com.ansoft.shutterbox.Fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ansoft.shutterbox.HomeActivity;
import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.CurrentUser;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.AlertDialogs.LoadingAlertDialog;
import com.ansoft.shutterbox.Util.ClickEffect;

import java.io.ByteArrayOutputStream;

public class MyProfileFragment extends Fragment {

    EditText nameField, userNameField, emailField, phField, pwField;
    ImageView choosePhotBtn;
    ImageView profilePhoto;
    Bitmap photoBitmap;
    RelativeLayout backRL;
    private static final int SELECT_PHOTO = 100;
    final int PIC_CROP = 1;

    public MyProfileFragment() {
    }

    private void performCrop(Uri picUri) {
        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }

        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
        }
    }

    public void onActivityResult(int requestCode, int resultCode,
                                 Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    performCrop(selectedImage);

                }
                break;

            case PIC_CROP:
                if (imageReturnedIntent != null) {
                    // get the returned data
                    Bundle extras = imageReturnedIntent.getExtras();
                    // get the cropped bitmap
                    photoBitmap = extras.getParcelable("data");

                    profilePhoto.setImageBitmap(photoBitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photoBitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
                    byte[] byte_arr = stream.toByteArray();
                    String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                    final LoadingAlertDialog alert = new LoadingAlertDialog(getActivity(), "Saving Photo..");
                    alert.show();
                    ShutterUser user = new ShutterUser(getActivity());
                    user.SavePhotoInBackGround(image_str, new ShutterUser.PhotoSaveCallBack() {
                        @Override
                        public void OnDone() {
                            alert.dismiss();

                        }
                    });
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_my_profile, container, false);
        backRL = (RelativeLayout) cv.findViewById(R.id.rlBack);
        backRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickEffect.OnBackClick(getFragmentManager());
            }
        });
        ClickEffect.Opacity(backRL);
        choosePhotBtn = (ImageView) cv.findViewById(R.id.ic_choose_photo_btn);
        profilePhoto = (ImageView) cv.findViewById(R.id.profilePhoto);
        if (!new CurrentUser(getActivity()).getProfilePhoto().isEmpty()) {
            byte[] decodedString = Base64.decode(new CurrentUser(getActivity()).getProfilePhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profilePhoto.setImageBitmap(decodedByte);
        }
        ClickEffect.Opacity(choosePhotBtn);
        choosePhotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        nameField = (EditText) cv.findViewById(R.id.nameField);
        userNameField = (EditText) cv.findViewById(R.id.userNameField);
        emailField = (EditText) cv.findViewById(R.id.emailField);
        phField = (EditText) cv.findViewById(R.id.phField);
        pwField = (EditText) cv.findViewById(R.id.pwField);
        nameField.setText(new CurrentUser(getActivity()).getFullname());
        userNameField.setText(new CurrentUser(getActivity()).getEmail());
        emailField.setText(new CurrentUser(getActivity()).getEmail());
        phField.setText(new CurrentUser(getActivity()).getPhoneNumber());
        setDrawableClickAction(nameField, new EditCallBack() {
            @Override
            public void onDone() {
                ShutterUser user = new ShutterUser(getActivity());
                user.saveFullName(nameField.getText().toString());
            }
        });
        setDrawableClickAction(userNameField, new EditCallBack() {
            @Override
            public void onDone() {
                ShutterUser user = new ShutterUser(getActivity());
                user.saveEmailField(userNameField.getText().toString());
            }
        });
        setDrawableClickAction(emailField, new EditCallBack() {
            @Override
            public void onDone() {
                ShutterUser user = new ShutterUser(getActivity());
                user.saveFullName(emailField.getText().toString());
            }
        });
        setDrawableClickAction(phField, new EditCallBack() {
            @Override
            public void onDone() {
                ShutterUser user = new ShutterUser(getActivity());
                user.savePhonenumberField(phField.getText().toString());
            }
        });
        setDrawableClickAction(pwField, new EditCallBack() {
            @Override
            public void onDone() {
                ShutterUser user = new ShutterUser(getActivity());
                user.savePasswordField(pwField.getText().toString());
            }
        });
        ImageView icDrawer = (ImageView) cv.findViewById(R.id.ic_drawer);
        icDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        return cv;
    }

    public void setDrawableClickAction(final EditText editText, final EditCallBack cb) {

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        cb.onDone();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public interface EditCallBack {
        void onDone();
    }

}
