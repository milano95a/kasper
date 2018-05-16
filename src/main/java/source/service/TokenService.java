package source.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import source.domain.Token;
import source.domain.User;
import source.repository.TokenRepo;

import javax.inject.Inject;
import java.util.List;

@Service
public class TokenService {

    @Inject
    private TokenRepo tokenRepository;

    public List<Token> findAll() {
        return Lists.newArrayList(tokenRepository.findAll());
    }

    public Token findById(String userId) {
        return tokenRepository.findById(userId);
    }

    public void save(Token token) {
        tokenRepository.save(token);
    }

    public void delete(Token token) {
        tokenRepository.delete(token);
    }

    public void deleteAll() {
        tokenRepository.deleteAll();
    }

    public Object isExist(String userId){
        return tokenRepository.findById(userId);
    }

    public String checkByToken(String token){
        List<Token> tokens = tokenRepository.findAll();
        if(token == null){
            return null;
        }

        for(Token iToken: tokens){
            if(iToken.getToken().equals(token)){
                return iToken.getUserId();
            }
        }

        return null;
    }
}
