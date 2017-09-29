package br.assembleia.controle;

import br.assembleia.entidades.Categoria;
import br.assembleia.entidades.Departamento;
import br.assembleia.entidades.Membro;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.assembleia.entidades.Receita;
import br.assembleia.enuns.EnumMes;
import br.assembleia.service.CategoriaService;
import br.assembleia.service.DepartamentoService;
import br.assembleia.service.DespesaService;
import br.assembleia.service.MembroService;
import br.assembleia.service.ReceitaService;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.persistence.PersistenceException;
import org.primefaces.context.RequestContext;

@Controller
@Scope("session")
public class ReceitaControle {

    private Receita receita;
    private List<Receita> receitas;
    private List<Receita> receitasFiltrados;
    private String titulo;
    private BigDecimal valorReceitaPeriodo;
    private static final Locale BRASIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRASIL);
    private int mesPesquisa = Calendar.getInstance().get(Calendar.MONTH);
    private int anoPesquisa = Calendar.getInstance().get(Calendar.YEAR);
    private List<Categoria> categorias;
    private List<Membro> membros;
    private List<Departamento> departamentos;
    private Categoria categoria;

//    TELA VISAO GERAL
    private List<Receita> ultimasReceitasVisaoGeral = new ArrayList<Receita>();
    private Boolean inicio;
    private Boolean fluxoCaixa = false;

