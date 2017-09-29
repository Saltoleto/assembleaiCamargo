package br.assembleia.controle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.assembleia.entidades.Acervo;
import br.assembleia.enuns.EnumStatusAcervo;
import br.assembleia.enuns.EnumTipoAcervo;
import br.assembleia.service.AcervoService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.persistence.PersistenceException;

@Controller
@Scope("session")
public class AcervoControle {

    private Acervo acervo;
    private List<Acervo> acervos;
    private List<Acervo> acervosFiltrados;
    private String titulo;

    @Autowired
    private AcervoService service;

    @PostConstruct
    private void init() {
        acervo = new Acervo();
    }

    public String novo() {
        acervo = new Acervo();
        titulo = "Cadastrar Item no Acervo";
        return "form?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (acervo != null) {
            titulo = "Editar Item do Acervo";
            return "form?faces-redirect=true";
        }
        adicionaMensagem("Nenhum Item do Acervo foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String salvar() {

        try {
            service.salvar(acervo);
            adicionaMensagem("Item do Acervo salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            acervo = null;
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (acervo == null) {
                adicionaMensagem("Nenhum Item do Acervo foi selecionado para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }
             if (acervo.getStatus().equals(EnumStatusAcervo.EMPRESTADO)) {
                adicionaMensagem("O Item do Acervo está emprestado, não pode ser exlcuído!", FacesMessage.SEVERITY_INFO);
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoMe.show()");
        }
    }

    public String deletar() {
        try {
            service.deletar(acervo);
            acervos = null;
            adicionaMensagem("Item Excluido com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem("O Item do Acervo está emprestado, não pode ser exlcuído!", FacesMessage.SEVERITY_INFO);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        acervo = null;
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public List<Acervo> getAcervos() {
        acervos = service.listarTodos();

        Collections.sort(acervos);

        return acervos;
    }

    public List<EnumTipoAcervo> getListaTipoAcervo() {
        return Arrays.asList(EnumTipoAcervo.values());
    }

    public void setAcervos(List<Acervo> acervos) {
        this.acervos = acervos;
    }

    public Acervo getAcervo() {
        return acervo;
    }

    public void setAcervo(Acervo acervo) {
        this.acervo = acervo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Acervo> getAcervosFiltrados() {
        return acervosFiltrados;
    }

    public void setAcervosFiltrados(List<Acervo> acervosFiltrados) {
        this.acervosFiltrados = acervosFiltrados;
    }

}
