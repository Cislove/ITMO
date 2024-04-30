package View;

/**
 * Класс описывающий передатчик ответов пользователю. Имеет полный функционал для превращения готового ответа в необходимый вид
 * @author Ильнар Рахимов
 */
public class Receiver implements acceptable{
    /**
     * Метод отправки ответов пользователю
     */
    @Override
    public void print(String str) {
        System.out.print(str);
    }
}
