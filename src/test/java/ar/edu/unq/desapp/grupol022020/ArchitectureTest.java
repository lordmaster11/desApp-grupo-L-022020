package ar.edu.unq.desapp.grupol022020;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class ArchitectureTest {	
	private JavaClasses classes = new ClassFileImporter()
			.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
		    .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
		    .importPackages("ar.edu.unq.desapp.grupol022020");
	
	//////SERVICE  	
	@Test
	public void serviceClassesShouldBeAnnotatedWithServiceAnnotation() {
	    ArchRule rule = classes()
	    		.that().haveSimpleNameEndingWith("Service")
	    		.should().beAnnotatedWith(Service.class);
	    
	    rule.check(classes);
	}
	  
	@Test
	public void noClassesWithServiceAnnotationShouldResideOutsideOfServicesPackages() {
	    ArchRule rule = ArchRuleDefinition.noClasses()
	    		.that().areAnnotatedWith(Service.class)
	    		.should().resideOutsideOfPackage("..services..");
	    
	    rule.check(classes);
	}
	
	@Test
	public void servicesShouldOnlyBeAccessedByControllers() {
	    ArchRule myRule = classes()
	        .that().resideInAPackage("..services..")
	        .should()
	        .onlyBeAccessed().byAnyPackage("..webservices..", "..services..");
	        
	    myRule.check(classes);
	}
	
	@Test
	public void transactionalAnnotationChecks() {
	    ArchRule rule = classes()      
	    		.that()
	    		.areAssignableTo(Entity.class)
	    		.should().onlyBeAccessed()
	    		.byClassesThat().areAnnotatedWith(Transactional.class);
	    
	    rule.check(classes);
	}
	
	//////REPOSITORY
	@Test
	public void repositoryClassesShouldBeAnnotatedWithRepositoryAnnotation() {
	    ArchRule rule = classes()
	    		.that().haveSimpleNameEndingWith("Repository")
	            .and().areNotInterfaces()
	            .should().beAnnotatedWith(Repository.class);
	    
	    rule.check(classes);
	}
	  
	@Test
	public void noClassesWithRepositoryAnnotationShouldResideOutsideOfRepositoriesPackages() {
	    ArchRule rule = ArchRuleDefinition.noClasses()
	    		.that().areAnnotatedWith(Repository.class)
	            .should().resideOutsideOfPackage("..repositories..");
	    
	    rule.check(classes);
	}
	
	/////MODEL
	@Test
	public void modelClassesShouldOnlyBeAccessedByOtherModelClasses() {
	    ArchRule rule = classes()
	    		.that().resideInAPackage(".model..")
	    		.should().onlyBeAccessed().byAnyPackage(".model..");
	    
	    rule.check(classes);
	}
	  
	@Test
	public void theModelClassesNotShouldAccessToServiceControllerAndRepositoryClasses() {		
		ArchRule myRule = classes()	
				.that().resideInAPackage("..model..")
				.should().onlyAccessClassesThat().areNotAnnotatedWith(Service.class)
				.andShould().onlyAccessClassesThat().areNotAnnotatedWith(Controller.class)
				.andShould().onlyAccessClassesThat().areNotAnnotatedWith(Repository.class);

		myRule.check(classes);
	}
	
	//////CONTROLLER  	
	@Test
	public void controllerClassesShouldBeAnnotatedWithControllerOrRestControllerAnnotation() {
	    ArchRule rule = classes()      
	    		.that()
	    		.haveSimpleNameEndingWith("Controller")
	    		.should().beAnnotatedWith(Controller.class)
	    		.orShould().beAnnotatedWith(RestController.class);
	    
	    rule.check(classes);
	}
	
	@Test
	public void noClassesWithControllerOrRestControllerAnnotationShouldResideOutsideOfWebservicesPackages() {
	    ArchRule rule = ArchRuleDefinition.noClasses()
	    		.that().areAnnotatedWith(Controller.class)
	    		.or().areAnnotatedWith(RestController.class)
	    		.should().resideOutsideOfPackage("..webservices");
	    
	    rule.check(classes);
	}
}