/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spiceworks.utils.dom;

import java.io.PrintStream;

/**
 *
 * @author Zay
 */
public class NamedNode extends Node {
    private String name;
    private String text;
    private String attributes;
    
    
    /**
     * Constructs a named node with no attribute values
     * 
     * @param name name of the node
     */
    public NamedNode(String name)
    {
        this.name = name;
    }

    /**
     * Constructs a named node with attribute values
     * 
     * @param name          name of the nome
     * @param attributes    a string representing the attributes set on this node (should be a map of values in reality)
     */
    public NamedNode(String name, String attributes)
    {
        this(name);
        /** this is really a map of values but treat as a flat string for now **/
        this.attributes = attributes;
    }

    /**
     * Constructs a named node with body text and attribute values
     * 
     * @param name          name of the node
     * @param text          body text for the node
     * @param attributes    a string representing the attributes set on this node (should be a map of values in reality)
     */
    public NamedNode(String name, String text, String attributes)
    {
        this(name, attributes);
        this.text = text;
                
    }
    
    public <T extends Node> T addChild(T child) {
        /** if this is our first child then the child does not yet have any siblings **/
        if (firstChild == null) {
            firstChild = child;
        }
        /** 
         * if this is a new child and we already have a first child
         * but no siblings, then we now have a sibling
         */
        else if (firstChild.nextSibling != null) {
            Node tmp = firstChild;
            while(tmp.nextSibling != null) 
                tmp = tmp.nextSibling;
            
            tmp.nextSibling = child;
        }
        /**
         * this is the case where we have a first child and a sibling
         * so now we add to the tail of our siblings linked list
         */
        else {
            firstChild.nextSibling = child;
        }
        return child;
    }
    public String getName()
    {
        return name;
    }

    private NamedNode firstChild()
    {
        return firstChild instanceof NamedNode ? (NamedNode)firstChild : null;
    }
            
    private NamedNode nextSibling()
    {
        return nextSibling instanceof NamedNode ? (NamedNode)nextSibling : null;
    }

    void dump(PrintStream out) {
        dump("", out);
    }    
    
    void dump(String indent, PrintStream out) {
        out.print(indent + "<" + name);
        if ((attributes != null) && !attributes.isEmpty() ) {
            out.print(" ");
            out.print(attributes);
        }
        out.print(">");

        if ((text != null) && !text.isEmpty() ) {
            out.print(text);
        }
        
        NamedNode fc = firstChild();
        if (fc != null) {
            out.println();
            fc.dump(indent + "  ", out);

            NamedNode tmp = fc;
            while(tmp.nextSibling() != null)  {
                tmp = tmp.nextSibling();
                if (tmp == null)
                    break;
                tmp.dump(indent + "  ", out);
            }
        }
        
        
        out.println(indent + "</" + name + ">");
    }
    
    public int countDescendents()
    {
        int descendants = 0;
        NamedNode fc = firstChild();
        if (fc != null) {

            NamedNode tmp = fc;
            while(tmp.nextSibling() != null)  {
                descendants++;
                tmp = tmp.nextSibling();
                if (tmp == null)
                    break;
                
                descendants += tmp.countDescendents();
                
            }
        }
        
        return descendants;
    }
}
