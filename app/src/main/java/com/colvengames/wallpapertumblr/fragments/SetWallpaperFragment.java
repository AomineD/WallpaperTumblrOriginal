package com.colvengames.wallpapertumblr.fragments;



import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.activities.WallpaperActivity;
import com.colvengames.wallpapertumblr.api.DownloadArchiveResponse;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.login.LoginException;

import pl.droidsonroids.gif.GifImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetWallpaperFragment extends Fragment implements DownloadArchiveResponse {


    private Button[] buttons = new Button[4];
    private Uri data;
    private Uri path;
    private Bundle bondol;
    private ImageView backgr;
    private Uri final_data;
    public static final String key_path = "jdasndajdnas";
    public static final String key_bondol = "kkeys-bondol";
    private LinearLayout List_buttons;
    private Fragment Fragment;
    private boolean activado = false;


    public SetWallpaperFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle rescata = getArguments();


Fragment = this;

        data = Uri.parse(rescata.getString(key_bondol));

        path = Uri.parse(rescata.getString(key_path));


        String urr = String.valueOf(data);



        Log.e("MAIN", "onCreate: "+urr);

     //  downloader  = new Downloader(getContext(), null, this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_set_wallpaper, container, false);


        backgr = view.findViewById(R.id.back_setter);
        SetupButtons(view);
        Picasso.get().load(data).into(backgr);
        backgr.getDrawingCache();

//cropView = view.findViewById(R.id.cropview);
//Layout_Crop = view.findViewById(R.id.croprelative);

        return view;
    }


    private void SetupButtons(final View view){

        buttons[0] = view.findViewById(R.id.likewallpaper);
        buttons[1] = view.findViewById(R.id.likelock);
        buttons[2] = view.findViewById(R.id.share);

        final GifImageView[] gifs = new GifImageView[3];

        gifs[0] = view.findViewById(R.id.star_gif_1);
        gifs[1] = view.findViewById(R.id.star_gif_2);
        gifs[2] = view.findViewById(R.id.star_gif_3);


// ==================================== BOTONES ============================================ //
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if(!activado) {
                    isWallpaper();
                    LoadSound(gifs[1]);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SetCourt(v);
                        }
                    }, 1000);


                    activado = true;
                }

            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!activado) {
                    ProfileWhatsapp();
                    LoadSound(gifs[1]);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SetCourt(v);
                        }
                    }, 1000);
activado = true;
                }

            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!activado) {
                    LoadSound(gifs[2]);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            ShareImage(v);
                        }
                    }, 1000);
activado = true;
                }

            }
        });



        // =============================================== TEXTOS ======================================================== //
        TextView[] textViews = new TextView[3];


        textViews[0] = view.findViewById(R.id.ss1);
        textViews[1] = view.findViewById(R.id.ss2);
        textViews[2] = view.findViewById(R.id.ss3);





        textViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!activado) {
                    isWallpaper();
                    LoadSound(gifs[0]);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SetCourt(v);
                        }
                    }, 1000);
activado = true;
                }
            }
        });

        textViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!activado) {
                    ProfileWhatsapp();
                    LoadSound(gifs[1]);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SetCourt(v);
                        }
                    }, 1000);
activado = true;
                }

            }
        });

        textViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!activado) {
                    LoadSound(gifs[2]);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            ShareImage(v);
                        }
                    }, 1000);
