package pl.javowe.swirki.zzpjapp.documents.responseDocuments;

import lombok.Data;

import java.util.Map;

@Data
public class ForumResponseDocument {

    int amountOfThreads;
    int amountOfPosts;
    Map<String, Integer> amoutOfPostsinThread;


}
