package com.project.blogger.controller;

import com.project.blogger.dto.BlogPostCreateDto;
import com.project.blogger.dto.BlogPostDto;
import com.project.blogger.dto.BlogPostUpdateDto;
import com.project.blogger.service.BlogPostService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog-post")
public class BlogPostController {

  private final BlogPostService blogPostService;

  @GetMapping("/all")
  public ResponseEntity<List<BlogPostDto>> getAll() {
    return ResponseEntity.ok(blogPostService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BlogPostDto> getOneById(@PathVariable final Long id) {
    return ResponseEntity.ok(blogPostService.findOneById(id));
  }

  @PostMapping("/create")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<BlogPostDto> create(@Valid @RequestBody final BlogPostCreateDto dto) {
    return ResponseEntity.ok(blogPostService.create(dto));
  }

  @PutMapping("/update/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<BlogPostDto> update(@PathVariable final Long id, @Valid @RequestBody BlogPostUpdateDto dto) {
    return ResponseEntity.ok(blogPostService.update(id, dto));
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<BlogPostDto> delete(@PathVariable final Long id) {
    return ResponseEntity.ok(blogPostService.delete(id));
  }

  @GetMapping("/favorite/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<BlogPostDto>> getFavorites(@PathVariable final Long id) {
    return ResponseEntity.ok(blogPostService.findAllFavorites(id));
  }

}
