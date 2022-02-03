import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        uses-permission android:name="android.permission.ACCESS_NETWORK_STATE
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = systemService.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo.isAvailable() && networkInfo.isConnected()) {
            Toast.makeText(context,"network is enanle", Toast.LENGTH_SHORT).show();
        }
    }
}

