package ru.job4j.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.enums.Statuses;

import java.util.Date;
import java.util.List;

/** Репозиторий для {@link UserPost} */
public interface UserPostRepository extends ListCrudRepository<UserPost, Long> {

    List<UserPost> findByUserIdAndStatus(Long userId, Statuses status);

    List<UserPost> findByCreateDateGreaterThanEqualAndCreateDateLessThanEqualAndStatus(Date dateFrom, Date dateTo, Statuses status);

    Page<UserPost> findByStatusOrderByCreateDateAsc(Statuses status, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ru.job4j.api.entity.UserPost p set p.title = :title, p.text = :text where p.id = :id")
    int updateTitleAndText(@Param("id") Long id, @Param("title") String title, @Param("text") String text);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ru.job4j.api.entity.UserPost p set p.status = 'D' where p.id = :id")
    int delete(@Param("id") Long id);

    @NativeQuery(
            value = """
                    select p.*
                    from public.user_post p
                      join public.user_subscribe s on p.user_id = s.subscribe_user_id
                    where s.user_id = ?1
                    order by p.create_date desc
                    """,
            countQuery = """
                    select count(p.*)
                    from public.user_post p
                      join public.user_subscribe s on p.user_id = s.subscribe_user_id
                    where s.user_id = ?1
                    order by p.create_date desc
                    """
    )
    Page<UserPost> findSubscribersPostsByUserId(Long userId, Pageable pageable);
}
