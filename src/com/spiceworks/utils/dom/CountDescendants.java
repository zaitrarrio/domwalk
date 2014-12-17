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
        // start with zero descendents
        int numberOfDescendents = 0;
        
        // count descendents if there is a first child
        if (node.firstChild != null) {
            numberOfDescendents = 1; // we have at least 1 child
            numberOfDescendents += countDescendents(node.firstChild);

            // count the siblings and their descendents
            if (node.firstChild.nextSibling != null) {
                Node tmp = node.firstChild;
                
                while(tmp.nextSibling != null) {
                    tmp = tmp.nextSibling;
                    numberOfDescendents += countDescendents(tmp) + 1;
                }
            }
            
        }


        return numberOfDescendents;
    }

    public static void main(String[] args) {
        // build our sample hierarchy
        NamedNode header_nav = new NamedNode("div", "id='header_nav'");
        NamedNode avatar_div = header_nav.addChild(new NamedNode("div", "class=\"avatar\">"));
        avatar_div.addChild(new NamedNode("img", "width=\"40\" height=\"50\" style=\"top: -5px; width: 40px; position: relative; height: 50px;\" src=\"/images/users/0006/0629/avatar_big.jpg?1265782357\" alt=\"Avatar_big\""));
        
        header_nav.addChild(new NamedNode("a", "Brian G (Spiceworks)", "class=\"logged_in_user admin profile_link\" title=\"View this user's profile=\" href=\"/profile/show/Brian%20G%20(Spiceworks)\""));
        
        NamedNode ul = header_nav.addChild(new NamedNode("ul"));
        NamedNode li1 = ul.addChild(new NamedNode("li", "class=\"\""));

        li1.addChild(new NamedNode("a", "Inbox", "id=\"header_inbox_link\" title=\"You have no unread messages\" href=\"/messages/inbox\" name=\"header_inbox_link\""));

        NamedNode li2 = ul.addChild(new NamedNode("li", "class=\"last\""));
        li2.addChild(new NamedNode("a", "Logout", "title=\"Log out of Spiceworks\" href=\"logout\""));
        
        header_nav.addChild(new NamedNode("form", "id=\"search_box\" target=\"_top\" method=\"get\" autocomplete=\"off\" action=\"/search\" name=\"search_box\""));
        
        System.out.println("Content of sample DOM is: ");
        header_nav.dump(System.out);
        
        // Could have used a static method but requirements for function described non-static class method
        CountDescendants counter = new CountDescendants();
        counter.countDescendents(header_nav);
        System.out.println("Number of descendants in sample DOM is: " + counter.countDescendents(header_nav));
    }
}
