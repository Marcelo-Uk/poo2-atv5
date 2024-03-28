package entities;
import java.util.Date;
import entities.enums.DiaSemana;

public class EventoPeriodico extends Evento {
	
    private DiaSemana diaSemana;

    public EventoPeriodico(String titulo, String descricao, Date dataInicio, Date dataFim, String localizacao, DiaSemana diaSemana) {
        super(titulo, descricao, dataInicio, dataFim, localizacao);
        this.diaSemana = diaSemana;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }
}