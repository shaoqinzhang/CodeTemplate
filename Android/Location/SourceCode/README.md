# Source code analysis(Android 9)

```LocationManager``` communicate with ```LocationManagerService``` through Binder. The proxy interfaces is aidl ```ILocationManager```

Tree provider:

1. NetworkLocationProvider which implement LocationProviderProxy

2. GpsLocationProvider which use JNI to load libgps.so. so will access gps hardware driver.

3. PassiveProvider.



GeocoderProxy is supplied by third-party application, which transfer latitude and longitude to address. 



LocationManager:

+ requestSingleUpdate

  

```java
// LocationManager.java
@RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})
    public void requestSingleUpdate(String provider, LocationListener listener, Looper looper) {
        ...
        LocationRequest request = LocationRequest.createFromDeprecatedProvider(
                provider, 0, 0, true);
        requestLocationUpdates(request, listener, looper, null);
    }
//LocationRequest.java
  public static LocationRequest createFromDeprecatedProvider(String provider, long minTime,
            float minDistance, boolean singleShot) {
 ...
        int quality;
        if (LocationManager.PASSIVE_PROVIDER.equals(provider)) {
            quality = POWER_NONE;
        } else if (LocationManager.GPS_PROVIDER.equals(provider)) {
            quality = ACCURACY_FINE;
        } else {
            quality = POWER_LOW;
        }

        LocationRequest request = new LocationRequest()
                .setProvider(provider)
                .setQuality(quality)
                .setInterval(minTime)
                .setFastestInterval(minTime)
                .setSmallestDisplacement(minDistance);
      //requestSingleUpdate() singleShott is true
      //default mNumUpdates = Integer.MAX_VALUE;
        if (singleShot) request.setNumUpdates(1);
        return request;
    }
```

  

LocationManagerService.java

```java
//SystemServer.java  startOtherServices()

      try {
          location = new LocationManagerService(context);
          ServiceManager.addService(Context.LOCATION_SERVICE, location);
      } catch (Throwable e) {
          reportWtf("starting Location Manager", e);
      }
//systemRunning to intitial provider and get other services such as pms, ams
 	try {
        if (locationF != null) locationF.systemRunning();
    } catch (Throwable e) {
        reportWtf("Notifying Location Service running", e);
    }
// LocationManagerService.java
    public void systemRunning() {
        synchronized (mLock) {
            ...
            // prepare providers
            loadProvidersLocked();
            updateProvidersLocked();
        }
// LocationManagerService.java
     private void loadProvidersLocked() {
        // create a passive location provider, which is always enabled
        PassiveProvider passiveProvider = new PassiveProvider(this);
        addProviderLocked(passiveProvider);
        mEnabledProviders.add(passiveProvider.getName());
        mPassiveProvider = passiveProvider;

        if (GnssLocationProvider.isSupported()) {
            // Create a gps location provider
            GnssLocationProvider gnssProvider = new GnssLocationProvider(mContext, this,
                    mLocationHandler.getLooper());
            mGnssSystemInfoProvider = gnssProvider.getGnssSystemInfoProvider();
            mGnssBatchingProvider = gnssProvider.getGnssBatchingProvider();
            mGnssMetricsProvider = gnssProvider.getGnssMetricsProvider();
            mGnssStatusProvider = gnssProvider.getGnssStatusProvider();
            mNetInitiatedListener = gnssProvider.getNetInitiatedListener();
            addProviderLocked(gnssProvider);
            mRealProviders.put(LocationManager.GPS_PROVIDER, gnssProvider);
            mGnssMeasurementsProvider = gnssProvider.getGnssMeasurementsProvider();
            mGnssNavigationMessageProvider = gnssProvider.getGnssNavigationMessageProvider();
            mGpsGeofenceProxy = gnssProvider.getGpsGeofenceProxy();
        }

        /*
        Load package name(s) containing location provider support.
        These packages can contain services implementing location providers:
        Geocoder Provider, Network Location Provider, and
        Fused Location Provider. They will each be searched for
        service components implementing these providers.
        The location framework also has support for installation
        of new location providers at run-time. The new package does not
        have to be explicitly listed here, however it must have a signature
        that matches the signature of at least one package on this list.
        */
        Resources resources = mContext.getResources();
        ArrayList<String> providerPackageNames = new ArrayList<>();
        String[] pkgs = resources.getStringArray(
                com.android.internal.R.array.config_locationProviderPackageNames);
        if (D) {
            Log.d(TAG, "certificates for location providers pulled from: " +
                    Arrays.toString(pkgs));
        }
        if (pkgs != null) providerPackageNames.addAll(Arrays.asList(pkgs));

        ensureFallbackFusedProviderPresentLocked(providerPackageNames);

        // bind to network provider. ServiceWatcher start. ServiceWatcher implement ServiceConnection
        LocationProviderProxy networkProvider = LocationProviderProxy.createAndBind(
                mContext,
                LocationManager.NETWORK_PROVIDER,
                NETWORK_LOCATION_SERVICE_ACTION,
                com.android.internal.R.bool.config_enableNetworkLocationOverlay,
                com.android.internal.R.string.config_networkLocationProviderPackageName,
                com.android.internal.R.array.config_locationProviderPackageNames,
                mLocationHandler);
        if (networkProvider != null) {
            mRealProviders.put(LocationManager.NETWORK_PROVIDER, networkProvider);
            mProxyProviders.add(networkProvider);
            addProviderLocked(networkProvider);
        } else {
            Slog.w(TAG, "no network location provider found");
        }

        // bind to fused provider
        LocationProviderProxy fusedLocationProvider = LocationProviderProxy.createAndBind(
.....

        // bind to geocoder provider
        mGeocodeProvider = GeocoderProxy.createAndBind(mContext,
.....
        // bind to geofence provider
        GeofenceProxy provider = GeofenceProxy.createAndBind(
...
    }

//LocationProviderProxy.java
    
```



OS use ILocationProvider.aidl to corp with NLP apk service.

```java
//LocationProviderProxy.java  
@Override
    public void setRequest(ProviderRequest request, WorkSource source) {
        synchronized (mLock) {
            mRequest = request;
            mWorksource = source;
        }
        mServiceWatcher.runOnBinder(new ServiceWatcher.BinderRunner() {
            @Override
            public void run(IBinder binder) {
                ILocationProvider service = ILocationProvider.Stub.asInterface(binder);
                try {
                    service.setRequest(request, source);
                } catch (RemoteException e) {
                    Log.w(TAG, e);
                } catch (Exception e) {
                    // never let remote service crash system server
                    Log.e(TAG, "Exception from " + mServiceWatcher.getBestPackageName(), e);
                }
            }
        });
    }
// LocationManagerService.java
// use reportLocation to upload location to app
    public void reportLocation(Location location, boolean passive) {
        checkCallerIsProvider();

        if (!location.isComplete()) {
            Log.w(TAG, "Dropping incomplete location: " + location);
            return;
        }

        mLocationHandler.removeMessages(MSG_LOCATION_CHANGED, location);
        Message m = Message.obtain(mLocationHandler, MSG_LOCATION_CHANGED, location);
        m.arg1 = (passive ? 1 : 0);
        mLocationHandler.sendMessageAtFrontOfQueue(m);
    }

```

 

UpdatRecod class consist of LocationRequst, Receiver and so on.

Receiver class include listener, worksource and so on. Receriver will listen if client release or unbind.

ProviderRequest consist of all client LocationRequest. Variable reportLocaton is default false which show Provider is off.











