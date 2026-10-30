package polizas.Marcas;

import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {

    private MarcasVista mv;
    private MarcasModelo mm;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();
        marcasEventos();
    }

    public void marcasEventos() {

        mv.btn1.addActionListener(e -> {
            refrescar();
        });

        mv.btn_a.addActionListener(e -> {
            String nombre = mv.txt1.getText().trim();

            if (nombre.isEmpty()) {
                aviso("El nombre no puede estar vacio");
                return;
            }
            if (existeNombre(nombre, 0)) {
                aviso("Esa marca ya está registrada");
                return;
            }

            int resultado = mm.create(new Marcas(0, nombre));
            aviso(resultado > 0 ? "Marca agregada correctamente" : "No se pudo agregar la marca");
            limpiar();
            refrescar();
        });

        mv.btn_up.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(mv.txt2.getText().trim());
            } catch (NumberFormatException ex) {
                aviso("Selecciona un id que este apa");
                return;
            }

            String nombre = mv.txt1.getText().trim();

            if (nombre.isEmpty()) {
                aviso("El nombre no puede estar vacío");
                return;
            }
            if (existeNombre(nombre, id)) {
                aviso("Esa marca ya está registrada");
                return;
            }

            int resultado = mm.update(new Marcas(id, nombre));
            aviso(resultado > 0 ? "Actualizado correctamente" : "No se pudo actualizar");
            limpiar();
            refrescar();
        });

        mv.btn_eli.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(mv.txt2.getText().trim());
            } catch (NumberFormatException ex) {
                aviso("Selecciona escribe un id que este apa");
                return;
            }

            int resultado = mm.delete(new Marcas(id, ""));
            aviso(resultado > 0 ? "Eliminado correctamente" : "No se pudo eliminar");
            limpiar();
            refrescar();
        });

        mv.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    mv.txt2.setText(String.valueOf(mv.tabla.getValueAt(fila, 0)));
                    mv.txt1.setText(String.valueOf(mv.tabla.getValueAt(fila, 1)));
                }
            }
        });
    }

    private void refrescar() {
        List<Marcas> lista = mm.read();
        mv.actualizarVista(lista);
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(mv, mensaje);
    }

    private void limpiar() {
        mv.txt1.setText("");
        mv.txt2.setText("");
    }

    private boolean existeNombre(String nombre, int idPropio) {
        List<Marcas> todas = mm.read();
        for (Marcas m : todas) {
            if (m.getNombre().equalsIgnoreCase(nombre) && m.getId() != idPropio) {
                return true;
            }
        }
        return false;
    }
}
