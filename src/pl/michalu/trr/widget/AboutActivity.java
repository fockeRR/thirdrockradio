package pl.michalu.trr.widget;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
		WebView aboutText = (WebView) findViewById(R.id.aboutText);
		aboutText.loadDataWithBaseURL(null,"<html><body style='text-align:justify'>"+getString(R.string.about)+"<br /><br />(more on <a href=\"http://www.rfcmedia.com/thirdrockradio/\">http://www.rfcmedia.com/thirdrockradio//</a>)"+"</body></html>","text/html","utf-8",null);
	}
}