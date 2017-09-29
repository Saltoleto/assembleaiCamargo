package br.assembleia.controle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.assembleia.entidades.Aluno;
import br.assembleia.entidades.Cargo;
import br.assembleia.enuns.EnumEstado;
import br.assembleia.enuns.EnumEstadoCivil;
import br.assembleia.service.AlunoService;
import br.assembleia.service.CargoService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.persistence.PersistenceException;

@Controller
@Scope("session")
public class AlunoControle {

    private Aluno aluno;
    private List<Aluno> alunos;
    private List<Aluno> alunosFiltrados;
    private String titulo;
    private List<Cargo> cargos;

    @Autowired
    private AlunoService service;
    @Autowired
    private CargoService serviceCargo;

    @PostConstruct
    private void init() {
        aluno = new Aluno();
    }

    public String novo() {
        aluno = new Aluno();
        titulo = "Cadastrar Aluno";
        return "aluno?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (aluno != null) {
            titulo = "Editar Aluno";
            return "aluno?faces-redirect=true";
        }
        adicionaMensagem("Nenhum  Aluno foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String salvar() {

        try {
            service.salvar(aluno);
            adicionaMensagem("Aluno salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            aluno = null;
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (aluno == null) {
                adicionaMensagem("Nenhum Aluno foi selecionado para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoExcluirAluno.show()");
        }
    }

    public String deletar() {
        try {
            service.deletar(aluno);
            alunos = null;
            adicionaMensagem("Aluno Excluido com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem("O Alunos está matriculado e nao pode ser excluído!", FacesMessage.SEVERITY_ERROR);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        aluno = null;
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public List<Aluno> getAlunos() {
        alunos = service.listarTodos();

        Collections.sort(alunos);

        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Aluno> getAlunosFiltrados() {
        return alunosFiltrados;
    }

    public void setAlunosFiltrados(List<Aluno> alunosFiltrados) {
        this.alunosFiltrados = alunosFiltrados;
    }

    public List<EnumEstadoCivil> getListaEstadoCivil() {
        return Arrays.asList(EnumEstadoCivil.values());
    }

    public List<EnumEstado> getListaEstado() {
        return Arrays.asList(EnumEstado.values());
    }

    public List<Cargo> getCargos() {
        return cargos = serviceCargo.listarTodos();
    }
    
    
}
