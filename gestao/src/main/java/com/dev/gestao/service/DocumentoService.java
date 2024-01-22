package com.dev.gestao.service;

import com.dev.gestao.domain.aluno.Aluno;
import com.dev.gestao.domain.aluno.Documento;
import com.dev.gestao.domain.aluno.TipoDocumento;
import com.dev.gestao.repos.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentoService {
    final StorageService storageService;
    final DocumentoRepository documentoRepository;

    @Transactional(readOnly = false)
    public Documento create(MultipartFile arquivo, TipoDocumento tipoDoc, Aluno aluno) {
       Objects.requireNonNull(arquivo);
        Objects.requireNonNull(tipoDoc);
        String link = storageService.uploadFile(arquivo);
        String nomeOriginal = arquivo.getOriginalFilename();
        String nomeDoArquivoSemExtensao = nomeOriginal.substring(0, nomeOriginal.lastIndexOf('.'));
        String extensaoDoArquivo = nomeOriginal.substring(nomeOriginal.lastIndexOf('.') + 1);
        Documento documento = Documento.builder().link(link).tipoDocumento(tipoDoc).nome(nomeDoArquivoSemExtensao).extensao(extensaoDoArquivo).aluno(aluno).build();
        return documentoRepository.save(documento);
    }
}
