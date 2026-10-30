package polizas.Marcas;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import polizas.general.Conexion;
import polizas.general.DAO;

public class MarcasModelo extends Conexion implements DAO {

    MarcasModelo() {
        super();
    }

    @Override
    public List read() {
        List<Marcas> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT * FROM marca");
            rs = ps.executeQuery();
            lista.clear();
            while (rs.next()) {
                lista.add(new Marcas(rs.getInt("idMarca"), rs.getString("nombreMarca")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Marcas marca = (Marcas) obj;
            ps = cnx.prepareStatement("insert into marca (nombreMarca) values (?)");
            ps.setString(1, marca.getNombre());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion" + ex.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        try {
            this.conectar();
            Marcas marcas = (Marcas) obj;
            ps = cnx.prepareStatement("DELETE FROM marca WHERE idMarca = ?");
            ps.setInt(1, marcas.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion: " + ex.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Marcas marcas = (Marcas) obj;
            ps = cnx.prepareStatement("UPDATE marca SET nombreMarca = ? WHERE idMarca = ?");
            ps.setString(1, marcas.getNombre());
            ps.setInt(2, marcas.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion: " + ex.getMessage());
            }
        }
        return 0;

    }
}
