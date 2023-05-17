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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.net.URI;
import java.util.List;

@ApplicationScoped
@Path("/api/v1/game")
@Tag(name = "Game Controller", description = "Games REST API")
public class GameController {

    private final GameService gameService;

    public GameController( GameService gameService) {
        this.gameService = gameService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "createGame",
            summary = "Create a new Game",
            description = "Create a new Game in database"

    )
    @APIResponse(
            responseCode = "201",
            description = "Operation completed"
    )
    public Response createGame(GameRequestDTO gameRequestDTO, @Context UriInfo uriInfo){
        GameResponseDTO game = gameService.createGame(gameRequestDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(game.id().toString()).build();
        return Response.created(uri).entity(game).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(
            operationId = "getGameById",
            summary = "Get game by Id",
            description = "Get game by ID in database"

    )
    @APIResponse(
            responseCode = "200",
            description = "Operation completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response findGameById(@PathParam("id") Long id){
        GameResponseDTO game = gameService.findGameById(id);
        return Response.ok(game).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getGames",
            summary = "Get Games",
            description = "Get all games in database with pagination"

    )
    @APIResponse(
            responseCode = "200",
            description = "Operation completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
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
    @Operation(
            operationId = "updateGame",
            summary = "Update a new Game",
            description = "Update a new Game in database"

    )
    @APIResponse(
            responseCode = "200",
            description = "Operation completed"
    )
    public Response updateGame(@PathParam("id") Long id,  @Valid GameRequestDTO gameRequestDTO){
        GameResponseDTO game = gameService.updateGame(id, gameRequestDTO);
        return Response.ok(game).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            operationId = "deleGameById",
            summary = "delete game by Id",
            description = "Delete game by ID in database"

    )
    @APIResponse(
            responseCode = "204",
            description = "Operation completed"
    )
    public Response deleGame(@PathParam("id") Long id){
        gameService.deleteGame(id);
        return Response.noContent().build();
    }

}
