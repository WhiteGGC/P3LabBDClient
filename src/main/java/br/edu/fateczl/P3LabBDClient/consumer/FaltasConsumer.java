package br.edu.fateczl.P3LabBDClient.consumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.fateczl.P3LabBDClient.model.Faltas;
import br.edu.fateczl.P3LabBDClient.util.HTTPConn;

@Component
public class FaltasConsumer {
	
	@Autowired
	HTTPConn httpConn;
	
	private final String HTTP_URL = "http://localhost:8080/P3LabBD/siga2/faltas/";
	
	public String modify(Faltas falta) throws IOException {
		String saida = httpConn.sendOp(HTTP_URL, RequestMethod.PUT, falta);
		return saida;		
	}
	
}
