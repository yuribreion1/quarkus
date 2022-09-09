import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(title = "Number microservice",
        description = "This microservice generate book numbers",
        version = "1.0",
        contact = @Contact(name = "@yuribreion", url = "https://linktree/breion")
        ), externalDocs = @ExternalDocumentation(url = "https://github.com/yuribreion1", description = "You'll find name repositories of Java coding"),
        tags = {
                @Tag(name = "api", description = "Public API for numbers"),
                @Tag(name = "numbers", description = "APIs related to the ISBN numbers")
        }
)
public class NumberMicroServices extends Application {
}
