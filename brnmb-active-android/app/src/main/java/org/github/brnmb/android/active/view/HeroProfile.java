package org.github.brnmb.android.active.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import org.github.brnmb.android.active.R;
import org.github.brnmb.android.active.model.Hero;

public class HeroProfile extends AppCompatActivity {

    Hero myHero;
    TextView tvName;
    TextView tvAttribute;
    ImageView ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);


        tvName = (TextView) findViewById(R.id.name);
        tvAttribute = (TextView) findViewById(R.id.attribute);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            myHero = extra.getParcelable("hero");
            if (myHero != null) {
                tvName.setText(myHero.name);
                tvAttribute.setText(myHero.heroAttribute.name);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(myHero.heroImage, 0, myHero.heroImage.length);
//                ivProfile.setImageBitmap(bitmap);
            } else {
                Log.e("ERROR 1", "HERO NULL");
            }
        } else {
            Log.e("ERROR 1", "EXTRA NULL");
        }
    }
}
