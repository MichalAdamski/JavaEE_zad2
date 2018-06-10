package michal.model;

public abstract class Library {
    long id = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    abstract boolean isAdded();
}