//    USADOS PARA COMPOR O SALDO ATUAL
    private BigDecimal saldoAtual;
    private BigDecimal totalReceitasRecebidas;
    private BigDecimal totalDespesasPagas;

    @Autowired
    private ReceitaService service;
    @Autowired
    private DepartamentoService serviceDepartamento;
    @Autowired
    private CategoriaService serviceCategoria;
    @Autowired
    private MembroService serviceMembroService;
    @Autowired
    private DespesaService serviceDespesa;

    @PostConstruct
    private void init() {
        receita = new Receita();
        titulo = "Receitas";
        mesPesquisa = Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public void novaCategoria() {
        categoria = new Categoria();
    }

    public String novo() {
        receita = new Receita();
        titulo = "Nova Receita";
        inicio = false;
        fluxoCaixa = false;
        return "form?faces-redirect=true";
    }

    public String novoVisaoGeral() {
        receita = new Receita();
        titulo = "Nova Receita";
        fluxoCaixa = false;
        inicio = true;
        return "/receita/form.xhtml?faces-redirect=true";
    }

    public String novoFluxoCaixa() {
        receita = new Receita();
        titulo = "Nova Receita";
        fluxoCaixa = true;
        inicio = false;
        return "/receita/form.xhtml?faces-redirect=true";
    }

    public String carregarCadastro() {
        if (receita != null) {
            titulo = "Editar Receita";
            inicio = false;
            return "form?faces-redirect=true";
        }
        adicionaMensagem("Nenhuma Receita foi selecionada para a alteração!", FacesMessage.SEVERITY_INFO);
        return "lista?faces-redirect=true";

    }

    public void salvarCategoria() {
        try {
            if (!categoria.getDescricao().isEmpty()) {
                serviceCategoria.salvar(categoria);
                categorias = serviceCategoria.listarTodos();
                categoria = null;
                org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogCategoria').hide();");
            } else {

                RequestContext context = RequestContext.getCurrentInstance();
                context.addCallbackParam("loggedIn", false);
            }
        } catch (IllegalArgumentException e) {
            adicionaMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public String salvar() {

        try {
            service.salvar(receita);
            adicionaMensagem("Receita salva com sucesso!", FacesMessage.SEVERITY_INFO);
            receita = null;
        } catch (PersistenceException ex) {
            adicionaMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "lista?faces-redirect=true";
    }

    public void chamarExclusao() {
        if (new AplicacaoControle().validaUsuario()) {
            if (receita == null) {
                adicionaMensagem("Nenhuma Receita foi selecionada para a exclusão!", FacesMessage.SEVERITY_INFO);
                return;
            }
            org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmacaoMe.show()");
        }
    }

    public String deletar() {
        try {
            service.deletar(receita);
            receitas = null;
            adicionaMensagem("Receita excluída com Sucesso!", FacesMessage.SEVERITY_INFO);

        } catch (PersistenceException ex) {
            adicionaMensagem("O Receita está emprestado, não pode ser exlcuído!", FacesMessage.SEVERITY_INFO);
            voltar();
        }
        return "lista?faces-redirect=true";
    }

    public String voltar() {
        receita = null;
        if (inicio) {
            inicio = false;
            return "/index.xhtml?faces-redirect=true";
        }
        if (fluxoCaixa) {
            fluxoCaixa = false;
            return "/fluxocaixa/lista.xhtml?faces-redirect=true";
        }
        return "lista?faces-redirect=true";
    }

    public static void adicionaMensagem(String message, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, message, null));
    }

    public List<Receita> getReceitas() {
        if (anoPesquisa < 2014) {
            voltar();
        } else {
            receitas = service.listarReceitasMesAno(mesPesquisa, anoPesquisa);
            Collections.sort(receitas, new Comparator<Receita>() {
                @Override
                public int compare(Receita o1, Receita o2) {
                    return o1.getData().compareTo(o2.getData());
                }
            });

        }

        return receitas;
    }

    public String listar() {
        return "lista?faces-redirect=true";
    }

    public List<Receita> getUltimasReceitasVisaoGeral() {
        ultimasReceitasVisaoGeral = service.listarUltimasReceitasVisao(mesPesquisa, anoPesquisa);

        Collections.sort(ultimasReceitasVisaoGeral, new Comparator<Receita>() {
            @Override
            public int compare(Receita o1, Receita o2) {
                return o2.getData().compareTo(o1.getData());
            }
        });

        return ultimasReceitasVisaoGeral;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Receita> getReceitasFiltrados() {
        return receitasFiltrados;
    }

    public void setReceitasFiltrados(List<Receita> receitasFiltrados) {
        this.receitasFiltrados = receitasFiltrados;
    }

    public List<Categoria> getCategorias() {
        return categorias = serviceCategoria.listarTodos();
    }

    public String getValorTotalReceita() {
        valorReceitaPeriodo = service.valorReceitaPeriodo(mesPesquisa, anoPesquisa);
        if (valorReceitaPeriodo == null) {
            valorReceitaPeriodo = new BigDecimal(BigInteger.ZERO);
        }
        String teste = null;
        if (valorReceitaPeriodo != null) {
            DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", REAL);
            return teste = df.format(valorReceitaPeriodo);
        } else {
            return teste;
        }

    }

    public String getSaldoAtual() {
        totalReceitasRecebidas = service.listarReceitasRecebidas();
        totalDespesasPagas = serviceDespesa.listarDespesasPagas();
        if (totalReceitasRecebidas == null) {
            totalReceitasRecebidas = new BigDecimal(BigInteger.ZERO);
        }
        if (totalDespesasPagas == null) {
            totalDespesasPagas = new BigDecimal(BigInteger.ZERO);
        }

        saldoAtual = totalReceitasRecebidas.subtract(totalDespesasPagas);
        String teste = null;
        if (saldoAtual != null) {
            DecimalFormat df = new DecimalFormat("###,###,##0.00");
            return teste = df.format(saldoAtual);
        } else {
            return teste;
        }

    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public int getMesPesquisa() {
        return mesPesquisa;
    }

    public void setMesPesquisa(int mesPesquisa) {
        this.mesPesquisa = mesPesquisa;
    }

    public List<EnumMes> getListaMes() {
        return Arrays.asList(EnumMes.values());
    }

    public int getAnoPesquisa() {
        return anoPesquisa;
    }

    public void setAnoPesquisa(int anoPesquisa) {
        this.anoPesquisa = anoPesquisa;
    }

    public List<Membro> getMembros() {
        return membros = serviceMembroService.listarTodos();
    }

    public List<Departamento> getDepartamentos() {
        return departamentos = serviceDepartamento.listarTodos();
    }

    public BigDecimal getTotalReceitasRecebidas() {
        return totalReceitasRecebidas;
    }

    public void setTotalReceitasRecebidas(BigDecimal totalReceitasRecebidas) {
        this.totalReceitasRecebidas = totalReceitasRecebidas;
    }

    public BigDecimal getTotalDespesasPagas() {
        return totalDespesasPagas;
    }

    public void setTotalDespesasPagas(BigDecimal totalDespesasPagas) {
        this.totalDespesasPagas = totalDespesasPagas;
    }

    public Boolean getInicio() {
        return inicio;
    }

    public Boolean getFluxoCaixa() {
        return fluxoCaixa;
    }

    public void setFluxoCaixa(Boolean fluxoCaixa) {
        this.fluxoCaixa = fluxoCaixa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
