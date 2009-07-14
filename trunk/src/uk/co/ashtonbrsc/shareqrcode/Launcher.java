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
