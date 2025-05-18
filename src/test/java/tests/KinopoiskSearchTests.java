package tests;

import com.codeborne.selenide.Configuration;
import enums.Navigator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.KinopoiskMainPage;

import static com.codeborne.selenide.Selenide.open;

public class KinopoiskSearchTests extends BaseTest {

    KinopoiskMainPage page = new KinopoiskMainPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://www.kinopoisk.ru";
    }

    @BeforeEach
    void init() {
        open("/");
    }

    @ParameterizedTest(name = "Для поискового запроса {0} - найден один наиболее подходящий фильм")
    @ValueSource(strings = {"Интерстеллар", "Падмавати", "Рапунцель: "})
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @DisplayName("Проверка блока 'Скорее всего вы ищете'")
    void searchMovieByNameCheckSucceed(String movieName) {
        page.setSearchInput(movieName)
                .confirmSearching();
        page.checkMostWantedHas(movieName);
    }

    @ParameterizedTest(name = "Для поискового запроса {0} - отображается время {1} мин и год {2}")
    @CsvSource(value = {
            "Оно. Новая глава|102|2024",
            "Падмавати|163|2018",
            "Достать ножи|130|2019"
    }, delimiter = '|')
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @DisplayName("Проверка блока 'Скорее всего вы ищете' : наличие времени и года у зарубежного фильма")
    void checkMoviesYearAndTime(String movieName, String time, String year) {
        page.setSearchInput(movieName)
                .confirmSearching();
        page.checkMostWantedHasTimeAndYear(time, year);
    }

    @EnumSource(Navigator.class)
    @ParameterizedTest(name = "Проверка ссылок навигатора : переход по лейблу {0}")
    @Tags({
            @Tag("WEB")
    })
    @DisplayName("Проверка навигатора : переходы по лейблам")
    void checkTopNavigator(Navigator navigator) {
        page.goWideSearch()
                .goSearchBestFilms();
        page.checkNavigatorTransfers(navigator.description, navigator.path);
    }
}
