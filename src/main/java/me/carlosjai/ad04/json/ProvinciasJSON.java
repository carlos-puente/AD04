package me.carlosjai.ad04.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProvinciasJSON {

    @SerializedName("provincias")
    @Expose
    private List<ProvinciaJSON> provincias = null;

    public List<ProvinciaJSON> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<ProvinciaJSON> provincias) {
        this.provincias = provincias;
    }

}
