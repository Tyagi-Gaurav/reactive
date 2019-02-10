package org.gt.shipping;

public interface MessagingClient {
    void sendMessage(String content, MessageMetaData messageMetaData);

    void close();
}
