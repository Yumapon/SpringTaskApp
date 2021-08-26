/*
package com.yuma.javaarchitect.springtaskappspring.repository.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Transient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class UserDaoEntity implements UserDetails, Persistable<String>{

    @Id
    @NonNull
    private String id;

    @NonNull
    private String password;

    @Builder.Default
    @Setter
    @Transient
    private boolean isNew = false;

    @Override
    public String getId(){
        return id;
    }

    //ユーザーに与えられる権限リストを返却するメソッド
	@Override　
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return null;
	}
	
	//ユーザー名を返却するメソッド
	@Override
	public String getUsername() {
		return this.id;
	}
	
	//パスワードを返却するメソッド
	@Override
	public String getPassword() {
		return this.password;
	}

	//アカウントの有効期限の状態を判定するメソッド
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//アカウントのロック状態を判定するメソッド
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//資格情報の有効期限の状態を判定するメソッド
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//有効なユーザーか判定するメソッド
	@Override
	public boolean isEnabled() {
		return true;
	}
    
}
*/
