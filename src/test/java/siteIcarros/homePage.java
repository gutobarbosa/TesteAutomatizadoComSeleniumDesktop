package siteIcarros;

import GerarArquivo.gerarArquivo;
import Prop.Prop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class homePage {
    String url; //endereço do site alvo
    WebDriver driver; //objeto do selenium WebDriver
    String fileDate = "C:\\Users\\augus\\OneDrive\\Área de Trabalho\\images";
    String demilit = "|";

    //3.2 Métodos ou funções
    @Before //acontece no começo de cada teste
    public void iniciar() {
        url = "https://www.icarros.com.br/principal/index.jsp";
        System.setProperty("webdriver.chrome.driver","C:\\Users\\augus\\IdeaProjects\\Teste_Web_Icarros\\driver\\chrome\\86\\chromedriver.exe"); //caminho do chromeDriver
        driver = new ChromeDriver(); // setando a variavel com o Chromedriver para conseguir utilizar seus recursos.
        //driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS); // vai esperar tal objeto aparecer na tela caso ainda não tenha aparecido, no tempo setado
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize(); // caso a pagina abra minimizada o mesmo maximiza.
    }
    //@BeforeClass ele acontece uma vez só antes de qualquer coisa


    @Test
    public void testIcarros() throws IOException {
        // Home
        driver.get(url); // Abrir o navegador na pagina indicada na URL
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[2]/div[1]/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[2]/div[1]/div/div/div/div/div/div/input")).sendKeys(Keys.chord("Hyundai"));
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[2]/div[1]/div/div/div/div/div/ul/li[11]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[2]/div[2]/div/div/div/div/div/div/input")).sendKeys(Keys.chord("Creta"));
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[2]/div[2]/div/div/div/div/div/ul/li[7]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[3]/div[1]/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[3]/div[1]/div/div/div/div/div/div/input")).sendKeys(Keys.chord("2017"));
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[3]/div[1]/div/div/div/div/div/ul/li[6]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[4]/div[2]")).click();

        // Tela de itens
        //necessario conferir se numero de resultados foi maior do que o esperado
        String numero;
        String numero1;
        Integer numero2;
        Boolean result = false;
       numero = driver.findElement(By.xpath("//*[@id=\"ctdoTitle\"]/div/h1/span")).getText();
        numero1 = numero.replace(" ofertas", "");//removendo informações indesejadas
        numero1 = numero1.replace(")", "");//removendo informações indesejadas
        numero1 = numero1.replace("(", "");//removendo informações indesejadas
        numero2= Integer.parseInt(numero1);
        if(numero2 > 7){
            result = true;
        }

        //conferir dados dos dois primeiros veiculos da lista retornada

        //Variaveis de uso para validações
        String preco = "R$79.890,00";//value item 1
        String preco2 = "R$84.990,00";
        String value;
        String model;
        Integer model1;
        Boolean response = false;

        //Validando preços

        //item 1
        value = driver.findElement(By.xpath("//*[@id=\"ac31307057\"]/div/a/h3")).getText();//capturando valor real do primeiro produto
        String nova = value.replace("preço à vista", "");//removendo informações indesejadas
        String nova2 = nova.replace(" ", "");//removendo espaços
        String nova3 = nova2.trim();//removendo espaços sobreçalentes


        //item 2
        String value2 = driver.findElement(By.xpath("//*[@id=\"ac30819714\"]/div/a/h3")).getText();//capturando valor real do primeiro produto
        String item2 = value2.replace("preço à vista", "");//removendo informações indesejadas
        item2 = item2.replace(" ", "");//removendo espaços
        item2 = item2.trim();//removendo espaços sobreçalentes


        //validando modelo

        //item 1
        model = driver.findElement(By.xpath("//*[@id=\"ac31307057\"]/div/a/h2")).getText();//capturando valor real do primeiro produto
         model1 = model.indexOf("Creta");//removendo informações indesejadas
        if(model1 != -1){
            response= true;
        }


        //item 2
        Boolean response2 = false;
        String modelItem2 = driver.findElement(By.xpath("//*[@id=\"ac30819714\"]/div/a/h2")).getText();//capturando valor real do primeiro produto
        Integer modelItemInt2 = modelItem2.indexOf("Creta");//removendo informações indesejadas
        if(modelItemInt2 != -1){
            response2= true;
        }


        // capturando dados para criação do arquivo


        //capturando informação do item numero 1
        String cptItem1;
        cptItem1 = driver.findElement(By.id("ac31307057")).getText();
        gerarArquivo.escreverCSV(demilit, cptItem1,fileDate);
        //capturando informação do item numero 2
        cptItem1 = driver.findElement(By.id("ac30819714")).getText();
        gerarArquivo.escreverCSV(demilit, cptItem1,fileDate);
        //capturando informação do item numero 3
        cptItem1 = driver.findElement(By.id("ac31281328")).getText();
        gerarArquivo.escreverCSV(demilit, cptItem1,fileDate);
        //capturando informação do item numero 4
        cptItem1 = driver.findElement(By.id("ac31257565")).getText();
        gerarArquivo.escreverCSV(demilit, cptItem1,fileDate);
        //capturando informação do item numero 5
        cptItem1 = driver.findElement(By.id("ac31128072")).getText();
        gerarArquivo.escreverCSV(demilit, cptItem1,fileDate);



        //Resultados esperados
        assertEquals(preco2,item2);// valida se os valores estão iguais
        assertEquals(preco,nova3);// valida se os valores estão iguais
        assertTrue(response);// valida se o modelo é o mesmo da pesquisa
        assertTrue(response2);// valida se o modelo é o mesmo da pesquisa
        assertTrue(result);// valida se retornou mais de 7 itens

    }


}
