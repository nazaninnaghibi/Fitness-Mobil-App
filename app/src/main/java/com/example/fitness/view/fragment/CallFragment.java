package com.example.fitness.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.fitness.R;
import com.example.fitness.databinding.FragmentCallBinding;
import com.gruveo.sdk.Gruveo;
import com.gruveo.sdk.model.CallEndReason;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CallFragment extends Fragment {
    FragmentCallBinding binding;
    NavController navController;
    private static final int REQUEST_CALL = 1;
    public static final int RESULT_OK = -1;
    private static final String SIGNER_URL = "https://api-demo.gruveo.com/signer";
    CallEndReason endReason;
    String callCode;
    String leftMessageTo;
    int duration, messagesSent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCallBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
        binding.mainVideoButton.setOnClickListener(view -> initCall(true, false));
        binding.windowVideoButton.setOnClickListener(view -> initCall(true, true));
        binding.mainVoiceButton.setOnClickListener(view -> initCall(false, false));
        return binding.getRoot();
    }

    private void initCall(Boolean videoCall, Boolean windowCall) {
        final Bundle otherExtras = new Bundle();
        otherExtras.putBoolean(Gruveo.GRV_EXTRA_VIBRATE_IN_CHAT, false);
        otherExtras.putBoolean(Gruveo.GRV_EXTRA_DISABLE_CHAT, false);
        final String code = binding.mainEdittext.getText().toString();
        final Gruveo.Builder builder = new Gruveo.Builder(requireActivity())
                .callCode(code)
                .videoCall(videoCall)
                .clientId("demo")
                .requestCode(REQUEST_CALL)
                .otherExtras(otherExtras)
                .eventsListener(eventsListener);

        if (windowCall) {
            builder.viewContainer(R.id.container_frg);
        }

        final String result = builder.build();

        switch (result) {
            case Gruveo.GRV_INIT_MISSING_CALL_CODE: {
                Toasty.error(requireActivity(), R.string.missing_call, Toast.LENGTH_SHORT, true).show();
                break;
            }
            case Gruveo.GRV_INIT_INVALID_CALL_CODE: {
                Toasty.error(requireActivity(), R.string.invalid_call, Toast.LENGTH_SHORT, true).show();
                break;
            }
            case Gruveo.GRV_INIT_MISSING_CLIENT_ID: {
                Toasty.error(requireActivity(), R.string.missing_client, Toast.LENGTH_SHORT, true).show();
                break;
            }
            case Gruveo.GRV_INIT_OFFLINE: {
                Toasty.error(requireActivity(), R.string.init_offline, Toast.LENGTH_SHORT, true).show();
                break;
            }
            default: {
                break;
            }
        }
    }

    private final Gruveo.EventsListener eventsListener = new Gruveo.EventsListener() {
        @Override
        public void callInit(boolean videoCall, @NonNull String code) {
        }

        @Override
        public void requestToSignApiAuthToken(@NonNull String token) {
            try {
                Gruveo.Companion.authorize(signToken(token));
            } catch (IOException ignored) {
            }
        }

        @Override
        public void callEstablished(@NonNull String code) {
        }

        @Override
        public void callEnd(@NonNull Intent data, boolean isInForeground) {
            parseCallExtras(data);
        }

        @Override
        public void recordingStateChanged(boolean us, boolean them) {
        }

        @Override
        public void recordingFilename(@NonNull String filename) {
        }
    };

    private String signToken(String token) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), token);
        Request request = new Request.Builder()
                .url(SIGNER_URL)
                .post(body)
                .build();

        Response response = new OkHttpClient().newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CALL && resultCode == RESULT_OK && data != null) {
            parseCallExtras(data);
        }
    }

    private void parseCallExtras(Intent data) {
        endReason = (CallEndReason) data.getSerializableExtra(Gruveo.GRV_RES_CALL_END_REASON);
        callCode = data.getStringExtra(Gruveo.GRV_RES_CALL_CODE);
        leftMessageTo = data.getStringExtra(Gruveo.GRV_RES_LEFT_MESSAGE_TO);
        duration = data.getIntExtra(Gruveo.GRV_RES_CALL_DURATION, 0);
        messagesSent = data.getIntExtra(Gruveo.GRV_RES_MESSAGES_SENT, 0);
    }

}