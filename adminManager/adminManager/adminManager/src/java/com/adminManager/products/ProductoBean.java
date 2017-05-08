/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.products;

/**
 *
 * @author dideleo
 */
public final class ProductoBean {
    
    private final int id_producto;
    private int id_proveedor;
    private String nacional;
    private int id_familia;
    private int id_division;
    private int id_almacen;
    private String nombre;
    private String activo;
    private int id_unidadAlmacenamiento;
    private String oem;
    private String noPteProv;
    private String ubicacion;
    private String consignacion;
    private String foto;
    private String desc_esp;
    private String desc_ing;
    private String observaciones;
    private String nombre_proveedor;
    private String nombre_unidad;
    
    public ProductoBean(){
        
        this.id_producto = -1;
        this.nacional = "SI";
        this.consignacion = "NO";
        this.activo = "SI";
        
    }
    
    //Implementar constructor para update.

    public ProductoBean(int id_producto, int id_proveedor, String nacional, int id_familia, int id_division, int id_almacen, String nombre, String activo, int id_unidadAlmacenamiento, String oem, String noPteProv, String ubicacion, String consignacion, String desc_esp, String desc_ing, String observaciones, String nombre_proveedor,String unidad_nombre,String foto) {
        this.id_producto = id_producto;
        this.id_proveedor = id_proveedor;
        this.nacional = nacional;
        this.id_familia = id_familia;
        this.id_division = id_division;
        this.id_almacen = id_almacen;
        this.nombre = nombre;
        this.activo = activo;
        this.id_unidadAlmacenamiento = id_unidadAlmacenamiento;
        this.oem = oem;
        this.noPteProv = noPteProv;
        this.ubicacion = ubicacion;
        this.consignacion = consignacion;
        this.desc_esp = desc_esp;
        this.desc_ing = desc_ing;
        this.observaciones = observaciones;
        this.nombre_proveedor = nombre_proveedor;
        this.nombre_unidad = unidad_nombre;
        this.foto = foto;
    }

    public ProductoBean(int id_producto, String nombre, String noPteProv, String ubicacion) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.noPteProv = noPteProv;
        this.ubicacion = ubicacion;
    }
    

    
    

    public String getNombre_unidad() {
        return nombre_unidad;
    }

    public void setNombre_unidad(String nombre_unidad) {
        this.nombre_unidad = nombre_unidad;
    }
    
    

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }
    

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        
        activo = activo.trim();
        activo = activo.toUpperCase();
        this.activo = activo;
    }

    public String getConsignacion() {
        return consignacion;
    }

    public void setConsignacion(String consignacion) {
        consignacion = consignacion.trim();
        consignacion = consignacion.toUpperCase();
        this.consignacion = consignacion;
    }

    public String getDesc_esp() {
        return desc_esp;
    }

    public void setDesc_esp(String desc_esp) {
        desc_esp = desc_esp.trim();
        desc_esp = desc_esp.toUpperCase();
        this.desc_esp = desc_esp;
    }

    public String getDesc_ing() {
        return desc_ing;
    }

    public void setDesc_ing(String desc_ing) {
        desc_ing = desc_ing.trim();
        desc_ing = desc_ing.toUpperCase();
        this.desc_ing = desc_ing;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        foto = foto.trim();
        this.foto = foto;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_division() {
        return id_division;
    }

    public void setId_division(int id_division) {
        this.id_division = id_division;
    }

    public int getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_unidadAlmacenamiento() {
        return id_unidadAlmacenamiento;
    }

    public void setId_unidadAlmacenamiento(int id_unidadAlmacenamiento) {
        this.id_unidadAlmacenamiento = id_unidadAlmacenamiento;
    }

    public String getNacional() {
        return nacional;
    }

    public void setNacional(String nacional) {
        nacional = nacional.trim();
        nacional = nacional.toUpperCase();
        this.nacional = nacional;
    }

    public String getNoPteProv() {
        return noPteProv;
    }

    public void setNoPteProv(String noPteProv) {
        noPteProv = noPteProv.trim();
        noPteProv = noPteProv.toUpperCase();
        this.noPteProv = noPteProv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre.trim();
        nombre = nombre.toUpperCase();
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        observaciones = observaciones.trim();
        observaciones = observaciones.toUpperCase();
        this.observaciones = observaciones;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        oem = oem.trim();
        oem = oem.toUpperCase();
        this.oem = oem;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        ubicacion = ubicacion.trim();
        ubicacion = ubicacion.toUpperCase();
        this.ubicacion = ubicacion;
    }
    
    
    
}
