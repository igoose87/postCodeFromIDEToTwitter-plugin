import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/*
1. Получаем выделенный текст
2. Кодируем в URLEncoding
3. Склеиваем со ссылкой
4. Переходим по ней
*/
public class TweetAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Step 1
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();

        // Step 2
        String encoded = "";
        try {
            encoded = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Step 3
        String url = String.format("https://twitter.com/intent/tweet?text=%s", encoded);
        Messages.showMessageDialog(url, "Tweet Action", Messages.getInformationIcon());

        // Step 4
        BrowserUtil.browse(url);
    }

    @Override
    public boolean isDumbAware(){
        return false;
    }
}