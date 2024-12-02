package com.iti.iti_project_java;


import java.util.ArrayList;


public class MoviesModel {

    ArrayList<Movie>results;
    String total_pages;
    int  total_results;

    public ArrayList<Movie> getMovies() {
        return results;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public String getTotalPages() {
        return total_pages;
    }

    public int getResultsNumber() {
        return total_results;
    }
}
