package fr.unice.polytech.si3.gregorymerlet.enseigne.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;

public class Firm implements Serializable{

    private String name;
    private List<Shop> shops;
    private List<User> users;
    private User actualUser;

    public Firm(){
        this.shops = new ArrayList<>();
        this.users = new ArrayList<>();
        this.actualUser = null;
    }

    public void init(){
        this.name = "Macrogamia";

        //Creating shops
        Shop stLaurent = new Shop("Macrogamia St-Laurent-du-Var", new LatLng(43.658772, 7.197495), "Avenue Eugène Donadeï, 06700 Saint-Laurent-du-Var");
        stLaurent.addOpenHour(0, "10:00 - 20:00");
        stLaurent.addOpenHour(1, "10:00 - 20:00");
        stLaurent.addOpenHour(2, "10:00 - 20:00");
        stLaurent.addOpenHour(3, "10:00 - 20:00");
        stLaurent.addOpenHour(4, "10:00 - 20:00");
        stLaurent.addOpenHour(5, "10:00 - 20:00");
        stLaurent.addOpenHour(6, "Fermé");
        Shop paris = new Shop("Macrogamia Paris", new LatLng(48.8503438, 2.3979738), "3Bis Rue du Faubourg Saint-Antoine, 75011 Paris");
        paris.addOpenHour(0, "10:00 - 20:00");
        paris.addOpenHour(1, "10:00 - 20:00");
        paris.addOpenHour(2, "10:00 - 20:00");
        paris.addOpenHour(3, "10:00 - 20:00");
        paris.addOpenHour(4, "10:00 - 20:00");
        paris.addOpenHour(5, "10:00 - 20:00");
        paris.addOpenHour(6, "Fermé");

        //Creating products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Mosser Effect", "Jeu PC", "Incarnez le célébre commandant Mosser dans la galaxie d'Andromède , bien au-delà de la Voie Lactée . Là-bas, les joueurs mèneront notre combat pour un nouveau foyer en terrain hostile où NOUS sommes les aliens. Incarnez le Pionnier, leader d'une escouade d'explorateurs ayant reçu un entrainement militaire, avec de nombreuses possibilités de progression et de personnalisation. Il s'agit du prochain chapitre de l'histoire de l'humanité et les choix du joueur tout au long du jeu détermineront notre survie dans la galaxie Andromède. Alors que les mystères de la galaxie d'Andromède se révèleront à vous et que le dernier espoir de l'humanité repose sur vos épaules, jusqu'où pourrez-vous aller ?", 50, "http://nsm07.casimages.com/img/2017/06/03//17060308211023025815074596.png"));
        products.add(new Product("Final Fantasy XV", "Jeu PC", "Préparez-vous à entrer dans un monde dépassant l'imagination : plongez dans l'univers de FINAL FANTASY XV pour vivre une véritable épopée, ponctuée de combats intenses. Dans la peau de Noctis, le prince héritier du royaume de Lucis, à vous de renverser l'armée impériale, libérer votre patrie de l'envahisseur, et reconquérir le trône. Prenez la route aux côtés de vos fidèles compagnons pour un voyage inoubliable à travers les paysages époustouflants du monde d'Eos, peuplé de créatures colossales... et d'impitoyables ennemis. Au fil de votre périple, vous apprendrez à manier les armes et la magie, ainsi qu'à maîtriser le pouvoir de vos ancêtres, permettant de vous téléporter dans les airs en plein combat. Nouveaux venus et fans de longue date, accomplissez votre destin et découvrez un FINAL FANTASY d'un nouveau genre. Des combats intenses, riches en action : personnalisez le vaste arsenal d'armes à votre disposition et tirez parti des compétences de soutien uniques de chaque personnage au cours de combats en temps réel, faciles à prendre en main sans pour autant perdre en profondeur. Téléportez-vous sur le champ de bataille pour frapper vos ennemis par surprise, exécutez des coups spéciaux et lancez des sorts de plus en plus puissants à mesure que votre équipe gagne en expérience. Plongez dans un voyage sans limites : explorez l'arrière-pays en voiture aux côtés de vos amis ou aventurez-vous à pied au cœur d'un monde immense, peuplé de créatures merveilleuses, ponctué de grottes impénétrables et de villes où l'aventure vous tend les bras. Des liens d'amitié inaltérables : dans la peau de Noctis, vous verrez vos relations avec vos amis se renforcer à mesure que l'histoire vous rapprochent. Libérer sa patrie nécessite de la force, une bonne dose de courage, mais aussi et surtout un sentiment profond de solidarité. Effectuez des coups coopératifs en combat et émerveillez-vous devant l'esprit d'équipe de Noctis et ses compagnons.", 70, "http://nsm07.casimages.com/img/2017/06/03//17060308203323025815074591.png"));
        products.add(new Product("Resident Evil : Rainero", "Jeu PS4", "Resident Evil 7 Rainero marque le début d\'une nouvelle ère dans la saga Resident Evil. Il revient aux racines du jeu et offre une nouvelle expérience de l\'horreur, avec un nouveau personnage sans précédent : le duc Rainero. Le jeu se déroule dans un domaine au cœur de l\'Amérique rurale et prend place après les tragiques évènements de Resident Evil 6. Les joueurs vont découvrir une nouvelle expérience dans les Resident Evil avec une jouabilité à la première personne.", 60, "http://nsm07.casimages.com/img/2017/06/03//17060308212623025815074599.png"));
        products.add(new Product("Boule & Beal : Vive les vacances !", "Jeu Nintendo DS", "Boule et Beal : Vive les Vacances sur DS est constitué d'une suite de mini-jeux qui forment une bande dessinée interactive. Boule et son fidèle ami Beal partent ensemble pour de nouvelles aventures le temps de 30 défis (collection, cerf-volant, vélo, raquettes, balle au prisonnier...), parfois accompagnés de leur amie Caroline la tortue.", 10, "http://nsm07.casimages.com/img/2017/06/03//17060308203223025815074590.png"));
        products.add(new Product("Nintendo Switch", "Console de salon", "C'est LA console de l'année ! Avec ses fonctionnalités nomades et son design high-tech, la console Switch te procurera des sensations de liberté inégalées. Que tu sois dans ton salon ou en déplacement, la Switch te suit partout ! La console Nintendo Switch est à portée de mains, plus que quelques clics et elle est à toi !", 299, "http://nsm07.casimages.com/img/2017/06/03//17060308211423025815074597.png"));
        products.add(new Product("PlayStation4", "Console de salon", "La PlayStation 4 Pro est la nouvelle console de Sony. Cette version améliorée de la PlayStation 4 permet de jouer à des jeux en 4K. Pour cela, il est nécessaire de posséder un téléviseur compatible.\n\nLa console est également compatible avec la technologie HDR qui améliore l'ensemble des jeux, notamment la qualité graphique. Pour cela, il est nécessaire de posséder un téléviseur HDR compatible.\n\nLe design général de la console a été revue en hausse.\n\nLa console accueille une prise USB complémentaire à l'arrière de la console, en plus des 2 ports USB sur le devant de la console. Tout ceci permet de connecter facilement le casque de réalité virtuelle, le PlayStation VR (non inclus).\n\nLe disque dur dispose d'une capacité de 1 To pour facilement installer un nombre plus important de jeux.\n\nTous les jeux PS4 sont compatibles avec la nouvelle PS4 Pro.", 349.9, "http://nsm07.casimages.com/img/2017/06/03//17060308211823025815074598.png"));
        products.add(new Product("XBox One", "Console de salon", "Comprend la console Xbox One S, un Disque dur (interne) de 500 Go, une Manette Xbox sans fil (avec une prise Jack de 3,5mm), un câble HDMI, un câble d\'alimentation AC.\n\nJouez au meilleur catalogue de jeux tel que Gears of War 4, Dead Rising 4, Forza Horizon 3 et ReCore. Jouez également aux classiques de Xbox 360 sur une console 40 % plus petite.\n\nDécouvrez des couleurs plus riches, plus lumineuses dans les jeux et les vidéos grâce à la technologie HDR (High Dynamic Range).\n\nRegardez des vidéos en flux 4K UHD, et regardez vos films UHD Blu-ray en 4K Ultra HD. Pour pouvoir profiter de la technologie 4K, il est impératif de posséder un téléviseur compatible 4K.\n\nNouvelle manette Xbox sans fil avec grip et technologie Bluetooth.", 269.8, "http://nsm07.casimages.com/img/2017/06/03//17060308213323025815074601.png"));
        products.add(new Product("PlayStation Vita", "Console portable", "Sony offre à sa Playstation Vita un nouveau modèle, après deux ans de bons et loyaux services. Pour la peine, la portable a cédé au diktat de la minceur. Elle a perdu 15 % de son épaisseur initiale, ainsi que 20 % de son poids : l\'idéal pour être belle sur la plage cet été ! Plus pratique, la console dispose désormais d\'une mémoire interne pour les sauvegardes, ainsi que d\'une entrée Micro-USB pour la charge et la connexion PC. L\'autonomie de la batterie a aussi augmenté et la technologie d\'écran a changé. Une foule de petits plus, qui raviront les futurs acquéreurs de la console et les indécis.", 199.99, "http://nsm07.casimages.com/img/2017/06/03//17060308212823025815074600.png"));
        products.add(new Product("The Legend Of Zelda : Breath of the Wild", "Jeu Nintendo Switch", "Annoncé pour 2015, The Legend of Zelda Wii U est un jeu d'aventure qui promet d'être différent de ses prédécesseurs et de se rapprocher de l'organisation du premier opus de la série. Celui-ci se déroulera dans un monde vaste et ouvert avec des donjons sans ordre imposé.", 69.99, "http://nsm07.casimages.com/img/2017/06/03//17060308214823025815074603.png"));
        products.add(new Product("Mario Sports Superstars", "Jeu Nintendo DS", "Cinq sports à la sauce champignon : les passionnés de football pourront participer à des matches en équipes de 11 alors que les férus de golf et de tennis retrouveront le gameplay des séries Mario Golf et Mario Tennis. Quant aux joueurs de Baseball, il sera nécessaire d'affiner leurs techniques de lanceur et de batteur, alors qu'en équitation, les jockeys en herbe devront filer à bride abattue. Enfin, il est également possible de s'occuper de son cheval dans le mode Ecurie en le soignant et le nourrissant afin d'avoir un avantage pendant les courses !", 49.99, "http://nsm07.casimages.com/img/2017/06/03//17060308205123025815074593.png"));
        products.add(new Product("Horizon Zero Dawn", "Jeu PS4", "Dans un monde post-apocalyptique luxuriant où la nature a repris ses droits sur les vestiges d'une civilisation oubliée, des groupes d'humains vivent une vie primitive au sein de tribus. La domination du monde sauvage leur a été usurpée par les machines, des créatures mécaniques redoutables d'origine inconnue.\n\nVous incarnez Aloy, une jeune chasseresse qui cherche à découvrir son destin dans les vestiges du passé. Bannie de sa tribu à la naissance, Aloy a appris à exploiter son agilité, son ingéniosité et ses capacités mortelles au tir à l'arc pour chasser les machines, se défendre contre les tribus rivales et survivre dans ce monde sauvage impitoyable.\n\nParcourez un monde jonché d'étranges artefacts et de ruines afin de percer ses plus grands mystères. Comment les machines ont-elles pu dominer le monde ? Qu'est-il arrivé à la civilisation précédente ? Les réponses à ces questions pourraient déterminer le destin d'Aloy... et de l'humanité.\n\nDans ce jeu de rôle gratifiant au gameplay riche en possibilités, l'accent est mis sur la variété des stratégies. Combinez des outils primitifs avec une technologie avancée pour fabriquer de quoi transformer les prédateurs en proies. Développez des tactiques pour vaincre les différentes machines et piratez les spécimens capturés pour qu'ils vous aident dans votre tâche.\n\nExplorez des décors incroyables mettant en valeur la beauté de la nature. Parcourez des forêts superbement détaillées, des montagnes imposantes et les ruines mystérieuses d'une civilisation disparue, le tout dans un monde qui regorge de vie grâce à la météo changeante et au système de cycle jour/nuit.", 69.99, "http://nsm07.casimages.com/img/2017/06/03//17060308221323025815074605.png"));

        //Adding products to shops
        stLaurent.addProduct(products.get(0));
        stLaurent.addProduct(products.get(1));
        stLaurent.addProduct(products.get(2));
        stLaurent.addProduct(products.get(3));
        stLaurent.addProduct(products.get(4));
        stLaurent.addProduct(products.get(5));
        stLaurent.addProduct(products.get(6));
        stLaurent.addProduct(products.get(8));
        stLaurent.addProduct(products.get(9));
        paris.addProduct(products.get(0));
        paris.addProduct(products.get(1));
        paris.addProduct(products.get(2));
        paris.addProduct(products.get(3));
        paris.addProduct(products.get(4));
        paris.addProduct(products.get(5));
        paris.addProduct(products.get(6));
        paris.addProduct(products.get(7));
        paris.addProduct(products.get(10));

        //Adding shops to firm
        addShop(stLaurent);
        addShop(paris);

        users.add(new User("John", "Doe", "user1@gmail.com", "password"));
        users.add(new User("Georges", "Dupont", "user2@gmail.com", "password"));
    }

