package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.assembler.OcorrenciaAsssembler;
import com.algaworks.algalog.api.model.OcorrenciaModel;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAsssembler ocorrenciaAsssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
                                     @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
       Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

       return ocorrenciaAsssembler.toModel(ocorrenciaRegistrada);

    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId ){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAsssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
