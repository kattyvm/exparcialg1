package exparcialg1.demo.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="usuarios")
public class UsuariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idusuarios;
    @Size(min=1,max = 2, message = "Ingrese máximo 40 caracteres.")
    @NotBlank(message = "Ingrese un nombre.")
    @Column(nullable = false)
    private String nombre;
    @Size(min=1,max = 2, message = "Ingrese máximo 40 caracteres.")
    @NotBlank(message = "Ingrese un apellido.")
    @Column(nullable = false)
    private String apellido;
    @Digits(integer = 8, fraction = 0, message = "Ingrese un número de DNI válido.")
    @Positive(message = "Ingrese un numero de DNI válido.")
    @NotNull(message = "Ingrese un numero de DNI.")
    @Column(nullable = false)
    private int dni;
    @Size(max = 50, message = "Ingrese máximo 50 caracteres.")
    @NotBlank(message = "Ingrese una direccion de correo electronico.")
    @Column(nullable = false)
    private String correo;
    @Size(max = 10, message = "Ingrese máximo 8 caracteres y 2 digitos.")
    @NotBlank(message = "Ingrese una contraseña.")
    @Column(nullable = false)
    private String pwd;
    @Column(nullable = false)
    private Boolean enabled;
    @ManyToOne
    @JoinColumn(name="idroles", nullable = false)
    private RolesEntity rol;

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public RolesEntity getRol() {
        return rol;
    }

    public void setRol(RolesEntity rol) {
        this.rol = rol;
    }
}
