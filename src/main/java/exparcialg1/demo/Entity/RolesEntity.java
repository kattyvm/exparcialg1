package exparcialg1.demo.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class RolesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idroles;
    @Column(nullable = false)
    private String nombrerol;

    public int getIdroles() {
        return idroles;
    }

    public void setIdroles(int idroles) {
        this.idroles = idroles;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }
}
