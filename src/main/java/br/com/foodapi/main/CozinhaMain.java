//package br.com.foodapi.main;
//
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import br.com.foodapi.FoodApiApplication;
//import br.com.foodapi.service.CozinhaService;
//
//public class CozinhaMain {
//	public static void main(String[] args) {
//		// Configurando o contexto da aplicação para rodar uma aplicação não WEB
//		ConfigurableApplicationContext context = new SpringApplicationBuilder(FoodApiApplication.class)
//				.web(WebApplicationType.NONE)
//				.run(args);
//		
//		/**
//		 * Listando todos registros da tabela
//		 * context.getBean(MyClass.class) recupera o bean da classe.
//		 */
//		CozinhaService service = context.getBean(CozinhaService.class);
//	}
//}
