<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <h1>connexion</h1>

        <hr>
        <hr>
        <form action="action">
            <h3>Vous avez déjà un compte ?</h3>
            <br>
            <br>
            <label for="adresseMail">Adresse mail :</label> <input type="text" name="adresseMail" id="adresseMail" /><br>
            <br>
            <label for="motDePasse">Mot de passe :</label> <input type="text" name="motDePasse" id="motDePasse" /><br>
            <br>
            <input type="submit" value="Connexion" name="connect"/><br>
        </form>

        <hr>
        <hr> 
        
        <form action="action">
            <h3>Vous êtes un nouveau client ? </h3>
            <br>
            <br>
            <p>
                En créant un compte sur notre boutique, vous pourrez passer vos commandes plus rapidement, enregistrer plusieurs adresses de livraison,
                consulter et suivre vos commandes,et pleins d'autres choses encore.
            </p>
            <br>
            <input type="submit" value="Créer un compte" name="createAccount"/><br>
        </form>
        
        <hr>
        <hr> 
        
    </body>
</html>
