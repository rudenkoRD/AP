package lab6.menu;

import lab6.db.documentation.AppDocumentationRepository;
import lab6.db.documentation.DocumentationRepository;
import lab6.model.Documentation;

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
