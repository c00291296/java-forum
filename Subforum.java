public class Subforum {
        private int id;
        private ForumDB fdb;
    public Subforum(int id, ForumDB fdb) {
        this.id=id;
        this.fdb=fdb;
    }

    public String getTitle() {
        return fdb.getSubforumTitle(id);
    }

    public Post[] getPosts() {
        return fdb.getSubforumPosts(id);
    }
    
    public void addPost(Post post) {
        fdb.postPostToSubforum(id, post.getId());
    }

    public void removePost(Post post) {
        fdb.removePostFromSubforum(post.getId());
    }
}
