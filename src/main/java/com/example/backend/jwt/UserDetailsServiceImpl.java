package com.example.backend.jwt;

import com.example.backend.dtoresponse.AccountDTORes;
import com.example.backend.model.EmployeeEntity;
import com.example.backend.model.StudentEntity;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDTORes account = new AccountDTORes();
        StudentEntity student = studentRepository.findByAccountId(username);
        EmployeeEntity employee = employeeRepository.findByAccountId(username);
        if (student != null) {
            account.setId(student.getId());
            account.setUsername(student.getAccountId());
            account.setPassword(student.getPassword());
            account.setRoleName(student.getRoleEntity().getName());
            return UserDetailsImpl.build(account);
        }
        if (employee != null) {
            account.setId(employee.getId());
            account.setUsername(employee.getAccountID());
            account.setPassword(employee.getPassword());
            account.setRoleName(employee.getRoleEntity().getName());
            return UserDetailsImpl.build(account);
        }
        throw new UsernameNotFoundException("User Not Found with Username: " + username);
    }
}
