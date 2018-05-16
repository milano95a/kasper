package source.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import source.domain.Token;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class TokenRepo {

    @Inject
    private RedisTemplate<String, Token> redisTemplate;

    public void save(Token token) {
        redisTemplate.opsForValue().set(token.getUserId(), token);
    }

    public Token findById(String key) {
        try{
            return redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Token> findAll() {
        List<Token> tokens = new ArrayList<>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            tokens.add(findById(it.next()));
        }
        return tokens;
    }

    public void delete(Token token) {
        String key = token.getUserId();
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    public void deleteAll() {
        Set<String> keys = redisTemplate.keys("*");

        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            redisTemplate.opsForValue().getOperations().delete(it.next());
        }
    }
}
