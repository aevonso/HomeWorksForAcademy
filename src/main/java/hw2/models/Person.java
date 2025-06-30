package hw2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String id;
    private String name;
    private String address;
    private List<Fine> fines;


    public Person(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fines = new ArrayList<>();
    }

    public String getIdCode() {
        return  id;
    }
    public String getName() {
        return  name;
    }
    public String getAddress() {
        return  address;
    }
    public List<Fine> getFines() {
        return fines;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void AddFine(Fine fine) {
        fines.add(fine);
    }
    public boolean removeFine(Fine fine) {
        return fines.remove(fine);
    }

    @Override
    public String toString() {
        return "Код: " + id + ", Имя: " + name + ", Адрес: "  +address +
                "\nШтрафы:\n" + fines.stream().map(Fine::toString).collect(Collectors.joining("\n"));
    }
}
