public class Forum {
    private ForumDB fdb;
    public Forum(ForumDB fdb) {
        this.fdb = fdb;
    }
    public void addSubforum(String title) {
        fdb.addSubforum(title);
    }

    public Subforum[] getSubforums() {
        return fdb.getSubforums();
    }
}
