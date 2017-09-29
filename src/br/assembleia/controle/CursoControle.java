package br.assembleia.controle;

import br.assembleia.entidades.Aluno;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.assembleia.entidades.Curso;
import br.assembleia.entidades.Professor;
import br.assembleia.service.AlunoService;
import br.assembleia.service.CursoService;
import br.assembleia.service.ProfessorService;
import java.util.Collections;
import java.util.List;
import javax.persistence.PersistenceException;

@Controller
@Scope("session")
public class CursoControle {

    private Curso curso;
    private Professor professor;
    private Aluno aluno;
    private List<Curso> cursos;
    private List<Professor> professores;
    private List<Aluno> alunos;
    private List<Curso> cursosFiltrados;
    private String titulo;
    private int tab;

    @Autowired
    private CursoService service;
    @Autowired
    private AlunoService serviceAluno;
    @Autowired
    private ProfessorService serviceProfessor;

    @PostConstruct
    private void init() {
        curso = new Curso();
        titulo = "Lista de Cursos";
    }

    public String novo() {
        curso = new Curso();

        titulo = "Cadastrar Curso";
        return "form?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (curso != null) {
            titulo = "Editar Curso";
            return "form?faces-redirect=true";
        }
        adicionaMensagem("Nenhum Curso foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String adicionarAlunos() {
        curso.getAlunos().add(aluno);
        tab = 0;
        return "form?faces-redirect=true";
    }

    public String retirarAlunoLista() {
        if (curso != null) {
            curso.getAlunos().remove(aluno);
            tab = 0;
        }
        return "form?faces-redirect=true";
    }

    public String adicionarProfessores() {
        curso.getProfessores().add(professor);
        tab = 1;
        return "form?faces-redirect=true";
    }

    public String retirarProfessorLista() {
        if (curso != null) {
            curso.getProfessores().remove(professor);
            tab = 1;
        }
        return "form?faces-redirect=true";
    }

    public String salvar() {

        try {
            service.salvar(curso);
            adicionaMensagem("Curso salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            curso = null;
            tab = 0;
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (curso == null) {
                adicionaMensagem("Nenhum Curso foi selecionado para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("excluirCurso.show()");
        }
    }

    public String deletar() {
        try {
            service.deletar(curso);
            cursos = null;
            adicionaMensagem("Curso Excluido com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem("O Curso está emprestado, não pode ser exlcuído!", FacesMessage.SEVERITY_INFO);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        curso = null;
        tab = 0;
        titulo = "Cadastrar Curso   ";
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public List<Curso> getCursos() {
        cursos = service.listarTodos();

        Collections.sort(cursos);

        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Curso> getCursosFiltrados() {
        return cursosFiltrados;
    }

    public void setCursosFiltrados(List<Curso> cursosFiltrados) {
        this.cursosFiltrados = cursosFiltrados;
    }

    public List<Professor> getProfessores() {
        return professores = serviceProfessor.listarTodos();
    }

    public List<Aluno> getAlunos() {
        return alunos = serviceAluno.listarTodos();
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    

}
