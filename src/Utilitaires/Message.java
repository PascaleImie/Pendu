package Utilitaires;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Pierre on 30/11/2016.
 */
public class Message implements Serializable {


    private String cle;
    private Object value;

    public Message(String cle, Object value) {
        this.cle = cle;
        this.value = value;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}