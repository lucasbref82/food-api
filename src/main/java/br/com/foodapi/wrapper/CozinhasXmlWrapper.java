package br.com.foodapi.wrapper;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import br.com.foodapi.domain.entity.Cozinha;
import lombok.AllArgsConstructor;
import lombok.Data;

// Serve para alterar o elemento raiz da representação do XML.
@JacksonXmlRootElement(localName = "cozinhas")
@Data
@AllArgsConstructor
public class CozinhasXmlWrapper {
	
	// Altera o nome da propriedade no XML.
	@JacksonXmlProperty(localName = "cozinha")
	// Desabilita o embrulho da propriedade no XML.
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;
	
}