activado = true;
                }

            }
        });



        // ================================================================================================ //


        List_buttons = view.findViewById(R.id.list_buttons);







    }

    private void ShareImage(View v) {


    Intent intent = new Intent(Intent.ACTION_SEND);


    BitmapDrawable bitmapDrawable = (BitmapDrawable) backgr.getDrawable();

    Bitmap bitmap = bitmapDrawable.getBitmap();

    Uri iru = GetUriFromImage(bitmap, getContext());

    Log.e("MAIN", "ShareImage: url = " + iru.toString());


    String texto = getString(R.string.look_share) + " " + getString(R.string.linkapp) + getActivity().getPackageName();

    intent.putExtra("sms_body", texto);
    intent.putExtra(Intent.EXTRA_TEXT, texto);
    intent.putExtra(Intent.EXTRA_STREAM, iru);
    intent.setType("image/*");


    startActivity(Intent.createChooser(intent, getString(R.string.title_chooser_share)));
}





   /* private void CropImage(){
        BitmapDrawable drawable = (BitmapDrawable) backgr.getDrawable();


        Bitmap batmap = drawable.getBitmap();

        Log.e("MAIN", "CropImage: bitmap = "+(batmap!=null));
        if (batmap != null) {
            final_data = GetUriFromImage(batmap);
        }

        Log.e("MAIN", "CropImage: "+final_data.toString());

        Intent CropIntent = new Intent("com.android.camera.action.CROP");



        CropIntent.setDataAndType(final_data, "image/*");

        CropIntent.putExtra("crop", true);
        CropIntent.putExtra("outputX", 768);
        CropIntent.putExtra("outputY", 768);
        CropIntent.putExtra("ascpectX", 3);
        CropIntent.putExtra("ascpectY", 2);
        CropIntent.putExtra("scale", true);
       // CropIntent.putExtra("scaleUpIfNeeded", true);
        CropIntent.putExtra("return-data", true);


        startActivityForResult(CropIntent, 22);


    }*/

   private void SetupFinalData(){
        BitmapDrawable drawable = (BitmapDrawable) backgr.getDrawable();


        Bitmap batmap = drawable.getBitmap();

        Log.e("MAIN", "CropImage: bitmap = "+(batmap!=null));
        if (batmap != null) {
            final_data = GetUriFromImage(batmap, getContext());
        }
    }

    private void isWallpaper(){
       try {
           SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

           SharedPreferences.Editor editor = preferences.edit();

           editor.putInt(WallpaperActivity.key_type, 0);

           editor.commit();


       }catch (NullPointerException e){
           Log.e("MAIN", "IsWallpaper: "+e.getMessage());
       }
    }

    private void ProfileWhatsapp(){
        try {
            SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();

            editor.putInt(WallpaperActivity.key_type, 2);

            editor.commit();


        }catch (NullPointerException e){
            Log.e("MAIN", "IsWallpaper: "+e.getMessage());
        }
    }



    @Override
    public void Donwloaded(Uri path) {
        data = path;
    }

    @Override
    public void Failed(String errno) {

    }

    private void LoadSound(GifImageView gif){
       gif.setVisibility(View.VISIBLE);
        MediaPlayer plyer = MediaPlayer.create(getContext(), R.raw.coin);

        plyer.start();
    }


    private void SetCourt(View view){
    SetupFinalData();

    if (getActivity().getPackageName() != null) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(SetWallpaperFragment.this).commit();


        Uri uri = Uri.parse(Environment.getExternalStorageDirectory() + "/" + getContext().getResources().getString(R.string.app_name) + "/" + "jaj.png");

        //  Log.e("MAIN", "onClick: URI "+uri.toString());
        UCrop.of(final_data, uri).withAspectRatio(9, 16).withMaxResultSize(2280, 1800).start(getActivity());

    }

}




    public static Uri GetUriFromImage(Bitmap bitmap, Context mcontext){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(mcontext.getContentResolver(), bitmap, "nose", null);
        return Uri.parse(path);

    }
/*
    public static Bitmap GetBitmap(ImageView view){
        BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();

        Log.e("MAIN", "GetBitmap: no es null =  "+(drawable!=null));

try {
    Bitmap batmap = drawable.getBitmap();
    return batmap;
}catch (NullPointerException e){
    Log.e("MAIN", "GetBitmap: POINTER EXCEPTION "+e.getMessage());
    return null;
}


    }*/
}
