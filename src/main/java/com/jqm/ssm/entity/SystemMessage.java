package com.jqm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class SystemMessage implements Serializable {
    //自增主键,所属表字段为d_system_message.id
    private Integer id;

    //通知类型：1系统内部消息 2消息推送 4公告通知 5警报,所属表字段为d_system_message.message_type
    private Byte messageType;

    //通知手段：1短信 2邮件,所属表字段为d_system_message.message_method
    private Byte messageMethod;

    //标题,所属表字段为d_system_message.title
    private String title;

    //通知内容,所属表字段为d_system_message.content
    private String content;

    //状态,所属表字段为d_system_message.status
    private Byte status;

    //记录生成人,所属表字段为d_system_message.create_person
    private String createPerson;

    //记录生成时间,所属表字段为d_system_message.create_date
    private Date createDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    public Byte getMessageMethod() {
        return messageMethod;
    }

    public void setMessageMethod(Byte messageMethod) {
        this.messageMethod = messageMethod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", messageType=").append(messageType);
        sb.append(", messageMethod=").append(messageMethod);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        messageType("message_type"),
        messageMethod("message_method"),
        title("title"),
        content("content"),
        status("status"),
        createPerson("create_person"),
        createDate("create_date");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Column(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}