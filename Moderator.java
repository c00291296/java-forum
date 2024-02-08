public class Moderator extends User {

    public Moderator(int id, ForumDB fdb) {
        super(id, fdb);
    }

    public void censorPost(Post post) {
        post.getCensored();
    }

    public void createSubforum(String name) {
        getForumDB().addSubforum(name);
    }
}
