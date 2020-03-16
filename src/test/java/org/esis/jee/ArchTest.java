package org.esis.jee;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.esis.jee");

        noClasses()
            .that()
            .resideInAnyPackage("org.esis.jee.service..")
            .or()
            .resideInAnyPackage("org.esis.jee.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.esis.jee.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
