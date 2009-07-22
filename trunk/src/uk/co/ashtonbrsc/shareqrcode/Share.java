/*
 * Copyright 2009 Michael Basil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.ashtonbrsc.shareqrcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class Share extends Activity {
	private String url;

	private static final String DEFAULT_TITLE = "Install Barcode Scanner?";
	private static final String DEFAULT_MESSAGE = "This application requires Barcode Scanner. Would you like to install it?";
	private static final String DEFAULT_YES = "Yes";
	private static final String DEFAULT_NO = "No";

	// TODO Get title of webpage to display as confirmation

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent callingIntent = getIntent();
		String type = callingIntent.getType();
		if ("text/plain".equals(type)) {
			url = callingIntent.getStringExtra(Intent.EXTRA_TEXT);
		}
		else {
			finish();
		}
		Intent intent = new Intent();
		intent.setAction("com.google.zxing.client.android.ENCODE");
		intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
		intent.putExtra("ENCODE_DATA", url);
		try {
			startActivity(intent);
			finish();
		} catch (ActivityNotFoundException e) {
			showDownloadDialog(this, DEFAULT_TITLE, DEFAULT_MESSAGE,
					DEFAULT_YES, DEFAULT_NO);
		}

	}

	private void showDownloadDialog(final Activity activity,
			String stringTitle, String stringMessage, String stringButtonYes,
			String stringButtonNo) {
		AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
		downloadDialog.setTitle(stringTitle);
		downloadDialog.setMessage(stringMessage);
		downloadDialog.setPositiveButton(stringButtonYes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogInterface, int i) {
						Uri uri = Uri
								.parse("market://search?q=pname:com.google.zxing.client.android");
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						activity.startActivity(intent);
						TextView textView = (TextView) findViewById(R.id.TextView);
						textView.setText(getResources().getString(
								R.string.waitForInstall));
					}
				});
		downloadDialog.setNegativeButton(stringButtonNo,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogInterface, int i) {
						finish();
					}
				});
		downloadDialog.show();
	}

}
