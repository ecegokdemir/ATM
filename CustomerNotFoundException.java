public class CustomerNotFoundException extends RuntimeException {
    private int id;
    private String name;
    private String surname;

    public CustomerNotFoundException(int id) {
        this.id = id;
        name = null;
        surname = null;
    }

    public CustomerNotFoundException(String name, String surname) {
        this.name = name;
        this.surname = surname;
        id = 0;
    }

    @Override
    public String toString() {
        if (name != null && surname != null)
            return "CustomerNotFoundException: name - " + name + " " + surname;
        else
            return "CustomerNotFoundException: id - " + id;

    }

}
