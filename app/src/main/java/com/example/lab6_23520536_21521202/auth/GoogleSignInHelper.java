package com.example.lab6_23520536_21521202.auth;

import android.content.Context;
import android.content.Intent;
import com.example.lab6_23520536_21521202.R; // Thêm dòng này để gọi được R.string
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

public class GoogleSignInHelper {

    private final GoogleSignInClient googleSignInClient;

    public GoogleSignInHelper(Context context) {
        // ĐÃ BỔ SUNG: Yêu cầu Google cấp idToken cho Firebase
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public Intent getSignInIntent() {
        return googleSignInClient.getSignInIntent();
    }

    public static GoogleSignInAccount getAccountFromIntent(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            return task.getResult();
        } catch (Exception e) {
            return null;
        }
    }

    public GoogleSignInClient getClient() {
        return googleSignInClient;
    }
}