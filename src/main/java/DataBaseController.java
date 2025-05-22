import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Level;

public class DataBaseController implements Controlador, AutoCloseable {
    private final SessionFactory factory;
    private final Session session;
    private final CriteriaBuilder criteriaBuilder;

    public DataBaseController() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        this.session = this.factory.openSession();
        this.criteriaBuilder = this.session.getCriteriaBuilder();
    }

    @Override
    public void close() throws Exception {
        this.session.close();
        this.factory.close();
    }

    @Override
    public Contacto nuevoContacto(String nombre, String apellido, int telefono, String email) {
        Contacto c = new Contacto(nombre, apellido, telefono, email);
        Transaction transaction = this.session.beginTransaction();
        this.session.persist(c);
        transaction.commit();
        return c;
    }

    @Override
    public Contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email) {
        Contacto c = this.session.get(Contacto.class, id);
        if (c != null) {
            Transaction tx = this.session.beginTransaction();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTelefono(telefono);
            c.setEmail(email);
            this.session.merge(c);
            tx.commit();
        }
        return c;
    }

    @Override
    public boolean borrarContacto(int id) {
        Contacto c = this.session.get(Contacto.class, id);
        if (c != null) {
            Transaction tx = this.session.beginTransaction();
            this.session.remove(c);
            tx.commit();
            return true;
        }
        return false;
    }

    @Override
    public Contacto buscarContactoPorId(int id) {
        return this.session.get(Contacto.class, id);
    }

    @Override
    public List<Contacto> buscarContactoPorNombre(String nombre) {
        return buscarContactosPorCampo("nombre", nombre);
    }

    @Override
    public List<Contacto> buscarContactoPorApellido(String apellido) {
        return buscarContactosPorCampo("apellido", apellido);
    }

    @Override
    public List<Contacto> buscarContactoPorTelefono(int telefono) {
        return buscarContactosPorCampo("telefono", String.valueOf(telefono));
    }

    @Override
    public List<Contacto> buscarContactoPorEmail(String email) {
        return buscarContactosPorCampo("email", email);
    }

    private List<Contacto> buscarContactosPorCampo(String campo, String valor) {
        CriteriaQuery<Contacto> cr = this.criteriaBuilder.createQuery(Contacto.class);
        Root<Contacto> root = cr.from(Contacto.class);

        CriteriaQuery<Contacto> query;

        if (campo.equals("telefono")) {
            query = cr.select(root).where(
                    this.criteriaBuilder.equal(root.get(campo), Integer.parseInt(valor))
            );
        } else {
            query = cr.select(root).where(
                    this.criteriaBuilder.like(
                            this.criteriaBuilder.lower(root.get(campo).as(String.class)),
                            "%" + valor.toLowerCase() + "%"
                    )
            );
        }

        return this.session.createQuery(query).getResultList();
    }

    public List<Contacto> getContactos() {
        CriteriaQuery<Contacto> cr = this.criteriaBuilder.createQuery(Contacto.class);
        Root<Contacto> root = cr.from(Contacto.class);
        CriteriaQuery<Contacto> query = cr.select(root);
        return this.session.createQuery(query).getResultList();
    }
}
