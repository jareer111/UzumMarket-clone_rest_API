package uz.market.uzum.services.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.product.Comment;
import uz.market.uzum.dtos.comment.CommentCreateDto;
import uz.market.uzum.dtos.comment.CommentUpdateDTO;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.CommentRepository;
import uz.market.uzum.repositories.ProductRepository;

import java.util.List;

import static uz.market.uzum.mappers.comment.CommentMapper.COMMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ProductRepository productRepository;
    private  final CommentRepository commentRepository;

    public Comment create(CommentCreateDto dto){
        Comment comment = COMMENT_MAPPER.toEntity(dto);
        System.out.println("comment = " + comment);
        return commentRepository.save(comment);
    }

    public Comment update(CommentUpdateDTO dto, Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Comment not found "));
        if(comment.isDeleted()) throw new  RuntimeException("Comment deleted by "+id);
        COMMENT_MAPPER.toUpdateCommentEntity(dto,comment);
        return comment;
    }

    public Comment delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Comment not found "));
        comment.setDeleted(true);
        return comment;
    }
    public List<Comment> getComments(Integer productId){
        productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product not found "));
        return commentRepository.findByProductId(productId).orElseThrow(() -> new ItemNotFoundException("Comments not found "));
    }



}
