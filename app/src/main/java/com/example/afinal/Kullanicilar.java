package com.example.afinal;

public class Kullanicilar {
    private String Kullaniciadi,Hakkinda,Dogumyili;

    public Kullanicilar(String kullaniciadi, String hakkinda, String dogumyili) {
        Kullaniciadi = kullaniciadi;
        Hakkinda = hakkinda;
        Dogumyili = dogumyili;
    }

    public String getKullaniciadi() {
        return Kullaniciadi;
    }

    public String getHakkinda() {
        return Hakkinda;
    }

    public String getDogumyili() {
        return Dogumyili;
    }

    public void setKullaniciadi(String kullaniciadi) {
        Kullaniciadi = kullaniciadi;
    }

    public void setHakkinda(String hakkinda) {
        Hakkinda = hakkinda;
    }

    public void setDogumyili(String dogumyili) {
        Dogumyili = dogumyili;
    }
}
