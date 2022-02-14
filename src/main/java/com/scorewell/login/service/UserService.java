package com.scorewell.login.service;

import com.google.gson.JsonObject;
import com.scorewell.dto.UserRole;
import com.scorewell.login.repository.RoleRepository;
import com.scorewell.login.repository.UserRepository;
import com.scorewell.model.Role;
import com.scorewell.model.User;
import com.scorewell.service.DaoService;
import com.scorewell.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Autowired private DaoService daoService;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        
        return userRepository.save(user);
    }


	public List<String> getAllSubscribers() {
		return daoService.getAllSubscribers();
	}
	
	public JsonObject createSubscribeOnlyUser(String email) {
		User user = new User();
		user.setEmail(email);
		user.setSubscribe_only(true);
		user.setDeleted(false);
		user.setCreateTime(System.currentTimeMillis());
//		UserRole role = daoService.getUserRole("USER");
//		user.setRole(role);
		user.setPassword(null);
		User ue=daoService.getUserByEmail(user.getEmail());
		if(ue!=null){
			return JsonUtils.createErrorResponse("email already exist");
		}
		String userId = daoService.createUser(user);
		return JsonUtils.createSuccessResponse(userId);
		
//		return JsonUtils.createSuccessResponse("Pending your request. Will update soon...");
		
	}
}