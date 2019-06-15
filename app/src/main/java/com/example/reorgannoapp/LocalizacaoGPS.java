package com.example.reorgannoapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class LocalizacaoGPS implements LocationListener {
    Context context;

public LocalizacaoGPS(Context c){
    context = c;
}

    public Location getLocation(){
    if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED){
        Toast.makeText(context,"Permissão de gps negada", Toast.LENGTH_LONG).show();
    }
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGpsEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,10,this);
            Location retorno = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return retorno;
        }else {
            Toast.makeText(context,"Ative o GPS",Toast.LENGTH_LONG).show();
        }
        return null;

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
