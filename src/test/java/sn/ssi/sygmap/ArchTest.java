package sn.ssi.sygmap;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("sn.ssi.sygmap");

        noClasses()
            .that()
                .resideInAnyPackage("sn.ssi.sygmap.service..")
            .or()
                .resideInAnyPackage("sn.ssi.sygmap.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..sn.ssi.sygmap.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
