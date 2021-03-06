package br.assembleia.entidades;

import br.assembleia.enuns.EnumEstado;
import br.assembleia.enuns.EnumEstadoCivil;
import br.assembleia.enuns.EnumSexo;
import br.assembleia.enuns.EnumSituacao;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author fernandosaltoleto
 */
@Entity
public class Membro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private String cpf;
    private String rg;
    private EnumEstadoCivil estadoCivil;
    private EnumSexo sexo;
    private String nomeConjuge;
    private int quantidadeFilhos;
    private String nomePai;
    private String nomeMae;
    private String nacionalidade;
    private String naturalidade;
    private EnumEstado estado;
    private String cidade;
    private String bairro;
    private String endereco;
    private String cep;
    private String telefone;
    private String celular;
    private String email;
    private String observacao;
    private boolean dizimista;

//   ----------------- DADOS ECLESIÁTICOS -------------------- 
    @Temporal(TemporalType.DATE)
    private Date membroDesde;
    @Temporal(TemporalType.DATE)
    private Date dataBatismo;
    @Temporal(TemporalType.DATE)
    private Date dataBatismoEspiritoSanto;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cargo cargo;
    @Temporal(TemporalType.DATE)
    private Date dataConsagracao;
    private EnumSituacao situacao;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Membro lider;
    private String procedencia;
    @Transient
    private String codigoMembro;

//    ------------------FOTO-------------------
    private byte[] foto;
    @Transient
    private InputStream is;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public EnumEstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public EnumSexo getSexo() {
        return sexo;
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public int getQuantidadeFilhos() {
        return quantidadeFilhos;
    }

    public void setQuantidadeFilhos(int quantidadeFilhos) {
        this.quantidadeFilhos = quantidadeFilhos;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public EnumEstado getEstado() {
        return estado;
    }

    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getMembroDesde() {
        return membroDesde;
    }

    public void setMembroDesde(Date membroDesde) {
        this.membroDesde = membroDesde;
    }

    public Date getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(Date dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public Date getDataBatismoEspiritoSanto() {
        return dataBatismoEspiritoSanto;
    }

    public void setDataBatismoEspiritoSanto(Date dataBatismoEspiritoSanto) {
        this.dataBatismoEspiritoSanto = dataBatismoEspiritoSanto;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Date getDataConsagracao() {
        return dataConsagracao;
    }

    public void setDataConsagracao(Date dataConsagracao) {
        this.dataConsagracao = dataConsagracao;
    }

    public EnumSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacao situacao) {
        this.situacao = situacao;
    }

    public Membro getLider() {
        return lider;
    }

    public void setLider(Membro lider) {
        this.lider = lider;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public boolean isDizimista() {
        return dizimista;
    }

    public void setDizimista(boolean dizimista) {
        this.dizimista = dizimista;
    }

    public String getDizimistaFormatado() {
        if (dizimista) {
            return "Sim";
        } else {
            return "Não";
        }
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
        if (!(object instanceof Membro)) {
            return false;
        }
        Membro other = (Membro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getCodigoMembroFormatado() {
        DecimalFormat df = new DecimalFormat("0000");
        codigoMembro = df.format(getId());
        return codigoMembro;
    }

    public String getCodigoMembro() {
        return codigoMembro;
    }

    public void setCodigoMembro(String codigoMembro) {
        this.codigoMembro = codigoMembro;
    }

}
