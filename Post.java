public class Post {
    private int id;
    private ForumDB fdb;
    public Post(int id, ForumDB fdb) {
        this.id=id;
        this.fdb=fdb;
    }
    

    public void addAttachement(Attachement attachement) {

    }

    public String getTitle() {
        return fdb.getPostTitle(id);
    }

    public String getBody() {
        return fdb.getPostBody(id);
    }

    public String getDate() {
        return fdb.getPostDate(id).toString();
    }

    public String getAuthor() {
        return fdb.getPostAuthor(id);
    }

    public Attachement[] getAttachements() {
        return new Attachement[0];
    }

    public void getCensored() {
        fdb.censorPost(id);
    }

    public void setBody(String body) {
        fdb.setPostBody(id, body);
    }

    public int getId() {return id;}
}
