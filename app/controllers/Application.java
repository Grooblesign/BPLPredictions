package controllers;

import java.util.List;

import models.Match;
import services.MatchesService;

import play.*;
import play.mvc.*;
import play.libs.Json;

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
}
