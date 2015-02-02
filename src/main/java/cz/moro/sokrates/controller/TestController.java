package cz.moro.sokrates.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.moro.sokrates.model.*;

/**
 * Controller ser
 * 
 * @author Juraj Vlk
 *
 */
@Controller
public class TestController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	@Autowired
	private ServletContext servletContext;

	@Value("#{config['directory']}")
	private String directory;

	@RequestMapping(value = "/admin/phase1/data", method = RequestMethod.GET)
	public String showModel(Model model) {

		MovieCharacter char1 = new MovieCharacter("Somerset");
		MovieCharacter char2 = new MovieCharacter("Mills");
		MovieCharacter char3 = new MovieCharacter("Tracy");
		MovieCharacter char4 = new MovieCharacter("John Doe");

		List<MovieCharacter> characters = new ArrayList<MovieCharacter>();
		characters.add(char1);
		characters.add(char2);
		characters.add(char3);
		characters.add(char4);

		Map<MovieCharacter, String> actors = new HashMap<MovieCharacter, String>();
		actors.put(char1, "Morgan Freeman");
		actors.put(char2, "Brad Pitt");
		actors.put(char3, "Gvyneth Paltrow");
		actors.put(char4, "Kevin Spacey");

		Calendar publishDate = Calendar.getInstance();
		publishDate.set(1996, Calendar.JANUARY, 5);
		Movie movie = new Movie(1, "Seven", "David Fincher",
				publishDate.getTime(), characters, actors);

		logger.debug(movie.toString());
		model.addAttribute("movie", movie);

		return "test/index";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/phase1/image", method = RequestMethod.GET)
	public ResponseEntity<byte[]> showImage(
			@RequestParam(value = "name") String name) throws IOException {
		InputStream in = servletContext.getResourceAsStream(this.directory
				+ name);

		if (in == null){
			throw new FileNotFoundException("Image located on path: " + this.directory
					+ name + " was not found");
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers,
				HttpStatus.CREATED);
	}

}
