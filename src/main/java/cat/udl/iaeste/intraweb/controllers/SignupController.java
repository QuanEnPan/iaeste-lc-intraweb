package cat.udl.iaeste.intraweb.controllers;

import cat.udl.iaeste.intraweb.models.User;
import cat.udl.iaeste.intraweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by eduard on 22/05/15.
 */
@Controller
public class SignupController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ConnectionFactoryLocator connectionFactoryLocator;
    @Autowired
    UsersConnectionRepository usersConnectionRepository;
    @Autowired
    ConnectionRepository connectionRepository;

    @RequestMapping(value="/login")
    public String login() { return "login"; }

    @RequestMapping(value="/signup/{provider}", method=RequestMethod.GET)
    public String signup(@PathVariable String provider, WebRequest request) {
        switch (provider) {
            case "facebook":
                Connection<Facebook> facebook = connectionRepository.getPrimaryConnection(Facebook.class);
                if (facebook != null) {
                    User user;
                    if (userRepository.exists(facebook.getDisplayName()))
                        user = userRepository.findOne(facebook.getDisplayName());
                    else {
                        user = new User(facebook.getDisplayName());
                        user.setImageUrl(facebook.getImageUrl());
                        userRepository.save(user);
                    }
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return "redirect:/api/users/"+facebook.getDisplayName();
                }
                break;
            case "twitter":
                Connection<Twitter> twitter = connectionRepository.getPrimaryConnection(Twitter.class);
                if (twitter != null) {
                    User user;
                    if (userRepository.exists(twitter.getDisplayName()))
                        user = userRepository.findOne(twitter.getDisplayName());
                    else {
                        user = new User(twitter.getDisplayName());
                        user.setImageUrl(twitter.getImageUrl());
                        userRepository.save(user);
                    }
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return "redirect:/api/users/"+twitter.getDisplayName();
                }
                break;
        }
        return null;
    }
}
