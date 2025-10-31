package br.com.phone.store.infra.controller;

import br.com.phone.store.infra.dto.LoginRequestDto;
import br.com.phone.store.infra.dto.RegisterRequestDto;
import br.com.phone.store.infra.dto.ResponseDto;
import br.com.phone.store.infra.token.TokenService;
import br.com.phone.store.tables.users.model.UsersModel;
import br.com.phone.store.tables.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto body){

        UsersModel user = this.repository.findByUserEmail(body.email()).orElseThrow(() -> new RuntimeException("User Not Found"));
        if(passwordEncoder.matches(body.password(), user.getUserPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDto(user.getUserName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto body){

        Optional<UsersModel> user = this.repository.findByUserEmail(body.userEmail());
        if (user.isEmpty()){
            UsersModel newUser = new UsersModel();
            newUser.setUserPassword(passwordEncoder.encode(body.userPassword()));
            newUser.setUserEmail(body.userEmail());
            newUser.setUserName(body.userName());
            newUser.setUserSsn(body.userSsn());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDto(newUser.getUserName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
