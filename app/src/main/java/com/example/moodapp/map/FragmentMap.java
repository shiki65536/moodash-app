package com.example.moodapp.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.example.moodapp.R;
import com.example.moodapp.databinding.FragmentMapBinding;
import com.example.moodapp.utility.GetLocation;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMap extends Fragment implements GetLocation.OnLocationUpdateListener {

    private FragmentMapBinding mapBinding;
    private MapView mapView;
    private GetLocation getLocation;
    private String address;
    private static final String MAPBOX_ACCESS_TOKEN= "pk.eyJ1IjoibWFnaWNtaXp1a2kiLCJhIjoiY2xoM2MzZGJyMTd2bjNjcHFudXo2OG05OSJ9.VozXu-vmBXRyzdG0yhVIGA";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mapBinding = FragmentMapBinding.inflate(inflater);
        View view = mapBinding.getRoot();
        mapView = mapBinding.mapView;
        // call getLocation utility
        getLocation = new GetLocation(requireContext(), this);
        getLocation.startLocationUpdates();

        mapBinding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = mapBinding.searchText.getText().toString();
                if (address == null || address.isEmpty()) {
                    toastMsg("Please input the name or address");
                } else {
                    getLocation.stopLocationUpdates();
                    searchAddress(address);
                }
            }
        });
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //STOP Listening
        getLocation.stopLocationUpdates();
        mapBinding = null;
    }
    @Override
    //Get latitude and longitude automatically
    public void onLocationUpdate(double latitude, double longitude) {
        setMapBox(latitude, longitude);
    }

    /** MAPBOX **/
    // below is all MAPBOX setting
    private void setMapBox(double latitude, double longitude) {
//        final Point point = Point.fromLngLat(longitude, latitude);
        CameraOptions cameraPosition = new CameraOptions.Builder().zoom(13.0)
                .center(Point.fromLngLat(longitude, latitude))
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
        mapView.getMapboxMap().loadStyleUri(
                Style.MAPBOX_STREETS,
                new Style.OnStyleLoaded() {
                    @Override
                    //add notation
                    public void onStyleLoaded(@NonNull Style style) {
                        addAnnotationToMap(longitude, latitude);
                    }
                }
        );
    }

    //Add annotation
    private void addAnnotationToMap(double longitude, double latitude) {
        //define annotation img
        Bitmap bitmap = bitmapFromDrawableRes(getActivity(), R.drawable.red_marker);
        if (bitmap != null) {
            AnnotationPlugin annotationApi = AnnotationPluginImplKt.getAnnotations(mapView);
            PointAnnotationManager pointAnnotationManager =
                    PointAnnotationManagerKt.createPointAnnotationManager(
                            annotationApi, new AnnotationConfig());
            // Set options for the resulting symbol layer.
            PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions();
            // Define a geographic coordinate.
            pointAnnotationOptions.withPoint(Point.fromLngLat(longitude, latitude));
            // Specify the bitmap you assigned to the point annotation
            // The bitmap will be added to map style automatically.
            pointAnnotationOptions.withIconImage(bitmap);
            // Add the resulting pointAnnotation to the map.
            pointAnnotationManager.create(pointAnnotationOptions);
        }
    }

    private Bitmap bitmapFromDrawableRes(Context context, @DrawableRes int resourceId) {
        return convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId));
    }

    private Bitmap convertDrawableToBitmap(Drawable sourceDrawable) {
        if (sourceDrawable == null) {
            return null;
        }
        if (sourceDrawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) sourceDrawable).getBitmap();
        } else {
            // copying drawable object to not manipulate on the same reference
            Drawable drawable = sourceDrawable.getConstantState().newDrawable().mutate();
            Bitmap bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }

    //search
    private void searchAddress(String address) {
        MapboxGeocoding client = MapboxGeocoding.builder()
                .accessToken(MAPBOX_ACCESS_TOKEN)
                .query(address)
                .build();

        client.enqueueCall(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CarmenFeature> results = response.body().features();
                    if (results.size() > 0) {
                        CarmenFeature feature = results.get(0);
                        Point point = feature.center();
                        setMapBox(point.latitude(), point.longitude());
                        toastMsg("This is the location for your address!");
                    }
                }
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable t) {
                toastMsg("Can't search this address.");
            }
        });
    }
    public void toastMsg(String message){
        Context context = getContext();
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


}
