package br.assembleia.controle;

import br.assembleia.entidades.Acervo;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.assembleia.entidades.Emprestimo;
import br.assembleia.entidades.Membro;

import br.assembleia.enuns.EnumStatuEmprestimo;
import br.assembleia.enuns.EnumStatusAcervo;
import br.assembleia.service.AcervoService;
import br.assembleia.service.EmprestimoService;
import br.assembleia.service.MembroService;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.PersistenceException;

@Controller
@Scope("session")
public class EmprestimoControle {

    private Emprestimo emprestimo;
    private List<Emprestimo> emprestimos;
    private List<Emprestimo> emprestimosFiltrados;
    private List<Membro> membros;
    private List<Acervo> listaAcervo;
    private String titulo;
    private Acervo itemAcervo;
    @Autowired
    private EmprestimoService service;
    @Autowired
    private MembroService membroService;
    @Autowired
    private AcervoService acervoService;

    @PostConstruct
    private void init() {
        emprestimo = new Emprestimo();
    }

    public String novo() {
        emprestimo = new Emprestimo();
        titulo = "Novo Empréstimo";
        return "form?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (emprestimo != null) {
            if (emprestimo.getStatuEmprestimo().equals(EnumStatuEmprestimo.SIM)) {
                adicionaMensagem("Empréstimo devolvido nao pode ser alterado!", FacesMessage.SEVERITY_INFO);
                return "lista?faces-redirect=true";
            }
            titulo = "Editar Empréstimo";
            return "form?faces-redirect=true";
        }
        adicionaMensagem("Nenhum emprestimo foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String salvar() {

        try {
            if (emprestimo != null && emprestimo.getStatuEmprestimo().equals(EnumStatuEmprestimo.SIM)) {
                service.editar(emprestimo);
            } else {
                itemAcervo = emprestimo.getAcervo();
                itemAcervo.setStatus(EnumStatusAcervo.EMPRESTADO);
                acervoService.editar(itemAcervo);
                emprestimo.setStatuEmprestimo(EnumStatuEmprestimo.NAO);
                service.salvar(emprestimo);
            }
            adicionaMensagem("Emprestimo salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void chamarDevolucao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (emprestimo == null) {
                adicionaMensagem("Nenhum emprestimo foi selecionado para a devolução!", FacesMessage.SEVERITY_INFO);
                return;
            }
            if (emprestimo.getStatuEmprestimo().equals(EnumStatuEmprestimo.SIM)) {
                adicionaMensagem("Devolução já efetuada!", FacesMessage.SEVERITY_INFO);
                emprestimo = null;
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoMe.show()");
        }
    }

    public String devolver() {
        try {
            itemAcervo = emprestimo.getAcervo();
            itemAcervo.setStatus(EnumStatusAcervo.DISPONIVEL);
            acervoService.editar(itemAcervo);
            emprestimo.setStatuEmprestimo(EnumStatuEmprestimo.SIM);
            service.editar(emprestimo);
            emprestimos = null;
            adicionaMensagem("Empréstimo Devolvido com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        emprestimo = null;
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public List<Emprestimo> getEmprestimos() {
        emprestimos = service.listarTodos();

        Collections.sort(emprestimos, new Comparator<Emprestimo>() {
            @Override
            public int compare(Emprestimo o1, Emprestimo o2) {
                return o2.getDataEmprestimo().compareTo(o1.getDataEmprestimo());
            }
        });

        return emprestimos;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Membro> getMembros() {
        return membros = membroService.listarTodos();
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    public List<Acervo> getListaAcervo() {

        listaAcervo = acervoService.listaAcervoStatus(EnumStatusAcervo.DISPONIVEL);

        return listaAcervo;
    }

    public Acervo getItemAcervo() {
        return itemAcervo;
    }

    public void setItemAcervo(Acervo itemAcervo) {
        this.itemAcervo = itemAcervo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Emprestimo> getEmprestimosFiltrados() {
        return emprestimosFiltrados;
    }

    public void setEmprestimosFiltrados(List<Emprestimo> emprestimosFiltrados) {
        this.emprestimosFiltrados = emprestimosFiltrados;
    }

}
