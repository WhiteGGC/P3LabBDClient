package br.edu.fateczl.P3LabBDClient.consumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.fateczl.P3LabBDClient.model.Notas;
import br.edu.fateczl.P3LabBDClient.util.HTTPConn;

@Component
public class NotasConsumer {
	
	@Autowired
	HTTPConn httpConn;
	
	private final String HTTP_URL = "http://localhost:8080/P3LabBD/siga2/notas/";
	
	public String modify(Notas nota) throws IOException {
		String saida = httpConn.sendOp(HTTP_URL, RequestMethod.PUT, nota);
		return saida;		
	}
	
	public String save(Notas nota) throws IOException {
		String saida = httpConn.sendOp(HTTP_URL, RequestMethod.POST, nota);
		return saida;		
	}
}
