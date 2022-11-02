package lab6.model;

import org.json.simple.JSONObject;

public class Documentation {
    private final String text;

    public Documentation(String text) {
        this.text = text;
    }

    public static Documentation fromJson(JSONObject json){
        return new Documentation(
                json.get("text").toString()
        );
    }

    @Override
    public String toString() {
        return text;
    }
}
