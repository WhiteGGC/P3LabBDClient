package br.edu.fateczl.P3LabBDClient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.P3LabBDClient.consumer.AlunoConsumer;
import br.edu.fateczl.P3LabBDClient.consumer.DisciplinaConsumer;
import br.edu.fateczl.P3LabBDClient.consumer.NotasConsumer;
import br.edu.fateczl.P3LabBDClient.consumer.NotasRelatorioConsumer;
import br.edu.fateczl.P3LabBDClient.model.Aluno;
import br.edu.fateczl.P3LabBDClient.model.Disciplina;
import br.edu.fateczl.P3LabBDClient.model.FaltasRelatorio;
import br.edu.fateczl.P3LabBDClient.model.Notas;
import br.edu.fateczl.P3LabBDClient.model.NotasRelatorio;

@Controller
public class NotasController {
	
	@Autowired
	NotasConsumer nCons;;
	
	@Autowired
	NotasRelatorioConsumer nrCons;
	
	@Autowired
	DisciplinaConsumer dCons;
	
	@Autowired
	AlunoConsumer aCons;
	
	@RequestMapping(name = "notas", value = "/notas", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<Disciplina> discs = new ArrayList<Disciplina>();
		String erro = "";
		String saida = "";
		
	try {
		alunos = aCons.findAll();
		discs = dCons.findAll();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		model.addAttribute("discs", discs);
		model.addAttribute("alunos", alunos);
		model.addAttribute("erro", erro);
		model.addAttribute("saida", saida);
	}
	return new ModelAndView("notas");
	}
	
	@RequestMapping(name = "notas", value = "/notas", method = RequestMethod.POST)
	public ModelAndView op(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String codigo = allRequestParam.get("codigo");
		String ra = allRequestParam.get("ra");
		String button = allRequestParam.get("button");
		
		NotasRelatorio nr = new NotasRelatorio();
		Notas n = new Notas();
		
		nr.setCodigoDisciplina(Integer.parseInt(codigo));
		nr.setRaAluno(Integer.parseInt(ra));
		
		List<NotasRelatorio> notas = new ArrayList<NotasRelatorio>();
		String erro = "";
		String saida = "";

		try {
			notas = nrCons.findOne(nr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			init(model);
			model.addAttribute("notas", notas);
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
		}
		return new ModelAndView("notas");
	}
}
