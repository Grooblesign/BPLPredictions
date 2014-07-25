package controllers;

import java.util.List;
import java.util.Map;

import models.Match;
import services.MatchesService;

import play.*;
import play.mvc.*;
import play.libs.Json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import views.html.*;

public class Application extends Controller {

	private static Json json = new Json();

    public static Result index() {
        return ok(index.render("BPL Predictions"));
    }

    public static Result matches() {
		MatchesService service = new MatchesService();
		
		return ok(json.toJson(service.findAll()));
    }
	
    public static Result matchesForTeam(String teamName) {
		MatchesService service = new MatchesService();
		
		return ok(json.toJson(service.findAll()));
    }
	
	public static Result postMatch(String id) {
		String score = request().body().asJson().findPath("score").toString();
		String prediction = request().body().asJson().findPath("prediction").toString();
		
		MatchesService service = new MatchesService();
		
		service.update(id, score, prediction);
		
		return ok();
    }
}
