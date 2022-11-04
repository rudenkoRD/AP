package model;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DocumentationTest {
    static private final Documentation documentation = new Documentation("Documentation");
    static private final JSONObject documentationJson = new JSONObject();

    @BeforeClass
    public static void setUp() {
        documentationJson.put("text", "Documentation");
    }

    @Test
    public void parsesDocumentationFromJson() {
        Documentation parsedDocumentation = Documentation.fromJson(documentationJson);

        assertEquals(documentation, parsedDocumentation);
    }
}
