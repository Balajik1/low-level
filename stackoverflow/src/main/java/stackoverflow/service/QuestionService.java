package stackoverflow.service;

import stackoverflow.dto.Filter;
import stackoverflow.enums.VoteType;
import stackoverflow.model.Answer;
import stackoverflow.model.Question;
import stackoverflow.repository.QuestionRepository;

import java.util.List;

public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public List<Question> findAll(Filter filter){
        return questionRepository.findAll().stream().filter(
                q -> {
                   //1. Tag filter : If tag is null, true. Otherwise, check if the question contains the tag.
                   boolean matchesTag = filter.getTag() == null || q.getTags().contains(filter.getTag());

                   //2. User Filter: If userId is null, true. Otherwise, check author id.
                   boolean matchesUser = filter.getAuthorId() == null || q.getAuthorId().equals(filter.getAuthorId());
                   //only return true if BOTH conditions are satisfied
                   return matchesTag && matchesUser;
                }
        ).toList();
    }

    public void voteQuestion(String questionId, VoteType voteType) {
        Question question = findQuestionOrElseThrow(questionId);
        question.vote(voteType);
//        questionRepository.save(question);
    }
    public Question findQuestionOrElseThrow(String questionId) {
        return questionRepository.findByQuestionId(questionId).orElseThrow(() -> new IllegalArgumentException("Question not found"));
    }
    public void voteAnswer(String questionId, String answerId, VoteType voteType) {
        Answer answer = findAnswerOrElseThrow(questionId, answerId);
        answer.vote(voteType);
    }
    public Answer findAnswerOrElseThrow(String questionId, String answerId) {
         Question question = findQuestionOrElseThrow(questionId);
         return question.getAnswers().stream().filter(a -> a.getId().equals(answerId)).findFirst().orElseThrow(() -> new IllegalArgumentException("Answer not found"));
    }
}
