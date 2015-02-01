package cz.moro.sokrates.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Movie {

	private int id;
	
	private String title;
	
	private String director;
	
	private Date publishDate;
	
	private List<MovieCharacter> characters;

	private Map<MovieCharacter, String> actors;

	public Movie(int id, String title, String director, Date publishDate,
			List<MovieCharacter> characters, Map<MovieCharacter, String> actors) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.publishDate = publishDate;
		this.characters = characters;
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<MovieCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(List<MovieCharacter> characters) {
		this.characters = characters;
	}

	public Map<MovieCharacter, String> getActors() {
		return actors;
	}

	public void setActors(Map<MovieCharacter, String> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", publishDate="
				+ publishDate + ", characters=" + characters + ", actors="
				+ actors + "]";
	}
}
