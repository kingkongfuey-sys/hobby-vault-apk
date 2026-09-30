package com.hobbyvault.app;

import android.webkit.WebView;
import android.widget.Toast;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    private long lastBack = 0;

    @Override
    public void onBackPressed() {
        // Let the app close whatever is open on screen first
        WebView web = getBridge().getWebView();
        web.evaluateJavascript(
            "(function(){" +
            " var b=document.querySelector('.sheet .back'); if(b){b.click();return 1}" +
            " var d=document.querySelector('.backdrop');   if(d){d.click();return 1}" +
            " var l=document.querySelector('.lb');        if(l){l.click();return 1}" +
            " return 0" +
            "})()",
            result -> {
                if ("1".equals(result)) return;
                long now = System.currentTimeMillis();
                if (now - lastBack < 2000) {
                    finish();
                } else {
                    lastBack = now;
                    Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }
}
