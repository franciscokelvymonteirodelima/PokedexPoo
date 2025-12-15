package model.pokedex;

import model.pokemon.Pokemon;
import java.util.List;
import java.util.ArrayList;	

public class Pokedex {

    //deois ver essa questao de usar list ou arraylist para esses atributos
    protected List<Pokemon> dicionarioPokemon;
    protected List<Pokemon> favoritos;
    protected List<Pokemon> capturados;
    protected int totalCapturados;

    public Pokedex(){
        this.dicionarioPokemon = new ArrayList<>();
        this.favoritos = new ArrayList<>();
        this.capturados = new ArrayList<>();
        this.totalCapturados = 0;
        carregarPrimeiraGeracao();
    }

    public List<Pokemon> listarTodos(){
        return new ArrayList<>(dicionarioPokemon); // neste caso ele ta retornando a copia da lista, para nao modificar a lista original
    }

    public List <Pokemon> listarFavoritos(){
        return new ArrayList<>(favoritos);
    }

    public List<Pokemon> listarCapturados(){
        return new ArrayList<>(capturados);
    }

    public void adicionarPokemon(Pokemon pokemon){
        if(pokemon != null && !dicionarioPokemon.contains(pokemon)){ // a funçao .contains verifica se o pokemon ja existe na lista, se nao ele adiciona o mesmo ao dicionario
            dicionarioPokemon.add(pokemon);
        }
    }

    public void adicionarFavorito(Pokemon pokemon){
        if(pokemon != null && !favoritos.contains(pokemon)){
            favoritos.add(pokemon);
        }
    }

    public void removerFavorito(Pokemon pokemon){
        favoritos.remove(pokemon);
    }

    public void adicionarCapturado(Pokemon pokemon){
        if(pokemon != null && !capturados.contains(pokemon)){
            capturados.add(pokemon);
            totalCapturados++;
        }
    }

    public int getTotalCapturados() {
        return totalCapturados;
    }

