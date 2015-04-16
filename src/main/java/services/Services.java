package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Student;
import command.CreateStudentCommand;
import command.DeleteSongCommand;
import command.GetStudentCommand;
import command.ListStudentCommand;
import command.SearchCommand;

import util.Constants;

@Path("students")
public class Services {
	ObjectMapper mapper = new ObjectMapper();

	// Browse all songs
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response browseSongs(@QueryParam("offset") int offset,
			@QueryParam("count") int count) {
		ListStudentCommand command = new ListStudentCommand();
		ArrayList<Student> list = command.execute();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put(Constants.Pagination.DATA, list);
		hm.put(Constants.Pagination.OFFSET, offset);
		hm.put(Constants.Pagination.COUNT, count);
		String songString = null;
		try {
			songString = mapper.writeValueAsString(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}

	// get student by Id
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSong(@PathParam("id") int id) {
		GetStudentCommand command = new GetStudentCommand();
		String songString = null;
		try {
			songString = mapper.writeValueAsString(command.execute(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}

	// Add a song
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createSongs(String payload) {
		CreateStudentCommand create = new CreateStudentCommand();
		Student s = null;
		String i = "";
		try {
			s = mapper.readValue(payload, Student.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			i = create.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).entity(i).build();
	}

	// Delete a song
	
	@POST
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteSongs(String payload, @PathParam("id") int id) {
		DeleteSongCommand delete = new DeleteSongCommand();
		Student s = null;
		try {
			s = mapper.readValue(payload, Student.class);
			s.setId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			delete.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).build();
	}

	// Search songs

	@GET
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getbyName(@PathParam("name") String name) {
		SearchCommand command = new SearchCommand();
		String songString = null;
		try {
			songString = mapper.writeValueAsString(command.executeName(name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}
	@GET
	@Path("{department}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getbyDepartment(@PathParam("department") String department) {
		SearchCommand command = new SearchCommand();
		String songString = null;
		try {
			songString = mapper.writeValueAsString(command.executeName(department));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}
	@GET
	@Path("{year}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getbyyear(@PathParam("year") String year) {
		SearchCommand command = new SearchCommand();
		String songString = null;
		try {
			songString = mapper.writeValueAsString(command.executeYear(year));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}
	
	
}
