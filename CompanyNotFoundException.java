public class CompanyNotFoundException extends RuntimeException {
    private int id;
    private String name;

    public CompanyNotFoundException(int id) {
        this.id = id;
        name = null;
    }

    public CompanyNotFoundException(String name) {
        this.name = name;
        id = 0;
    }

    @Override
    public String toString() {
        if (name != null)
            return "CompanyNotFoundException: name - " + name;
        else
            return "CompanyNotFoundException: id - " + id;
    }

}
