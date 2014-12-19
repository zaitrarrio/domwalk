/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spiceworks.utils.dom;

/**
 *
 * @author Zay
 */
public class CountDescendants {
    
    public int countDescendents(Node node) {
        int numberOfDescendents = 0;
        
        for (Node tmp = node.firstChild; tmp != null; tmp = tmp.nextSibling) {
            numberOfDescendents += countDescendents(tmp) + 1;
        }
        
        return numberOfDescendents;
    }

    public static void main(String[] args) {
        // build our sample hierarchy
        ContentNode header_nav = new ContentNode("div", "id='header_nav'");
        ContentNode avatar_div = header_nav.addChild(new ContentNode("div", "class=\"avatar\">"));
        avatar_div.addChild(new ContentNode("img", "width=\"40\" height=\"50\" style=\"top: -5px; width: 40px; position: relative; height: 50px;\" src=\"/images/users/0006/0629/avatar_big.jpg?1265782357\" alt=\"Avatar_big\""));
        
        header_nav.addChild(new ContentNode("a", "Brian G (Spiceworks)", "class=\"logged_in_user admin profile_link\" title=\"View this user's profile=\" href=\"/profile/show/Brian%20G%20(Spiceworks)\""));
        
        ContentNode ul = header_nav.addChild(new ContentNode("ul"));
        ContentNode li1 = ul.addChild(new ContentNode("li", "class=\"\""));

        li1.addChild(new ContentNode("a", "Inbox", "id=\"header_inbox_link\" title=\"You have no unread messages\" href=\"/messages/inbox\" name=\"header_inbox_link\""));

        ContentNode li2 = ul.addChild(new ContentNode("li", "class=\"last\""));
        li2.addChild(new ContentNode("a", "Logout", "title=\"Log out of Spiceworks\" href=\"logout\""));
        
        header_nav.addChild(new ContentNode("form", "id=\"search_box\" target=\"_top\" method=\"get\" autocomplete=\"off\" action=\"/search\" name=\"search_box\""));
        
        System.out.println("Content of sample DOM is: ");
        header_nav.dump(System.out);
        
        // Could have used a static method but requirements for function described non-static class method
        CountDescendants counter = new CountDescendants();
        counter.countDescendents(header_nav);
        System.out.println("Number of descendants in sample DOM is: " + counter.countDescendents(header_nav));
    }
}
