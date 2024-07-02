package br.com.alura.ForumHub.controller.topic;

import br.com.alura.ForumHub.dto.topic.TopicDTO;
import br.com.alura.ForumHub.models.topic.Topic;
import br.com.alura.ForumHub.repository.topic.ITopicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private ITopicRepository iTopicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid TopicDTO topicDTO){
        var topic = new Topic(topicDTO);
        iTopicRepository.save(topic);

        return ResponseEntity.ok(topic);
    }
}