    public void addShop(Shop shop){
        if(!shops.contains(shop))
            this.shops.add(shop);
    }

    public List<Shop> getShops(){
        return this.shops;
    }

    public List<String> getShopNames(){
        List<String> shopNames = new ArrayList<>();

        for(Shop shop : shops)
            shopNames.add(shop.getName());

        return shopNames;
    }

    public List<Shop> getShopsForProduct(Product product){
        List<Shop> shopList = new ArrayList<>();

        for(Shop shop : shops)
            if(shop.getProducts().contains(product))
                shopList.add(shop);

        return shopList;
    }

    public List<Product> getProducts(String type, String sortBy, boolean ascending, String shopName){
        List<Product> products = new ArrayList<>();
        for(Shop shop : shops)
            if(shopName.equals("all") || shop.getName().equals(shopName))
                for (Product product : shop.getProducts())
                    if (!products.contains(product))
                        if(type.equals("all") || product.getType().equals(type))
                            products.add(product);
        products = sortProductsBy(products, sortBy, ascending);
        return products;
    }

    public List<String> getTypes(){
        List<String> types = new ArrayList<>();
        for(Shop shop : shops)
            for(Product product : shop.getProducts())
                if(!types.contains(product.getType()))
                    types.add(product.getType());
        return types;
    }

