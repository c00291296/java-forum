public class User extends Guest {
    private int id;
    private ForumDB fdb;

    public User(int id, ForumDB fdb) {
        this.id = id;
        this.fdb = fdb;
    }

    public String getName() {
        return fdb.getUserName(id);
    }

    public void setName(String name) {
        fdb.setUserName(id, name);
    }

    protected int getId() {
        return id;
    }

    protected ForumDB getForumDB() {
        return fdb;
    }

    public Post createPost (String title, String body) {
        
    }
}
