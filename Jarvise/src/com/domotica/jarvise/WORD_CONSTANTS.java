package com.domotica.jarvise;

public interface WORD_CONSTANTS {
	String giusto=".*[Ss]i.*|" +
			".*[Ee]satt.*|" +
			".*[Pp]erfett.*|" +
			".*[Gg]iusto.*|" +
			".*[Cc]orrett.*|" +
			".*[Rr]agion.*|" +
			".*[Bb]rav.*|" +
			".*[Ff]inalment.*";
	
	String sbagliato=".*[Nn]o.*|" +
			".*[Nn]eanch.*|" +
			".*[Ss]bagli.*|" +
			".*[Nn]on.*|";
	
	String name="^[Gg]iord.*|" +
			"^[Jj]arv.*|" +
			"^[Gg]iard.*|" +
			"^[Gg]iar.*|" +
			"^[Gg]ior.*|" ;
	
	
	String on="^[Aa]ccendi.*|" +
			"^[Aa]ttiva.*|" ;
	
	String off="^[Ss]pegni.*|" +
			"^[Dd]isattiva.*|";
	
	String inizio="^[Hh]o [Dd]ett.*|";
	
	String inizio1="^[Tt]i [Hh]o [Dd]ett.*|"+
				"^[Tt]i [Ss]to [Dd]ic.*|";
	
	String orario=".*[Oo]ra.*|"+
			".*[Oo]rario.*|";
	
	String cerca=".*[Cc]erca.*|" +
			".*[Rr]icerca.*|" +
			".*[Tt]rova.*|";
	
	String cerca1="^[Cc]erca [Ss]u [Ii]nter.*|"+
			"^[Cc]erca [Ss]u [Cc]ro.*|";
}
