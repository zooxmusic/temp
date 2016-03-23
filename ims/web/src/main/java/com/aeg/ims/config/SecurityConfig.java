package com.aeg.ims.config;


//@Configuration
//@EnableWebSecurity
public class SecurityConfig {/*} extends WebSecurityConfigurerAdapter implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(authenticationProvider())
                .userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        JaasAuthenticationProvider provider = new JaasAuthenticationProvider();
        provider.setLoginConfig(loginConfig());
        provider.setLoginContextName("ims_policy");
        provider.setCallbackHandlers(callbackHandlers());
        provider.setAuthorityGranters(authorityGranters());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailService() {
        return new UserDetailsServiceImpl();
    }

    public Resource loginConfig()  {
        checkNotNull(resourceLoader);
        return resourceLoader.getResource("/WEB-INF/login.conf");
    }

    @Bean
    public JaasAuthenticationCallbackHandler[] callbackHandlers() {
        List<JaasAuthenticationCallbackHandler> handlers = new ArrayList<>();
        handlers.add(new JaasNameCallbackHandler());
        handlers.add(new JaasPasswordCallbackHandler());
        return handlers.toArray(new JaasAuthenticationCallbackHandler[2]);
    }

    @Bean
    public AuthorityGranter[] authorityGranters() {
        List<AuthorityGranter> handlers = new ArrayList<>();
        handlers.add(new RoleUserAuthorityGranter());
        return handlers.toArray(new AuthorityGranter[1]);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }*/
}