    // depois rever a questao o construtor de pokemons para aceitar sem o nivel ja que no dicionario eles sempre vao estar no nivel 1 e isso nao é necessario ...
     private void carregarPrimeiraGeracao() {
        // Bulbasaur line
        adicionarPokemon(new Pokemon("Bulbasaur", 1, "Grass", "Poison", "Bulba!", 45, 49, 49, 65, 65, 45, "Pokémon semente. Carrega uma semente nas costas desde o nascimento.", "Overgrow"));
        adicionarPokemon(new Pokemon("Ivysaur", 2, "Grass", "Poison", "Ivy!", 60, 62, 63, 80, 80, 60, "Quando o bulbo nas costas cresce, parece perder a capacidade de ficar de pé.", "Overgrow"));
        adicionarPokemon(new Pokemon("Venusaur", 3, "Grass", "Poison", "Saur!", 80, 82, 83, 100, 100, 80, "A planta floresce quando absorve energia solar, ficando mais ativo no verão.", "Overgrow"));
        
        // Charmander line
        adicionarPokemon(new Pokemon("Charmander", 4, "Fire", "-", "Char!", 39, 52, 43, 60, 50, 65, "Prefere coisas quentes. Dizem que quando chove, vapor sai da ponta de sua cauda.", "Blaze"));
        adicionarPokemon(new Pokemon("Charmeleon", 5, "Fire", "-", "Meleon!", 58, 64, 58, 80, 65, 80, "Tem natureza bárbara. Em batalha, bate a cauda e corta o inimigo com garras afiadas.", "Blaze"));
        adicionarPokemon(new Pokemon("Charizard", 6, "Fire", "Flying", "Zard!", 78, 84, 78, 109, 85, 100, "Cospe fogo tão quente que pode derreter pedregulhos. Pode causar incêndios florestais.", "Blaze"));
        
        // Squirtle line
        adicionarPokemon(new Pokemon("Squirtle", 7, "Water", "-", "Squirtle!", 44, 48, 65, 50, 64, 43, "Após o nascimento, suas costas incham e endurecem formando um casco.", "Torrent"));
        adicionarPokemon(new Pokemon("Wartortle", 8, "Water", "-", "Wartor!", 59, 63, 80, 65, 80, 58, "É reconhecido como símbolo de longevidade. Se sua cauda tem algas, é velho.", "Torrent"));
        adicionarPokemon(new Pokemon("Blastoise", 9, "Water", "-", "Blast!", 79, 83, 100, 85, 105, 78, "Esmaga o inimigo com seu peso. Retrai-se no casco se em perigo.", "Torrent"));
        
        // Caterpie line
        adicionarPokemon(new Pokemon("Caterpie", 10, "Bug", "-", "Caterpie!", 45, 30, 35, 20, 20, 45, "Seus pés grudados permitem escalar árvores e se alimentar de folhas.", "Shield Dust"));
        adicionarPokemon(new Pokemon("Metapod", 11, "Bug", "-", "Metapod!", 50, 20, 55, 25, 25, 30, "Seu corpo está em preparação para evolução. Endurece para proteger seu interior.", "Shed Skin"));
        adicionarPokemon(new Pokemon("Butterfree", 12, "Bug", "Flying", "Free!", 60, 45, 50, 90, 80, 70, "Adora o néctar das flores. Pode localizar flores com apenas 2 milhas de distância.", "Compound Eyes"));
        
        // Weedle line
        adicionarPokemon(new Pokemon("Weedle", 13, "Bug", "Poison", "Weedle!", 40, 35, 30, 20, 20, 50, "Muitas vezes encontrado em florestas comendo folhas. Tem ferrão venenoso na cabeça.", "Shield Dust"));
        adicionarPokemon(new Pokemon("Kakuna", 14, "Bug", "Poison", "Kakuna!", 45, 25, 50, 25, 25, 35, "Quase incapaz de se mover, endurece seu corpo para se proteger de predadores.", "Shed Skin"));
        adicionarPokemon(new Pokemon("Beedrill", 15, "Bug", "Poison", "Beedrill!", 65, 90, 40, 45, 80, 75, "Voa em velocidade incrível. Ataca usando os ferrões venenosos em suas patas.", "Swarm"));
        
        // Pidgey line
        adicionarPokemon(new Pokemon("Pidgey", 16, "Normal", "Flying", "Pidgey!", 40, 45, 40, 35, 35, 56, "Tem senso de direção extremamente aguçado. Pode retornar ao seu ninho de qualquer lugar.", "Keen Eye"));
        adicionarPokemon(new Pokemon("Pidgeotto", 17, "Normal", "Flying", "Pidgeotto!", 63, 60, 55, 50, 50, 71, "Muito protetor de seu território. Bica ferozmente qualquer intruso.", "Keen Eye"));
        adicionarPokemon(new Pokemon("Pidgeot", 18, "Normal", "Flying", "Pidgeot!", 83, 80, 75, 70, 70, 101, "Quando caça, voa incrivelmente rápido e ataca com suas garras poderosas.", "Keen Eye"));
        
        // Rattata line
        adicionarPokemon(new Pokemon("Rattata", 19, "Normal", "-", "Rattata!", 30, 56, 35, 25, 35, 72, "Morde qualquer coisa quando ataca. Pequeno e muito rápido, é visto em muitos lugares.", "Run Away"));
        adicionarPokemon(new Pokemon("Raticate", 20, "Normal", "-", "Raticate!", 55, 81, 60, 50, 70, 97, "Usa seus bigodes para manter o equilíbrio. Parece desacelerar se forem cortados.", "Run Away"));
        
        // Spearow line
        adicionarPokemon(new Pokemon("Spearow", 21, "Normal", "Flying", "Spearow!", 40, 60, 30, 31, 31, 70, "Come insetos na grama alta. Precisa bater as asas rapidamente para voar.", "Keen Eye"));
        adicionarPokemon(new Pokemon("Fearow", 22, "Normal", "Flying", "Fearow!", 65, 90, 65, 61, 61, 100, "Com seu pescoço e bico enormes, pode facilmente capturar presas na água ou no solo.", "Keen Eye"));
        
        // Ekans line
        adicionarPokemon(new Pokemon("Ekans", 23, "Poison", "-", "Ekans!", 35, 60, 44, 40, 54, 55, "Quanto mais velha fica, mais longa cresce. À noite, enrola seu corpo em galhos.", "Intimidate"));
        adicionarPokemon(new Pokemon("Arbok", 24, "Poison", "-", "Arbok!", 60, 95, 69, 65, 79, 80, "O padrão assustador em sua barriga foi estudado. Seis variações foram confirmadas.", "Intimidate"));
        
        // Pikachu line
        adicionarPokemon(new Pokemon("Pikachu", 25, "Electric", "-", "Pika!", 35, 55, 40, 50, 50, 90, "Quando vários destes Pokémon se reúnem, sua eletricidade pode causar tempestades.", "Static"));
        adicionarPokemon(new Pokemon("Raichu", 26, "Electric", "-", "Raichu!", 60, 90, 55, 90, 80, 110, "Sua cauda descarrega eletricidade no chão, protegendo-o de seu próprio poder.", "Static"));
        
        // Sandshrew line
        adicionarPokemon(new Pokemon("Sandshrew", 27, "Ground", "-", "Sandshrew!", 50, 75, 85, 20, 30, 40, "Enterra-se e dorme sob o solo. Come areia ao invés de comida comum.", "Sand Veil"));
        adicionarPokemon(new Pokemon("Sandslash", 28, "Ground", "-", "Sandslash!", 75, 100, 110, 45, 55, 65, "Enrola-se formando uma bola para se proteger de inimigos. Também rola para se mover.", "Sand Veil"));
        
        // Nidoran line
        adicionarPokemon(new Pokemon("Nidoran♀", 29, "Poison", "-", "Nido!", 55, 47, 52, 40, 40, 41, "Apesar de pequeno, emite veneno poderoso de suas farpas. Aparentemente mais dócil.", "Poison Point"));
        adicionarPokemon(new Pokemon("Nidorina", 30, "Poison", "-", "Nidorina!", 70, 62, 67, 55, 55, 56, "A fêmea tem chifre menor. Quando se alimenta de seus filhotes, primeiro mastiga comida.", "Poison Point"));
        adicionarPokemon(new Pokemon("Nidoqueen", 31, "Poison", "Ground", "Nidoqueen!", 90, 92, 87, 75, 85, 76, "Seu corpo é coberto por escamas duras como agulhas. Pressiona adversários com força.", "Poison Point"));
        adicionarPokemon(new Pokemon("Nidoran♂", 32, "Poison", "-", "Nido!", 46, 57, 40, 40, 40, 50, "Estica suas orelhas para verificar seus arredores. Se irritado, libera veneno potente.", "Poison Point"));
        adicionarPokemon(new Pokemon("Nidorino", 33, "Poison", "-", "Nidorino!", 61, 72, 57, 55, 55, 65, "Facilmente irritável. Usa seu chifre para espetar o inimigo, então injetando veneno.", "Poison Point"));
        adicionarPokemon(new Pokemon("Nidoking", 34, "Poison", "Ground", "Nidoking!", 81, 102, 77, 85, 75, 85, "Usa sua cauda poderosa em combate para golpear, apertar e quebrar os ossos do inimigo.", "Poison Point"));
        
        // Clefairy line
        adicionarPokemon(new Pokemon("Clefairy", 35,  "Fairy", "-", "Clefairy!", 70, 45, 48, 60, 65, 35, "Sua aparência adorável o torna popular como animal de estimação. Mas é raro e difícil de achar.", "Cute Charm"));
        adicionarPokemon(new Pokemon("Clefable", 36,  "Fairy", "-", "Clefable!", 95, 70, 73, 95, 90, 60, "Um Pokémon tímido que raramente aparece diante das pessoas. Corre e se esconde quando sente.", "Cute Charm"));
        
        // Vulpix line
        adicionarPokemon(new Pokemon("Vulpix", 37, "Fire", "-", "Vulpix!", 38, 41, 40, 50, 65, 65, "Ao nascer, tem uma cauda branca. A cauda se separa em seis se receber amor suficiente.", "Flash Fire"));
        adicionarPokemon(new Pokemon("Ninetales", 38, "Fire", "-", "Ninetales!", 73, 76, 75, 81, 100, 100, "Muito inteligente e vingativo. Segurar uma de suas caudas traz mil anos de má sorte.", "Flash Fire"));
        
        // Jigglypuff line
        adicionarPokemon(new Pokemon("Jigglypuff", 39,  "Normal", "Fairy", "Jiggly!", 115, 45, 20, 45, 25, 20, "Quando seus enormes olhos brilham, canta uma canção de ninar misteriosamente calmante.", "Cute Charm"));
        adicionarPokemon(new Pokemon("Wigglytuff", 40,  "Normal", "Fairy", "Wiggly!", 140, 70, 45, 85, 50, 45, "Tem pelo excepcionalmente bom, delicado como veludo. Se inflado, pode pular incrivelmente.", "Cute Charm"));
        
        // Zubat line
        adicionarPokemon(new Pokemon("Zubat", 41, "Poison", "Flying", "Zubat!", 40, 45, 35, 30, 40, 55, "Forma colônias em lugares perpetuamente escuros. Usa ultrassom para identificar objetos.", "Inner Focus"));
        adicionarPokemon(new Pokemon("Golbat", 42,  "Poison", "Flying", "Golbat!", 75, 80, 70, 65, 75, 90, "Ataca em grupo furtivamente. Drena o sangue da vítima, mas também bebe o de outros Pokémon.", "Inner Focus"));
        
        // Oddish line
        adicionarPokemon(new Pokemon("Oddish", 43,  "Grass", "Poison", "Oddish!", 45, 50, 55, 75, 65, 30, "Durante o dia, mantém seu rosto no chão. À noite, vaga espalhando suas sementes.", "Chlorophyll"));
        adicionarPokemon(new Pokemon("Gloom", 44,  "Grass", "Poison", "Gloom!", 60, 65, 70, 85, 75, 40, "O fluido que escorre de sua boca não é baba. É néctar usado para atrair presas.", "Chlorophyll"));
        adicionarPokemon(new Pokemon("Vileplume", 45,  "Grass", "Poison", "Vileplume!", 75, 80, 85, 110, 90, 50, "Quanto maiores suas pétalas, mais pólen tóxico contém. Sua cabeça é pesada e difícil de erguer.", "Chlorophyll"));
        
        // Paras line
        adicionarPokemon(new Pokemon("Paras", 46,  "Bug", "Grass", "Paras!", 35, 70, 55, 45, 55, 25, "Cogumelos chamados tochukaso crescem em suas costas. Crescem junto com este Pokémon.", "Effect Spore"));
        adicionarPokemon(new Pokemon("Parasect", 47,  "Bug", "Grass", "Parasect!", 60, 95, 80, 60, 80, 30, "Um par hospedeiro-parasita. O cogumelo no topo controla todo o corpo.", "Effect Spore"));
        
        // Venonat line
        adicionarPokemon(new Pokemon("Venonat", 48,  "Bug", "Poison", "Venonat!", 60, 55, 50, 40, 55, 45, "Vive na sombra de árvores altas onde come insetos. Atrai presas com sua cor.", "Compound Eyes"));
        adicionarPokemon(new Pokemon("Venomoth", 49,  "Bug", "Poison", "Venomoth!", 70, 65, 60, 90, 75, 90, "As escamas nas asas liberam um pó altamente venenoso a cada batida de asa.", "Shield Dust"));
        
        // Diglett line
        adicionarPokemon(new Pokemon("Diglett", 50, "Ground", "-", "Diglett!", 10, 55, 25, 35, 45, 95, "Vive um metro abaixo do solo. Levanta a terra ao túnelar, deixando-a perfeita para plantio.", "Sand Veil"));
        adicionarPokemon(new Pokemon("Dugtrio", 51, "Ground", "-", "Dugtrio!", 35, 100, 50, 50, 70, 120, "Um conjunto de trigêmeos que podem cavar mais de 60 milhas por hora. Desconhece o que está embaixo.", "Sand Veil"));
        
        // Meowth line
        adicionarPokemon(new Pokemon("Meowth", 52, "Normal", "-", "Meowth!", 40, 45, 35, 40, 40, 90, "Adora objetos redondos e brilhantes. Vaga pelas ruas procurando moedas caídas.", "Pickup"));
        adicionarPokemon(new Pokemon("Persian", 53, "Normal", "-", "Persian!", 65, 70, 60, 65, 65, 115, "Tem seis sensores de bigode que lhe dão percepção aguçada. Observa orgulhosamente ao redor.", "Limber"));
        
        // Psyduck line
        adicionarPokemon(new Pokemon("Psyduck", 54, "Water", "-", "Psyduck!", 50, 52, 48, 65, 50, 55, "Usa um misterioso poder. Ao fazer isso, o Pokémon gera ondas cerebrais nunca vistas antes.", "Damp"));
        adicionarPokemon(new Pokemon("Golduck", 55, "Water", "-", "Golduck!", 80, 82, 78, 95, 80, 85, "Frequentemente confundido com o Kappa japonês. Nada em rios rápidos para encontrar presas.", "Damp"));
        
        // Mankey line
        adicionarPokemon(new Pokemon("Mankey", 56, "Fighting", "-", "Mankey!", 40, 80, 35, 35, 45, 70, "Extremamente rápido em ficar com raiva. Pode estar calmo um minuto e furioso no próximo.", "Vital Spirit"));
        adicionarPokemon(new Pokemon("Primeape", 57, "Fighting", "-", "Primeape!", 65, 105, 60, 60, 70, 95, "Sempre furiosamente enfurecido. Perseguirá o inimigo, não importa quão longe ele corra.", "Vital Spirit"));
        
        // Growlithe line
        adicionarPokemon(new Pokemon("Growlithe", 58, "Fire", "-", "Growlithe!", 55, 70, 45, 70, 50, 60, "Muito protetor de seu território. Late e morde para repelir intrusos.", "Intimidate"));
        adicionarPokemon(new Pokemon("Arcanine", 59, "Fire", "-", "Arcanine!", 90, 110, 80, 100, 80, 95, "Um Pokémon lendário na China. Muitas pessoas ficam cativadas por sua grande majestade.", "Intimidate"));
        
        // Poliwag line
        adicionarPokemon(new Pokemon("Poliwag", 60, "Water", "-", "Poliwag!", 40, 50, 40, 40, 40, 90, "Seus pés recém-desenvolvidos são mal crescidos. Nada melhor do que caminha.", "Water Absorb"));
        adicionarPokemon(new Pokemon("Poliwhirl", 61, "Water", "-", "Poliwhirl!", 65, 65, 65, 50, 50, 90, "Capaz de viver dentro ou fora da água. Quando fora d'água, sua pele fica pegajosa.", "Water Absorb"));
        adicionarPokemon(new Pokemon("Poliwrath", 62, "Water", "Fighting", "Poliwrath!", 90, 95, 95, 70, 90, 70, "Nadador habilidoso em crawl e peito. Facilmente ultrapassa os melhores nadadores humanos.", "Water Absorb"));
        
        // Abra line
        adicionarPokemon(new Pokemon("Abra", 63, "Psychic", "-", "Abra!", 25, 20, 15, 105, 55, 90, "Dorme 18 horas por dia. Usa telepatia para sentir perigo enquanto dorme.", "Synchronize"));
        adicionarPokemon(new Pokemon("Kadabra", 64, "Psychic", "-", "Kadabra!", 40, 35, 30, 120, 70, 105, "Emite ondas alfa especiais de seu corpo que induzem dores de cabeça se você se aproximar.", "Synchronize"));
        adicionarPokemon(new Pokemon("Alakazam", 65, "Psychic", "-", "Alakazam!", 55, 50, 45, 135, 95, 120, "Seu cérebro pode superar um supercomputador. Seu QI é de 5.000.", "Synchronize"));
        
        // Machop line
        adicionarPokemon(new Pokemon("Machop", 66, "Fighting", "-", "Machop!", 70, 80, 50, 35, 35, 35, "Adora construir músculos. Treina em todos os estilos de artes marciais para ficar mais forte.", "Guts"));
        adicionarPokemon(new Pokemon("Machoke", 67, "Fighting", "-", "Machoke!", 80, 100, 70, 50, 60, 45, "Seu corpo musculoso é tão poderoso que deve usar cinto para controlar seus movimentos.", "Guts"));
        adicionarPokemon(new Pokemon("Machamp", 68, "Fighting", "-", "Machamp!", 90, 130, 80, 65, 85, 55, "Usando seus quatro braços, pode mover uma montanha. Usando apenas um braço, pode mover mil toneladas.", "Guts"));
        
        // Bellsprout line
        adicionarPokemon(new Pokemon("Bellsprout", 69, "Grass", "Poison", "Bellsprout!", 50, 75, 35, 70, 30, 40, "Prefere lugares quentes e úmidos. Captura pequenos insetos com seus tentáculos.", "Chlorophyll"));
        adicionarPokemon(new Pokemon("Weepinbell", 70, "Grass", "Poison", "Weepinbell!", 65, 90, 50, 85, 45, 55, "Cospe fluido corrosivo que derrete até ferro. No entanto, não pode se mover por conta própria.", "Chlorophyll"));
        adicionarPokemon(new Pokemon("Victreebel", 71, "Grass", "Poison", "Victreebel!", 80, 105, 65, 100, 70, 70, "Dizem que vive em colônias em locais profundos da selva, mas ninguém voltou para contar.", "Chlorophyll"));
        
        // Tentacool line
        adicionarPokemon(new Pokemon("Tentacool", 72, "Water", "Poison", "Tentacool!", 40, 40, 35, 50, 100, 70, "Seu corpo é quase 99% água. Vive em mares rasos onde captura presas com tentáculos venenosos.", "Clear Body"));
        adicionarPokemon(new Pokemon("Tentacruel", 73, "Water", "Poison", "Tentacruel!", 80, 70, 65, 80, 120, 100, "Tem 80 tentáculos. Captura presas e enfraquece o inimigo injetando veneno.", "Clear Body"));
        
        // Geodude line
        adicionarPokemon(new Pokemon("Geodude", 74, "Rock", "Ground", "Geodude!", 40, 80, 100, 30, 30, 20, "Encontrado em campos e montanhas. Confundir com uma rocha pode fazê-lo atacar.", "Rock Head"));
        adicionarPokemon(new Pokemon("Graveler", 75, "Rock", "Ground", "Graveler!", 55, 95, 115, 45, 45, 35, "Rola ladeira abaixo sobre obstáculos. Usa as pernas para ajustar direção.", "Rock Head"));
        adicionarPokemon(new Pokemon("Golem", 76, "Rock", "Ground", "Golem!", 80, 120, 130, 55, 65, 45, "Seu corpo duro como rocha é protegido por um casco. Derrama esse casco uma vez por ano.", "Rock Head"));
        
        // Ponyta line
        adicionarPokemon(new Pokemon("Ponyta", 77, "Fire", "-", "Ponyta!", 50, 85, 55, 65, 65, 90, "Suas pernas crescem fortes enquanto persegue seus pais. Corre em campos e montanhas o dia todo.", "Run Away"));
        adicionarPokemon(new Pokemon("Rapidash", 78, "Fire", "-", "Rapidash!", 65, 100, 70, 80, 80, 105, "Muito competitivo, perseguirá qualquer coisa que se mova rápido. Corre a 150 mph.", "Run Away"));
        
        // Slowpoke line
        adicionarPokemon(new Pokemon("Slowpoke", 79, "Water", "Psychic", "Slowpoke!", 90, 65, 65, 40, 40, 15, "Incrivelmente lento e burro. Leva 5 segundos para sentir dor quando atacado.", "Oblivious"));
        adicionarPokemon(new Pokemon("Slowbro", 80, "Water", "Psychic", "Slowbro!", 95, 75, 110, 100, 80, 30, "O Shellder que morde sua cauda o torna imune à dor. Não se preocupa se a cauda for comida.", "Oblivious"));
        
        // Magnemite line
        adicionarPokemon(new Pokemon("Magnemite", 81, "Electric", "Steel", "Magnemite!", 25, 35, 70, 95, 55, 45, "Usa antigravidade para se manter no ar. Aparece sem aviso e ataca usando ondas de trovão.", "Magnet Pull"));
        adicionarPokemon(new Pokemon("Magneton", 82, "Electric", "Steel", "Magneton!", 50, 60, 95, 120, 70, 70, "Formado por três Magnemite ligados. Gera ondas de rádio poderosas.", "Magnet Pull"));
        
        // Farfetch'd
        adicionarPokemon(new Pokemon("Farfetch'd", 83, "Normal", "Flying", "Farfetch'd!", 52, 90, 55, 58, 62, 60, "A alga-marinha que carrega é sua arma. O alho-poró é usado como ferramenta de construção de ninhos.", "Keen Eye"));
        
        // Doduo line
        adicionarPokemon(new Pokemon("Doduo", 84, "Normal", "Flying", "Doduo!", 35, 85, 45, 35, 35, 75, "Pássaro que não consegue voar. Suas cabeças representam alegria e tristeza.", "Run Away"));
        adicionarPokemon(new Pokemon("Dodrio", 85, "Normal", "Flying", "Dodrio!", 60, 110, 70, 60, 60, 110, "Usa suas três cabeças inteligentemente. Uma cabeça dorme, as outras ficam atentas.", "Run Away"));
        
        // Seel line
        adicionarPokemon(new Pokemon("Seel", 86, "Water", "-", "Seel!", 65, 45, 55, 45, 70, 45, "Coberto por pele grossa. Quanto mais fria a temperatura, mais ativo se torna.", "Thick Fat"));
        adicionarPokemon(new Pokemon("Dewgong", 87, "Water", "Ice", "Dewgong!", 90, 70, 80, 70, 95, 70, "Armazena energia térmica em seu corpo. Nada a velocidades de 8 nós mesmo em águas geladas.", "Thick Fat"));
        
        // Grimer line
        adicionarPokemon(new Pokemon("Grimer", 88, "Poison", "-", "Grimer!", 80, 80, 50, 40, 50, 25, "Lagartas de lama que se formam em esgotos. Alimenta-se de lixo tóxico.", "Stench"));
        adicionarPokemon(new Pokemon("Muk", 89, "Poison", "-", "Muk!", 105, 105, 75, 65, 100, 50, "Um amontoado de gosma malcheirosa. Aumenta de tamanho à medida que absorve lixo.", "Stench"));

        // Shellder line
        adicionarPokemon(new Pokemon("Shellder", 90, "Water", "-", "Shellder!", 30, 65, 100, 45, 25, 40, "Fecha-se em concha forte quando atacado. Vive enterrado em areia.", "Shell Armor"));
        adicionarPokemon(new Pokemon("Cloyster", 91, "Water", "Ice", "Cloyster!", 50, 95, 180, 85, 45, 70, "Protege-se com sua concha incrivelmente dura. Ataca com estilhaços cortantes.", "Shell Armor"));

        // Gastly line
        adicionarPokemon(new Pokemon("Gastly", 92, "Ghost", "Poison", "Gastly!", 30, 35, 30, 100, 35, 80, "Formado de gás tóxico. Invade casas e sufoca inimigos por dentro.", "Levitate"));
        adicionarPokemon(new Pokemon("Haunter", 93, "Ghost", "Poison", "Haunter!", 45, 50, 45, 115, 55, 95, "Assombra suas vítimas até enlouquecer. Flutua por paredes e teto.", "Levitate"));
        adicionarPokemon(new Pokemon("Gengar", 94, "Ghost", "Poison", "Gengar!", 60, 65, 60, 130, 75, 110, "Adora pregar peças. Rouba a sombra de sua vítima para assustar.", "Levitate"));

        // Onix
        adicionarPokemon(new Pokemon("Onix", 95, "Rock", "Ground", "Onix!", 35, 45, 160, 30, 45, 70, "Gigante feito de pedras. Vive em túneis e cavidades subterrâneas.", "Rock Head"));

        // Drowzee line
        adicionarPokemon(new Pokemon("Drowzee", 96, "Psychic", "-", "Drowzee!", 60, 48, 45, 90, 80, 42, "Puxa sonhos das pessoas e os digere. Pode causar amnésia temporária.", "Insomnia"));
        adicionarPokemon(new Pokemon("Hypno", 97, "Psychic", "-", "Hypno!", 85, 73, 70, 115, 115, 67, "Usa seu pêndulo para induzir sono profundo. Alimenta-se de sonhos.", "Insomnia"));

        // Krabby line
        adicionarPokemon(new Pokemon("Krabby", 98, "Water", "-", "Krabby!", 30, 105, 90, 25, 25, 50, "Garras extremamente fortes para seu tamanho. Vive perto da costa.", "Hyper Cutter"));
        adicionarPokemon(new Pokemon("Kingler", 99, "Water", "-", "Kingler!", 55, 130, 115, 50, 50, 75, "Sua garra cresce e pode esmagar rochas. Uso agressivo em batalhas.", "Hyper Cutter"));

        // Voltorb line
        adicionarPokemon(new Pokemon("Voltorb", 100, "Electric", "-", "Voltorb!", 40, 30, 50, 55, 55, 100, "Parecido com uma pokébola. Explode quando provocado.", "Soundproof"));
        adicionarPokemon(new Pokemon("Electrode", 101, "Electric", "-", "Electrode!", 60, 50, 70, 80, 80, 140, "Rola em alta velocidade. Detona ao se auto-destruir.", "Soundproof"));

        // Exeggcute line
        adicionarPokemon(new Pokemon("Exeggcute", 102, "Grass", "Psychic", "Exeggcute!", 60, 40, 80, 60, 45, 40, "Grupo de sementes que se move como uma unidade. Cada ovo reage de maneira diferente.", "Chlorophyll"));
        adicionarPokemon(new Pokemon("Exeggutor", 103, "Grass", "Psychic", "Exeggutor!", 95, 95, 85, 125, 75, 55, "Tem um tronco longo e cabeças múltiplas. Produz frutos nutritivos.", "Chlorophyll"));

        // Cubone line
        adicionarPokemon(new Pokemon("Cubone", 104, "Ground", "-", "Cubone!", 50, 50, 95, 40, 50, 35, "Usa um osso como arma e capacete. Chora pela mãe perdida.", "Rock Head"));
        adicionarPokemon(new Pokemon("Marowak", 105, "Ground", "-", "Marowak!", 60, 80, 110, 50, 80, 45, "Resistente e feroz. Usa seu osso como clava nas batalhas.", "Rock Head"));

        // Hitmonlee / Hitmonchan
        adicionarPokemon(new Pokemon("Hitmonlee", 106, "Fighting", "-", "Hitmonlee!", 50, 120, 53, 35, 110, 87, "Mestre de chutes. Suas pernas se alongam para ataques de longo alcance.", "Limber"));
        adicionarPokemon(new Pokemon("Hitmonchan", 107, "Fighting", "-", "Hitmonchan!", 50, 105, 79, 35, 110, 76, "Especialista em socos rápidos e precisos. Treina incansavelmente.", "Keen Eye"));

        // Lickitung
        adicionarPokemon(new Pokemon("Lickitung", 108, "Normal", "-", "Lickitung!", 90, 55, 75, 60, 75, 30, "Língua extremamente longa e forte. Usa para pegar e prender presas.", "Own Tempo"));

        // Koffing line
        adicionarPokemon(new Pokemon("Koffing", 109, "Poison", "-", "Koffing!", 40, 65, 95, 60, 45, 35, "Bolha de gás tóxico que explode com facilidade. Vive em fábricas poluentes.", "Levitate"));
        adicionarPokemon(new Pokemon("Weezing", 110, "Poison", "-", "Weezing!", 65, 90, 120, 85, 70, 60, "Duas cabeças que exalam gases venenosos. Polui o ar ao redor.", "Levitate"));

        // Rhyhorn line
        adicionarPokemon(new Pokemon("Rhyhorn", 111, "Ground", "Rock", "Rhyhorn!", 80, 85, 95, 30, 30, 25, "Corpo duro coberto por placas. Usa seu peso para atacar.", "Lightning Rod"));
        adicionarPokemon(new Pokemon("Rhydon", 112, "Ground", "Rock", "Rhydon!", 105, 130, 120, 45, 45, 40, "Quase indestrutível. Usa seu chifre para abrir rochas e atacar ferozmente.", "Lightning Rod"));

        // Chansey
        adicionarPokemon(new Pokemon("Chansey", 113, "Normal", "-", "Chansey!", 250, 5, 5, 35, 105, 50, "Extremamente gentil e caridosa. Carrega um ovo que cura feridas.", "Natural Cure"));

        // Tangela
        adicionarPokemon(new Pokemon("Tangela", 114, "Grass", "-", "Tangela!", 65, 55, 115, 100, 40, 60, "Coberto por trepadeiras azuis que escondem seu verdadeiro corpo.", "Chlorophyll"));

        // Kangaskhan
        adicionarPokemon(new Pokemon("Kangaskhan", 115, "Normal", "-", "Kangaskhan!", 105, 95, 80, 40, 80, 90, "Forte e protetor. Carrega seu filhote na bolsa durante batalhas.", "Early Bird"));

        // Horsea line
        adicionarPokemon(new Pokemon("Horsea", 116, "Water", "-", "Horsea!", 30, 40, 70, 70, 25, 60, "Nadador ágil que expele tinta para fugir de predadores.", "Swift Swim"));
        adicionarPokemon(new Pokemon("Seadra", 117, "Water", "-", "Seadra!", 55, 65, 95, 95, 45, 85, "Feroz e territorial. Seus espinhos são usados para defesa.", "Poison Point"));

        // Goldeen line
        adicionarPokemon(new Pokemon("Goldeen", 118, "Water", "-", "Goldeen!", 45, 67, 60, 35, 50, 63, "Peixe elegante com chifre pontudo. Nade em cardumes por segurança.", "Swift Swim"));
        adicionarPokemon(new Pokemon("Seaking", 119, "Water", "-", "Seaking!", 80, 92, 65, 65, 80, 68, "Mais resistente que Goldeen. Usa seu chifre para perfurar inimigos.", "Swift Swim"));

        // Staryu line
        adicionarPokemon(new Pokemon("Staryu", 120, "Water", "-", "Staryu!", 30, 45, 55, 70, 55, 85, "Corpo em forma de estrela que brilha no núcleo quando perto da luz.", "Illuminate"));
        adicionarPokemon(new Pokemon("Starmie", 121, "Water", "Psychic", "Starmie!", 60, 75, 85, 100, 85, 115, "Núcleo brilhante que emite ondas de luz. Dizem que tem poderes místicos.", "Illuminate"));

        // Mr. Mime
        adicionarPokemon(new Pokemon("Mr. Mime", 122, "Psychic", "Fairy", "Mr. Mime!", 40, 45, 65, 100, 120, 90, "Cria barreiras invisíveis com gestos. Muito ágil e brincalhão.", "Soundproof"));

        // Scyther
        adicionarPokemon(new Pokemon("Scyther", 123, "Bug", "Flying", "Scyther!", 70, 110, 80, 55, 80, 105, "Rápido e feroz. Usa suas lâminas afiadas para cortar tudo.", "Swarm"));

        // Jynx
        adicionarPokemon(new Pokemon("Jynx", 124, "Ice", "Psychic", "Jynx!", 65, 50, 35, 115, 95, 95, "Dança graciosamente para se comunicar. Usa poderes psíquicos congelantes.", "Oblivious"));

        // Electabuzz
        adicionarPokemon(new Pokemon("Electabuzz", 125, "Electric", "-", "Electabuzz!", 65, 83, 57, 95, 85, 105, "Corpo coberto por pelos que geram eletricidade. Agita os braços para liberar energia.", "Static"));

        // Magmar
        adicionarPokemon(new Pokemon("Magmar", 126, "Fire", "-", "Magmar!", 65, 95, 57, 100, 85, 93, "Corpo quente que emite chamas. Vive perto de vulcões e fontes termais.", "Flame Body"));

        // Pinsir
        adicionarPokemon(new Pokemon("Pinsir", 127, "Bug", "-", "Pinsir!", 65, 125, 100, 55, 70, 85, "Usa suas mandíbulas poderosas para esmagar árvores e inimigos.", "Hyper Cutter"));

        // Tauros
        adicionarPokemon(new Pokemon("Tauros", 128, "Normal", "-", "Tauros!", 75, 100, 95, 40, 70, 110, "Animal selvagem e agressivo. Corre em manadas e ataca com chifres." , "Intimidate"));

        // Magikarp line
        adicionarPokemon(new Pokemon("Magikarp", 129, "Water", "-", "Magikarp!", 20, 10, 55, 15, 20, 80, "Considerado o Pokémon mais fraco. Salta fora da água com facilidade.", "Splash"));
        adicionarPokemon(new Pokemon("Gyarados", 130, "Water", "Flying", "Gyarados!", 95, 125, 79, 60, 100, 81, "Criatura feroz e violenta. Causa destruição em massa quando enfurecido.", "Intimidate"));

        // Lapras
        adicionarPokemon(new Pokemon("Lapras", 131, "Water", "Ice", "Lapras!", 130, 85, 80, 95, 80, 60, "Gentil gigante que transporta pessoas através de águas geladas.", "Water Absorb"));

        // Ditto
        adicionarPokemon(new Pokemon("Ditto", 132, "Normal", "-", "Ditto!", 48, 48, 48, 48, 48, 48, "Pode se transformar em qualquer coisa. Usa essa habilidade para se camuflar.", "Limber"));

        // Eevee line
        adicionarPokemon(new Pokemon("Eevee", 133, "Normal", "-", "Eevee!", 55, 55, 50, 45, 65, 55, "Extremamente adaptável. Evolui para várias formas dependendo do ambiente.", "Run Away"));
        adicionarPokemon(new Pokemon("Vaporeon", 134, "Water", "-", "Vaporeon!", 130, 65, 60, 110, 95, 65, "Corpo molecularmente ligado à água. Pode desaparecer na água.", "Water Absorb"));
        adicionarPokemon(new Pokemon("Jolteon", 135, "Electric", "-", "Jolteon!", 65, 65, 60, 110, 95, 130, "Corpo coberto por pelos que geram eletricidade. Pode disparar raios.", "Volt Absorb"));
        adicionarPokemon(new Pokemon("Flareon", 136, "Fire", "-", "Flareon!", 65, 130, 60, 95, 110, 65, "Armazena calor em seu corpo. Quando expele esse calor, a temperatura atinge 1.600°F.", "Flash Fire"));

        // Porygon
        adicionarPokemon(new Pokemon("Porygon", 137, "Normal", "-", "Porygon!", 65, 60, 70, 85, 75, 40, "Criado inteiramente por computador. Pode se converter em dados digitais para viajar.", "Trace"));

        // Omanyte line
        adicionarPokemon(new Pokemon("Omanyte", 138, "Rock", "Water", "Omanyte!", 35, 40, 100, 90, 55, 35, "Fóssil reanimado. Usa seus tentáculos para se mover e capturar presas.", "Swift Swim"));
        adicionarPokemon(new Pokemon("Omastar", 139, "Rock", "Water", "Omastar!", 70, 60, 125, 115, 70, 55, "Fóssil reanimado. Usa seus tentáculos para agarrar presas e se defender.", "Swift Swim"));
        
        // Kabuto line
        adicionarPokemon(new Pokemon("Kabuto", 140, "Rock", "Water", "Kabuto!", 30, 80, 90, 55, 45, 55, "Fóssil reanimado. Vive no fundo do mar e se move rapidamente.", "Swift Swim"));
        adicionarPokemon(new Pokemon("Kabutops", 141, "Rock", "Water", "Kabutops!", 60, 115, 105, 65, 70, 80, "Fóssil reanimado. Usa suas lâminas afiadas para cortar presas e nadar rapidamente.", "Swift Swim"));

        // Aerodactyl
        adicionarPokemon(new Pokemon("Aerodactyl", 142, "Rock", "Flying", "Aerodactyl!", 80, 105, 65, 60, 75, 130, "Fóssil reanimado. Voa a velocidades incríveis, cortando o ar com suas asas.", "Rock Head"));

        // Snorlax
        adicionarPokemon(new Pokemon("Snorlax", 143, "Normal", "-", "Snorlax!", 160, 110, 65, 65, 110, 30, "Dorme profundamente e só acorda para comer. Seu apetite é insaciável.", "Immunity"));

        // Articuno
        adicionarPokemon(new Pokemon("Articuno", 144, "Ice", "Flying", "Articuno!", 90, 85, 100, 95, 125, 85, "Lendário pássaro do gelo. Suas asas congelam o ar ao redor.", "Pressure"));
        // Zapdos
        adicionarPokemon(new Pokemon("Zapdos", 145, "Electric", "Flying", "Zapdos!", 90, 90, 85, 125, 90, 100, "Lendário pássaro do trovão. Dizem que aparece durante tempestades elétricas.", "Pressure"));
        // Moltres
        adicionarPokemon(new Pokemon("Moltres", 146, "Fire", "Flying", "Moltres!", 90, 100, 90, 125, 85, 90, "Lendário pássaro do fogo. Suas chamas nunca se apagam.", "Pressure"));
        
        // Dratini line
        adicionarPokemon(new Pokemon("Dratini", 147, "Dragon", "-", "Dratini!", 41, 64, 45, 70, 45, 50, "Pequeno lagarto dragão. Lentamente cresce seus atributos ao evoluir.", "Shed Skin"));
        adicionarPokemon(new Pokemon("Dragonair", 148, "Dragon", "-", "Dragonair!", 61, 84, 65, 92, 65, 70, "Forma intermediária. Seu corpo sinuoso se move graciosamente.", "Shed Skin"));
        adicionarPokemon(new Pokemon("Dragonite", 149, "Dragon", "Flying", "Dragonite!", 91, 134, 95, 100, 100, 80, "Poderoso dragão voador. Voa através das montanhas sem esforço.", "Multiscale"));
        
        // Mewtwo
        adicionarPokemon(new Pokemon("Mewtwo", 150, "Psychic", "-", "Mewtwo!", 106, 110, 90, 154, 90, 130, "Criado a partir do DNA de Mew. Extremamente poderoso e inteligente.", "Pressure"));
        
        // Mew
        adicionarPokemon(new Pokemon("Mew", 151, "Psychic", "-", "Mew!", 100, 100, 100, 100, 100, 100, "Diz-se que contém o DNA de todos os Pokémon. Muito raro e esquivo.", "Synchronize"));
        
    }

}
