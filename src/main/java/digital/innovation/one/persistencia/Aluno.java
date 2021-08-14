package digital.innovation.one.persistencia;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ALUNO")
@SequenceGenerator( name = "SEQ", sequenceName = "ALUNO_ID_GEN", allocationSize = 1)
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID", unique = true, updatable = false)
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "IDADE")
    private Integer idade;


    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ESTADO")
    private Estado estado;

    public Aluno(Integer id, String nome, Integer idade, Estado estado ) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public Aluno(String nome, Integer idade, Estado estado) {
        this.nome = nome;
        this.idade = idade;
        this.estado = estado;
    }

    public Aluno(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return id.equals(aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "digital.innovation.one.persistencia.Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", estado='" + estado + '\'' +
                '}';
    }
}
