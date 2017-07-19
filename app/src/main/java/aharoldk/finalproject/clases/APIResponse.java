package aharoldk.finalproject.clases;

import java.util.List;

/**
 * Created by alwandy on 10/07/17.
 */

public class APIResponse {
    private String count;
    private List<Recipes> recipes;

    public APIResponse() {
    }

    public APIResponse(String count, List<Recipes> recipes) {
        this.count = count;
        this.recipes = recipes;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Recipes> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipes> recipes) {
        this.recipes = recipes;
    }
}
