package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class KinopoiskMainPage {

    SelenideElement searchInput = $(byName("kp_query"));
    SelenideElement wideSearchIcon = $(byAttribute("aria-label","Расширенный поиск"));
    SelenideElement searchOfTheBestLink = $(byTagAndText("a","Поиск лучших фильмов"));
    SelenideElement mostWanted = $(byTagAndText("p", "Скорее всего, вы ищете:")).sibling(0);
    SelenideElement mostWantedName = mostWanted.$(byClassName("name"));
    SelenideElement mostWantedFilmLabel = mostWantedName.$(byAttribute("data-type","film"));
    SelenideElement mostWantedYear = mostWantedName.$(byClassName("year"));
    SelenideElement mostWantedLabelWithTime = mostWantedName.sibling(0);

    public KinopoiskMainPage setSearchInput(String text){
        searchInput.setValue(text);
        return this;
    }

    public KinopoiskMainPage goWideSearch(){
        wideSearchIcon.click();
        return this;
    }
    public void goSearchBestFilms(){
        searchOfTheBestLink.click();
    }

    public void confirmSearching(){
        $(byText("Найти")).submit();
    }

    public void checkMostWantedHas(String name){
        mostWantedFilmLabel.shouldHave(partialText(name));
    }

    public void checkNavigatorTransfers(String name, String expectedPath){
        $("#block_left_pad").$(byTagAndText("a", name)).click();
        Assertions.assertEquals(WebDriverRunner.url(), Configuration.baseUrl + expectedPath);
    }

    public void checkMostWantedHasTimeAndYear(String time, String year){
        mostWantedYear.shouldHave(text(year));
        mostWantedLabelWithTime.should(matchText(format("^.*, %s мин", time)));
    }
}
