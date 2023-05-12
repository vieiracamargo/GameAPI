package com.store.controller;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.service.GameService;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@ApplicationScoped
@Path("/api/v1/game")
public class GameController {

    private final GameService gameService;

    public GameController( GameService gameService) {
        this.gameService = gameService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(GameRequestDTO gameRequestDTO, @Context UriInfo uriInfo){
        GameResponseDTO game = gameService.createGame(gameRequestDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(game.id().toString()).build();
        return Response.created(uri).entity(game).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findGameById(@PathParam("id") Long id){
        GameResponseDTO game = gameService.findGameById(id);
        return Response.ok(game).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllGames(@QueryParam("page") @DefaultValue("0") int page,
                                 @QueryParam("size") @DefaultValue("5")int size,
                                 @QueryParam("sort") @DefaultValue("id") String sort,
                                 @QueryParam("direction") @DefaultValue("Ascending") Sort.Direction direction
    ){
        Page pageble = Page.of(page, size);
        List<GameResponseDTO> allGames = gameService.findAllGames(pageble, sort, direction);
        return Response.ok(allGames).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateGame(@PathParam("id") Long id,  @Valid GameRequestDTO gameRequestDTO){
        GameResponseDTO game = gameService.updateGame(id, gameRequestDTO);
        return Response.ok(game).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleGame(@PathParam("id") Long id){
        gameService.deleteGame(id);
        return Response.noContent().build();
    }

}
