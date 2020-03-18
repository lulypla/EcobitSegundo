package com.example.ecobit.Model;

import android.widget.EditText;

import java.io.Serializable;

public class User implements Serializable {

        private static int id;
        private String email;
        private String password;
        private String nombre;
        private String apellido;
        private String tipo_doc;
        private String nro_documento;
        private String fecha_nac;
        private String tel;
        private String foto;

        public User(String email, String password, String nombre, String apellido, String tipo_doc, String nro_documento, String fecha_nac, String tel, String foto) {
            this.id = id +1;
            this.email = email;
            this.password = password;
            this.nombre = nombre;
            this.apellido = apellido;
            this.tipo_doc = tipo_doc;
            this.nro_documento = nro_documento;
            this.fecha_nac = fecha_nac;
            this.tel = tel;
            this.foto = foto;
        }



    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(String nro_documento) {
        this.nro_documento = nro_documento;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
