package br.assembleia.controle;

import br.assembleia.entidades.Congregacao;
import br.assembleia.entidades.Despesa;
import br.assembleia.entidades.ModeloClasseFluxocaixa;
import br.assembleia.entidades.Receita;
import br.assembleia.enuns.EnumMesInt;
import br.assembleia.service.CongregacaoService;
import br.assembleia.service.DespesaService;
import br.assembleia.service.ReceitaService;
import br.assembleia.util.ReportsUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fernandosaltoleto
 */
@Controller
@Scope("session")
public class FluxoCaixaControle {

    private static final Locale BRASIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRASIL);
    private int mesPesquisa = Calendar.getInstance().get(Calendar.MONTH);
    private int anoPesquisa = Calendar.getInstance().get(Calendar.YEAR);
    private String str;

    private List<Receita> listaReceitasFluxoCaixa = new ArrayList<Receita>();
    private List<Despesa> despesas = new ArrayList<Despesa>();
    private BigDecimal saldoAtual;
    private BigDecimal totalReceitasRecebidas;
    private BigDecimal totalDespesasPagas;
    private List<ModeloClasseFluxocaixa> listaFlusxoCaixa = new ArrayList<ModeloClasseFluxocaixa>();
    private StreamedContent file;
    private ReportsUtil report = new ReportsUtil();
    
    
