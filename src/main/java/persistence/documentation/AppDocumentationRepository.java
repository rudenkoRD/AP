package persistence.documentation;

import model.Documentation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.IgnoreInTests;

import java.io.FileReader;

public class AppDocumentationRepository implements DocumentationRepository {

    @IgnoreInTests
    @Override
    public Documentation loadDocumentation() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader documentationJsonfile = new FileReader("src/main/resources/documentation.json");
            JSONObject json = (JSONObject) jsonParser.parse(documentationJsonfile);

            return Documentation.fromJson(json);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
