package entities.observer;

import java.util.ArrayList;
import java.util.List;

public class EstacaoClimatica implements Subject {
    private List<Observer> observers;
    private float temperatura;
    private float umidade;
    private float pressao;

    public EstacaoClimatica() {
        observers = new ArrayList<>();
    }

    public void setMedicoes(float temperatura, float umidade, float pressao) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
        notificarObservers();
    }

    @Override
    public void adicionarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.atualizar(temperatura, umidade, pressao);
        }
    }
}
