package entities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Evento {
    
	private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private String localizacao;
    private ArrayList<Participante> participantes;

    public Evento(String titulo, String descricao, Date dataInicio, Date dataFim, String localizacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.localizacao = localizacao;
        this.participantes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }


    public void adicionarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public void removerParticipante(Participante participante) {
        participantes.remove(participante);
    }

    public void imprimirParticipantes() {
        System.out.println("Lista de Participantes do Evento '" + titulo + "':");
        for (Participante participante : participantes) {
            System.out.println(participante.getNome());
        }
    }
    
    public static void imprimirEvento(Evento evento) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("pt", "BR"));

        System.out.println("Título: " + evento.getTitulo());
        System.out.println("Descrição: " + evento.getDescricao());
        System.out.println("Data de Início: " + dateFormat.format(evento.getDataInicio()));
        System.out.println("Data de Fim: " + dateFormat.format(evento.getDataFim()));
        System.out.println("Localização: " + evento.getLocalizacao());
        System.out.println();
    }
}