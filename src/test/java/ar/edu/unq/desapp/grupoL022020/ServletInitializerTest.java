package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

class ServletInitializerTest {
	  @Test
	  public void servletInitializer() {
		SpringApplicationBuilder springApplicationBuilder = mock(SpringApplicationBuilder.class);

		ServletInitializer servletInitializer = new ServletInitializer();
	    when(springApplicationBuilder.sources(DesAppGrupoL022020Application.class)).thenReturn(springApplicationBuilder);

	    SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

	    verify(springApplicationBuilder).sources(DesAppGrupoL022020Application.class);
	    assertEquals(springApplicationBuilder,result);
	  }
}