package facades;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dtos.AnimeDTO;
import dtos.ChuckJokeDTO;
import dtos.DadJokeDTO;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnimeFacade {

    private static AnimeFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private AnimeFacade(){}

    // Method returns an instance of the FacadeExample class
    public static AnimeFacade getAnimeFacade(EntityManagerFactory entityManagerFactory1){
        if( instance == null) {
            entityManagerFactory = entityManagerFactory1;
            instance = new AnimeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();

    public String getHttpResponse(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        DataDTO dataDTO = GSON.fromJson(response.body(),DataDTO.class);
        JsonObject jo = dataDTO.data;

        String img = jo.get("images").getAsJsonObject().get("jpg").getAsJsonObject().get("image_url").getAsString();
        String title = jo.get("titles").getAsJsonArray().get(0).getAsJsonObject().get("title").getAsString();
        AnimeDTO animeDTO = new AnimeDTO(img,title);
        return GSON.toJson(animeDTO);

    }

    public AnimeDTO createAnimedDTO(String input){
        return GSON.fromJson(input, AnimeDTO.class);
    }

    public static void main(String[] args) throws Exception{
         EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

        AnimeFacade FACADE = AnimeFacade.getAnimeFacade(EMF);

        String txt = FACADE.getHttpResponse("https://api.jikan.moe/v4/random/anime");
        System.out.println(txt);
    }
    class DataDTO{
         public JsonObject data;
    }
}
