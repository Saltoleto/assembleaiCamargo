package br.assembleia.controle;

import br.assembleia.entidades.Departamento;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.assembleia.entidades.Patrimonio;
import br.assembleia.enuns.EnumSituacaoPatrimonio;
import br.assembleia.service.DepartamentoService;
import br.assembleia.service.PatrimonioService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.persistence.PersistenceException;

@Controller
@Scope("session")
public class PatrimonioControle {

    private Patrimonio patrimonio;
    private List<Patrimonio> patrimonios;
    private List<Patrimonio> patrimoniosFiltrados;
    private String titulo;
    private List<Departamento> departamentos;
    private BigDecimal valorTotalPatrimonio;
    private static final Locale BRASIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRASIL);

    @Autowired
    private PatrimonioService service;
    @Autowired
    private DepartamentoService serviceDepartamento;

    @PostConstruct
    private void init() {
        patrimonio = new Patrimonio();
    }

    public String novo() {
        patrimonio = new Patrimonio();
        titulo = "Cadastrar Patrimônio";
        return "form?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (patrimonio != null) {
            titulo = "Editar Patrimônio";
            return "form?faces-redirect=true";
        }
        adicionaMensagem("Nenhum Patrimonio foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String salvar() {

        try {
            if (patrimonio.getQuantidade().intValue() == 0 || patrimonio.getQuantidade().toString().equals("")) {
                patrimonio.setQuantidade(BigDecimal.ONE);
            }
            patrimonio.setValorTotal(patrimonio.getValorUnitario().multiply(patrimonio.getQuantidade()));

            service.salvar(patrimonio);
            adicionaMensagem("Patrimonio salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            patrimonio = null;
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (patrimonio == null) {
                adicionaMensagem("Nenhum Patrimonio foi selecionado para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoMe.show()");
        }
    }

    public String deletar() {
        try {
            service.deletar(patrimonio);
            patrimonios = null;
            adicionaMensagem("Patrimônio excluído com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem("O Patrimonio está emprestado, não pode ser exlcuído!", FacesMessage.SEVERITY_INFO);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        patrimonio = null;
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public List<Patrimonio> getPatrimonios() {
        patrimonios = service.listarTodos();

        Collections.sort(patrimonios);

        return patrimonios;
    }

    public void setPatrimonios(List<Patrimonio> patrimonios) {
        this.patrimonios = patrimonios;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Patrimonio> getPatrimoniosFiltrados() {
        return patrimoniosFiltrados;
    }

    public void setPatrimoniosFiltrados(List<Patrimonio> patrimoniosFiltrados) {
        this.patrimoniosFiltrados = patrimoniosFiltrados;
    }

    public List<EnumSituacaoPatrimonio> getListaSituacaoPatrimonio() {
        return Arrays.asList(EnumSituacaoPatrimonio.values());
    }

    public List<Departamento> getDepartamentos() {
        return departamentos = serviceDepartamento.listarTodos();
    }

    public String getValorTotalPatrimonio() {
        valorTotalPatrimonio = service.valorPatrimonio();
        String teste = null;
        if (valorTotalPatrimonio!=null) {
            DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", REAL);
            return teste = df.format(valorTotalPatrimonio);
        } else {
            return teste;
        }

    }

}
