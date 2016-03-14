package codepath.apps.demointroandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Window;
import android.view.*;

import com.firebase.client.Firebase;

public class ConsentFormActivity extends Activity {
    private String question;
    private TextView textview;
    private final Firebase ref = new Firebase("https://healthsurvey1.firebaseio.com/survey");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent myIntent = getIntent(); // gets the previously created intent
        Firebase.setAndroidContext(this);
        String firstKeyName = myIntent.getStringExtra("firstKeyName");
        question = firstKeyName;
        String secondKeyName = myIntent.getStringExtra("secondKeyName");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_consent_form);
        String consentFormText = "blah blah blah blah blah blah blah blah blah blah blah "
                + "blah blah blah blah blah blah blah blah blah blah blah blah blah blah "
                + "blah blah blah blah blah blah blah blah blah blah blah blah blah blah ";
        textview = (TextView) findViewById(R.id.textView7);
        textview.setText(consentFormText);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.55));




    }

    /*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_simple_return_result, menu);
		return true;
	}*/

    public void sendResult(View v) {
        //String answer = ((EditText) findViewById(R.id.txtRandomResultText)).getText().toString();
        String uid = ((MyApplication) this.getApplication()).getUID(); //to get uid.
        //ref.child("answers").child(uid).child(question).setValue(result);
        int totalQuestionNum = ((MyApplication) this.getApplication()).getQuestionArr().length;
        int totalQuestionAns = ((MyApplication) this.getApplication()).getQuestionsAnswered();

        if(totalQuestionAns == totalQuestionNum) {
            int questionNum = ((MyApplication) this.getApplication()).getQuestionNum();
            int answerNum = ((MyApplication) this.getApplication()).getAnswerNum();
            String[] questions = ((MyApplication) this.getApplication()).getQuestions();
            String[] answers = ((MyApplication) this.getApplication()).getAnswers();
            for(int i = 0; i < questionNum; i++) {
                ref.child("answers").child(uid).child(questions[i]).setValue(answers[i]);
                Log.d(questions[i],answers[i]);
            }
            Log.d("heyyoooo","hella1111");
            Intent i = new Intent();
            i.putExtra("result", "Submitted! Thank you.");
            setResult(RESULT_OK, i);
            finish();
        } else {
            Intent i = new Intent();
            i.putExtra("result", "Please answer all questions.");
            setResult(RESULT_OK, i);
            finish();
        }

    }

}