    public String getName() {
        return name;
    }

    private List<Product> sortProductsBy(List<Product> products, String attribute, boolean ascending){
        List<Product> result = new ArrayList<>();
        switch(attribute){
            case "name":
                return sortProductsByName(products, ascending);
            case "price":
                return sortProductsByPrice(products, ascending);
            default:
                return sortProductsByName(products, ascending);
        }
    }

    private List<Product> sortProductsByName(List<Product> products, boolean ascending){
        List<Product> result = new ArrayList<>();
        for(Product product : products){
            if(result.isEmpty()) {
                result.add(product);
            } else {
                for (int i = 0; i < result.size(); i++) {
                    if(ascending) {
                        if (result.get(i).getName().compareTo(product.getName()) > 0) {
                            result.add(i, product);
                            break;
                        } else if (i == result.size() - 1) {
                            result.add(product);
                            break;
                        }
                    } else {
                        if (result.get(i).getName().compareTo(product.getName()) < 0) {
                            result.add(i, product);
                            break;
                        } else if (i == result.size() - 1) {
                            result.add(product);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private List<Product> sortProductsByPrice(List<Product> products, boolean ascending){
        List<Product> result = new ArrayList<>();
        for(Product product : products){
            if(result.isEmpty()) {
                result.add(product);
            } else {
                for (int i = 0; i < result.size(); i++) {
                    if(ascending) {
                        if (result.get(i).getPrice() > product.getPrice()) {
                            result.add(i, product);
                            break;
                        } else if (i == result.size() - 1) {
                            result.add(product);
                            break;
                        }
                    } else {
                        if (result.get(i).getPrice() < product.getPrice()) {
                            result.add(i, product);
                            break;
                        } else if (i == result.size() - 1) {
                            result.add(product);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public User getUser(String mail){
        User found = null;
        for(User user : users)
            if(user.getMail().equals(mail))
                found = user;
        return found;
    }

    public boolean connect(User user, String password){
        if(actualUser != null)
            return false;
        if(!user.getPassword().equals(password))
            return false;
        actualUser = user;
        return true;
    }

    public void disconnect(){
        actualUser = null;
    }

    public boolean isSomeoneConnected(){
        if(actualUser != null)
            return true;
        return false;
    }

    public User getActualUser() {
        return actualUser;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        this.name = inputStream.readUTF();
        this.shops = (List<Shop>) inputStream.readObject();
        this.users = (List<User>) inputStream.readObject();
        this.actualUser = (User) inputStream.readObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeUTF(this.name);
        outputStream.writeObject(this.shops);
        outputStream.writeObject(this.users);
        outputStream.writeObject(this.actualUser);
    }
}
