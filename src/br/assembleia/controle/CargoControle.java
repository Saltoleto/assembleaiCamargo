package br.assembleia.controle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.assembleia.entidades.Cargo;
import br.assembleia.service.CargoService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class CargoControle {

    private Cargo cargo;
    private List<Cargo> cargos;
    private List<Cargo> cargosFiltrados;
    private String titulo;

    @Autowired
    private CargoService service;

    @PostConstruct
    private void init() {
        cargo = new Cargo();
    }

    public String novo() {
        cargo = new Cargo(); 
        titulo = "Cadastro de Cargo";
        return "form?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (cargo != null) {
            titulo = "Editar Cargo";
            return "form?faces-redirect=true";
        }
        adicionaMensagem("Nenhum cargo foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String salvar() {
        try {
            service.salvar(cargo);
            adicionaMensagem("Cargo salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            cargo = null;
        } catch (IllegalArgumentException e) {
            adicionaMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (cargo == null) {
                adicionaMensagem("Nenhum cargo foi selecionado para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoMe.show()");
        }
    }

    public String deletar() {
        try {
            if (cargo != null) {

                service.deletar(cargo);
                cargos = null;
                adicionaMensagem("Cargo excluido com sucesso!", FacesMessage.SEVERITY_INFO);
            }

        } catch (PersistenceException ex) {
            adicionaMensagem("O cargo esta vinculado a algum membro, não pode ser excluído!", FacesMessage.SEVERITY_ERROR);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        cargo = null;
        return "lista?faces-redirect=true";
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Cargo> getCargos() {
        return cargos = service.listarTodos();
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public List<Cargo> getCargosFiltrados() {
        return cargosFiltrados;
    }

    public void setCargosFiltrados(List<Cargo> cargosFiltrados) {
        this.cargosFiltrados = cargosFiltrados;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    

}
