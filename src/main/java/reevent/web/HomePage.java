package reevent.web;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

public class HomePage extends Template {
    BookmarkablePageLink signInLink;
    public HomePage() {
        signInLink = new BookmarkablePageLink("signInLink", FacebookConnectPage.class);
        add(signInLink);
    }
}
