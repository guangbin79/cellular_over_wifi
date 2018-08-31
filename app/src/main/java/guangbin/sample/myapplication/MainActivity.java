package guangbin.sample.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectivityManager connectivityManager;
    WebView webView;
    NetworkRequest.Builder req;
    ConnectivityManager.NetworkCallback networkCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);

        connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        req = new NetworkRequest.Builder();
        req.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR);
        req.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);

        networkCallback = new ConnectivityManager.NetworkCallback() {

                    @Override
                    public void onAvailable(Network network) {
                        connectivityManager.bindProcessToNetwork(network);
                    }
                };

        connectivityManager.requestNetwork(req.build(), networkCallback);
    }

    public void btnClick(View view) {
        webView.loadUrl("https://www.baidu.com");
    }
}
