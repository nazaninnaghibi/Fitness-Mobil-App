package com.example.fitness.view.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;


import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.fitness.R;
import com.example.fitness.databinding.FragmentFitnessDietDetailsBinding;
import com.example.fitness.model.ModelDetails;
import com.example.fitness.model.ModelFitnessDietDetails;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.adapter.ImageSliderAdapter;
import com.example.fitness.viewmodel.ViewModelAddFavorite;
import com.example.fitness.viewmodel.ViewModelFitnessDietDetails;


import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class FitnessDietDetailsFragment extends Fragment {
    FragmentFitnessDietDetailsBinding binding;
    NavController navController;
    IMapController iMapController;
    GeoPoint geoPoint;
    Marker marker;
    FitnessDietDetailsFragmentArgs args;
    String name, id;
    String ok = "ok";
    String error = "error";
    String yes = "Yes";
    String no = "No";
    String successfully_added = "Successfully added ";
    String is_available = "Is available";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = requireContext().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFitnessDietDetailsBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        ViewModelFitnessDietDetails viewmodel = new ViewModelProvider(this).get(ViewModelFitnessDietDetails.class);
        ViewModelAddFavorite viewModelAddFavorite = new ViewModelProvider(this).get(ViewModelAddFavorite.class);
        String user = Repository.Shared_Read(requireActivity());
        assert getArguments() != null;
        args = FitnessDietDetailsFragmentArgs.fromBundle(getArguments());
        name = Objects.requireNonNull(args.getFitnessDiet()).getTitle();
        id = args.getFitnessDiet().getId();
        viewmodel.ModelFitnessDietDetails(id);
        binding.textName.setText(name);
        viewModelAddFavorite.id = id;
        binding.setAdd(viewModelAddFavorite);
        viewModelAddFavorite.token = user;
        CheckBookmark();
        binding.imageBack.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
        viewmodel.mutableLiveDataPost.observe(requireActivity(), this::onChanged);
        viewModelAddFavorite.mutableLiveData.observe(requireActivity(), status -> {
            if (user == null) {
                navController.navigate(R.id.action_fitnessDietDetailsFragment_to_loginFragment);
                Toasty.error(requireActivity(), R.string.login_account, Toast.LENGTH_SHORT, true).show();
            } else {
                if (status.getStatus().equals(ok)) {
                    binding.imageAdd.setImageResource(R.drawable.bookmarkpor);
                    Toasty.success(requireActivity(), successfully_added + name, Toast.LENGTH_SHORT, true).show();
                } else if (status.getStatus().equals(error)) {
                    binding.imageAdd.setImageResource(R.drawable.bookmarkpor);
                    Toasty.error(requireActivity(), is_available, Toast.LENGTH_SHORT, true).show();
                }

            }
        });
        binding.imageShare.setOnClickListener(view -> {
            String text = "Diet name : " + name + "\n" + "Hi" + "\n" + "I like this diet\n" + "I suggest you watch it";
            Uri uri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/drawable/" + R.drawable.main);
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, text);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));

        });

        return binding.getRoot();

    }


    public void CheckBookmark() {
        String user = Repository.Shared_Read(requireActivity());
        assert getArguments() != null;
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().CheckFavorite(id, user), object -> {
            Status status = (Status) object;
            if (status.getStatus().equals(yes)) {
                binding.imageAdd.setImageResource(R.drawable.bookmarkpor);
            } else if (status.getStatus().equals(no)) {
                binding.imageAdd.setImageResource(R.drawable.bookmark);
            }

        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void Map(Double aLatitude, Double aLongitude, Double z) {
        binding.map.isTilesScaledToDpi();
        binding.map.getTilesScaleFactor();
        binding.map.setMultiTouchControls(true);
        binding.map.invalidate();
        iMapController = binding.map.getController();
        iMapController.setZoom(14.5);
        iMapController = binding.map.getController();
        geoPoint = new GeoPoint(aLatitude, aLongitude, z);
        iMapController.setCenter(geoPoint);
        binding.map.setZoomRounding(false);
        marker = new Marker(binding.map);
        marker.setIcon(requireActivity().getDrawable(R.drawable.ic_location));
        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        binding.map.getOverlays().add(marker);
        binding.map.addMapListener(new MapListener() {
            @Override
            public boolean onScroll(ScrollEvent event) {
                return false;
            }

            @Override
            public boolean onZoom(ZoomEvent event) {
                return true;
            }
        });
        marker.setOnMarkerClickListener((marker, mapView) -> {
            Toasty.info(requireActivity(), "This " + name + " is available here", Toast.LENGTH_SHORT, true).show();
            return true;
        });
    }


    private void onChanged(ModelFitnessDietDetails modelFitnessDietDetails) {
        ModelDetails modelFitnessDiet = new ModelDetails();
        binding.textDescription.setText(modelFitnessDietDetails.details.get(0).getDescription());
        modelFitnessDiet.setImage(modelFitnessDietDetails.details.get(0).getImage());
        Map(modelFitnessDietDetails.details.get(0).getaLatitude(), modelFitnessDietDetails.details.get(0).getaLongitude(), modelFitnessDietDetails.details.get(0).getZ());
        binding.sliderViewPager.setAdapter(new ImageSliderAdapter(modelFitnessDietDetails.slider));
        binding.sliderViewPager.setPadding(45, 0, 10, 0);
        binding.sliderViewPager.setCurrentItem(1);
        setupSliderIndicators(modelFitnessDietDetails.slider.size());
        binding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);

            }
        });
        binding.setData(modelFitnessDiet);
    }

    private void setupSliderIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(requireActivity());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.background_slider_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            binding.layoutSliderIndicators.addView(indicators[i]);
        }
        binding.layoutSliderIndicators.setVisibility(View.VISIBLE);
        setCurrentSliderIndicator(0);
    }

    private void setCurrentSliderIndicator(int position) {
        int childCount = binding.layoutSliderIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.layoutSliderIndicators.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(requireActivity(), R.drawable.background_slider_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(requireActivity(), R.drawable.background_slider_indicator_inactive)
                );
            }
        }

    }
}