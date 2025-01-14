package com.literalura;

import com.literalura.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;

        try {
            do {
                System.out.println("1 - Buscar livro por título");
                System.out.println("2 - Listar livros registrados");
                System.out.println("3 - Listar autores registrados");
                System.out.println("4 - Listar autores vivos em determinado ano");
                System.out.println("5 - Listar livros por idioma");
                System.out.println("0 - Sair");

                option = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (option) {
                    case 1:
                        System.out.println("Digite o título do livro:");
                        String title = scanner.nextLine();
                        bookService.searchBookByTitle(title);
                        break;
                    case 2:
                        bookService.listAllBooks();
                        break;
                    case 3:
                        authorService.listAllAuthors();
                        break;
                    case 4:
                        System.out.println("Digite o ano:");
                        int year = scanner.nextInt();
                        authorService.listAuthorsAliveInYear(year);
                        break;
                    case 5:
                        System.out.println("Digite o idioma:");
                        String language = scanner.nextLine();
                        bookService.listBooksByLanguage(language);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Escolha inválida.");
                }
            } while (option != 0);
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Por favor digite un número.");
        } finally {
            scanner.close();
        }
    }
}
