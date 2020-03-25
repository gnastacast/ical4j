/**
 * Copyright (c) 2012, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.ical4j.model.component;

import junit.framework.TestSuite;
import net.fortuna.ical4j.model.ComponentTest;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.parameter.TzId;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import java.net.SocketException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * $Id$
 *
 * Created on: 25/11/2008
 *
 * @author fortuna
 */
public class VAvailabilityTest extends ComponentTest {

    /**
     * @param component
     */
    public VAvailabilityTest(String testMethod, VAvailability component) {
        super(testMethod, component);
    }

    /**
     * @return
     * @throws SocketException 
     */
    public static TestSuite suite() throws SocketException {
        TestSuite suite = new TestSuite();

        VAvailability a = new VAvailability();
        suite.addTest(new VAvailabilityTest("testIsCalendarComponent", a));
        suite.addTest(new VAvailabilityTest("testValidationException", a));
        
        UidGenerator g = new RandomUidGenerator();
        a = new VAvailability();
        a.getProperties().add(g.generateUid());
        List<Parameter> tzParams = new ArrayList<>();
        tzParams.add(new TzId(ZoneId.systemDefault().getId()));
        a.getProperties().add(new DtStart<>(tzParams, ZonedDateTime.now()));
        suite.addTest(new VAvailabilityTest("testValidation", a));
        
        return suite;
    }

}
