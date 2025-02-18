package br.com.alura.ForumHub.controller.topic;

import br.com.alura.ForumHub.dto.topic.TopicDTO;
import br.com.alura.ForumHub.dto.topic.UpdateTopicDTO;
import br.com.alura.ForumHub.models.topic.Topic;
import br.com.alura.ForumHub.repository.topic.ITopicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private ITopicRepository iTopicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid TopicDTO topicDTO) {
        var topic = new Topic(topicDTO);
        iTopicRepository.save(topic);

        return ResponseEntity.ok(topic);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> findAll() {
        List<Topic> topicList = iTopicRepository.findAll();
        return ResponseEntity.ok(topicList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid UpdateTopicDTO updateTopicDTO, @PathVariable Long id) {

        // BUSCANDO O MÉDICO PELO ID
        var topic = iTopicRepository.getReferenceById(id);

        // ATUALIZA OS DADOS
        topic.updateTopic(updateTopicDTO);

        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        var topic = iTopicRepository.getReferenceById(id);

        topic.logicalExclusion();

        // 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }
}
