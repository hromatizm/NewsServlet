package com.gmail.hromatizm

import com.google.gson.Gson
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import javax.persistence.TypedQuery
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(
    name = "NewsApi",
    urlPatterns = ["/getNews"] // В корне приложения сервлет будет слушать этот путь
)
class Servlet : HttpServlet() {

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "UTF-8" // Для поддержки кирилицы в запросе
        response.characterEncoding = "UTF-8" // И в ответе


        val firstLines = request.getParameter("first_lines")
        val name = request.getParameter("name")
        val creationDate = request.getParameter("creation_date")
        val primiereDate = request.getParameter("primiere_date")
        val publicPrimiereDate = request.getParameter("public_primiere_date")
        val lastDate = request.getParameter("last_date")
        val comment = request.getParameter("comment")

        // Подключаемся к hibernate:
        val registry = StandardServiceRegistryBuilder().configure().build()
        val sessionFactory: SessionFactory = MetadataSources(registry).buildMetadata()
            .buildSessionFactory()
        val session: Session = sessionFactory.openSession()

        // Сюда будем писать список песен, который будет возвращаться
        var list: MutableList<Article>

        session.use { ses ->
            val query = "select s from Song s"
            val q: TypedQuery<Article> = ses.createQuery(query, Article::class.java)
            list = q.resultList

            // Вывод html
//            response.contentType = "text/html;Windows-1251"
//
//            list.forEach { song ->
//                response.writer.println("<p> ${song.id} <b>Название:</b> ${song.name} <b>Первая строка:</b> ${song.firstLines} <b>Год создания:</b> ${song.creationDate}</p>")
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"

            response.writer.write("{\"songs\": [")
            list.forEachIndexed { index, song ->
                val json = Gson().toJson(song)
                response.writer.write(json)
                if (index != list.size - 1) {
                    response.writer.write(",")
                }
            }
            response.writer.write("] }")

        }


        /*
        // criteriaBuilder - позволяет писать запросы через объектный синтаксис
        val builder = session.criteriaBuilder
        // Создаем CriteriaQuery - указываем класс, объект которого будем испльзовать для запроса (их будем отбирать):
        val posQuery: CriteriaQuery<com.gmail.hromatizm.Song> = builder.createQuery(com.gmail.hromatizm.Song::class.java)
        // Указываем начальную позицию Query, к которой потом будем добавлять разные папаметры ниже в where
        val rootPos: Root<com.gmail.hromatizm.Song> = posQuery.from(com.gmail.hromatizm.Song::class.java)

        posQuery.select(rootPos) // Выполняем where запрос (select)
            // Указываем по какому полю сортируем результат
            .orderBy(builder.asc(rootPos.get<com.gmail.hromatizm.Song>("id")))
        // Формируем TypedQuery, из которого потом будем отбирать список магазинов для отправки клиенту:
        val posTypedQuery: TypedQuery<com.gmail.hromatizm.Song> = session.createQuery(posQuery)


        // Следующий запрос будет одну цифру: возвращать кол-во магазинов, удовлетворяющих критерию:
        val posCountQuery = builder.createQuery(Long::class.java) // Отличается от предыдущего классом
        val rootPosCount = posCountQuery.from(com.gmail.hromatizm.Song::class.java)

//            val countWhere = builder.and(
//                builder.between(rootPosCount.get("lat"), latFrom, latTo),
//                builder.between(rootPosCount.get("lon"), lonFrom, lonTo)
//            )

        posCountQuery.select(builder.count(rootPosCount))
//            count = session.createQuery(posCountQuery).singleResult

        // Ограничиваем результат диапазоном строк. Таким способом удобно делать пейджинг.
        posTypedQuery.firstResult = 0
        posTypedQuery.maxResults = 100
//            posTypedQuery.firstResult = from.toInt()
//            posTypedQuery.maxResults = pageSize.toInt()

        list = posTypedQuery.resultList // Получаем результат
    }

    val result = list.forEach { it.toString() }

    response.writer.println(
        result
    )

         */
    }
}