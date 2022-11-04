package model;

import org.json.simple.JSONObject;
import utils.Generated;

import java.util.Objects;

public class Documentation {
    private final String text;

    public Documentation(String text) {
        this.text = text;
    }

    public static Documentation fromJson(JSONObject json) {
        return new Documentation(
                json.get("text").toString()
        );
    }

    @Generated
    @Override
    public String toString() {
        return text;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documentation that = (Documentation) o;
        return Objects.equals(text, that.text);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
