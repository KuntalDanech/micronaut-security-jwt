package micronaut.security.config;

import java.util.Optional;

import org.reactivestreams.Publisher;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import micronaut.security.model.User;
import micronaut.security.service.IUserService;
import reactor.core.publisher.Mono;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

	@Inject
	IUserService userService;
	
    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Mono.<AuthenticationResponse>create(emitter -> {
        	Optional<User> dbUserOptl = userService.findByUserName(authenticationRequest.getIdentity().toString());
        	if(dbUserOptl.isPresent()) {
        		User user = dbUserOptl.get();
        		if(authenticationRequest.getSecret().equals(user.getPassword()))
        			emitter.success(AuthenticationResponse.success(user.getUserName(),user.getRoles()));
        		else
        			emitter.error(AuthenticationResponse.exception());
        	}else {
        		emitter.error(AuthenticationResponse.exception());	
        	}
        	/*if (authenticationRequest.getIdentity().equals("user") && authenticationRequest.getSecret().equals("password")) {
                emitter.success(AuthenticationResponse.success("user",List.of("ROLE_ADMIN","ROLE_SUPER")));
            } else {
                emitter.error(AuthenticationResponse.exception());
            }*/
        });
    }    
    
}