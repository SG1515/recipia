package com.ll.exam.RecipiaProject.post;

import com.ll.exam.RecipiaProject.base.BaseRepository;
import com.ll.exam.RecipiaProject.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post, Integer>, BaseRepository {
    public Post findByTitle(String title);

    @Query("select new com.ll.exam.RecipiaProject.post.PostMainDto(p.id ,p.title ,p.score,p.views ,p.likes,pi.imgUrl) " +
            "from PostImg pi " +
            "join pi.post p " +
            "where pi.thumbnailYn = true ")
    Page<PostMainDto> getPostList(Pageable pageable);



    @Query(value = "truncate table post",nativeQuery = true)
    @Modifying
    @Transactional
    void truncate();
    @Query("select p.siteUser from Post p where p.id = :postId ")
    SiteUser getSiteUser(@Param("postId") int postId);


}




