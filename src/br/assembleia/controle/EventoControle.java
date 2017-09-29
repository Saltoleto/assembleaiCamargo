package br.assembleia.controle;

import br.assembleia.entidades.Convidado;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.assembleia.entidades.Evento;
import br.assembleia.entidades.Membro;
import br.assembleia.enuns.EnumMes;
import br.assembleia.service.EventoService;
import br.assembleia.service.MembroService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.PersistenceException;
import org.primefaces.context.RequestContext;

@Controller
@Scope("session")
public class EventoControle {

    private Evento evento;
    private List<Evento> eventos;
    private Convidado convidado;
    private Membro participante;
    private List<Membro> participantes;
    private List<Membro> membros;
    private int tab = 0;
    private List<Evento> eventosVisaoGeral;
    private boolean inicio;
    Calendar agora;
    private String mesExtenso;

    private String titulo;

    @Autowired
    private EventoService service;
    @Autowired
    private MembroService serviceMembro;

    @PostConstruct
    private void init() {
        evento = new Evento();
        agora = Calendar.getInstance();

    }

    public String novo() {
        evento = new Evento();
        titulo = "Cadastrar Evento";
        convidado = new Convidado();
        return "form?faces-redirect=true";
    }

    public String novoVisao() {
        evento = new Evento();
        titulo = "Cadastrar Evento";
        convidado = new Convidado();
        inicio = true;
        return "/evento/lista.xhtml?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (evento != null) {
            titulo = "Editar o Evento";
            convidado = new Convidado();
            return "form?faces-redirect=true";
        }
        adicionaMensagem("Nenhum Evento foi selecionado para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public String salvar() {

        try {
            service.salvar(evento);
            adicionaMensagem("Evento salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            evento = null;
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void adicionarConvidados() {

        if (convidado == null) {
            convidado = new Convidado();
        }
        if (!convidado.getNome().isEmpty() && !convidado.getFuncao().isEmpty()) {
            evento.getConvidados().add(convidado);
            convidado = new Convidado();
            tab = 0;
            RequestContext context = RequestContext.getCurrentInstance();
            context.addCallbackParam("loggedIn", true);
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.addCallbackParam("loggedIn", false);
        }

    }

    public void adicionarParticipantes() {
        if (participante.getId() != null) {
            evento.getParticipantes().add(participante);
            tab = 1;
            RequestContext context = RequestContext.getCurrentInstance();
            context.addCallbackParam("loggedIn", true);
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.addCallbackParam("loggedIn", false);
        }

    }

    public void retirarParticipanteLista() {

        if (evento != null) {
            evento.getParticipantes().remove(participante);
            tab = 1;
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoIntegrante.hide()");
        }

    }

    public void retirarConvidadoLista() {
 
        if (evento != null) {
            List<Convidado> convidados = new ArrayList<Convidado>();
            for (Convidado conv : evento.getConvidados()) {
                if (!conv.getNome().equals(convidado.getNome())) {
                    convidados.add(conv);
                } 
            }
            evento.setConvidados(convidados);
            convidado = new Convidado();
            tab = 0;
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoConvidado.hide()");
        }
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (evento == null) {
                adicionaMensagem("Nenhum Evento foi selecionado para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoMe.show()");
        }
    }

    public String deletar() {
        try {
            service.deletar(evento);
            adicionaMensagem("Evento excluido com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem("O  Evento não pode ser exlcuído!", FacesMessage.SEVERITY_INFO);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        evento = null;
        if (inicio) {
            inicio = false;
            return "/index.xhtml?faces-redirect=true";
        }
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public Convidado getConvidado() {
        if (convidado == null) {
            convidado = new Convidado();
        }
        return convidado;
    }

    public void setConvidado(Convidado convidado) {
        this.convidado = convidado;
    }

    public Membro getParticipante() {
        return participante;
    }

    public void setParticipante(Membro participante) {
        this.participante = participante;
    }

    public List<Membro> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Membro> participantes) {
        this.participantes = participantes;
    }

    public EventoService getService() {
        return service;
    }

    public void setService(EventoService service) {
        this.service = service;
    }

    public MembroService getServiceMembro() {
        return serviceMembro;
    }

    public void setServiceMembro(MembroService serviceMembro) {
        this.serviceMembro = serviceMembro;
    }

    public List<Membro> getMembros() {
        return membros = serviceMembro.listarTodos();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Evento> getEventos() {
        eventos = service.listarTodos();

        Collections.sort(eventos, new Comparator<Evento>() {
            @Override
            public int compare(Evento o1, Evento o2) {
                return o2.getDataInicio().compareTo(o1.getDataInicio());
            }
        });

        return eventos;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public List<Evento> getEventosVisaoGeral() {
        return eventosVisaoGeral = service.eventosVisaoGral();
    }

    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    public Calendar getAgora() {
        return agora;
    }

    public void setAgora(Calendar agora) {
        this.agora = agora;
    }

    public String getMesExtenso() {
        return mesExtenso = EnumMes.busca(agora.get(Calendar.MONTH)).toString();
    }

    public void setMesExtenso(String mesExtenso) {
        this.mesExtenso = mesExtenso;
    }

}
