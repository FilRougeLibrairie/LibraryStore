
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Créer un compte</h1>

        <br>
        <label for="adresseMail">Adresse mail* :</label> <input type="text" name="adresseMail" id="adresseMail" /><br><br>
        Civilité : Mme <input type="radio" name="Civilité" value="" />
        Mr <input type="radio" name="Civilité" value="" /><br><br>
        <label for="lastName">Nom* :</label> <input type="text" name="lastName" id="lastName" /> <br><br>
        <label for="firstName">Prénom* :</label> <input type="text" name="firstName" id="firstName" /><br><br>
        <label for="organisationName">Organisation :</label> <input type="text" name="organisationName" id="organisationName" /><br><br>
        <label for="phoneNumber">Téléphone :</label> <input type="text" name="phoneNumber" id="phoneNumber" /><br><br>

        <p>Afin de bénéficier d'offres personalisées, merci de bien vouloir nous indiquer votre date de naissance :</p><br><br>

        
        
        
        <div class="form-group">
            <label for="jj"></label>
            <select class="form-control" id="jj">
                <option>jj</option>
                <option>01</option>
                <option>02</option>
                <option>03</option>
                <option>04</option>
                <option>05</option>
                <option>06</option>
                <option>07</option>
                <option>08</option>
                <option>09</option>
                <option>10</option>
                <option>11</option>
                <option>12</option>
                <option>13</option>
                <option>14</option>
                <option>15</option>
                <option>16</option>
                <option>17</option>
                <option>18</option>
                <option>19</option>
                <option>20</option>
                <option>21</option>
                <option>22</option>
                <option>23</option>
                <option>24</option>
                <option>25</option>
                <option>26</option>
                <option>27</option>
                <option>28</option>
                <option>29</option>
                <option>30</option>
                <option>31</option>
            </select>
            <label for="mm"></label>
            <select class="form-control" id="mm">
                <option>mm</option>
                <option>01</option>
                <option>02</option>
                <option>03</option>
                <option>04</option>
                <option>05</option>
                <option>06</option>
                <option>07</option>
                <option>08</option>
                <option>09</option>
                <option>10</option>
                <option>11</option>
                <option>12</option>
            </select>
            <label for="yyyy"></label>
            <select class="form-control" id="yyyy">
                <option>aaaa</option>
                <option>1978</option>
                <option>1979</option>
                <option>1980</option>
                <option>1981</option>
                <option>1982</option>
                <option>1983</option>
                <option>1984</option>
                <option>1985</option>
                <option>1986</option>
                <option>1987</option>
                <option>1988</option>
                <option>1989</option>
            </select>
        </div><br><br>
        <label for="password">Mot de passe* :</label> <input type="text" name="password" id="password"/><br><br>
        <label for="confirmPassword">Confirmer le mot de passe* :</label> <input type="text" name="confirmPassword" id="confirmPassword" /><br><br>
        je souhaite recevoir par email : <input type="checkbox" name="newsletter" value="off" /> La newsletter de Caroline<br>
        <input type="checkbox" name="commerciarlPartners" value="off" /> Les offres des partenaires de Caroline<br><br>
        <p>Les informations recueillies sont destinées aux sociétés du groupe Caroline(Gavanese SAS, Bhian SAS, Arrais SAS, Kouamen SAS et Cuaz SAS)
        et à leurs prestataires situés dans et hors de l'union européenne, afin de permetrre aux Membres de bénéficier d'informations sur Caroline. 
        Conformément à la loi "informatiques et libertés" n°78-17 du 6 janvier 1978 modifiée, chaque Membre bénéficie d'un droit d'accès, de rectification et
        d'opposition aux informations qui le concernent auprès de : Service Clients Caroline - 1 tombe du soldat inconnu - 75008 PARIS ou à l'adresse mail :
        caroline@serviceClientCaroline.com
        </p><br><br>
        <p> * Champs obligatoires</P>
        <input type="submit" value="Valider" name="createAccount"/><br>
        
        
        





    </body>
</html>
