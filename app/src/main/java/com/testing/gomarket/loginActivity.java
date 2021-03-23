package com.testing.gomarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticCheckButton;

import dmax.dialog.SpotsDialog;

public class loginActivity extends AppCompatActivity {
    ElasticCheckButton iniciar;
    TextInputEditText email, password;
    private FirebaseAuth auth;
    AlertDialog alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //titulo centrado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);

        auth = FirebaseAuth.getInstance();
        iniciar = (ElasticCheckButton)findViewById(R.id.btnEnviar);
        email = findViewById(R.id.gmail);
        password = findViewById(R.id.password);
        alerta = new SpotsDialog.Builder().setContext(loginActivity.this).setMessage("Por favor espere...").build();
        getIniciar();

    }

    private void limpiar() {
      email.setText("");
      password.setText("");
    }

    private void getIniciar() {
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(user)){
                    Toast.makeText(getApplicationContext(), "Ingrese su correo",
                            Toast.LENGTH_LONG).show();
                }else{
                    if (TextUtils.isEmpty(pass)){
                        Toast.makeText(getApplicationContext(), "Ingrese su contraseña",
                                Toast.LENGTH_LONG).show();
                    }else{
                        alerta.show();
                        auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Correo o contraseña incorrecta",
                                            Toast.LENGTH_LONG).show();
                                } else {

                                    Intent intent = new Intent(loginActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                }
                                alerta.dismiss();
                            }
                        });
                        limpiar();
                    }
                }
            }
        });
    }
}