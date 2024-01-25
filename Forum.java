public class Forum {
    private ForumDB fdb;
    public Forum(ForumDB fdb) {
        this.fdb = fdb;
    }
    public void addSubforum(String title) {

    }

    public Subforum[] getSubforums() {
        return new Subforum[0];
    }
}
