package com.gba.contatosbackend;

import com.gba.contatosbackend.models.Contato;
import com.gba.contatosbackend.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContatosBackEndApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ContatosBackEndApplication.class, args);
	}

	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public void run(String... args) throws Exception{
		this.contatoRepository.save(new Contato("João", "joao@gmail.com", "123456789", "2000/12/1",null));
		this.contatoRepository.save(new Contato("Maria", "maria@hotmail.com", "987654321", "1999/11/23", null));
	}
}
