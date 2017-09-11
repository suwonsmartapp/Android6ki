
package com.example.weather.models.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("pod")
    @Expose
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Sys{");
        sb.append("pod='").append(pod).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
