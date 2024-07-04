package com.lec11.orm.jpa;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec11.orm.jpa.entity.UserEntity;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	//---------------------------------------------------
	@Autowired
    private UserRepository userRepository;

    public UserEntity svcUserLogin(String userId, String userPw) {
        return userRepository.findByUserIdAndUserPw(userId, userPw);
    }
		
    public List<UserEntity> svcUserList(){
    	return userRepository.findAll();
    }
    
    
	public UserEntity svcUserDetail(Long userSeq){ 
		return userRepository.findOne(userSeq);
		// userRepository.getOne(Long id); // hibernate방식
		// userRepository.findById(Long id); //JPA2.x부터 지원 가장많이 사용.
	}
	
	public UserEntity svcUserUpdate(UserEntity userEntity) {
		UserEntity entity = userRepository.findByUserId(userEntity.getUserId());
		if(entity != null) {
			entity.setUserPw(userEntity.getUserPw());
			userRepository.save(entity);
			return entity;
		}
		return null;
	}
	
	public UserEntity svcUserDel(UserEntity userEntity) {
		UserEntity entity = userRepository.findByUserId(userEntity.getUserId());
		if(entity != null) {
			userRepository.delete(userEntity.getId());
		}
		return null;
	}
	
    /**
  //select count(1) from users3;
	userRepository.count();
	//delete from users3 where userSeq= ?
	userRepository.delete(Long id);
	
	//delete from users3 where userId= ? and userPw=? and userName=?
	userRepository.delete(UserEntity entity);
	//delete from users3;
	userRepository.deleteAll();
	//select * from users3 wehre userSeq=?
	userRepository.exists(Long id);
	//select * from users3;
	userRepository.findAll()
	//sub.. sub.. sub..select * from users3 where~~~ rnum between 1 and 10;
	userRepository.findAll(Pageable);
	//select * from users3 order by userName asc, userSeq desc;
	userRepository.findAll(Sort)
	
	//select * from users3 where userSeq=?
	userRepository.findOne(Long id);
	userRepository.getOne(Long id);
	
	//insert into users3 (...)
	//update users3 set (...)
	userRepository.save(UserEntity entity);
	*/
	//---------------------------------------------------
//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	@Override
//	@Transactional
//	public List<UserEntity> svcUserList() {
//		@SuppressWarnings("unchecked") //ignore generic type compile warning
//		List<UserEntity> list = (List<UserEntity>)sessionFactory.getCurrentSession()
//														.createCriteria(UserEntity.class)
//														.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//		return list;
//	}
	
	

}
