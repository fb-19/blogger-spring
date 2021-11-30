package com.project.blogger.service.impl;

import com.project.blogger.converter.BlogPostConverter;
import com.project.blogger.converter.BlogPostCreateConverter;
import com.project.blogger.converter.BlogPostUpdateConverter;
import com.project.blogger.dto.BlogPostCreateDto;
import com.project.blogger.dto.BlogPostDto;
import com.project.blogger.dto.BlogPostUpdateDto;
import com.project.blogger.repository.BlogPostRepository;
import com.project.blogger.repository.FollowerRepository;
import com.project.blogger.service.BlogPostService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

  private final BlogPostRepository blogPostRepository;

  private final FollowerRepository followerRepository;

  private final BlogPostConverter blogPostConverter;

  private final BlogPostCreateConverter blogPostCreateConverter;

  private final BlogPostUpdateConverter blogPostUpdateConverter;

  @Override
  public List<BlogPostDto> findAll() {
    return blogPostRepository.findAll().stream().map(blogPostConverter::convert).collect(Collectors.toList());
  }

  @Override
  public BlogPostDto findOneById(final Long id) {
    final var blogPost = blogPostRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Blog post with ID: " + id + " not found."));

    return blogPostConverter.convert(blogPost);
  }

  @Override
  public BlogPostDto create(final BlogPostCreateDto blogPostCreateDto) {
    final var blogPost = blogPostCreateConverter.convert(blogPostCreateDto);
    if (Objects.isNull(blogPost)) {
      throw new IllegalArgumentException("Empty blog post cannot be created.");
    }

    return blogPostConverter.convert(blogPostRepository.save(blogPost));
  }

  @Override
  public BlogPostDto update(final Long id, final BlogPostUpdateDto blogPostUpdateDto) {
    final var blogPost = blogPostRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Blog post with ID: " + id + " not found."));

    final var convertedBlogPost = blogPostUpdateConverter.convert(blogPostUpdateDto);

    if (Objects.isNull(convertedBlogPost)) {
      throw new IllegalArgumentException("Empty blog post cannot be updated.");
    }

    convertedBlogPost.setId(id);
    convertedBlogPost.setAuthor(blogPost.getAuthor());
    convertedBlogPost.setTimeCreated(blogPost.getTimeCreated());

    blogPostRepository.save(convertedBlogPost);

    return blogPostConverter.convert(convertedBlogPost);
  }

  @Override
  public BlogPostDto delete(final Long id) {
    final var blogPost = blogPostRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Blog post with ID: " + id + " not found."));

    blogPostRepository.deleteById(id);

    return blogPostConverter.convert(blogPost);
  }

  @Override
  public List<BlogPostDto> findAllFavorites(final Long id) {
    final var followedAuthorsIds = followerRepository.findAllByFollowerId(id).stream()
        .map(follower -> follower.getAuthor().getId()).collect(Collectors.toList());

    return blogPostRepository.findAllByAuthorIdIn(followedAuthorsIds).stream()
        .map(blogPostConverter::convert).collect(Collectors.toList());
  }

}
