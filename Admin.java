public class Admin extends Moderator {
    public Admin(int id, ForumDB fdb) {
        super(id, fdb);
    }

    public void appointModerator(User user) {
        super.getForumDB().appointModerator(user.getId());
    }
}
