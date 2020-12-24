package com.example.smartchef;

import java.util.Date;

public class model
{
    String name,unidad;
    String stock;
    Date fecha;
    String purl;
    model()
    {

    }
    public model(String name, String stock, String unidad, Date fecha) {
        this.name = name;
        this.stock = stock;
        this.unidad = unidad;
        this.fecha = fecha;
        this.purl="https://img2.freepng.es/20180421/rbe/kisspng-vegetarian-cuisine-ingredient-food-computer-icons-ingredients-5adb227fb36d57.8326866015243106557349.jpg";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getStock() {
        return stock;
    }

    public String getPurl() {
        return "https://img2.freepng.es/20180421/rbe/kisspng-vegetarian-cuisine-ingredient-food-computer-icons-ingredients-5adb227fb36d57.8326866015243106557349.jpg";
    }
    public void setStock(String stock) {
        this.stock = stock;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
