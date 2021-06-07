package com.picposter;

import com.picposter.domain.Comment;
import com.picposter.repository.CommentDAO;
import com.picposter.repository.LikeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PicposterApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PicposterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
