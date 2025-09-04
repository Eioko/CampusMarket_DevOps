package com.Po.Vo;

import com.Po.Entity.Chatter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChatterVO extends Chatter {
    private String senderName;

    private String senderAccount;

    private String senderAvatar;

    private String receiverName;

    private String receiverAccount;

    private String receiverAvatar;

    public String getSenderName() {
        return senderName;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverAccount(){
        return receiverAccount;
    }

    public String getReceiverAvatar() {
        return receiverAvatar;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public void setReceiverAvatar(String receiverAvatar) {
        this.receiverAvatar = receiverAvatar;
    }
}
