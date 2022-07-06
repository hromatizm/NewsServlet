package com.gmail.hromatizm

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

object HibernateInsertUtil {

    fun insetArticleList(list: List<Article>) {
        list.forEach(::insertArticle)
    }

    fun insertArticle(article: Article) {
        val config = Configuration()
            .configure()
            .addAnnotatedClass(Article::class.java)

        val reg = StandardServiceRegistryBuilder()
            .applySettings(config.properties)
            .build()

        val sf: SessionFactory = config.buildSessionFactory(reg)
        val session: Session = sf.openSession()

        val tx: Transaction = session.beginTransaction()
        session.saveOrUpdate(article)

        tx.commit()
    }
}