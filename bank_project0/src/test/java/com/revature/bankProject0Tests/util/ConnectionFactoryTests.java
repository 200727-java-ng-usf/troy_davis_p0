package com.revature.bankProject0Tests.util;

import com.revature.bankProject0.util.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ConnectionFactoryTests {
    private ConnectionFactory sut;

    @Before
    public void setup() {
        sut = ConnectionFactory.getInstance();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void ensureConnectionFactoryIsSingleton() {
        ConnectionFactory c1 = ConnectionFactory.getInstance();
        ConnectionFactory c2 = ConnectionFactory.getInstance();

        assertSame(c1, c2);
    }

    @Test
    public void ensureThatAConnectionIsObtained() {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        assertNotNull(conn);
    }
}
