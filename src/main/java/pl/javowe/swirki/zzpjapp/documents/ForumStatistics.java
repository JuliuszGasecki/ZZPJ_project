package pl.javowe.swirki.zzpjapp.documents;

import org.json.JSONException;
import org.json.JSONObject;
import pl.javowe.swirki.zzpjapp.documents.responseDocuments.ForumResponseDocument;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
    int amountOfThreads;
    int amountOfPosts;
    Map<String, Long> amoutOfPostsinThread;
 */
public class ForumStatistics implements DocumentCreator{



    public ForumStatistics(List<User> users, ForumServiceImpl repository) {
        this.users = users;
        this.repository = repository;
        threads = repository.getAll();
    }

    List<User> users;
    List<Thread> threads;
    ForumServiceImpl repository;
    @Override
    public JSONObject create()
    {
        JSONObject jsonObject = new JSONObject();
        ForumResponseDocument forumResponseDocument = fillForumResponseDocument();

        try {
            jsonObject.put("ForumDocument", forumResponseDocument);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private ForumResponseDocument fillForumResponseDocument(){
        ForumResponseDocument forumResponseDocument = new ForumResponseDocument();
        forumResponseDocument.setAmoutOfPostsinThread(amoutOfPostsinThread());
        forumResponseDocument.setAmountOfPosts(amoutOPosts());
        forumResponseDocument.setAmountOfThreads(amoutOfThreads());
        return forumResponseDocument;
    }
    private Map<String, Integer> amoutOfPostsinThread(){
        Map<String, Integer> amoutOfPostsinThread = new HashMap<>();
        for (Thread thread: this.threads) {
            List<Post> posts = this.repository.getPosts(thread);
            amoutOfPostsinThread.put(thread.getTitle(),
                    posts.size()
                    );
        }
        return amoutOfPostsinThread;
    }

    private int amoutOfThreads(){
        int amount = this.threads.size();
        return amount;
    }

    private int amoutOPosts() {
        int amount = 0;
        for (Thread thread : this.threads) {
            List<Post> posts = this.repository.getPosts(thread);
            amount += posts.size();
        }
        return amount;
    }

}
