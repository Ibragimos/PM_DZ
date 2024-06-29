import java.util.*;

public class PhoneBook {

    private Map<String, Set<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void addContact(String name, String number) {
        String lowercaseName = name.toLowerCase();
        
        for (Map.Entry<String, Set<String>> entry : phoneBook.entrySet()) {
            if (entry.getKey().toLowerCase().equals(lowercaseName)) {
                Set<String> numbers = entry.getValue();
                numbers.add(number);
                phoneBook.put(entry.getKey(), numbers);
                return;
            }
        }
        
        Set<String> numbers = new HashSet<>();
        numbers.add(number);
        phoneBook.put(name, numbers);
    }

    public Set<String> findNumbers(String name) {
        String lowercaseName = name.toLowerCase();
        return phoneBook.getOrDefault(lowercaseName, new HashSet<>());
    }

    public void displayAllContacts() {
        List<Map.Entry<String, Set<String>>> sortedContacts = new ArrayList<>(phoneBook.entrySet());
        
        sortedContacts.sort((a, b) -> b.getValue().size() - a.getValue().size()); 

        for (Map.Entry<String, Set<String>> entry : sortedContacts) {
            System.out.println("Name: " + entry.getKey() + " - Numbers: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact("Ivanov", "123456789");
        phoneBook.addContact("Ivanov", "987654321");
        phoneBook.addContact("Petrov", "555555555");
        phoneBook.addContact("Ivanov", "999999999");

        System.out.println("Numbers for Ivanov: " + phoneBook.findNumbers("Ivanov"));
        System.out.println("Numbers for Petrov: " + phoneBook.findNumbers("Petrov"));

        System.out.println("\nAll Contacts (sorted by number of phones):");
        phoneBook.displayAllContacts();
    }
}
