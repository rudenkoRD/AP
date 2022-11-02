package db.documentation;

import model.Documentation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class AppDocumentationRepository implements DocumentationRepository {
    @Override
    public Documentation loadDocumentation() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader documentationJsonfile = new FileReader("src/lab6/db/data/documentation.json");
            JSONObject json = (JSONObject) jsonParser.parse(documentationJsonfile);

            return Documentation.fromJson(json);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
