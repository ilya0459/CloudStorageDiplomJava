package ru.netology.DiplomCloudStorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.DiplomCloudStorage.model.entity.User;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LoginRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

}
