package rest;

import com.google.gson.Gson;
import dtos.AnimeDTO;
import dtos.ChuckJokeDTO;
import dtos.DadJokeDTO;
import dtos.UltimateJokeDTO;
import facades.AnimeFacade;
import facades.JokeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("anime")
public class AnimeResource {

    private EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();
    private AnimeFacade FACADE = AnimeFacade.getAnimeFacade(EMF);

@GET
@Produces({MediaType.APPLICATION_JSON})
@Path("random")
public Response getAnime() throws Exception {
    AnimeDTO animeDTO = FACADE.createAnimedDTO(FACADE.getHttpResponse("https://api.jikan.moe/v4/random/anime"));
    return Response.ok().entity(animeDTO).build();
}





}
