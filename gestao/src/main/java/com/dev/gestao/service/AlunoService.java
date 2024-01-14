package com.dev.gestao.service;

import com.dev.gestao.domain.Aluno;
import com.dev.gestao.model.AlunoDTO;
import com.dev.gestao.repos.AlunoRepository;
import com.dev.gestao.util.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import com.dev.gestao.util.UniqueViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final StorageService storageService;


    public List<AlunoDTO> findAll() {
        final List<Aluno> alunoes = alunoRepository.findAll(Sort.by("id").descending());
        return alunoes.stream()
                .map(aluno -> mapToDTO(aluno, new AlunoDTO()))
                .toList();
    }

    public AlunoDTO get(final Integer id) {
        return alunoRepository.findById(id)
                .map(aluno -> mapToDTO(aluno, new AlunoDTO()))
                .orElseThrow(NotFoundException::new);
    }
    @Transactional
    public Integer create(final AlunoDTO alunoDTO) {
        if(cpfExists(alunoDTO.getCpf())) {
           throw new UniqueViolationException(String.format("O cpf %s já existe!", alunoDTO.getCpf()));
        }
        final Aluno aluno = new Aluno();
        mapToEntity(alunoDTO, aluno);
        Aluno salvo = alunoRepository.save(aluno);
        Integer id = salvo.getId();
        salvo.setMatricula(String.valueOf(LocalDate.now().getYear()) +  id );
        return id;
    }
    @Transactional
    public void update(final Integer id, final AlunoDTO alunoDTO) {
        final Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(alunoDTO, aluno);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void delete(final Integer id) {
        alunoRepository.deleteById(id);
    }

    private AlunoDTO mapToDTO(final Aluno aluno, final AlunoDTO alunoDTO) {
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setEmail(aluno.getEmail());
        alunoDTO.setUf(aluno.getUf());
        alunoDTO.setFoto(aluno.getFoto());
        alunoDTO.setDataNascimento(aluno.getDataNascimento());
        alunoDTO.setTelefone(aluno.getTelefone());
        alunoDTO.setMatricula(aluno.getMatricula());
        return alunoDTO;
    }

    private Aluno mapToEntity(final AlunoDTO alunoDTO, final Aluno aluno) {
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setUf(alunoDTO.getUf());
        aluno.setFoto(alunoDTO.getFoto());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());

        return aluno;
    }

    public boolean cpfExists(final String cpf) {
        return alunoRepository.existsByCpfIgnoreCase(cpf);
    }

    @Transactional
    public String upsertFoto(Integer id, MultipartFile file) {
        Aluno aluno = alunoRepository.getReferenceById(id);
        String link = storageService.uploadFile(file);
        aluno.setFoto(link);
        alunoRepository.save(aluno);
        return aluno.getFoto();
    }
}
