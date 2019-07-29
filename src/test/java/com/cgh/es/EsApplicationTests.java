package com.cgh.es;

import com.cgh.es.bean.Article;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() {
        Article article = new Article();
        article.setId(001);
        article.setAuthor("chen");
        article.setContext("Jest");
        article.setTitle("神经");

        Index type = new Index.Builder(article).index("cgh").type("news").build();

        try {
            jestClient.execute(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void search(){
        String json = "";

        Search search = new Search.Builder(json).addIndex("cgh").addType("news").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
