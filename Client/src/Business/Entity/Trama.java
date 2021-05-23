package Business.Entity;

import java.io.Serializable;

/**
 * The type Trama.
 */
public class Trama implements Serializable {
    private final String context;

    /**
     * Instantiates a new Trama.
     *
     * @param context the context
     */
    public Trama(String context){
        this.context = context;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public String getContext() {
        return context;
    }
}
