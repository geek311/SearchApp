package com.mycompany.LogUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
 
@ManagedBean(name = "LNATableService")
@ApplicationScoped
public class LNATableService implements Serializable{
     
	private static final long serialVersionUID = 1L;

	private final static String[] colors;
     
    private final static String[] brands;
     
    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
         
        brands = new String[10];
        brands[0] = "Proj1";
        brands[1] = "Proj2";
        brands[2] = "Proj3";
        brands[3] = "Proj4";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }
     
    public List<ManifestRecord> createRecords(int size) {
        List<ManifestRecord> list = new ArrayList<ManifestRecord>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new ManifestRecord(getRandomId(), getRandomColor(), getRandomYear(), getRandomPrice(), getRandomColor(), getRandomId(), getRandomId()));
        }
         
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1990);
    }
     
    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }

    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getColors() {
        return Arrays.asList(colors);
    }
     
    public List<String> getBrands() {
        return Arrays.asList(brands);
    }
}