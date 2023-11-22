package com.kh.jpatotalapp.service;

import com.kh.jpatotalapp.dto.CommentDto;
import com.kh.jpatotalapp.entity.Board;
import com.kh.jpatotalapp.entity.Comment;
import com.kh.jpatotalapp.entity.Member;
import com.kh.jpatotalapp.repository.BoardRespository;
import com.kh.jpatotalapp.repository.CommentRepository;
import com.kh.jpatotalapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

        private final CommentRepository commentRepository;
        private final BoardRespository boardRespository;
        private final MemberRepository memberRepository;

        // 댓글 등록
        public boolean commentRegister(CommentDto commentDto) {
            try {
                Comment comment = new Comment();
                    Board board = boardRespository.findById(commentDto.getBoardId()).orElseThrow(
                        () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
                );
                Member member = memberRepository.findByEmail(commentDto.getEmail()).orElseThrow(
                        () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
                );
                comment.setContent(commentDto.getContent());
                comment.setMember(member);
                comment.setBoard(board);
                commentRepository.save(comment);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        // 댓글 수정
        public boolean commentModify(CommentDto commentDto) {
            try {
                Comment comment = commentRepository.findById(commentDto.getCommentId()).orElseThrow(
                        () ->new RuntimeException("해당 댓글이 존재하지 않습니다.")
                );
                comment.setContent(commentDto.getContent());
                commentRepository.save(comment);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        // 댓글 삭제
        public boolean commentDelete(Long commentId) {
            try {
                Comment comment = commentRepository.findById(commentId).orElseThrow(
                        () -> new RuntimeException("해당 댓글이 존재하지 않습니다.")
                );
                commentRepository.delete(comment);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        // 댓글 목록 조회
        public List<CommentDto> getCommentList(Long boardId) {
            try {
                Board board = boardRespository.findById(boardId).orElseThrow(
                        () -> new RuntimeException("해당 게시물이 존재하지 않습니다.")
                );
                List<Comment> comments = commentRepository.findByBoard(board);
                List<CommentDto> commentDtos = new ArrayList<>();
                for(Comment comment : comments) {
                    commentDtos.add(convertEntityToDto(comment));
                }
                return commentDtos;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        // 댓글 목록 페이징
        // 댓글 상세
        // 댓글 검색
        public List<CommentDto> getCommentList(String keyword) {
            List<Comment> comments = commentRepository.findByContentContaining(keyword);
            List<CommentDto> commentDtos = new ArrayList<>();
            for(Comment comment : comments) {
                commentDtos.add(convertEntityToDto(comment));
            }
            return commentDtos;
        }
        // 댓글 엔티티를 DTO로 변환
        private CommentDto convertEntityToDto(Comment comment) {
            CommentDto commentDto = new CommentDto();
            commentDto.setCommentId(comment.getCommentId());
            commentDto.setBoardId(comment.getBoard().getBoardId());
            commentDto.setEmail(comment.getMember().getEmail());
            commentDto.setContent(comment.getContent());
            commentDto.setRegDate(comment.getRegDate());
            return commentDto;
        }
    }
