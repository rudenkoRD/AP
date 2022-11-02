package menu;

import db.documentation.AppDocumentationRepository;
import db.documentation.DocumentationRepository;
import model.Documentation;

public class DocumentationMenuCommand implements MenuCommand {
    final DocumentationRepository repository = new AppDocumentationRepository();

    @Override
    public void execute() {
        Documentation docs = repository.loadDocumentation();
        System.out.println(docs);
    }

    @Override
    public String menuOptionString() {
        return "Documentation";
    }
}
