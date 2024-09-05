package jp.ac.meijou.android.s231205090;

import android.os.Bundle;
import android.view.textclassifier.TextLinks;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import jp.ac.meijou.android.s231205090.databinding.ActivityMain5Binding;
import jp.ac.meijou.android.s231205090.databinding.ActivityMain6Binding;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity6 extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityMain6Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        var request = new Request.Builder()
                .url("https://placehold.jp/350x350.png")
                .build();
    }
}