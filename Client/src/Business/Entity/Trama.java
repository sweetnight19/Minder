package Business.Entity;

import java.io.Serializable;

public class Trama implements Serializable {
    private final String context;

    public Trama(String context){
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
