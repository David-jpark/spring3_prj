package com.lec11.orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lec11.orm.jpa.entity.UserEntity;

public class CtxJPACallTest {

	public static void main(String[] args) {
		//------------------------------------------------
		//select * from users3 where userSeq=1;
		// EntityManager.find(UserEntity.class, 1L);
		//------------------------------------------------		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		EntityManager em = emf.createEntityManager();
		if (em != null)
			System.out.println("em ok");
		
		em.getTransaction().begin();

		UserEntity user = em.find(UserEntity.class, 1L);
		System.out.println("User: " + user.getUserName());
		em.close();
		emf.close();
		
		
		//------------------------------------------------
		//select * from users3 where userId='admin' and userPw='0000';
		// EntityManagerFactory 및 EntityManager 생성
		//------------------------------------------------
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em2 = emf2.createEntityManager();

        // Transaction 시작
        em2.getTransaction().begin();

        // CriteriaBuilder & CriteriaQuery 생성
        CriteriaBuilder builder = em2.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);

        //쿼리생성
		Root<UserEntity> root = query.from(UserEntity.class);
        Predicate userIdPredicate = builder.equal(root.get("userId"), "admin");
        Predicate userPwPredicate = builder.equal(root.get("userPw"), "11111");
        query.where(builder.and(userIdPredicate, userPwPredicate));

        // 쿼리 실행 및 결과 조회
        UserEntity entity  = null;
        try {
        	entity = em2.createQuery(query).getSingleResult();
        	if (entity  != null) {
                System.out.println("로그인 성공: " + entity);
            }
        } catch (Exception e) {  //결과가 없을 경우 NoResultException을 발생
            System.out.println("로그인 실패");
        }

        // Transaction 커밋 및 엔티티 매니저 종료
        em2.getTransaction().commit();
        em2.close();
        emf2.close();
        
        //SPRING CallTest
        String xmlFile = "C:\\IT\\S3917_J11\\workspace_sts3\\spring3_prj\\src\\main\\webapp\\WEB-INF\\spring\\lec11-servlet-context.xml";
        ApplicationContext ctx = new FileSystemXmlApplicationContext(xmlFile);
        UserService userService = ctx.getBean(UserService.class);
        UserEntity userEntity = userService.svcUserLogin("admin", "11111");
        if (userEntity != null) {
            System.out.println("User found: " + userEntity.getUserName());
        } else {
            System.out.println("User not found");
        }
        
        
	}

}
