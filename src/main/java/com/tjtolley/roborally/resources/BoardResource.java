package com.tjtolley.roborally.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.tjtolley.roborally.game.BoardConfiguration;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import lombok.extern.apachecommons.CommonsLog;

@Path("board")
@CommonsLog
public class BoardResource
{
	private final ObjectMapper mapper;

	@Inject
	public BoardResource(ObjectMapper mapper)
	{
		this.mapper = mapper;
	}
//
//	@GET
//	public Response getBoards()
//	{
//
//	}

	@GET
	@Path("{boardName}")
	public Response getGame(@PathParam("boardName") String boardName) throws IOException
	{
		try
		{
			BoardConfiguration configuration = BoardConfiguration.fromSavedBoard(boardName);
			return Response.ok(mapper.writeValueAsString(configuration)).build();
		}
		catch (JsonProcessingException ex)
		{
			log.error("Error serializing game", ex);
			return Response.serverError().build();
		}
	}

}
