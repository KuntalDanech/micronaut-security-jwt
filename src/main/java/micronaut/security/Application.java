package micronaut.security;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.runtime.Micronaut;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import micronaut.security.model.User;
import micronaut.security.service.IUserService;
import reactor.core.publisher.Mono;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class Application {

	@Inject
	IUserService userService;
	
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
    
    @Get("admin/welcome")
    @Secured("ROLE_ADMIN")
    public Mono<String> admin(Authentication authentication) {
    	return Mono.just("Admin : "+authentication.getName());
    }
    
    @Post("register")
    @PermitAll
    public Mono<String> register(@Body User user){
    	user = userService.registerUser(user);
    	return Mono.just("User "+user.getId()+" has been registered");
    }
    
    @Get("super/welcome")
    @Secured("ROLE_SUPER")
    public Mono<String> super_admin(Authentication authentication) {
    	return Mono.just("Super Admin : "+authentication.getName());
    }

    @Get("welcome")
    public String images() {
    	return "Welcome Netty + Micronaut application";
    }
}
