package entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import entities.enums.DiaSemana;

public class Programa {
    public static void main(String[] args) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Scanner scanner = new Scanner(System.in);
            ArrayList<Evento> eventos = new ArrayList<>();

            boolean sair = false;
            while (!sair) {
                System.out.println("\nMenu:");
                System.out.println("1 - Adicionar evento");
                System.out.println("2 - Adicionar participante");
                System.out.println("3 - Imprimir Eventos");
                System.out.println("4 - Imprimir Participantes de um Evento");
                System.out.println("5 - Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("Cadastro de Evento:");
                        Evento evento = cadastrarEvento(scanner, df, eventos.size() + 1);
                        eventos.add(evento);
                        System.out.println("Evento adicionado com Sucesso");
                        break;
                    case 2:
                        if (eventos.isEmpty()) {
                            System.out.println("Não há eventos cadastrados.");
                            break;
                        }
                        System.out.println("Escolha o evento para adicionar participantes:");
                        for (int i = 0; i < eventos.size(); i++) {
                            System.out.println((i + 1) + ". " + eventos.get(i).getTitulo());
                        }
                        System.out.print("Digite o número do evento (1-" + eventos.size() + "): ");
                        int escolhaEvento = Integer.parseInt(scanner.nextLine());
                        Evento eventoEscolhido = eventos.get(escolhaEvento - 1);
                        adicionarParticipantes(scanner, eventoEscolhido);
                        break;
                    case 3:
                        if (eventos.isEmpty()) {
                            System.out.println("Não há eventos cadastrados.");
                        } else {
                            System.out.println("Eventos cadastrados:");
                            for (int i = 0; i < eventos.size(); i++) {
                                System.out.println((i + 1) + ". " + eventos.get(i).getTitulo());
                            }
                        }
                        break;
                    case 4:
                        if (eventos.isEmpty()) {
                            System.out.println("Não há eventos cadastrados.");
                        } else {
                            System.out.println("Escolha o evento para imprimir os participantes:");
                            for (int i = 0; i < eventos.size(); i++) {
                                System.out.println((i + 1) + ". " + eventos.get(i).getTitulo());
                            }
                            System.out.print("Digite o número do evento (1-" + eventos.size() + "): ");
                            int escolhaEventoImprimir = Integer.parseInt(scanner.nextLine());
                            Evento eventoEscolhidoImprimir = eventos.get(escolhaEventoImprimir - 1);
                            System.out.println("Participantes do evento '" + eventoEscolhidoImprimir.getTitulo() + "':");
                            eventoEscolhidoImprimir.imprimirParticipantes();
                        }
                        break;
                    case 5:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }

            scanner.close(); 

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public static boolean dataValida(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Evento cadastrarEvento(Scanner scanner, SimpleDateFormat df, int numeroEvento) throws Exception {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Evento evento;
        System.out.print("Tipo de Evento (Único, Recorrente, Periódico): ");
        String tipoEvento = scanner.nextLine();
        switch (tipoEvento.toLowerCase()) {
            case "único":
            	System.out.print("Data de Realização (dd/MM/yyyy): ");
                String dataRealizacaoStr = scanner.nextLine();
                if (!dataValida(dataRealizacaoStr)) {
                    throw new DataInvalidaException("Data de realização inválida. Formato esperado: dd/MM/yyyy");
                }
                Date dataRealizacao = df.parse(dataRealizacaoStr);
                System.out.print("Localização: ");
                String localizacaoUnico = scanner.nextLine();
                evento = new EventoUnico(titulo, descricao, dataRealizacao, dataRealizacao, localizacaoUnico);
                break;
            case "recorrente":
            	System.out.print("Data de Início (dd/MM/yyyy): ");
                String dataInicioStr = scanner.nextLine();
                if (!dataValida(dataInicioStr)) {
                    throw new DataInvalidaException("Data de início inválida. Formato esperado: dd/MM/yyyy");
                }
                Date dataInicio = df.parse(dataInicioStr);
                System.out.print("Data de Fim (dd/MM/yyyy): ");
                String dataFimStr = scanner.nextLine();
                if (!dataValida(dataFimStr)) {
                    throw new DataInvalidaException("Data de fim inválida. Formato esperado: dd/MM/yyyy");
                }
                Date dataFim = df.parse(dataFimStr);
                System.out.print("Localização: ");
                String localizacaoRecorrente = scanner.nextLine();
                evento = new EventoRecorrente(titulo, descricao, dataInicio, dataFim, localizacaoRecorrente);
                break;
            case "periódico":
            	System.out.print("Data de Início (dd/MM/yyyy): ");
                String dataInicioPeriodicoStr = scanner.nextLine();
                if (!dataValida(dataInicioPeriodicoStr)) {
                    throw new DataInvalidaException("Data de início do evento periódico inválida. Formato esperado: dd/MM/yyyy");
                }
                Date dataInicioPeriodico = df.parse(dataInicioPeriodicoStr);
                System.out.print("Data de Fim (dd/MM/yyyy): ");
                String dataFimPeriodicoStr = scanner.nextLine();
                if (!dataValida(dataFimPeriodicoStr)) {
                    throw new DataInvalidaException("Data de fim do evento periódico inválida. Formato esperado: dd/MM/yyyy");
                }
                Date dataFimPeriodico = df.parse(dataFimPeriodicoStr);
                System.out.print("Localização: ");
                String localizacaoPeriodico = scanner.nextLine();
                System.out.print("Dia da semana (DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO): ");
                DiaSemana diaSemana = DiaSemana.valueOf(scanner.nextLine().toUpperCase());
                evento = new EventoPeriodico(titulo, descricao, dataInicioPeriodico, dataFimPeriodico, localizacaoPeriodico, diaSemana);
                break;
            default:
                throw new Exception("Tipo de evento inválido.");
        }
        return evento;
    }

    public static void adicionarParticipantes(Scanner scanner, Evento evento) {
        System.out.println("Adicionando participantes para o evento '" + evento.getTitulo() + "':");
        while (true) {
            System.out.print("Nome do participante (ou 'fim' para sair): ");
            String nomeParticipante = scanner.nextLine();
            if (nomeParticipante.equalsIgnoreCase("fim")) {
                break;
            }
            Participante participante = new Participante(nomeParticipante);
            evento.adicionarParticipante(participante);
            System.out.println("Participante adicionado.");
        }
        System.out.println("Participantes do evento '" + evento.getTitulo() + "':");
        evento.imprimirParticipantes();
    }
}
