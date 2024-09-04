package jp.ac.meijou.android.s231205090;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s231205090.databinding.ActivityMain4Binding;
import jp.ac.meijou.android.s231205090.databinding.ActivityMainBinding;

public class MainActivity4 extends AppCompatActivity {

    private ActivityMain4Binding binding;
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        Optional.ofNullable(result.getData())
                                .map(data ->data.getStringExtra("ret"))
                                .map(ret -> "Result: " + ret)
                                .ifPresent(text -> binding.view.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        binding.view.setText("Result: Canceled");
                    }
                    default -> {
                        binding.view.setText("Result: Unknown(" );
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 明示的
        binding.button1.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        // 暗黙的
        binding.button2.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        // 送信ボタン
        binding.sendButton.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", binding.text.getText().toString());
            startActivity(intent);
        });

        // 起動
        binding.startButton.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            getActivityResult.launch(intent);
        });
    }
}
