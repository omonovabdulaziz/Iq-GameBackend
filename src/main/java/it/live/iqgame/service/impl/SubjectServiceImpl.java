package it.live.iqgame.service.impl;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.Education;
import it.live.iqgame.entity.Subject;
import it.live.iqgame.entity.User;
import it.live.iqgame.entity.enums.RoleName;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.SubjectDTOs.GetSubjectDTO;
import it.live.iqgame.repository.EducationRepository;
import it.live.iqgame.repository.SubjectRepository;
import it.live.iqgame.service.SubjectService;
import it.live.iqgame.utils.FileComposer;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final EducationRepository educationRepository;
    private final SubjectRepository subjectRepository;
    private final ReturnTypeParser genericReturnTypeParser;

    @Override
    public ResponseEntity<ApiResponse> create(Long educId, String name, MultipartFile file) {
        Education education = educationRepository.findById(educId).orElseThrow(() -> new NotFoundException("education topilmadi"));
        String filename = FileComposer.imageUploader(file);
        subjectRepository.save(Subject.builder().education(education).name(name).imgUrl(filename).build());
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long subjectId, String name, MultipartFile file) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new NotFoundException("subject topilmadi"));
        subject.setName(name);
        if (!file.isEmpty()) {
            subject.setImgUrl(FileComposer.imageUploader(file));
        }
        subjectRepository.save(subject);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());

    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long subjectId) {
        subjectRepository.deleteById(subjectId);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("deleted").build());
    }

    @Override
    public Page<GetSubjectDTO> getAllSubjects(int page, int size) {
        return subjectRepository.findAllByPage(PageRequest.of(page, size));
    }

    @Override
    public List<GetSubjectDTO> getSubjectByEducId(Long educId) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        if (systemUser.getRoleName() == RoleName.ADMIN && educId != null) {
            return subjectRepository.getSubjectByEducId(educId);
        }
        return subjectRepository.getSubjectByEducId(systemUser.getEducation().getId());
    }
}