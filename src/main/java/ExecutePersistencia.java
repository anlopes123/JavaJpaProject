import digital.innovation.one.persistencia.Aluno;
import digital.innovation.one.persistencia.Estado;
import org.dom4j.tree.DefaultEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExecutePersistencia {
    public static void main(String[] args) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa01");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Estado estado = new Estado("Rio de Janeiro", "RJ");
            Aluno aluno = new Aluno("Anizair", 35, estado);

        try {
//
//            entityManager.getTransaction().begin();
//
//            entityManager.persist(estado);
//            entityManager.persist(aluno);
//
//            entityManager.getTransaction().commit();
             Estado estadofind = entityManager.find(Estado.class, 3);
             Aluno alunofind = entityManager.find(Aluno.class, 3);

            System.out.println(estadofind);
            System.out.println(alunofind);

            entityManager.getTransaction().begin();

            alunofind.setNome("Maria Eduarda");
            alunofind.setIdade(13);

            entityManager.getTransaction().commit();

            System.out.println(alunofind);

            Aluno alunoRemover = entityManager.find(Aluno.class, 3);
            System.out.println("Aluno a remover " + alunoRemover);
            entityManager.getTransaction().begin();
            entityManager.remove(alunoRemover);
            entityManager.getTransaction().commit();

            Aluno alunoNull = entityManager.find(Aluno.class, 3);
            if (alunoNull == null) {
                System.out.println("Aluno removido");
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }


    }
}
