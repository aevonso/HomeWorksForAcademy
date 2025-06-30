package hw2.database;

import hw2.models.Fine;
import hw2.models.Person;

import java.util.*;

import static javax.swing.UIManager.get;

public class TaxDatabase {
    private Map<String, Person> personsById;
    private Map<String, List<Person>> personsByFineType;
    private Map<String, List<Person>> personsByCity;

    public TaxDatabase() {
        personsById = new HashMap<>();
        personsByFineType = new HashMap<>();
        personsByCity = new HashMap<>();
    }

    public void printAll() {
        if(personsById.isEmpty()) {
            System.out.println("База данных пуста:(");
            return;
        }

        System.out.println("Полная база данных:");
        for (Person person : personsById.values()) {
            System.out.println(person);
            System.out.println("-------------");
        }
    }

    private void updateIndexes(Person person, Fine fine) {
        personsByFineType.computeIfAbsent(fine.getType(), k->new ArrayList<>()).add(person);

        personsByCity.computeIfAbsent(fine.getCity(), k-> new ArrayList<>()).add(person);
    }
    private void removeFromIndexes(Person person, Fine fine) {
        List<Person> personsWithFineType = personsByFineType.get(fine.getType());
        if(personsWithFineType!=null) {
            personsWithFineType.remove(person);
            if(personsWithFineType.isEmpty()) {
                personsByFineType.remove(fine.getType());
            }
        }

        List<Person> personWithCity = personsByCity.get(fine.getCity());
        if(personWithCity !=null) {
            personWithCity.remove(person);
            if(personWithCity.isEmpty()) {
                personsByCity.remove(fine.getCity());
            }
        }
    }
    public void printById(String Id) {
        Person person = personsById.get(Id);
        if(person!=null) {
            System.out.println("Данные для кода " + Id + ":");
            System.out.println(person);
        }
        else {
            System.out.println("Человек с кодом " + Id + "не найден");
        }
    }
    public void printByFineType(String fineType) {
        List<Person> persons = personsByFineType.get(fineType);
        if(persons !=null && !persons.isEmpty()) {
            System.out.println("Люди со штрафом типа '" + fineType + "':");
            for(Person person : persons) {
                System.out.println(person.getName() + " (" + person.getIdCode() + ")");

                person.getFines().stream()
                        .filter(f->f.getType().equals(fineType))
                        .forEach(System.out::println);
                System.out.println("--------------");
            }
        }
        else {
            System.out.println("Нет людей со штрафом типа " +fineType);
        }
    }

    public void PrintByCity(String city) {
        List<Person> persons = personsByCity.get(city);
        if(persons !=null && !persons.isEmpty()) {
            System.out.println("Люди со штрафами в городе" + city + ":");
            for (Person person : persons) {
                System.out.println(person.getName() + " (" + person.getIdCode() + "(");
                person.getFines().stream()
                        .filter(f->f.getCity().equals(city))
                        .forEach(System.out::println);
                System.out.println("-------------");
            }
        }
        else {
            System.out.println("Нет людей со штрафами в городе" + city);
        }
    }

    public void addPerson(String id, String name, String address) {
        if(personsById.containsKey(id)) {
            System.out.println("Человек с кодом " + id + "уже существует");
            return;
        }
        Person person = new Person(id, name, address);
        personsById.put(id, person);
        System.out.println("Человек успешно добавлен:)");
    }

    public void addFineToPerson(String id, String type, double amount, String city, Date date) {
        Person person = personsById.get(id);
        if(person==null) {
            System.out.println("Человек с кодом " + id + "не найден");
            return;
        }
        Fine fine = new Fine(type, amount, city, date);
        person.AddFine(fine);

        updateIndexes(person, fine);

        System.out.println("Штраф успешно добавлен");
    }

    public void removeFine(String id, int fineIndex) {
        Person person = personsById.get(id);
        if(person==null) {
            System.out.println("Человек с кодом " + id + "не найден");
            return;
        }
        if(fineIndex < 0 || fineIndex >=person.getFines().size()) {
            System.out.println("Неверный индекс штрафа");
            return;
        }

        Fine fineToRemove = person.getFines().get(fineIndex);
        if(person.removeFine(fineToRemove)) {
            removeFromIndexes(person, fineToRemove);
            System.out.println("Штраф успешно удален");
        }
        else {
            System.out.println("Не удалось удалить штраф");
        }
    }

    public void updatePerson(String id, String newName, String newAddress, List<Fine> newFines) {
        Person person = personsById.get(id);
        if(person == null) {
            System.out.println("Человек с кодом " + id + "не найден");
            return;
        }

        for(Fine fine : person.getFines()) {
            removeFromIndexes(person, fine);
        }
        person.setName(newName);
        person.setAddress(newAddress);
        person.getFines().clear();
        person.getFines().addAll(newFines);

        for(Fine fine : newFines) {
            updateIndexes(person, fine);
        }

        System.out.println("Данные успешно добавлены");
    }
}
