package entities;
import java.util.ArrayList;
import java.util.Date;

public class EventoRecorrente extends Evento {
	
    private ArrayList<Date> datasRecorrentes;

    public EventoRecorrente(String titulo, String descricao, Date dataInicio, Date dataFim, String localizacao) {
        super(titulo, descricao, dataInicio, dataFim, localizacao);
        this.datasRecorrentes = new ArrayList<>();
    }

    public ArrayList<Date> getDatasRecorrentes() {
        return datasRecorrentes;
    }

    public void setDatasRecorrentes(ArrayList<Date> datasRecorrentes) {
        this.datasRecorrentes = datasRecorrentes;
    }
}