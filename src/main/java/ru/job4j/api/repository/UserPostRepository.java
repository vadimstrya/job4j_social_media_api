package ru.job4j.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.enums.Statuses;

import java.util.Date;
import java.util.List;

/** Репозиторий для {@link UserPost} */
public interface UserPostRepository extends ListCrudRepository<UserPost, Long> {

    List<UserPost> findByUserIdAndStatus(Long userId, Statuses status);

    List<UserPost> findByCreateDateGreaterThanEqualAndCreateDateLessThanEqualAndStatus(Date dateFrom, Date dateTo, Statuses status);

    Page<UserPost> findByStatusOrderByCreateDateAsc(Statuses status, Pageable pageable);
}
