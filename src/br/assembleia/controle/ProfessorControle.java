package br.assembleia.controle;

import br.assembleia.entidades.Professor;
import br.assembleia.service.ProfessorService;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import java.util.Collections;
import java.util.List;
import javax.persistence.PersistenceException;

@Controller
@Scope("session")
public class ProfessorControle {

    private Professor profesor;
    private List<Professor> profesors;
    private List<Professor> profesorsFiltrados;
    private String titulo;

    @Autowired
    private ProfessorService service;

    @PostConstruct
    private void init() {
        profesor = new Professor();
    }

    public String novo() {
        profesor = new Professor();
        titulo = "Cadastrar Professor";
        return "professor?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (profesor != null) {
            titulo = "Editar Professor";
            return "professor?faces-redirect=true";
        }
        adicionaMensagem("Nenhum Item Professor foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String salvar() {

        try {
            service.salvar(profesor);
            adicionaMensagem("Professor salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            profesor = null;
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (profesor == null) {
                adicionaMensagem("Nenhum Professor foi selecionado para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }             
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoExcluirProfessor.show()");
        }
    }

    public String deletar() {
        try {
            service.deletar(profesor);
            profesors = null;
            adicionaMensagem("Professor Excluido com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem("O Professor pertence a algum curso, não pode ser excluído!", FacesMessage.SEVERITY_ERROR);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        profesor = null;
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public List<Professor> getProfessors() {
        profesors = service.listarTodos();

        Collections.sort(profesors);

        return profesors;
    }

    public void setProfessors(List<Professor> profesors) {
        this.profesors = profesors;
    }

    public Professor getProfessor() {
        return profesor;
    }

    public void setProfessor(Professor profesor) {
        this.profesor = profesor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Professor> getProfessorsFiltrados() {
        return profesorsFiltrados;
    }

    public void setProfessorsFiltrados(List<Professor> profesorsFiltrados) {
        this.profesorsFiltrados = profesorsFiltrados;
    }

}
