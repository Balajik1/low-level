package demo;

import stackoverflow.dto.Filter;
import stackoverflow.enums.VoteType;
import stackoverflow.model.Answer;
import stackoverflow.model.Question;
import stackoverflow.model.Tag;
import stackoverflow.model.User;
import stackoverflow.repository.InMemoryQuestionRepository;
import stackoverflow.repository.InMemoryUserRepository;
import stackoverflow.service.QuestionService;
import stackoverflow.service.StackOverflowService;
import stackoverflow.service.UserService;

import java.util.List;
import java.util.Set;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println("--- Starting Stack Overflow LLD Demo ---");

        //1. Initialize System
        UserService userService=new UserService(new InMemoryUserRepository());
        QuestionService questionService=new QuestionService(new InMemoryQuestionRepository());
        StackOverflowService system=new StackOverflowService(userService, questionService);

        //2. Create User
        User user1=system.createUser("Alice_Dev");
        User bob=system.createUser("Bob_Coder");
        User user3=system.createUser("Fighter_123");

        //3. Alice ask question
        Tag javaTag=new Tag("java");
        Tag concurrencyTag=new Tag("concurrency");
        Tag hackingTag=new Tag("hacking");

        Question q1=system.askQuestion(
                user1.getId(),
                "How does ConcurrentHashMap works?",
                "I am confused about segment locking and node locking in java",
                Set.of(javaTag, concurrencyTag)
        );

        //Bob ask question
        Question q2=system.askQuestion(
                bob.getId(),
                "Can I hack NASA using HTML?",
                "Can I do without CSS",
                Set.of(hackingTag,concurrencyTag)
        );

        //Bob answer question
        Answer a1=system.answerQuestion(bob.getId(),
                q1.getId(),
                "In Java 8, ConcurrentHashMap uses CAS operations and synchronized blocks on the first node of the bin, abandoning the rest of the segment");

        //Printing Answer
        System.out.println("Answer: "+a1);

        //5. Simulate THread safe voting
        System.out.println("Simulation Concurrent upvotes ....");

        //we will simulate 100 threads upvoting Bob's answer simultaneously
        Runnable upvoteTask=()->{
            system.voteAnswer(q1.getId(), a1.getId(), VoteType.UPVOTE);
        };

        Thread[] threads=new Thread[100];
        for(int i=0;i<100;i++){
            threads[i]=new Thread(upvoteTask);
            threads[i].start();
        }

        //wait for all threads to complete
        for(int i=0;i<100;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //6 output results
        System.out.println("\n --- Final Results ----");
         System.out.println(a1);
         System.out.println("Question : "+q1.getTitle());
         System.out.println("Answer by : "+user1+" : "+a1.getContent());
         System.out.println("Answer Votes (Should be 100) : "+a1.getVoteCount());
         System.out.println("Bob's reputation (Should be 100*10) : "+bob.getReputation());

         //7. Test Search
         System.out.println("\n --- Search Results ----");
         List<Question> questions= system.searchQuestion(new Filter.Builder().tag(javaTag).authorId(user1.getId()).build());
         questions.forEach(System.out::println);

    }
}
