package polizas;

import polizas.general.Bienvenida;
import javax.swing.SwingUtilities;
import polizas.general.Controlador;


  public class Inicio {
    private Controlador controlador;
    private Bienvenida vista;

    public Inicio() {
        vista = new Bienvenida();
        controlador = new Controlador(vista);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Inicio::new);
    }
    
}

