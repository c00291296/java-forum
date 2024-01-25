public class User extends Guest {
    private int id;
    private ForumDB fdb;

    public User(int id, ForumDB fdb) {
        this.id = id;
        this.fdb = fdb;
    }

    protected int getId() {
        return id;
    }

    protected ForumDB getForumDB() {
        return fdb;
    }
}
