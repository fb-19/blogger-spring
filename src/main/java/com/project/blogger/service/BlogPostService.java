package com.project.blogger.service;

import com.project.blogger.dto.BlogPostCreateDto;
import com.project.blogger.dto.BlogPostDto;
import com.project.blogger.dto.BlogPostUpdateDto;
import java.util.List;
import java.util.Optional;

public interface BlogPostService {

  List<BlogPostDto> findAll();

  BlogPostDto findOneById(Long id);

  BlogPostDto create(BlogPostCreateDto blogPostCreateDto);

  BlogPostDto update(Long id, BlogPostUpdateDto blogPostUpdateDto);

  BlogPostDto delete(Long id);

  List<BlogPostDto> findAllFavorites(Long id);

}
