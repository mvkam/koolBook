package com.diploma.kulBook.services;

import com.diploma.kulBook.repositories.CustUserRepository;
import com.diploma.kulBook.entities.UserRole;
import com.diploma.kulBook.config.AppConfig;
import com.diploma.kulBook.entities.CustUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class CustUserService {
    @Autowired
    private CustUserRepository custUserRepository;

    @Transactional(readOnly = true)
    public List<CustUser> getAllUsers(Pageable pageable) {
        return custUserRepository.findAllUsers(pageable);
    }

    @Transactional(readOnly = true)
    public CustUser findByLogin(String login) {
        return custUserRepository.findByLogin(login);
    }

    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            Optional<CustUser> user = custUserRepository.findById(id);
            user.ifPresent(u -> {
                if (!AppConfig.ADMIN.equals(u.getLogin())) {
                    custUserRepository.deleteById(u.getId());
                }
            });
        });
    }

    @Transactional
    public boolean addUser(String login, String passHash,
                           UserRole role, String email,
                           String phone) {
        if (custUserRepository.existsByLogin(login))
            return false;

        CustUser user = new CustUser(login, passHash, role, email, phone);
        custUserRepository.save(user);

        return true;
    }

    @Transactional
    public CustUser getById(Long id) {
        return custUserRepository.getById(id);
    }

    @Transactional
    public void updateUser(Long id, UserRole role, String login, String email, String phone) {
        CustUser user = custUserRepository.getOne(id);
        user.setRole(role);
        user.setLogin(login);
        user.setEmail(email);
        user.setPhone(phone);

        custUserRepository.save(user);
    }

    @Transactional
    public CustUser findByEmail(String email) {
        return custUserRepository.findByEmail(email);
    }

    @Transactional
    public void changePassword(Long id, String passHash) {
        CustUser user = custUserRepository.getOne(id);
        user.setPassword(passHash);
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return custUserRepository.countAll();
    }
}
