package com.lec13.sec;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;


public class CustomUserDetails extends User { //implements UserDetails {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	private MemberVO memberVO;

	public CustomUserDetails(
			String username, 				//<input type="mid"
			String password,				//<input type="mpw"
			boolean enabled,             	//계성활성화여부
			boolean accountNonExpired,   	//계정만료여부 	=  true이면 계정사용가능
			boolean credentialsNonExpired,	//자격증명만료여부 	=  true이면 계정사용가능
			boolean accountNonLocked,		//계정잠금여부		=  true이면 계정사용가능
			Collection<? extends GrantedAuthority> authorities, //권한 ROLE_*
			MemberVO memberVO) {			//우리꺼VO	******************************	
		super(username, password, enabled, accountNonExpired
				, credentialsNonExpired
				, accountNonLocked
				, authorities);
		this.memberVO = memberVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return super.toString() + "; MemberVO: "+ this.memberVO;
	}
}