package mpr.a1_1801040217.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String P_LANG = "LANG";
    private SharedPreferences sp;
    private TextView txtLanguage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLanguage = findViewById(R.id.txtLanguage);

        sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String language = sp.getString(P_LANG, null);

        if (language != null){
            txtLanguage.setText(language);
        }else{
            selectLanguage();
        }

    }

    public void selectLanguage(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose a language")
                .setMessage("Which language would you like?")
                .setPositiveButton("Vietnamese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("Vietnamese");
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("English");

                    }
                })
                .show();

    }

    public void setLanguage(String language){
        //update language preferences
        sp.edit().putString(P_LANG, language).apply();
        //update the text view
        txtLanguage.setText(language);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuEng:
                setLanguage("English");
                break;
            case R.id.menuVi:
                setLanguage("Vietnamese");
                break;
            case R.id.menuClear:
                sp.edit().clear().apply();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}