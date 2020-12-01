package vikas.androidinterview;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class Simple_question extends AppCompatActivity {

        TextView ques,ans,xx,yy;
        Button bLeft,bRight,bAns;
        String[] simple_ques,simple_ans;
        int index;
        private  static final String default_answer="Press Button \"A\" for the Answer";
        //variable obj of text to speech
        TextToSpeech obj;
        int result;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.questions);

            //code to add custom actionBar

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            View customView = getLayoutInflater().inflate(R.layout.ques_title_bar, null);
            actionBar.setCustomView(customView);
            Toolbar parent =(Toolbar) customView.getParent();
            parent.setPadding(0,0,0,0);//for tab otherwise give space in tab
            parent.setContentInsetsAbsolute(0,0); // Main Point

            //LinearLayout ques_p=(LinearLayout)findViewById(R.id.ques_title_bar);
            //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            //getSupportActionBar().setCustomView(R.layout.ques_title_bar);

            Button bSpeak=(Button)findViewById(R.id.bSpeak);
            Button bStop=(Button)findViewById(R.id.bStop);
            TextView cat=(TextView)findViewById(R.id.bCat);
            cat.setText("Simple Question");

            simple_ques=getResources().getStringArray(R.array.simple_ques);
            simple_ans=getResources().getStringArray(R.array.simple_ans);
            ques=(TextView)findViewById(R.id.ques);
            ans=(TextView)findViewById(R.id.ans);
            xx=(TextView)findViewById(R.id.xx);
            yy=(TextView)findViewById(R.id.yy);
            bLeft=(Button)findViewById(R.id.bLeft);
            bRight=(Button)findViewById(R.id.bRight);
            bAns=(Button)findViewById(R.id.bShowAns);
            index=0;
            ques.setText(simple_ques[index]);
            ans.setText(default_answer);
            xx.setText(String.valueOf(index+1)) ;
            yy.setText("/"+String.valueOf(simple_ques.length));

            //Textto speech process
            obj=new TextToSpeech(Simple_question.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                   if(status==TextToSpeech.SUCCESS){
                       result=obj.setLanguage(Locale.ENGLISH);
                   }else{
                       Toast.makeText(getApplicationContext(),"Feature not Supported in your phone",Toast.LENGTH_SHORT).show();
                   }
                }
            });

            bLeft.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                ans.setText(default_answer);
                index-=1;
                if(index==-1){
                    index=simple_ques.length-1;
                    ques.setText(simple_ques[index]);
                    xx.setText(String.valueOf(index+1)) ;
                }
                else{
                    ques.setText(simple_ques[index]);
                    xx.setText(String.valueOf(index+1)) ;
                }
                }
            });

            bRight.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ans.setText(default_answer);

                    index+=1;
                    if(index==simple_ques.length){
                        index=0;
                        ques.setText(simple_ques[index]);
                        xx.setText(String.valueOf(index+1)) ;
                    }
                    else{
                        ques.setText(simple_ques[index]);
                        xx.setText(String.valueOf(index+1)) ;
                    }
                }
            });

            bAns.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                ans.setText(simple_ans[index]);
                }
            });

            bSpeak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(result==TextToSpeech.LANG_MISSING_DATA){
                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();

                    }else{
                        if(ans.getText().toString().equals(default_answer)){

                        }else {
                            obj.speak(simple_ans[index], TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                }
            });

            bStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(obj!=null){
                        obj.stop();
                    }
                }
            });

        }
        @Override
    protected void onDestroy() {

            super.onDestroy();
            if(obj!=null){
                obj.stop();
                obj.shutdown();
            }
        }
}
