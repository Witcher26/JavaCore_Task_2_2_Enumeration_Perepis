import java.util.*;
import java.util.stream.Collectors;

import enumeration.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1_00; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(w -> w.getAge() < 18)
                .count();

        System.out.println("Количество лиц, младше 18 лет: " + count);

        List<String> recruit = persons.stream()
                .filter(w -> w.getAge() >= 18 && w.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Фамилии всех призывников: ");
        for (int i = 0; i < recruit.size(); i++) {
            System.out.println(i + " - " + recruit.get(i));
        }

        List<Person> ablePeople = persons.stream()
                .filter(w -> w.getEducation() == Education.HIGHER)
                .filter(w -> w.getAge() >= 18 && (w.getSex() == Sex.MAN ? w.getAge() <= 65 : w.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Фамилии всех трудоспособных: ");
        for (Person ablePersonTmp : ablePeople) {
            System.out.println(ablePersonTmp.getFamily());
        }
    }
}