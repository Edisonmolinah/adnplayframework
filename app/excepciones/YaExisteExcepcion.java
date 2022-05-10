package excepciones;

public class YaExisteExcepcion extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public YaExisteExcepcion(String mensaje){
        super(mensaje);
    }
}
