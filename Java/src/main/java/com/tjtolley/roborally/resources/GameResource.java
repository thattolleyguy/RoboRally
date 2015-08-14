package com.tjtolley.roborally.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.tjtolley.roborally.game.Game;
import com.tjtolley.roborally.game.GameManager;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import lombok.extern.apachecommons.CommonsLog;

@Path("/game")
@CommonsLog
public class GameResource
{
    private final GameManager gameManager;
    private final ObjectMapper mapper;

    @Inject
    public GameResource(GameManager gameManager, ObjectMapper mapper)
    {
        this.gameManager = gameManager;
        this.mapper = mapper;
    }

    @GET
    public Response getGames()
    {
        try {
            Collection<Game> games = gameManager.getGames();
            return Response.ok(mapper.writeValueAsString(games)).build();
        }
        catch (JsonProcessingException ex) {
            log.error("Error serializing game list", ex);
            return Response.serverError().build();
        }
    }

    @POST
    public Response createGame(String payload)
    {
        try {
            Map<String, Object> gameSettings = mapper.readValue(payload, Map.class);
            
            return Response.created(URI.create("/" + gameManager.createGame(gameSettings))).build();
        }
        catch (IOException ex) {
            log.error("Error creating game from settings");
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{gameId}")
    public Response getGame(@PathParam("gameId") UUID gameId)
    {
        try {
            Game game = gameManager.getGame(gameId);
            return Response.ok(mapper.writeValueAsString(game)).build();
        }
        catch (JsonProcessingException ex) {
            log.error("Error serializing game", ex);
            return Response.serverError().build();
        }
    }

}
