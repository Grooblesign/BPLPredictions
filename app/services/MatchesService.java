package services;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileWriter;

import com.google.gson.stream.JsonReader;

import models.Match;

import play.libs.Json;
import play.Logger;

public class MatchesService {
	
	public void update(String id, String score, String prediction) {

		List<Match> matches = findAll();
		
		for (Match match : matches) {
			if (match.getId() == Integer.parseInt(id)) {
				match.setScore(score.replace("\"", ""));
				match.setPrediction(prediction.replace("\"", ""));
				break;
			}
		}
		
		try {
			File f = new File("public/data/scores.json");
			if (!f.exists()) {
				f.createNewFile();
			}
			
			FileWriter fw = new FileWriter(f);
			
			Json json = new Json();
			fw.write(json.toJson(matches).toString());
			
			fw.close();
		} catch (Exception ex) {
			Logger.info(ex.getMessage());
		}
	}
	
	public List<Match> findAll() {
		
		List<Match> matches = null;

		InputStream in = null;
		
		try {
			File f = new File("public/data/scores.json");
			
			if (!f.exists()) {
				f = new File("public/data/matches.json");
			}
			
			if (f.exists()) {
				Logger.info("Loading file - " + f.getCanonicalPath());
				in = new FileInputStream(f);
			} else {
				Logger.info("File not found - " + f.getCanonicalPath());
			}

			if (in != null) {
				matches = readJsonStream(in);
			
				in.close();
			}
		} catch (Exception ex) {
			Logger.info(ex.getMessage());
		}

		return matches;
	}
	
	private List<Match> readJsonStream(InputStream in) throws IOException {
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		try {
			return readMatchesArray(reader);
		} finally {
			reader.close();
		}
    }	
	
	private List<Match> readMatchesArray(JsonReader reader) throws IOException {
		List<Match> matches = new ArrayList<Match>();

		reader.beginArray();
		while (reader.hasNext()) {
			matches.add(readMatch(reader));
		}
		reader.endArray();
		
		return matches;
	}	
   
	public Match readMatch(JsonReader reader) throws IOException {
		
		Match match = new Match();
		
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			
			if (name.equals("id")) {
				match.setId(reader.nextInt());
			} else if (name.equals("homeTeam")) {
				match.setHomeTeam(reader.nextString());
			} else if (name.equals("awayTeam")) {
				match.setAwayTeam(reader.nextString());
			} else if (name.equals("date")) {
				match.setDate(reader.nextString());
			} else if (name.equals("time")) {
				match.setTime(reader.nextString());
			} else if (name.equals("score")) {
				match.setScore(reader.nextString());
			} else if (name.equals("prediction")) {
				match.setPrediction(reader.nextString());
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		return match;
	}
}