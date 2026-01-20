package br.com.exemplo.credito.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.credito.domain.Credito;
import br.com.exemplo.credito.service.CreditoService;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {
	private final CreditoService creditoService;

	public CreditoController(CreditoService creditoService) {
		this.creditoService = creditoService;
	}

	@GetMapping("/{numeroNfse}")
	public List<Credito> nfse(@PathVariable String numeroNfse) {
		return creditoService.buscarPorNumeroNfse(numeroNfse);
	}

	@GetMapping("/credito/{numeroCredito}")
	public Credito credito(@PathVariable String numeroCredito) {
		return creditoService.buscarPorNumeroCredito(numeroCredito);
	}
}
