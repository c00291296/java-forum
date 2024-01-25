public class Subforum {
        private int id;
        private ForumDB fdb;
    public Subforum(int id, ForumDB fdb) {
        this.id=id;
        this.fdb=fdb;
    }

    public String getTitle() {
        return "";
    }

    public Post[] getPosts() {
        return new Post[0];
    }
    
    public void addPost(Post post) {

    }

    public void removePost(Post post) {
        
    }
}
