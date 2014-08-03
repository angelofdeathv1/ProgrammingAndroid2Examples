package com.angeldev;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainWindow extends Activity {

	private Helper utilities = new Helper();
	private ArrayList<NameValuePair> nameValuePairs = null;

	private Button bScanBarcode;
	private Button bSave;

	private TextView tvUserName;

	private EditText etPurchaseTotal = null;
	private EditText etPurchaseID = null;
	private EditText etBarcodeDisplay = null;
	private EditText etPurchaseNotes = null;

	private String scriptUpdateName = "http://gametecstop.vacau.com/updateScript.php";

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainwindow);

		tvUserName = (TextView) findViewById(R.id.textViewUserName);

		etBarcodeDisplay = (EditText) findViewById(R.id.etBarcodeDisplay);
		etPurchaseTotal = (EditText) findViewById(R.id.etPurchaseTotal);
		etPurchaseID = (EditText) findViewById(R.id.etPurchaseID);
		etPurchaseNotes = (EditText) findViewById(R.id.etPurchaseNotes);

		bScanBarcode = (Button) findViewById(R.id.bScanBarcode);
		bSave = (Button) findViewById(R.id.bSave);

		tvUserName.setText(AndroidSyncActivity.userLogin);

		bScanBarcode.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "CODE_128,QR_CODE");
				startActivityForResult(intent, 1);
			}

		});

		bSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				saveData(etBarcodeDisplay.getText().toString(), etPurchaseTotal
						.getText().toString(), etPurchaseID.getText()
						.toString(), etPurchaseNotes.getText().toString());

			}

		});
	}

	private boolean validateMandatoryFields(String userNumber,
			String purchaseTotal) {

		if (userNumber.equals("") || userNumber.length() == 0) {
			return false;
		}

		double purchaseTotalDouble = Double.parseDouble(purchaseTotal);
		if (purchaseTotal.equals("") || purchaseTotalDouble <= 0) {
			return false;
		}
		return true;
	}

	private void saveData(String userNumber, String purchaseTotal,
			String purchaseID, String notes) {
		if (validateMandatoryFields(userNumber, purchaseTotal) == false) {
			Toast.makeText(getBaseContext(),
					"Favor de llenar los campos obligatorio", Toast.LENGTH_LONG)
					.show();
			return;
		}

		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("userNumber", userNumber));
		nameValuePairs.add(new BasicNameValuePair("purchaseTotal",
				purchaseTotal));
		nameValuePairs.add(new BasicNameValuePair("purchaseID", purchaseID));
		nameValuePairs.add(new BasicNameValuePair("notes", notes));

		utilities.getEntityContent(scriptUpdateName, nameValuePairs);

		Toast.makeText(getBaseContext(), "Datos guardados con exito...",
				Toast.LENGTH_LONG).show();
		resetFields();

	}

	private void resetFields() {
		etBarcodeDisplay.setText("");
		etPurchaseTotal.setText("");
		etPurchaseID.setText("");
		etPurchaseNotes.setText("");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				setQRText(contents);
			} else if (resultCode == RESULT_CANCELED) {
				showDialog(R.string.result_failed,
						getString(R.string.result_failed_why));
			}
		}
	}

	private void setQRText(CharSequence message) {
		etBarcodeDisplay.setText(message);
	}

	private void showDialog(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", null);
		builder.show();
	}
	
	@Override
	public void onBackPressed() {
	   return;
	}
}
