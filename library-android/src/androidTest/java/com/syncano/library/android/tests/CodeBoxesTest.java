package com.syncano.library.android.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.syncano.library.android.Config;
import com.syncano.library.android.SyncanoAndroid;
import com.syncano.library.api.Response;
import com.syncano.library.choice.RuntimeName;
import com.syncano.library.data.CodeBox;
import com.syncano.library.data.RunCodeBoxResult;

import java.util.List;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class CodeBoxesTest extends ApplicationTestCase<Application> {

    private static final String TAG = CodeBoxesTest.class.getSimpleName();

    private static final String EXPECTED_RESULT = "this is message from our Codebox";

    private SyncanoAndroid syncano;

    public CodeBoxesTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        syncano = new SyncanoAndroid(Config.API_KEY, Config.INSTANCE_NAME);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCodeBoxes() throws InterruptedException {

        String codeBoxName = "CodeBox Test";
        String codeBoxNewName = "CodeBox Test New";
        RuntimeName runtime = RuntimeName.NODEJS;
        String source = "var msg = '" + EXPECTED_RESULT + "'; console.log(msg);";

        final CodeBox newCodeBox = new CodeBox();
        newCodeBox.setName(codeBoxName);
        newCodeBox.setRuntimeName(runtime);
        newCodeBox.setSource(source);

        CodeBox codeBox;

        // ----------------- Create -----------------
        Response <CodeBox> responseCreateCodeBox = syncano.createCodeBox(newCodeBox).send();

        assertEquals(Response.HTTP_CODE_CREATED, responseCreateCodeBox.getHttpResultCode());
        assertNotNull(responseCreateCodeBox.getData());
        codeBox = responseCreateCodeBox.getData();

        // ----------------- Get One -----------------
        Response <CodeBox> responseGetCodeBox = syncano.getCodeBox(codeBox.getId()).send();

        assertEquals(Response.HTTP_CODE_SUCCESS, responseGetCodeBox.getHttpResultCode());
        assertNotNull(responseGetCodeBox.getData());
        assertEquals(codeBox.getName(), responseGetCodeBox.getData().getName());
        assertEquals(codeBox.getRuntimeName(), responseGetCodeBox.getData().getRuntimeName());
        assertEquals(codeBox.getSource(), responseGetCodeBox.getData().getSource());

        // ----------------- Update -----------------
        codeBox.setName(codeBoxNewName);
        Response <CodeBox> responseUpdateCodeBox = syncano.updateCodeBox(codeBox).send();

        assertEquals(Response.HTTP_CODE_SUCCESS, responseUpdateCodeBox.getHttpResultCode());
        assertNotNull(responseUpdateCodeBox.getData());
        assertEquals(codeBox.getName(), responseUpdateCodeBox.getData().getName());
        assertEquals(codeBox.getRuntimeName(), responseUpdateCodeBox.getData().getRuntimeName());
        assertEquals(codeBox.getSource(), responseUpdateCodeBox.getData().getSource());

        // ----------------- Get List -----------------
        Response <List<CodeBox>> responseGetCodeBoxes = syncano.getCodeBoxes().send();

        assertNotNull(responseGetCodeBoxes.getData());
        assertTrue("List should contain at least one item.", responseGetCodeBoxes.getData().size() > 0);

        // ----------------- Run -----------------
        Response <RunCodeBoxResult> responseRunCodeBox = syncano.runCodeBox(codeBox.getId()).send();

        assertEquals(responseRunCodeBox.getHttpReasonPhrase(), Response.HTTP_CODE_SUCCESS, responseRunCodeBox.getHttpResultCode());
        assertNotNull(responseRunCodeBox.getData());

        // ----------------- Delete -----------------
        Response <CodeBox> responseDeleteCodeBox = syncano.deleteCodeBox(codeBox.getId()).send();

        assertEquals(Response.HTTP_CODE_NO_CONTENT, responseDeleteCodeBox.getHttpResultCode());

        // ----------------- Get One -----------------
        Response <CodeBox> responseGetOneCodeBox = syncano.getCodeBox(codeBox.getId()).send();

        // After delete, CodeBox should not be found.
        assertEquals(Response.HTTP_CODE_NOT_FOUND, responseGetOneCodeBox.getHttpResultCode());
    }
}