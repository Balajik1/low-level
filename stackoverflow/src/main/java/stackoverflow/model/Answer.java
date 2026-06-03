package stackoverflow.model;

import stackoverflow.enums.VoteType;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Answer extends Post{

    private final String questionId;
    private boolean isAccepted;
    private final AtomicInteger voteCount;
    //Thread-safe list for Concurrent comment additions
    private final List<Comment> comments;

    public Answer(String id,String content,String authorId,String questionId){
        super(id,content,authorId);
        this.questionId=questionId;
        this.isAccepted=false;
        this.voteCount=new AtomicInteger();
        this.comments=new CopyOnWriteArrayList<>();
    }
    public void vote(VoteType voteType){
        this.voteCount.addAndGet(voteType.getValue());
    }

    public String getQuestionId() {
        return questionId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public AtomicInteger getVoteCount() {
        return voteCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment  comment){
        this.comments.add(comment);
    }

    public void accept(){
        this.isAccepted=true;
    }
    @Override
    public String toString(){
        return String.format("Answer: %s \n Author: %s \n Vote: %s ",getContent(),getAuthorId(),getVoteCount());
    }
}
