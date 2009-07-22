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
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class Launcher extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    // Show instructions once if app launched directly rather than through "Share" menu item
    TextView textView = (TextView) findViewById(R.id.TextView);
    textView.setText(getResources().getString(R.string.helpBlurb));
    
    // Then remove app icon from Launcher so don't clutter menu
	PackageManager packageManager = getPackageManager();
	packageManager
			.setComponentEnabledSetting(
					getComponentName(),
					PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
					PackageManager.DONT_KILL_APP);
    }
}
