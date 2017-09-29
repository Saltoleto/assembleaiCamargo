package br.assembleia.entidades;

import br.assembleia.enuns.EnumStatusAcervo;
import br.assembleia.enuns.EnumTipoAcervo;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fernandosaltoleto
 */
@Entity
public class Acervo implements Serializable, Comparable<Acervo> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private EnumTipoAcervo tipo;
    private String nome;
    private String autor;
    private String descricao;
    private EnumStatusAcervo status = EnumStatusAcervo.DISPONIVEL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 

    public EnumStatusAcervo getStatus() {
        return status;
    }

    public void setStatus(EnumStatusAcervo status) {
        this.status = status;
    }

    public EnumTipoAcervo getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoAcervo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acervo)) {
            return false;
        }
        Acervo other = (Acervo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public int compareTo(Acervo t) {
        return this.nome.compareTo(t.nome);
    }
}
