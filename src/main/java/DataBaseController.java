import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Level;

public class DataBaseController implements controlador, AutoCloseable {
    private final SessionFactory factory;
    private final Session session;
    private final CriteriaBuilder criteriaBuilder;

    public DataBaseController() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        this.session = this.factory.openSession();
        this.criteriaBuilder = this.session.getCriteriaBuilder(); // CORREGIDO (línea 17)
    }

    @Override
    public void close() throws Exception {
        this.session.close();
        this.factory.close();
    }

    @Override
    public contacto nuevoContacto(String nombre, String apellido, int telefono, String email) {
        contacto c = new contacto(0, nombre, apellido, telefono, email); // ID lo genera Hibernate
        Transaction transaction = this.session.beginTransaction();
        this.session.persist(c);
        transaction.commit();
        return c;
    }

    @Override
    public contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email) {
        contacto c = this.session.get(contacto.class, id);
        if (c != null) {
            Transaction tx = this.session.beginTransaction();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTelefono(telefono);
            c.setEmail(email);
            this.session.merge(c); // actualiza el objeto
            tx.commit();
        }
        return c;
    }

    @Override
    public boolean borrarContacto(int id) {
        contacto c = this.session.get(contacto.class, id);
        if (c != null) {
            Transaction tx = this.session.beginTransaction();
            this.session.remove(c);
            tx.commit();
            return true;
        }
        return false;
    }

    @Override
    public contacto buscarContactoPorId(int id) {
        return this.session.get(contacto.class, id);
    }

    @Override
    public List<contacto> buscarContactoPorNombre(String nombre) {
        return cercarContactesPerCamp("nombre", nombre);
    }

    @Override
    public List<contacto> buscarContactoPorApellido(String apellido) {
        return cercarContactesPerCamp("apellido", apellido);
    }

    @Override
    public List<contacto> buscarContactoPorTelefono(int telefono) {
        return cercarContactesPerCamp("telefono", String.valueOf(telefono));
    }

    @Override
    public List<contacto> buscarContactoPorEmail(String email) {
        return cercarContactesPerCamp("email", email);
    }

    // Método auxiliar para búsquedas genéricas
    private List<contacto> cercarContactesPerCamp(String camp, String valor) {
        CriteriaQuery<contacto> cr = this.criteriaBuilder.createQuery(contacto.class);
        Root<contacto> root = cr.from(contacto.class);

        // CORREGIDO: esta línea era la línea 50 problemática
        CriteriaQuery<contacto> query = cr.select(root).where(
                this.criteriaBuilder.like(this.criteriaBuilder.lower(root.get(camp).as(String.class)), "%" + valor.toLowerCase() + "%")
        );

        return this.session.createQuery(query).getResultList();
    }

    // (Opcional) Para obtener todos los contactos
    public List<contacto> getContactes() {
        CriteriaQuery<contacto> cr = this.criteriaBuilder.createQuery(contacto.class);
        Root<contacto> root = cr.from(contacto.class);
        CriteriaQuery<contacto> query = cr.select(root);
        return this.session.createQuery(query).getResultList();
    }
}


