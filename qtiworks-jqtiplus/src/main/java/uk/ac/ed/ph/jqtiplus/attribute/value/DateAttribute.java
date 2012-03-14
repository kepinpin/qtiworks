/* Copyright (c) 2012, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.jqtiplus.attribute.value;

import uk.ac.ed.ph.jqtiplus.attribute.SingleAttribute;
import uk.ac.ed.ph.jqtiplus.exception.QTIParseException;
import uk.ac.ed.ph.jqtiplus.node.XmlNode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Attribute with Date value.
 * 
 * @author Jiri Kajaba
 */
public class DateAttribute extends SingleAttribute<Date> {

    private static final long serialVersionUID = 2736828962257037608L;

    /** Date formatting pattern. */
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Constructs attribute.
     * 
     * @param parent attribute's parent
     * @param localName attribute's localName
     */
    public DateAttribute(XmlNode parent, String localName) {
        super(parent, localName);
    }

    /**
     * Constructs attribute.
     * 
     * @param parent attribute's parent
     * @param localName attribute's localName
     * @param defaultValue attribute's default value
     */
    public DateAttribute(XmlNode parent, String localName, Date defaultValue) {
        super(parent, localName, defaultValue);
    }

    /**
     * Constructs attribute.
     * 
     * @param parent attribute's parent
     * @param localName attribute's localName
     * @param value attribute's value
     * @param defaultValue attribute's default value
     * @param required is this attribute required
     */
    public DateAttribute(XmlNode parent, String localName, Date value, Date defaultValue, boolean required) {
        super(parent, localName, value, defaultValue, required);
    }

    @Override
    protected Date parseValue(String value) {
        if (value == null || value.length() == 0) {
            throw new QTIParseException("Invalid datetime '" + value + "'. Length is not valid.");
        }

        try {
            return format.parse(value);
        }
        catch (final ParseException ex) {
            throw new QTIParseException("Invalid datetime '" + value + "'.", ex);
        }
    }

    @Override
    public String valueToString() {
        return getValue() != null ? format.format(getValue()) : "";
    }

    @Override
    public String defaultValueToString() {
        return getDefaultValue() != null ? format.format(getDefaultValue()) : "";
    }
}