# listen network environment

intent action is android.net.conn.CONNECTIVITY_CHANGE 

you should have permission **android.permission.ACCESS_NETWORK_STATE**

# register to receive broadcast

 registerReceiver(BroadcastReceiver receiver, IntentFilter filter);

 