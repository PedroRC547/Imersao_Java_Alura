
public class App {
    public static void main(String[] args) throws Exception {

        // fazer um conexão HTTP ebuscar os top 250 filmes

        Json Json = new Json();
        // Exibir e manipular os dados

        System.out.println("----------------------Top Movies-------------------------");
        Json.coletaInfo("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("-------------------------Top TVs-------------------------");
        Json.coletaInfo("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("------------------Most Popular Movies--------------------");
        Json.coletaInfo(
                "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("------------------Most Popular TVs-----------------------");
        Json.coletaInfo("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json");
        System.out.println("---------------------------------------------------------");
        System.out.println();

    }
}
