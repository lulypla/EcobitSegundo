package com.example.ecobit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.ecobit.Model.User;

/**
 * Clase dedicada a el manejo de la sesion
 */
public class Sesion  {

    final static private String prefs = "Preferencias";

    /**
     *
     * @param activity Hay que pasarle una actividad para obtener el usuario
     * @return Los datos del usuario que se habia logeado
     */
    public static User getUser(Activity activity) {
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(Sesion.prefs, Context.MODE_PRIVATE);
        User user = new User();
        user.setEmail(sharedPref.getString("email", null));
        // Si no hay email, dejamos de procesar y retornamos null de inmediato
        if(user.getEmail() == null) return null;
        user.setNombre(sharedPref.getString("nombre", user.getNombre()));
        user.setApellido(sharedPref.getString("apellido", user.getApellido()));
        user.setTipo_doc(sharedPref.getString("tipo_doc", user.getTipo_doc()));
        user.setNro_documento(sharedPref.getString("nro_documento", user.getNro_documento()));
        user.setFecha_nac(sharedPref.getString("fecha_nac", user.getFecha_nac()));
        user.setTel(sharedPref.getString("tel", user.getTel()));
        user.setFoto(sharedPref.getString("foto", user.getFoto()));
        user.setSaldo(sharedPref.getString("saldo", user.getSaldo()));
        return user;
    }

    /**
     *
     * @param user Se le pasa el usuario a setear
     * @param activity Se le pasa
     * @return El usuario loggeado
     */
    public static User  setUser(User user, Activity activity) {
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(Sesion.prefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", user.getEmail());
        editor.putString("nombre", user.getNombre());
        editor.putString("apellido", user.getApellido());
        editor.putString("tipo_doc", user.getTipo_doc());
        editor.putString("nro_documento", user.getNro_documento());
        editor.putString("fecha_nac", user.getFecha_nac());
        editor.putString("tel", user.getTel());
        editor.putString("foto", user.getFoto());
        editor.putString("saldo", user.getSaldo());
        editor.commit();
        return user;
    }

    /**
     * Limpia los datos del usuario loggeado
     * Cuidado con su uso
     * @param activity Actividad para poder ejecutar la funcion
     */
    public static void borrar(Activity activity) {
        activity.getApplicationContext().getSharedPreferences(Sesion.prefs, Context.MODE_PRIVATE).edit().clear().commit();
    }
}