//    LISTA DE RECEITAS PARA FLUXO DE CAIXA
    private BigDecimal valorPrevistoPeriodo;
    private BigDecimal receitasPeriodo;
    private BigDecimal despesasPeriodo;
    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private DespesaService despesaService;
    @Autowired
    private CongregacaoService serviceCongregacao;

    @PostConstruct
    private void init() {

        mesPesquisa = Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public String listar() {
        return "lista?faces-redirect=true";
    }

    public List<ModeloClasseFluxocaixa> getListaFlusxoCaixa() {
        List<Receita> listReceita = new ArrayList<Receita>();
        List<Despesa> listDespesa = new ArrayList<Despesa>();
        listaFlusxoCaixa = new ArrayList<ModeloClasseFluxocaixa>();

        listReceita = getListaReceitasFluxoCaixa();
        listDespesa = getDespesas();

        for (Receita rece : listReceita) {
            ModeloClasseFluxocaixa fluxo = new ModeloClasseFluxocaixa();
            fluxo.setDescricao(rece.getDescricao());
            fluxo.setCategoria(rece.getCategoria().getDescricao());
            if (rece.getMembro() != null) {
                fluxo.setMembroDepartamento(rece.getMembro().getNome());
            }
            fluxo.setValor(rece.getValorFormatado());
            fluxo.setRecebidoPago(rece.getRecebidoFormatado());
            fluxo.setData(rece.getData());
            fluxo.setTipo(0);
            listaFlusxoCaixa.add(fluxo);
        }

        for (Despesa desepes : listDespesa) {
            ModeloClasseFluxocaixa fluxo = new ModeloClasseFluxocaixa();
            fluxo.setDescricao(desepes.getDescricao());
            fluxo.setCategoria(desepes.getCategoria().getDescricao());
            if (desepes.getDepartamento() != null) {
                fluxo.setMembroDepartamento(desepes.getDepartamento().getDescricao());
            }
            fluxo.setValor(desepes.getValorFormatado());
            fluxo.setRecebidoPago(desepes.getRecebidoFormatado());
            fluxo.setData(desepes.getData());
            fluxo.setTipo(1);
            listaFlusxoCaixa.add(fluxo);

        }

        return listaFlusxoCaixa;
    } 

    public String getSaldoAtual() {
        totalReceitasRecebidas = receitaService.listarReceitasRecebidas();
        totalDespesasPagas = despesaService.listarDespesasPagas();
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

    public StreamedContent imprimir() throws SQLException, IOException, JRException, ClassNotFoundException, Throwable {
        Map parametros = new HashMap();
        List<Congregacao> listaCong = new ArrayList<Congregacao>();
        listaCong = serviceCongregacao.listarTodos();
        str = "Fluxo de Caixa - " + EnumMesInt.busca(mesPesquisa - 1).toString();

        for (Congregacao congre : listaCong) {
            InputStream is = new ByteArrayInputStream(congre.getLogoIgreja());
            parametros.put("nomeIgrena", congre.getNome());
            parametros.put("endereco", congre.getEndereco());
            parametros.put("telefone", congre.getTelefone());
            parametros.put("cidade", congre.getCidade());
            parametros.put("email", congre.getEmail());
            parametros.put("logo", is);
            parametros.put("cnpj", congre.getCnpj());
            parametros.put("uf", congre.getEstado().getUf());
            parametros.put("bairro", congre.getBairro());
            parametros.put("cep", congre.getCep());
            parametros.put("mesExtenso", EnumMesInt.busca(mesPesquisa - 1).toString().toUpperCase());
            parametros.put("ano", anoPesquisa);
            parametros.put("vp",getValorPrevistoPeriodo());
        }

        return file = (StreamedContent) report.gerarRelatorioPDFcomDSTeste(listaFlusxoCaixa, parametros, "/report/fluxocaixa.jasper", str);
 
    } 
 
    public String getValorPrevistoPeriodo() {
        receitasPeriodo = receitaService.valorReceitaPeriodo(mesPesquisa, anoPesquisa);
        despesasPeriodo = despesaService.valorDespesaPeriodo(mesPesquisa, anoPesquisa);
        String teste = null;
        if (receitasPeriodo == null) {
            receitasPeriodo = new BigDecimal(BigInteger.ZERO);
        }
        if (despesasPeriodo == null) {
            despesasPeriodo = new BigDecimal(BigInteger.ZERO); 
        }
        valorPrevistoPeriodo = receitasPeriodo.subtract(despesasPeriodo);

        DecimalFormat df = new DecimalFormat("###,###,##0.00", REAL);
        return teste = df.format(valorPrevistoPeriodo);
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public List<Despesa> getDespesas() {
        if (anoPesquisa < 2014) {
            voltar();
        } else {
            despesas = despesaService.listarDespesasMesAno(mesPesquisa, anoPesquisa);
            Collections.sort(despesas, new Comparator<Despesa>() {
                @Override
                public int compare(Despesa o1, Despesa o2) {
                    return o1.getData().compareTo(o2.getData());
                }
            });

        }

        return despesas;
    }

    public List<Receita> getListaReceitasFluxoCaixa() {
        listaReceitasFluxoCaixa = receitaService.listarReceitasMesAno(mesPesquisa, anoPesquisa);

        Collections.sort(listaReceitasFluxoCaixa, new Comparator<Receita>() {
            @Override
            public int compare(Receita o1, Receita o2) {
                return o1.getData().compareTo(o2.getData());
            }
        });

        return listaReceitasFluxoCaixa;
    }

    public String voltar() {
        return "lista?faces-redirect=true";
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

    public void setListaReceitasFluxoCaixa(List<Receita> listaReceitasFluxoCaixa) {
        this.listaReceitasFluxoCaixa = listaReceitasFluxoCaixa;
    }

    public int getMesPesquisa() {
        return mesPesquisa;
    }

    public void setMesPesquisa(int mesPesquisa) {
        this.mesPesquisa = mesPesquisa;
    }

    public int getAnoPesquisa() {
        return anoPesquisa;
    }

    public void setAnoPesquisa(int anoPesquisa) {
        this.anoPesquisa = anoPesquisa;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public ReportsUtil getReport() {
        return report;
    }

    public void setReport(ReportsUtil report) {
        this.report = report;
    }
  

    public void setValorPrevistoPeriodo(BigDecimal valorPrevistoPeriodo) {
        this.valorPrevistoPeriodo = valorPrevistoPeriodo;
    }

    public BigDecimal getReceitasPeriodo() {
        return receitasPeriodo;
    }

    public void setReceitasPeriodo(BigDecimal receitasPeriodo) {
        this.receitasPeriodo = receitasPeriodo;
    }

    public BigDecimal getDespesasPeriodo() {
        return despesasPeriodo;
    }

    public void setDespesasPeriodo(BigDecimal despesasPeriodo) {
        this.despesasPeriodo = despesasPeriodo;
    }
    
    

}
