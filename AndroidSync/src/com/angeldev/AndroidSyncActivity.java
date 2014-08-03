package com.angeldev;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidSyncActivity extends Activity {

	private Helper utilities = new Helper();

	private ArrayList<NameValuePair> nameValuePairs = null;

	private InputStream entityContent = null;

	private String entityStringContent = null;
	private String result = null;
	private String scriptLoginName = "http://gametecstop.vacau.com/loginScript.php";

	private Button bLogin = null;

	private EditText etUser = null;
	private EditText etPassword = null;

	public static String userLogin = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		etUser = (EditText) findViewById(R.id.etUser);
		etPassword = (EditText) findViewById(R.id.etPassword);
		bLogin = (Button) findViewById(R.id.bLogin);

		bLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (validateUser(etUser.getText().toString(), etPassword
						.getText().toString())) {

					Intent myIntent = new Intent(view.getContext(),
							MainWindow.class);
					startActivityForResult(myIntent, 1);
				}

			}
		});
	}

	public boolean validateInputs(String userLogin, String userPassword) {
		if (userLogin.equals("") || (userLogin.length() == 0)) {
			return false;
		}
		return true;
	}

	public boolean validateUser(String userLogin, String userPassword) {

		if (validateInputs(userLogin, userPassword) == false) {
			return false;
		}

		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", userLogin));
		nameValuePairs.add(new BasicNameValuePair("password", userPassword));

		// entityContent = utilities.getEntityContent(scriptLoginName,
		// nameValuePairs);
		// entityStringContent =
		// utilities.getEntityContentToString(entityContent);

		result = "1";//utilities.getResult(entityStringContent);

		if (Integer.parseInt(result) > 0) {

			System.out.println("EXITO " + result);
			return true;

		} else {
			Toast.makeText(getBaseContext(), "Usuario no encontrado",
					Toast.LENGTH_LONG).show();
		}
		return false;
	}

}