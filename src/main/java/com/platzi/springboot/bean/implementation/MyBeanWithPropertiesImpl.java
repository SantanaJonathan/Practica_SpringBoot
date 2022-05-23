package com.platzi.springboot.bean.implementation;

import com.platzi.springboot.bean.MyBeanWithProperties;

public class MyBeanWithPropertiesImpl implements MyBeanWithProperties {

    private String nombre;
    private String apellido;

    public MyBeanWithPropertiesImpl(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String pruebaProperties() {
        return nombre + " " + apellido;
    }
}
