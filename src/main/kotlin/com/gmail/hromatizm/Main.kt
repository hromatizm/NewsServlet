package com.gmail.hromatizm

import com.gmail.hromatizm.gson.ArticleResult
import retrofit2.Response

fun main(args: Array<String>) {

    val articleList = mutableListOf<Article>()
    val callResult: Response<ArticleResult>? = RetrofitUtil.getArticleList()
    callResult?.apply {
        if (this.isSuccessful) {
            this.body()?.value?.forEach { article ->
                articleList.add(
                    Article(
                        id = article.id.toULong(),
                        provider = article.provider.name,
                        datePublished = article.datePublished.replace('T', ' '),
                        language = article.language,
                        url = article.url,
                        title = article.title,
                        description = article.description,
                        body = article.body,
                        imageUrl = article.image.url
                    )
                )
            }
        }
    }

    HibernateInsertUtil.insetArticleList(articleList)
    /*
     Class.forName("com.mysql.cj.jdbc.Driver")

     println("раз")
     val url = "jdbc:mysql://95.165.144.4/servlet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
     println("два")
     var con: Connection? = null
     con = DriverManager.getConnection(url, "tomcat", "s\$v!\$t0_PL4\$K4_loo%q")
     println("три")
     val st: Statement = con.createStatement()

     st.maxRows = 100
 // Вариант с executeQuery:
     // Вариант с executeQuery:
     val rs: ResultSet = st.executeQuery("select * from visotsky.songs")

 //    while (rs.next())
 //        System.out.printf("%s %-10s %s %s\n",rs.getString(3), rs.getString(4),
 //            rs.getString(5), rs.getString(6));
     con.close()

     doGet()
 }

 fun doGet() {

     // Подключаемся к hibernate:
     val registry = StandardServiceRegistryBuilder().configure().build()
     val sessionFactory: SessionFactory = MetadataSources(registry).buildMetadata()
         .buildSessionFactory()
     val session: Session = sessionFactory.openSession()

     // Сюда будем писать список песен, который будет возвращаться


     session.use { ses ->
         val query = "select s from Song s"
         val q: TypedQuery<Article> = ses.createQuery(query, Article::class.java)
         val list = q.resultList

 //        list.forEach { song ->
 //            println(song.name)
 //        }

     }
        */
}

