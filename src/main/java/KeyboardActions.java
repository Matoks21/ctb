import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class KeyboardActions extends BotConnection {
    private final String chatId;
    public KeyboardActions(String chatId) {
        this.chatId = chatId;
    }

    public void sendStart() {
        String messageBuilder = convert("Hello") + " \uD83D\uDC4B\uD83C\uDFFB " +
                "\n" + convert("This bot will help you track the current exchange rates");

        SendMessage answer = new SendMessage();

        answer.setText(messageBuilder);
        answer.setChatId(chatId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInline.add(createRowKeyboardWith1Button(convert("GET INFO"), "getInfoAbCourse"));
        rowsInline.add(createRowKeyboardWith1Button(convert("SETTINGS"), "settings"));

        markupInline.setKeyboard(rowsInline);

        answer.setReplyMarkup(markupInline);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setCurrency(Update u, boolean checkUSD, boolean checkEUR) {
        CallbackQuery callbackquery = u.getCallbackQuery();

        EditMessageText editMarkup = new EditMessageText();

        editMarkup.setChatId(chatId);
        editMarkup.setInlineMessageId(callbackquery.getInlineMessageId());
        editMarkup.setText(convert("Select the currency you want to receive the exchange rate in."));
        editMarkup.enableMarkdown(true);
        editMarkup.setMessageId(callbackquery.getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFirst = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineSecond = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineThree = new ArrayList<>();
        InlineKeyboardButton but1 = new InlineKeyboardButton();
        but1.setCallbackData("USD");
        if (checkUSD) {
            but1.setText("\u2705 USD");
        } else {
            but1.setText("USD");
        }

        InlineKeyboardButton but2 = new InlineKeyboardButton("EUR");
        but2.setCallbackData("EUR");

        if (checkEUR) {
            but2.setText("\u2705 EUR");
        } else {
            but2.setText("EUR");
        }
        InlineKeyboardButton but3 = new InlineKeyboardButton(convert("Back"));
        but3.setCallbackData("back");
        rowInlineFirst.add(but1);
        rowInlineSecond.add(but2);
        rowInlineThree.add(but3);

        rowsInline.add(rowInlineFirst);
        rowsInline.add(rowInlineSecond);
        rowsInline.add(rowInlineThree);

        markupInline.setKeyboard(rowsInline);

        editMarkup.setReplyMarkup(markupInline);
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendGetCurrency(String info) {
        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);

        String defaultInfoMessage = null;
        try {
            defaultInfoMessage = info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        answer.setText(defaultInfoMessage);

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("error" + e);
        }
    }


    public void sendCountSignMenu(Update u, int quantityOfSignsAfterDot) {
        CallbackQuery callbackquery = u.getCallbackQuery();

        EditMessageText editMarkup = new EditMessageText();

        editMarkup.setChatId(chatId);
        editMarkup.setInlineMessageId(callbackquery.getInlineMessageId());
        editMarkup.setText(convert("Choose the number of decimal places"));
        editMarkup.enableMarkdown(true);
        editMarkup.setMessageId(callbackquery.getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFirst = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineSecond = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFour = new ArrayList<>();
        InlineKeyboardButton but1 = new InlineKeyboardButton();
        but1.setCallbackData("2");
        if (quantityOfSignsAfterDot == 2) {
            but1.setText("\u2705 2");
        } else {
            but1.setText("2");
        }

        InlineKeyboardButton but2 = new InlineKeyboardButton("3");
        but2.setCallbackData("3");

        if (quantityOfSignsAfterDot == 3) {
            but2.setText("\u2705 3");
        } else {
            but2.setText("3");
        }
        InlineKeyboardButton but3 = new InlineKeyboardButton("4");
        but3.setCallbackData("4");

        if (quantityOfSignsAfterDot == 4) {
            but3.setText("\u2705 4");
        } else {
            but3.setText("4");
        }
        InlineKeyboardButton but4 = new InlineKeyboardButton(convert("BACK"));
        but4.setCallbackData("back");
        rowInlineFirst.add(but1);
        rowInlineSecond.add(but2);
        rowInlineThree.add(but3);
        rowInlineFour.add(but4);


        rowsInline.add(rowInlineFirst);
        rowsInline.add(rowInlineSecond);
        rowsInline.add(rowInlineThree);
        rowsInline.add(rowInlineFour);


        markupInline.setKeyboard(rowsInline);

        editMarkup.setReplyMarkup(markupInline);
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendBankMenu(Update u, String bank) {
        CallbackQuery callbackquery = u.getCallbackQuery();

        EditMessageText editMarkup = new EditMessageText();

        editMarkup.setChatId(chatId);
        editMarkup.setInlineMessageId(callbackquery.getInlineMessageId());
        editMarkup.setText(convert("Select the bank from which you want to receive data"));
        editMarkup.enableMarkdown(true);
        editMarkup.setMessageId(callbackquery.getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFirst = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineSecond = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineFour = new ArrayList<>();
        InlineKeyboardButton but1 = new InlineKeyboardButton();
        but1.setCallbackData("PB");
        if (bank.equals("PB")) {
            but1.setText("\u2705" + convert("PRIVAT"));
        } else {
            but1.setText(convert("PRIVAT"));
        }

        InlineKeyboardButton but2 = new InlineKeyboardButton(convert("MONO"));
        but2.setCallbackData("Monobank");

        if (bank.equals("Monobank")) {
            but2.setText("\u2705" + convert("MONO"));
        } else {
            but2.setText(convert("MONO"));
        }
        InlineKeyboardButton but3 = new InlineKeyboardButton("4");
        but3.setCallbackData("NBU");

        if (bank.equals("NBU")) {
            but3.setText("\u2705" + convert("NBU"));
        } else {
            but3.setText(convert("NBU"));
        }
        InlineKeyboardButton but4 = new InlineKeyboardButton(convert("back"));
        but4.setCallbackData("back");
        rowInlineFirst.add(but1);
        rowInlineSecond.add(but2);
        rowInlineThree.add(but3);
        rowInlineFour.add(but4);


        rowsInline.add(rowInlineFirst);
        rowsInline.add(rowInlineSecond);
        rowsInline.add(rowInlineThree);
        rowsInline.add(rowInlineFour);


        markupInline.setKeyboard(rowsInline);

        editMarkup.setReplyMarkup(markupInline);
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private List<InlineKeyboardButton> createRowKeyboardWith1Button(String text, String callbackData) {
        List<InlineKeyboardButton> rowInlineFirst = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(callbackData);
        rowInlineFirst.add(button);
        return rowInlineFirst;
    }

    private List<InlineKeyboardButton> createRowKeyboardWith3Button(String textB1, String callbackDataB1,
                                                                    String textB2, String callbackDataB2,
                                                                    String textB3, String callbackDataB3,
                                                                    String dataToCompare) {
        List<InlineKeyboardButton> rowInlineFirst = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton(textB1);
        button1.setCallbackData(callbackDataB1);
        if (dataToCompare.equals(callbackDataB1)) {
            button1.setText("\u2705 "+ textB1);
        } else {
            button1.setText(textB1);
        }
        InlineKeyboardButton button2 = new InlineKeyboardButton(textB2);
        button2.setCallbackData(callbackDataB2);
        if (dataToCompare.equals(callbackDataB2)) {
            button2.setText("\u2705 "+ textB2);
        } else {
            button2.setText(textB2);
        }
        InlineKeyboardButton button3 = new InlineKeyboardButton(textB3);
        button3.setCallbackData(callbackDataB3);
        if (dataToCompare.equals(callbackDataB3)) {
            button3.setText("\u2705 "+ textB3);
        } else {
            button3.setText(textB3);
        }
        rowInlineFirst.add(button1);
        rowInlineFirst.add(button2);
        rowInlineFirst.add(button3);
        return rowInlineFirst;
    }

    public void sendSettingsMenu() {

        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText(convert("SETTINGS"));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInline.add(createRowKeyboardWith1Button(convert("Number of decimal places"),"countSign"));
        rowsInline.add(createRowKeyboardWith1Button(convert("BANK"),"bank"));
        rowsInline.add(createRowKeyboardWith1Button(convert("CURRENCY"),"currency"));
        rowsInline.add(createRowKeyboardWith1Button(convert("TIME NOTIFIC"),"timeNotific"));
        rowsInline.add(createRowKeyboardWith1Button(convert("MAIN"),"main"));

        markupInline.setKeyboard(rowsInline);

        answer.setReplyMarkup(markupInline);


        try {
            execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("error" + e);
        }
    }

    public void sendTimeMenu(Update u, String timeSetting){
        CallbackQuery callbackquery = u.getCallbackQuery();

        EditMessageText editMarkup = new EditMessageText();

        editMarkup.setChatId(chatId);
        editMarkup.setInlineMessageId(callbackquery.getInlineMessageId());
        editMarkup.setText(convert("Choose notification time"));
        editMarkup.enableMarkdown(true);
        editMarkup.setMessageId(callbackquery.getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInline.add(createRowKeyboardWith3Button("9","9","10","10","11","11", timeSetting));
        rowsInline.add(createRowKeyboardWith3Button("12","12","13","13","14","14", timeSetting));
        rowsInline.add(createRowKeyboardWith3Button("15","15","16","16","17","17", timeSetting));
        List<InlineKeyboardButton> rowInlineFour = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton("18");
        button1.setCallbackData("18");
        if (timeSetting.equals("18")) {
            button1.setText("\u2705 "+ "18");
        } else {
            button1.setText("18");
        }
        InlineKeyboardButton button2 = new InlineKeyboardButton(convert("Off notific"));
        button2.setCallbackData("off notific");
        if (timeSetting.equals("off notific")) {
            button2.setText("\u274c");
        } else {
            button2.setText(convert("off" ));
        }
        rowInlineFour.add(button1);
        rowInlineFour.add(button2);

        List<InlineKeyboardButton> rowInlineFive = new ArrayList<>();

        InlineKeyboardButton but6 = new InlineKeyboardButton(convert("Back"));
        but6.setCallbackData("back");
        rowInlineFive.add(but6);

        rowsInline.add(rowInlineFour);
        rowsInline.add(rowInlineFive);


        markupInline.setKeyboard(rowsInline);

        editMarkup.setReplyMarkup(markupInline);
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String convert(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }
}