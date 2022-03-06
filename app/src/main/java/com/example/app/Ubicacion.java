package com.example.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class Ubicacion implements LocationListener {
    private Context ctx;
    LocationManager locationManager;
    String provedor;
    private boolean net;

    public Ubicacion(Context ctx) {
        this.ctx = ctx;
        provedor=LocationManager.GPS_PROVIDER;
        locationManager= (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            return;

        }
        net=locationManager.isProviderEnabled(provedor);
        locationManager.requestLocationUpdates(provedor,1000,1,this);
    }

    private void getLocation(){
        if(net){
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
                return;
            }
            Location location= locationManager.getLastKnownLocation(provedor);
            if(location!=null){
                StringBuilder builder=new StringBuilder();
                builder.append(location.getLongitude()).append("").append(location.getLongitude());
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}
