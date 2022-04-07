package pl.jnews.core.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface NewsRepository extends JpaRepository<News,Long> {
}
