import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Json {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;

    }

    public void coletaInfo(String url) throws Exception {
        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        URI endereco = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        // pegar só os dados que interessam (Titulo, poster, classificação)
        Json parser = new Json();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeDaString = "saida/" + titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();

            geradora.cria(inputStream, nomeDaString);

            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
