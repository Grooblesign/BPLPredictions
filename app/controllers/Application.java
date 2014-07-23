package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Match;

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
		List<Match> matches = new ArrayList<Match>();
		
		Match match = new Match();
		match.setId(1);
		match.setHomeTeam("Arsenal");
		match.setAwayTeam("Crystal Palace");
		match.setDate("16-Aug-2014");
		matches.add(match);
		
		match = new Match();
		match.setId(2);
		match.setHomeTeam("Everton");
		match.setAwayTeam("Arsenal");
		match.setDate("23-Aug-2014");
		matches.add(match);

		return ok(json.toJson(matches));
    }
}
