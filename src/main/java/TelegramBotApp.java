class TelegramBotApp {
    public static void main(String[] args) {
        BotInitialization.initialization();
        DataCaching dataCache = DataCaching.getInstance();
        Thread dcThread = new Thread(dataCache);
        dcThread.start();
    }
}