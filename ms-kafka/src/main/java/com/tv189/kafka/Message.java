/**
 * @author dingli
 * @date 2018年9月18日
 */
package com.tv189.kafka;

import java.util.Date;

import lombok.Data;

/**
 * 消息体
 *
 * @author dingli02
 * @date 2018/09/18 16:24
 */
@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳
}
