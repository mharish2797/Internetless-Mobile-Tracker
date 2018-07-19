package tracker.de.detracker;
/*
* Created by Harish on 17-Feb-2016
*/
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences setting=this.getPreferences(0);
        SharedPreferences.Editor editor=setting.edit();
        editor.putString("mobno","9123456780");
        editor.commit();
    }
}
