/*
package com.yuma.javaarchitect.springtaskappspring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

     /**
     * UserDetailsServiceインターフェースを実装した独自の認証レルムを使用する設定
     * @param auth
     * @throws Exception
     */

/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/loginFrom").permitAll()//ログインフォームは許可
                .antMatchers("/user/**").permitAll()
                .antMatchers("/new").permitAll()//test用(ユーザ登録)※終わったら消す
                .antMatchers("/index").permitAll()//test用(ユーザ登録後の遷移画面）※終わったら消す
                .antMatchers("/user/create").permitAll()//test用機能※終わったら消す
                .anyRequest().authenticated();// それ以外は全て認証無しの場合アクセス不許可
        http.formLogin()
                .loginProcessingUrl("/login")//ログイン処理をするURL
                .loginPage("/loginFrom")//ログイン画面のURL
                .failureUrl("/login?error")//認証失敗時のURL
                .successForwardUrl("/success")//認証成功時のURL
                .usernameParameter("email")//ユーザのパラメータ名
                .passwordParameter("password");//パスワードのパラメータ名
        http.logout()
                .logoutUrl("/logout**")//ログアウト時のURL（今回は未実装）
                .logoutSuccessUrl("/login");//ログアウト成功時のURL
    }
    
}
*/
