package com.miempresa.tp1sharedprefmvvm.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.miempresa.tp1sharedprefmvvm.Model.Usuario;
import com.miempresa.tp1sharedprefmvvm.R;
import com.miempresa.tp1sharedprefmvvm.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
  private ActivityRegistroBinding binding;
  private RegistroActivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        //setContentView(R.layout.activity_registro);
        setContentView(binding.getRoot());
        Intent intent = getIntent();

       mv.getUsuario().observe(this, new Observer<Usuario>() {
           @Override
           public void onChanged(Usuario usuario) {
               binding.etApellido.setText(usuario.getApellido());
               binding.etDni.setText(usuario.getDni()+"");
               binding.etNombre.setText(usuario.getNombre());
               binding.etMail.setText(usuario.getMail());
               binding.etPassword.setText(usuario.getPassword());
           }
       });

        mv.usuarioSiONo(intent.getBooleanExtra("Log", false));

        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.guardarDatos(binding.etDni.getText().toString(), binding.etNombre.getText().toString(),
                        binding.etApellido.getText().toString(), binding.etMail.getText().toString(),
                        binding.etPassword.getText().toString());
            }
        });


    }
}