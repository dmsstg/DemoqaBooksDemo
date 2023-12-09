package com.demoqa.books.base;

import com.demoqa.books.pages.*;

import java.lang.reflect.InvocationTargetException;

import static com.demoqa.books.utils.JavaUtils.*;

public class PageProvider {
    private BookStorePage bookStorePage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private BookPage bookPage;

    public BookStorePage getBookStorePage() {
        return (bookStorePage == null) ? (bookStorePage = new BookStorePage()) : (bookStorePage);
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? (loginPage = new LoginPage()) : (loginPage);
    }

    public ProfilePage getProfilePage() {
        return (profilePage == null) ? (profilePage = new ProfilePage()) : (profilePage);
    }

    public BookPage getBookPage() {
        return (bookPage == null) ? (bookPage = new BookPage()) : (bookPage);
    }

    public static PageBase getPageByUrl(String url) {
        if (url.contains("?book=")){
            url = url.substring(0,url.indexOf("?book=")+6);
        }
        Class<?>[] classes = getAllDeclaredFieldClasses(PageProvider.class);
        classes = getAllSubclasses(classes,PageBase.class);
        for (Class<?> each : classes){
            try {
                PageBase obj = (PageBase) Class.forName(each.getName()).getConstructor().newInstance();
                if (obj.PAGE_URL.equals(url)) {
                    return obj;
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


}


