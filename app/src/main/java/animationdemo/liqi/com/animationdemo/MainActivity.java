package animationdemo.liqi.com.animationdemo;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void anim(View view){
        ImageView imageView = (ImageView) view;
        Drawable drawable = imageView.getDrawable();
        if(drawable instanceof Animatable){
            ((Animatable) drawable).start();
        }
    }

    public void toBerzierActivity(View view){
        Intent i = new Intent(this,BerzierActivity.class);
        startActivity(i);
    }

    public void toMorphingActivity(View view){
        Intent i = new Intent(this,MorphingBerzierActivity.class);
        startActivity(i);
    }

    public void toPadActivity(View view){
        Intent i = new Intent(this,PadBerzierActivity.class);
        startActivity(i);
    }

    public void anim_five(View view){
        ImageView imageView = (ImageView) view;
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getDrawable(R.drawable.fivestar_anim);
        imageView.setImageDrawable(drawable);
        if(drawable != null){
            drawable.start();
        }
    }

    public void toWaveActivity(View view){
        Intent i = new Intent(this,WaveBerzierActivity.class);
        startActivity(i);
    }

    public void toPathActivity(View view){
        Intent i = new Intent(this,PathBerzierActivity.class);
        startActivity(i);
    }

    public void toLoadingActivity(View view){
        Intent i = new Intent(this,LoadingActivity.class);
        startActivity(i);
    }

    public void topathpaintActivity(View view){
        Intent i = new Intent(this,PathPaintActivity.class);
        startActivity(i);
    }

    public void topostanActivity(View view){
        Intent i = new Intent(this,PathPosTanActivity.class);
        startActivity(i);
    }
}
