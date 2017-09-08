
package com.example.weather.models;


public class Weather {

    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Weather{");
        sb.append("id=").append(id);
        sb.append(", main='").append(main).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
