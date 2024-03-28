package entities;
import java.util.Date;

public class EventoUnico extends Evento {

    public EventoUnico(String titulo, String descricao, Date dataInicio, Date dataFim, String localizacao) {
        super(titulo, descricao, dataInicio, dataFim, localizacao);
    }
}