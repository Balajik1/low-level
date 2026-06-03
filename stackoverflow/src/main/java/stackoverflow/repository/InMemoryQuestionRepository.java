package stackoverflow.repository;

import stackoverflow.model.Question;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//Here we are not adding answer repository due to time constraint , but in actual project we must have it
public class InMemoryQuestionRepository implements QuestionRepository{
    //IMP Always store entity in map for easy retrieval
    private final Map<String, Question> questions=new ConcurrentHashMap<>();

    @Override
    public void save(Question question) {
        questions.put(question.getId(),question);
    }

    @Override
    public Optional<Question> findByQuestionId(String questionId) {
        return Optional.ofNullable(questions.get(questionId));
    }

    @Override
    public List<Question> findAll() {
        return List.copyOf(questions.values());
    }
}
