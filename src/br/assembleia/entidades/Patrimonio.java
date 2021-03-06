package br.assembleia.entidades;

import br.assembleia.enuns.EnumSituacaoPatrimonio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author fernandosaltoleto
 */
@Entity
public class Patrimonio implements Serializable, Comparable<Patrimonio> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer codigo;
    private String nome;
    private String descricao;
    @ManyToOne
    private Departamento departamento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAquisicao;
    private BigDecimal valorUnitario;
    private BigDecimal quantidade;
    private EnumSituacaoPatrimonio situacaoPatrimonio;
    private String obervacao;
    private BigDecimal valorTotal;
    private static final Locale BRASIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRASIL);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getValorUnitarioFormatado() {
        String teste = null;
        DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", REAL);
        teste = df.format(valorUnitario);

        return teste;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
    
    

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public EnumSituacaoPatrimonio getSituacaoPatrimonio() {
        return situacaoPatrimonio;
    }

    public void setSituacaoPatrimonio(EnumSituacaoPatrimonio situacaoPatrimonio) {
        this.situacaoPatrimonio = situacaoPatrimonio;
    }

    public String getObervacao() {
        return obervacao;
    }

    public void setObervacao(String obervacao) {
        this.obervacao = obervacao;
    }

    public String getValorTotal() {
        String teste = null;
        DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", REAL);
        teste = df.format(valorTotal);
        return teste;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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
        if (!(object instanceof Patrimonio)) {
            return false;
        }
        Patrimonio other = (Patrimonio) object;
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
    public int compareTo(Patrimonio t) {
        return this.nome.compareTo(t.nome);
    }
}
