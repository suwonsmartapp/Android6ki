
package com.example.weather.models.current;


public class Clouds {

    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Clouds{");
        sb.append("all=").append(all);
        sb.append('}');
        return sb.toString();
    }
}
