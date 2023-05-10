package com.miempresa.tp1sharedprefmvvm.ui.registro;

import android.app.Application;
import android.app.Person;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.miempresa.tp1sharedprefmvvm.Model.Usuario;
import com.miempresa.tp1sharedprefmvvm.request.ApiClient;
import com.miempresa.tp1sharedprefmvvm.ui.login.MainActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuario;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);



    }

    public LiveData<Usuario> getUsuario(){;
        if(usuario==null){
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }
    public void usuarioSiONo(boolean bool) {
        if(bool==true){
            File archivo = new File(getApplication().getApplicationContext().getFilesDir(), "usuario.dat");

            try {
                FileInputStream fis = new FileInputStream(archivo);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
                Usuario usuarioRec= (Usuario)ois.readObject();
                usuario.setValue(usuarioRec);
                ois.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public void guardarDatos(String dni, String nombre, String apellido, String mail, String password){
        long DNI=Long.parseLong(dni);
        Usuario usuario = new Usuario(DNI, nombre, apellido,mail,password);

        File archivo = new File(getApplication().getApplicationContext().getFilesDir(), "usuario.dat");

            try {
                FileOutputStream fo = new FileOutputStream(archivo);
                BufferedOutputStream bos = new BufferedOutputStream(fo);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(usuario);
                bos.flush();
                oos.close();
                Intent intent=new Intent(getApplication().getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().getApplicationContext().startActivity(intent);
            }catch (FileNotFoundException e){
                throw  new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



    }


}
