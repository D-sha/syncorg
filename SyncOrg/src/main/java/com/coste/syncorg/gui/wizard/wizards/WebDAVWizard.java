package com.coste.syncorg.gui.wizard.wizards;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coste.syncorg.R;
import com.coste.syncorg.synchronizers.WebDAVSynchronizer;

import static com.coste.syncorg.settings.SettingsActivity.KEY_SYNC_SOURCE;

public class WebDAVWizard extends AppCompatActivity {

    private static final String TAG = "syncorg";
    private EditText webdavUser;
    private EditText webdavPass;
    private EditText webdavUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wizard_webdav);

        webdavUser = (EditText) findViewById(R.id.wizard_webdav_username);
        webdavPass = (EditText) findViewById(R.id.wizard_webdav_password);
        webdavUrl = (EditText) findViewById(R.id.wizard_webdav_url);
        Button webdavLoginButton = (Button) findViewById(R.id.wizard_webdav_login_button);
        webdavLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				loginWebdav();
            }
        });
    }

	public void loginWebdav() {
		final String urlActual = webdavUrl.getText().toString();
		final String passActual = webdavPass.getText().toString();
		final String userActual = webdavUser.getText().toString();

        final Context context = getApplicationContext();

		class Atask extends AsyncTask<String, Void, String> {
            protected String doInBackground(String... params) {
                WebDAVSynchronizer wds = new WebDAVSynchronizer(context);
                return wds.testConnection(urlActual, userActual, passActual);
            }

            @Override
            protected void onPostExecute(String testResult) {
                if (testResult != null){
                    Toast.makeText(WebDAVWizard.this, "Login failed: " + testResult, Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(WebDAVWizard.this, "Login succeeded", Toast.LENGTH_LONG).show();
                    saveSettings();
                }
            }
        }
        new Atask().execute("");

    }

	public void saveSettings() {
		SharedPreferences appSettings = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = appSettings.edit();

		editor.putString(KEY_SYNC_SOURCE, "webdav");

		editor.putString("webUrl", webdavUrl.getText().toString());
		editor.putString("webPass", webdavPass.getText().toString());
		editor.putString("webUser", webdavUser.getText().toString());

		editor.apply();
		finish();
	}
}
