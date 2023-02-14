package com.sb.themillgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sb.themillgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.touchPiece1.setOnClickListener(view -> {
            setPiece(view, binding.touchPiece1);
        });

        binding.touchPiece2.setOnClickListener(view -> setPiece(view, binding.touchPiece2));
        binding.touchPiece3.setOnClickListener(view -> setPiece(view, binding.touchPiece3));
        binding.touchPiece4.setOnClickListener(view->setPiece(view, binding.touchPiece4));
        binding.touchPiece5.setOnClickListener(view -> setPiece(view, binding.touchPiece5));
        binding.touchPiece6.setOnClickListener(view -> setPiece(view, binding.touchPiece6));
        binding.touchPiece7.setOnClickListener(view -> setPiece(view, binding.touchPiece7));
        binding.touchPiece8.setOnClickListener(view->setPiece(view, binding.touchPiece8));
        binding.touchPiece9.setOnClickListener(view -> setPiece(view, binding.touchPiece9));
        binding.touchPiece10.setOnClickListener(view -> setPiece(view, binding.touchPiece10));
        binding.touchPiece11.setOnClickListener(view -> setPiece(view, binding.touchPiece11));
        binding.touchPiece12.setOnClickListener(view->setPiece(view, binding.touchPiece12));
        binding.touchPiece13.setOnClickListener(view -> setPiece(view, binding.touchPiece13));
        binding.touchPiece14.setOnClickListener(view -> setPiece(view, binding.touchPiece14));
        binding.touchPiece15.setOnClickListener(view -> setPiece(view, binding.touchPiece15));
        binding.touchPiece16.setOnClickListener(view->setPiece(view, binding.touchPiece16));
        binding.touchPiece17.setOnClickListener(view -> setPiece(view, binding.touchPiece17));

    }

    private void setPiece(View view, Button b){

        b.setBackground(getDrawable(R.drawable.white_button));
    }
}