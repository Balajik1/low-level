package stackoverflow.service;

import stackoverflow.dto.Filter;
import stackoverflow.enums.VoteType;
import stackoverflow.model.Answer;
import stackoverflow.model.Question;
import stackoverflow.model.Tag;
import stackoverflow.model.User;
import stackoverflow.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

//This is the entry pt of our application
//let user want to create a new user, it will call this method
//any operation in our application will come here
//client want to post question, it will come here
//This StackOverflowService will don't do anything by him, it will call other services
//follows the single responsibility principle and low coupling
public class StackOverflowService {
    //Thread safe collections for in-memory DB storage
    private final UserService userService;
    private final QuestionService questionService;

    public StackOverflowService(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    public User createUser(String username) {
        User user=new User(UUID.randomUUID().toString(),username);
        userService.saveUser(user);
        return user;
    }

    public Question askQuestion(String authorId, String title, String content, Set<Tag> tags) {
        // validating user
        userService.findByUserIdOrElseThrow(authorId);
        Question question=new Question(UUID.randomUUID().toString(),title, tags, content, authorId);
        questionService.save(question);
        return question;
    }

    public Answer answerQuestion(String authorId, String questionId, String content) {
        // validating user
        userService.findByUserIdOrElseThrow(authorId);
        Answer answer=new Answer(UUID.randomUUID().toString(),content,authorId,questionId);
       //Ideally we get question id and call Answer Service and have answerRepository but due to time constraint we are not adding it
       // questionService.save(answer);
        Question question=questionService.findQuestionOrElseThrow(questionId);
        question.addAnswer(answer);
        return answer;
    }
    public void voteQuestion(String questionId, VoteType voteType) {
        Question question = questionService.findQuestionOrElseThrow(questionId);
        User author = userService.findByUserIdOrElseThrow(question.getAuthorId());

        int reputationDelta = (voteType == VoteType.UPVOTE) ? 5 : -2;
        userService.updateReputation(author.getId(), reputationDelta);
        questionService.voteQuestion(questionId, voteType);
    }
    public void voteAnswer(String questionId, String answerId, VoteType voteType) {
        //Validation
        Answer answer = questionService.findAnswerOrElseThrow(questionId, answerId);
        User author = userService.findByUserIdOrElseThrow(answer.getAuthorId());

        int reputationDelta = (voteType == VoteType.UPVOTE) ? 10: -2;
        userService.updateReputation(author.getId(), reputationDelta);

        questionService.voteAnswer(questionId, answerId, voteType);
    }

    //Example search logic
    public List<Question> searchQuestion(Filter filter) {
        return questionService.findAll(filter);
    }
}
