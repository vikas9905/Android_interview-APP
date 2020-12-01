package vikas.androidinterview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{
    Button bSimple,bTough,bOther,bRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //codes to add action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        View customView = getLayoutInflater().inflate(R.layout.frontpage_title_bar, null);
        actionBar.setCustomView(customView);
        Toolbar parent =(Toolbar) customView.getParent();
        parent.setPadding(0,0,0,0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0,0); // Main Point
        //LinearLayout front_p=(LinearLayout)findViewById(R.id.frontpage_title_bar);
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

        bSimple=(Button)findViewById(R.id.btn1);
        bTough=(Button)findViewById(R.id.btn2);
        bOther=(Button)findViewById(R.id.btn3);
        bRate=(Button)findViewById(R.id.btn4);
        bSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("In Main","simple clicked");
                Intent i=new Intent(MainActivity.this,Simple_question.class);
                startActivity(i);
            }
        });
        bTough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(MainActivity.this,Tough_question.class);
                startActivity(j);
            }
        });
        bOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri1 = Uri.parse("market://search?q=vikas9905");
                    Intent goTomarket = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goTomarket);
                }catch (ActivityNotFoundException e){
                    Uri uri1 = Uri.parse("http://paly.google.com/store/search?q=vikas9905");
                    Intent goTomarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goTomarket1);
                }
            }
        });
        bRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri1 = Uri.parse("market://details?id= " + getPackageName());
                    Intent goTomarket = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goTomarket);
                }catch (ActivityNotFoundException e){
                    Uri uri1 = Uri.parse("http://paly.google.com/store/apps/details?id=" + getPackageName());
                    Intent goTomarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goTomarket1);
                }
            }
        });
    }


}
