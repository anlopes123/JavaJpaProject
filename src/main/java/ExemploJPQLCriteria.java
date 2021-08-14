import digital.innovation.one.persistencia.Aluno;
import digital.innovation.one.persistencia.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExemploJPQLCriteria {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Estado estado = new Estado("São paulo", "SP");
//        Aluno aluno = new Aluno("Anizair", 35, estado);
//
//        try {
//
//            entityManager.getTransaction().begin();
//
//            entityManager.persist(estado);
//            entityManager.persist(aluno);
//            entityManager.persist(new Aluno("Maria Eduarda", 13, estado));
//            entityManager.persist(new Aluno("Rosimeire", 45, estado));
//            entityManager.persist(new Aluno("Laura", 30, estado));
//            entityManager.persist(new Aluno("Fernando", 38, estado));
//            entityManager.persist(new Aluno("Joaquim", 39, estado));
//
//
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//            entityManagerFactory.close();
//        }

         String nome = "Anizair";

//         String sql = "Select * from Aluno where nome = ?";
//
//        Aluno alunoSql = (Aluno) entityManager.createNativeQuery(sql, Aluno.class)
//                   .setParameter(1, nome)
//                   .getSingleResult();
//
//        String sqlList = "Select * from Aluno";
//        List<Aluno> alunoList = entityManager.createNativeQuery(sqlList, Aluno.class)
//                        .getResultList();
//
//        System.out.println("Consulta AlunoSql  " + alunoSql);
//        System.out.println("Consulta AlunoSqlList ");
//        alunoList.forEach(System.out::println);

//        //Agora JPQL
//        String jpql = "Select a from Aluno a where a.nome = :nome";
//        Aluno alunoJpql = entityManager.createQuery(jpql, Aluno.class)
//                           .setParameter("nome", nome)
//                            .getSingleResult();
//
//        String jpqlList = "Select a from Aluno a";
//        List<Aluno> alunoJpqlList = entityManager
//                   .createQuery(jpqlList, Aluno.class)
//                  .getResultList();
//        System.out.println("Consulta Alno Jpql  " + alunoJpql);
//        System.out.println("Consulta AlunojpqlList ");
//        alunoJpqlList.forEach(System.out::println);

        //Criteria
        CriteriaQuery<Aluno> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> alunoRoot = criteriaQuery.from(Aluno.class);
        CriteriaBuilder.In<String> inClausule = entityManager.getCriteriaBuilder().in(alunoRoot.get("nome"));
        inClausule.value(nome);
        criteriaQuery.select(alunoRoot).where(inClausule);
        Aluno alunoApiCriteria = entityManager.createQuery(criteriaQuery).getSingleResult();

        //trazendo uma lista
        CriteriaQuery<Aluno> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> alunoRootList = criteriaQueryList.from(Aluno.class);
        List<Aluno> alunoPCriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();

        System.out.println("Aluno Criteria Simple  " + alunoApiCriteria);
        System.out.println("Aluno Criteria List ");
        alunoPCriteriaList.forEach(System.out::println);




    }
}
