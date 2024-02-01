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
        return "";
    }

    public String getBody() {
        return "";
    }

    public String getDate() {
        return "";
    }

    public String getAuthor() {
        return "";
    }

    public Attachement[] getAttachements() {
        return new Attachement[0];
    }

    public void getCensored() {
        fdb.censorPost(id);
    }

    public void setBody(String body) {
        
    }
}
