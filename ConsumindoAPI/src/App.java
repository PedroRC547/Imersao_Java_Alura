import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // var extrator = new ExtratorDeConteudoImdb();

        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        // var extrator = new ExtratorDeConteudoNasa();

        String url = "http://localhost:8080/linguagens";
        var extrator = new ExtratorDeConteudoImdb();

        ClientHttp http = new ClientHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 2; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeDaString = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeDaString);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }

    }
}
