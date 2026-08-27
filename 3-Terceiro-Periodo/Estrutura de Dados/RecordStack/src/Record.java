import java.time.LocalDate;

public class Record {
private LocalDate date;
private double time;
private String name;

    //----------------Construtor-----------------------
    public Record(LocalDate date, double time, String name) {
        this.date = date;
        this.time = time;
        this.name = name;
    }

    //----------------Getters e Setters-----------------------
    public LocalDate getDate() {
        return date;
    }

    public double getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }


    //---------------toString-------------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return
                "Nome do recordista: " + name +
                "\nData do recorde: " + date +
                "\nTempo de recorde: " + time
                ;
    }
}
