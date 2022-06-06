package br.edu.fateczl.P3LabBDClient.consumer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.edu.fateczl.P3LabBDClient.model.Disciplina;
import br.edu.fateczl.P3LabBDClient.model.FaltasRelatorio;
import br.edu.fateczl.P3LabBDClient.util.HTTPConn;

@Component
public class FaltasRelatorioConsumer {
	
	@Autowired
	HTTPConn httpConn;
	
	private final String HTTP_URL = "http://localhost:8080/P3LabBD/siga2/listafaltas/";
	
	public List<FaltasRelatorio> findOne(FaltasRelatorio falta) throws IOException {
		String faltasJson = httpConn.getOp(HTTP_URL, String.valueOf(falta.getRaAluno()), "/"+String.valueOf(falta.getCodigoDisciplina()));
		String[] vetfaltasJson = faltasJson.split("List\":");
		faltasJson = vetfaltasJson[1].substring(0, vetfaltasJson[1].length() - 1);
		Gson gson = new Gson();
		Type faltasTipo = new TypeToken<ArrayList<FaltasRelatorio>>(){}.getType();
		List<FaltasRelatorio> faltas = gson.fromJson(faltasJson, faltasTipo);
		System.out.println(faltas);
		return faltas;
	}
	
}
