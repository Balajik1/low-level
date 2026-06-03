package stackoverflow.model;

import stackoverflow.enums.VoteType;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Question extends Post{
    private final String title;
    private final Set<Tag> tags;
    private final AtomicInteger voteCount;
    private final List<Answer> answers;
    private final List<Comment> comments;
    public Question(String id,String title,Set<Tag> tags, String content, String authorId) {
        super(id, content, authorId);
        this.title=title;
        this.tags=tags;
        this.voteCount=new AtomicInteger();
        //thread safe lists
        this.answers=new CopyOnWriteArrayList<>();
        this.comments=new CopyOnWriteArrayList<>();
    }
    public void vote(VoteType voteType){
        this.voteCount.addAndGet(voteType.getValue());
    }

    public void addAnswer(Answer answer){
        this.answers.add(answer);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public  String getTitle(){
        return title;
    }
    public Set<Tag> getTags(){
        return Set.copyOf(tags);
    }

    //returning copy of collection, so client or consumer dont modify by own or accidentally
    public List<Answer> getAnswers(){
        return List.copyOf(answers);
    }
    public List<Comment> getComments(){
        return List.copyOf(comments);
    }

    public int getVoteCount(){
       return this.voteCount.get();
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
