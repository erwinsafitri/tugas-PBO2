
package apppendaftaran;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


public class AppPendaftaran {

    public static void main(String[] args) {
        
         Pendaftar objDft = new Pendaftar();
    
        objDft.setHp("098123");
        objDft.setNama("erwin");
        //tambah
       AppPendaftaran tambah = new AppPendaftaran();
        tambah.Add(objDft);
        tambah.show(); 
    
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AppPendaftaranPU");
        EntityManager em = emf.createEntityManager();
        //update 
        em.getTransaction().begin();
        em.createQuery("UPDATE Pendaftar e " + 
                        "SET e.nama = ?1 " + 
                        "WHERE e.hp = ?2 ")
                .setParameter(1,"Safitri")
                .setParameter(2,"098123")
                .executeUpdate();
        em.getTransaction().commit();  
        //hapus 
        em.getTransaction().begin();
        String HpHapus = "12345";
        int hasilDel = 
                em.createQuery("DELETE FROM Pendaftar c WHERE c.hp = :par")
                .setParameter("par",HpHapus).executeUpdate();
        em.getTransaction().commit();        
    }
    public void Add(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AppPendaftaranPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public void show() {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AppPendaftaranPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(" SELECT e FROM Pendaftar e");
        List<Pendaftar> result = query.getResultList();
        for (Pendaftar e : result) {
            System.out.print(e.getId() + "/" + e.getHp()+ "/" +e.getNama());
        }
    }
}    